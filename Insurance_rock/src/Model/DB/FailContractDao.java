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
		
		System.out.println(query);
		return super.create(query);
	}

	public ResultSet retrive() {
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

}
