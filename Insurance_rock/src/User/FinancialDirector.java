package User;

import java.util.Scanner;

import Insurance.Insurance;

/**
 * @author ansm6
 * @version 1.0
 * @created 08-5-2022 ���� 10:34:18
 */
public class FinancialDirector {

	public Insurance m_Insurance;

	public FinancialDirector(){

	}

	public void finalize() throws Throwable {

	}
	public void examine(){

	}

	public void permitInsurance(){

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