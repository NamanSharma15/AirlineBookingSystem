package com.example.functions;
import java.util.Scanner;
import com.example.database.Database;
import com.example.schemas.Flight;
import com.example.schemas.Ticket;
import com.example.schemas.User;
public interface GetPass {
    default void getPass(User user,Database database, Scanner sc){
        System.out.println("Enter your Ticket. No");
        String ticket_no = sc.nextLine();
        Ticket ticket = database.getTicket(ticket_no,user);
        Flight flight = database.getFlight(ticket.flight.flight_no);
    }
} 
