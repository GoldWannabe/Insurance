package Model.Customer;

import java.util.ArrayList;

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

	@SuppressWarnings("unlikely-arg-type")
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