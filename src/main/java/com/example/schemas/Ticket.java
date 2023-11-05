package com.example.schemas;

public class Ticket {
    public String ticket_no, time_of_booking, class_, date;
    public User user;
    public Flight flight;
    public Ticket(String ticket_no, String time_of_booking, String class_, String date) {
        this.ticket_no = ticket_no;
        this.time_of_booking = time_of_booking;
        this.class_ = class_;
        this.date = date;
    }
    public Ticket(String ticket_no, String time_of_booking, String class_, String date,User user,Flight flight){
        this.ticket_no = ticket_no;
        this.time_of_booking = time_of_booking;
        this.class_ = class_;
        this.date = date;
        this.flight  = flight;
        this.user = user;
    }
}
