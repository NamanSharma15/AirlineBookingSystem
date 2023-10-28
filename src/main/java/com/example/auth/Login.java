package com.example.auth;
import java.util.Scanner;
import com.example.database.Database;
public class Login extends Auth{
    public void onInit(Scanner sc,Database database){
        this.database = database;
        System.out.println("Enter your Email: ");
        this.email = sc.nextLine();
        System.out.println("Enter your Password: ");
        this.password = sc.nextLine();
        this.getUser();
    }
    private void getUser(){
        this.user = this.database.getUser(this.email);
    }
}
