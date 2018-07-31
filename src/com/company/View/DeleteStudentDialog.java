package com.company.View;

import com.company.Controller.MainController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteStudentDialog extends  CommonBetweenDeleteAndSearchDialog{
    private MainController dataBaseManipulation;
    private JDialog deleteDialog;
    private View view;

    public DeleteStudentDialog(MainController deleteController, View view){
        dataBaseManipulation=deleteController;
        this.view=view;
        deleteDialog= new JDialog();
        deleteDialog= createButtons("Удаление");
        createDeleteWindow();
    }

    public void createDeleteWindow(){

        getFindByName().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                removeElements(deleteDialog);
                addByName("Удаление",new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        int count = dataBaseManipulation.deleteStudentByName(getFirstData(),getSecondData(),getThirdData());
                        if (count != 0) {
                            JOptionPane.showMessageDialog(new JFrame(), "Записей найдено и удалено " + count);
                        } else JOptionPane.showMessageDialog(new JFrame(), "Записей не найдено");
                        view.getFramesGraphics().dispose();
                        view.getTabl().updateTable();
                    }
                },deleteDialog);
            }
        });

        getFindBynNameParents().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                removeElements(deleteDialog);
                addByName("Удаление", new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        int count = dataBaseManipulation.deleteParentByName(getFirstData(), getSecondData(),getThirdData());
                        if (count != 0) {
                            JOptionPane.showMessageDialog(new JFrame(), "Записей найдено и удалено " + count);
                        } else JOptionPane.showMessageDialog(new JFrame(), "Записей не найдено");
                        view.getFramesGraphics().dispose();
                        view.getTabl().updateTable();
                    }
                },deleteDialog);
            }
        });

        getFindByCountBrotherandSisters().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                removeElements(deleteDialog);
                addByCountBrothersandSisters("Удаление", new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        int count=dataBaseManipulation.deleteStudentByCountSistersAndBrothers(getFirstData(), getSecondData());
                        if (count!=0){
                            JOptionPane.showMessageDialog(new JFrame(), "Записей найдено и удалено "+count);
                        }
                        else JOptionPane.showMessageDialog(new JFrame(), "Записей не найдено");
                        view.getFramesGraphics().dispose();
                        view.getTabl().updateTable();
                    }
                },deleteDialog);
            }
        });

        getFindByParentsSalary().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                removeElements(deleteDialog);
                addStudentByParentsSalary("Удаление", new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        int count=dataBaseManipulation.deleteStudentByParensSalary(getFirstData(),getSecondData(),getThirdData());
                        if (count!=0){
                            JOptionPane.showMessageDialog(new JFrame(), "Записей найдено и удалено "+count);
                        }
                        else JOptionPane.showMessageDialog(new JFrame(), "Записей не найдено");
                        view.getFramesGraphics().dispose();
                        view.getTabl().updateTable();
                    }
                },deleteDialog);
            }
        });

    }

    public JDialog getDialog() {
        return deleteDialog;
    }

}
