package com.example;
import  java.util.Scanner;
import java.util.List;
import com.example.database.Database;
import com.example.schemas.Flight;
public class App 
{
    public static void main( String[] args )
    {
        Scanner sc = new Scanner(System.in);
        Database database = new Database();
        List<Flight> flights = database.getFlights();
        System.out.println(flights.get(0).flight_no);
        sc.close();
        database.closeConnection();
    }
}
