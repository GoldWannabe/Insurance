package User;

import java.util.Scanner;

import Account.Account;

//488548123456789jyjjyy
/**
 * @author ansm6
 * @version 1.0
 * @created 08-5-2022 ���� 10:34:18
 */
public class CompensateTeam {

	/////////////////////

	public Account m_Account;

	public CompensateTeam() {

	}

	public void finalize() throws Throwable {

	}

	public void add() {

	}

	public void edit() {

	}

	public void payCost() {

	}

	public void search() {

	}

	public void start() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("1. 손해사정");
		String selectNum = scanner.next();
		scanner.close();

		switch (selectNum) {
		case "1":
			search();
			break;

		default:
			System.out.println("선택 이상함");
			break;
		}
	}// end CompensateTeam
}