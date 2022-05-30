package SalesTeam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import Contract.Contract;
import Contract.ContractList;
import Contract.ContractListImpl;
import Customer.Customer;
import Customer.CustomerList;
import Customer.CustomerListImpl;
import Customer.Rank;
import Insurance.GeneralInsurance;
import Insurance.HouseInsurance;
import Insurance.Insurance;
import Insurance.Insurance.EInsurance;
import Insurance.InsuranceList;
import Insurance.InsuranceListImpl;

public class InsuranceSales {
	private Insurance insurance;
	private InsuranceList insuranceList = new InsuranceListImpl();
	
	private Customer customer;
	private CustomerList customerList = new CustomerListImpl();
	
	private Contract contract;
	private ContractList contractList = new ContractListImpl();
	
	private Rank rank;
	
	public InsuranceSales() {
		
	}

	public void searchInsurance() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("가입하실 보험을 선택해주십시오.");
		System.out.println("1. 일반보험 2. 주택보험");
		String type = scanner.next();
			
		if(type.equals("1")) {
			this.insurance = new GeneralInsurance();
			getInsurance(scanner, EInsurance.general);
		}else if(type.equals("2")) {
			this.insurance = new HouseInsurance();
			getInsurance(scanner, EInsurance.house);
		}
	}

	private boolean getInsurance(Scanner scanner, EInsurance insuranceType) {
		ResultSet resultSet = this.insurance.getInsurance();
		
		try {
			while(resultSet.next()) {
				Insurance insurance = null;
				if(insuranceType.equals(EInsurance.general)) {
					insurance = new GeneralInsurance();
				}else if(insuranceType.equals(EInsurance.house)) {
					insurance = new HouseInsurance();
				}
				insurance.setInsuranceID(resultSet.getString("insuranceID"));
				insurance.setInsuranceName(resultSet.getString("insuranceName"));
				insurance.setStandardFee(resultSet.getInt("StandradFee"));
				insurance.setSpecialContract(resultSet.getString("specialContract"));
				insurance.setLongTerm(resultSet.getBoolean("longTerm"));
				insurance.setApplyCondition(resultSet.getString("applyCondition"));
				insurance.setCompensateCondition(resultSet.getString("compensateCondition"));
				insurance.setExplanation(resultSet.getString("explanation"));
				this.insuranceList.add(insurance);
				
				//if insuranceList is empty -> error
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		showInsurance();
		return selectInsurance(insuranceType);
	}
	
	private void showInsurance() {
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.printf("%10s %10s", "보험명", "보험종류");
		System.out.println();
		System.out.println("---------------------------------------------------------------------------------------");
		for (Insurance insurance : this.insuranceList.getAll()) {
			System.out.format("%10s %12s", insurance.getInsuranceName(), insurance.getInsuranceType().toString());
			System.out.println();
		}
	}
	
	private boolean selectInsurance(EInsurance insuranceType) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("보험 이름을 입력하여 주십시오.");
		System.out.print("보험 이름: ");
		String insuranceName = scanner.next();
		this.insurance = this.insuranceList.get(insuranceName);
		
		while (this.insurance == null) {
			System.out.println("해당하는 이름의 보험이 존재하지 않습니다. 다시 입력해주세요.");
			this.insurance = this.insuranceList.get(scanner.next());
		}
		
		System.out.println("보험명: " + this.insurance.getInsuranceName() + "\n" + "보험종류: "
				+ this.insurance.getInsuranceType() + "\n" + "기준보험료: " + this.insurance.getStandardFee() + "\n" + "특약: "
				+ this.insurance.getSpecialContract() + "\n" + "장기여부: " + this.insurance.isLongTerm() + "\n" + "가입조건: "
				+ this.insurance.getApplyCondition() + "\n" + "보상조건: " + this.insurance.getCompensateCondition() + "\n"
				+ "설명: " + this.insurance.getExplanation() + "\n" + "요율: [1등급, 2등급, 3등급]"
				+ Arrays.toString(this.insurance.getStandardRate()));
		
			System.out.println(insurance.getInsuranceName()+" 보험에 가입하시겠습니까?");
			System.out.println("1. 예 2. 아니오");
			String answer = scanner.next();
			// create new Contract
			if(answer.equals("1")) newContract(scanner, insurance);
			else if(answer.equals("2")) System.out.println("가입을 취소합니다.");
			else System.out.println("잘못된 입력입니다.");
		
		return false;
	}

	public void newContract(Scanner scanner, Insurance insurance) {
		//가입자 이름, 주민/사업자 번호,연락처, 주소, 성별, 담보액, 보험료, 납부 방식, 가입기간, 은행명, 계좌번호
		//, 소화시설, 스케일, 주변시설을 입력하고 높이, 건물재질을 선택한 후 가입 신청하기 버튼을 클릭한다(A1, E1,E2)
		System.out.println("가입자 선택");
		System.out.println("1. 신규 고객 2. 기존 고객");
		this.customer = new Customer();
		//-----------------------------
		switch (scanner.next()) {
			case "1":
				this.customer = newCustomer();
				break;
			case"2":
				this.customer = searchCustomer();
				break;
			default:
				System.out.println("잘못된 선택입니다.");
				break;
		}
		
		this.contract = new Contract();
		this.rank = new Rank();

		this.contract.setCustomerID(customer.getCustomerID());
		this.rank.setCustomerID(customer.getCustomerID());
		
		this.contract.setInsuranceName(insurance.getInsuranceName());
		this.contract.setInsuranceID(insurance.getInsuranceID());
		this.contract.setCustomerName(this.customer.getName());
		this.contract.setPhoneNum(this.customer.getPhoneNum());

		System.out.print("담보액: ");
		this.contract.setSecurityFee(scanner.nextInt());
		System.out.print("보험료: ");
		this.contract.setInsuranceFee(scanner.nextInt());
		System.out.print("납부 방식(paymentCycle): ");
		this.contract.setPaymentCycle(scanner.nextInt());
		System.out.print("가입 기간: ");
		this.contract.setPeriod(scanner.nextInt());
		
		System.out.print("소화시설(Float): ");
		this.rank.setFireFacilities(scanner.nextFloat());
		System.out.print("스케일(Int): ");
		this.rank.setScale(scanner.nextInt());
		System.out.print("주변 시설(Float): ");
		this.rank.setSurroundingFacilities(scanner.nextFloat());
		System.out.print("높이(boolean): ");
		this.rank.setHeight(scanner.nextBoolean());
		System.out.print("건물 재질(rock, wood etc.): ");
		this.rank.setMaterial(scanner.next());
		System.out.println("건물 목적(living, factory etc.)");
		this.rank.setPurpose(scanner.next());
		
		//DB write
		this.contract.registerApplyContract();
		this.rank.register();
		
		//DB update
		this.customer.setInsuranceNum(this.customer.getInsuranceNum()+0.1);
		
	}
	
	private Customer searchCustomer() {
		ResultSet resultSet = this.customer.getCustomer();
		
		try {
			while(resultSet.next()) {
				Customer customer = new Customer();
				customer.setCustomerID(resultSet.getString("customerID"));
				customer.setName(resultSet.getString("Name"));
				customer.setSSN(resultSet.getString("SSN"));
				customer.setSex(resultSet.getString("Sex"));
				customer.setPhoneNum(resultSet.getString("phoneNum"));
				customer.setAddress(resultSet.getString("address"));
				customer.setBankName(resultSet.getString("bankName"));
				customer.setAccountNum(resultSet.getString("accountNum"));
				customer.setInsuranceNum(resultSet.getDouble("insuranceNum"));
				this.customerList.add(customer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		boolean isNull = true;
		while(isNull) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("-----회원 조회-----");
			System.out.print("회원 이름: ");
			String customerName = scanner.next();
			System.out.print("전화번호: ");
			String phoneNum = scanner.next();
			
			if(this.customerList.search(customerName, phoneNum).equals(null)) {
				System.out.println("해당하는 고객 정보가 존재하지 않습니다. 재검색하여 주십시오.");
			}else {
				this.customer = this.customerList.search(customerName, phoneNum);
				System.out.println("환영합니다, "+this.customer.getName()+"님. 보험 가입을 진행합니다.");
				System.out.println("-----고객 조회 완료-----");
				isNull = false;
			}
		}
		
		return this.customer;
	}

	private Customer newCustomer() {
		//기존 고객 여부를 확인한 후 입력한 정보(가입자명, 주민/사업자번호, 연락처, 주소, 성별, 계약번호, 가입보험개수, 은행명, 계좌번호) 저장을 요청한다(A2, E3)
		System.out.println("신규 고객으로 회원가입을 진행합니다.");
		
		Scanner scanner = new Scanner(System.in);
		Customer customer = new Customer();		
		
		System.out.print("고객 이름: ");
		customer.setName(scanner.next());
		System.out.print("주민/사업자 번호: ");
		customer.setSSN(scanner.next());
		System.out.print("연락처: ");
		customer.setPhoneNum(scanner.next());
		System.out.print("주소: ");
		customer.setAddress(scanner.next());
		System.out.println("성별: [male, female, none]");
		System.out.print("선택: ");
		customer.setSex(scanner.next());
		System.out.print("은행 이름: ");
		customer.setBankName(scanner.next());
		System.out.print("계좌번호: ");
		customer.setAccountNum(scanner.next());
		
		//This new customer has only one "applyContract".
		customer.setInsuranceNum((double) 0);
		
		ArrayList<String> nullArray = new ArrayList<String>();
		nullArray.add("null");
		
		customer.setContractID(nullArray);
		customer.setRankID(nullArray);
		
		this.customerList.add(customer);
		//save Customer DB
		customer.register();
		System.out.println("신규 회원가입이 완료되었습니다.");
		return customer;
	}
}
