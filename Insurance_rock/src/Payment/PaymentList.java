package Payment;

import java.util.ArrayList;

public interface PaymentList {

	
	public boolean add(Payment payment);
	public boolean delete(String paymentID);
	public Payment get(String paymentID);
	public boolean update(String paymentID);
	public ArrayList<Payment> getAll();
	
	public void finalize() throws Throwable;
}//end PaymentList