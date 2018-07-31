package com.company.View;

import com.company.Controller.CheckInputForAdding;
import com.company.Controller.MainController;
import com.company.Model.DataBase;


import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Lemba on 08.05.2018.
 */

public class AddDialog {
    private JDialog dialog;

    private JTextField studentFirstName;
    private JTextField studentSecondName;
    private JTextField studentSurName;
    private JTextField dadFirstName;
    private JTextField dadSecondName;
    private JTextField dadSurName;
    private JTextField mumFirstName;
    private JTextField mumSecondName;
    private JTextField mumSurName;
    private JTextField dadSalary;
    private JTextField mumSalary;
    private JTextField numOfBrothers;
    private JTextField numOfSisters;
    private View view;

    public AddDialog(final MainController addController, final View view){
        this.view= view;
        dialog = new JDialog();

        Box studentForm = Box.createVerticalBox();
        Box txtLine1 = Box.createHorizontalBox();

        numOfBrothers = new JTextField();
        numOfBrothers.setBorder(new TitledBorder("Кол-во братьев"));
        numOfSisters = new JTextField();
        numOfSisters.setBorder(new TitledBorder("Кол-во сестер"));

        JButton addButton = new JButton("Добавить");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CheckInputForAdding temp = new CheckInputForAdding();
                temp.addNewStudentInfo(getStudentSurName(), getStudentFirstName(), getStudentSecondName(),
                        getDadSurName(),getDadFirstName(),getDadSecondName(),getDadSalary(),
                        getMumSurName(),getMumFirstName(),getMumSecondName(),getMumSalary(),
                        getBrotherNum(),getSisterNum(),
                        addController.getDataBase());

                view.updateData();

            }
        });
        dialog.setVisible(false);
        dialog.dispose();


        txtLine1.add(numOfBrothers);

        txtLine1.add(numOfSisters);

        studentForm.add(Box.createVerticalStrut(10));
        studentForm.add(Box.createVerticalStrut(10));
        studentForm.add(studentFIOBox());

        studentForm.add(Box.createVerticalStrut(10));
        studentForm.add(dadInfoBox());
        studentForm.add(Box.createVerticalStrut(10));
        studentForm.add(mumInfoBox());

        studentForm.add(Box.createVerticalStrut(10));
        studentForm.add(txtLine1);
        studentForm.add(Box.createVerticalStrut(10));
        studentForm.add(addButton);
        studentForm.add(Box.createVerticalStrut(10));
        dialog.add(studentForm);
    }


    private Box studentFIOBox(){
        studentSurName = new JTextField(10);
        studentSurName.setBorder(new TitledBorder("Фамилия"));
        studentFirstName = new JTextField(10);
        studentFirstName.setBorder(new TitledBorder("Имя"));
        studentSecondName = new JTextField(10);
        studentSecondName.setBorder(new TitledBorder("Отчество"));

        Box studentFIObox = Box.createVerticalBox();
        Box txtLine1 = Box.createHorizontalBox();
        txtLine1.add(studentSurName);
        txtLine1.add((Box.createHorizontalStrut(10)));
        txtLine1.add(studentFirstName);
        txtLine1.add((Box.createHorizontalStrut(10)));
        txtLine1.add(studentSecondName);

        studentFIObox.add(txtLine1);
        studentFIObox.setBorder(new TitledBorder("ФИО студента"));

        return studentFIObox;
    }
    private Box dadInfoBox(){
        dadSurName = new JTextField(10);
        dadSurName.setBorder(new TitledBorder("Фамилия отца"));
        dadFirstName = new JTextField(10);
        dadFirstName.setBorder(new TitledBorder("Имя отца"));
        dadSecondName = new JTextField(10);
        dadSecondName.setBorder(new TitledBorder("Отчество отца"));
        dadSalary = new JTextField(8);
        dadSalary.setBorder(new TitledBorder("З/п отца "));

        Box dadFIObox = Box.createVerticalBox();
        Box txtLine1 = Box.createHorizontalBox();
        txtLine1.add(dadSurName);
        txtLine1.add((Box.createHorizontalStrut(10)));
        txtLine1.add(dadFirstName);
        txtLine1.add((Box.createHorizontalStrut(10)));
        txtLine1.add(dadSecondName);
        Box txtLine2 = Box.createHorizontalBox();
        txtLine2.add((Box.createHorizontalStrut(170)));
        txtLine2.add(dadSalary);
        txtLine2.add((Box.createHorizontalStrut(170)));


        dadFIObox.add(txtLine1);
        dadFIObox.add(Box.createVerticalStrut(10));
        dadFIObox.add(txtLine2);

        return dadFIObox;
    }
    private Box mumInfoBox(){

        mumSurName = new JTextField(10);
        mumSurName.setBorder(new TitledBorder("Фамилия матери"));
        mumFirstName = new JTextField(10);
        mumFirstName.setBorder(new TitledBorder("Имя матери"));
        mumSecondName = new JTextField(10);
        mumSecondName.setBorder(new TitledBorder("Отчество матери"));
        mumSalary = new JTextField(8);
        mumSalary.setBorder(new TitledBorder("З/п матери"));

        Box mumFIObox = Box.createVerticalBox();
        Box txtLine1 = Box.createHorizontalBox();
        txtLine1.add(mumSurName);
        txtLine1.add((Box.createHorizontalStrut(10)));
        txtLine1.add(mumFirstName);
        txtLine1.add((Box.createHorizontalStrut(10)));
        txtLine1.add(mumSecondName);
        Box txtLine2 = Box.createHorizontalBox();
        txtLine2.add((Box.createHorizontalStrut(170)));
        txtLine2.add(mumSalary);
        txtLine2.add((Box.createHorizontalStrut(170)));


        mumFIObox.add(txtLine1);
        mumFIObox.add(Box.createVerticalStrut(10));
        mumFIObox.add(txtLine2);

        return mumFIObox;
    }

    public String getStudentSurName(){return studentSurName.getText();}
    public String getStudentFirstName(){return studentFirstName.getText();}
    public String getStudentSecondName(){return studentSecondName.getText();}
    public String getDadSurName(){return dadSurName.getText();}
    public String getDadFirstName(){return dadFirstName.getText();}
    public String getDadSecondName(){return dadSecondName.getText();}
    public String getMumSurName(){return mumSurName.getText();}
    public String getMumFirstName(){return mumFirstName.getText();}
    public String getMumSecondName(){return mumSecondName.getText();}
    public String getDadSalary(){return  dadSalary.getText();}
    public String getMumSalary(){return  mumSalary.getText();}
    public String getBrotherNum(){return numOfBrothers.getText();}
    public String getSisterNum(){return numOfSisters.getText();}

    public JDialog getDialog(){return dialog;}
}
