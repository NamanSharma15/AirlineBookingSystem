package com.example.database;

import java.sql.*;
import com.example.schemas.User;
import com.example.structures.Flights;
import com.example.structures.Strings;
import com.example.structures.Users;
import com.example.schemas.Flight;
import com.example.schemas.Ticket;

public class Database {
    Statement stml = null;
    Connection c = null;

    public Database() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:Airline.db");
            c.setAutoCommit(false);
            stml = c.createStatement();
        } catch (Exception e) {
            c = null;
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(1);
        }
    }

    public Flights getFlights() {
        try {
            ResultSet values = stml.executeQuery("SELECT * FROM AIRLINE;");
           Flights flights = new Flights(200);
            try {
                while (values.next()) {
                    Flight flight = new Flight(
                            values.getString(1),
                            values.getString(2),
                            values.getString(3),
                            values.getString(4),
                            values.getString(5),
                            values.getString(6));
                    flights.add(flight);
                }
                values.close();
                return flights;
            } catch (SQLException e) {
                System.out.println("Unable to fetch data from database");
                return flights;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(1);
            return null;
        }
    }

    public Users getUsers() {
        try {
            ResultSet values = stml.executeQuery("SELECT * FROM USERS;");
            Users users = new Users(100);   
            try {
                while (values.next()) {
                    User user = new User(
                            values.getString(1),
                            values.getString(2),
                            values.getString(3),
                            values.getString(4));
                    users.add(user);
                }
                values.close();
                return users;
            } catch (SQLException e) {
                System.out.println("Unable to fetch data from database");
                return users;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(1);
            return null;
        }
    }

    private String getId() {
        int id = 10001;
        try {
            ResultSet values = stml.executeQuery("SELECT * FROM USERS;");
            while (values.next()) {
                id += 1;
            }
            return Integer.toString(id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(1);
            return null;
        }
    }

    public String getTicketNo() {
        int id = 1001;
        try {
            ResultSet values = stml.executeQuery("Select * from tickets;");
            while (values.next()) {
                id += 1;
            }
            return "T" + Integer.toString(id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(1);
            return "";
        }
    }

    public boolean addUser(String name, String email, String password) {
        try {
            String id = this.getId();
            String query = String.format("Insert into users values('%s','%s','%s','%s');", id, name, email, password);
            stml.executeUpdate(query);
            c.commit();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public User getUser(String email) {
        try {
            String query = String.format("Select * from users where email = '%s'", email);
            ResultSet res = stml.executeQuery(query);
            User user = new User(
                    res.getString(1),
                    res.getString(2),
                    res.getString(3),
                    res.getString(4));
            return user;
        } catch (SQLException e) {
            return null;
        }
    }

    public User getUserbyId(String id) {
        try {
            String query = String.format("Select * from users where Uid = '%s'", id);
            ResultSet res = stml.executeQuery(query);
            User user = new User(
                    res.getString(1),
                    res.getString(2),
                    res.getString(3),
                    res.getString(4));
            return user;
        } catch (SQLException e) {
            return null;
        }
    }

    public Flight getFlight(String flight_no) {
        try {
            String query = String.format("Select * from airline where Flight_No = '%s'", flight_no);
            ResultSet values = stml.executeQuery(query);
            Flight flight = new Flight(
                    values.getString(1),
                    values.getString(2),
                    values.getString(3),
                    values.getString(4),
                    values.getString(5),
                    values.getString(6));
            return flight;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(1);
            return null;
        }
    }

    public boolean addTicket(Ticket ticket, String uid, String fid) {
        String query = String.format("Insert into tickets values('%s','%s','%s','%s','%s','%s')",
                ticket.ticket_no,
                ticket.time_of_booking,
                ticket.class_,
                ticket.date,
                uid,
                fid);
        try {
            stml.executeUpdate(query);
            c.commit();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    private String flightByTicket(String ticket_no) {
        try {
            String query = String.format("Select fid fro/m tickets where id = '%s'", ticket_no);
            ResultSet values = stml.executeQuery(query);
            return values.getString(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(1);
            return null;
        }
    }

    public void setPass(Ticket ticket, String seat_no, String time) {
        try {
            String query = String.format("INSERT INTO PASSES VALUES('%s','%s','%s')", ticket.ticket_no, seat_no, time);
            stml.executeUpdate(query);
            c.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    public Strings getSeats(Flight flight) {
        try {
           Strings booked_seats = new Strings(200);
            String query = String.format(
                    "Select seat_no from passes where tid in (select id from tickets where fid='%s');",
                    flight.flight_no);
            ResultSet values = stml.executeQuery(query);
            while (values.next()) {
                booked_seats.add(values.getString(1));
            }
            return booked_seats;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(1);
            return null;
        }
    }

    public Ticket getTicket(String ticket_no) {
        try {
            String query = String.format("Select * from tickets where id = '%s'", ticket_no);
            ResultSet res = stml.executeQuery(query);
            Ticket ticket = new Ticket(
                    res.getString(1),
                    res.getString(2),
                    res.getString(3),
                    res.getString(4),
                    getUserbyId(res.getString(5)), getFlight(flightByTicket(ticket_no)));
            return ticket;
        } catch (SQLException e) {
            return null;
        }
    }

    public void getUserHistory(User user){
        try{
            String query = String.format("Select * from tickets where uid = '%s'", user.u_id); 
            ResultSet res = stml.executeQuery(query);
            System.out.print("|");
            while (res.next()) {
                System.out.println(
                res.getString(1)+"|"+
                res.getString(2)+"|"+
                res.getString(3)+"|"+
                res.getString(4)+"|\n");
   }
        }catch(SQLException e){
            System.exit(1);
        }
    }
    public boolean checkPass(Ticket ticket){
        boolean valid = true;
        try {
         String query = String.format(
"Select tid from passes;",ticket.ticket_no);
        ResultSet res = stml.executeQuery(query);
        while (res.next()) {
            if(res.getString(1).equals(ticket.ticket_no)){
                valid = false;
            }
        }
        return valid;
        } catch (Exception e) {
            System.out.println(e.getMessage());
             System.exit(1);
             return false;
        }
    }
    public void closeConnection() {
        try {
            stml.close();
            c.commit();
            c.close();
        } catch (SQLException e) {
            System.out.println(e);
            System.exit(0);
        }
    }
}
