package Model.DB;

import java.sql.ResultSet;

import Model.Insurance.GeneralInsurance;

public class GeneralRateDao  extends Dao {
	public GeneralRateDao() {
		super.connect();
	}

	public boolean create(GeneralInsurance generalInsurance) {
		double[] rate = generalInsurance.getPremiumRate();
		String query = "insert into generalRate(insuranceID, insurancerank, generalPremiumRate) values (\""+generalInsurance.getInsuranceID()+"\","+1+","+rate[0]+"),(\""+generalInsurance.getInsuranceID()+"\","+2+","+rate[1]+"),(\""+generalInsurance.getInsuranceID()+"\","+3+","+rate[2]+");";

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
		String query = "select generalPremiumRate from generalRate where insuranceID=\""+insuranceID+"\";";

		return super.retrive(query);
	}
}
