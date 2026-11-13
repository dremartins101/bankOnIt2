import java.util.*;
import java.io.*;

public abstract class Admin extends User{
	public admin(){
		super("admin", "0000");
	}

	public String menu(){
		System.out.println("\n Admin Menu");
		System.out.println("0) Exit ");
		System.out.println("1) Full customer report: ");
		System.out.println("2) Add user");
		System.out.println("3) Apply Interest");
		System.out.println("\n Action: ");

		return CheckingAccount.input.nextLine();
	}

	public void start(){

	}

	public String getReport(){
		return String.format("Admin: %s", userName);
	}
} // end admin
