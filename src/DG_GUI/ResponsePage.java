package DG_GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResponsePage extends JFrame implements ActionListener {

    JFrame frame = new JFrame();
    JLabel answer = new JLabel("Hello!");
    JButton button = new JButton("close");

    String ans;
    int f;

    ResponsePage(String ans, int f) {

        this.ans = ans;
        this.f = f;

        answer.setBounds(170, 150, 250, 35);
        answer.setFont(new Font(null, Font.ITALIC, 25));
        answer.setText(ans);

        if (f == 1) {
            if (ans.equals("Connected")) {
                answer.setForeground(Color.green);
            }
            else if (ans.equals("Not Connected")) {
                answer.setForeground(Color.red);
            }
        }

        if (f == 2) {
            answer.setBounds(70, 150, 250, 35);
        }

        if (f == 3) {
            answer.setBounds(70, 150, 250, 35);
        }

        button.setBounds(150, 250, 100, 25);
        button.setFocusable(false);
        button.addActionListener(this);


        frame.add(answer);
        frame.add(button);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            frame.dispose();
        }
    }
}
