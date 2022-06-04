package Model.DB;

import java.sql.ResultSet;

import Model.Accident.Accident;

public class AccidentDao extends Dao {
	public AccidentDao() {
		super.connect();
	}


	public ResultSet retrive() {
		String query = "SELECT * FROM Accident";

		System.out.println(query);
		return super.retrive(query);
	}


	public boolean delete() {
		String query = "";

		System.out.println(query);
		return super.delete(query);
	}




	public boolean creat(Accident accident) {
		StringBuilder stringBuilder = new StringBuilder();

		String query = stringBuilder.append("insert into Accident values(")
				.append("\'" + accident.getID() + "\',")
				.append("\'" + accident.getContractID() + "\',")
				.append("\'" + accident.getCustomerID() + "\',")
				.append("\'" + accident.getCustomerName() + "\',")
				.append("\'" + accident.getPhoneNum() + "\',")
				.append("\'" + accident.getAccidentDate() + "\',")
				.append("\'" + accident.getContent() + "\',")
				.append("\'" + accident.getTotalCost() + "\',")
				.append("\'" + accident.getDamagePer() + "\',")
				.append("\'" + accident.getKindOfCost() + "\',")
				.append(  accident.isPayCompleted() + ",")
				.append("\'" + accident.getLiablityRate() + "\',")
				.append("\'" + accident.getLiablityCost() + "\')")
				.toString();

		System.out.println(query);
		return super.create(query);
	}


	public ResultSet retriveaccident(Accident accident) {
		StringBuilder stringBuilder = new StringBuilder();

		String query = stringBuilder.append("select * from accident Where accidentDate =")
				.append(" \'" + accident.getAccidentDate() + "\' AND ")
				.append("customerName = \'" + accident.getCustomerName() + "\'")
				.toString();

		System.out.println(query);
		return super.retrive(query);
	}


	public boolean updatedate(Accident accident) {
		StringBuilder stringBuilder = new StringBuilder();

		String query = stringBuilder.append("update accident set accidentDate =")
				.append("\'"+accident.getAccidentDate() + "\'")
				.append("where accidentID  = \'" + accident.getID() + "\'")
				.toString();

		System.out.println(query);
		return super.update(query);
		
	}


	public boolean updatecontent(Accident accident) {
		StringBuilder stringBuilder = new StringBuilder();

		String query = stringBuilder.append("update accident set content  =")
				.append("\'"+accident.getContent() + "\'")
				.append("where accidentID  = \'" + accident.getID() + "\'")
				.toString();

		System.out.println(query);
		return super.update(query);
	}


	public boolean updatetotal(Accident accident) {
		StringBuilder stringBuilder = new StringBuilder();

		String query = stringBuilder.append("update accident set totalCost  =")
				.append("\'"+accident.getTotalCost() + "\'")
				.append("where accidentID  = \'" + accident.getID() + "\'")
				.toString();

		System.out.println(query);
		return super.update(query);
	}


	public boolean updateDamage(Accident accident) {
		StringBuilder stringBuilder = new StringBuilder();

		String query = stringBuilder.append("update accident set damagePer =")
				.append("\'"+accident.getDamagePer()  + "\'")
				.append("where accidentID = \'" + accident.getID() + "\'")
				.toString();

		System.out.println(query);
		return super.update(query);
	}


	public boolean updateKind(Accident accident) {
		StringBuilder stringBuilder = new StringBuilder();

		String query = stringBuilder.append("update accident set kindOfCost  =")
				.append("\'"+accident.getKindOfCost()+ "\'")
				.append("where accidentID = \'" + accident.getID() + "\'")
				.toString();

		System.out.println(query);
		return super.update(query);
	}


	public boolean updateLiablityCost(Accident accident) {
		StringBuilder stringBuilder = new StringBuilder();

		String query = stringBuilder.append("update accident set liablityCost =")
				.append("\'"+accident.getLiablityCost()+ "\'")
				.append("where accidentID = \'" + accident.getID() + "\'")
				.toString();

		System.out.println(query);
		return super.update(query);
	}


	public boolean updateLiablityRate(Accident accident) {
		StringBuilder stringBuilder = new StringBuilder();

		String query = stringBuilder.append("update accident set liablityRate =")
				.append("\'"+accident.getLiablityRate()+ "\'")
				.append("where accidentID = \'" + accident.getID() + "\'")
				.toString();

		System.out.println(query);
		return super.update(query);
	}


	public boolean updatePaycompleted(Accident accident) {
		StringBuilder stringBuilder = new StringBuilder();

		String query = stringBuilder.append("update accident set payCompleted = ")
				.append(accident.isPayCompleted())
				.append(" where accidentID = \'" + accident.getID() + "\'")
				.toString();

		System.out.println(query);
		return super.update(query);
		
	}
}
