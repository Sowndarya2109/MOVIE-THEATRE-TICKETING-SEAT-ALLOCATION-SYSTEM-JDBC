package com.theatre.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.theatre.bean.Show;
import com.theatre.util.DBUtil;


public class ShowDAO {
	
	public Show findShow(String showID) {
		Connection con = DBUtil.getDBConnection();
		Show show = new Show();
		String query = "SELECT * FROM SHOW_TBL1 WHERE Show_ID = ?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, showID);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
			show.setShowID(rs.getString("show_ID"));
			show.setMovieTitle(rs.getString("movie_title"));
			show.setAuditorium(rs.getString("auditorium"));
			show.setShowDate(rs.getDate("show_Date"));
			show.setShowTime(rs.getString("show_Time"));
			show.setTotalSeats(rs.getInt("total_Seats"));
			show.setAvailableSeats(rs.getInt("available_Seats"));
			
			return show;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public List<Show> viewAllShows(){
		Connection con = DBUtil.getDBConnection();
		List<Show> list = new ArrayList<>();
		String query = "SELECT * FROM SHOW_TBL1 ";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Show show = new Show();
				
				show.setShowID(rs.getString("show_ID"));
				show.setMovieTitle(rs.getString("movie_Title"));
				show.setAuditorium(rs.getString("auditorium"));
				show.setShowDate(rs.getDate("show_Date"));
				show.setShowTime(rs.getString("show_Time"));
				show.setTotalSeats(rs.getInt("total_Seats"));
				show.setAvailableSeats(rs.getInt("available_Seats"));
				
				list.add(show);
			}			
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public boolean insertShow(Show show) {
		Connection con = DBUtil.getDBConnection();
		String query = "INSERT INTO SHOW_TBL1 VALUES(?,?,?,?,?,?,?) ";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, show.getShowID());
			ps.setString(2, show.getMovieTitle());
			ps.setString(3, show.getAuditorium());
			Date d = new Date(show.getShowDate().getTime());
			ps.setDate(4, d);
			ps.setString(5, show.getShowTime());
			ps.setInt(6, show.getTotalSeats());
			ps.setInt(7, show.getAvailableSeats());
			int row = ps.executeUpdate();
			if(row > 0)
				return true;
			return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}		
	}
	public boolean updateAvailableSeats(String showID, int newCount) {
		Connection con = DBUtil.getDBConnection();
		String query = "UPDATE SHOW_TBL1 SET available_Seats = ? WHERE show_ID = ?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, newCount);
			ps.setString(2, showID);
			int row = ps.executeUpdate();
			if(row > 0) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean deleteShow(String showID) {
		Connection con = DBUtil.getDBConnection();
		String checkQuery = "SELECT COUNT(*) FROM BOOKING_TBL WHERE show_ID = ? AND status = 'ACTIVE'";
		String deleteQuery = "DELETE FROM SHOW_TBL1 WHERE show_ID = ?";
		try {
			PreparedStatement checkPs = con.prepareStatement(checkQuery);
			checkPs.setString(1, showID);
			ResultSet rs = checkPs.executeQuery();
			rs.next();
			int count = rs.getInt(1);
			if(count == 0) {
				PreparedStatement deletePs = con.prepareStatement(deleteQuery);
				deletePs.setString(1, showID);
				int row = deletePs.executeUpdate();
				return row > 0;
			}
			return false;	
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
}
