package Model.Customer;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.UUID;

import Model.DB.CustomerDao;

public class Customer {

	private String customerID;
	private String name;
	private String SSN;
	private Esex sex;
	private String phoneNum;
	private String address;
	private String bankName;
	private String accountNum;
	private Double insuranceNum;

	private ArrayList<String> contractID = new ArrayList<String>();
	private ArrayList<String> rankID = new ArrayList<String>();

	private enum Esex {
		male, female, none,
	};
	
	private CustomerDao customerDao;

	public Customer() {
		this.customerID = UUID.randomUUID().toString();
	}

	public void finalize() throws Throwable {

	}

	public void add() {

	}

	public void apply() {

	}

	public void checkPayment() {

	}

	public void checkProvision() {

	}

	public void checkUnpaidFee() {

	}

	public void choosePaymentMethod() {

	}

	public void delete() {

	}

	public void edit() {

	}

	public void payInsuranceFee() {

	}

	public void reapply() {

	}

	public void receiveProvision() {

	}

	public void search() {

	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public ArrayList<String> getContractID() {
		return contractID;
	}

	public void setContractID(ArrayList<String> contractID) {
		this.contractID = contractID;
	}

	public ArrayList<String> getRankID() {
		return rankID;
	}

	public void setRankID(ArrayList<String> rankID) {
		this.rankID = rankID;
	}

	public Double getInsuranceNum() {
		return insuranceNum;
	}

	public void setInsuranceNum(Double insuranceNum) {
		this.insuranceNum = insuranceNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getSSN() {
		return SSN;
	}

	public void setSSN(String SSN) {
		this.SSN = SSN;
	}

	public String getSex() {
		return sex.toString();
	}
	
	public void setSex(String sex) {
		// set logic
		if (sex.equals(Esex.male.toString())) {
			this.sex = Esex.male;
		} else if (sex.equals(Esex.female.toString())) {
			this.sex = Esex.female;
		} else if (sex.equals(Esex.none)) {
			this.sex = Esex.none;
		} else {
			// default
			this.sex = Esex.none;
		}
	}

	public String toStringContract() {
		if (!this.contractID.equals(null)) {
			String returnID = String.join("-", this.contractID);
			return returnID;
		}
		return "null";
	}

	public String toStringRankID() {
		if (!this.rankID.equals(null)) {
			String returnID = String.join(" ", this.rankID);
			return returnID;
		}
		return "null";
	}

	public ResultSet getCustomer() {
		this.customerDao = new CustomerDao();
		return this.customerDao.retrive();
	}
	
	public boolean register() {
		this.customerDao = new CustomerDao();
		return this.customerDao.create(this);
	}


	public ResultSet retrivecustomerBank() {
		this.customerDao = new CustomerDao();
		return this.customerDao.retrivecustomerBank(this);
	}

	public boolean updateInsuranceNum() {
		this.customerDao = new CustomerDao();
		return this.customerDao.updateInsuranceNum(this);
	}
}