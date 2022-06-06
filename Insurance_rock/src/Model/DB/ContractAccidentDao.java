package Model.DB;

import java.sql.ResultSet;

import Model.Contract.Contract;

public class ContractAccidentDao  extends Dao {
	public ContractAccidentDao() {
		super.connect();
	}

	public boolean create() {
		String query = "";

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

	public boolean createContractAccident(Contract contract, String accidentID) {
		StringBuilder stringBuilder = new StringBuilder();

		String query = stringBuilder.append("insert into contractaccident values(")
				.append("\'" + contract.getContractID() + "\',")
				.append("\'" + accidentID + "\')")
				.toString();

		return super.create(query);
	}

	public ResultSet retriveCount(String contractID) {
		String query = "select count(*) as accidentNum from contractaccident where contractID = \""+contractID+"\"";
		return super.retrive(query);
	}
}
