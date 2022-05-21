package Insurance;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

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
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		this.insuranceID = UUID.randomUUID().toString();
		System.out.println("이름을 입력해주세요.");
		this.insuranceName = scanner.next();
		System.out.println("특약을 입력해주세요.");
		this.specialContract = scanner.next();
		System.out.println("가입조건을 입력해주세요.");
		this.applyCondition = scanner.next();
		System.out.println("보상 조건을 입력해주세요.");
		this.compensateCondition = scanner.next();
		System.out.println("설명을 입력해주세요.");
		this.explanation = scanner.next();

//		while(checkName(this.insuranceName)){
//		System.out.println("이름이 중복 됩니다. 다시 입력해주세요.");
//			this.insuranceName = scanner.next();
//		}; 검색을 위함

	}

	public boolean examine() {
		return true;
	}

	public void permitInsurance() {
		// DB 저장
	}

	public void register() {

		try {
			File file = new File(".//DB//registerInsurance.txt");
			FileWriter fileWriter = new FileWriter(file, true);
			fileWriter.write(this.insuranceID + " " + this.insuranceName + " " + this.insuranceType + " "
					+ this.standardFee + " " + this.specialContract + " " + this.longTerm + " " + this.applyCondition
					+ " " + this.compensateCondition + " " + this.explanation + "\n");
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			System.out.println("DB 접근 오류: 정보 접근에 실패하였습니다. 해당 문제가 계속 발생할 시에는 사내 시스템 관리팀(1234-5678)에게 문의 주시기 바랍니다.");
			e.printStackTrace();
		}
	}

	public void saveTempInsurance() {
		try {
			File file = new File(".//DB//tempInsurance.txt");
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write("1"+"\n" + this.insuranceID + "\n" + this.insuranceName + "\n" + this.insuranceType + "\n"
					+ this.standardFee + "\n" + this.specialContract + "\n" + this.longTerm + "\n" + this.applyCondition
					+ "\n" + this.compensateCondition + " " + this.explanation + "\n");
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			System.out.println("DB 접근 오류: 정보 접근에 실패하였습니다. 해당 문제가 계속 발생할 시에는 사내 시스템 관리팀(1234-5678)에게 문의 주시기 바랍니다.");
			e.printStackTrace();
		}
		
	}

	public void sell() {

	}

	public abstract void measureStandardFee();

	public abstract void verifyPremium();

}// end Insurance