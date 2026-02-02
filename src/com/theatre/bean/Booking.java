package com.theatre.bean;

import java.sql.Date;

public class Booking {
	private int bookingID;
	private String showID;
	private String customerName;
	private int seatsBooked;
	private Date bookingDate;
	private String status;
	
	public Booking(int bookingID, String showID, String customerName, int seatsBooked, Date bookingDate,
			String status) {
		super();
		this.bookingID = bookingID;
		this.showID = showID;
		this.customerName = customerName;
		this.seatsBooked = seatsBooked;
		this.bookingDate = bookingDate;
		this.status = status;
	}
	
	public Booking() {
		// TODO Auto-generated constructor stub
	}

	public int getBookingID() {
		return bookingID;
	}

	public void setBookingID(int bookingID) {
		this.bookingID = bookingID;
	}

	public String getShowID() {
		return showID;
	}

	public void setShowID(String showID) {
		this.showID = showID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getSeatsBooked() {
		return seatsBooked;
	}

	public void setSeatsBooked(int seatsBooked) {
		this.seatsBooked = seatsBooked;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
