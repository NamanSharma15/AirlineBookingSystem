package com.example.database;
import java.sql.*;
import com.example.schemas.User;
import com.example.schemas.Flight;
import java.util.List;
import java.util.ArrayList;

public class Database {
    Statement stml = null;
    Connection c = null;
    public Database(){
    try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:Airline.db");
         System.out.println("Opened database successfully");
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
    public boolean addUser(String name,String ){
        try{
            stml.executeQuery("SELECT * FROM USERS;");
        }
        return false;
    }
    public void closeConnection(){
        try{
            stml.close();
            c.close();
        }catch(Exception e){
            System.out.println(e);
            System.exit(0);
        }
    }
}
