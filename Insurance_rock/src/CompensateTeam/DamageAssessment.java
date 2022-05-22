package CompensateTeam;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

import com.sun.tools.javac.Main;

import Accident.Accident;
import Contract.Contract;

public class DamageAssessment {
	

	Accident accident;
	Contract contract;
	public DamageAssessment() {
		this.contract = new Contract();
	}

	public boolean selectAccidentMenagement(Scanner scanner) {
		System.out.println("1. 사고검색, 2. 사고 추가, 0. 취소");
		String selectNum = scanner.next();
		this.accident = new Accident();
		
		switch (selectNum) {
		case "1":
			search();
			break;
		case "2":
			add();
			break;
		case "0":
			System.out.println("취소되었습니다. 전 선택창으로 돌아갑니다");
			return true;
		default:
			System.out.println("선택 이상함");
			break;
		}
		return false;
		
	}

	private void search() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("<사고내역을 출력할 가입자명과 사고날짜를 입력해 주세요.>");
		System.out.println("가입자명 :");
		String customerName_inser = scanner.nextLine();

		System.out.println("사고날짜 [년(공백) 월(공백) 일] :");
		String date = scanner.nextLine();

		String[] dateArray = date.split(" ");
		int[] intArray = new int[dateArray.length];

		for (int i = 0; i < intArray.length; i++) {
			intArray[i] = Integer.parseInt(dateArray[i]);

		}
		LocalDate accidentDate_inser = LocalDate.of(intArray[0], intArray[1], intArray[2]);

		boolean empty = false;
		if(!empty) {
			accident.search(customerName_inser, accidentDate_inser,empty);
			System.out.println("1. 보상금 지급, 2. 수정");
			String selectNum = scanner.next();
			switch (selectNum) {
			case "1":
				
				break;
			case "2":
				
				break;
			}
		}else {//E3. DB접근에 실패한 경우
			System.out.println(
					"DB 접근 오류: 정보 접근에 실패하였습니다. 해당 문제가 계속 발생할 시에는 사내시스템 관리팀(1234-5678)에게 문의 주시기 바랍니다.");
		}

	}


	private void add() {

		// 가입자명, 사고날짜, 연락처가 해당 고객과 동일하다면 거기에 이어서 사고내역 추가하기 검색은 불러오기.
		Scanner scanner = new Scanner(System.in);
		// 계약 DB에서 가져옴.
		System.out.println("<추가할 가입자명과 연락처를 입력해 주세요.>");
		System.out.println("가입자명 :");
		String customerName_inser = scanner.nextLine();

		System.out.println("연락처 :");
		String phoneNum_inser = scanner.nextLine();
		
		System.out.println("사고날짜 [년(공백) 월(공백) 일:");
		String date = scanner.nextLine();

		String[] dateArray = date.split(" ");
		int[] intArray = new int[dateArray.length];

		for (int i = 0; i < intArray.length; i++) {
			intArray[i] = Integer.parseInt(dateArray[i]);
		}
		LocalDate accidentDate_inser = LocalDate.of(intArray[0], intArray[1], intArray[2]);
		
		//임의의 데이터------------------------어떻게 가져와야할지 모르겠어서 우선 get set함.
		contract.setCustomerName("여다은");
		contract.setPhoneNum("01031932436");
		contract.setContractID("a");
		//---------------------------------
		String content = null;
		String kindOfCost= null;
		int damagePer =0;
		int totalCost=0;
		int liablityCost= 0;
		int liablityRate=0;
		if(contract.getCustomerName().equals(customerName_inser) && contract.getPhoneNum().equals(phoneNum_inser)) {
			//고객이 있다면, 사고를 접수할건데 그 데이터의 연결은 어떻게 하지?
			System.out.println("<사고내용, 종류, 손해정도, 총비용, 책임 비용, 책임비율을 입력하세요>");
			System.out.println("사고내용 :");
			content = scanner.nextLine();
			
			System.out.println("종류 : ");
			kindOfCost = scanner.next();

			System.out.println("(숫자)손해정도: ");
			damagePer = scanner.nextInt();

			System.out.println("(숫자)총 비용 :");
			totalCost = scanner.nextInt();

			System.out.println("(숫자)책임 비용");
			liablityCost = scanner.nextInt();

			System.out.println("(숫자)책임 비율");
			liablityRate = scanner.nextInt();
			
			accident.setID(UUID.randomUUID().toString());
			accident.setContractID(contract.getContractID());
			accident.setPayCompleted(false);
			accident.setAccidentDate(accidentDate_inser);
			accident.setCustomerName(contract.getCustomerName());
			accident.setPhoneNum(contract.getPhoneNum());
			accident.setContent(content);
			accident.setDamagePer(damagePer);
			accident.setKindOfCost(kindOfCost);
			accident.setTotalCost(totalCost);
			accident.setLiablityCost(liablityCost);
			accident.setLiablityRate(liablityRate);
			accident.add();//텍스트에 저장.
			System.out.println("사고정보가 추가되었습니다.");
		}else {//E2. 고객에 해당하는 계약이 존재하지 않는 경우
			System.out.println("해당 고객이 가입한 보험이 존재하지 않습니다. 다시 입력해주세요.");
		}
		// 사고정보(사고내용, 비용, 종류, 손해정도, 총비용, 책임 비용, 책임비율 )입력창과 계약 목록 선택창??과 추가, 취소버튼을 출력.
		
	}

}
