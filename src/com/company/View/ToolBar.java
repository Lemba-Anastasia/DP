package com.company.View;

import com.company.Controller.MainController;
import com.company.Model.DataBase;
import com.sun.deploy.panel.DeleteFilesDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Lemba on 09.05.2018.
 */
public class ToolBar
{
    public JToolBar toolBar;
    private MainController controller;


    public ToolBar(final DataBase dataBase, final View view){

        controller=new MainController(dataBase);
        ImageIcon addSudentIcon = new ImageIcon("src\\com\\company\\images\\addStudentIcon.png");
        JButton addStudent = new JButton(addSudentIcon);

        ImageIcon deleteSudentIcon2 = new ImageIcon("src\\com\\company\\images\\deleteStudentIcon.png");
        JButton deleteStudent2 = new JButton(deleteSudentIcon2);

        ImageIcon searchSudentIcon2 = new ImageIcon("src\\com\\company\\images\\searchStudentIcon.png");
        final JButton searchStudent2 = new JButton(searchSudentIcon2);


        toolBar = new JToolBar();
        toolBar.add(addStudent);
        toolBar.add(deleteStudent2);
        toolBar.add(searchStudent2);


        addStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog add = new AddDialog(controller, view).getDialog();
                add.setSize(555,500);
                add.setVisible(true);
                add.setLocationRelativeTo(null);
            }
        });

        deleteStudent2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog add = new DeleteStudentDialog(controller,view ).getDialog();
                add.setSize(330,500);
                add.setVisible(true);
                add.setLocationRelativeTo(null);
            }
        });

        searchStudent2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog searchDialog = new SearchStudentDialog(controller).getSeachDialog();
                searchDialog.setBounds(200, 100, 900, 500);
                searchDialog.setVisible(true);
            }
        });

        toolBar.addSeparator();

    }

    public JToolBar getToolBar(){
        return toolBar;
    }
}
