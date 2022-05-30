package dao;

import java.sql.ResultSet;

import Contract.Contract;

public class ContractDao  extends Dao {
	public ContractDao() {
		super.connect();
	}
	
	public void create(Contract contract) {
		
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

}
