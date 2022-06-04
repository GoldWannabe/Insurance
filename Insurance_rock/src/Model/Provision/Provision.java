
package Model.Provision;

import java.time.LocalDate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.StringTokenizer;
import java.util.UUID;


import Model.Customer.Customer;
import Model.DB.ProvisionDao;

public class Provision {
	private String provisionID;
	private String customerID;
	private String accountNum;
	private String contractID;
	private String insuranceName;
	private String bankName;
	private int compensation;
	private LocalDate compensationDate;
	private String customerName;
	private String insuranceID;
	private boolean longTerm;
	private String phoneNum;
	public Customer m_Customer;
	@SuppressWarnings("unused")
	private enum EInsurance{generalInsurance, houseInsurace};
	private Model.Insurance.Insurance.EInsurance insuranceType;
	public ProvisionDao provisionDao;

	public ResultSet getProvision() {
		this.provisionDao = new ProvisionDao();
		return this.provisionDao.retrive();
	}
	public String getContractID() {
		return contractID;
	}

	public void setContractID(String contractID) {
		this.contractID = contractID;
	}
	
	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getInsuranceName() {
		return insuranceName;
	}
	
	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
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
	public void setInsuranceType(Model.Insurance.Insurance.EInsurance e) {
		this.insuranceType = e;
	}

	public Model.Insurance.Insurance.EInsurance getInsuranceType() {
		return insuranceType;
	}

	public Provision(){

	}

	public void finalize() throws Throwable {

	}
	public void checkProvision(){

	}


	public void creatNew() {
		ProvisionDao provisionDao = new ProvisionDao();
		provisionDao.creatNew(this);
		
	}
}

