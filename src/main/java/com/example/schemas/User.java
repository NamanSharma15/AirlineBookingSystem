package com.example.schemas;
import java.util.ArrayList;
import java.util.List;

public class User {
    public  String u_id,name,email,password;
    public List<String> flight_list;
    public User(String u_id, String name, String email, String password){
        this.u_id = u_id;
        this.name =name;
        this.email = email;
        this.password = password;
        this.flight_list = new ArrayList<String>();
    }
}