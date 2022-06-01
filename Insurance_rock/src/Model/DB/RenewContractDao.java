package Model.DB;

import java.sql.ResultSet;

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
