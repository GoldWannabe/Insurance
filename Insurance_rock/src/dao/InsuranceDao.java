package dao;

import java.sql.ResultSet;

<<<<<<< HEAD
public class InsuranceDao  extends Dao {
	public InsuranceDao() {
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
=======
import Insurance.Insurance;

public class InsuranceDao  extends Dao {
	public InsuranceDao() {
		super.connect();
	}

	public boolean create(Insurance insurance) {
		String query = "insert into Insurance(insuranceID, insuranceName, insuranceType, StandradFee, specialContract , longTerm, applyCondition, compensateCondition, explanation, releaseDate) values (\""+insurance.getInsuranceID()+"\",\""+ insurance.getInsuranceName()+"\",\""+ insurance.getInsuranceType().toString()+"\","+ insurance.getStandardFee()+",\""+ insurance.getSpecialContract() +"\","+ insurance.isLongTerm()+",\""+ insurance.getApplyCondition()+"\",\""+ insurance.getCompensateCondition()+"\",\""+ insurance.getExplanation()+"\",\""+insurance.getReleaseDate().toString()+"\");";

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
		String query = "select * from Insurance where insuranceName=\""+insuranceName+"\";";

		System.out.println(query);
		return super.retrive(query);
>>>>>>> refs/remotes/origin/main
	}
}
