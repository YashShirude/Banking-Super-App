package bank.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HomePage extends JFrame implements ActionListener{
    JButton balance, deposit,transferFunds,logout;
    HomePage(){
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/b2.png"));
        Image i2 = i1.getImage().getScaledInstance(650, 580, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,650,580);

        setTitle("Home Page");

        setSize(650,580);
        setLocation(330,50);
        setVisible(true);
        setLayout(null);

        JLabel welcome = new JLabel("Welcome");
        welcome.setFont(new Font("Aerial", Font.BOLD, 48));
        welcome.setBounds(200, 40, 400, 40);
        welcome.setForeground(Color.WHITE);
        add(welcome);

        balance = new JButton("PORTFOLIO");
        balance.setBackground(Color.WHITE);
        balance.setForeground(Color.BLACK);
        balance.setFont(new Font("Arial", Font.BOLD, 14));
        balance.setBounds(220,150,175,30);
        add(balance);

        deposit = new JButton("DEPOSITS");
        deposit.setBackground(Color.WHITE);
        deposit.setForeground(Color.BLACK);
        deposit.setFont(new Font("Arial", Font.BOLD, 14));
        deposit.setBounds(220,250,175,30);
        add(deposit);

        transferFunds = new JButton("TRANSFER FUNDS");
        transferFunds.setBackground(Color.WHITE);
        transferFunds.setForeground(Color.BLACK);
        transferFunds.setFont(new Font("Arial", Font.BOLD, 14));
        transferFunds.setBounds(220,350,175,30);
        add(transferFunds);

        logout = new JButton("( ! )");
        logout.setBackground(Color.RED);
        logout.setForeground(Color.WHITE);
        logout.setFont(new Font("Arial", Font.BOLD, 14));
        logout.setBounds(550,450,57,45);
        add(logout);

        balance.addActionListener(this);
        deposit.addActionListener(this);
        transferFunds.addActionListener(this);
        logout.addActionListener(this);

        getContentPane().setBackground(Color.WHITE);
        add(image);

    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == balance){
            setVisible(false);
            new Portfolio().setVisible(true);
        }else if(ae.getSource() == deposit){
            setVisible(false);
            new Deposit().setVisible(true);
        }else if(ae.getSource() == transferFunds){
            setVisible(false);
            new TransferFunds().setVisible(true);
        }else if(ae.getSource() == logout){
            setVisible(false);
            new Login().setVisible(true);
        }
    }
}
