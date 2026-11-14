import java.util.*;
import java.io.*;

public class Bank implements HasMenu {
	private Admin admin;
	private ArrayList<Customer> customers;

	public Bank(){
		this.admin = new Admin();
		this.customers = new ArrayList<Customer>();
	}

	public static void main(String[] args){
		Bank b = new Bank();
		b.start();
	}
	
	public String menu(){
		System.out.println("\nBank Menu");
		System.out.println("0) Exit system");
		System.out.println("1) Login as admin");
		System.out.println("2) Login as customer:");
		System.out.print("\nAction: ");

		return CheckingAccount.input.nextLine();
	}

	public void start(){
		boolean keepGoing = true;
		String response;

		while (keepGoing){
			response = menu();

			if (response.equals("0")){
				keepGoing = false;
				System.out.println("Exiting bank system");
			} else if (response.equals("1")){
				System.out.println("Admin login");
			} else if (response.equals("2")){
				System.out.println("Customer login");
			} else {
				System.out.println("Please enter a number 0-2");
			}
		}
	}
} // end bank



