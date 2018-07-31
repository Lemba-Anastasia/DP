package com.company.View;

import com.company.Model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Lemba on 19.05.2018.
 */
public class CommonBetweenDeleteAndSearchDialog {
    private JButton buttonByName;
    private JButton buttonByNameParents;
    private JButton buttonByCountBrotherandSisters;
    private JButton buttonByParentsSalary;
    private int x=20;
    private int higthAligment=20;
    private TextField firstData, secondData, thirdData;
    private int width =270;
    private int heigth=20;
    private JLabel jLabelFrom,jLabelTo,jMainLabel;
    private JButton actionButton;
    private ActionListener totalAcionListener;
    public CommonBetweenDeleteAndSearchDialog(){
        firstData = new TextField("");
        secondData = new TextField("");
        thirdData = new TextField("");
        jLabelFrom= new JLabel("от");
        jLabelTo= new JLabel("до");
        jMainLabel = new JLabel();
        actionButton=new JButton();
    }
    public JDialog createButtons(String string){

        JDialog tempDialog=new JDialog();
        tempDialog.getContentPane().setLayout(null);
        buttonByName = new JButton(string+" по ФИО студента");
        buttonByName.setBounds(x, higthAligment , width, heigth);
        tempDialog.add(buttonByName);

        buttonByNameParents = new JButton(string+" по ФИО одного из родителей");
        buttonByNameParents.setBounds(x, higthAligment + 30, width, heigth);
        tempDialog.add(buttonByNameParents);

        buttonByCountBrotherandSisters = new JButton(string+" по числу братьев или сестер");
        buttonByCountBrotherandSisters.setBounds(x, higthAligment + 60, width, heigth);
        tempDialog.add(buttonByCountBrotherandSisters);

        buttonByParentsSalary = new JButton(string+" по зарплате родителей");
        buttonByParentsSalary.setBounds(x, higthAligment + 90, width, heigth);
        tempDialog.add(buttonByParentsSalary);
        return tempDialog;
    }

    public  void addByName(String btnName, ActionListener actionForButton, JDialog tempDialog){
        totalAcionListener=actionForButton;
        jMainLabel.setText(btnName+" по ФИО: ");
        jMainLabel.setBounds(x, higthAligment +120, 300, 20);
        tempDialog.add(jMainLabel);
        firstData.setBounds(x, higthAligment+150, width/3, heigth);
        tempDialog.add(firstData);
        secondData.setBounds(x, higthAligment+180, width/3, heigth);
        tempDialog.add(secondData);
        thirdData.setBounds(x, higthAligment+ 210, width/3, heigth);
        tempDialog.add(thirdData);
        actionButton.setText(btnName);
        actionButton.setBounds(x, higthAligment + 240, 85, 20);
        tempDialog.add(actionButton);
        tempDialog.update(tempDialog.getGraphics());
        actionButton.addActionListener(actionForButton);
    }

    public  void addByCountBrothersandSisters(String btnName, ActionListener actionForButton, JDialog tempDialog){
        totalAcionListener=actionForButton;
        jMainLabel.setText(btnName+" по числу братьев или сестер: ");
        jMainLabel.setBounds(x, higthAligment +120, 300, 20);
        tempDialog.add(jMainLabel);
        firstData.setBounds(x, higthAligment+150, width/3, heigth);
        tempDialog.add(firstData);
        secondData.setBounds(x, higthAligment+180, width/3, heigth);
        tempDialog.add(secondData);

        actionButton.setText(btnName);
        actionButton.setBounds(x, higthAligment + 210, 85, 20);
        tempDialog.add(actionButton);
        tempDialog.update(tempDialog.getGraphics());
        actionButton.addActionListener(actionForButton);
    }

    public  void addStudentByParentsSalary(String btnName, ActionListener actionForButton, JDialog tempDialog){////////////////////////
        totalAcionListener=actionForButton;
        jMainLabel.setText(btnName+" по зарплате радителей: ");
        jMainLabel.setBounds(x, higthAligment +120, 300, heigth);
        tempDialog.add(jMainLabel);
        jLabelFrom.setBounds(x,higthAligment+150,20,heigth);
        tempDialog.add(jLabelFrom);
        firstData.setBounds(x+30, higthAligment+150,40 ,heigth);
        tempDialog.add(firstData);
        jLabelTo.setBounds(x+75,higthAligment+150,20,heigth);
        tempDialog.add(jLabelTo);
        secondData.setBounds(x+110, higthAligment+150,40,heigth);
        tempDialog.add(secondData);
        thirdData.setBounds(x+40, higthAligment+180,80,20);
        tempDialog.add(thirdData);
        actionButton.setText(btnName);
        actionButton.setBounds(x+40, higthAligment + 210 , 85, 20);
        tempDialog.add(actionButton);
        tempDialog.update(tempDialog.getGraphics());
        actionButton.addActionListener(actionForButton);
    }

    public void removeElements(JDialog tempDialog) {
        tempDialog.remove(jLabelFrom);
        tempDialog.remove(jLabelTo);
        tempDialog.remove(thirdData);
        tempDialog.remove(firstData);
        tempDialog.remove(secondData);
        tempDialog.remove(jMainLabel);
        firstData.setText("");
        secondData.setText("");
        thirdData.setText("");
        if(totalAcionListener!=null)
            actionButton.removeActionListener(totalAcionListener);
    }

    public String getFirstData() {
        return firstData.getText();
    }

    public String getSecondData() {
        return secondData.getText();
    }

    public String getThirdData() {
        return thirdData.getText();
    }

    public JButton getFindByName() {
        return buttonByName;
    }

    public JButton getFindBynNameParents() {
        return buttonByNameParents;
    }

    public JButton getFindByCountBrotherandSisters() {
        return buttonByCountBrotherandSisters;
    }

    public JButton getFindByParentsSalary() {
        return buttonByParentsSalary;
    }

}
