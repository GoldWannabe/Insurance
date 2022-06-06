package Model.DB;

import java.sql.ResultSet;

import Model.Insurance.Insurance;
import Model.Insurance.Insurance.EInsurance;

public class RegisterInsuranceDao  extends Dao {
	public RegisterInsuranceDao() {
		super.connect();
	}

	public boolean create(Insurance insurance) {
		String query = "insert into RegisterInsurance(insuranceID, insuranceName, insuranceType, StandradFee, specialContract , longTerm, applyCondition, compensateCondition, explanation) values (\""+insurance.getInsuranceID()+"\",\""+ insurance.getInsuranceName()+"\",\""+ insurance.getInsuranceType().toString()+"\","+ insurance.getStandardFee()+",\""+ insurance.getSpecialContract() +"\","+ insurance.isLongTerm()+",\""+ insurance.getApplyCondition()+"\",\""+ insurance.getCompensateCondition()+"\",\""+ insurance.getExplanation()+"\");";

		return super.create(query);
	}

	public ResultSet retrive(EInsurance insuranceType) {
		String query = "select * from RegisterInsurance;";

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

	public ResultSet retriveName(String insuranceName) {
		String query = "select * from RegisterInsurance where insuranceName=\""+insuranceName+"\";";

		return super.retrive(query);
	}

	public boolean deleteInsurance(String insuranceID) {
		String query = "delete from RegisterInsurance where insuranceID=\""+insuranceID+"\";";

		return super.delete(query);		
	}
}
