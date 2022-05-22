package SalesTeam;

import java.util.ArrayList;
import java.util.Scanner;

import Customer.Customer;
import Customer.CustomerList;
import Customer.CustomerListImpl;
import Insurance.Insurance;
import Insurance.Insurance.EInsurance;
import Insurance.InsuranceList;
import Insurance.InsuranceListImpl;

public class InsuranceSales {
	
	private InsuranceList insuranceList = new InsuranceListImpl();
	private CustomerList customerList = new CustomerListImpl();
	
	public InsuranceSales() {
		
	}

	public void searchInsurance(Scanner scanner) {
		System.out.println("가입하실 보험을 선택해주십시오.");
		System.out.println("1. 일반보험 2. 주택보험");
		String type = scanner.next();
		
		
		if(type.equals("1")) {
			ArrayList<Insurance> GeneralInsuranceList = this.insuranceList.get(EInsurance.general);
			System.out.println("------일반보험 목록------");
			for(int i = 0; i<GeneralInsuranceList.size(); i++) {
				System.out.println(GeneralInsuranceList.get(i).getInsuranceID()+" "+GeneralInsuranceList.get(i).getInsuranceName());
			}
			System.out.println("보험 이름을 입력하여 주십시오.");
			System.out.print("보험 이름: ");
			String insuranceName = scanner.next();
			Insurance insurance = this.insuranceList.get(insuranceName, EInsurance.general);
			
//			if(insurance.equals(null)) {
//				System.out.println("Excetion");
//				throw new Exception();
//			}
			
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
			if(answer.equals("1")) searchCustomer(scanner, insurance);
			else if(answer.equals("2")) System.out.println("가입을 취소합니다.");
			else System.out.println("잘못된 입력입니다.");
			
		}
	}

	public void searchCustomer(Scanner scanner, Insurance insurance) {
		System.out.println("1. 신규 가입자 2. 기존 보험 가입자");
		
		//test 
		Scanner scanner1 = new Scanner(System.in);
		System.out.println("고객 조회");
		System.out.print("고객 이름 입력: ");
		
		Customer customer = this.customerList.search("홍길동", "010-1234-5678");
		System.out.println("customer: "+ customer.getName()+" \n"
				+"Rank: "+customer.getRankList().get(0).getFireFacilities()+" ");
	}
}
