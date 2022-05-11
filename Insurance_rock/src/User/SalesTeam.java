package User;

import java.util.Scanner;

import Contract.Contract;
import Customer.Customer;
import Insurance.Insurance;

/**
 * @author ansm6
 * @version 1.0
 * @created 08-5-2022 ���� 10:34:19
 */
public class SalesTeam {

	public Contract m_Contract;
	public Customer m_Customer;
	public Insurance m_Insurance;

	public SalesTeam(){

	}

	public void finalize() throws Throwable {

	}
	public void addChannel(){

	}

	public void addCustomer(){

	}

	public void apply(){

	}

	public void deleteChannel(){

	}

	public void deleteCustomer(){

	}

	public void editChannel(){

	}

	public void editCustomer(){

	}

	public void reapply(){

	}

	public void searchChannel(){

	}

	public void searchCustomer(){

	}

	public void sell(){

	}

	private void manageChannel() {

	}
	
	public void start() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("1. 보험 판매, 2. 고객 정보 관리, 3. 판매채널 관리");
		String selectNum = scanner.next();
		scanner.close();
		
		switch (selectNum) {
		case "1":
			sell();
			break;
		case "2":
			searchCustomer();
			break;
		case "3":
			manageChannel();
			break;

		default:
			System.out.println("선택 이상함");
			break;
		}
	}

	
}//end SalesTeam