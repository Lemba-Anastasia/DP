package com.company.Controller;

import com.company.Model.DataBase;
import com.company.Model.Student;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lemba on 11.05.2018.
 */
public class MainController {
    private DataBase dataBase;

    public MainController(DataBase dataBase){
        this.dataBase=dataBase;
    }

    public ArrayList<Student>findStudentByName(String surName, String fName, String sName){
        ArrayList<Student> findStudentArrayList = new ArrayList<Student>();
        for (Student student: dataBase.getStudents()) {
            if (student.getSurName().equalsIgnoreCase(surName) && student.getFirstName().equalsIgnoreCase(fName) &&
                    student.getSecondName().equalsIgnoreCase(sName) ) {
                findStudentArrayList.add(student);
            }
        }
        return findStudentArrayList;
    }

    public ArrayList<Student> findParentByName(String surName, String fName, String sName){
        ArrayList<Student> findStudentArrayList = new ArrayList<Student>();
        for (Student student: dataBase.getStudents()) {
            if (student.getFather().getSurName().equalsIgnoreCase(surName) && student.getFather().getFirstName().equalsIgnoreCase(fName) &&
                    student.getFather().getSecondName().equalsIgnoreCase(sName) ) {
                findStudentArrayList.add(student);
            }
            if (student.getMother().getSurName().equalsIgnoreCase(surName) && student.getMother().getFirstName().equalsIgnoreCase(fName) &&
                    student.getMother().getSecondName().equalsIgnoreCase(sName) ) {
                findStudentArrayList.add(student);
            }
        }
        return  findStudentArrayList;
    }

    public ArrayList<Student> findBrothersAndSisters(String brothersNum , String sistersNum ){
        ArrayList<Student> findStudentArrayList = new ArrayList<Student>();
        for (Student student: dataBase.getStudents()) {
            if (student.getBrotherNum()== Integer.parseInt(brothersNum) && student.getSisterNum()== Integer.parseInt(sistersNum)) {
                findStudentArrayList.add(student);
            }
        }
        return  findStudentArrayList;

    }

    public  ArrayList<Student> findByParentsSalary(String lowel, String upper, String kindOfParent){
        int min,max;
        try{
            min = Integer.parseInt(lowel);
        }
        catch (NumberFormatException e){
            min = 0;
        }
        try{
            max = Integer.parseInt(upper);
        }
        catch (NumberFormatException e){
            max =Integer.MAX_VALUE;
        }
        ArrayList<Student> findStudentArrayList = new ArrayList<Student>();
        for (Student student: dataBase.getStudents()) {
            if(kindOfParent.equalsIgnoreCase("мать")){
                if (min<=student.getMother().getSalary() && student.getMother().getSalary()<=max) {
                    findStudentArrayList.add(student);
                }
            }
            if(kindOfParent.equalsIgnoreCase("отец")){
                if (min<=student.getFather().getSalary() && student.getFather().getSalary()<=max) {
                    findStudentArrayList.add(student);
                }
            }
        }
        return  findStudentArrayList;
    }

    public int deleteStudentByName(String surName, String fName, String sName){
        int newSizeTable=dataBase.removeStudents(findStudentByName(surName,fName,sName));

        return newSizeTable;
    }
    public int deleteParentByName(String surName, String fName, String sName){
        int newSizeTable=dataBase.removeStudents(findParentByName(surName, fName, sName));

        return newSizeTable;
    }
    public int deleteStudentByCountSistersAndBrothers(String countOfBrothers, String countOfSisters){
        int newSizeTable=dataBase.removeStudents(findBrothersAndSisters(countOfBrothers, countOfSisters));

        return newSizeTable;
    }
    public int deleteStudentByParensSalary(String name, String lowerLimit,String upperLimit){
        int newSizeTable=dataBase.removeStudents(findByParentsSalary(name,lowerLimit,upperLimit));

        return newSizeTable;
    }
    public ArrayList<Student> getStudentList(){
        return dataBase.studentsList;
    }
    public DataBase getDataBase(){
        return dataBase;
    }

}







