package com.frames;

import com.Airport;
import com.CentralRegistry;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.TreeLayout;
import edu.uci.ics.jung.algorithms.shortestpath.DistanceStatistics;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindAirportFrame extends JFrame{
    private final JPanel panel = new JPanel();
    private final JTextField inputField = new JTextField(10);
    private final JButton btn = new JButton("Find");
    private JButton visualizeBtn = new JButton("Visualize Network");
    private JTextField diameter = new JTextField(15);

    public FindAirportFrame(){
        panel.add(inputField);
        panel.add(btn);
        panel.add(visualizeBtn);
        setListeners();


        this.setContentPane(panel);
        this.setVisible(true);
        this.setSize(300,200);
        this.setTitle("Find com.Airport");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    private void setListeners() {
        ActionListener listener = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String city = inputField.getText();
                if (e.getSource().equals(btn)){
                    if (CentralRegistry.getAirport(city) == null){
                        JOptionPane.showMessageDialog(new JFrame(), city + " dose not have an airport!", "Message", JOptionPane.ERROR_MESSAGE);
                    }else{
                        new AirportPageFrame(CentralRegistry.getAirport(city));
                        dispose();
                    }
                }else if (e.getSource().equals(visualizeBtn)){
                    UndirectedSparseGraph<String, String> graph = new UndirectedSparseGraph<>();
                    for (Airport airport : CentralRegistry.getAirports()){
                        graph.addVertex(airport.getCity());
                    }
                    int i = 1;
                    for (Airport airportA : CentralRegistry.getAirports()){
                        for (Airport airportB : CentralRegistry.getAirports()){
                            if (airportA.isDirectlyConnectedTo(airportB)){
                                graph.addEdge("Edge" + i, airportA.getCity(), airportB.getCity());
                                i++;
                            }
                        }
                    }

                    VisualizationViewer<String, String> vv = new VisualizationViewer<>(new CircleLayout<>(graph), new Dimension(500, 300));

                    vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<String>());

                    diameter.setText("Diameter = " + DistanceStatistics.diameter(graph));

                    JPanel frame = new JPanel();
                    frame.add(vv);
                    frame.add(diameter);
                    setContentPane(frame);
                    setSize(450,450);
                    frame.setVisible(true);
                }
            }
        };

        btn.addActionListener(listener);
        visualizeBtn.addActionListener(listener);
    }
}
