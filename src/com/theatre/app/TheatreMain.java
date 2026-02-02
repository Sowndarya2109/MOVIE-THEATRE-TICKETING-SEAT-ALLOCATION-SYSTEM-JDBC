package com.theatre.app;

import java.util.Scanner;

import com.theatre.service.BookingService;

public class TheatreMain {
		 private static BookingService bookingService;
		 public static void main(String[] args) {
		 bookingService = new BookingService();
		 Scanner sc = new Scanner(System.in);
		 System.out.println("--- Theatre Booking Console ---");
		 try {
		 boolean r = bookingService.bookSeats("SHW2001","Rohan Mehra",2);
		 System.out.println(r ? "BOOKED" : "FAILED");
		 } catch(Exception e) { 
			 System.out.println(e); 
			 }
		 try {
		 boolean r = bookingService.bookSeats("SHW1001","Neelima Rao",3);
		 System.out.println(r ? "BOOKED" : "FAILED");
		 } catch(Exception e) { 
			 System.out.println(e); 
			 }
		 try {
		 boolean r = bookingService.cancelBooking(120001);
		 System.out.println(r ? "CANCELLED" : "FAILED");
		 } catch(Exception e) { 
			 System.out.println(e);
			 }
		 sc.close();
		 }
		
}
