package Model.DB;

import java.sql.ResultSet;

import Model.Contract.Contract;

public class RenewContractDao extends Dao {
	public RenewContractDao() {
		super.connect();
	}

	public boolean create() {
		String query = "";

		System.out.println(query);
		return super.create(query);
	}

	public ResultSet retrive() {
		String query = "select * from renewContract";

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

	public boolean createBesidesConstract(Contract contract) {
		StringBuilder stringBuilder = new StringBuilder();

		String query = stringBuilder.append("insert into renewContract values(")
				.append("\'" + contract.getContractID() + "\',").append("\'" + contract.getCustomerID() + "\',")
				.append("\'" + contract.getInsuranceID() + "\',").append("\'" + contract.getPaymentCycle() + "\',")
				.append("\'" + contract.getInsuranceFee() + "\',").append("\'" + contract.getSecurityFee() + "\',")
				.append("\'" + contract.getPaymentCycle() + "\')").toString();

		System.out.println(query);
		return super.create(query);
	}

	public ResultSet retriveRenewContract(Contract contract) {
		StringBuilder stringBuilder = new StringBuilder();

		String query = stringBuilder.append("select * from renewcontract Where contractID =")
				.append(" \'" + contract.getContractID() + "\'").toString();

		System.out.println(query);
		return super.retrive(query);
	}

	public boolean deleteByID(String contractID) {
		String query = "delete from renewContract where contractID=\""+contractID+"\";";

		System.out.println(query);
		return super.delete(query);
	}
}
