package com.company.Controller;
import com.company.Model.DataBase;
import com.company.Model.Parent;
import com.company.Model.Student;

import javax.swing.*;

/**
 * Created by Lemba on 08.05.2018.
 */

public class CheckInputForAdding {

    public void addNewStudentInfo(String surName, String firstName, String secName,
                                  String dadSurName, String dadFirstName, String dadSecName, String dadSalary,
                                  String mumSurName, String mumFirstName, String mumSecName, String mumSalary,
                                  String numBrohers, String numSisters,
                                  DataBase dataBase){
        if (surName.equals("") || firstName.equals("") || secName.equals("")){
            JOptionPane.showMessageDialog(null, "Введите имя студента.");
            return;
        }
        if (dadSurName.equals("") || dadFirstName.equals("") || dadSecName.equals("") || dadSalary.equals("")){
            JOptionPane.showMessageDialog(null, "Введите инфромацию о отце студента.");
            return;
        }
        if (mumSurName.equals("") || mumFirstName.equals("") || mumSecName.equals("") || mumSalary.equals("")){
            JOptionPane.showMessageDialog(null, "Введите информацию о матери студента.");
            return;
        }
        if (numBrohers.equals("") || numBrohers.equals("")){
            JOptionPane.showMessageDialog(null, "Введите число братьев и сестер.");
            return;
        }

        int intBrothers = 0;
        int intSisters = 0;
        int intDadSalary = 0;
        int intMumSalary = 0;

        try{
            intBrothers = Integer.parseInt(numBrohers);
        }
        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Нужно вести число братьев.");
            return;
        }
        try{
            intSisters = Integer.parseInt(numSisters);
        }
        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Нужно вести число сестер.");
            return;
        }
        try{
            intDadSalary = Integer.parseInt(dadSalary);
        }
        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Нужно вести число зарплаты (отца).");
            return;
        }
        try{
            intMumSalary = Integer.parseInt(mumSalary);
        }
        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Нужно вести число зарплаты (матери).");
            return;
        }

        Parent dad = new Parent(dadSurName,dadFirstName,dadSecName,intDadSalary);
        Parent mom = new Parent(mumSurName,mumFirstName,mumSecName,intMumSalary);
        dataBase.addStud(new Student(surName,firstName,secName,dad, mom, intBrothers,intSisters));
    }
}