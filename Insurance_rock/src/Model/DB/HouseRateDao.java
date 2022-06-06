package Model.DB;

import java.sql.ResultSet;

import Model.Insurance.HouseInsurance;

public class HouseRateDao  extends Dao {
	public HouseRateDao() {
		super.connect();
	}

	public boolean create(HouseInsurance houseInsurance) {
		double[] rate = houseInsurance.getPremiumRate();
		String query = "insert into houseRate(insuranceID, insurancerank, housePremiumRate) values (\""+houseInsurance.getInsuranceID()+"\","+1+","+rate[0]+"),(\""+houseInsurance.getInsuranceID()+"\","+2+","+rate[1]+"),(\""+houseInsurance.getInsuranceID()+"\","+3+","+rate[2]+");";

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

	public ResultSet retriveRate(String insuranceID) {
		String query = "select housePremiumRate from houseRate where insuranceID=\""+insuranceID+"\";";

		return super.retrive(query);
	}
}
