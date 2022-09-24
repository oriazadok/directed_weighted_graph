package DG_GUI;

import api.DirectedWeightedGraphAlgorithms;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    DirectedWeightedGraphAlgorithms algo;

    public Frame(DirectedWeightedGraphAlgorithms alg) {
        this.algo = alg;

        this.setTitle("Zadok & Bar's Directed Weighted Graph");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1400, 800);
        this.getContentPane().setBackground(new Color(94, 87, 87));
        this.setLayout(new FlowLayout());

        this.setResizable(false);

        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 50));

        ///////////////////////////////////
        // head label
        JLabel head = new JLabel("Directed Weighted Graph");
        head.setFont(new Font("MV Boli", Font.PLAIN, 20));
        head.setBackground(new Color(94, 87, 87));
        head.setForeground(Color.blue);
        head.setOpaque(true);
        // End of head label
        ///////////////////////////////////




        // panel contain the graph
        Panel panel = new Panel(this.algo);

        // menu bar
        MenuBar mb = new MenuBar(this.algo, panel);



        this.add(head);
        this.setJMenuBar(mb.menuBar);

        this.add(panel);

        this.setVisible(true);
    }


}
