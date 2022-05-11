package User;

import java.util.Scanner;

import Contract.Contract;
import Insurance.Insurance;

/**
 * @author ansm6
 * @version 1.0
 * @created 08-5-2022 ���� 10:34:18
 */
public class ContractTeam {

	public Insurance m_Insurance;
	public Contract m_Contract;

	public ContractTeam() {

	}

	public void finalize() throws Throwable {

	}

	public void cancelContract() {

	}

	public void design() {

	}

	public void permit() {

	}

	public void renew() {

	}

	public void underwrite() {

	}
	private void search() {
		// TODO Auto-generated method stub
		
	}
	public void start() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("1. 설계, 2. 인수심사,  3. 계약관리");
		String selectNum = scanner.next();
		scanner.close();

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