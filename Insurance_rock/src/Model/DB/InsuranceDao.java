package Model.DB;

import java.sql.ResultSet;

import Model.Insurance.Insurance;
import Model.Insurance.Insurance.EInsurance;

public class InsuranceDao extends Dao {
	public InsuranceDao() {
		super.connect();
	}

	public boolean create(Insurance insurance) {
		String query = "insert into Insurance(insuranceID, insuranceName, insuranceType, StandradFee, specialContract , longTerm, applyCondition, compensateCondition, explanation, releaseDate) values (\""
				+ insurance.getInsuranceID() + "\",\"" + insurance.getInsuranceName() + "\",\""
				+ insurance.getInsuranceType().toString() + "\"," + insurance.getStandardFee() + ",\""
				+ insurance.getSpecialContract() + "\"," + insurance.isLongTerm() + ",\""
				+ insurance.getApplyCondition() + "\",\"" + insurance.getCompensateCondition() + "\",\""
				+ insurance.getExplanation() + "\",\"" + insurance.getReleaseDate().toString() + "\");";

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

	public ResultSet retriveName(String insuranceName) {
		String query = "select * from Insurance where insuranceName=\"" + insuranceName + "\";";

		System.out.println(query);
		return super.retrive(query);
	}

	public ResultSet retrive(EInsurance insuranceType) {
		String query = "select * from Insurance where insuranceType =\"" + insuranceType.toString() + "\";";
		return super.retrive(query);
	}

	public ResultSet retriveID(String insuranceID) {
		String query = "select * from Insurance where insuranceID =\"" + insuranceID + "\";";
		return super.retrive(query);
	}

	public ResultSet retriveType(String insuranceName) {
		String query = "select insuranceType from Insurance where insuranceName=\"" + insuranceName + "\";";

		System.out.println(query);
		return super.retrive(query);
	}
}
