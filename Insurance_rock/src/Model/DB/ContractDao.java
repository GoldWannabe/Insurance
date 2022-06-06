package Model.DB;

import java.sql.ResultSet;

import Model.Contract.Contract;

public class ContractDao  extends Dao {
	public ContractDao() {
		super.connect();
	}

	public boolean create(Contract contract) {
		String query = "insert into contract(contractID, customerID, customerName, customerPhoneNum, insuranceID, insuranceName, paymentCycle, insuranceFee, unpaidFee, securityFee, provisionFee, startDate, endDate) values "
				+ "(\""+contract.getContractID()+"\", \""+contract.getCustomerID()+"\", \""+contract.getCustomerName()+"\", \""+contract.getPhoneNum()+"\", \""+contract.getInsuranceID()+"\", \""+contract.getInsuranceName()+"\", "+contract.getPaymentCycle()+", "+contract.getInsuranceFee()+", "+contract.getUnpaidFee()+", "+contract.getSecurityFee()+", "+contract.getProvisionFee()+", \""+contract.getStartDate()+"\", \""+contract.getEndDate()+"\");";


		System.out.println(query);
		return super.create(query);
	}

	public ResultSet retrive() {
		String query = "SELECT * FROM Contract;";

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
		String query = stringBuilder.append("select * From Contract ")
				.append("Where customerName = \'"+customerName+"\' AND ") 
				.append("customerPhoneNum = \'" + phoneNum +"\'" )
				.toString();

		System.out.println(query);
		return super.retrive(query);
	}
	
	public ResultSet retriveFailContractID(Contract contract) {
		String query = "select * from failcontract where contractID =\""+contract.getContractID()+"\"";
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
	
	public ResultSet retriveFailContract(Contract contract) {
		String query = "select * from failcontract where customerName =\""+contract.getCustomerName()+"\" and customerPhoneNum = \""+contract.getPhoneNum()+"\"";
		System.out.println(query);
		return super.retrive(query);
	}

	public boolean updateProvisionFee(Contract contract, int lablityCost) {
		StringBuilder stringBuilder = new StringBuilder();

		String query = stringBuilder.append("update contract set provisionFee =")
				.append(+contract.getProvisionFee() +"+")
				.append(lablityCost)
				.append(" where contractID = \'" + contract.getContractID() + "\'")
				.toString();
		

		System.out.println(query);
		return super.update(query);
	}

	public ResultSet resultAccidentHistory(Contract contract) {
		StringBuilder stringBuilder = new StringBuilder();

		String query = stringBuilder.append("select * From contractAccident Where contractID = ")
				.append("\'" + contract.getContractID()+ "\'" )
				.toString();

		System.out.println(query);
		return super.retrive(query);
	}

	public boolean deleteContract(Contract contract) {
		StringBuilder stringBuilder = new StringBuilder();

		String query = stringBuilder.append("delete from contract Where contractID = ")
				.append("\'" + contract.getContractID()+ "\'" )
				.toString();

		System.out.println(query);
		return super.delete(query);
	}


	public boolean updateUnpaidFee(int newUnpaidFee, String contractID) {
		String query = "update contract set unpaidFee = " + "'" + newUnpaidFee + "'" + "where contractID = " + "'" + contractID + "';";

		System.out.println(query);
		return super.update(query);
	}
	public ResultSet retriveByID(String contractID) {
		String query = "select * from contract where contractID = \""+contractID+"\"";
		System.out.println(query);
		return super.retrive(query);
	}

	public boolean updateRenew(Contract contract) {
		String query = "update contract set paymentCycle=" + contract.getPaymentCycle() +", insuranceFee ="+ contract.getInsuranceFee()+", securityFee ="+ contract.getSecurityFee()+", endDate =\""+ contract.getEndDate()+"\" where contractID= \""
				+ contract.getContractID() + "\"";
		return super.update(query);
	}

}
