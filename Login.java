package bank;
		import java.sql.Connection;
		import java.sql.DriverManager;
		import java.sql.PreparedStatement;
		import java.sql.ResultSet;
		import java.util.Scanner;
		
		public class Login 
		{
		  String uname; 
		  String passcode;
		  int accno;
		  Login(String uname,String passcode,int accno)
		  {
			  this.uname=uname;
			  this.passcode=passcode;
			  this.accno=accno;
		  }
		 
		public void userLog()
		  {
			System.out.println("----------------------------------------------");  
			try
			  {
				  Class.forName("oracle.jdbc.driver.OracleDriver");
				  Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Adi","adi");
				  PreparedStatement ps=con.prepareStatement("select *from customers where username=? and passcode=?");
				  ps.setString(1, uname);
				  ps.setString(2, passcode);
				  ResultSet rs=ps.executeQuery();
				  if(rs.next())
				  {
					  System.out.println("Successful login.....");
					  Scanner sc=new Scanner(System.in);
					  while(true)
					  {
						  System.out.println("1.Balance");
						  System.out.println("2.Deposit");
						  System.out.println("3.Withdraw");
						  System.out.println("4.Transfer");
						  System.out.println("5. Logout");
						  System.out.println("Enter your choice: ");
						  int ch=sc.nextInt();
						  if(ch==1)
						  {
							  Balance b=new Balance(uname,passcode);
							  b.checkBalance();
						  }
						  else if(ch==2)
						  {
							 System.out.println("Enter Amount:");
							 double amount=sc.nextDouble();
							  Deposit d=new Deposit(accno,amount);
							  d.doDeposit();
						  }
						  else if(ch==3)
						  {
							  System.out.println("Enter Amount:");
							  double amount=sc.nextDouble();
							  Withdraw w=new Withdraw(accno,amount);
							  w.doWithdraw();
						  }
						  else if(ch==4)
						  {
							  System.out.println("Enter targetAccount number: ");
							  int taccno=sc.nextInt();
							  System.out.println("Enter amount: ");
							  double amount=sc.nextDouble();
							  Transfer t=new Transfer(accno,amount,taccno,passcode);
							  t.doTrasfer();
						  }
						  else if(ch==5)
						  {
							  System.out.println("-------Thank you----Visit Again-----");
							  System.exit(1);
						  }
						  else
						  {
							  System.out.println("Invalid choice");
						  }
						  
					  }
				  }
				  else
				  {
					  System.out.println("Invalid Username And Pascode");
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
