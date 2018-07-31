package com.company.Controller;


import com.company.Model.DataBase;
import com.company.Model.Parent;
import com.company.Model.Student;
import com.company.View.Table;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Parser {
    private DataBase dataBase;
    private Table table;
    private Student tempStudent;
    private String tempData;
    private Parent dad,mom;


    public Parser(DataBase dataBase, Table table){
        this.table = table;
        this.dataBase=dataBase;
        tempStudent=new Student();
        tempData="";

    }


    public void saveFile2(){
        List<Student> studentArrayList = dataBase.studentsList;
        try
        {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();
            Element students = document.createElement("students");
            document.appendChild(students);
            for(Student stud:studentArrayList) {
                Element student = document.createElement("student");
                students.appendChild(student);

                Element surName = document.createElement("surName");
                surName.appendChild(document.createTextNode(stud.getSurName()));
                student.appendChild(surName);

                Element firstName = document.createElement("firstName");
                firstName.appendChild(document.createTextNode(stud.getFirstName()));
                student.appendChild(firstName);

                Element secName = document.createElement("secName");
                secName.appendChild(document.createTextNode(stud.getSecondName()));
                student.appendChild(secName);

                Element dad = document.createElement("dad");
                student.appendChild(dad);

                Element dadSurName = document.createElement("dadSurName");
                dadSurName.appendChild(document.createTextNode(stud.getFather().getSurName()));
                student.appendChild(dadSurName);

                Element dadFirstName = document.createElement("dadFirstName");
                dadFirstName.appendChild(document.createTextNode(stud.getFather().getFirstName()));
                student.appendChild(dadFirstName);

                Element dadSecName = document.createElement("dadSecName");
                dadSecName.appendChild(document.createTextNode(stud.getFather().getSecondName()));
                student.appendChild(dadSecName);

                Element dadSalary = document.createElement("dadSalary");
                dadSalary.appendChild(document.createTextNode(String.valueOf(stud.getFather().getSalary())));
                student.appendChild(dadSalary);

                Element mom = document.createElement("mom");
                student.appendChild(mom);

                Element momSurName = document.createElement("momSurName");
                momSurName.appendChild(document.createTextNode(stud.getMother().getSurName()));
                student.appendChild(momSurName);

                Element momFirstName = document.createElement("momFirstName");
                momFirstName.appendChild(document.createTextNode(stud.getMother().getFirstName()));
                student.appendChild(momFirstName);

                Element momSecName = document.createElement("momSecName");
                momSecName.appendChild(document.createTextNode(stud.getMother().getSecondName()));
                student.appendChild(momSecName);

                Element momSalary = document.createElement("momSalary");
                momSalary.appendChild(document.createTextNode(String.valueOf(stud.getMother().getSalary())));
                student.appendChild(momSalary);

                Element countBrothers = document.createElement("countOfBrothers");
                countBrothers.appendChild(document.createTextNode(String.valueOf(stud.getBrotherNum())));
                student.appendChild(countBrothers);

                Element countSisters = document.createElement("countOfSisters");
                countSisters.appendChild(document.createTextNode(String.valueOf(stud.getSisterNum())));
                student.appendChild(countSisters);

            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            JFileChooser jf = new JFileChooser();
            String fileName = null;
            int result = jf.showSaveDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                fileName = jf.getSelectedFile().getAbsolutePath();
            }
            StreamResult streamResult = new StreamResult(new File(fileName));
            transformer.transform(domSource, streamResult);
        }
        catch (ParserConfigurationException pce)
        {
            System.out.println(pce.getLocalizedMessage());
            pce.printStackTrace();
        }
        catch (TransformerException te)
        {
            System.out.println(te.getLocalizedMessage());
            te.printStackTrace();
        }
    }

    public void openFile2(){
        dataBase.removeStudents(dataBase.studentsList);
        JFileChooser fOpen = new JFileChooser();
        fOpen.setFileFilter(new FileNameExtensionFilter(".xml", "xml"));
        if (fOpen.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            String fileName = fOpen.getSelectedFile().getPath();

            try {
                SAXParserFactory factory = SAXParserFactory.newInstance();
                final SAXParser saxParser = factory.newSAXParser();

                DefaultHandler handler = new DefaultHandler() {

                    @Override
                    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                        if(qName.equals("student")){
                            System.out.println("student #");
                            tempStudent=new Student();
                        }
                        if(qName.equals( "dad")){
                            System.out.println("dad #");
                            dad = new Parent();
                            tempStudent.setFather(dad);
                        }
                        if(qName.equals("mom")){
                            System.out.println("mom #");
                            mom = new Parent();
                            tempStudent.setMother(mom);
                        }
                    }


                    @Override
                    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
                        switch (qName) {
                            case "surName":
                                System.out.println("surName: " + tempData);
                                tempStudent.setSurName(tempData);
                                break;
                            case "firstName":
                                System.out.println("firstName: " + tempData);
                                tempStudent.setFirstName(tempData);
                                break;
                            case "secName":
                                System.out.println("secName: " + tempData);
                                tempStudent.setSecondName(tempData);
                                break;
                            case "dadSurName":
                                System.out.println("dadSurName: " + tempData);
                                dad.setSurName(tempData);

                                break;
                            case "dadFirstName":
                                System.out.println("dadFirstName: " + tempData);
                                dad.setFirstName(tempData);
                                break;
                            case "dadSecName":
                                System.out.println("dadSecName: " + tempData);
                                dad.setSecondName(tempData);
                                break;
                            case "dadSalary":
                                System.out.println("dadSalary: " + tempData);
                                dad.setSalary(Integer.parseInt(tempData));
                                break;

                            case "momSurName":
                                System.out.println("momSurName: " + tempData);
                                mom.setSurName(tempData);
                                break;
                            case "momFirstName":
                                System.out.println("momFirstName: " + tempData);
                                mom.setFirstName(tempData);
                                break;
                            case "momSecName":
                                System.out.println("momSecName: " + tempData);
                                mom.setSecondName(tempData);
                                break;
                            case "momSalary":
                                System.out.println("momSalary: " + tempData);
                                mom.setSalary(Integer.parseInt(tempData));
                                break;
                            case "countOfBrothers":
                                System.out.println("countBrother: " + tempData);
                                tempStudent.setBrotherNum(Integer.parseInt(tempData));
                                break;
                            case "countOfSisters":
                                System.out.println("countOfSisters: " + tempData);
                                tempStudent.setSisterNum(Integer.parseInt(tempData));
                                break;
                            case "student":
                                dataBase.addStud(tempStudent);
                                break;
                        }
                    }

                    @Override
                    public void characters(char ch[], int start, int length) throws SAXException {
                        tempData=new String(ch,start,length);
                        if(tempData.equals("")) return;

                    }
                };

                saxParser.parse(fileName, handler);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        table.updateTable();

    }
}
