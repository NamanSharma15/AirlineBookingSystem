package com.example;
import  java.util.Scanner;
import java.util.List;
import com.example.auth.Login;
import com.example.auth.Signup;
import com.example.auth.Auth;
import com.example.database.Database;
import com.example.schemas.Flight;
import com.example.schemas.User;
public class App 
{
    public static void main( String[] args )
    {
        Auth authentication = null;
        Scanner sc = new Scanner(System.in);
        Database database = new Database();
        List<Flight> flights = database.getFlights();
        System.out.println(flights.get(0).flight_no);
        sc.close();
        database.closeConnection();
    }
}
