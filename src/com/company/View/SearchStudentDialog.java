package com.company.View;

import com.company.Controller.MainController;
import com.company.Model.Student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchStudentDialog extends CommonBetweenDeleteAndSearchDialog {
    private JDialog seachDialog;
    private Table table;
    private MainController controllerSearch;

    private ArrayList<Student> copyMainStudentList;


    public SearchStudentDialog (MainController mainController){
        controllerSearch=mainController;
        seachDialog= new JDialog();
        table=new Table(mainController.getStudentList());
        copyMainStudentList = mainController.getStudentList();
        seachDialog= createButtons("Поиск");
        table.setBounds(300, 20, 550, 400);
        seachDialog.add(table);
        createActionButton();
    }

    private void createActionButton(){

        getFindByName().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                removeElements(seachDialog);
                returnTostartPositionTable();
                addByName("Поиск", new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        if (getFirstData().equals("") || getSecondData().equals("") || getThirdData().equals("")){
                            JOptionPane.showMessageDialog(null, "Введите ФИО студента.");
                            return;
                        }
                        ArrayList<Student> tempSearchBase=controllerSearch.findStudentByName(getFirstData(),getSecondData(),getThirdData());
                        table.setStudents(tempSearchBase);
                        table.updateTable();
                    }
                },seachDialog);
            }
        });

        getFindBynNameParents().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                returnTostartPositionTable();
                removeElements(seachDialog);
                addByName("Поиск", new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        if (getFirstData().equals("") || getSecondData().equals("") || getThirdData().equals("")){
                            JOptionPane.showMessageDialog(null, "Введите ФИО дного из родителей.");
                            return;
                        }
                        ArrayList<Student> tempSearchBasedDads=controllerSearch.findParentByName(getFirstData(), getSecondData(),getThirdData());

                        table.setStudents(tempSearchBasedDads);

                        table.updateTable();
                    }
                },seachDialog);
            }
        });

        getFindByCountBrotherandSisters().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                returnTostartPositionTable();
                removeElements(seachDialog);

                addByCountBrothersandSisters("Поиск", new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        if (getFirstData().equals("") || getSecondData().equals("") ){
                            JOptionPane.showMessageDialog(null, "Введите число братьев и сестер.");
                            return;
                        }
                        ArrayList<Student> tempSearchBase=controllerSearch.findBrothersAndSisters(getFirstData(), getSecondData());
                        table.setStudents(tempSearchBase);
                        table.updateTable();
                    }
                },seachDialog);
            }
        });

        getFindByParentsSalary().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                returnTostartPositionTable();
                removeElements(seachDialog);
                addStudentByParentsSalary("Поиск", new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {

                        if ((getFirstData().equals("") || getSecondData().equals("") )&& getThirdData().equals("") ){
                            JOptionPane.showMessageDialog(null, "Введите интервал искомой з/п одного из родителя или формат родителя.");
                            return;
                        }
                        ArrayList<Student> tempSearchBase=controllerSearch.findByParentsSalary(getFirstData(), getSecondData(), getThirdData());
                        table.setStudents(tempSearchBase);
                        table.updateTable();
                    }
                },seachDialog);
            }
        });

    }
    private void returnTostartPositionTable(){
        table.setStudents(copyMainStudentList);
        table.updateTable();
    }
    public JDialog getSeachDialog(){
        return seachDialog;
    }
}
