package com.example.auth;
import java.util.Scanner;
import com.example.database.Database;
import com.example.schemas.User;

abstract public class Auth {
    String u_id,name,email,password;
    Database database;
    public boolean is_Authenticated;
    public User user = null;
    public abstract void onInit(Scanner sc,Database database);
}
