package View.Team;

import java.util.Arrays;
import Model.Insurance.Insurance;

public class ContractTeamTui {

	public void showStart() {
		System.out.println("장기여부와 보험 종류를 선택해 주세요.");
	}

	public void showSelectLongterm() {
		System.out.println("장기여부를 선택해주세요.");
		System.out.println("1. 장기 2. 단기 0. 취소");

	}

	public void showSelectInsuranceType() {
		System.out.println("보험 종료를 선택해주세요.");
		System.out.println("1. 일반보험 2. 주택보험 0. 취소");
	}

	public void showCancel() {
		System.out.println("취소되었습니다. 선택창으로 돌아갑니다.");
	}

	public void showEnterName() {
		System.out.println("이름을 입력해주세요.");
	}

	public void showEnterSpecialContract() {
		System.out.println("특약을 입력해주세요.");
	}

	public void showEnterApplyCondition() {
		System.out.println("가입조건을 입력해주세요.");
	}

	public void showEnterCompensateCondition() {
		System.out.println("보상 조건을 입력해주세요.");
	}

	public void showEnterExplanation() {
		System.out.println("설명을 입력해주세요.");
	}

	public void showSelectRate(String standardRate) {
		System.out.println("기준 요율을 사용하시겠습니까? 기존요율: " + standardRate);
		System.out.println("1. 예 2. 아니오 0. 취소");
	}

	public void showEnterPremiumRate() {
		System.out.println("등급 별 요율을 입력해주세요. (0 이상의 소수를 넣어주세요.)");
		System.out.println("1등급, 2등급, 3등급의 요율을 차례대로 입력해주세요.");
	}

	public void showInsurance(Insurance insurance) {
		System.out.println("보험명: " + insurance.getInsuranceName() + "\n" + "보험종류: " + insurance.getInsuranceType()
				+ "\n" + "기준보험료: " + insurance.getStandardFee() + "\n" + "특약: " + insurance.getSpecialContract() + "\n"
				+ "장기여부: " + insurance.isLongTerm() + "\n" + "가입조건: " + insurance.getApplyCondition() + "\n" + "보상조건: "
				+ insurance.getCompensateCondition() + "\n" + "설명: " + insurance.getExplanation() + "\n"
				+ "요율: [1등급, 2등급, 3등급]" + Arrays.toString(insurance.getPremiumRate()));
	}

	public void showSelctRegister() {
		System.out.println("등록하시겠습니까?");
		System.out.println("1. 등록 2. 취소");
	}

	public void showSuccessRegister() {
		System.out.println("보험이 심사 등록 되었습니다.");
	}

	public void showSelectTempInsurance() {
		System.out.println("이전에 작업하던 설계가 있습니다. 이어서 하시길 원하면 1을 입력해주세요.");
	}

}
