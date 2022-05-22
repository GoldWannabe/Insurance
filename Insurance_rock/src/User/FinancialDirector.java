package User;

import java.util.Scanner;

import Insurance.Insurance;

/**
 * @author ansm6
 * @version 1.0
 * @created 08-5-2022 ���� 10:34:18
 */
public class FinancialDirector {

	public Insurance insurance;

	public FinancialDirector(){
//		this.insurance = new Insurance();
	}

	public void finalize() throws Throwable {

	}
	public void examine(){
		//1.DB에서 파일 목록 보여줌
		
		if(this.insurance.examine()) {
			permitInsurance();
		}
	}

	public void permitInsurance(){
		this.insurance.permitInsurance();
	}

	public void start() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("1. 보험 심사");
		String selectNum = scanner.next();
		scanner.close();
		
		switch (selectNum) {
		case "1":
			examine();
			break;

		default:
			System.out.println("선택 이상함");
			break;
		}
	}
}//end FinancialDirector