package com.company.View;

import com.company.Controller.MainController;
import com.company.Controller.Parser;
import com.company.Model.DataBase;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Lemba on 08.05.2018.
 */
public class MyMenuBar {
    private JMenuBar menuBar;
    private MainController controller;
    private Parser theParser;
    private JMenu fileMenu;
    private JMenuItem openFile;
    private JMenuItem saveFile;
    private JMenu toolsMenu;
    private JMenuItem addStudentTool;
    private JMenuItem deleteStudentTool;
    private JMenuItem searchStudentTool;


    public MyMenuBar(DataBase dataBase, final View view, Table table){

        this.controller=new MainController(dataBase);
        theParser = new Parser(dataBase,table);

        menuBar = new JMenuBar();

        fileMenu = new JMenu("Файл");
        menuBar.add(fileMenu);
        openFile = new JMenuItem("Открыть");
        fileMenu.add(openFile);
        saveFile = new JMenuItem("Сохранить");
        fileMenu.add(saveFile);
        fileMenu.addSeparator();

        toolsMenu = new JMenu("Инструменты");
        addStudentTool = new JMenuItem("Добавить");
        toolsMenu.add(addStudentTool);
        deleteStudentTool = new JMenuItem("Удалить");
        toolsMenu.add(deleteStudentTool);
        searchStudentTool = new JMenuItem("Поиск");
        toolsMenu.add(searchStudentTool);


        saveFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theParser.saveFile2();
            }
        });
        openFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theParser.openFile2();
            }
        });

        addStudentTool.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog add = new AddDialog(controller,view).getDialog();
                add.setSize(555,500);
                add.setVisible(true);
                add.setLocationRelativeTo(null);
            }
        });

        deleteStudentTool.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog add = new DeleteStudentDialog(controller,view).getDialog();
                add.setSize(330,500);
                add.setVisible(true);
                add.setLocationRelativeTo(null);
            }
        });
        searchStudentTool.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog add = new SearchStudentDialog(controller).getSeachDialog();
                add.setSize(555,500);
                add.setVisible(true);
                add.setLocationRelativeTo(null);

            }
        });

        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }
}
