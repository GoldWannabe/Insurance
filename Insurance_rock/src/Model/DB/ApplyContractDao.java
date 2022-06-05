package Model.DB;

import java.sql.ResultSet;

import Model.Contract.Contract;

public class ApplyContractDao  extends Dao {
	public ApplyContractDao() {
		super.connect();
	}

	public boolean create() {
		String query = "";

		System.out.println(query);
		return super.create(query);
	}
	
	public boolean create(Contract contract) {
		String query = "insert into applyContract(contractID, customerID, customerName, customerPhoneNum, insuranceID, insuranceName, paymentCycle, insuranceFee, securityFee, period) values (\""+
				contract.getContractID()+"\", \""+contract.getCustomerID()+"\", \""+contract.getCustomerName()+"\", \""+contract.getPhoneNum()+"\", \""+contract.getInsuranceID()+"\", \""+contract.getInsuranceName()
				+"\", \""+contract.getPaymentCycle()+"\", \""+contract.getInsuranceFee()+"\", \""+contract.getSecurityFee()+"\", \""+contract.getPeriod()+"\");";
		
		System.out.println(query);
		return super.create(query);
	}

	public ResultSet retrive() {
		String query = "select * from applyContract";

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
}
