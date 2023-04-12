package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class Balance {

	String uname;
	   String passcode;
	   Balance(String uname,String passcode)
	   {
		   this.uname=uname;
		   this.passcode=passcode;
	   }
	   public void checkBalance()
	   {
		   System.out.println("----------------------------------------------");
		   try
		   {
		   	Class.forName("oracle.jdbc.driver.OracleDriver");
		   	Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Adi","adi");
		   	PreparedStatement ps=con.prepareStatement("select accno,cname,balance from customers where username=? and passcode=?");
		   	ps.setString(1, uname);
		   	ps.setString(2, passcode);
		   	ResultSet rs=ps.executeQuery();
		   	ResultSetMetaData rsmd=rs.getMetaData();
		   	int n=rsmd.getColumnCount();
		   	for(int i=1; i<=n; i++)
		   	{
		   		System.out.print(rsmd.getColumnName(i)+" ");
		   	}
		   	System.out.println();
		   	while(rs.next())
		   	{
		   		for(int j=1; j<=n; j++)
		   		{
		   			System.out.print(rs.getString(j)+" ");
		   		}
		   	}
		   	System.out.println();
		   	System.out.println("---------------------------------------");
		   	con.close();
		   }
		   catch(Exception ex)
		   {
		   	System.out.println(ex);
		   }
	   }

}
