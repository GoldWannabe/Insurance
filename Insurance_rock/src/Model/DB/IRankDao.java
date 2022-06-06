package Model.DB;

import java.sql.ResultSet;

import Model.Customer.Rank;

public class IRankDao  extends Dao {
	public IRankDao() {
		super.connect();
	}

	public boolean create() {
		String query = "";

		System.out.println(query);
		return super.create(query);
	}
	
	public boolean create(Rank rank) {
		String query ="insert into IRank(RankID, material, fireFacilities, height, scale, surroundingFacilities, purpose) values (\""
				+rank.getRankID()+"\", \""+rank.getMaterial()+"\", \""+rank.getFireFacilities()+"\", "+rank.isHeight()+", \""+rank.getScale()+"\", \""
				+rank.getSurroundingFacilities()+"\", \""+rank.getPurpose()+"\");";
		
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

	public ResultSet retriveByID(String rankID) {
		String query = "SELECT * FROM IRank where RankID =\""+rankID+"\";";

		System.out.println(query);
		return super.retrive(query);
	}
	public ResultSet retriveID(String rankID) {
		String query = "SELECT RankID FROM IRank where RankID =\""+rankID+"\";";

		System.out.println(query);
		return super.retrive(query);
	}

	public boolean deleteRank(String rankID) {
		StringBuilder stringBuilder = new StringBuilder();

		String query = stringBuilder.append("delete from irank")
				.append(" where RankID = \'"+ rankID+"\'")
				.toString();

		System.out.println(query);
		return super.delete(query);
		
	}



}
