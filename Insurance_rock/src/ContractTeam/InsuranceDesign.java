package ContractTeam;

import java.util.Scanner;

import Insurance.GeneralInsurance;
import Insurance.HouseInsurance;
import Insurance.Insurance;

public class InsuranceDesign {

	public Insurance insurance;

	public boolean design() {
		String type;
		String longterm;
		boolean IsLongTerm = false;
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("보험 종류와 장기여부를 선택해 주세요.");
		System.out.println("1. 일반보험 2. 주택보험 0. 취소");
		type = scanner.next();
		System.out.println("1. 장기 2. 단기 0. 취소");
		
		
//		if(type.equals("1") || type.equals("일반보험")) {
//			
//		} else if(type.equals("1") || type.equals("일반보험")) {
//			
//		} else if(type.equals("1") || type.equals("일반보험")) {
//			
//		} else {type.equals("1") || type.equals("일반보험")}
//		
//		if(type.equals("1") || type.equals("일반보험")) {
//			
//		} else if(type.equals("2") || type.equals("일반보험")) {
//			
//		} else if(type.equals("0") || type.equals("일반보험")) {
//			
//		} else {}
		
		
		

//		if (scanner.nextInt() == 1) {
//			IsLongTerm = true;
//		} else {
//			IsLongTerm = false;
//		}
//
//		if (type == 1) {
//			this.insurance = new GeneralInsurance(longTerm);
//
//		} else if (type == 2) {
//			this.insurance = new HouseInsurance(longTerm);
//		} else {
//			System.out.println("잘 못 입력함");
//			System.exit(0);
//		}

		this.insurance.design();
		this.insurance.measureStandardFee();

		System.out.println("등록을 원하면 1번");
		if ((scanner.nextInt() == 1) && this.insurance.apply()) {
			System.out.println("등록 완료");
		}
		return true;
	}

}
