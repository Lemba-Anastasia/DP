package com.company.View;

import com.company.Controller.MainController;
import com.company.Model.DataBase;
import com.company.Model.Student;

import javax.accessibility.AccessibleComponent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Lemba on 11.05.2018.
 */
public class SearchStudentDialog {
    private JDialog seachDialog;
    private JButton findByName;
    private JButton findBynNameParents;
    private JButton findByCountBrotherandSisters;
    private  JButton findByParentsSalary;
    private int higthAligment=20;
    private int heigth=20;
    private int width=270;
    private int x =20;
    private Table table;
    private MainController controllerSearch;
    private TextField firstData, secondData, thirdData;
    private JLabel jLabelFrom,jLabelTo,jMainLabel;
    private JButton actionButton;
    private ArrayList<Student> copyMainStudentList;
    private String resultChooseParent;
    private ActionListener totalAcionListener;

    public SearchStudentDialog (MainController mainController){
        firstData = new TextField("");
        secondData = new TextField("");
        thirdData = new TextField("");
        jLabelFrom= new JLabel("от");
        jLabelTo= new JLabel("до");
        jMainLabel = new JLabel();
        controllerSearch=mainController;
        seachDialog= new JDialog();
        actionButton=new JButton();
        table=new Table(mainController.getStudentList());
        copyMainStudentList = mainController.getStudentList();
        seachDialog=createButtonForSeach("Поиск");
        table.setBounds(x+width+10, higthAligment, 550, 400);
        seachDialog.add(table);
        createActionButton();

    }

     public JDialog createButtonForSeach(String string){
        JDialog tempDialog=new JDialog();
        tempDialog.getContentPane().setLayout(null);
        findByName = new JButton(string+" по ФИО студента");
        findByName.setBounds(x, higthAligment , width, heigth);
        tempDialog.add(findByName);

        findBynNameParents = new JButton(string+" по ФИО одного из родителей");
        findBynNameParents.setBounds(x, higthAligment + 30, width, heigth);
        tempDialog.add(findBynNameParents);

        findByCountBrotherandSisters = new JButton(string+" по числу братьев или сестер");
        findByCountBrotherandSisters.setBounds(x, higthAligment + 60, width, heigth);
        tempDialog.add(findByCountBrotherandSisters);

        findByParentsSalary = new JButton(string+" по зарплате родителей");
        findByParentsSalary.setBounds(x, higthAligment + 90, width, heigth);
        tempDialog.add(findByParentsSalary);
        return tempDialog;
    }

    private void createActionButton(){

        findByName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                removeElements(seachDialog);
                returnTostartPositionTable();
                addFindByName("Поиск", new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        if (firstData.getText().equals("") || secondData.getText().equals("") || thirdData.getText().equals("")){
                            JOptionPane.showMessageDialog(null, "Введите ФИО студента.");
                            return;
                        }
                        ArrayList<Student> tempSearchBase=controllerSearch.findStudentByName(firstData.getText(), secondData.getText(),thirdData.getText());
                        table.setStudents(tempSearchBase);
                        table.updateTable();
                    }
                },seachDialog);
            }
        });

        findBynNameParents.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                returnTostartPositionTable();
                removeElements(seachDialog);
                addFindByName("Поиск", new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        if (firstData.getText().equals("") || secondData.getText().equals("") || thirdData.getText().equals("")){
                            JOptionPane.showMessageDialog(null, "Введите ФИО дного из родителей.");
                            return;
                        }
                        ArrayList<Student> tempSearchBasedDads=controllerSearch.findParentByName(firstData.getText(), secondData.getText(),thirdData.getText());

                        table.setStudents(tempSearchBasedDads);

                        table.updateTable();
                    }
                },seachDialog);
            }
        });

        findByCountBrotherandSisters.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                returnTostartPositionTable();
                removeElements(seachDialog);

                addFindByCountBrothersandSisters("Поиск", new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        if (firstData.getText().equals("") || secondData.getText().equals("") ){
                            JOptionPane.showMessageDialog(null, "Введите число братьев и сестер.");
                            return;
                        }
                        ArrayList<Student> tempSearchBase=controllerSearch.findBrothersAndSisters(firstData.getText(), secondData.getText());
                        table.setStudents(tempSearchBase);
                        table.updateTable();
                    }
                },seachDialog);
            }
        });

        findByParentsSalary.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                returnTostartPositionTable();
                removeElements(seachDialog);
                addFindStudentByParentsSalary("Поиск", new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        ///////////////

                        /*String[] searchCritarion = {"Мать","Отец"};
                        final JComboBox<String> chooseCriteria = new JComboBox<>(searchCritarion);
                        chooseCriteria.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                resultChooseParent=(String)chooseCriteria.getSelectedItem();
                            }
                        });
                        chooseCriteria.setEditable(true);
                        chooseCriteria.setMaximumRowCount(2);
                        chooseCriteria.setBounds(140,higthAligment+150,25,20);
                        seachDialog.add(chooseCriteria);*/
                        //////////////
                        if ((firstData.getText().equals("") || secondData.getText().equals("") )&& thirdData.getText().equals("") ){
                            JOptionPane.showMessageDialog(null, "Введите интервал искомой з/п одного из родителя или формат родителя.");
                            return;
                        }
                        ArrayList<Student> tempSearchBase=controllerSearch.findByParentsSalary(firstData.getText(), secondData.getText(), thirdData.getText());
                        table.setStudents(tempSearchBase);
                        table.updateTable();
                    }
                },seachDialog);
            }
        });

    }

    public  void addFindByName(String btnName, ActionListener actionForButton, JDialog tempDialog){
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

    public  void addFindByCountBrothersandSisters(String btnName, ActionListener actionForButton, JDialog tempDialog){
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

    public  void addFindStudentByParentsSalary(String btnName, ActionListener actionForButton, JDialog tempDialog){////////////////////////
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

    public JDialog getDialog() {
        return seachDialog;
    }

    private void returnTostartPositionTable(){
        table.setStudents(copyMainStudentList);
        table.updateTable();
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

    public JDialog getjDialog() {
        return seachDialog;
    }

    public JButton getFindByName() {
        return findByName;
    }

    public JButton getFindBynNameParents() {
        return findBynNameParents;
    }

    public JButton getFindByCountBrotherandSisters() {
        return findByCountBrotherandSisters;
    }

    public JButton getFindByParentsSalary() {
        return findByParentsSalary;
    }

    public TextField getFirstData() {
        return firstData;
    }

    public TextField getSecondData() {
        return secondData;
    }

    public TextField getThirdData() {
        return thirdData;
    }
}
