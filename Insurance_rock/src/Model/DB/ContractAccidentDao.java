package Model.DB;

import java.sql.ResultSet;

import Model.Contract.Contract;

public class ContractAccidentDao  extends Dao {
	public ContractAccidentDao() {
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

	public boolean createContractAccident(Contract contract, String accidentID) {
		StringBuilder stringBuilder = new StringBuilder();

		String query = stringBuilder.append("insert into contractaccident values(")
				.append("\'" + contract.getContractID() + "\',")
				.append("\'" + accidentID + "\')")
				.toString();

		System.out.println(query);
		return super.create(query);
	}
}
