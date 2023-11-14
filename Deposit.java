package bank.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Deposit extends JFrame implements ActionListener{
    String pin, accNo;
    JButton backButton, openDepositButton;

    Deposit(){
        new SqlQuery(SqlQuery.accNo);
        pin = SqlQuery.pin;
        setTitle("Deposits");

        setSize(650,580);
        setLocation(330,50);
        setVisible(true);
        setLayout(null);

        ImageIcon bankLogo = new ImageIcon(ClassLoader.getSystemResource("images/logo.jpg"));
        Image bankLogo2 = bankLogo.getImage().getScaledInstance(75,75, Image.SCALE_DEFAULT);
        ImageIcon bankLogo3 = new ImageIcon(bankLogo2);
        JLabel bankLogoLabel = new JLabel(bankLogo3);
        bankLogoLabel.setBounds(60, 5,100, 75);
        add(bankLogoLabel);

        JLabel heading = new JLabel("Deposit Details");
        heading.setFont(new Font("Aerial", Font.BOLD, 45));
        heading.setBounds(160, 20, 400, 50);
        add(heading);

        /*
        FD / RD
        Rs. 10000   15 Months
        From 20/10/23 to 20/2/25
        Amount at Present:
        Amount on Maturity:
        */

        backButton= new JButton("X");
        backButton.setFont(new Font("Rale way", Font.BOLD, 25));
        backButton.setBounds(550,15,45,45);
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(Color.RED);
        backButton.setBorder(null);
        backButton.addActionListener(this);
        add(backButton);

        openDepositButton= new JButton("New Deposit");
        openDepositButton.setFont(new Font("Rale way", Font.BOLD, 25));
        openDepositButton.setBounds(220,450,200,45);
        openDepositButton.setBackground(Color.GREEN);
        openDepositButton.setForeground(Color.BLACK);
        openDepositButton.addActionListener(this);
        add(openDepositButton);

        getContentPane().setBackground(Color.WHITE);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()== backButton){
            setVisible(false);
            new HomePage().setVisible(true);

        }else if(ae.getSource()== openDepositButton){
            setVisible(false);
            new OpenDeposit().setVisible(true);
        }
    }
}
