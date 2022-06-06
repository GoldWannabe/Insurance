package Control.SalesTeam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Model.Customer.*;
import View.Team.SalesTeamTui;
import exception.DBAcceptException;

public class CustomerManagement {
	private SalesTeamTui salesTeamTui;
	private Customer customer;
	private CustomerListImpl customerList;
	
	public boolean searchCustomer() {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		this.customer = new Customer();
		this.customerList = new CustomerListImpl();
		this.salesTeamTui = new SalesTeamTui();
		
		this.salesTeamTui.showSearchCustomerStart();
		this.salesTeamTui.showEnterCustomerName();
		String name = scanner.next();
		this.salesTeamTui.showEnterCustomerPhoneNum();
		String phoneNum = scanner.next();
		this.setCustomer();
		this.customer = this.customerList.get(name, phoneNum);
		
		return showCustomerInfo(this.customer);
	}

	private void setCustomer() {
		// TODO Auto-generated method stub
		ResultSet resultSet = this.customer.getCustomer();
		try {
			while(resultSet.next()) {
				Customer customer = new Customer();
				customer.setCustomerID(resultSet.getString("customerID"));
				customer.setName(resultSet.getString("name"));
				customer.setSSN(resultSet.getString("SSN"));
				customer.setSex(resultSet.getString("sex"));
				customer.setPhoneNum(resultSet.getString("phoneNum"));
				customer.setAddress(resultSet.getString("address"));
				customer.setBankName(resultSet.getString("bankName"));
				customer.setAccountNum(resultSet.getString("accountNum"));
				customer.setInsuranceNum(resultSet.getDouble("insuranceNum"));
				this.customerList.add(customer);
			}
		} catch (SQLException e) {
			throw new DBAcceptException();
		}
	}

	   private boolean showCustomerInfo(Customer customer) {
		  Scanner scanner = new Scanner(System.in);
	      String string = scanner.next();
		  this.salesTeamTui.viewCustomerInfo(customer);
	      this.salesTeamTui.viewButton();
	      if(string.equals("1")) {
	    	  editCustomerInfo();
	      }
	      else if(string.equals("2")) {
	    	  addCustomerInfo();
	      }
	      else if(string.equals("3")) {
	    	  deleteCustomerInfo();
	      }  	  
	      return false;
	   }

	private void editCustomerInfo() {
	}
	
	private void deleteCustomerInfo() {
		
	}

	private void addCustomerInfo() {
		
	}




	

}
