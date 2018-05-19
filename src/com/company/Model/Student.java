package com.company.Model;

import java.util.ArrayList;

/**
 * Created by Lemba on 08.05.2018.
 */
public class Student {
    String surName;
    String firstName;
    String secondName;
    int brotherNum;
    int sisterNum;
    Parent mother;
    Parent father;


    public Student(String sName, String fstName, String secName, Parent dad, Parent mom, int brothNum, int sistNum){
        this.surName = sName;
        this.firstName = fstName;
        this.secondName = secName;
        this.father=dad;
        this.mother=mom;
        this.brotherNum = brothNum;
        this.sisterNum = sistNum;
    }

    public String getSurName(){return surName;}
    public String getFirstName(){return firstName;}
    public String getSecondName(){return secondName;}
    public String getFullName(){
        return (surName + firstName + secondName);
    }
    public int getBrotherNum(){return brotherNum;}
    public int getSisterNum(){return sisterNum;}

    public Parent getMother(){return mother;}
    public Parent getFather(){return father;}
}
