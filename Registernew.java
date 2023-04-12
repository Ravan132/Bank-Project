package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Registernew {

	int accno;
	String name;
	double balance;
	String uname;
	String passcode;
	Registernew(int acc,String name,double balance,String uname,String passcode)
	{
		this.accno=acc;
		this.name=name;
		this.balance=balance;
		this.uname=uname;
		this.passcode=passcode;
	}
	void addUser()
	{
		System.out.println("----------------------------------------------");
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Adi","adi");
			PreparedStatement ps=con.prepareStatement("insert into customers values(?,?,?,?,?)");
			ps.setInt(1, accno);
			ps.setString(2, name);
			ps.setDouble(3, balance);
			ps.setString(4, uname);
			ps.setString(5, passcode);
			int i=ps.executeUpdate();
			if(i==1)
			{
				System.out.println(i+" Register Successfully.....");
			}
			else
				System.out.println("Cannot Register .....Some thing is Wrong..!");
			System.out.println("----------------------------------------------");
			con.close();
			
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
}
