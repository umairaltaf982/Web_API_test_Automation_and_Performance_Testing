package com.bookerapi.utils;

import com.bookerapi.model.Booking;
import com.bookerapi.model.BookingDates;

public class TestDataGenerator {
    public static Booking createTestBooking() {
        Booking booking = new Booking();
        booking.setFirstname("John");
        booking.setLastname("Doe");
        booking.setTotalprice(150);
        booking.setDepositpaid(true);
        
        BookingDates dates = new BookingDates();
        dates.setCheckin("2024-01-01");
        dates.setCheckout("2024-01-05");
        booking.setBookingdates(dates);
        
        booking.setAdditionalneeds("Breakfast");
        return booking;
    }
}