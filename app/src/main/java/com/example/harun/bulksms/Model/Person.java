package com.example.harun.bulksms.Model;

public class Person {

    String name;
    String number;
    boolean selected = false;
    private Object id;



    public Person(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setNumber(String number){
        this.number = number;
    }

    public String getName(){
        return name;
    }

    public String getNumber(){
        return number;
    }

    public void setSelected(boolean selected){
        this.selected = selected;
    }

    public boolean isSelected(){

        return selected;
    }


    public Object getId() {
        return id;
    }

}
