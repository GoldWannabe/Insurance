package Customer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Channel.Channel;

public class CustomerListImpl implements CustomerList {

	private ArrayList<Customer> customerList;
	private ArrayList<Rank> rankList;

	public CustomerListImpl() {
		init();
	}

	public void init() {
		try {
			BufferedReader customerFile = new BufferedReader(new FileReader(".//DB//Customer.txt"));
			this.customerList = new ArrayList<Customer>();
			while (customerFile.ready()) {
				String customer = customerFile.readLine();
				if (!customer.equals("")) {
					this.customerList.add(new Customer(customer));
				}
			}
			customerFile.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void rewrite() {
		try {
			File file = new File(".//DB//Customer.txt");
			FileWriter fileWriter = new FileWriter(file);
			for (Customer customer : this.customerList) {
				fileWriter.write(customer.getCustomerID() + " " + customer.getName() + " "
						+ customer.getSSN() + " " + customer.getSex() + " "
						+ customer.getPhoneNum() + " " + customer.getAddress() +" "
						+ customer.getBankName()+" "+customer.getAccountNum()+" "
						+ customer.getInsuranceNum()+" "+customer.toStringContract()+" "
						+ customer.toStringRankID()+"\n");
				fileWriter.flush();

			}
			fileWriter.close();
		} catch (IOException e) {
			System.out.println("DB 접근 오류: 정보 접근에 실패하였습니다. 해당 문제가 계속 발생할 시에는 사내 시스템 관리팀(1234-5678)에게 문의 주시기 바랍니다.");
			e.printStackTrace();
		}
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
				customer.setInsuranceNum((float) (customer.getInsuranceNum()+0.1));
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