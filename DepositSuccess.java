package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DepositSuccess extends JFrame implements ActionListener {
    String pin, accNo;
    JButton backButton;
    DepositSuccess(){
        accNo = SqlQuery.accNo;

        SqlQuery query9 = new SqlQuery();
        pin = query9.pin;

        setTitle("Transfer Successful");
        setSize(650,600);
        setLocation(325,55);
        setVisible(true);
        setLayout(null);

        JLabel heading = new JLabel("Deposit Opened");
        heading.setFont(new Font("Aerial", Font.BOLD, 48));
        heading.setBounds(130, 230, 600, 40);
        add(heading);

        JLabel heading2 = new JLabel("Successfully");
        heading2.setFont(new Font("Aerial", Font.BOLD, 48));
        heading2.setBounds(155, 280, 600, 60);
        add(heading2);

        backButton= new JButton("X");
        backButton.setFont(new Font("Rale way", Font.BOLD, 25));
        backButton.setBounds(550,15,45,45);
        backButton.setBackground(Color.ORANGE);
        backButton.setForeground(Color.RED);
        backButton.setBorder(null);
        backButton.addActionListener(this);
        add(backButton);

        getContentPane().setBackground(Color.ORANGE);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == backButton){
            new HomePage().setVisible(true);
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new DepositSuccess();
    }
}
