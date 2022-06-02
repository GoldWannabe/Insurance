package Model.Provision;

import java.time.LocalDate;

import Model.Customer.Customer;

/**
 * @author ansm6
 * @version 1.0
 * @created 08-5-2022 ���� 10:34:19
 */
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
	public void checkProvision(){

	}
}//end Provision