package bank.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.sql.*;
import com.toedter.calendar.JDateChooser;

public class PersonalDetails extends JFrame implements ActionListener{

    public static int applicationNo = 1000;
    JLabel name, dob, gender, email, mobile, state;
    JTextField nameField, emailField, mobileField, stateField;
    JRadioButton maleField,femaleField;
    JButton nextPage;
    JDateChooser dobField;

    PersonalDetails(){
        try {
            Conn c0 = new Conn();
            String q0 = "SELECT MAX(appnNo) FROM personalDetails";
            ResultSet resultSet = c0.s.executeQuery(q0);

            if (resultSet.next() && resultSet.getInt(1) > 1000) {
                PersonalDetails.applicationNo = resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        setTitle("NEW ACCOUNT APPLICATION FORM");
        setSize(650,600);
        setLocation(330,45);
        setVisible(true);

        PersonalDetails.applicationNo++;

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

        JLabel subHeading = new JLabel("Page 1: Personal Details");
        subHeading.setFont(new Font("Aerial", Font.BOLD, 18));
        subHeading.setBounds(200, 80, 400, 40);
        add(subHeading);

        name = new JLabel("Name: ");
        name.setFont(new Font("Rale way", Font.BOLD, 23));
        name.setBounds(100, 165, 400, 30);
        add(name);

        dob = new JLabel("Date of Birth: ");
        dob.setFont(new Font("Rale way", Font.BOLD, 23));
        dob.setBounds(100, 215, 400, 30);
        add(dob);

        gender = new JLabel("Gender: ");
        gender.setFont(new Font("Rale way", Font.BOLD, 23));
        gender.setBounds(100, 265, 400, 30);
        add(gender);

        email = new JLabel("Email Id: ");
        email.setFont(new Font("Rale way", Font.BOLD, 23));
        email.setBounds(100, 315, 400, 30);
        add(email);

        mobile = new JLabel("Mobile No: ");
        mobile.setFont(new Font("Rale way", Font.BOLD, 23));
        mobile.setBounds(100, 365, 400, 30);
        add(mobile);

        state = new JLabel("State: ");
        state.setFont(new Font("Rale way", Font.BOLD, 23));
        state.setBounds(100, 415, 400, 30);
        add(state);

        nameField = new JTextField(15);
        nameField.setBounds(300,165,230,30);
        nameField.setFont(new Font("Arial", Font.BOLD, 14));
        add(nameField);

        dobField = new JDateChooser();
        dobField.setBounds(300, 215, 230, 30);
        dobField.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        dobField.setForeground(new Color(105, 105, 105));
        add(dobField);

        maleField = new JRadioButton("Male");
        maleField.setFont(new Font("Rale way", Font.BOLD, 14));
        maleField.setBackground(Color.WHITE);
        maleField.setBounds(325,265,60,30);
        add(maleField);

        femaleField = new JRadioButton("Female");
        femaleField.setFont(new Font("Rale way", Font.BOLD, 14));
        femaleField.setBackground(Color.WHITE);
        femaleField.setBounds(425,265,90,30);
        add(femaleField);

        ButtonGroup groupGender = new ButtonGroup();
        groupGender.add(maleField);
        groupGender.add(femaleField);

        emailField = new JTextField(15);
        emailField.setBounds(300,315,230,30);
        emailField.setFont(new Font("Arial", Font.BOLD, 14));
        add(emailField);

        mobileField = new JTextField(15);
        mobileField.setBounds(300,365,230,30);
        mobileField.setFont(new Font("Arial", Font.BOLD, 14));
        add(mobileField);

        stateField = new JTextField(15);
        stateField.setBounds(300,415,230,30);
        stateField.setFont(new Font("Arial", Font.BOLD, 14));
        add(stateField);

        nextPage = new JButton("Next Page");
        nextPage.setFont(new Font("Rale way", Font.BOLD, 16));
        nextPage.setBounds(230,500,150,30);
        nextPage.setBackground(Color.GREEN);
        nextPage.setForeground(Color.BLACK);
        add(nextPage);

        nextPage.addActionListener(this);

        getContentPane().setBackground(Color.WHITE);
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        String name = nameField.getText();
        String dob = ((JTextField) dobField.getDateEditor().getUiComponent()).getText();
        String gender = null;
        if(maleField.isSelected()){
            gender = "Male";
        }else if(femaleField.isSelected()){
            gender = "Female";
        }
        String mobile = mobileField.getText();
        String email = emailField.getText();
        String state = stateField.getText();

        try{
            if(nameField.getText().isEmpty() || gender == null || emailField.getText().isEmpty() || mobileField.getText().isEmpty() || stateField.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Fill all the required fields");
            }else{
                Conn c1 = new Conn();
                String q1 = "insert into personalDetails values('"+applicationNo+"','"+name+"','"+dob+"','"+gender+"','"+email+"','"+mobile+"','"+state+"')";
                c1.s.executeUpdate(q1);

                new FinancialDetails().setVisible(true);
                setVisible(false);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
