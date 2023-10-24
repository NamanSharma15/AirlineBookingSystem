package com.example.auth;

import java.util.Scanner;

abstract public class Auth {
    String u_id,name,email,password;
    abstract void onInit(Scanner sc);
}
