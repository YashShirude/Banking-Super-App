
package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Portfolio extends JFrame implements ActionListener{
    String pin, accountNo;
    int remOverdraft, overDraftUsed, remTransactions;
    String accType;
    int balance;
    JButton miniStatementButton,backButton,settleDraftButton;
    Portfolio(){
        setLayout(null);
        new SqlQuery(SqlQuery.accNo);

        CurrentAcc c = new CurrentAcc();
        accType = SqlQuery.accType;
        balance = SqlQuery.balance;
        pin = SqlQuery.pin;
        accountNo = SqlQuery.accNo;
        remTransactions = SqlQuery.remTransactions;
        remOverdraft = SqlQuery.remOverdraft;
        overDraftUsed = c.totalOverdraft() - remOverdraft;

//        finally {
//            try {
//                Conn c0 = new Conn();
//                String q0 = "SELECT accType, balance FROM financialDetails WHERE accNo = '" + accountNo + "'";
//                ResultSet output = c0.s.executeQuery(q0);
//
//                if (output.next()) {
//                    accType = output.getString(1);
//                    balance = output.getString(2);
//                }else{
//                    System.out.println("No results found.");
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

        ImageIcon bankLogo = new ImageIcon(ClassLoader.getSystemResource("images/logo.jpg"));
        Image bankLogo2 = bankLogo.getImage().getScaledInstance(75,75, Image.SCALE_DEFAULT);
        ImageIcon bankLogo3 = new ImageIcon(bankLogo2);
        JLabel bankLogoLabel = new JLabel(bankLogo3);
        bankLogoLabel.setBounds(125, 16,75, 75);
        add(bankLogoLabel);

        JLabel heading = new JLabel("My Portfolio");
        heading.setBounds(220,25,400,60);
        heading.setForeground(Color.black);
        heading.setFont(new Font("Aerial", Font.BOLD, 45));
        add(heading);

        JLabel h1 = new JLabel("Balance:        Rs. " + balance);
        h1.setBounds(30,200,600,30);
        h1.setForeground(Color.black);
        h1.setFont(new Font("System", Font.BOLD, 30));
        add(h1);

        JLabel h2 = new JLabel("Deposits:       Rs. ");
        h2.setBounds(30,300,600,30);
        h2.setForeground(Color.black);
        h2.setFont(new Font("System", Font.BOLD, 30));
        add(h2);

        JLabel h2a = new JLabel("( 1 Fixed Deposit & 3 Recurring Deposit)");
        h2a.setBounds(230,340,600,20);
        h2a.setForeground(Color.black);
        h2a.setFont(new Font("System", Font.ITALIC, 15));
        add(h2a);

        miniStatementButton = new JButton("Mini Statement");
        miniStatementButton.setFont(new Font("Rale way", Font.BOLD, 16));
        miniStatementButton.setBounds(230,470,175,50);
        miniStatementButton.setBackground(Color.GREEN);
        miniStatementButton.setForeground(Color.BLACK);

        if(accType.equals("Current")){
            h1.setBounds(30,170,600,30);
            h2.setBounds(30,260,600,30);
            h2a.setBounds(230,290,600,30);
            JLabel h3 = new JLabel("Overdraft Used: Rs. " + overDraftUsed);
            h3.setBounds(30,350,600,35);
            h3.setForeground(Color.RED);
            h3.setFont(new Font("System", Font.BOLD, 25));
            add(h3);

            JLabel h4 = new JLabel("(Overdraft Available: Rs. " + remOverdraft + " )");
            h4.setBounds(30,380,600,35);
            h4.setFont(new Font("System", Font.ITALIC, 15));
            add(h4);

            settleDraftButton= new JButton("Settle OverDraft");
            settleDraftButton.setFont(new Font("Rale way", Font.BOLD, 16));
            settleDraftButton.setBounds(100,470,200,50);
            settleDraftButton.setBackground(Color.GREEN);
            settleDraftButton.setForeground(Color.BLACK);
            settleDraftButton.addActionListener(this);
            add(settleDraftButton);

            miniStatementButton.setBounds(320,470,175,50);
        }

        miniStatementButton.addActionListener(this);
        add(miniStatementButton);

        backButton= new JButton("X");
        backButton.setFont(new Font("Rale way", Font.BOLD, 25));
        backButton.setBounds(550,15,45,45);
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(Color.RED);
        backButton.setBorder(null);
        backButton.addActionListener(this);
        add(backButton);

      setTitle("Balance");
      setSize(650,600);
      setLocation(330,45);
      setVisible(true);

      getContentPane().setBackground(Color.WHITE);
    }

    public void actionPerformed(ActionEvent ae){

        if(ae.getSource()== miniStatementButton){
            setVisible(false);
            new MiniStatement().setVisible(true);

        }else if(ae.getSource()== backButton){
            setVisible(false);
            new HomePage().setVisible(true);
        }
    }
}
