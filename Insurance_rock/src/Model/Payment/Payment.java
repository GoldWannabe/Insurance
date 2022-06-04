package Model.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.StringTokenizer;
import java.util.UUID;

import Model.DB.PaymentDao;

public class Payment {

	private String paymentID;
	private String customerID;
	private String customerName;
	private String customerPhoneNum;
	private String accountNum;
	private String cardOrBankName;
	private int insuranceFee;
	private String insuranceName;
	private LocalDate paidDate;
	private String contractID;

	public PaymentDao paymentDao;
	

	public Payment(){
	}

	public void finalize() throws Throwable {

	}
	public ResultSet getPayment() {
		this.paymentDao = new PaymentDao();
		return this.paymentDao.retrive(); 
	}
	public boolean addPayment() {
		return this.paymentDao.create(this);
	}
	
	public boolean paymentUpdate() {
		this.paymentDao = new PaymentDao();
		this.paidDate = LocalDate.now();
		return false;
//		if(this.paymentDao.create(this))
//			return true;
//		return false;
	}
	//위에말고
	//밑에로해보자...ㅠ
	
	public void createPayment(){
		this.paymentDao = new PaymentDao();
		this.paymentID = UUID.randomUUID().toString();
		this.paidDate = LocalDate.now();

	}

//	public void checkPayment(){
//
//	}

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

	public LocalDate getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(LocalDate paidDate) {
		this.paidDate = paidDate;
	}

	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerPhoneNum() {
		return customerPhoneNum;
	}
	public void setCustomerPhoneNum(String customerPhoneNum) {
		this.customerPhoneNum = customerPhoneNum;
	}
	public String getInsuranceName() {
		return insuranceName;
	}
	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}

	public void setContractID(String contractID) {
		this.contractID = contractID;
	}
	public String getContractID() {
		return contractID;
	}

	

	
}//end Payment
