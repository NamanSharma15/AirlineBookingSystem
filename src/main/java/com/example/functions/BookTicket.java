package com.example.functions;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.example.database.Database;
import com.example.schemas.Flight;
import com.example.schemas.User;

interface BookTicket {
    default void bookTicket(User user, List<Flight> flights, Database database, Scanner sc) {
        boolean found = false;
        String searchString;
        int first = 1;
        while (!(found)) {
            System.out.print("Search the destination for the flight name: ");
            searchString = sc.nextLine();
            if(first==1){
                searchString = sc.nextLine();
                first = 0;
            }
            List<Flight> result = new ArrayList<Flight>();
            for (Flight flight :flights) {
                if (flight.destination.contains(searchString)) {
                    result.add(flight);
                }
            }
            for (int i = 0; i < result.size(); i++) {
                System.out.print(Integer.toString(i+1)+" - ");
                System.out.println(result.get(i).flight_no+" "+result.get(i).destination+" "+result.get(i).time);
            }
            if(result.size()==0){
                System.out.println("No flight Available for the search input");
                continue;
            }
            System.out.println("Enter the index number to select destination \n or Enter 0 to search again: ");
            String selection = sc.nextLine();
            int index_flight = 0;
            boolean is_valid = false;
            while (!(is_valid)) {
                try {
                    index_flight = Integer.parseInt(selection);
                    is_valid = true;
                } catch (Exception e) {
                    System.out.println("Enter a valid response");
                    selection = sc.nextLine();
                }
            }
            if (index_flight <= 0 || result.size()==0) {
                continue;
            }
            Flight flight = result.get(index_flight-1);
            System.out.println(flight.flight_no);
            found = true;
        }
    }
}
