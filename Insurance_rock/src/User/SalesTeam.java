package User;

import java.util.Scanner;

import Contract.Contract;
import Contract.ContractList;
import Contract.ContractListImpl;
import Customer.Customer;
import Customer.CustomerList;
import Customer.CustomerListImpl;
import Insurance.GeneralInsurance;
import Insurance.Insurance;
import Insurance.InsuranceList;
import Insurance.InsuranceListImpl;
import Insurance.Insurance.EInsurance;

public class SalesTeam {

	private CustomerList customerList = new CustomerListImpl();
	private Customer currentCustomer = null;
	private InsuranceList insuranceList = new InsuranceListImpl();
	private Insurance currentInsurance = null;
	private ContractList contractList = new ContractListImpl();
	

	public SalesTeam(){
		//set customerList & insuranceList
		//test data
//		Customer customer = new Customer();
//		customer.setName("a");
//		customer.setPhoneNum("bc");
//		customer.setCustomerID("1");
//		this.customerList.add(customer);
//		//------------------------------
//		Insurance insurance = new GeneralInsurance(); //Insurance (x)  houseInsurance(o)
//		insurance.setInsuranceName("a");
//		this.insuranceList.add(insurance);
		
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
	
	public void manageCustomer() {
		
	}
	
	private void manageChannel() {

	}

	public void searchChannel(){

	}
	
	public boolean searchCustomer() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("고객 조회");
		System.out.print("고객 이름: ");
		String name = scanner.next();
		System.out.print("고객 전화번호: ");
		String phoneNum = scanner.next();
		
		if(!(this.customerList.search(name, phoneNum) == null)) {
			System.out.println("고객 조회 성공");
			this.currentCustomer = this.customerList.search(name, phoneNum);
			return true;
		}
		
		System.out.println("고객 조회 실패");
		this.currentCustomer = null;
		return false;
	}
	
	public boolean searchInsurance() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("보험 검색");
		System.out.println("보험 종류를 선택해주세요.");
		System.out.println("1. 일반 화재 보험 2. 주택 화재 보험");
		String type = scanner.next();
		System.out.print("보험 이름: ");
		String name = scanner.next();
		
		if(type.equals("1")) this.currentInsurance = this.insuranceList.get(name, EInsurance.general);
		else if(type.equals("2"))this.currentInsurance = this.insuranceList.get(name, EInsurance.house);
		
		if(!(this.currentInsurance == null)) {
			System.out.println("보험 조회 성공");
			System.out.println("보험 이름: "+this.currentInsurance.getInsuranceName()+"보험");
			return true;
		}
		return false;
	}

	public void sell(){
		
		System.out.println("1-1");
		if(searchCustomer()) {
			if(searchInsurance()) {
				Contract contract = new Contract();
				contract.setCustomerID(this.currentCustomer.getCustomerID());
				System.out.println("customerID: "+contract.getCustomerID());
				this.contractList.add(contract);
				System.out.println("보험 판매::가입 신청이 완료되었습니다.");
			}
		}
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


	
	public void start() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("1. 보험 판매, 2. 고객 정보 관리, 3. 판매채널 관리");
		String selectNum = scanner.next();
		
		
		switch (selectNum) {
		case "1":
			sell();
			break;
		case "2":
			manageCustomer();
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