package Model.DB;

import java.sql.ResultSet;

import Model.Customer.Customer;

public class CustomerDao  extends Dao {
	public CustomerDao() {
		super.connect();
	}

	public boolean create() {
		String query = "";

		System.out.println(query);
		return super.create(query);
	}
	
	public boolean create(Customer customer) {
		String query = "insert into customer(customerID, Name, SSN, Sex, phoneNum, address, bankName, accountNum, insuranceNum) values (\""
				+customer.getCustomerID()+"\","+customer.getName()+"\","+customer.getSSN()+"\","+customer.getSex()+"\","
				+customer.getPhoneNum()+"\","+customer.getAddress()+"\","+customer.getBankName()+"\","+customer.getAccountNum()+"\","+customer.getInsuranceNum()+"\");";
		return super.create(query);
	}

	public ResultSet retrive() {
		String query = "select * from customer;";

		System.out.println(query);
		return super.retrive(query);
	}

	public boolean update() {
		String query = "";

		System.out.println(query);
		return super.update(query);
	}

	public boolean delete() {
		String query = "";

		System.out.println(query);
		return super.delete(query);
	}

	public ResultSet retriveID(String CustomerID) {
		String query = "select * from customer where customerID=\""+CustomerID+"\";";

		System.out.println(query);
		return super.retrive(query);
	}

}
