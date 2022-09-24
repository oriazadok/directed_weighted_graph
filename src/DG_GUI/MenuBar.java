package DG_GUI;

import Ex2.My_Algo;
import api.DirectedWeightedGraphAlgorithms;


import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;

public class MenuBar extends JFrame implements ActionListener {

    DirectedWeightedGraphAlgorithms alg;
    Panel panel;

    JMenuBar menuBar;

    JMenu graphMenu;
    JMenu functionsMenu;
    JMenu exitMenu;

    JMenuItem loadItem;
    JMenuItem saveItem;

    JMenuItem IsConnectedItem;
    JMenuItem ShortestPathDistItem;
    JMenuItem ShortestPathItem;
    JMenuItem CenterItem;
    JMenuItem TspItem;
    JMenuItem exitItem;

    // Builder
    public MenuBar(DirectedWeightedGraphAlgorithms alg, Panel panel) {

        this.alg = alg;
        this.panel = panel;

        menuBar = new JMenuBar();

        graphMenu = new JMenu("Graph");
        functionsMenu = new JMenu("Functions");
        exitMenu = new JMenu("Exit");

        loadItem = new JMenuItem("Load");
        saveItem = new JMenuItem("Save");

        IsConnectedItem = new JMenuItem("IsConnected");
        ShortestPathDistItem = new JMenuItem("ShortestPathDist");
        ShortestPathItem = new JMenuItem("ShortestPath");
        CenterItem = new JMenuItem("Center");
        TspItem = new JMenuItem("Tsp");

        exitItem = new JMenuItem("Exit");


        loadItem.addActionListener(this);
        saveItem.addActionListener(this);

        IsConnectedItem.addActionListener(this);
        ShortestPathDistItem.addActionListener(this);
        ShortestPathItem.addActionListener(this);
        CenterItem.addActionListener(this);
        TspItem.addActionListener(this);

        exitItem.addActionListener(this);


        graphMenu.add(loadItem);
        graphMenu.add(saveItem);

        functionsMenu.add(IsConnectedItem);
        functionsMenu.add(ShortestPathDistItem);
        functionsMenu.add(ShortestPathItem);
        functionsMenu.add(CenterItem);
        functionsMenu.add(TspItem);

        exitMenu.add(exitItem);


        menuBar.add(graphMenu);
        menuBar.add(functionsMenu);
        menuBar.add(exitMenu);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadItem) {
            GetGraph getGraph = new GetGraph(this.alg, panel);
            System.out.println("loaded");
        }

        if (e.getSource() == saveItem) {

            JTextField text = new JTextField();
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Specify a file to save");

            int choose = fileChooser.showSaveDialog(text);

            if (choose == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
            }
            alg.save(fileChooser.getSelectedFile().getAbsolutePath());
        }

        if (e.getSource() == IsConnectedItem) {

            String res = "";
            boolean b = alg.isConnected();
            if (b == true) {
                res = "Connected";
            }
            else {
                res = "Not Connected";
            }

            ResponsePage responsePage = new ResponsePage(res, 1);
        }

        if (e.getSource() == ShortestPathDistItem) {
            GetInfoPage getInfoPage = new GetInfoPage(alg, "shortestPathDist");
        }

        if (e.getSource() == ShortestPathItem) {
            GetInfoPage getInfoPage = new GetInfoPage(alg, "shortestPath");
        }

        if (e.getSource() == CenterItem) {
            My_Algo centerNode = null;
            int center = alg.center().getKey();
            String res = "" + center;
            ResponsePage responsePage = new ResponsePage(res, 4);
        }

        if (e.getSource() == TspItem) {
            GetCities cities = new GetCities(alg, "tsp");
        }

        if (e.getSource() == exitItem) {
            System.exit(0);
        }
    }


}
