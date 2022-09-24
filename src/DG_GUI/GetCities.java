package DG_GUI;

import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

public class GetCities implements ActionListener {

    DirectedWeightedGraphAlgorithms alg;
    String op;

    JFrame frame = new JFrame();

    JLabel cities = new JLabel("Insert Node: ");
    JTextField citiesID = new JTextField();

    JButton sendButton = new JButton("Submit");
    JButton addButton = new JButton("Add");

    JLabel messageLabel = new JLabel();

    List<NodeData> list = new LinkedList<>();

    GetCities(DirectedWeightedGraphAlgorithms alg, String op) {
        this.alg = alg;
        this.op = op;;

        cities.setBounds(50, 100, 150, 25);
        citiesID.setBounds(200, 100, 150, 25);

        messageLabel.setBounds(125, 250, 250, 35);
        messageLabel.setFont(new Font(null, Font.ITALIC, 25));

        sendButton.setBounds(125, 200, 100, 25);
        sendButton.setFocusable(false);
        sendButton.addActionListener(this);

        addButton.setBounds(225, 200, 100, 25);
        addButton.setFocusable(false);
        addButton.addActionListener(this);


        frame.add(cities);
        frame.add(citiesID);

        frame.add(messageLabel);

        frame.add(sendButton);
        frame.add(addButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String str = "";
        if (e.getSource() == addButton) {

            int id = Integer.parseInt(citiesID.getText());
            if (alg.getGraph().getNode(id) == null) {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("Wrong input");
            }

            else {
                if (op.equals("tsp")){
                    list.add(alg.getGraph().getNode(id));
                    str = listToString(list);
                    messageLabel.setForeground(Color.blue);
                    messageLabel.setText(str);
                    citiesID.setText("");
                }
            }
        }

        if (e.getSource() == sendButton) {
            String s = "";
            List<NodeData> tsp = alg.tsp(list);
            s = listToString(tsp);
            frame.dispose();
            ResponsePage res = new ResponsePage(s, 4);
        }



    }

    private String listToString(List<NodeData> list) {
        String str = "";
        for (NodeData n : list) {
            str += n.getKey() + ",";
        }
        str = str.substring(0, str.length()-1);
        return str;
    }
}
