package Customer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CustomerListImpl implements CustomerList {

	private ArrayList<Customer> customerList;
	private ArrayList<Rank> rankList;

	public CustomerListImpl() {
		init();
	}

	private void init() {
		try {
			BufferedReader customerFile = new BufferedReader(new FileReader(".//DB//Customer.txt"));
			this.customerList = new ArrayList<Customer>();
			while (customerFile.ready()) {
				String customer = customerFile.readLine();
				if (!customer.equals("")) {
					this.customerList.add(new Customer(customer, setRank(customer)));
				}
			}
			customerFile.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private ArrayList<Rank> setRank(String customer) {
		String[] tempCustomer = customer.split(" ");
		try {
			BufferedReader rankFile = new BufferedReader(new FileReader(".//DB//Rank.txt"));
			this.rankList = new ArrayList<Rank>();
			while (rankFile.ready()) {
				String rank = rankFile.readLine();
				String[] tempRank = rank.split(" ");
				//rank is not null && equal customerID
				if (!rank.equals("") && tempCustomer[0].equals(tempRank[0])) {
					this.rankList.add(new Rank(rank));
				}
			}
			rankFile.close();
			return this.rankList;
			

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// exception
		return null;
	}

	public void finalize() throws Throwable {

	}

	@Override
	public boolean add(Customer customer) {
		if (this.customerList.add(customer))
			return true;
		return false;
	}

	@Override
	public boolean delete(String customerID) {
		if (this.customerList.remove(customerID))
			return true;
		return false;
	}

	@Override
	public boolean edit(String customerID) {
		for (Customer customer : this.customerList) {
			if (customer.getContractID().equals(customerID)) {
				// edit
				return true;
			}
		}
		return false;
	}

	@Override
	public Customer search(String name, String phoneNum) {
		for (Customer customer : this.customerList) {
			if (customer.getName().equals(name) && customer.getPhoneNum().equals(phoneNum)) {
				// search
				return customer;
			}
		}
		return null;
	}

}// end CustomerListImpl