package bank.management.system;

import java.sql.*;
public class SqlQuery {

    static String accNo;
    public static String appnNo, stringBalance, accType, name, pin;
    public static int remTransactions, remOverdraft, balance;

    SqlQuery(){

    }
    SqlQuery(String accNo){
        SqlQuery.accNo = accNo;
        try {
            Conn c0 = new Conn();
            String q0 = "SELECT appnNo,balance,accType,pin FROM financialDetails WHERE accNo = '" + accNo + "'";
            ResultSet output = c0.s.executeQuery(q0);
            if (output.next()) {
                appnNo = output.getString(1);
                stringBalance = output.getString(2);
                balance = Integer.parseInt(stringBalance);
                accType = output.getString(3);
                pin = output.getString(4);
            }else{
                System.out.println("No results found.");
            }
            output.close();

            String q1 = "SELECT name FROM personalDetails WHERE appnNo = '" + appnNo + "'";
            ResultSet output1 = c0.s.executeQuery(q1);
            if (output1.next()) {
                name = output1.getString(1);
            }else{
                System.out.println("No results found.");
            }
            output1.close();

            String q2 = "SELECT remainingtrans,remainingOverdraft FROM transaction WHERE accNo = '" + accNo + "'";
            ResultSet output2 = c0.s.executeQuery(q2);
            if (output2.next()) {
                remTransactions = output2.getInt(1);
                remOverdraft = output2.getInt(2);
            }else{
                System.out.println("No results found.");
            }
            output2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void insertTransactionDetails(String acc,String msg,String amt,String tranType,String balance){
        try{
            Conn c1 = new Conn();
            String q3 = "insert into transactionDetails values('"+acc+"','"+msg+"','"+amt+"','"+tranType+"','"+balance+"')";
            c1.s.executeUpdate(q3);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void updateTransaction(String acc, int remTrans,int remOverdraft){
        try{
            Conn c2 = new Conn();
            String q4 = "UPDATE transaction SET remainingtrans = '"+remTrans+"', remainingOverdraft = '"+remOverdraft+"' WHERE accNo = '"+acc+"'";
            c2.s.executeUpdate(q4);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void updateFinancialDetails(String acc,String balance){
        try{
            Conn c3 = new Conn();
            String q5 = "UPDATE financialDetails SET balance = '"+balance+"' WHERE accNo = '"+acc+"'";
            c3.s.executeUpdate(q5);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
