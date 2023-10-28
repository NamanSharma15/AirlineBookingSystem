package com.example.schemas;

public class Ticket {
    public String ticket_no, time_of_booking, seat, class_, date;
    public User user;
    public Flight flight;
    public Ticket(String ticket_no, String time_of_booking, String seat, String class_, String date) {
        this.ticket_no = ticket_no;
        this.time_of_booking = time_of_booking;
        this.seat = seat;
        this.class_ = class_;
        this.date = date;
    }
}
