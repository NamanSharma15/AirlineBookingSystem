package com.example.database;
import java.sql.*;
import com.example.schemas.User;
import com.example.schemas.Flight;
import com.example.schemas.Ticket;
import java.util.List;
import java.util.ArrayList;

public class Database {
    Statement stml = null;
    Connection c = null;
    public Database(){
    try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:Airline.db");
         c.setAutoCommit(false);
         stml = c.createStatement();
      } 
      catch ( Exception e ) {
        c= null;
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        System.exit(1);
      }
    }
    public List<Flight> getFlights(){
        try{
            ResultSet values = stml.executeQuery("SELECT * FROM AIRLINE;");
            List<Flight> flights = new ArrayList<Flight>();
            try{
            while(values.next()){
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
        }catch(Exception e){
            System.out.println("Unable to fetch data from database");
            return flights;
        }
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.exit(1);
            return null;
        }
    }
    public List<User> getUsers(){
        try{
            ResultSet values = stml.executeQuery("SELECT * FROM USERS;");
            List<User> users = new ArrayList<User>();
            try{
            while(values.next()){
                User user = new User(
                values.getString(1),
                values.getString(2),
                values.getString(3),
                values.getString(4));
                users.add(user);
            }
            values.close();
            return users;
        }catch(Exception e){
            System.out.println("Unable to fetch data from database");
            return users;
        }
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.exit(1);
            return null;
        }
    }
    private String getId(){
        int id = 10001;
        try{
            ResultSet values = stml.executeQuery("SELECT * FROM USERS;");
            while(values.next()){
                id+=1;
            }
            return Integer.toString(id);
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.exit(1);
            return null;
        }
    }
    public String getTicketNo(){
        int id = 1001;
        try{
            ResultSet values = stml.executeQuery("Select * from tickets;");
            while(values.next()){
                id+=1;
            }
            return "T"+Integer.toString(id);
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.exit(1);
            return "";
        }
    }
    public boolean addUser(String name,String email,String password){
        try{
            String id = this.getId();
            String query = String.format("Insert into users values('%s','%s','%s','%s');",id,name,email,password);
            stml.executeUpdate(query);
            c.commit();
            return true;
        }catch(Exception e){
            return false;
        }
    }
    public User getUser(String email){
        try{
            String query = String.format("Select * from users where email = '%s'",email);
            ResultSet res = stml.executeQuery(query);
            User user = new User(
                res.getString(1),
                res.getString(2),
                res.getString(3),
                res.getString(4));
            return user;
        }catch(Exception e){
            return null;
        }
    }
    public Flight getFlight(String flight_no){
        try{
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
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.exit(1);
            return null;
        }
    }
    public boolean addTicket(Ticket ticket,String uid,String fid){
        String query = String.format("Insert into tickets values('%s','%s','%s','%s','%s','%s')",
        ticket.ticket_no ,
        ticket.time_of_booking,
        ticket.class_,
        ticket.date,
        uid,
        fid);
        try{
            stml.executeUpdate(query);
            c.commit();
            return true;
        }catch(Exception e){
            return false;
        }
    }
    private String flightByTicket(String ticket_no){
        try{
            String query = String.format("Select fid from tickets where id = '%s'", ticket_no);
            ResultSet values = stml.executeQuery(query);
            return values.getString(1);
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.exit(1);
            return null;
        }
    }
    public Ticket getTicket(String ticket_no,User user){
        try{
            String query = String.format("Select from tickets where id = '%s'", ticket_no); 
            ResultSet res = stml.executeQuery(query);
            Ticket ticket = new Ticket(
                res.getString(1),
                res.getString(2),
                res.getString(3),
                res.getString(4),
                user,getFlight(flightByTicket(ticket_no)));
            return ticket;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.exit(1);
            return null;
        }
    }
    public void closeConnection(){
        try{
            stml.close();
            c.commit();
            c.close();
        }catch(Exception e){
            System.out.println(e);
            System.exit(0);
        }
    }
}
