package com.example.structures;

public class Strings {
     public int i;
    public String objects[];
    public Strings(int size){
        objects = new String[size];
        i =0;
    }
    public void add(String object){
        objects[i] = object;
        i+=1;
    }
    public Object get(int index){
        return objects[index];
    }
    public boolean contains(String val){
        for(int i=0;i<this.i;i++){
            if(objects[i].equals(val)){
                return true;
            }
        }
        return false;
    }
}
