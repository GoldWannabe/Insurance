package Model.DB;

import java.sql.ResultSet;
import Model.Payment.*;

public class PaymentDao  extends Dao {
	public PaymentDao() {
		super.connect();
	}

	public boolean create(Payment payment) {
		String query = "insert into payment(paymentID, customerID, customerName, customerPhoneNum, accountNum, cardOrBankName, insuranceFee, insuranceName, paidDate) values (\""
				+payment.getPaymentID()+"\",\""+payment.getCustomerID()+"\",\""+payment.getCustomerName()+"\",\""+payment.getCustomerPhoneNum()+"\",\""
				+payment.getAccountNum()+"\",\""+payment.getCardOrBankName()+"\",\""+payment.getInsuranceFee()+"\",\""+payment.getInsuranceName()+"\",\""+payment.getPaidDate()+"\");";

		return super.create(query);
	}

	public ResultSet retrive() {
		String query = "select * from payment;";

		return super.retrive(query);
	}

	public boolean update() {
		String query = "";

		return super.update(query);
	}

	public boolean delete() {
		String query = "";

		return super.delete(query);
	}
	//흠
	public boolean updateBankName(String newBankName, String paymentID) {
		String query = "update payment set cardOrBankName = " + "'" + newBankName + "'" + "where paymentID = " + "'" + paymentID + "';";

		return super.update(query);
		
	}
}
