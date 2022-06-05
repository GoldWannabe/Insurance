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

	public boolean delete() {
		String query = "";

		System.out.println(query);
		return super.delete(query);
	}

	public ResultSet retriveByID(String rankID) {
		String query = "SELECT * FROM IRank where RankID =\""+rankID+"\";";

		System.out.println(query);
		return super.retrive(query);
	}



}
