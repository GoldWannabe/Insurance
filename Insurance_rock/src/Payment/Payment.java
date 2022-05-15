package Payment;

import java.time.LocalDate;
import java.util.Date;

import Customer.Customer;

public class Payment {

	private String paymentID;
	private String customerID;
	private String accountNum;
	private String cardOrBankName;
	private int insuranceFee;
	private String insuranceID;
	private LocalDate paidDate;
	
	private enum EInsuranceType{
		genralInsurance,
		houseInsurance
	}
	private EInsuranceType insuranceType;

	public Payment(){

	}

	public void finalize() throws Throwable {

	}
	public void checkPayment(){

	}

	public String getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(String paymentID) {
		this.paymentID = paymentID;
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

	public String getCardOrBankName() {
		return cardOrBankName;
	}

	public void setCardOrBankName(String cardOrBankName) {
		this.cardOrBankName = cardOrBankName;
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

	public LocalDate getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(LocalDate paidDate) {
		this.paidDate = paidDate;
	}

	public EInsuranceType getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(String insuranceType) {
		// set logic
		this.insuranceType = EInsuranceType.genralInsurance;
	}
	
	
}//end Payment