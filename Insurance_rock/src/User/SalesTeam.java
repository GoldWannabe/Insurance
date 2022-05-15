package User;

import java.util.Scanner;

import Customer.Customer;
import Customer.CustomerList;
import Customer.CustomerListImpl;
import Insurance.Insurance;
import Insurance.InsuranceList;
import Insurance.InsuranceListImpl;

public class SalesTeam {

	private CustomerList customerList = new CustomerListImpl();
	private Customer currentCustomer = null;
	private InsuranceList insuranceList = new InsuranceListImpl();
	private Insurance currentInsurance = null;
	

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
	
	public boolean searchCustomer(String name, String phoneNum) {
		
		return false;
	}

	public void sell(){
		
		System.out.println("1-1");
		searchCustomer();
//		Scanner scanner = new Scanner(System.in);
//		Contract contract = new Contract();
//		contract.setAccidentHistory("나는 몰라요");
//		contract.getRank().setHeight(true);
//		LocalDate a= LocalDate.of(2012, 5, 6);
//		
//		contract.setEndDate(a);
//		System.out.println(contract.getAccidentHistory());
//		System.out.println(contract.getRank().isHeight());
//		System.out.println(a);
	}

	private void manageChannel() {

	}
	
	public void start() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("1. 보험 판매, 2. 고객 정보 관리, 3. 판매채널 관리");
		String selectNum = scanner.next();
		
		
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