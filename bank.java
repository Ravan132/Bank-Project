package bank;
import java.util.Scanner;
public class bank {

	public static void main(String[] args) {
		
		System.out.println("------> Welcome To MyBank <-------");
		Scanner sc=new Scanner(System.in);
		while(true)
		{
			System.out.println("1.Create New Account");
			System.out.println("2.Login IN ");
			System.out.println("Select your Choice: ");
			int n=sc.nextInt();
			if(n==1)
			{
				System.out.println("Enter your Accno:");
				int acc=sc.nextInt();
				System.out.println("Enter your name:");
				String name=sc.next();
				System.out.println("Enter balance:");
				double balance=sc.nextDouble();
				System.out.println("Enter your UserName:");
				String uname=sc.next();
				System.out.println("Enter your passcode:");
				String passcode=sc.next();
				Registernew r=new Registernew(acc,name,balance,uname,passcode);
				r.addUser();
			}
			else if(n==2)
			{
				System.out.println("Enter the Username:");
				String uname=sc.next();
				System.out.println("Enter the passcode: ");
				String passcode=sc.next();
				System.out.println("Enter your Accno:");
				int acc=sc.nextInt();
				Login l=new Login(uname,passcode,acc);
				l.userLog();
			}
			else
			{
				System.out.println("Invalid choice.....");
			}
		}

	}
}


