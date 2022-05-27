package SalesTeam;

import java.util.ArrayList;
import java.util.Scanner;

import Contract.Contract;
import Contract.ContractList;
import Contract.ContractListImpl;
import Customer.Customer;
import Customer.CustomerList;
import Customer.CustomerListImpl;
import Customer.Rank;
import Insurance.Insurance;
import Insurance.Insurance.EInsurance;
import Insurance.InsuranceList;
import Insurance.InsuranceListImpl;

public class InsuranceSales {
	
	private InsuranceList insuranceList = new InsuranceListImpl();
	private CustomerList customerList = new CustomerListImpl();
	private ContractList contractList = new ContractListImpl();
	
	public InsuranceSales() {
		
	}

	public void searchInsurance(Scanner scanner) {
		System.out.println("가입하실 보험을 선택해주십시오.");
		System.out.println("1. 일반보험 2. 주택보험");
		String type = scanner.next();
			
		if(type.equals("1")) {
			generalInsurance(scanner, EInsurance.general);
		}else if(type.equals("2")) {
			generalInsurance(scanner, EInsurance.house);
		}
	}

	private void generalInsurance(Scanner scanner, EInsurance insuranceType) {
		ArrayList<Insurance> InsuranceList = this.insuranceList.get(insuranceType);
		System.out.println("------"+insuranceType.toString()+"보험 목록------");
		for(int i = 0; i<InsuranceList.size(); i++) {
			System.out.println(InsuranceList.get(i).getInsuranceID()+" "+InsuranceList.get(i).getInsuranceName());
		}
		System.out.println("보험 이름을 입력하여 주십시오.");
		System.out.print("보험 이름: ");
		String insuranceName = scanner.next();
		if(!this.insuranceList.get(insuranceName, insuranceType).equals(null)) {
			Insurance insurance = this.insuranceList.get(insuranceName, insuranceType);
			
			//보험 상세 정보(보험 이름, 보험 종류, 기준 보험료, 특약, 출시일, 장기여부, 가입 조건, 보상 조건, 설명)와 가입, 재신청, 취소 버튼을 출력한다
			System.out.println("-----선택하신 보험-----");
			System.out.println("보험 이름: "+insurance.getInsuranceName()+" \n"+
					"보험 종류: "+insurance.getInsuranceType()+" \n"+
					"기준 보험료: "+insurance.getStandardFee()+" \n"+
					"특약: "+insurance.getReleaseDate()+" \n"+
					"장기여부: "+insurance.isLongTerm()+" \n"+
					"가입조건: "+insurance.getApplyCondition()+ " \n"+
					"보상조건: "+insurance.getCompensateCondition()+ "\n"+
					"설명: "+insurance.getExplanation()+" \n");
			System.out.println(insurance.getInsuranceName()+" 보험에 가입하시겠습니까?");
			System.out.println("1. 예 2. 아니오");
			String answer = scanner.next();
			
			// create new Contract
			if(answer.equals("1")) newContract(scanner, insurance);
			else if(answer.equals("2")) System.out.println("가입을 취소합니다.");
			else System.out.println("잘못된 입력입니다.");
		}else {
			System.out.println("입력하신 이름의 보험이 없습니다.");
		}
	}


	public void newContract(Scanner scanner, Insurance insurance) {
		//가입자 이름, 주민/사업자 번호,연락처, 주소, 성별, 담보액, 보험료, 납부 방식, 가입기간, 은행명, 계좌번호
		//, 소화시설, 스케일, 주변시설을 입력하고 높이, 건물재질을 선택한 후 가입 신청하기 버튼을 클릭한다(A1, E1,E2)
		Contract newContract = new Contract();
		Customer customer = new Customer();
		Rank rank = new Rank();
		
		//set UUID
		customer.setCustomerID();
		newContract.setContractID();
		newContract.setCustomerID(customer.getCustomerID());
		rank.setRankID();
		rank.setCustomerID(customer.getCustomerID());
		
		newContract.setInsuranceName(insurance.getInsuranceName());
		newContract.setInsuranceID(insurance.getInsuranceID());
		
		
		System.out.print("가입자 이름: ");
		String customerName = scanner.next();
		newContract.setCustomerName(customerName);
		customer.setName(customerName);
		
		System.out.print("주민/사업자 번호: ");
		customer.setSSN(scanner.next());
		
		System.out.print("연락처: ");
		String phoneNum = scanner.next();
		customer.setPhoneNum(phoneNum);
		newContract.setPhoneNum(phoneNum);

		System.out.print("담보액: ");
		newContract.setSecurityFee(scanner.nextInt());
		System.out.print("보험료: ");
		newContract.setInsuranceFee(scanner.nextInt());
		//System.out.println("납부 방식: ");
		System.out.print("가입 기간: ");
		newContract.setPeriod(scanner.nextInt());
		
		System.out.print("소화시설(Float): ");
		rank.setFireFacilities(scanner.nextFloat());
		System.out.print("스케일(Int): ");
		rank.setScale(scanner.nextInt());
		System.out.print("주변 시설(Float): ");
		rank.setSurroundingFacilities(scanner.nextFloat());
		System.out.print("높이(boolean): ");
		rank.setHeight(scanner.nextBoolean());
		System.out.print("건물 재질(rock, wood etc.): ");
		rank.setMaterial(scanner.next());
		
		//File write
		newContract.register();
		rank.register();
		
		//신규 고객이라면
		if(customerList.search(customer.getName(), customer.getPhoneNum())==null) {
			newCustomer();
		}else {
			customer = customerList.search(customer.getName(), customer.getPhoneNum());
			customer.setInsuranceNum((float) (customer.getInsuranceNum()+0.1));
			customerList.rewrite();
			System.out.println(customer.getName()+" 고객님, 가입 신청에 성공했습니다.");
			
		}
		
	}
	
	private void newCustomer() {
		//기존 고객 여부를 확인한 후 입력한 정보(가입자명, 주민/사업자번호, 연락처, 주소, 성별, 계약번호, 가입보험개수, 은행명, 계좌번호) 저장을 요청한다(A2, E3)
		System.out.println("신규 고객으로 회원가입을 진행합니다.");
		
		Scanner scanner = new Scanner(System.in);
		Customer customer = new Customer();		
		customer.setCustomerID();
		
		System.out.print("고객 이름: ");
		customer.setName(scanner.next());
		System.out.print("주민/사업자 번호: ");
		customer.setSSN(scanner.next());
		System.out.print("연락처: ");
		customer.setPhoneNum(scanner.next());
		System.out.print("주소: ");
		customer.setAddress(scanner.next());
		System.out.println("성별: 1. 남 2. 여 3. 없음");
		System.out.print("선택: ");
		String select = scanner.next();
		if(select.equals("1")) customer.setSex("male");
		else if(select.equals("2")) customer.setSex("female");
		else if(select.equals("3")) customer.setSex("none");
		//else 처리
		System.out.print("은행 이름: ");
		customer.setBankName(scanner.next());
		System.out.print("계좌번호: ");
		customer.setAccountNum(scanner.next());
		
		//This new customer has only one "applyContract".
		customer.setInsuranceNum((float) 0.1);
		
		ArrayList<String> nullArray = new ArrayList<String>();
		nullArray.add("null");
		
		customer.setContractID(nullArray);
		customer.setRankID(nullArray);
		
		customerList.add(customer);
		//File write
		customer.register();
		
		System.out.println("회원가입과 보험 가입 신청이 완료되었습니다.");
		
		
	}

}
