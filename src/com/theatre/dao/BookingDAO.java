package com.theatre.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.theatre.bean.Booking;
import com.theatre.util.DBUtil;

public class BookingDAO {
	
	public int generateBookingID()  {
		Connection con = DBUtil.getDBConnection();
	    String query = "SELECT bookingId_seq2.NEXTVAL FROM DUAL";
	    try {
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int bookingID = rs.getInt(1);
			return bookingID;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	public Booking findBooking(int bookingID) {
		Connection con = DBUtil.getDBConnection();
	    String query = "SELECT * FROM BOOKING_TBL WHERE booking_ID = ?";
	    try {
	        PreparedStatement ps = con.prepareStatement(query);
	        ps.setInt(1, bookingID);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                Booking booking = new Booking();
	                booking.setBookingID(rs.getInt("booking_ID"));
	                booking.setShowID(rs.getString("show_ID"));
	                booking.setCustomerName(rs.getString("customer_Name"));
	                booking.setSeatsBooked(rs.getInt("seats_Booked"));
	                booking.setBookingDate(rs.getDate("booking_Date"));
	                booking.setStatus(rs.getString("status"));
	                return booking;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	public boolean recordBooking(Booking booking) {
		booking.setBookingID(generateBookingID());
		Connection con = DBUtil.getDBConnection();
		String query = "INSERT INTO BOOKING_TBL VALUES(?,?,?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, booking.getBookingID());
			ps.setString(2, booking.getShowID());
			ps.setString(3, booking.getCustomerName());
			ps.setInt(4, booking.getSeatsBooked());
			Date d = new Date(booking.getBookingDate().getTime());
			ps.setDate(5, d);
			ps.setString(6, booking.getStatus());
			
			int row = ps.executeUpdate();
			if(row > 0)
				return true;
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean cancelBooking(int bookingID) {
		String query = "UPDATE BOOKING_TBL SET status = 'CANCELLED' WHERE booking_ID = ?";
		try (Connection con = DBUtil.getDBConnection(); 
				PreparedStatement ps = con.prepareStatement(query)) {
			ps.setInt(1, bookingID); 
			int row = ps.executeUpdate(); 
			return row > 0; 
			} catch (SQLException e) { 
				e.printStackTrace(); 
				return false; 
				}
	}
	

}
	
	

