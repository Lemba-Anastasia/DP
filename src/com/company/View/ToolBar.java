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
    private DataBase dataBase;
    public JToolBar toolBar;
    private View view;
    private MainController controller;


    public ToolBar(final DataBase dataBase, final View view){

        this.dataBase=dataBase;
        this.view=view;
        controller=new MainController(dataBase);
        ImageIcon addSudentIcon = new ImageIcon("src\\com\\company\\images\\addStudentIcon.png");
        JButton addStudent = new JButton(addSudentIcon);

        ImageIcon deleteSudentIcon = new ImageIcon("src\\com\\company\\images\\deleteStudentIcon.png");
        JButton deleteStudent = new JButton(deleteSudentIcon);

        ImageIcon searchSudentIcon = new ImageIcon("src\\com\\company\\images\\searchStudentIcon.png");
        final JButton searchStudent = new JButton(searchSudentIcon);


        toolBar = new JToolBar();
        toolBar.add(addStudent);
        toolBar.add(deleteStudent);
        toolBar.add(searchStudent);


        addStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog add = new AddDialog(controller, view).getDialog();
                add.setSize(555,500);
                add.setVisible(true);
                add.setLocationRelativeTo(null);
            }
        });
        deleteStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog add = new DeleteDialog(controller,view ).getDialog();
                add.setSize(330,500);
                add.setVisible(true);
                add.setLocationRelativeTo(null);
            }
        });
        searchStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog searchDialog = new SearchStudentDialog(controller).getDialog();
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
