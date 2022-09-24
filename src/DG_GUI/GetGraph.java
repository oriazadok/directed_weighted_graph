package DG_GUI;

import api.DirectedWeightedGraphAlgorithms;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;

public class GetGraph implements ActionListener {

    JFrame frame = new JFrame();

    JLabel choose = new JLabel("Choose a graph: ");


    JButton buttonG1 = new JButton("G1.json");
    JButton buttonG2 = new JButton("G2.json");
    JButton buttonG3 = new JButton("G3.json");
    JButton buttonG4 = new JButton("G4.json");
    JButton buttonG5 = new JButton("import");

    DirectedWeightedGraphAlgorithms alg;
    Panel panel;
    public GetGraph(DirectedWeightedGraphAlgorithms alg, Panel panel) {

        this.alg = alg;
        this.panel = panel;

        choose.setBounds(50, 100, 150, 25);

        buttonG1.setBounds(125, 100, 100, 25);
        buttonG1.setFocusable(false);
        buttonG1.addActionListener(this);

        buttonG2.setBounds(125, 150, 100, 25);
        buttonG2.setFocusable(false);
        buttonG2.addActionListener(this);

        buttonG3.setBounds(125, 200, 100, 25);
        buttonG3.setFocusable(false);
        buttonG3.addActionListener(this);

        buttonG4.setBounds(125, 250, 100, 25);
        buttonG4.setFocusable(false);
        buttonG4.addActionListener(this);

        buttonG5.setBounds(125, 300, 100, 25);
        buttonG5.setFocusable(false);
        buttonG5.addActionListener(this);


        frame.add(buttonG1);
        frame.add(buttonG2);
        frame.add(buttonG3);
        frame.add(buttonG4);
        frame.add(buttonG5);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String g1 = "data/G1.json";
        String g2 = "data/G2.json";
        String g3 = "data/G3.json";
        String notConnected = "data/NotConnected.json";

        if (e.getSource() == buttonG1) {
            alg.load(g1);
            System.out.println("g1");
            frame.dispose();
        }

        if (e.getSource() == buttonG2) {
            alg.load(g2);
            System.out.println("g2");
            frame.dispose();
        }

        if (e.getSource() == buttonG3) {
            alg.load(g3);
            System.out.println("g3");
            frame.dispose();
        }

        if (e.getSource() == buttonG4) {
            alg.load(notConnected);
            System.out.println("notConnected");
            frame.dispose();
        }

        if (e.getSource() == buttonG5) {
            JFileChooser fileChooser = new JFileChooser();
            int response = fileChooser.showSaveDialog(null);  // select file to save

            if (response == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                System.out.println(file);
            }
            alg.load(fileChooser.getSelectedFile().getAbsolutePath());
            frame.dispose();
        }

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        frame = new Frame(this.alg);
        frame.setVisible(true);
    }

    public void  loadFromJsom(String str){
        alg.load(str);
        frame = new Frame(this.alg);
        frame.setVisible(true);
    }
}
