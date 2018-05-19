package com.company.View;

import com.company.Controller.MainController;
import com.company.Main;
import com.company.Model.DataBase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Lemba on 11.05.2018.
 */
public class DeleteDialog {
    private MainController dataBaseManipulation;
    private SearchStudentDialog findView;
    private JDialog deleteDialog;
    private View view;

    public DeleteDialog(MainController deleteController, View view){
        dataBaseManipulation=deleteController;
        findView=new SearchStudentDialog(dataBaseManipulation);
        this.view=view;
        deleteDialog = new JDialog();
        deleteDialog=findView.createButtonForSeach("Удаление");
        createDeleteWindow();
    }
    public void createDeleteWindow(){

        findView.getFindByName().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                findView.removeElements(deleteDialog);
                findView.addFindByName("Удаление",new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        int count = dataBaseManipulation.deleteStudentByName(findView.getFirstData().getText(),
                                findView.getSecondData().getText(), findView.getThirdData().getText());
                        //findView.getjDialog().setVisible(false);
                        if (count != 0) {
                            JOptionPane.showMessageDialog(new JFrame(), "Записей найдено и удалено " + count);
                        } else JOptionPane.showMessageDialog(new JFrame(), "Записей не найдено");
                        view.getFramesGraphics().dispose();
                        view.getTabl().updateTable();
                    }
                },deleteDialog);
            }
        });

        findView.getFindBynNameParents().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {//////////////////////////////
                findView.removeElements(deleteDialog);
                findView.addFindByName("Удаление", new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        int count = dataBaseManipulation.deleteParentByName(findView.getFirstData().getText(),
                                findView.getSecondData().getText(), findView.getThirdData().getText());
                        findView.getjDialog().setVisible(false);
                        if (count != 0) {
                            JOptionPane.showMessageDialog(new JFrame(), "Записей найдено и удалено " + count);
                        } else JOptionPane.showMessageDialog(new JFrame(), "Записей не найдено");
                       view.getFramesGraphics().dispose();
                        view.getTabl().updateTable();
                    }
                },deleteDialog);
            }
        });

        findView.getFindByCountBrotherandSisters().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                findView.removeElements(deleteDialog);
                findView.addFindByCountBrothersandSisters("Удаление", new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        int count=dataBaseManipulation.deleteStudentByCountSistersAndBrothers(findView.getFirstData().getText(),
                                findView.getSecondData().getText());
                        findView.getjDialog().setVisible(false);
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

        findView.getFindByParentsSalary().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                findView.removeElements(deleteDialog);
                findView.addFindStudentByParentsSalary("Удаление", new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        int count=dataBaseManipulation.deleteStudentByParensSalary(findView.getFirstData().getText(),
                                findView.getSecondData().getText(),findView.getThirdData().getText());
                        findView.getjDialog().setVisible(false);
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
