package Model.DB;

import java.sql.ResultSet;

import Model.Contract.Contract;

public class FailContractDao  extends Dao {
	public FailContractDao() {
		super.connect();
	}

	public boolean create(Contract contract) {
		String query = "insert into failContract(contractID, customerID, customerName, customerPhoneNum, insuranceID, insuranceName, paymentCycle, insuranceFee, securityFee, period, reason) values (\""+
				contract.getContractID()+"\", \""+contract.getCustomerID()+"\", \""+contract.getCustomerName()+"\", \""+contract.getPhoneNum()+"\", \""+contract.getInsuranceID()+"\", \""+contract.getInsuranceName()
				+"\", \""+contract.getPaymentCycle()+"\", \""+contract.getInsuranceFee()+"\", \""+contract.getSecurityFee()+"\", \""+contract.getPeriod()+"\", \""+contract.getReason()+"\");";
		
		return super.create(query);
	}

	public ResultSet retrive() {
		String query = "";

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
	
	public boolean deleteFailContract(Contract contract) {
		String query="delete from failcontract where contractID=\""+contract.getContractID()+"\"";
		return super.delete(query);
	}

}
