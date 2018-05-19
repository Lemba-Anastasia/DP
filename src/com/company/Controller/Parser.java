package com.company.Controller;

import com.company.Model.DataBase;
import com.company.Model.Parent;
import com.company.Model.Student;
import com.company.View.Table;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileInputStream;
import java.io.FileWriter;

/**
 * Created by Lemba on 18.05.2018.
 */
public class Parser {
    private DataBase dataBase;
    private Table table;
    public Parser(DataBase dataBase, Table table){
        this.table = table;
        this.dataBase=dataBase;
    }

    public void saveFile(){
        try{
            JFileChooser fSave = new JFileChooser();
            if (fSave.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
                XMLOutputFactory output = XMLOutputFactory.newInstance();
                XMLStreamWriter writer = output.createXMLStreamWriter(new FileWriter(fSave.getSelectedFile() + "." + "xml"));
                writer.writeStartDocument("UTF-8", "1.0");

                writer.writeStartElement("students");

                for (int i = 0; i < dataBase.getStudents().size(); i++){
                    writer.writeStartElement("student");

                    writer.writeAttribute(ParserConsts.STUDSURNAME, dataBase.getStudentByIndex(i).getSurName());
                    writer.writeAttribute(ParserConsts.STUDFNAME, dataBase.getStudentByIndex(i).getFirstName());
                    writer.writeAttribute(ParserConsts.STUDSNAME, dataBase.getStudentByIndex(i).getSecondName());

                    writer.writeAttribute(ParserConsts.DADSURNAME, dataBase.getStudentByIndex(i).getFather().getSurName());
                    writer.writeAttribute(ParserConsts.DADFNAME, dataBase.getStudentByIndex(i).getFather().getFirstName());
                    writer.writeAttribute(ParserConsts.DADSNAME, dataBase.getStudentByIndex(i).getFather().getSecondName());
                    writer.writeAttribute(ParserConsts.DADSALARY, String.valueOf(dataBase.getStudentByIndex(i).getFather().getSalary()));

                    writer.writeAttribute(ParserConsts.MUMSURNAME, dataBase.getStudentByIndex(i).getMother().getSurName());
                    writer.writeAttribute(ParserConsts.MUMFNAME, dataBase.getStudentByIndex(i).getMother().getFirstName());
                    writer.writeAttribute(ParserConsts.MUMSNAME, dataBase.getStudentByIndex(i).getMother().getSecondName());
                    writer.writeAttribute(ParserConsts.MUMSALARY, String.valueOf(dataBase.getStudentByIndex(i).getMother().getSalary()));

                    writer.writeAttribute(ParserConsts.BROTHNUM, String.valueOf(dataBase.getStudentByIndex(i).getBrotherNum()));
                    writer.writeAttribute(ParserConsts.SISTNUM, String.valueOf(dataBase.getStudentByIndex(i).getSisterNum()));

                    writer.writeEndElement();
                }
                writer.writeEndElement();
                writer.writeEndDocument();
                writer.flush();
            }

        }catch( Exception e){
            JOptionPane.showMessageDialog(null, "Почему-то невозможно сохранить файл");
        }
    }

    public void openFile(){
        try{
            JFileChooser fOpen = new JFileChooser();
            fOpen.setFileFilter(new FileNameExtensionFilter(".xml", "xml"));
            if (fOpen.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                String fileName = fOpen.getSelectedFile().getPath();

                String studSurname = "";
                String studFirName = "";
                String studSecName = "";
                String dadSurname = "";
                String dadFirName = "";
                String dadSecName = "";
                String dadSalary = "";
                String mumSurname = "";
                String mumFirName = "";
                String mumSecName = "";
                String mumSalary = "";
                String brotherNum = "";
                String sisterNum = "";
                dataBase.getStudents().clear();

                table.updateTable();////////////////
                XMLStreamReader xmlReader = XMLInputFactory.newInstance()
                        .createXMLStreamReader(fileName, new FileInputStream(fileName));
                while (xmlReader.hasNext()){
                    xmlReader.next();
                    if (xmlReader.isStartElement()){
                        if (xmlReader.getLocalName().equals("student")){
                            studSurname = xmlReader.getAttributeValue(null, ParserConsts.STUDSURNAME);
                            studFirName = xmlReader.getAttributeValue(null,ParserConsts.STUDFNAME);
                            studSecName = xmlReader.getAttributeValue(null, ParserConsts.STUDSNAME);
                            dadSurname = xmlReader.getAttributeValue(null,ParserConsts.DADSURNAME);
                            dadFirName = xmlReader.getAttributeValue(null,ParserConsts.DADFNAME);
                            dadSecName = xmlReader.getAttributeValue(null,ParserConsts.DADSNAME);
                            dadSalary = xmlReader.getAttributeValue(null,ParserConsts.DADSALARY);
                            mumSurname = xmlReader.getAttributeValue(null,ParserConsts.MUMSURNAME);
                            mumFirName = xmlReader.getAttributeValue(null,ParserConsts.MUMFNAME);
                            mumSecName = xmlReader.getAttributeValue(null,ParserConsts.MUMSNAME);
                            mumSalary = xmlReader.getAttributeValue(null,ParserConsts.MUMSALARY);
                            brotherNum = xmlReader.getAttributeValue(null,ParserConsts.BROTHNUM);
                            sisterNum = xmlReader.getAttributeValue(null,ParserConsts.SISTNUM);
                            Parent father = new Parent(dadSurname,dadFirName,dadSecName,Integer.parseInt(dadSalary));
                            Parent mother = new Parent(mumSurname,mumFirName,mumSecName,Integer.parseInt(mumSalary));

                            Student student = new Student(studSurname,studFirName,studSecName, father, mother,
                                    Integer.parseInt(brotherNum),Integer.parseInt(sisterNum));
                            dataBase.addStud(student);

                        }
                    }
                }

            }
        }catch (Exception e){
            JOptionPane.showMessageDialog
                    (null, "Can't open file", "Error:", JOptionPane.ERROR_MESSAGE);
        }
        table.updateTable();
    }

    class ParserConsts{
        static final String STUDSURNAME = "studSurname";
        static final String STUDFNAME = "studFirName";
        static final String STUDSNAME = "studSecName";

        static final String DADSURNAME = "dadSurname";
        static final String DADFNAME = "dadFirName";
        static final String DADSNAME = "dadSecName";
        static final String DADSALARY = "dadSalary";

        static final String MUMSURNAME = "mumSurname";
        static final String MUMFNAME = "mumFirName";
        static final String MUMSNAME = "mumSecName";
        static final String MUMSALARY = "mumSalary";

        static final String BROTHNUM = "brotherCount";
        static final String SISTNUM = "sisterCount";
    }

}
