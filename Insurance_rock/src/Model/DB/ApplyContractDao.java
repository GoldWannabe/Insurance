package Model.DB;

import java.sql.ResultSet;

import Model.Contract.Contract;

public class ApplyContractDao  extends Dao {
	public ApplyContractDao() {
		super.connect();
	}

	public boolean create() {
		String query = "";

		return super.create(query);
	}
	
	public boolean create(Contract contract) {
		String query = "insert into applyContract(contractID, customerID, customerName, customerPhoneNum, insuranceID, insuranceName, paymentCycle, insuranceFee, securityFee, period) values (\""+
				contract.getContractID()+"\", \""+contract.getCustomerID()+"\", \""+contract.getCustomerName()+"\", \""+contract.getPhoneNum()+"\", \""+contract.getInsuranceID()+"\", \""+contract.getInsuranceName()
				+"\", \""+contract.getPaymentCycle()+"\", \""+contract.getInsuranceFee()+"\", \""+contract.getSecurityFee()+"\", \""+contract.getPeriod()+"\");";
		
		return super.create(query);
	}

	public ResultSet retrive() {
		String query = "select * from applyContract";

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

	public boolean deleteByID(String contractID) {
		String query = "delete from applyContract where contractID=\""+contractID+"\";";
		
		return super.delete(query);
	}

	

	
}
