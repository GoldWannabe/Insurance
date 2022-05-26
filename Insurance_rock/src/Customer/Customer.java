package Customer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.UUID;

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
	private ArrayList<String> rankID = new ArrayList<String>();

	private enum Esex {
		male, female, none,
	};

	public Customer() {

	}

	public Customer(String inputString) {
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

		String contractTemp = stringTokenizer.nextToken();
		if (!contractTemp.equals(null) || contractTemp.equals("null")) {
			String[] tempID = contractTemp.split("-");
			for (int i = 0; i < tempID.length; i++) {
				this.contractID.add(tempID[i]);
			}
		}
		String rankTemp = stringTokenizer.nextToken();
		if (!rankTemp.equals(null) || rankTemp.equals("null")) {
			this.rankID.add(rankTemp);
			while (stringTokenizer.hasMoreTokens()) {
				this.rankID.add(stringTokenizer.nextToken());
			}
		}
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

	public void setCustomerID() {
		this.customerID = UUID.randomUUID().toString();
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

//	public String toStringRankID() {
//		String returnID = "";
//		for(Rank rank : this.rankList) {
//			returnID = rank.getRankID()+" ";
//		}
//		System.out.println("rankID: "+returnID);
//		return returnID;
//	}

	public void register() {
		try {
			File file = new File(".//DB//Customer.txt");
			FileWriter fileWriter = new FileWriter(file, true);
			fileWriter.write(this.customerID + " " + this.name + " " + this.SSN + " " + this.sex + " " + this.phoneNum
					+ " " + this.address + " " + this.bankName + " " + this.accountNum + " " + this.insuranceNum + " "
					+ this.toStringContract() + " " + this.toStringRankID() + "\n");
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			System.out.println("DB 접근 오류: 정보 접근에 실패하였습니다. 해당 문제가 계속 발생할 시에는 사내 시스템 관리팀(1234-5678)에게 문의 주시기 바랍니다.");
			e.printStackTrace();
		}
	}

}// end Customer