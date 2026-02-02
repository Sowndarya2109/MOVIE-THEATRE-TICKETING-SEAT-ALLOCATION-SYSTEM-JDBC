package com.theatre.bean;

import java.sql.Date;

public class Show {
	private String showID;
	private String movieTitle;
	private String auditorium;
	private Date showDate;
	private String showTime;
	private int totalSeats;
	private int availableSeats;

	public Show(String showID, String movieTitle, String auditorium, Date showDate, String showTime, int totalSeats, int availableSeats) {
		super();
		this.showID = showID;
		this.movieTitle = movieTitle;
		this.auditorium = auditorium;
		this.showDate = showDate;
		this.showTime = showTime;
		this.totalSeats = totalSeats;
		this.availableSeats = availableSeats;
	}

	public Show() {
		// TODO Auto-generated constructor stub
	}

	public String getShowID() {
		return showID;
	}
	public void setShowID(String showID) {
		this.showID = showID;
	}
	public String getMovieTitle() {
		return movieTitle;
	}
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	public String getAuditorium() {
		return auditorium;
	}
	public void setAuditorium(String auditorium) {
		this.auditorium = auditorium;
	}
	public Date getShowDate() {
		return showDate;
	}
	public void setShowDate(Date showDate) {
		this.showDate = showDate;
	}
	public String getShowTime() {
		return showTime;
	}
	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}
	public int getTotalSeats() {
		return totalSeats;
	}
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}
	public int getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}
}
