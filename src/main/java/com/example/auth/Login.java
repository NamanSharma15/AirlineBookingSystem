package com.example.auth;
import java.util.Scanner;
import java.io.Console;
import com.example.database.Database;
public class Login extends Auth{
    public void onInit(Scanner sc,Database database){
        this.database = database;
        Console console =System.console();
        System.out.println("Enter your Email: ");
        this.email = sc.nextLine();
        char pass[] = console.readPassword("Enter your Password: \n");
        this.password =new String(pass);
        this.getUser();
        if(user!=null){
            if(!(this.user.password.equals(this.password))){
                System.out.println("Invalid Cradentials");
                this.onInit(sc, database);
            }
        }
    }
    private void getUser(){
        this.user = this.database.getUser(this.email);
    }
}
