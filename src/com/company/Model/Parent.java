package com.company.Model;

/**
 * Created by Lemba on 08.05.2018.
 */
public class Parent {
    String surName;
    String firstName;
    String secondName;
    int salary;

    public Parent(String sName, String fstName, String secName, int sal){
        this.surName = sName;
        this.firstName = fstName;
        this.secondName = secName;
        this.salary = sal;
    }
    public Parent(){
    }

    public String getSurName(){return surName;}
    public String getFirstName(){return firstName;}
    public String getSecondName(){return secondName;}
    public int getSalary(){return salary;}
    public void setSurName(String surName){this.surName = surName;}
    public void setFirstName(String firstName){this.firstName=firstName;}
    public void setSecondName(String secondName){this.secondName = secondName;}
    public void setSalary(int salary){this.salary = salary;}
}
