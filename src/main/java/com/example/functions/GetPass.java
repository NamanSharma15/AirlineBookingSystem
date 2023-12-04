package com.example.functions;
import java.util.Scanner;
import com.example.database.Database;
import com.example.schemas.Flight;
import com.example.schemas.Ticket;
import com.example.schemas.User;
import com.example.structures.Strings;
import com.example.utils.Utils;
import java.time.LocalDate;
import java.time.LocalTime;

import com.example.Constants;
public interface GetPass {
    default void getPass(Database database, Scanner sc,User user,boolean k){
        int no_of_seats,total_row,max_in_one;
        String class_,selected;
        String[] seat_letters = {"A","B","C"};
        Strings seats = new Strings(350);
        System.out.println("Enter your Ticket. No: ");
        String ticket_no = sc.nextLine();
        if(k){
            ticket_no = sc.nextLine();
        }
        Ticket ticket = database.getTicket(ticket_no);
        if(ticket==null){
            System.out.println("Entered ticket number is either invalid");
            getPass(database, sc,user,false);
            return;
        }
        if(!ticket.user.email.equals(user.email)){
            System.out.println("The Signin user is not same as specified on the ticket");
            System.out.println(Utils.getFileContents("instructions.txt"));
            return;
        }
        if(!database.checkPass(ticket)){
            System.out.println("Ticket has already been used for pass generation");
            System.out.println(Utils.getFileContents("instructions.txt"));
            return;
        }
        Flight flight = database.getFlight(ticket.flight.flight_no);
        Strings booked_seats = database.getSeats(flight);
        if(ticket.class_.equals("First class")){
            no_of_seats= Constants.FIRST_SEATS;
            max_in_one = 1;
            class_ = "F";
        }
        else if(ticket.class_.equals("Business Class")){
            no_of_seats = Constants.BUSINESS_SEATS;
            max_in_one = 2;
            class_ = "B";
        }
        else{
            no_of_seats = Constants.ECONOMY_SEATS;
            max_in_one = 3;
            class_ = "E";
        }
        total_row = no_of_seats/max_in_one;
        System.out.println("Available seats are :");
        int i,j;
        i = 1;
        while (i<=total_row) {
            j=0;
            while(j<max_in_one){
                String seat_no;
                if(i<10){
                    seat_no = class_+"0"+Integer.toString(i)+seat_letters[j];
                }else{
                    seat_no = class_+Integer.toString(i)+seat_letters[j];
                }
                if(!(booked_seats.contains(seat_no))){
                    System.out.print(seat_no+" ");
                    seats.add(seat_no);
                }else{
                    System.out.print("XXXX ");
                }
                j+=1;
            }
            System.out.print("    ");
            if(i%2==0){
                System.out.println("\n");
            }
            i+=1;
        }
        System.err.println("\nEnter your seat no:");
        selected = sc.nextLine(); 
        while (!(seats.contains(selected))) {
            System.out.println("Enter a valid seat no: "); 
            selected = sc.nextLine(); 
        }
        String time = LocalTime.now().toString().substring(0, 8);
        database.setPass(ticket, selected, time);
        String pass_text = Utils.getFileContents("pass_format.txt");
        String get_pass = genneratePass(pass_text, selected, ticket);
        Utils.createTextFile(ticket.ticket_no+".txt","passes", get_pass);
        System.out.println("Your boarding pass has been saved in the out/passes folder with filename "+ticket.ticket_no+".txt");
    }
    static String genneratePass(String text,String seat,Ticket ticket){
        String date = LocalDate.now().toString();
        text = text.replace("-flight_no-",ticket.flight.flight_no);
        text = text.replace("-name-",ticket.user.name);
        text = text.replace("-destination-",ticket.flight.destination);
        text = text.replace("-class-",ticket.class_);
        text = text.replace("-date-",date);
        text = text.replace("-gate-",ticket.flight.gate_no);
        text = text.replace("-seat-",seat);
        text = text.replace("-time-",ticket.flight.time);
        return text;
    }
} 