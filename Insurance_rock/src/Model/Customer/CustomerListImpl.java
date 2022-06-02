package Model.Customer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Model.Channel.Channel;

public class CustomerListImpl implements CustomerList {

	private ArrayList<Customer> customerList;

	public CustomerListImpl() {
		this.customerList = new ArrayList<Customer>();
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
	public boolean editInsuranceNum(String customerID) {
		for (Customer customer : this.customerList) {
			if (customer.getContractID().equals(customerID)) {
				customer.setInsuranceNum((Double) (customer.getInsuranceNum()+0.1));
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