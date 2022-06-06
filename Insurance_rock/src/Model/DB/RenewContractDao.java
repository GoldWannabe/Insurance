package Model.DB;

import java.sql.ResultSet;

import Model.Contract.Contract;

public class RenewContractDao extends Dao {
	public RenewContractDao() {
		super.connect();
	}

	public boolean create() {
		String query = "";

		return super.create(query);
	}

	public ResultSet retrive() {
		String query = "select * from renewContract";

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

	public boolean createBesidesConstract(Contract contract) {
		StringBuilder stringBuilder = new StringBuilder();

		String query = stringBuilder.append("insert into renewContract values(")
				.append("\'" + contract.getContractID() + "\',").append("\'" + contract.getCustomerID() + "\',")
				.append("\'" + contract.getInsuranceID() + "\',").append("\'" + contract.getPaymentCycle() + "\',")
				.append("\'" + contract.getInsuranceFee() + "\',").append("\'" + contract.getSecurityFee() + "\',")
				.append("\'" + contract.getPaymentCycle() + "\')").toString();

		return super.create(query);
	}

	public ResultSet retriveRenewContract(Contract contract) {
		StringBuilder stringBuilder = new StringBuilder();

		String query = stringBuilder.append("select * from renewcontract Where contractID =")
				.append(" \'" + contract.getContractID() + "\'").toString();

		return super.retrive(query);
	}

	public boolean deleteByID(String contractID) {
		String query = "delete from renewContract where contractID=\""+contractID+"\";";

		return super.delete(query);
	}
}
