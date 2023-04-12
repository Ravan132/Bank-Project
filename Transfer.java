package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Transfer {

	 int accno;
	  double amount;
	  int taccno;
	  String passcode;
	  Transfer(int accno,double  amount,int taccno,String passcode)
	  {
		  this.accno=accno;
		  this.amount=amount;
		  this.taccno=taccno;
		  this.passcode=passcode;
	  }
	  public void doTrasfer()
	  {
		  System.out.println("----------------------------------------------");
		  try
		  {
		  	Class.forName("oracle.jdbc.driver.OracleDriver");
		  	Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Adi","adi");
		  	PreparedStatement ps=con.prepareStatement("select balance from customers where accno=? and passcode=?");
		  	ps.setLong(1, accno);
		  	ps.setString(2, passcode);
		  	ResultSet rs=ps.executeQuery();
		  	
		  	if(rs.next())
		  	{
		  		if(rs.getDouble(1)>amount)
		  		{
		  		System.out.println(amount+" is Transfer from Account number "+accno+" to "+taccno+"<br>");
		  		double amount1=rs.getDouble(1)- amount;
		  		PreparedStatement ps3=con.prepareStatement("update customers set balance=? where accno=?");
		  		ps3.setDouble(1, amount1);
		  		ps3.setLong(2, accno);
		  		int i=ps3.executeUpdate();
		  		System.out.println(" current balance: "+amount1);
		  		PreparedStatement ps1=con.prepareStatement("select balance from customers where accno=?");
		  		ps1.setLong(1, taccno);
		  		ResultSet rs1=ps1.executeQuery();
		  		if(rs1.next())
		  		{
		  			amount=amount+rs1.getDouble(1);
		  			PreparedStatement ps2=con.prepareStatement("update customers set balance=? where accno=?");
		  			ps2.setDouble(1, amount);
		  			ps2.setLong(2, taccno);
		  			int j=ps2.executeUpdate();
		  			if(j==1)
		  			     System.out.println("Transfer successfully......");
		  			else
		  				System.out.println("Some problem Cannot Transfer...");
		  			
		  		}
		  		}
		  		else
		  		{
		  			System.out.println("InSufficient Balance....!<br>");
		  			System.out.println("Check Balance");
		  		}
		  	}
		  	else
		  	{
		  		System.out.println("Invalid Account Number And Password");
		  		System.out.println("....Try Again.....");
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
