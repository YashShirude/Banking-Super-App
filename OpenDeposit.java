package bank.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class OpenDeposit extends JFrame implements ActionListener{
    String pin;
    JButton backButton, confirmButton, previewButton;
    JTextField principalField, tenureField;
    JLabel amt;
    JRadioButton fdField, rdField;
    JPasswordField pinField;

    OpenDeposit(){

        setTitle("New Deposit");

        new SqlQuery(SqlQuery.accNo);

        setSize(650,580);
        setLocation(330,50);
        setVisible(true);
        setLayout(null);

        ImageIcon bankLogo = new ImageIcon(ClassLoader.getSystemResource("images/logo.jpg"));
        Image bankLogo2 = bankLogo.getImage().getScaledInstance(75,75, Image.SCALE_DEFAULT);
        ImageIcon bankLogo3 = new ImageIcon(bankLogo2);
        JLabel bankLogoLabel = new JLabel(bankLogo3);
        bankLogoLabel.setBounds(90, 5,100, 75);
        add(bankLogoLabel);

        JLabel heading = new JLabel("New Deposit");
        heading.setFont(new Font("Aerial", Font.BOLD, 45));
        heading.setBounds(210, 20, 400, 50);
        add(heading);

        // Print Balance
        // Radio Button FD or RD
        // Principal Amount
        // Print Rate of Interest(blue)
        // Tenure in months (1 year or customizable)
        // Print total amount on maturity (Create a function in account)

        // Amount calculator on maturity
        // Amount Calculator present

        JLabel bal = new JLabel("Balance: Rs. " + SqlQuery.balance);
        bal.setFont(new Font("Aerial", Font.ITALIC, 20));
        bal.setBounds(150, 100, 400, 30);
        add(bal);

        fdField = new JRadioButton("FD");
        fdField.setFont(new Font("Rale way", Font.BOLD, 20));
        fdField.setBackground(Color.WHITE);
        fdField.setBounds(210,150,60,30);
        add(fdField);

        rdField = new JRadioButton("RD");
        rdField.setFont(new Font("Rale way", Font.BOLD, 20));
        rdField.setBackground(Color.WHITE);
        rdField.setBounds(310,150,130,30);
        add(rdField);

        ButtonGroup groupAcc = new ButtonGroup();
        groupAcc.add(fdField);
        groupAcc.add(rdField);

        JLabel principal = new JLabel("Principal: ");
        principal.setFont(new Font("Aerial", Font.BOLD, 30));
        principal.setBounds(100, 200, 400, 40);
        add(principal);

        principalField = new JTextField(15);
        principalField.setBounds(300,200,230,30);
        principalField.setFont(new Font("Arial", Font.BOLD, 14));
        add(principalField);

        JLabel roi = new JLabel("Interest Rate: " + AbstractAccount.rate + "%");
        roi.setFont(new Font("Aerial", Font.BOLD, 20));
        roi.setBounds(210, 235, 400, 50);
        roi.setForeground(Color.BLUE);
        add(roi);

        JLabel tenure = new JLabel("Tenure: ");
        tenure.setFont(new Font("Aerial", Font.BOLD, 30));
        tenure.setBounds(100, 300, 400, 40);
        add(tenure);

        tenureField = new JTextField(15);
        tenureField.setBounds(300,300,230,30);
        tenureField.setFont(new Font("Arial", Font.BOLD, 14));
        add(tenureField);

        JLabel mpin = new JLabel("Pin: ");
        mpin.setFont(new Font("Rale way", Font.BOLD, 30));
        mpin.setBounds(100, 400, 400, 30);
        add(mpin);

        pinField = new JPasswordField(15);
        pinField.setBounds(300,400,230,30);
        pinField.setFont(new Font("Arial", Font.BOLD, 14));
        add(pinField);

        previewButton= new JButton("Preview Amount");
        previewButton.setFont(new Font("Rale way", Font.BOLD, 15));
        previewButton.setBounds(210,350,170,20);
        previewButton.setBackground(Color.LIGHT_GRAY);
        previewButton.setForeground(Color.BLACK);
        previewButton.addActionListener(this);
        add(previewButton);

        backButton= new JButton("X");
        backButton.setFont(new Font("Rale way", Font.BOLD, 25));
        backButton.setBounds(550,15,45,45);
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(Color.RED);
        backButton.setBorder(null);
        backButton.addActionListener(this);
        add(backButton);

        confirmButton = new JButton("Confirm");
        confirmButton.setFont(new Font("Rale way", Font.BOLD, 20));
        confirmButton.setBounds(210,475,200,35);
        confirmButton.setBackground(Color.GREEN);
        confirmButton.setForeground(Color.BLACK);
        confirmButton.addActionListener(this);
        add(confirmButton);

        getContentPane().setBackground(Color.WHITE);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == backButton) {
            setVisible(false);
            new HomePage().setVisible(true);
        }

        int principal = Integer.parseInt(principalField.getText());
        int tenure = Integer.parseInt(tenureField.getText());
        float amount = 0, interest;
        String msg = "no msg";
        String deposit = "not selected";
        String dt = "2008-11-11";
        pin = pinField.getText();
        if(fdField.isSelected()){
            deposit = "FD";
            msg = "Invested into FD";
        }else if(rdField.isSelected()){
            deposit = "RD";
            msg = "Invested into RD";
        }

        if(deposit.equals("FD")){
            amount = AbstractAccount.fdCalculator(principal, tenure);
        }else if(deposit.equals("RD")){
            amount = AbstractAccount.rdCalculator(principal, tenure);
        }
        interest = amount - principal;

        if(ae.getSource() == previewButton){
            remove(previewButton);
            repaint();
            amt = new JLabel("Maturity Amount: Rs. " + amount);
            amt.setFont(new Font("Aerial", Font.BOLD, 20));
            amt.setBounds(150, 335, 400, 50);
            amt.setForeground(Color.BLUE);
            add(amt);
            repaint();
        }
        if(ae.getSource() == confirmButton){
            if(pin.equals(SqlQuery.pin)){
                SqlQuery query12 = new SqlQuery();
                query12.updateFinancialDetails(SqlQuery.accNo,String.valueOf(SqlQuery.balance - principal));
                query12.insertTransactionDetails(SqlQuery.accNo,msg,String.valueOf(principal),"Debited",String.valueOf(SqlQuery.balance - principal));

                try{
                    Conn c1 = new Conn();
                    String q6 = "insert into deposit values('"+SqlQuery.accNo+"','"+SqlQuery.accType+"','"+principal+"','"+AbstractAccount.rate+"','"+tenure+"','"+interest+"','"+dt+"')";
                    c1.s.executeUpdate(q6);
                }catch(Exception e) {
                    e.printStackTrace();
                }
                new SqlQuery(SqlQuery.accNo);
                setVisible(false);
                new DepositSuccess().setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(null, "Incorrect Pin");
                pinField.setText("");
            }
        }
    }
}
