package Payment;

import java.util.Date;

import Customer.Customer;

/**
 * @author ansm6
 * @version 1.0
 * @created 08-5-2022 ¿ÀÈÄ 10:34:19
 */
public class Payment {

	private String accountNum;
	private String cardOrBankName;
	private String customerName;
	private int insuranceFee;
	private String insuranceID;
	private enum insuranceType{a,b};
	private Date paidDate;
	private String phoneNum;
	public Customer m_Customer;

	public Payment(){

	}

	public void finalize() throws Throwable {

	}
	public void checkPayment(){

	}
}//end Payment