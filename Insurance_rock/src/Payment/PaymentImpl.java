package Payment;

import java.util.ArrayList;

public class PaymentImpl implements PaymentList {

	private ArrayList<Payment> paymentList = new ArrayList<Payment>();

	public PaymentImpl(){

	}

	public void finalize() throws Throwable {

	}

	@Override
	public boolean add(Payment payment) {
		if(this.paymentList.add(payment)) return true;
		return false;
	}

	@Override
	public boolean delete(String paymentID) {
		if(this.paymentList.remove(paymentID)) return true;
		return false;
	}

	@Override
	public Payment get(String paymentID) {
		for(Payment payment : this.paymentList) {
			if(payment.getPaymentID().equals(paymentID)) return payment;
		}
		return null;
	}

	@Override
	public boolean update(String paymentID) {
		for(Payment payment : this.paymentList) {
			if(payment.getPaymentID().equals(paymentID)) {
				//update
				return true;
			}
		}
		return false;
	}

}//end PaymentImpl