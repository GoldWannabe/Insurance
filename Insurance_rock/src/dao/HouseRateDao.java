package dao;

import java.sql.ResultSet;

import Insurance.HouseInsurance;

public class HouseRateDao  extends Dao {
	public HouseRateDao() {
		super.connect();
	}

	public boolean create(HouseInsurance houseInsurance) {
		double[] rate = houseInsurance.getStandardRate();
		String query = "insert into houseRate(insuranceID, insurancerank, housePremiumRate) values (\""+houseInsurance.getInsuranceID()+"\","+1+","+rate[0]+"),(\""+houseInsurance.getInsuranceID()+"\","+2+","+rate[1]+"),(\""+houseInsurance.getInsuranceID()+"\","+3+","+rate[2]+");";

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
