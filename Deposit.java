package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Deposit {
	int accno;
	   double amount;
	   Deposit(int accno,double amount)
	   {
		   this.accno=accno;
		   this.amount=amount;
	   }
	   public void doDeposit()
	   {
		   try
		   {
		   Class.forName("oracle.jdbc.driver.OracleDriver");
		   Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Adi","adi");
		   PreparedStatement ps=con.prepareStatement("select balance from customers where accno=?");
		   ps.setInt(1, accno);
		   ResultSet rs=ps.executeQuery();
		   if(rs.next())
		   {
		   	amount=amount+rs.getDouble(1);
		   	PreparedStatement ps1=con.prepareStatement("update customers set balance=? where accno=?");
		   	ps1.setDouble(1, amount);
		   	ps1.setInt(2, accno);
		   	int i=ps1.executeUpdate();
		   	if(i==1)
		      System.out.println("Deposite successful......");
		   	else
		   		System.out.println("Can not Deposited..."); 
		   }
		   else
		   {
		   	System.out.println("check your account number");
		   	System.out.println(".........Try again............");
		   }
		   con.close();
		   }
		   catch(Exception ex)
		   {
		    	System.out.print(ex);
		   }
	   }
}
