package Model.Insurance;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.UUID;

import Model.DB.InsuranceDao;
import Model.DB.RegisterInsuranceDao;

public abstract class Insurance {
	private String insuranceID;

	public enum EInsurance {
		general, house
	};

	private EInsurance insuranceType;
	private boolean longTerm;
	private String insuranceName;
	private String specialContract;
	private String applyCondition;
	private String compensateCondition;
	private String explanation;
	private int standardFee;
	private LocalDate releaseDate;

	public RegisterInsuranceDao registerInsuranceDao;
	public InsuranceDao insuranceDao;

	public Insurance(String inputString, EInsurance insuranceType) {
		StringTokenizer stringTokenizer = new StringTokenizer(inputString);
		this.insuranceID = stringTokenizer.nextToken();
		this.insuranceName = stringTokenizer.nextToken();

		// nextToken
		String type = stringTokenizer.nextToken();
		// define in subClass
		this.insuranceType = insuranceType;

		this.standardFee = Integer.parseInt(stringTokenizer.nextToken());
		this.specialContract = stringTokenizer.nextToken();
		this.longTerm = Boolean.parseBoolean(stringTokenizer.nextToken());
		this.applyCondition = stringTokenizer.nextToken();
		this.compensateCondition = stringTokenizer.nextToken();
		this.explanation = stringTokenizer.nextToken();
	}

	public Insurance(EInsurance insuranceType) {
		this.insuranceType = insuranceType;
	}

	public Insurance(EInsurance insuranceType, boolean longTerm) {
		this.insuranceType = insuranceType;
		this.longTerm = longTerm;
	}

	public String getInsuranceID() {
		return insuranceID;
	}

	public void setInsuranceID(String insuranceID) {
		this.insuranceID = insuranceID;
	}

	public String getInsuranceName() {
		return insuranceName;
	}

	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}

	public String getApplyCondition() {
		return applyCondition;
	}

	public void setApplyCondition(String applyCondition) {
		this.applyCondition = applyCondition;
	}

	public String getCompensateCondition() {
		return compensateCondition;
	}

	public void setCompensateCondition(String compensateCondition) {
		this.compensateCondition = compensateCondition;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public boolean isLongTerm() {
		return longTerm;
	}

	public void setLongTerm(boolean longTerm) {
		this.longTerm = longTerm;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getSpecialContract() {
		return specialContract;
	}

	public void setSpecialContract(String specialContract) {
		this.specialContract = specialContract;
	}

	public int getStandardFee() {
		return standardFee;
	}

	public void setStandardFee(int standardFee) {
		this.standardFee = standardFee;
	}

	public EInsurance getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(EInsurance insuranceType) {
		this.insuranceType = insuranceType;
	}

	public void finalize() throws Throwable {

	}

	public boolean apply() {
		return true;
	}

	public void design() {
		this.insuranceDao = new InsuranceDao();
		this.registerInsuranceDao = new RegisterInsuranceDao();
		this.insuranceID = UUID.randomUUID().toString();

	}

	public boolean checkName() {
		try {
			if (this.insuranceDao.retriveName(this.insuranceName).next()
					|| (this.registerInsuranceDao.retriveName(this.insuranceName).next()))
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public boolean examine() {
		return true;
	}

	public boolean permitInsurance() {
		this.insuranceDao = new InsuranceDao();
		this.registerInsuranceDao = new RegisterInsuranceDao();
		this.releaseDate = LocalDate.now();
		if(this.registerInsuranceDao.deleteInsurance(this.insuranceID)) {
			return this.insuranceDao.create(this);
		}
		
		return false;
		// DB 저장
	}

	public boolean notPermitInsurance() {
		this.registerInsuranceDao = new RegisterInsuranceDao();
		return this.registerInsuranceDao.deleteInsurance(this.insuranceID);

	}

	public boolean register() {
		if (this.registerInsuranceDao.create(this))
			return true;

		return false;

	}

	public void saveTempInsurance() {
		try {
			File file = new File(".//DB//tempInsurance.txt");
			FileWriter fileWriter = new FileWriter(file);
			double[] tempRate = this.getStandardRate();
			fileWriter.write("1" + "\n" + this.insuranceID + "\n" + this.insuranceName + "\n" + this.insuranceType
					+ "\n" + this.standardFee + "\n" + this.specialContract + "\n" + this.longTerm + "\n"
					+ this.applyCondition + "\n" + this.compensateCondition + "\n" + this.explanation + "\n"
					+ tempRate[0] + "\n" + tempRate[1] + "\n" + tempRate[2] + "\n");
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			System.out.println("DB 접근 오류: 정보 접근에 실패하였습니다. 해당 문제가 계속 발생할 시에는 사내 시스템 관리팀(1234-5678)에게 문의 주시기 바랍니다.");
			e.printStackTrace();
		}

	}

	public void sell() {

	}

	public ResultSet getRegisterInsurance() {
		this.registerInsuranceDao = new RegisterInsuranceDao();
		return this.registerInsuranceDao.retrive(this.insuranceType);
	}
	
	public ResultSet getInsurance() {
		this.insuranceDao = new InsuranceDao();
		return this.insuranceDao.retrive(this.insuranceType);
	}

	
	public abstract void measureStandardFee();

	public abstract void verifyPremium();

	public abstract void setStandardRate(double[] rate);

	public abstract double[] getStandardRate();

	public abstract boolean registerRate();

	

	public abstract void setRate();

	public abstract boolean notPermitRate();

	public abstract boolean permitRate();


}// end Insurance