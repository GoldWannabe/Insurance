package Model.DB;

import java.sql.ResultSet;

import Model.Contract.Contract;

public class FailContractDao  extends Dao {
	public FailContractDao() {
		super.connect();
	}

	public boolean create() {
		String query = "";

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
	
	public ResultSet retriveFailContract(Contract contract) {
		String query = "select * from failcontract where customerName =\""+contract.getCustomerName()+"\" and customerPhoneNum = \""+contract.getPhoneNum()+"\""
				+ " and insuranceID =\""+contract.getInsuranceID()+"\"";
		System.out.println(query);
		return super.retrive(query);
	}
	
	public ResultSet retriveFailContractID(Contract contract) {
		String query = "select * from failcontract where contractID =\""+contract.getContractID()+"\"";
		return super.retrive(query);
	}

	public boolean deleteFailContract(Contract contract) {
		String query="delete from failcontract where contractID=\""+contract.getContractID()+"\"";
		return super.delete(query);
	}
}
