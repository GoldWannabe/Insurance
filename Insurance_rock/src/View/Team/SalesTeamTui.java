package View.Team;

import java.util.Arrays;

import Model.Contract.Contract;
import Model.Contract.ContractList;
import Model.Insurance.Insurance;
import Model.Insurance.InsuranceList;

public class SalesTeamTui {
	
	public void showInsuranceType() {
		System.out.println("가입하실 보험을 선택해주십시오.");
		System.out.println("1. 일반보험 2. 주택보험 0. 취소");
	}

	public void showCancel() {
		System.out.println("취소되었습니다. 선택창으로 돌아갑니다.");
	}
	
	public void showInsurance(InsuranceList insuranceList) {
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.printf("%10s %10s", "보험명", "보험종류");
		System.out.println();
		System.out.println("---------------------------------------------------------------------------------------");
		for (Insurance insurance : insuranceList.getAll()) {
			System.out.format("%10s %12s", insurance.getInsuranceName(), insurance.getInsuranceType().toString());
			System.out.println();
		}
	}
	
	public void showEnterInsuranceName() {
		System.out.println("보험 이름을 입력하여 주십시오.");
		System.out.print("보험 이름: ");
	}
	
	public void showSearchFail() {
		System.out.println("해당하는 정보가 존재하지 않습니다. 다시 입력해주세요.");
	}

	public void showInsuranceDetail(Insurance insurance) {
		System.out.println("보험명: " + insurance.getInsuranceName() + "\n" + "보험종류: "
				+ insurance.getInsuranceType() + "\n" + "기준보험료: " + insurance.getStandardFee() + "\n" + "특약: "
				+ insurance.getSpecialContract() + "\n" + "장기여부: " + insurance.isLongTerm() + "\n" + "가입조건: "
				+ insurance.getApplyCondition() + "\n" + "보상조건: " + insurance.getCompensateCondition() + "\n"
				+ "설명: " + insurance.getExplanation() + "\n" + "요율: [1등급, 2등급, 3등급]"
				+ Arrays.toString(insurance.getPremiumRate()));
	}
	
	public void showSelectInsurance(String insuranceName) {
		System.out.println(insuranceName+" 보험에 가입하시겠습니까?");
		System.out.println("1. 예 2. 재가입 신청 0. 취소");
	}
	
	public void showSelectCustomer() {
		System.out.println("가입자 선택");
		System.out.println("1. 신규 고객 2. 기존 고객 0. 취소");
	}

	public void showEnterSecurityFee() {
		System.out.print("담보액: ");
	}

	public void showEnterInsuranceFee() {
		System.out.print("보험료: ");		
	}

	public void showEnterPaymentCycle() {
		System.out.print("납부 방식(paymentCycle): ");		
	}

	public void showEnterPeriod() {
		System.out.print("가입 기간: ");		
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

	public void showSearchCustomerStart() {
		System.out.println("-----회원 조회-----");
	}
	
	public void showEnterCustomerName() {
		System.out.print("고객 이름: ");
	}

	public void showEnterCustomerPhoneNum() {
		System.out.print("전화번호: ");		
	}

	public void showExistBlankInput() {
		System.out.println("이름과 전화번호 모두 입력하여 주십시오.");
	}

	public void showSearchCustomerSuccess(String customerName) {
		System.out.println("환영합니다, " + customerName + "님. 보험 가입을 진행합니다.");
		System.out.println("-----고객 조회 완료-----");
	}
	
	public void showNewCustomerStart() {
		System.out.println("신규 고객으로 회원가입을 진행합니다.");
	}

	public void showEnterSSN() {
		System.out.print("주민/사업자 번호: ");
	}

	public void shoeEnterAddress() {
		System.out.print("주소: ");
	}

	public void showEnterSex() {
		System.out.println("성별: [male, female, none]");
		System.out.print("선택: ");
	}

	public void showEnterBankName() {
		System.out.print("은행 이름: ");
	}

	public void showEnterAccountNum() {
		System.out.print("계좌번호: ");
	}
	public void showNewCustomerEnd() {
		System.out.println("신규 회원가입이 완료되었습니다.");
	}

	public void showReInput() {
		System.out.print("재입력: ");
		
	}

	public void showFailContractList(ContractList contractFailList) {
		System.out.println("-------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%10s %10s %10s %10s %10s %10s %10s %10s", "가입자 이름", "연락처", "보험 이름", "납부 방식", "보험료", "담보액", "가입기간", "탈락 이유");
		System.out.println();
		System.out.println("-------------------------------------------------------------------------------------------------------------------");
		for (Contract contract : contractFailList.getAll()) {
			System.out.format("%10s %15s %10s %10s %10s %15s %5s %13s",
					contract.getCustomerName(), contract.getPhoneNum(), contract.getInsuranceName(), contract.getPaymentCycle(),
					contract.getInsuranceFee(), contract.getSecurityFee(), contract.getPeriod(), contract.getReason());
			System.out.println();
		}
		
	}

	public void showSelectContract() {
		System.out.println("재신청할 ContractID를 입력하여 주십시오.");
	}
	
}
