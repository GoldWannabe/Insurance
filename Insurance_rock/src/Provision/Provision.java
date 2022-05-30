package Provision;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import Customer.Customer;

public class Provision {
	
	private String provisionID;
	private String accountNum;
	private String bankName;
	private int compensation;
	private LocalDate compensationDate;
	private String customerName;
	private String insuranceID;
	private boolean longTerm;
	private String phoneNum;
	public Customer m_Customer;
	private enum einsuranceType{generalInsurance, houseInsurace};
		
	private einsuranceType insuranceType;
	private ProvisionList provisionList;

	///insurancetype 여기도~~ setter어떻게 해 . 
	
	public void setProvision() {
		this.provisionList = new ProvisionListImpl();
		try {
			File file = new File(".//DB//Provision.txt");
			
			Scanner fileScanner = new Scanner(file);
			
			while(fileScanner.hasNextLine()) {
				Provision tempProvision = new Provision();
				tempProvision.setProvisionID(fileScanner.next());
				tempProvision.setAccountNum(fileScanner.next());
				tempProvision.setBankName(fileScanner.next());
				tempProvision.setCompensation(fileScanner.nextInt());
				tempProvision.setCompensationDate(LocalDate.parse(fileScanner.next()));
				tempProvision.setCustomerName(fileScanner.next());
				tempProvision.setInsuranceID(fileScanner.next());
				tempProvision.setLongTerm(fileScanner.nextBoolean());
				tempProvision.setPhoneNum(fileScanner.next());
//				tempProvision.setType(fileScanner.next());
				fileScanner.nextLine();
//				tempProvision.getInsuranceType(); //?
				this.provisionList.add(tempProvision);
			}
		}catch(IOException e) {
			System.out.println(
					"지급 기록이 없습니다. 문제가 있을 시에는 사내 시스템 관리팀(1234-5678)에게 문의 주시기 바랍니다.");
			e.getStackTrace();
		}
	}
//	private void setType(String type) {
//		if(type.equals(einsuranceType.generalInsurance.toString())) {
//			this.insuranceType = einsuranceType.generalInsurance;
//		}else if() {
//			
//		}
//	}
	public void checkProvision(){
//		지급 정보(보험이름,보험종류,장기여부,은행명,계좌번호,보상금액,보상일)를 출력한다
		if(!(this.provisionList == null)) {
			System.out.println("------------------------------------------------------------------------------------");
			System.out.printf("%10s %10s %10s %10s %15s %15s %15s", 
					"보험이름","보험종류","장기여부","은행명","계좌번호","보상금액","보상일");
			System.out.println();
			System.out.println("------------------------------------------------------------------------------------");
			for(Provision provision : this.provisionList.getAll()) {
				System.out.format("%10s %10s %10s %10s %15s %15s %15s",
						provision.getInsuranceID(), provision.getInsuranceID(),provision.isLongTerm(),getBankName(),
						provision.getAccountNum(), provision.getCompensation(), provision.getCompensationDate());
				System.out.println();
			}
		}
	}
	
	public String getProvisionID() {
		return provisionID;
	}

	public void setProvisionID(String provisionID) {
		this.provisionID = provisionID;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public int getCompensation() {
		return compensation;
	}

	public void setCompensation(int compensation) {
		this.compensation = compensation;
	}

	public LocalDate getCompensationDate() {
		return compensationDate;
	}

	public void setCompensationDate(LocalDate compensationDate) {
		this.compensationDate = compensationDate;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getInsuranceID() {
		return insuranceID;
	}

	public void setInsuranceID(String insuranceID) {
		this.insuranceID = insuranceID;
	}

	public boolean isLongTerm() {
		return longTerm;
	}

	public void setLongTerm(boolean longTerm) {
		this.longTerm = longTerm;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public Customer getM_Customer() {
		return m_Customer;
	}

	public void setM_Customer(Customer m_Customer) {
		this.m_Customer = m_Customer;
	}

	public einsuranceType getInsuranceType() {
		return insuranceType;
	}

	public Provision(){

	}

	public void finalize() throws Throwable {

	}

}//end Provision