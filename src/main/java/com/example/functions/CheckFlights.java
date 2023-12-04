package com.example.functions;
import com.example.database.Database;
import com.example.schemas.Flight;
import com.example.structures.Flights;

public interface CheckFlights {
    default void checkFlights(Database database){
        Flights flights = database.getFlights();
        System.out.println("These are details for all the flights: \n");
        System.out.println("Flight No. | Destination | Gate No. | Time | Status");
        for(int i=0;i<flights.i;i++){
            Flight flight = flights.get(i);
            System.out.printf("%s | %s | %s | %s | %s",flight.flight_no,flight.destination,flight.gate_no,flight.time,flight.status);
            System.out.println();
        }
    }   
}