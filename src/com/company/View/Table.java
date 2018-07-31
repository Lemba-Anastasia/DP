package com.company.View;

/**
 * Created by Lemba on 08.05.2018.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.company.Model.Student;

public class Table extends JPanel {
    private List<Student> studentsList;
    private int countStudOnPage = 10;
    private int currentPage = 1;


    public Table (List<Student> studentsList){
        this.studentsList = studentsList;
        setLayout(new BorderLayout());
        add(new JScrollPane(createTable(studentsList)));
        add(makeTableToolBar(), BorderLayout.SOUTH);
    }

    public JTable createTable(List<Student> studentsList){
        JTable tableWithStudents = new JTable(new Object[countStudOnPage][7], new String[]
                {"ФИО студента", "ФИО отца","Заработок отца","ФИО матери","Заработок матери","Число братьев","Число сестер"});
        tableWithStudents.setEnabled(false);
        int firstStudOnPage = countStudOnPage*(currentPage-1);
        for(int position = 0, stud = firstStudOnPage; position < countStudOnPage && stud < studentsList.size(); position++, stud++){
            tableWithStudents.setValueAt(studentsList.get(stud).getSurName() +
                    " " + studentsList.get(stud).getFirstName() +
                    " " + studentsList.get(stud).getSecondName(),position,0);
            tableWithStudents.setValueAt(studentsList.get(stud).getFather().getSurName() +
                    " " + studentsList.get(stud).getFather().getFirstName() +
                    " " + studentsList.get(stud).getFather().getSecondName(),position,1);
            tableWithStudents.setValueAt(studentsList.get(stud).getFather().getSalary(),position,2);
            tableWithStudents.setValueAt(studentsList.get(stud).getMother().getSurName() +
                    " " + studentsList.get(stud).getMother().getFirstName() +
                    " " + studentsList.get(stud).getMother().getSecondName(),position,3);
            tableWithStudents.setValueAt(studentsList.get(stud).getMother().getSalary(),position,4);
            tableWithStudents.setValueAt(studentsList.get(stud).getBrotherNum(),position,5);
            tableWithStudents.setValueAt(studentsList.get(stud).getSisterNum(),position,6);
        }

        return tableWithStudents;
    }

    private JPanel makeTableToolBar(){
        JPanel panelToolBar = new JPanel();
        JLabel jlab = new JLabel("  Cтрок на странице:");
        panelToolBar.add(jlab);
        String[] numberDisplayingStudents = {"10", "20", "30"};
        final JComboBox<String> checkNumberToDisplay = new JComboBox<>(numberDisplayingStudents);
        checkNumberToDisplay.setSelectedIndex(Arrays.asList(numberDisplayingStudents).indexOf(Integer.toString(countStudOnPage)));
        checkNumberToDisplay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                countStudOnPage = Integer.parseInt(checkNumberToDisplay.getSelectedItem().toString());
                updateTable();
            }
        });
        panelToolBar.add(checkNumberToDisplay);

        JButton firstPageButton = new JButton(new ImageIcon("src\\com\\company\\images\\doublebackward.jpg"));
        panelToolBar.add(firstPageButton);

        firstPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentPage = 1;
                updateTable();
            }
        });

        JButton prevPageButton = new JButton(new ImageIcon("src\\com\\company\\images\\backward.jpg"));
        panelToolBar.add(prevPageButton);

        prevPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentPage > 1){
                    currentPage--;
                    updateTable();
                }
                else
                    JOptionPane.showMessageDialog(null, "Это первая страница.");
            }
        });

        String pageInfoStr = "Страница " + currentPage + " из " + getNumberofMaxPage();
        JLabel pageInfo = new JLabel(pageInfoStr);
        panelToolBar.add(pageInfo);
        JButton nextPageButton = new JButton(new ImageIcon("src\\com\\company\\images\\forward.jpg"));
        panelToolBar.add(nextPageButton);

        nextPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentPage < getNumberofMaxPage()){
                    currentPage++;
                    updateTable();
                }
                else
                    JOptionPane.showMessageDialog(null, "Конец списка.");
            }
        });


        JButton lastPageButton = new JButton(new ImageIcon("src\\com\\company\\images\\doubleforward.jpg"));
        panelToolBar.add(lastPageButton);

        lastPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentPage = getNumberofMaxPage();
                updateTable();
            }
        });
        int list;
        if(currentPage!=getNumberofMaxPage()){list= countStudOnPage*currentPage;}
        else{list = studentsList.size()-(getNumberofMaxPage()-1)*countStudOnPage;}

        JLabel info = new JLabel("Записей: "+ list+"/"+studentsList.size());
        info.setSize(50,30);
        panelToolBar.add(info);

        return panelToolBar;
    }

    private int getNumberofMaxPage(){
        return ((studentsList.size()-1)/countStudOnPage + 1);
    }


    public void setStudents(List<Student> studentsList) {
        this.studentsList = studentsList;
    }

    public void updateTable(){
        removeAll();
        updateUI();
        add(new JScrollPane(createTable(studentsList)));
        add(makeTableToolBar(), BorderLayout.SOUTH);
        revalidate();
        repaint();
    }
}
