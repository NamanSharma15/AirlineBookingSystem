package com.example.auth;
import java.util.Scanner;
import com.example.database.Database;
import com.example.schemas.User;

abstract public class Auth {
    String u_id,name,email,password;
    Database database;
    boolean is_Authenticated;
    User user = null;
    abstract void onInit(Scanner sc,Database database);
}
