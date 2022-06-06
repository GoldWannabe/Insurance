package View.Team;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import Model.Contract.Contract;
import Model.Contract.ContractListImpl;
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

	public void viewContract(ContractListImpl contractList) {
		System.out.println(
				"-------------------------------------------검색된 계약--------------------------------------------------------------------------");
		System.out.printf("%15s %15s %15s %10s %10s %15s %20s %20s %15s %15s", "가입자명", "연락처", "보험이름", "납부방식", "보험료",
				"미납액", "담보액", "지급액", "가입일", "만료일");
		System.out.println();
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");
		for (Contract contract : contractList.getAll()) {
			System.out.format("%7s %20s %5s %10s %10s %10s %15s %10s %10s %10s",
					contract.getNum() + "." + contract.getCustomerName(), contract.getPhoneNum(),
					contract.getInsuranceName(), contract.getPaymentCycle(), contract.getInsuranceFee(),
					contract.getUnpaidFee(), contract.getSecurityFee(), contract.getProvisionFee(),
					contract.getStartDate(), contract.getEndDate());
			System.out.println();
		}
	}


	public void showEnterFireFacilities() {
		System.out.print("소화시설(Float): ");
	}

	public void showEnterScale() {
		System.out.print("스케일(Int): ");
	}

	public void showEnterSurroundingFacilities() {
		System.out.print("주변 시설(Float): ");
	}

	public void showEnterHeight() {
		System.out.println("높이가 15층 이상입니까? [true or false]");
		System.out.print("높이(boolean): ");
	}

	public void showEnterMaterial() {
		System.out.print("건물 재질(rock, wood etc.): ");
	}

	public void showEnterPurpose() {
		System.out.print("건물 목적(living, factory etc.): ");
	}

	public void showReInput() {
		System.out.println("재입력: ");
	}

	public void viewSearch() {
		System.out.println("1. 검색 , 0. 취소");
		
	}

	public void viewNotInquiry() {
		System.out.println("조회된 내역이 없습니다.");
		
	}

	public void viewcancelHome() {
		System.out.println("취소되었습니다.보험사 홈화면으로 돌아갑니다.");
	}

	public void veiwselectContract() {
		System.out.println("<갱신 혹은 해치할 계약을 선택해주세요.>");
	}

	public void veiwNotAccidentHistory() {
		System.out.println("해당 계약은 사고이력이 존재하지 않습니다.");
	}


	public void viewAccidentHistory(ArrayList<String> accidentHistoryArray) {
		System.out.println("-------------------------검색된 사고이력----------------------------");
		System.out.printf("%10s %40s ", "계약ID", "고객ID");
		System.out.println();
		System.out.println("----------------------------------------------------------------");
		for (int i = 0; i < accidentHistoryArray.size(); i++) {
			int i2 = i + 1;
			System.out.format("%10s %40s", accidentHistoryArray.get(i), accidentHistoryArray.get(i2));//
			i++;
			System.out.println();
		}
		
	}

	public void viewSelectRenewCacel() {
		System.out.println("1. 갱신 , 2. 해지,  0.취소");
	}

	public String[] viewInsertName(Scanner scanner) {
		String[] inser = new String[2];
		System.out.println("가입자명과 연락처를 입력해 주세요");
		System.out.println("가입자명 : ");
		inser[0] = scanner.next();
		System.out.println("연락처    : ");
		inser[1] = scanner.next();
		return inser;
	}

}
