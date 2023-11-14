package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    int totalUsers;
    JLabel cardNo,pin;
    JTextField cardField;
    JPasswordField pinField;
    JButton signInButton, signUpButton;
    Login(){
        setTitle("CFDH Banking Super App");

        setSize(600,480);
        setVisible(true);
        setLocation(350,110);

        ImageIcon bankLogo = new ImageIcon(ClassLoader.getSystemResource("images/logo.jpg"));

        //Adjusting the size of bankLogo
        Image bankLogo2 = bankLogo.getImage().getScaledInstance(150,150, Image.SCALE_DEFAULT);
        ImageIcon bankLogo3 = new ImageIcon(bankLogo2);
        JLabel bankLogoLabel = new JLabel(bankLogo3);
        setLayout(null);

        bankLogoLabel.setBounds(10, 10,150, 150);
        add(bankLogoLabel);

        JLabel heading = new JLabel("CFDH Bank");
        heading.setFont(new Font("Aerial", Font.BOLD, 48));
        heading.setBounds(200, 70, 400, 40);
        add(heading);

        JLabel subHeading = new JLabel("Banking & Lifestyle Super App");
        subHeading.setFont(new Font("Aerial", Font.ITALIC, 18));
        subHeading.setBounds(250, 110, 400, 40);
        add(subHeading);

        try {
            Conn c0 = new Conn();
            String q0 = "SELECT MAX(accNo) FROM financialDetails";
            ResultSet output = c0.s.executeQuery(q0);
            if (output.next()){
                totalUsers = output.getInt(1) - 202310100;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel users = new JLabel(totalUsers + " Happy Customers");
        users.setFont(new Font("Aerial", Font.PLAIN, 18));
        users.setBounds(200, 150, 400, 40);
        users.setForeground(Color.BLUE);
        add(users);

        cardNo = new JLabel("Acc. No:");
        cardNo.setFont(new Font("Rale way", Font.BOLD, 28));
        cardNo.setBounds(100,200,300,30);
        add(cardNo);

        cardField = new JTextField(15);
        cardField.setBounds(250,200,230,30);
        cardField.setFont(new Font("Arial", Font.BOLD, 14));
        add(cardField);

        pin = new JLabel("PIN:");
        pin.setFont(new Font("Rale way", Font.BOLD, 28));
        pin.setBounds(125,250,375,30);
        add(pin);

        pinField = new JPasswordField(15);
        pinField.setFont(new Font("Arial", Font.BOLD, 14));
        pinField.setBounds(250,250,230,30);
        add(pinField);

        signInButton = new JButton("SIGN IN");
        signInButton.setBackground(Color.GREEN);
        signInButton.setForeground(Color.black);
        signInButton.setFont(new Font("Arial", Font.BOLD, 14));
        signInButton.setBounds(225,310,100,30);
        add(signInButton);

        JLabel signUp = new JLabel("Don't have an Account: ");
        signUp.setFont(new Font("Aerial", Font.ITALIC, 16));
        signUp.setForeground(Color.blue);
        signUp.setBounds(100, 370, 200, 40);
        add(signUp);

        signUpButton = new JButton("SIGN UP");
        signUpButton.setBackground(Color.BLACK);
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setFont(new Font("Arial", Font.BOLD, 14));
        signUpButton.setBounds(275,375,100,30);
        add(signUpButton);

        signInButton.addActionListener(this);
        signUpButton.addActionListener(this);

        getContentPane().setBackground(Color.WHITE);
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == signInButton){
            String accNo  = cardField.getText();
            char[] pinInChar  = pinField.getPassword();
            String pin = String.valueOf(pinInChar);
            String accType;
            int balanceInt;

            new SqlQuery(accNo);
            accType = SqlQuery.accType;
            balanceInt = SqlQuery.balance;


            if(pin.equals(SqlQuery.pin)){
                setVisible(false);
                new HomePage().setVisible(true);
                if(accType.equals("Saving") && balanceInt < AbstractAccount.minBalance){
                    JOptionPane.showMessageDialog(null, "Low Balance");
                }
            }else{
                JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN");
            }
        }else if(ae.getSource()== signUpButton){
            setVisible(false);
            new PersonalDetails().setVisible(true);
        }
    }
}
