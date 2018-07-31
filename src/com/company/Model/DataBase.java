package com.company.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lemba on 08.05.2018.
 */
public class DataBase {

    public ArrayList<Student> studentsList = new ArrayList<>(30);

    public DataBase(){
    }
    public void addStud(Student student) {
        studentsList.add(student);
    }
    public int removeStudents(List<Student> studentList){
        studentsList.removeAll(studentList);
        return studentList.size();
    }
    public List<Student> getStudents() {
        return studentsList;
    }




}
