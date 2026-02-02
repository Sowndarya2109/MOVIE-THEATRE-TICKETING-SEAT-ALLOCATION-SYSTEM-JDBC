package com.theatre.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.theatre.bean.Booking;
import com.theatre.bean.Show;
import com.theatre.dao.BookingDAO;
import com.theatre.dao.ShowDAO;
import com.theatre.util.ActiveBookingException;
import com.theatre.util.DBUtil;
import com.theatre.util.ShowFullException;
import com.theatre.util.ValidationException;

public class BookingService {	
	public Show viewShowDetails(String showID) {
		ShowDAO show = new ShowDAO();
		if(showID == null || showID.isEmpty())
			return null;
		Show details = show.findShow(showID);
		if(details != null)
			return details;
		else
		    return null;
	}
	public List<Show> viewAllShows(){
		ShowDAO show = new ShowDAO();
		return show.viewAllShows();
	}
	public boolean addNewShow(Show show) {
		ShowDAO dao = new ShowDAO();
		if(show == null)
			return false;
		if(show.getMovieTitle() == null)
			return false;
		if(show.getAuditorium() == null)
			return false;
		if(show.getShowDate() == null)
			return false;
		if(show.getTotalSeats() <= 0)
			return false;		
		if(dao.findShow(show.getShowID()) != null)
			return false;
		return dao.insertShow(show);
	}
	public boolean removeShow(String showID) throws ActiveBookingException {
		ShowDAO show = new ShowDAO();
		if(showID == null)
			return false;
		boolean delete = show.deleteShow(showID);
		if(!delete)
			throw new ActiveBookingException();
		return true;		
	}
	public boolean bookSeats(String showID, String customerName, int seatsRequested) throws ValidationException, ShowFullException{
		ShowDAO show = new ShowDAO();
		BookingDAO booking = new BookingDAO();
		Booking b = new Booking();
		if(showID == null || showID.isEmpty() || customerName == null || customerName.isEmpty() || seatsRequested <= 0) {
			throw new ValidationException();
		}
		Show s = show.findShow(showID);
		if(s == null) 
			return false;
		
		if(seatsRequested > s.getAvailableSeats())
			throw new ShowFullException();
		
		Connection con = DBUtil.getDBConnection();
		try {
			con.setAutoCommit(false);
		
			int newAvailable = s.getAvailableSeats() - seatsRequested;
			boolean seatsUpdate = show.updateAvailableSeats(showID, newAvailable);
			if(!seatsUpdate) {
				con.rollback();
				return false;
			}
			b.setShowID(showID);
			b.setCustomerName(customerName);
			b.setSeatsBooked(seatsRequested);
			b.setStatus("CONFIRMED");
			b.setBookingDate(new java.sql.Date(System.currentTimeMillis()));
			
			boolean record =  booking.recordBooking(b);
			if(!record) {
				con.rollback();
				return false;
			}
			con.commit();
			return true;
		}catch(SQLException e) {
			try {
				con.rollback();
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
			return false;
		}
	}
	public boolean cancelBooking(int bookingID) throws ValidationException{
		BookingDAO booking = new BookingDAO();
		ShowDAO show = new ShowDAO();
		if(bookingID <= 0) {
			throw new ValidationException();
		}
		Booking b = booking.findBooking(bookingID);
		if(b == null)
			return false;
		Show s = show.findShow(b.getShowID());
		if (s == null) 
		    return false; 
		Connection con = DBUtil.getDBConnection();
		try {
			con.setAutoCommit(false);
		    int newAvailable = s.getAvailableSeats() + b.getSeatsBooked();
		    boolean seatsUpdate = show.updateAvailableSeats(s.getShowID(), newAvailable);
		    if(!seatsUpdate) {
		    	con.rollback();
		    	return false;
		    }
			if(!booking.cancelBooking(bookingID)) {
				con.rollback();
				return false;
			}
			con.commit();
			return true;
		}catch(SQLException e) {
			try{
				con.rollback();
			}catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	    return false;	
	}
}
