package Model.Payment;

public interface PaymentList {


	public void finalize() throws Throwable;
	
	public boolean add(Payment payment);
	public boolean delete(String paymentID);
	public Payment get(String paymentID);
	public boolean update(String paymentID);

	public Payment get(String CustomerName, String CustomerPhoneNum);
	
}//end PaymentList