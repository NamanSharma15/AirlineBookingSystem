package com.example.auth;
import java.util.Scanner;
import com.example.schemas.User;

public class Signup extends Auth{
    public void onInit(Scanner sc){
        System.out.println("Enter your Name: ");
        this.name = sc.nextLine();
        System.out.println("Enter your Email: ");
        this.email = sc.nextLine();
        System.out.println("Enter your Password: ");
        this.password = sc.nextLine();
    }
    
}
