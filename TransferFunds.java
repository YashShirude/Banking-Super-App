package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TransferFunds extends JFrame implements ActionListener {
    String pin, senderAccNo;
    JTextField accField, amtField;
    JPasswordField pinField;
    JButton backButton,confirmButton;
    boolean status;
    TransferFunds(){
        new SqlQuery(SqlQuery.accNo);
        senderAccNo = SqlQuery.accNo;
        pin = SqlQuery.pin;

        status = true;
        setSize(650,600);
        setLocation(330,45);
        setVisible(true);
        setLayout(null);
        setTitle("Transfer Funds");

        ImageIcon bankLogo = new ImageIcon(ClassLoader.getSystemResource("images/logo.jpg"));
        Image bankLogo2 = bankLogo.getImage().getScaledInstance(75,75, Image.SCALE_DEFAULT);
        ImageIcon bankLogo3 = new ImageIcon(bankLogo2);
        JLabel bankLogoLabel = new JLabel(bankLogo3);
        bankLogoLabel.setBounds(125, 16,75, 75);
        add(bankLogoLabel);

        JLabel heading = new JLabel("Send Money");
        heading.setBounds(220,25,400,60);
        heading.setForeground(Color.black);
        heading.setFont(new Font("Aerial", Font.BOLD, 45));
        add(heading);

        JLabel receiver = new JLabel("Account No. ");
        receiver.setBounds(80,150,200,30);
        receiver.setForeground(Color.black);
        receiver.setFont(new Font("Aerial", Font.BOLD, 30));
        add(receiver);

        accField = new JTextField(15);
        accField.setBounds(300,150,230,30);
        accField.setFont(new Font("Arial", Font.BOLD, 14));
        add(accField);

        JLabel amount = new JLabel("Amount: ");
        amount.setBounds(80,250,400,30);
        amount.setForeground(Color.black);
        amount.setFont(new Font("Aerial", Font.BOLD, 30));
        add(amount);

        amtField = new JTextField(15);
        amtField.setBounds(300,250,230,30);
        amtField.setFont(new Font("Arial", Font.BOLD, 14));
        add(amtField);

        JLabel confirm = new JLabel("Pin: ");
        confirm.setBounds(80,350,400,30);
        confirm.setForeground(Color.black);
        confirm.setFont(new Font("Aerial", Font.BOLD, 30));
        add(confirm);

        pinField = new JPasswordField(15);
        pinField.setBounds(300,350,230,30);
        pinField.setFont(new Font("Arial", Font.BOLD, 14));
        add(pinField);

        confirmButton= new JButton("Confirm");
        confirmButton.setFont(new Font("Rale way", Font.BOLD, 25));
        confirmButton.setBounds(220,475,200,45);
        confirmButton.setBackground(Color.GREEN);
        confirmButton.setForeground(Color.WHITE);
        confirmButton.addActionListener(this);
        add(confirmButton);

        backButton= new JButton("X");
        backButton.setFont(new Font("Rale way", Font.BOLD, 25));
        backButton.setBounds(550,15,45,45);
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(Color.RED);
        backButton.setBorder(null);
        backButton.addActionListener(this);
        add(backButton);

        getContentPane().setBackground(Color.WHITE);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == backButton){
            setVisible(false);
            new HomePage().setVisible(true);
        }

        if(ae.getSource() == confirmButton && (accField.getText().isEmpty() || amtField.getText().isEmpty() || pin.isEmpty())){
            JOptionPane.showMessageDialog(null, "Fill all the required fields");
        }
        else{
            String senderBalance ,receiverBalance ,message1,message2,tranType1,tranType2;
            String receiverAccNo = accField.getText();
            String amount = amtField.getText();
            int amountInt = Integer.parseInt(amount);
            String enteredPin = pinField.getText();

            SqlQuery query2 = new SqlQuery(receiverAccNo);
            int receiverBalanceInt = SqlQuery.balance;
            String receiverName = SqlQuery.name;

            SqlQuery query1 = new SqlQuery(senderAccNo);
            int senderBalanceInt = SqlQuery.balance;
            String senderAccType = SqlQuery.accType;
            String senderName = SqlQuery.name;
            int remTransactions = SqlQuery.remTransactions;
            int remOverdraft = SqlQuery.remOverdraft;

            if(accField.getText().isEmpty() || amtField.getText().isEmpty() || pin.isEmpty()){
                JOptionPane.showMessageDialog(null, "Fill all the required fields");
                status = false;
            }else if(!pin.equals(enteredPin)){
                pinField.setText("");
                JOptionPane.showMessageDialog(null, "Wrong Pin");
                status = false;
            }else if(senderAccType.equals("Saving") && amountInt > 100000){
                amtField.setText("");
                pinField.setText("");
                JOptionPane.showMessageDialog(null, "Amount can't exceed Rs.1,00,000");
                status = false;
            } else if(senderBalanceInt + remOverdraft < amountInt){
                amtField.setText("");
                pinField.setText("");
                JOptionPane.showMessageDialog(null, "Insufficient Balance");
                status = false;
            } else if (remTransactions == 0) {
                pinField.setText("");
                amtField.setText("");
                JOptionPane.showMessageDialog(null, "Transaction Limit Reached");
                status = false;
            }

            if(status){
                JOptionPane.showMessageDialog(null, "Pay Rs." + amount + " to " + receiverName);
                message1 = "Transferred to " + receiverName;
                tranType1 = "Debited";
                if(senderBalanceInt < amountInt){
                    remOverdraft -= amountInt;
                }else{
                    senderBalanceInt -= amountInt;
                }
                senderBalance = String.valueOf(senderBalanceInt);
                receiverBalanceInt += amountInt;
                receiverBalance = String.valueOf(receiverBalanceInt);
                message2 = "Received from " + senderName;
                tranType2 = "Credited";

                SqlQuery query3 = new SqlQuery();
                query3.insertTransactionDetails(senderAccNo,message1,amount,tranType1,senderBalance);
                query3.insertTransactionDetails(receiverAccNo,message2,amount,tranType2,receiverBalance);

                int remTransaction1 = remTransactions; // And condition for transaction table
                remTransactions--;
                SqlQuery query4 = new SqlQuery();
                query4.updateTransaction(senderAccNo,remTransactions,remOverdraft);
                query4.updateFinancialDetails(senderAccNo,senderBalance);
                query4.updateFinancialDetails(receiverAccNo,receiverBalance);
                setVisible(false);
                new TransactionSuccess().setVisible(true);
            }
            status = true;
        }
    }
}
