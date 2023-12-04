package com.example.structures;
import com.example.schemas.Flight;
public class Flights{
    public int i;
    public Flight objects[];
    public Flights(int size){
        objects = new Flight[size];
        i =0;
    }
    public void add(Flight object){
        objects[i] = object;
        i+=1;
    }
    public Flight get(int index){
        return objects[index];
    }
}