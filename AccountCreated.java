package bank.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AccountCreated extends JFrame implements ActionListener{
    String name, accountNo;
    int balance;
    JButton backToLogin;
    AccountCreated(){
        name = SqlQuery.name;
        accountNo = SqlQuery.accNo;
        balance = SqlQuery.balance;

        setTitle("Account Created");
        setSize(600,500);
        setLocation(350,95);
        setVisible(true);
        setLayout(null);

        JLabel heading = new JLabel("Account Created!");
        heading.setFont(new Font("Aerial", Font.BOLD, 48));
        heading.setBounds(100, 50, 400, 40);
        add(heading);

        JLabel holderName = new JLabel("Account Holder:    " + name);
        holderName.setFont(new Font("Aerial", Font.BOLD, 25));
        holderName.setBounds(75, 190, 500, 30);
        add(holderName);

        JLabel accNo = new JLabel("Account No:          " + accountNo);
        accNo.setFont(new Font("Aerial", Font.BOLD, 25));
        accNo.setBounds(75, 240, 400, 30);
        add(accNo);

        JLabel initialBalance = new JLabel("Balance:                Rs." + balance);
        initialBalance.setFont(new Font("Aerial", Font.BOLD, 25));
        initialBalance.setBounds(75, 290, 400, 30);
        add(initialBalance);

        backToLogin = new JButton("Back to Login");
        backToLogin.setBackground(Color.GREEN);
        backToLogin.setForeground(Color.BLACK);
        backToLogin.setFont(new Font("Rale way", Font.BOLD, 16));
        backToLogin.setBounds(190,400,150,30);
        add(backToLogin);

        backToLogin.addActionListener(this);

        getContentPane().setBackground(Color.ORANGE);

    }

    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()== backToLogin){
            new Login().setVisible(true);
            setVisible(false);
        }
    }
}
