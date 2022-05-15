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
	public Contract contract;

	public ContractTeam() {
		// 질문사항 Insurance insurance = new HouseInsurance(): 
		// HouseInsurance houseInsurance = new Insurance(); 
		// HouseInsurance houseInsurance = new HouseInsurance();
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
		if (scanner.nextInt() == 1) {
			longTerm = true;
		} else {
			longTerm = false;
		}

		if (type == 1) {
			this.insurance = new GeneralInsurance(longTerm);

		} else if (type == 2) {
			this.insurance = new HouseInsurance(longTerm);
		} else {
			System.out.println("잘 못 입력함");
			System.exit(0);
		}

		this.insurance.design();
		this.insurance.measureStandardFee();

		System.out.println("등록을 원하면 1번");
		if ((scanner.nextInt() == 1) && this.insurance.apply()) {
			System.out.println("등록 완료");
		}

	}

	public void underwrite() {
		this.contract = new Contract();

		Scanner scanner = new Scanner(System.in);
		System.out.println("1.신규 2.갱신");
		int select = scanner.nextInt();
		this.contract.underwrite(); // DB에서 받아옴
		if (select == 1) {

			this.contract.verify();
			permit(); // boolean으로 가져온
		} else if (select == 2) {
			this.contract.verifyRenew();

		} else {
			System.out.println("잘 못 입력햇습니다.");
		}

	}

	public void permit() {
		this.contract.permit();
	}

	public void allowRenew() {
		this.contract.allowRenew();
	}

	private void search() {
		this.contract = new Contract();
		Scanner scanner = new Scanner(System.in);
		System.out.println("고객이름을 입력해주세요.");
		String customerName = scanner.next();
		this.contract.search();
		System.out.println("갱신을 하겠습니까?");
		int doRenew = scanner.nextInt();
		if(doRenew==1) {
			renew();
		}
	}

	public void renew() {
		this.contract.renew();
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