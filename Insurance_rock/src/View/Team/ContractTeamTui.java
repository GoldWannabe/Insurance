package View.Team;

import java.util.Arrays;

import Model.Contract.Contract;
import Model.Customer.Customer;
import Model.Customer.Rank;
import Model.Insurance.Insurance;

public class ContractTeamTui {

	public void showStart() {
		System.out.println("장기여부와 보험 종류를 선택해 주세요. (해당하는 번호를 입력해주세요.)");
	}

	public void showSelectLongterm() {
		System.out.println("장기여부를 선택해주세요. (해당하는 번호를 입력해주세요.)");
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
		System.out.println("기준 요율을 사용하시겠습니까? (해당하는 번호를 입력해주세요.)");
		System.out.println("기존요율: " + standardRate);
		System.out.println("1. 예 2. 아니오 0. 취소");
	}

	public void showEnterPremiumRate() {
		System.out.println("등급 별 요율을 입력해주세요. (0 이상의 실수를 넣어주세요.)");
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
		System.out.println("등록하시겠습니까? (해당하는 번호 또는 단어를 적어주세요.)");
		System.out.println("1. 등록 2. 취소");
	}

	public void showSuccessRegister() {
		System.out.println("보험이 심사 등록 되었습니다.");
	}

	public void showSelectTempInsurance() {
		System.out.println("이전에 작업하던 설계가 있습니다. 이어서 하시길 원하면 1을 입력해주세요.");
	}

	public void showSelectContractMethods() {
		System.out.println("인수심사할 계약 방식을 고르세요.");
		System.out.println("1. 신규 2. 갱신 0. 취소");

	}

	public void showNoApplyContract() {
		System.out.println("심사할 계약이 없습니다.");
		System.out.println("메인화면으로 돌아갑니다.");
	}

	public void showSelectApplyContract() {
		System.out.println("심사할 계약 번호를 입력해 주세요.");

	}

	public void showConractColumn() {
		System.out.println(
				"-------------------------------------------------------------------------------------------------");
		System.out.printf("%10s %10s %10s %10s %15s %10s %10s", "계약번호(순번)", "보험이름", "가입자명", "연락처", "담보액", "보험료",
				"가입기간(달)");
		System.out.println();
		System.out.println(
				"--------------------------------------------------------------------------------------------------");
	}

	public void showContracts(int length, Contract contract) {
		System.out.format("%10s %10s %12s %15s %15d %10d %10d", length + "번", contract.getInsuranceName(),
				contract.getCustomerName(), contract.getPhoneNum(), contract.getSecurityFee(),
				contract.getInsuranceFee(), contract.getPeriod());
		System.out.println();
	}

	public void showSelectVerification() {
		System.out.println("해당 보험을 검증하시겠습니까?");
		System.out.println("1. 검증 2. 취소");

	}

	public void showDetailContract(Contract contract) {
		System.out.println("고객명: "+ contract.getCustomerName()+"   전화번호: "+contract.getPhoneNum());
		System.out.println("보험료: "+contract.getInsuranceFee());
		System.out.println("담보액: "+contract.getSecurityFee()+"   기간: "+contract.getPeriod());
	}

	public void showDetailInsurance(Insurance insurance) {
		System.out.println("보험명: "+ insurance.getInsuranceName());
		System.out.println("보험종류: "+insurance.getInsuranceType().toString()+"   장기여부: "+insurance.isLongTerm());
		System.out.println("가입조건: "+insurance.getApplyCondition());
		System.out.println("특약: "+insurance.getSpecialContract());
	}

	public void showDetailRank(Rank rank) {
		System.out.println("재질: "+rank.getMaterial()+"   용도: "+rank.getPurpose());
		System.out.println("크기(평수): "+rank.getScale()+"   고층여부: "+rank.isHeight());
		System.out.println("소방시설 점수: "+ rank.getFireFacilities()+"   주변시설 점수: "+rank.getSurroundingFacilities());
	}

	public void showSelectPermit() {
		System.out.println("가입을 허가시키겠습니까?");
		System.out.println("1.계약 2.반려 0.취소");
	}

	public void showEnterReason() {
		System.out.println("반려 사유를 입력해 주세요.");
	}

}
