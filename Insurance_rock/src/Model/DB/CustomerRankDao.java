package Model.DB;

import java.sql.ResultSet;

import Model.Contract.Contract;
import Model.Customer.Customer;
import Model.Customer.Rank;

public class CustomerRankDao  extends Dao {
	public CustomerRankDao() {
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

	public ResultSet retriveID(String customerID) {
		String query = "select * from customerRank where customerID = \""+customerID+ "\";";

		System.out.println(query);
		return super.retrive(query);
	}

	public boolean updateRank(String afterRankID, String beforeRankID) {
		StringBuilder stringBuilder = new StringBuilder();

		String query = stringBuilder.append("update customerRank set RankID =")
				.append("\'"+afterRankID + "\'")
				.append("where RankID  = \'" + beforeRankID + "\'")
				.toString();

		System.out.println(query);
		return super.update(query);
	}

	
	public boolean delete(String customerID, String rankID) {
		String query = "delete from customerRank where customerID=\""+customerID+"\" and RankID=\""+rankID+"\";";

		System.out.println(query);
		return super.delete(query);		
	}
}
