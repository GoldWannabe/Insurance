package dao;

import java.sql.ResultSet;

<<<<<<< HEAD
public class RegisterInsuranceDao  extends Dao {
	public RegisterInsuranceDao() {
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
import Insurance.Insurance.EInsurance;

public class RegisterInsuranceDao  extends Dao {
	public RegisterInsuranceDao() {
		super.connect();
	}

	public boolean create(Insurance insurance) {
		String query = "insert into RegisterInsurance(insuranceID, insuranceName, insuranceType, StandradFee, specialContract , longTerm, applyCondition, compensateCondition, explanation) values (\""+insurance.getInsuranceID()+"\",\""+ insurance.getInsuranceName()+"\",\""+ insurance.getInsuranceType().toString()+"\","+ insurance.getStandardFee()+",\""+ insurance.getSpecialContract() +"\","+ insurance.isLongTerm()+",\""+ insurance.getApplyCondition()+"\",\""+ insurance.getCompensateCondition()+"\",\""+ insurance.getExplanation()+"\");";

		System.out.println(query);
		return super.create(query);
	}

	public ResultSet retrive(EInsurance insuranceType) {
		String query = "select * from RegisterInsurance where insuranceType=\""+insuranceType.toString()+"\";";

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
		String query = "select * from RegisterInsurance where insuranceName=\""+insuranceName+"\";";

		System.out.println(query);
		return super.retrive(query);
	}

	public boolean deleteInsurance(String insuranceID) {
		String query = "delete from RegisterInsurance where insuranceID=\""+insuranceID+"\";";

		System.out.println(query);
		return super.delete(query);		
>>>>>>> refs/remotes/origin/main
	}
}
