package Customer;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Customer {

	private String customerID;
	private String name;
	private String SSN;
	private Esex sex;
	private String phoneNum;
	private String address;
	private String bankName;
	private String accountNum;
	private float insuranceNum;
	
	private ArrayList<String> contractID = new ArrayList<String>();
	private ArrayList<Rank> rankList = new ArrayList<Rank>();
	
	private enum Esex{
		male,
		female,
		none,
	};

	public Customer(String inputString, ArrayList<Rank> rankList){
		StringTokenizer stringTokenizer = new StringTokenizer(inputString);
		this.customerID = stringTokenizer.nextToken();
		this.name = stringTokenizer.nextToken();
		this.SSN = stringTokenizer.nextToken();
		this.setSex(stringTokenizer.nextToken());
		this.phoneNum = stringTokenizer.nextToken();
		this.address = stringTokenizer.nextToken();
		this.bankName = stringTokenizer.nextToken();
		this.accountNum = stringTokenizer.nextToken();
		this.insuranceNum = Float.parseFloat(stringTokenizer.nextToken());
		
		while (stringTokenizer.hasMoreTokens()) {
    		this.contractID.add(stringTokenizer.nextToken());
    	}
		this.rankList = rankList;
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

	public ArrayList<String> getContractID() {
		return contractID;
	}

	public void setContractID(ArrayList<String> contractID) {
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
		if(sex.equals(Esex.male.toString())) {
			this.sex = Esex.male;
		}else if(sex.equals(Esex.female.toString())) {
			this.sex = Esex.female;
		}else if(sex.equals(Esex.none)) {
			this.sex = Esex.none;
		}else {
			//default
			this.sex = Esex.none;
		}
	}

	public ArrayList<Rank> getRankList() {
		return rankList;
	}

	public void setRankList(ArrayList<Rank> rankList) {
		this.rankList = rankList;
	}
	
	
	
}//end Customer