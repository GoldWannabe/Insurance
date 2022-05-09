package Provision;

import java.util.Date;

import Customer.Customer;

/**
 * @author ansm6
 * @version 1.0
 * @created 08-5-2022 ¿ÀÈÄ 10:34:19
 */
public class Provision {

	private String accountNum;
	private String bankName;
	private int compensation;
	private Date compensationDate;
	private String customerName;
	private String insuranceID;
	private enum insuranceType{a,b};
	private boolean longTerm;
	private String phoneNum;
	public Customer m_Customer;

	public Provision(){

	}

	public void finalize() throws Throwable {

	}
	public void checkProvision(){

	}
}//end Provision