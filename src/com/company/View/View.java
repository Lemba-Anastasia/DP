package com.company.View;

import com.company.Model.DataBase;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Lemba on 08.05.2018.
 */


public class View {
    public DataBase dataBase ;
    private JFrame frame;

    public Table tablePanel;

    public View(){
        frame= new JFrame();
        dataBase= new DataBase();
        tablePanel = new Table(dataBase.studentsList);
        MyMenuBar myMenuBar = new MyMenuBar(dataBase,this, tablePanel);
        ToolBar myToolBar = new ToolBar(dataBase,this);

        frame.add(tablePanel, BorderLayout.CENTER);
        frame.add(myToolBar.getToolBar(), BorderLayout.NORTH);
        frame.setJMenuBar(myMenuBar.getMenuBar());

        frame.setSize(800,600);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public Table getTablePanel(){ return  tablePanel;}
    public Graphics getFramesGraphics(){ return  frame.getGraphics();}
    public Table getTabl(){ return  tablePanel;}
}