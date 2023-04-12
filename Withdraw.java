package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Withdraw {
	int accno;
	  double amount;
	  Withdraw(int accno,double amount)
	  {
		  this.accno=accno;
		  this.amount=amount;
	  }
	  public void doWithdraw()
	  {
		  System.out.println("----------------------------------------------");
		  try
		  {
		  Class.forName("oracle.jdbc.driver.OracleDriver");
		  Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Adi","adi");
		  PreparedStatement ps=con.prepareStatement("select balance from customers where accno=?");
		  ps.setInt(1, accno);
		  ResultSet rs=ps.executeQuery();
		  if(rs.next())
		  {
		  	if(rs.getDouble(1)>amount)
		  	{
		  		System.out.println(amount+"is Withdraw in Your Account");
		  		amount=rs.getDouble(1)-amount;
		  		PreparedStatement ps1=con.prepareStatement("update customers set balance=? where accno=?");
		  		ps1.setDouble(1, amount);
		  		ps1.setInt(2, accno);
		  		int i=ps1.executeUpdate();
		  		if(i==1)
		  		   System.out.println("Widrawal successfull");
		  		else
		  			System.out.println("Can not Withdraw ...");
		  	}
		  	else
		  	{
		  		System.out.println("Insufficient Balance<br>");
		  	}
		  	

		  }
		  else
		  {
		  	System.out.println("Please Check Account number And Balance");
		  	System.out.println("..........Try again........");
		  }
		  System.out.println("----------------------------------------------");
		  con.close();
		  }
		  catch(Exception ex)
		  {
		  	System.out.println(ex);
		  }
	  }
}
