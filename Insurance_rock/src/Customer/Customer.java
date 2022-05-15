package Customer;

import java.util.ArrayList;

import Account.Account;
import Contract.Contract;

public class Customer {

	private String customerID;
	private String accountNum;
	private String address;
	private String bankName;
	private ArrayList<Contract> contractID = new ArrayList<Contract>();
	private float insuranceNum;
	private String name;
	private String phoneNum;
	private enum Esex{
		male,
		female,
		none,
	};
	
	private Esex sex;
	private String SSN;

	public Customer(){

	}

	public void finalize() throws Throwable {

	}
	public void add(){

	}

	public void apply(){

	}

	public void checkPayment(){

	}

	public void checkProvision(){

	}

	public void checkUnpaidFee(){

	}

	public void choosePaymentMethod(){

	}

	public void delete(){

	}

	public void edit(){

	}

	public void payInsuranceFee(){

	}

	public void reapply(){

	}

	public void receiveProvision(){

	}

	public void search(){

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

	public ArrayList<Contract> getContractID() {
		return contractID;
	}

	public void setContractID(ArrayList<Contract> contractID) {
		this.contractID = contractID;
	}

	public float getInsuranceNum() {
		return insuranceNum;
	}

	public void setInsuranceNum(float insuranceNum) {
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

	public Esex getSex() {
		return sex;
	}

	public void setSex(String sex) {
		// set logic
		this.sex = Esex.male;
	}
	
}//end Customer