package CompensateTeam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

import Accident.Accident;
import Accident.AccidentListImpl;
import Contract.Contract;
import Contract.ContractListImpl;

public class DamageAssessment {

	private ContractListImpl contractList;
	private AccidentListImpl accidentList;
	private Accident accident;
	private Contract contract;

	public DamageAssessment() {
		this.accidentList = new AccidentListImpl();
		this.accident = new Accident();
		this.contractList = new ContractListImpl();
		this.contract = new Contract();

	}

	public boolean selectAccidentMenagement(Scanner scanner) {
		boolean isSearch = false;
		boolean overcheck = false;
		while (!isSearch) {
			System.out.println("사고를 접수하기 위한 메뉴 선택해주세요.");
			System.out.println("1. 검색, 2. 추가, 0. 취소 ");
			String selectNum = scanner.next();
			switch (selectNum) {
			case "1":
				overcheck = search();
				if (!overcheck) {
					showAccident();
					return compansate(scanner);
				}
				break;
			case "2":
				overcheck = addcheck();
				if (!overcheck) {
					showConctarct(scanner);
					return add(scanner);
				}
				break;
			case "0":
				System.out.println("취소되었습니다. 전 선택창으로 돌아갑니다");
				return true;
			default:
				System.out.println("선택 이상함");
				break;
			}
		}
		return isSearch;

	}

	private boolean compansate(Scanner scanner) {
		System.out.println("1. 보상금 지급, 2. 수정");
		String selectNum = scanner.next();
		switch (selectNum) {
		case "1":
			return paycompansate(scanner);
			
		case "2":
			update(scanner);
			break;
		}
		return false;

	}	

	private boolean paycompansate(Scanner scanner) {
		System.out.println("보상금을 지급할 계약을 선택해주세요.");
		int num;
		num = scanner.nextInt();
		this.accident = accidentList.getNum(num);
		System.out.println(" 가입자명 : " + this.accident.getCustomerName()+"\n 연락처: "+ this.accident.getPhoneNum()+ "\n 사고번호 : "+ this.accident.getID() + 
				"\n 의 사고의"+ this.accident.getLiablityCost() + "원을 지급하겠습니까? \n 지급을 원하시면 1번, 취소를 원하시면 2번을 입력해주세요.");
		String selectNum = scanner.next();
		if(selectNum.equals("1")) {
			requestconstomer();
		}else if(selectNum.equals("2")) {
			System.out.println("홈 화면으로 돌아갑니다.");
			return true;
		}
		return false;
		
	}

	private void requestconstomer() {
		
		// TODO Auto-generated method stub
		
	}

	private void showAccident() {
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%10s %35s %7s %7s %10s %20s %18s %15s %15s %15s %15s %15s", "사고ID", "계약ID", "고객ID", "가입자명",
				"연락처", "사고날짜", "사고내용", "총비용", "손해정도", "비용종류", "지급여부", "책입비율", "책임비용");
		System.out.println();
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------------------------------");

		for (Accident accident : this.accidentList.getAll()) {

			System.out.format("%5s %7s %7s %7s %20s %12s %7s %15s %7s %12s %15s %10s",
					accident.getNum() + "." + accident.getID(), accident.getContractID(), accident.getCustomerID(),
					accident.getCustomerName(), accident.getPhoneNum(), accident.getAccidentDate(),
					accident.getContent(), accident.getTotalCost(), accident.getDamagePer(), accident.getKindOfCost(),
					accident.isPayCompleted(), accident.getLiablityRate(), accident.getLiablityCost());
			System.out.println();

		}

	}

	private void showConctarct(Scanner scanner) {
		// CosntractList.

		System.out.println("<사고를 추가할 계약을 선택하세요.>");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%5s %7s %7s %7s %10s %20s %18s %15s %15s %15s %15s %15s %15s", "계약ID", "고객ID", "가입자명", "연락처",
				"보험ID", "보험이름", "납부방식", "보험료", "미납액", "담보액", "지급액", "가입일", "만료일");
		System.out.println();
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");
		for (Contract contract : this.contractList.getAll()) {
			System.out.format("%5s %7s %7s %7s %20s %12s %7s %15s %7s %12s %15s %10s %10s", contract.getContractID(),
					contract.getCustomerID(), contract.getCustomerName(), contract.getPhoneNum(),
					contract.getInsuranceID(), contract.getInsuranceName(), contract.getPaymentCycle(),
					contract.getInsuranceFee(), contract.getUnpaidFee(), contract.getSecurityFee(),
					contract.getProvisionFee(), contract.getStartDate(), contract.getEndDate());
			System.out.println();
		}

		// TODO Auto-generated method stub

	}

	private boolean addcheck() {

		// 가입자명, 사고날짜, 연락처가 해당 고객과 동일하다면 거기에 이어서 사고내역 추가하기 검색은 불러오기.
		Scanner scanner = new Scanner(System.in);
		// 계약 DB에서 가져옴.
		System.out.println("<추가할 가입자명과 연락처를 입력해 주세요.>");
		System.out.println("가입자명 :");
		String customerName_inser = scanner.nextLine();

		System.out.println("연락처 :");
		String phoneNum_inser = scanner.nextLine();

		contract.setCustomerName(customerName_inser);
		contract.setPhoneNum(phoneNum_inser);
		//
		ResultSet resultSet = contract.retrivecontract();
		// 사고번호ID, 계약ID ,고객ID,가입자명, 연락처,사고날짜,사고내용 ,총비용,손해정도,비용종류,지급여부,책임비율,책임비용

		try {
			while (resultSet.next()) {
				Contract contract = new Contract();
				contract.setContractID(resultSet.getString("contractID"));
				contract.setCustomerID(resultSet.getString("customerID"));
				contract.setCustomerName(resultSet.getString("customerName"));
				contract.setPhoneNum(resultSet.getString("customerPhoneNum"));
				contract.setInsuranceID(resultSet.getString("insuranceID"));
				contract.setInsuranceName(resultSet.getString("insuranceName"));
				contract.setPaymentCycle(resultSet.getInt("paymentCycle"));
				contract.setInsuranceFee(resultSet.getInt("insuranceFee"));
				contract.setUnpaidFee(resultSet.getInt("unpaidFee"));
				contract.setSecurityFee(resultSet.getInt("securityFee"));
				contract.setProvisionFee(resultSet.getInt("provisionFee"));
				contract.setStartDate(LocalDate.parse(resultSet.getString("startDate")));
				contract.setEndDate(LocalDate.parse(resultSet.getString("endDate")));
				contractList.add(contract);
			} // 계약ID, 고객ID, 가입자명, 연락처, 보험ID, 보험이름, 납부방식, 보험료, 미납액, 담보액, 지급액, 가입일, 만료일
		} catch (SQLException e) {
			System.out.println(
					"파일 접근 중 문제가 생겨 사고정보를 불러오지 못했습니다. 잠시후 다시 실행 해주십시오. 해당 문제가 계속 발생할 시에는 사내 시스템 관리팀(1234-5678)에게 문의 주시기 바랍니다.");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		boolean overcheck = false;
		while (!overcheck) {
			if (this.contractList.getcheck(customerName_inser, phoneNum_inser) != null) {
				return overcheck = false;
			} else {
				System.out.println("해당 계약이 존재하지 않습니다. 다시 입력해주세요.");
				return overcheck = true;
			}
		}
		return overcheck;

	}

	// 사고정보(사고내용, 비용, 종류, 손해정도, 총비용, 책임 비용, 책임비율 )입력창과 계약 목록 선택창??과 추가, 취소버튼을 출력.

	private boolean search() {
		Scanner scanner = new Scanner(System.in);
		boolean overcheck = true;

		System.out.println("<사고내역을 출력할 가입자명과 사고날짜를 입력해 주세요.>");
		System.out.println("가입자명 :");
		String customerName_inser = scanner.nextLine();
		System.out.println("<사고날짜>");
		System.out.println("년도 : ");
		int year = scanner.nextInt();
		System.out.println("월 : ");
		int monty = scanner.nextInt();
		System.out.println("일 : ");
		int date = scanner.nextInt();

//		String[] dateArray = date.split(" ");
//		int[] intArray = new int[dateArray.length];
//
//		for (int i = 0; i < intArray.length; i++) {
//			intArray[i] = Integer.parseInt(dateArray[i]);
//
//		}
		LocalDate accidentDate_inser = LocalDate.of(year, monty, date);
		accident.setCustomerName(customerName_inser);
		accident.setAccidentDate(accidentDate_inser);

		ResultSet resultSet = accident.retriveaccident();
		int num = 1;
		try {
			while (resultSet.next()) {
				Accident accident = new Accident();
				accident.setNum(num);
				accident.setID(resultSet.getString("accidentID"));
				accident.setContractID(resultSet.getString("contractID"));
				accident.setCustomerID(resultSet.getString("customerID"));
				accident.setCustomerName(resultSet.getString("customerName"));
				accident.setPhoneNum(resultSet.getString("customerPhoneNum"));
				accident.setAccidentDate(LocalDate.parse(resultSet.getString("accidentDate")));
				accident.setContent(resultSet.getString("content"));
				accident.setTotalCost(resultSet.getInt("totalCost"));
				accident.setDamagePer(resultSet.getInt("damagePer"));
				accident.setKindOfCost(resultSet.getString("kindOfCost"));
				accident.setPayCompleted(resultSet.getBoolean("payCompleted"));
				accident.setLiablityRate(resultSet.getInt("liablityRate"));
				accident.setLiablityCost(resultSet.getInt("liablityCost"));
				overcheck = false;
				num++;
				accidentList.add(accident);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(accidentList.get(customerName_inser, accidentDate_inser) != null) {
			return overcheck;
		}else {
			System.out.println("사고정보를 찾지못했습니다.사고날짜와 가입자명을 오탈자없이 적어주세요");
		}

//			isSearch = accident.search(customerName_inser, accidentDate_inser, isSearch);

		return overcheck;

	}

	private void update(Scanner scanner) {
		System.out.println("수정할 보험을 선택해주세요.");
		int num;
		num = scanner.nextInt();
		this.accident = accidentList.getNum(num);
		
		if(num != 0) {
			System.out.println("수정할 항목을 선택해주세요.");
			System.out.println("1. 사고날짜 : "+ this.accident.getAccidentDate());
			System.out.println("2. 사고내용 :"+ this.accident.getContent());
			System.out.println("3. 총비용 : "+this.accident.getTotalCost());
			System.out.println("4. 손해정도 : "+this.accident.getDamagePer());
			System.out.println("5. 비용종류 : "+ this.accident.getKindOfCost());
			System.out.println("6. 책임비용 :"+this.accident.getLiablityCost());
			System.out.println("7. 책임비율 : "+this.accident.getLiablityRate());	
			String selectNum = scanner.next();
			if(selectNum.equals("1")) {
				System.out.println("수정할 사고날짜를 입력해주세요");
				System.out.println("년도 : ");
				int year = scanner.nextInt();
				System.out.println("월 : ");
				int monty = scanner.nextInt();
				System.out.println("일 : ");
				int date = scanner.nextInt();
				LocalDate accidentdate = LocalDate.of(year, monty, date);
				this.accident.updatedate(accidentdate);
				this.accident.setAccidentDate(accidentdate);
			}else if(selectNum.equals("2")) {
				System.out.println("수정할 사고내용을 입력해주세요");
				String content = scanner.next();
				accident.updatecontent(content);
				this.accident.setContent(content);
			}else if(selectNum.equals("3")) {
				System.out.println("수정할 총비용을 입력해주세요");
				int totalCost = scanner.nextInt();
				accident.updatetotal(totalCost);
				this.accident.setTotalCost(totalCost);
			}else if(selectNum.equals("4")) {
				System.out.println("수정할 손해정도를 입력해주세요");
				int damagePer = scanner.nextInt();
				accident.updateDamage(damagePer);
				this.accident.setDamagePer(damagePer);
			}else if(selectNum.equals("5")) {
				System.out.println("수정할 비용종류를 입력해주세요");
				String kindOfCost = scanner.next();
				accident.updateKind(kindOfCost);
				this.accident.setKindOfCost(kindOfCost);
			}else if(selectNum.equals("6")) {
				System.out.println("수정할 책임비용을 입력해주세요");
				int liablityCost= scanner.nextInt();
				accident.updateLiablityCost(liablityCost);
				this.accident.setLiablityCost(liablityCost);
			}else if(selectNum.equals("7")) {
				System.out.println("수정할 총비율을 입력해주세요");
				int liablityRate = scanner.nextInt();
				accident.updateLiablityRate(liablityRate);
				this.accident.setLiablityRate(liablityRate);
			}
			
			System.out.println("사고정보가 변경되었습니다.");
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.printf("%10s %35s %7s %7s %10s %20s %18s %15s %15s %15s %15s %15s", "사고ID", "계약ID", "고객ID", "가입자명",
					"연락처", "사고날짜", "사고내용", "총비용", "손해정도", "비용종류", "지급여부", "책입비율", "책임비용");
			System.out.println();
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------------------------------------------------");

				System.out.format("%5s %7s %7s %7s %20s %12s %7s %15s %7s %12s %15s %10s",
						accident.getNum() + "." + accident.getID(), accident.getContractID(), accident.getCustomerID(),
						accident.getCustomerName(), accident.getPhoneNum(), accident.getAccidentDate(),
						accident.getContent(), accident.getTotalCost(), accident.getDamagePer(), accident.getKindOfCost(),
						accident.isPayCompleted(), accident.getLiablityRate(), accident.getLiablityCost());
				System.out.println();

			System.exit(0);
			
		}
		
		

	}


	private boolean add(Scanner scanner) {

		String contractID = null;
		boolean isSearch = false;
		while (!isSearch) {
			System.out.println("<<사고를 추가할 계약번호(ID)을 입력해주세요.>>");
			contractID = scanner.next();
			if (this.contractList.get(contractID) != null) {
				isSearch = true;
			} else {
				System.out.println("해당 계약이 존재하지 않습니다. 다시 입력해주세요.");

			}
		}
		if (isSearch) {

			String content = null;
			String kindOfCost = null;
			int damagePer = 0;
			int totalCost = 0;
			int liablityCost = 0;
			int liablityRate = 0;

			System.out.println("<사고날짜, 사고내용, 종류, 손해정도, 총비용, 책임 비용, 책임비율을 입력하세요>");
			System.out.println("<사고날짜>");
			System.out.println("년도:");
			int year = scanner.nextInt();
			System.out.println("월:");
			int month = scanner.nextInt();
			System.out.println("일");
			int date = scanner.nextInt();

			LocalDate accidentDate_inser = LocalDate.of(year, month, date);

			System.out.println("사고내용 :");
			content = scanner.next();

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

			// accidentList.get(customerName_inser, phoneNum_inser);
			Contract contract = this.contractList.get(contractID);

			accident.setID(UUID.randomUUID().toString());
			accident.setContractID(contract.getContractID());
			accident.setCustomerID(contract.getCustomerID());
			accident.setCustomerName(contract.getCustomerName());
			accident.setPhoneNum(contract.getPhoneNum());
			accident.setAccidentDate(accidentDate_inser);
			accident.setContent(content);
			accident.setKindOfCost(kindOfCost);
			accident.setDamagePer(damagePer);
			accident.setTotalCost(totalCost);
			accident.setLiablityCost(liablityCost);
			accident.setPayCompleted(false);
			accident.setLiablityRate(liablityRate);
			accidentList.add(accident);
		} else {
			System.out.println("추가가 취소되었습니다. 메인화면으로 돌아갑니다.");
		}
		// 사고번호ID, 계약ID ,고객ID,가입자명, 연락처,사고날짜,사고내용 ,총비용,손해정도,비용종류,지급여부,책임비율,책임비용
//				accidentList.addaccident(accidentDate_inser, content, kindOfCost, damagePer, totalCost, liablityCost, liablityRate);

		if (accident.addaccident()) {
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.printf("%5s %7s %7s %7s %10s %20s %18s %15s %15s %15s %15s %15s", "사고ID", "계약ID", "고객ID", "고객이름",
					"연락처", "사고날짜", "사고내용", "총비용", "손해정도", "비용종류", "지급여부", "책입비율", "책임비용");
			System.out.println();
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------------------------------------------------");

			System.out.format("%5s %7s %7s %7s %20s %12s %7s %15s %7s %12s %15s %10s", accident.getID(),
					accident.getContractID(), accident.getCustomerID(), accident.getCustomerName(),
					accident.getPhoneNum(), accident.getAccidentDate(), accident.getContent(), accident.getTotalCost(),
					accident.getDamagePer(), accident.getKindOfCost(), accident.isPayCompleted(),
					accident.getLiablityRate(), accident.getLiablityCost());
			System.out.println();
			System.out.println("사고정보가 추가되었습니다.");
		}
		return isSearch;

	}

}
