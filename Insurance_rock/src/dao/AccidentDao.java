package dao;

import java.sql.ResultSet;
import java.time.LocalDate;

import Accident.Accident;

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
		// TODO Auto-generated method stub
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


	public boolean updatedate(Accident accident, LocalDate accidentdate) {
		StringBuilder stringBuilder = new StringBuilder();

		String query = stringBuilder.append("update accident set accidentDate =")
				.append("\'"+accidentdate + "\'")
				.append("where accidentID  = \'" + accident.getID() + "\'")
				.toString();

		System.out.println(query);
		return super.update(query);
		
	}


	public boolean updatecontent(Accident accident, String content) {
		StringBuilder stringBuilder = new StringBuilder();

		String query = stringBuilder.append("update accident set content  =")
				.append("\'"+content + "\'")
				.append("where accidentID  = \'" + accident.getID() + "\'")
				.toString();

		System.out.println(query);
		return super.update(query);
	}


	public boolean updatetotal(Accident accident, int totalCost) {
		StringBuilder stringBuilder = new StringBuilder();

		String query = stringBuilder.append("update accident set totalCost  =")
				.append("\'"+totalCost + "\'")
				.append("where accidentID  = \'" + accident.getID() + "\'")
				.toString();

		System.out.println(query);
		return super.update(query);
	}


	public boolean updateDamage(Accident accident, int damagePer) {
		StringBuilder stringBuilder = new StringBuilder();

		String query = stringBuilder.append("update accident set damagePer =")
				.append("\'"+damagePer  + "\'")
				.append("where accidentID = \'" + accident.getID() + "\'")
				.toString();

		System.out.println(query);
		return super.update(query);
	}


	public boolean updateKind(Accident accident, String kindOfCost) {
		StringBuilder stringBuilder = new StringBuilder();

		String query = stringBuilder.append("update accident set kindOfCost  =")
				.append("\'"+kindOfCost+ "\'")
				.append("where accidentID = \'" + accident.getID() + "\'")
				.toString();

		System.out.println(query);
		return super.update(query);
	}


	public boolean updateLiablityCost(Accident accident, int liablityCost) {
		StringBuilder stringBuilder = new StringBuilder();

		String query = stringBuilder.append("update accident set liablityCost =")
				.append("\'"+liablityCost+ "\'")
				.append("where accidentID = \'" + accident.getID() + "\'")
				.toString();

		System.out.println(query);
		return super.update(query);
	}


	public boolean updateLiablityRate(Accident accident, int liablityRate) {
		StringBuilder stringBuilder = new StringBuilder();

		String query = stringBuilder.append("update accident set liablityRate =")
				.append("\'"+liablityRate+ "\'")
				.append("where accidentID = \'" + accident.getID() + "\'")
				.toString();

		System.out.println(query);
		return super.update(query);
	}
}
