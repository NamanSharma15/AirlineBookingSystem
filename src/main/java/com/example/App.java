package com.example;
import  java.util.Scanner;
import com.example.utils.Utils;
import com.example.auth.Login;
import com.example.auth.Signup;
import com.example.auth.Auth;
import com.example.database.Database;
import com.example.schemas.User;
import com.example.structures.Flights;
import com.example.functions.Functions;
public class App 
{
    public static void main( String[] args )
    {
        Database database = new Database();
        mainFunc(database);
        database.closeConnection();
    } 
    private static void mainFunc(Database database){
        Flights flights = new Flights(300);
        boolean iter_loop = true; 
        Scanner sc = new Scanner(System.in);
        Functions functions = new Functions();
        Auth authentication = userAuthentication(sc,database);
        User user = authentication.user;
        flights = database.getFlights();
        System.out.println(Utils.getFileContents("instructions.txt"));
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
                case 2:
                    functions.getPass(database, sc,user,true);
                    break;
                case 3:
                    functions.checkFlights(database);
                    break;
                case 4:
                    functions.getHistory(database, user);
                    break;
                case 5:
                    System.out.println("You have now been signed out from account "+user.name+".\n");
                    mainFunc(database);
                    return;
                default:
                    System.out.println("Invalid response");
                    break;
            }
        }
        sc.close();
    }

    private static Auth userAuthentication(Scanner sc,Database database){
        Auth authentication = null;
        System.out.println("Enter 1 to Login \nEnter 2 to Signup \nEnter 0 to Exit \nSelect the value:");
        String inp = sc.nextLine();
        int input = 0;
        boolean is_valid = false;
        while (!(is_valid)) {            
            try{
                input= Integer.parseInt(inp);
                is_valid = true;
            }catch(NumberFormatException e){
                System.out.println("Enter a valid response");
                return userAuthentication(sc, database);
            }
        }
        if(input==1){
            authentication = new Login();
        }else if(input==2){
            authentication = new Signup();
        }
        else if(input==0){
            System.exit(0);
            return null;
        }
        else{
            System.out.println("Enter a valid response");
            return userAuthentication(sc, database);
        }
        authentication.onInit(sc,database);
        if(authentication.user!=null){
            System.out.println("You are Signin as "+authentication.user.name);
        }else if(inp=="2"){
            System.out.println("User already exists for the given email");
            return userAuthentication(sc, database);
        }
        else{
            System.out.println("User Doesnt exists");
            return userAuthentication(sc, database);
        }
        return authentication;
    }
}
