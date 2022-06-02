package View.Team;

import java.util.Arrays;

import Model.Insurance.Insurance;

public class FinancialDirectorTui {

	public void showNoRegisterInsurance() {
		System.out.println("심사할 보험이 없습니다.");
		System.out.println("메인화면으로 돌아갑니다.");
	}

	public void showInsuranceColumn() {
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.printf("%10s %10s %10s", "보험명", "보험종류", "기준보험료");
		System.out.println();
		System.out.println("---------------------------------------------------------------------------------------");

	}

	public void showInsurance(Insurance insurance) {
		System.out.format("%10s %12s %12d", insurance.getInsuranceName(), insurance.getInsuranceType().toString(),
				insurance.getStandardFee());
		System.out.println();

	}

	public void showEnterInsuranceName() {
		System.out.println("심사할 보험명을 입력해 주세요.");
	}

	public void ShowNoInsuranceName() {
		System.out.println("해당하는 이름의 보험이 존재하지 않습니다. 다시 입력해주세요.");
	}

	public void showInsuranceAttribute(Insurance insurance) {
		System.out.println("보험명: " + insurance.getInsuranceName() + "\n" + "보험종류: "
				+ insurance.getInsuranceType() + "\n" + "기준보험료: " + insurance.getStandardFee() + "\n" + "특약: "
				+ insurance.getSpecialContract() + "\n" + "장기여부: " + insurance.isLongTerm() + "\n" + "가입조건: "
				+ insurance.getApplyCondition() + "\n" + "보상조건: " + insurance.getCompensateCondition() + "\n"
				+ "설명: " + insurance.getExplanation() + "\n" + "요율: [1등급, 2등급, 3등급]"
				+ Arrays.toString(insurance.getPremiumRate()));
	}

	public void showSelectPermit() {
		System.out.println("심사 결과를 입력해주세요. (해당하는 번호 또는 단어를 적어주세요.)");
		System.out.println("1. 승인 2. 비승인 3. 보류");
	}

	public void showCompletePermit() {
		System.out.println("해당 보험이 승인 되었습니다.");		
	}

	public void showNoCompletePermit() {
		System.out.println("해당 보험이 비승인 되었습니다.");
	}

	public void showPostponePermit(String insuranceName) {
		System.out.println( insuranceName+ "의 보험 심사가 보류되었습니다.");
	}

}
