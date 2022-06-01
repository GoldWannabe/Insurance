package View.Team;

import java.util.Arrays;
import java.util.Scanner;

import Control.ContractTeam.InsuranceDesign;
import Model.Insurance.GeneralInsurance;
import Model.Insurance.HouseInsurance;

public class ContractTeamTui {

	public boolean design() {
		return false;
//		InsuranceDesign insuranceDesign = new InsuranceDesign();
//		
//		boolean IsLongTerm = false;
//		Scanner scanner = new Scanner(System.in);
//		int flag = -1;
//		if (getTempInsurance(scanner)) {
//			return register(scanner);
//		}
//
//		System.out.println("장기여부와 보험 종류를 선택해 주세요.");
//
//		System.out.println("장기여부를 선택해주세요.");
//		System.out.println("1. 장기 2. 단기 0. 취소");
//
//		flag = getflag(scanner);
//		if (flag == 1) {
//			IsLongTerm = true;
//		} else if (flag == 2) {
//			IsLongTerm = false;
//		} else if (flag == 0) {
//			System.out.println("취소되었습니다. 선택창으로 돌아갑니다.");
//			return true;
//		}
//		
//		System.out.println("보험 종료를 선택해주세요.");
//		System.out.println("1. 일반보험 2. 주택보험 0. 취소");
//		flag = getflag(scanner);
//
//		if (flag == 1) {
//			
//		} else if (flag == 2) {
//			
//		} else if (flag == 0) {
//			System.out.println("취소되었습니다. 선택창으로 돌아갑니다.");
//			return true;
//		}
//
//		insuranceDesign.design();	
//		
//		
//		System.out.println("이름을 입력해주세요.");
//		this.insurance.setInsuranceName(scanner.next());
//		while (this.insurance.checkName()) {
//			System.out.println("중복되는 이름이 존재합니다. 다시 입력해주세요."); 
//			this.insurance.setInsuranceName(scanner.next());
//		}
//
//		System.out.println("특약을 입력해주세요.");
//		this.insurance.setSpecialContract(scanner.next());
//		System.out.println("가입조건을 입력해주세요.");
//		this.insurance.setApplyCondition(scanner.next());
//		System.out.println("보상 조건을 입력해주세요.");
//		this.insurance.setCompensateCondition(scanner.next());
//		System.out.println("설명을 입력해주세요.");
//		this.insurance.setExplanation(scanner.next());
//
//		System.out.println("기준 요율을 사용하시겠습니까? 기존요율: [1등급, 2등급, 3등급]" + Arrays.toString(this.insurance.getStandardRate()));
//		System.out.println("1. 예 2. 아니오 0. 취소");
//		flag = getflag(scanner);
//
//		if (flag == 1) {
//			this.insurance.measureStandardFee();
//		} else if (flag == 2) {
//
//			double rate[] = new double[] { 0, 0, 0 };
//
//			while (checkRate(rate)) {
//				System.out.println("등급 별 요율을 입력해주세요.");
//				System.out.println("1급");
//				rate[0] = checkDouble(scanner);
//				System.out.println("2급");
//				rate[1] = checkDouble(scanner);
//				System.out.println("3급");
//				rate[2] = checkDouble(scanner);
//			}
//			this.insurance.setStandardRate(rate);
//			this.insurance.measureStandardFee();
//		} else if (flag == 0) {
//			System.out.println("취소되었습니다. 선택창으로 돌아갑니다.");
//			return true;
//		}

	}
	private int getflag(Scanner scanner) {
		String flag = scanner.next();
		while (true) {
			
			if (flag.equals("1")) {
				return 1;
			} else if (flag.equals("2")) {
				return 2;
			} else if (flag.equals("0")) {
				System.out.println("취소되었습니다. 선택창으로 돌아갑니다.");
				return 0;
			} else {
				System.out.println("해당하는 번호가 없습니다. 다시 입력해주세요.");
				flag = scanner.next();
			}
		}
	}
	public void selectUnderwrite() {
		// TODO Auto-generated method stub
		
	}

	public void searchContract() {
		// TODO Auto-generated method stub
		
	}

}
