package com.example.structures;

import com.example.schemas.User;
public class Users{
    public int i;
    public User objects[];
    public Users(int size){
        objects = new User[size];
        i =0;
    }
    public void add(User object){
        objects[i] = object;
        i+=1;
    }
    public Object get(int index){
        return objects[index];
    }
}