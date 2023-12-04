package com.example.auth;
import java.util.Scanner;
import com.example.database.Database;

public class Signup extends Auth{
    public void onInit(Scanner sc,Database database){
        this.database = database;
        System.out.println("Enter your Name: ");
        this.name = sc.nextLine();
        System.out.println("Enter your Email: ");
        this.email = sc.nextLine();
        System.out.println("Enter your Password: ");
        this.password = sc.nextLine();
        this.newUser();
    }
    private void newUser(){
        this.is_Authenticated = this.database.addUser(this.name, this.email, this.password);
        if(this.is_Authenticated){
            this.user = this.database.getUser(this.email);
        }
    }
}
