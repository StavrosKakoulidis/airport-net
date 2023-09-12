package com.frames;

import com.Airport;
import com.CentralRegistry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;

public class AirportPageFrame extends JFrame {
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;
    private JPanel panel6;
    private JPanel centralPanel;
    private JTextField airportField = new JTextField(10);
    private JTextField codeField = new JTextField(10);
    private JTextField cityField = new JTextField(10);
    private JTextField countryField = new JTextField(10);
    private JTextArea companiesText =new JTextArea(5,8);
    private JTextField destinationField = new JTextField(10);
    private JButton btn = new JButton("Find Flights");
    private JTextArea directFlightsText =new JTextArea(10,20);
    private JTextArea indirectFlightsText =new JTextArea(10,20);
    private JButton backToBtn = new JButton("Back To Search Screen");

    public AirportPageFrame(Airport anAirport){

        panel1 = new JPanel(new FlowLayout());
        panel2 = new JPanel(new FlowLayout());
        panel3 = new JPanel(new FlowLayout());
        panel4 = new JPanel(new BorderLayout());
        panel5 = new JPanel(new BorderLayout());
        panel6 = new JPanel(new FlowLayout());

        centralPanel = new JPanel(new BorderLayout());

        panel1.add(airportField);
        panel1.add(codeField);
        panel1.add(cityField);
        panel1.add(countryField);
        panel1.add(companiesText);
        panel3.add(destinationField);
        panel3.add(btn);
        panel4.add(panel1,BorderLayout.NORTH);
        panel4.add(panel3,BorderLayout.CENTER);

        panel2.add(directFlightsText);
        panel2.add(indirectFlightsText);
        panel6.add(backToBtn);
        panel5.add(panel2,BorderLayout.NORTH);
        panel5.add(panel6,BorderLayout.CENTER);

        centralPanel.add(panel4, BorderLayout.NORTH);
        centralPanel.add(panel5,BorderLayout.CENTER);

        airportField.setText(anAirport.getName());
        codeField.setText(anAirport.getCodeName());
        cityField.setText(anAirport.getCity());
        countryField.setText(anAirport.getCountry());

        ArrayList<String> airLines = anAirport.getAirlineName();
        Collections.sort(airLines);

        for(String airline : airLines)
        {
            companiesText.append(airline);
            companiesText.append("\n");
        }

        setListeners(anAirport);
        this.setContentPane(centralPanel);
        this.setVisible(true);
        this.setSize(700,500);
        this.setTitle("com.Airport Page");



    }

    public void setListeners(Airport anAirport){
        ActionListener listener = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String destinationCity = destinationField.getText();
                if (e.getSource().equals(btn)){
                    if(destinationCity.equals(anAirport.getCity())){
                        JOptionPane.showMessageDialog(new JFrame()," Arrival and Departure city cannot be the same!", "Message",
                                JOptionPane.ERROR_MESSAGE);
                    }else{
                        directFlightsText.append("DIRECT FLIGHTS DETAILS\n");
                        indirectFlightsText.append("INDIRECT FLIGHTS through...\n");
                        directFlightsText.append(CentralRegistry.getDirectFlightsDetails(anAirport, CentralRegistry.getAirport(destinationCity)));
                        indirectFlightsText.append(CentralRegistry.getInDirectFlightsDetail(anAirport, CentralRegistry.getAirport(destinationCity)));
                        try {
                            FileOutputStream fileOut = new FileOutputStream(anAirport.getCity()+"To"+destinationCity+".txt");
                            PrintStream out = new PrintStream(fileOut);
                            out.println("City: "+anAirport.getCity()+", "+anAirport.getCountry());
                            out.println("Airport: "+anAirport.getName()+" "+"("+anAirport.getCodeName()+")"+System.lineSeparator());
                            out.println("Destination: "+destinationCity+System.lineSeparator());
                            out.print("DIRECT FLIGHTS DETAILS:");
                            out.println(CentralRegistry.getDirectFlightsDetails(anAirport,CentralRegistry.getAirport(destinationCity))+System.lineSeparator());
                            out.print("INDIRECT FLIGHTS through...");
                            out.println(CentralRegistry.getInDirectFlightsDetail(anAirport,CentralRegistry.getAirport(destinationCity)));
                            out.close();
                            fileOut.close();
                        } catch (IOException exception) {
                            exception.printStackTrace();
                        }

                    }
                }else if (e.getSource().equals(backToBtn)){
                    new FindAirportFrame();
                    dispose();
                }
            }
        };

        btn.addActionListener(listener);
        backToBtn.addActionListener(listener);
    }
}
