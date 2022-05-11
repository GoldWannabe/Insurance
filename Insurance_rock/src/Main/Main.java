package Main;

import java.util.Scanner;

import Customer.Customer;
import User.CompensateTeam;
import User.ContractTeam;
import User.FinancialDirector;
import User.SalesTeam;

public class Main {

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("1. 계약팀, 2. 금감원, 3. 마켓팅/영업팀, 4. 보상팀, 5.고객");
		String selectNum = scanner.next();
		
		
		switch (selectNum) {
		case "1":
			ContractTeam contractTeam = new ContractTeam();
			contractTeam.start();
			break;
		case "2":
			FinancialDirector financialDirector = new FinancialDirector();
			financialDirector.start();
			break;
		case "3":
			SalesTeam salesTeam = new SalesTeam();
			salesTeam.start();
			break;
		case "4":
			CompensateTeam compensateTeam = new CompensateTeam();
			compensateTeam.start();
			break;
			
		case "5":
			Customer customer = new Customer();
			//customer.start();
			break;
		default:
			System.out.println("제대로 입력해주세요.");
			break;
		}
	}

}
