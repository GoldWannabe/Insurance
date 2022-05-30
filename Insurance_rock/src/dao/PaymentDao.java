package dao;

import java.sql.ResultSet;
import Payment.Payment;

public class PaymentDao  extends Dao {
	public PaymentDao() {
		super.connect();
	}

	public boolean create() {
		String query = "";

		System.out.println(query);
		return super.create(query);
	}

	public ResultSet retrive(Payment payment) {
		String query = "";
		
		System.out.println(query);
		return super.retrive(query);
	}

	public boolean update() {
		String query = "";

		System.out.println(query);
		return super.update(query);
	}

	public boolean delete() {
		String query = "";

		System.out.println(query);
		return super.delete(query);
	}
	//다른 여러개의 클래스 만들수잇음. 
}
