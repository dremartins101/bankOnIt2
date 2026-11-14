import java.util.*;
import java.io.*;

public class Bank implements HasMenu {
	private Admin admin;
	private ArrayList<Customer> customers;

	public Bank(){
		this.admin = new Admin();
		this.customers = new ArrayList<Customer>();
		this.loadCustomers();
	}

	public static void main(String[] args){
		Bank b = new Bank();
		b.start();
		b.saveCustomers();
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

				if (admin.login()){
					startAdmin();
				} else {
					System.out.println("Incorrect username or PIN.");
				}
			} else if (response.equals("2")){
				System.out.println("Customer login");
				loginAsCustomer();
			} else {
				System.out.println("Please enter a number 0-2");
			}
		}
	}

	public void startAdmin(){
		boolean keepGoing = true;
		String response;

		while (keepGoing){
			response = admin.menu();
			if (response.equals("0")){
				keepGoing = false;
			} else if (response.equals("1")){
				fullCustomerReport();
			} else if (response.equals("2")){
				addUser();
			} else if (response.equals("3")){
				applyInterest();
			} else {
				System.out.println("Please enter a number 0-3.");
			}
		}
	} // end startAdmin
	
	public void fullCustomerReport(){
		System.out.println("Full customer report");

		for (Customer c : customers){
			System.out.println(c.getReport());
		}
	} // end customerreport
	
	public void addUser(){
		System.out.println("Add User");

		System.out.print("Name: ");
		String name = CheckingAccount.input.nextLine();

		System.out.print("PIN: ");
		String pin = CheckingAccount.input.nextLine();

		Customer c = new Customer(name, pin);
		customers.add(c);

		System.out.println("User added successfully.");
	} // end addUser
	
	public void applyInterest(){
		System.out.println("Apply interest");

		for (Customer c : customers){
			c.getSavings().calcInterest();

			System.out.println("New balance: " + c.getSavings().getBalanceString());
		}
	} // end applyInterest
	
	public void loginAsCustomer(){
		System.out.print("Username: ");
		String name = CheckingAccount.input.nextLine();

		System.out.print("PIN: ");
		String pin = CheckingAccount.input.nextLine();

		Customer currentCustomer = null;

		for (Customer c : customers){
			if (c.login(name, pin)){
				currentCustomer = c;
				break;
			}
		}
		if (currentCustomer != null){
			currentCustomer.start();
		} else {
			System.out.println("Incorrect username or PIN.");
		}
	} // end loginAsCustoemer
	
	public void loadSampleCustomers(){
		customers.clear();

		Customer alice = new Customer("Alice", "1111");
		alice.getChecking().setBalance(1000);
		alice.getSavings().setBalance(1000);

		Customer bob = new Customer("Bob", "2222");
		Customer cindy = new Customer("Cindy", "3333");

		customers.add(alice);
		customers.add(bob);
		customers.add(cindy);
	} // end loadSampleCustomers
	
	public void saveCustomers(){
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("customers.dat"));

			out.writeObject(customers);
			out.close();

			System.out.println("Customers saved.");
		} catch (IOException e){
			System.out.println("Error saving customers.");
		}
	} // end saveCustomers
	
	public void loadCustomers(){
		try{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("customers.dat"));

			customers = (ArrayList<Customer>) in.readObject();
			in.close();
			System.out.println("Customers loaded.");

		} catch (FileNotFoundException e){
			System.out.println("No saved data found.");
			loadSampleCustomers();
		} catch (IOException e){
			System.out.println("Error loading customers.");
		} catch (ClassNotFoundException e){
			System.out.println("Customer class not found.");
		}
	} // end loadCustomers

} // end bank



