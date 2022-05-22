package Contract;

import java.time.LocalDate;

import Customer.Rank;

/**
 * @author ansm6
 * @version 1.0
 * @created 08-5-2022 ���� 10:34:18
 */
public class Contract {

	private String accidentHistory;
	private String contractID;
	private String customerName;
	private LocalDate endDate;
	private int insuranceFee;
	private String insuranceID;
	private String paymentCycle;
	private int period;
	private String phoneNum;
	private int provisionFee;
	private int securityFee;
	private LocalDate startDate;
	private int unpaidFee;
	private String customerID;
	private String insuranceName;

	public Contract() {
		
	}

	public void finalize() throws Throwable {

	}

	public void apply() {

	}

	public void reapply() {

	}

	public void cancelContract() {

	}

	public void checkUnpaidFee() {

	}

	public void updatePayment() {

	}

	public void renew() {
		//갱신 내용 적고 저장
	}
	
	public void permit() {
		// Customer customer = new Customer(); 고객 정보 저장
		// 계약 정보 저장

	}

	public void allowRenew() {
		// DB에 갱신 정보 저장
	}

	

	public void underwrite() {
		// 심사 대기 DB에서 받아옴

	}

	public void verify() {
		// 보험료 검증
	}

	public void verifyRenew() {
		// 보험료 검증
	}

	public void search() {

	}

	public String getAccidentHistory() {
		return accidentHistory;
	}

	public void setAccidentHistory(String accidentHistory) {
		this.accidentHistory = accidentHistory;
	}

	public String getContractID() {
		return contractID;
	}

	public void setContractID(String contractID) {
		this.contractID = contractID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public int getInsuranceFee() {
		return insuranceFee;
	}

	public void setInsuranceFee(int insuranceFee) {
		this.insuranceFee = insuranceFee;
	}

	public String getInsuranceID() {
		return insuranceID;
	}

	public void setInsuranceID(String insuranceID) {
		this.insuranceID = insuranceID;
	}

	public String getPaymentCycle() {
		return paymentCycle;
	}

	public void setPaymentCycle(String paymentCycle) {
		this.paymentCycle = paymentCycle;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public int getProvisionFee() {
		return provisionFee;
	}

	public void setProvisionFee(int provisionFee) {
		this.provisionFee = provisionFee;
	}

	public int getSecurityFee() {
		return securityFee;
	}

	public void setSecurityFee(int securityFee) {
		this.securityFee = securityFee;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public int getUnpaidFee() {
		return unpaidFee;
	}

	public void setUnpaidFee(int unpaidFee) {
		this.unpaidFee = unpaidFee;
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

}// end Contract