package bank.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

import static bank.management.system.PersonalDetails.applicationNo;

public class FinancialDetails extends JFrame implements ActionListener{
    static int accountNo = 202310100;
    JLabel aadhar, pan, accType, initialBalance, pin, rePin;
    JTextField aadharField, panField, initialField;
    JPasswordField pinField,rePinField;
    JRadioButton savingField,currentField;
    JButton createAcc;

    FinancialDetails(){
        try {
            Conn c0 = new Conn();
            String q0 = "SELECT MAX(accNo) FROM financialDetails";
            ResultSet resultSet = c0.s.executeQuery(q0);

            if (resultSet.next() && resultSet.getInt(1) > 202310100) {
                FinancialDetails.accountNo = resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("NEW ACCOUNT APPLICATION FORM");
        setSize(650,600);
        setLocation(330,45);
        setVisible(true);

        FinancialDetails.accountNo++;

        ImageIcon bankLogo = new ImageIcon(ClassLoader.getSystemResource("images/logo.jpg"));
        Image bankLogo2 = bankLogo.getImage().getScaledInstance(75,75, Image.SCALE_DEFAULT);
        ImageIcon bankLogo3 = new ImageIcon(bankLogo2);
        JLabel bankLogoLabel = new JLabel(bankLogo3);
        setLayout(null);
        bankLogoLabel.setBounds(125, 5,100, 75);
        add(bankLogoLabel);

        JLabel heading = new JLabel("Create Account");
        heading.setFont(new Font("Aerial", Font.BOLD, 38));
        heading.setBounds(225, 25, 400, 40);
        add(heading);

        JLabel subHeading = new JLabel("Page 2: Financial Details");
        subHeading.setFont(new Font("Aerial", Font.BOLD, 18));
        subHeading.setBounds(200, 80, 400, 40);
        add(subHeading);

        aadhar = new JLabel("Aadhar: ");
        aadhar.setFont(new Font("Rale way", Font.BOLD, 23));
        aadhar.setBounds(100, 165, 400, 30);
        add(aadhar);

        pan = new JLabel("Pan No: ");
        pan.setFont(new Font("Rale way", Font.BOLD, 23));
        pan.setBounds(100, 215, 400, 30);
        add(pan);

        accType = new JLabel("Account: ");
        accType.setFont(new Font("Rale way", Font.BOLD, 23));
        accType.setBounds(100, 265, 400, 30);
        add(accType);

        initialBalance = new JLabel("Balance: ");
        initialBalance.setFont(new Font("Rale way", Font.BOLD, 23));
        initialBalance.setBounds(100, 315, 400, 30);
        add(initialBalance);

        pin = new JLabel("Pin: ");
        pin.setFont(new Font("Rale way", Font.BOLD, 23));
        pin.setBounds(100, 365, 400, 30);
        add(pin);

        rePin = new JLabel("Re-Enter Pin: ");
        rePin.setFont(new Font("Rale way", Font.BOLD, 23));
        rePin.setBounds(100, 415, 400, 30);
        add(rePin);

        aadharField = new JTextField(15);
        aadharField.setBounds(300,165,230,30);
        aadharField.setFont(new Font("Arial", Font.BOLD, 14));
        add(aadharField);

        panField = new JTextField(15);
        panField.setBounds(300,215,230,30);
        panField.setFont(new Font("Arial", Font.BOLD, 14));
        add(panField);

        savingField = new JRadioButton("Saving");
        savingField.setFont(new Font("Rale way", Font.BOLD, 14));
        savingField.setBackground(Color.WHITE);
        savingField.setBounds(310,265,120,30);
        add(savingField);

        currentField = new JRadioButton("Current");
        currentField.setFont(new Font("Rale way", Font.BOLD, 14));
        currentField.setBackground(Color.WHITE);
        currentField.setBounds(427,265,130,30);
        add(currentField);

        ButtonGroup groupAcc = new ButtonGroup();
        groupAcc.add(savingField);
        groupAcc.add(currentField);

        initialField = new JTextField(15);
        initialField.setBounds(300,315,230,30);
        initialField.setFont(new Font("Arial", Font.BOLD, 14));
        add(initialField);

        pinField = new JPasswordField(15);
        pinField.setBounds(300,365,230,30);
        pinField.setFont(new Font("Arial", Font.BOLD, 14));
        add(pinField);

        rePinField = new JPasswordField(15);
        rePinField.setBounds(300,415,230,30);
        rePinField.setFont(new Font("Arial", Font.BOLD, 14));
        add(rePinField);

        createAcc = new JButton("Create Account");
        createAcc.setFont(new Font("Rale way", Font.BOLD, 16));
        createAcc.setBounds(230,500,175,30);
        createAcc.setBackground(Color.GREEN);
        createAcc.setForeground(Color.BLACK);
        add(createAcc);

        createAcc.addActionListener(this);

        getContentPane().setBackground(Color.WHITE);
    }

    @Override
    public void actionPerformed(ActionEvent ae){

        String accNo = String. valueOf(accountNo);
        String aadhar = aadharField.getText();
        String pan = panField.getText();
        String accType = null;
        if(savingField.isSelected()){
            accType = "Saving";
        }else if(currentField.isSelected()){
            accType = "Current";
        }

        String balance = initialField.getText();
        String pin = pinField.getText();
        String rePin = rePinField.getText();

        try{
            if(!pin.equals(rePin)){
                pinField.setText("");
                rePinField.setText("");
                JOptionPane.showMessageDialog(null, "Pin doesn't match");
                pin = null;
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        try{

            if(aadharField.getText().isEmpty() || panField.getText().isEmpty() || initialField.getText().isEmpty() || accType == null || pin == null){
                JOptionPane.showMessageDialog(null, "Fill all the required fields");
            }else{
                Conn c1 = new Conn();
                String q1 = "insert into financialDetails values('"+accNo+"','"+aadhar+"','"+pan+"','"+accType+"','"+balance+"','"+pin+"','"+applicationNo+"')";
                c1.s.executeUpdate(q1);

                Conn c2 = new Conn();
                SavingAcc s = new SavingAcc();
                CurrentAcc c = new CurrentAcc();
                if(accType.equals("Saving")){
                    String q2 = "insert into transaction values('"+accNo+"','"+accType+"','"+s.totalTransactions()+"','"+s.totalOverdraft()+"')";
                    c2.s.executeUpdate(q2);
                }else{
                    String q2 = "insert into transaction values('"+accNo+"','"+accType+"','"+c.totalTransactions()+"','"+c.totalOverdraft()+"')";
                    c2.s.executeUpdate(q2);
                }
                new SqlQuery(accNo);
                new AccountCreated().setVisible(true);
                setVisible(false);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
