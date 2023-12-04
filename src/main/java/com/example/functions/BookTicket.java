package com.example.functions;
import java.time.LocalTime;
import java.time.LocalDate;
import java.util.Scanner;
import com.example.database.Database;
import com.example.schemas.Flight;
import com.example.schemas.Ticket;
import com.example.schemas.User;
import com.example.structures.Flights;
import com.example.utils.Utils;

interface BookTicket {
    default void bookTicket(User user, Flights flights, Database database, Scanner sc) {
        boolean found = false;
        String searchString;
        int first = 1;
        Flight flight = null;
        Ticket ticket = null;
        while (!(found)) {
            System.out.print("Search the destination for the flight name: ");
            searchString = sc.nextLine();
            if (first == 1) {
                searchString = sc.nextLine();
                first = 0;
            }
            Flights result = new Flights(300);
            for (int i=0;i<flights.i;i++) {
                if (flights.get(i).destination.contains(searchString)) {
                    result.add(flights.get(i));
                }
            }
            for (int i = 0; i < result.i; i++) {
                System.out.print(Integer.toString(i + 1) + " - ");
                System.out.println(result.get(i).flight_no + " " + result.get(i).destination + " " + result.get(i).time);
            }
            if (result.i == 0) {
                System.out.println("No flight Available for the search input");
                continue;
            }
            System.out.println("Enter the index number to select destination \n or Enter 0 to search again: ");
            int index_flight = intInput(sc);
            if (index_flight <= 0 || result.i == 0) {
                continue;
            }
            flight = result.get(index_flight - 1);
            System.out.println("You have selected flight no: " + flight.flight_no);
            found = true;
        }
        if (flight != null) {
            String class_ = getClass(sc);
            String ticket_no = database.getTicketNo();
            String date = LocalDate.now().toString();
            String time = LocalTime.now().toString().substring(0, 8);
            ticket = new Ticket(ticket_no, time, class_, date);
            boolean is_added = database.addTicket(ticket,user.u_id,flight.flight_no);
            if(is_added){
                String ticket_text = Utils.getFileContents("ticket_format.txt"); 
                String get_ticket = getTicket(ticket_text, ticket, user, flight);
                System.out.println(get_ticket);
                Utils.createTextFile(ticket_no+".txt","tickets",get_ticket);
                System.out.println("Your ticket has been saved in the out/tickets folder with filename "+ticket_no+".txt");
            }else{
                System.out.println("Ticket not booked due to intenal error");
            }
        }
    }
    static String getTicket(String text,Ticket ticket,User user,Flight flight){
        text = text.replace("-flight_no-",flight.flight_no);
        text = text.replace("-name-",user.name);
        text = text.replace("-email-",user.email);
        text = text.replace("-ticket_no-",ticket.ticket_no);
        text = text.replace("-destination-",flight.destination);
        text = text.replace("-class-",ticket.class_);
        text = text.replace("-booking_date-",ticket.date);
        text = text.replace("-booking_time-",ticket.time_of_booking);
        return text;
    }
    static String getClass(Scanner sc) {
        System.out.println(Utils.getFileContents("class.txt"));
        System.out.println("Enter your response:");
        String[] classes = { "First class", "Business Class", "Economy Class" };
        int val = intInput(sc);
        while(val>3){
            System.out.println("Enter a valid response");
            val = intInput(sc);
        }
        return classes[val-1];
    }
    static int intInput(Scanner sc) {
        String selection = sc.nextLine();
        int val = 0;
        boolean is_valid = false;
        while (!(is_valid)) {
            try {
                val = Integer.parseInt(selection);
                is_valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid response");
                selection = sc.nextLine();
            }
        }
        return val;
    }
}
