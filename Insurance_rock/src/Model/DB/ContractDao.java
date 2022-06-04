package Model.DB;

import java.sql.ResultSet;

import Model.Contract.Contract;

public class ContractDao  extends Dao {
	public ContractDao() {
		super.connect();
	}

	public boolean create(Contract contract) {
		String query = "";

		System.out.println(query);
		return super.create(query);
	}

	public ResultSet retrive() {
		String query = "SELECT * FROM Contract";

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

	public ResultSet select() {
		String query = "";

		System.out.println(query);
		return super.retrive(query);
	}

	public ResultSet retrivecontract(String customerName, String phoneNum) {
		StringBuilder stringBuilder = new StringBuilder();
		String query = stringBuilder.append("select contractID, customerID, customerName, customerPhoneNum, insuranceID, insuranceName, paymentCycle, insuranceFee, unpaidFee, securityFee, provisionFee, startDate, endDate From Contract ")
				.append("Where customerName = \'"+customerName+"\' AND ") 
				.append("customerPhoneNum = \'" + phoneNum +"\'" )
				.toString();

		System.out.println(query);
		return super.retrive(query);
	}

	public ResultSet retrivelongtermFee(Contract contract) {
		StringBuilder stringBuilder = new StringBuilder();

		String query = stringBuilder.append("select  insuranceID, insuranceName, securityFee, provisionFee, startDate, endDate  from contract Where contractID = ")
				.append("\'" + contract.getContractID()+ "\'" )
				.toString();
		

		System.out.println(query);
		return super.retrive(query);
		
	}

	public boolean updateProvisionFee(Contract contract) {
		StringBuilder stringBuilder = new StringBuilder();

		String query = stringBuilder.append("update contract set provisionFee =")
				.append("\'"+contract.getProvisionFee()+ "\'")
				.append("where contractID = \'" + contract.getContractID() + "\'")
				.toString();
		

		System.out.println(query);
		return super.update(query);
	}
}
