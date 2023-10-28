package com.example;
import  java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import com.example.utils.Utils;
import com.example.auth.Login;
import com.example.auth.Signup;
import com.example.auth.Auth;
import com.example.database.Database;
import com.example.schemas.Flight;
import com.example.schemas.User;
import com.example.functions.Functions;
public class App 
{
    public static void main( String[] args )
    {
        Database database = new Database();
        List<Flight> flights = new ArrayList<Flight>();
        // User Authentication
        boolean iter_loop = true; 
        Scanner sc = new Scanner(System.in);
        Functions functions = new Functions();
        Auth authentication = userAuthentication(sc,database);
        User user = authentication.user;
        // getFlights
        flights = database.getFlights();
        String instuctions = Utils.getFileContents("instructions.txt");
        System.out.println(instuctions);
        while(iter_loop){
            System.out.println("Enter your choice:");
            int input = sc.nextInt();
            switch(input){
                case 0:
                    iter_loop=false;
                    break;
                case 1:
                    functions.bookTicket(user, flights, database, sc);
                    break;
            }
        }
        sc.close();
        database.closeConnection();
    } 
    private static Auth userAuthentication(Scanner sc,Database database){
        Auth authentication = null;
        System.out.println("Enter 1 to login and 2 to signup");
        String inp = sc.nextLine();
        int input = 0;
        boolean is_valid = false;
        while (!(is_valid)) {            
            try{
                input= Integer.parseInt(inp);
                is_valid = true;
            }catch(Exception e){
                System.out.println("Enter a valid response");
                System.out.println("Enter 1 to login and 2 to signup");
                inp = sc.nextLine();
            }
        }
        if(input==1){
            authentication = new Login();
        }else{
            authentication = new Signup();
        }
        authentication.onInit(sc,database);
        if(authentication.user!=null){
            System.out.println("You are Signin as "+authentication.user.name);
        }
        return authentication;
    }
}
