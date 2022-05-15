package User;

import java.util.Scanner;

import Contract.Contract;
import Insurance.GeneralInsurance;
import Insurance.HouseInsurance;
import Insurance.Insurance;

/**
 * @author ansm6
 * @version 1.0
 * @created 08-5-2022 ���� 10:34:18
 */
public class ContractTeam {

	public Insurance insurance;
	public GeneralInsurance generalInsurance;
	public HouseInsurance houseInsurance;

	public ContractTeam() {
		//질문사항  Insurance insurance = new HouseInsurance(): HouseInsurance houseInsurance = new Insurance(); HouseInsurance houseInsurance = new HouseInsurance;
	}

	public void finalize() throws Throwable {

	}

	public void design() {
		int type;
		boolean longTerm = false;
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("1. 일반 보험 2. 주택 보험");
		type = scanner.nextInt();
		System.out.println("1. 장기 2. 단기");
		if(scanner.nextInt()==1) {
			longTerm = true;
		} else {
			longTerm = false;
		}
		System.out.println("이름을 입력해주세요.");
		String name = scanner.next();
		System.out.println("특약을 입력해주세요.");		
		String specialContract = scanner.next();
		System.out.println("가입조건을 입력해주세요.");
		String applyCondition = scanner.next();
		System.out.println("보상 조건을 입력해주세요.");
		String compensateCondition = scanner.next();
		System.out.println("설명을 입력해주세요.");
		String explanation = scanner.next();
		
		if(type == 1) {
			this.insurance = new GeneralInsurance(longTerm, name, specialContract, applyCondition, compensateCondition, explanation);
			
		} else if(type == 2) {
			this.insurance = new HouseInsurance(longTerm, name, specialContract, applyCondition, compensateCondition, explanation);
		} else {
			System.out.println("잘 못 입력함");
			System.exit(0);
		}
		
		//this.generalInsurance.checkName(); 검색의 효율성을 위함
		this.insurance.measureStandardFee();
		System.out.println("등록을 원하면 1번");
		if(scanner.nextInt() == 1) {
			System.out.println("등록 완료");
		}
	}
	
	public void underwrite() {

	}
	
	public void permit() {

	}
	
	public void renew() {

	}
	
	private void search() {
		
	}
	
	public void cancelContract() {

	}
	
	public void start() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("1. 설계, 2. 인수심사,  3. 계약관리");
		String selectNum = scanner.next();

		switch (selectNum) {
		case "1":
			design();
			break;
		case "2":
			underwrite();
			break;
		case "3":
			search();
			break;
		default:
			System.out.println("선택 이상함");
			break;
		}
	}// end ContractTeam


}