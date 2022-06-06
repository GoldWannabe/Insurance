package View.Team;

import java.util.Scanner;

import Model.Accident.Accident;
import Model.Accident.AccidentListImpl;
import Model.Contract.Contract;
import Model.Contract.ContractListImpl;
import Model.Provision.Provision;

public class CompensateTeamTui {

	public void viewbasic() {
		System.out.println("<사고를 접수하기 위한 메뉴 선택해주세요.>");
		System.out.println("1. 검색, 2. 추가, 0. 취소 ");

	}

	public void cancelback() {
		System.out.println("<<<<< 취소되었습니다. 전 선택창으로 돌아갑니다 >>>>>");

	}

	public int viewcomapansateNum(Scanner scanner) {

		System.out.println("<보상금을 지급할 계약을 선택해주세요.>");
		int num;
		num = scanner.nextInt();
		return num;
	}

	public void cancelhome() {

		System.out.println("<<<<<<< 취소되었습니다. 홈 화면으로 돌아갑니다. >>>>>>>>>>");

	}

	public void viewContract(ContractListImpl contractList) {
		System.out.println("<사고를 추가할 계약을 선택하세요.>");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%15s %15s %15s %10s %10s %15s %20s %20s %15s %15s", "가입자명", "연락처", "보험이름", "납부방식", "보험료",
				"미납액", "담보액", "지급액", "가입일", "만료일");
		System.out.println();
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");
		for (Contract contract : contractList.getAll()) {
			System.out.format("%7s %20s %5s %10s %10s %10s %15s %10s %10s %10s",
					contract.getNum() + "." + contract.getCustomerName(), contract.getPhoneNum(),
					contract.getInsuranceName(), contract.getPaymentCycle(), contract.getInsuranceFee(),
					contract.getUnpaidFee(), contract.getSecurityFee(), contract.getProvisionFee(),
					contract.getStartDate(), contract.getEndDate());
			System.out.println();
		}
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("null")
	public String[] viewaddcheck(Scanner scanner) {
		System.out.println("<추가할 가입자명과 연락처를 입력해 주세요.>");
		System.out.println("가입자명 :");
		String customerName_inser = scanner.nextLine();

		System.out.println("연락처 :");
		String phoneNum_inser = scanner.nextLine();

		String[] inser = new String[2];
		inser[0] = customerName_inser;
		inser[1] = phoneNum_inser;

		return inser;
	}

	public void viewAccident(AccidentListImpl accidentList) {
		System.out.println(
				"---------------------------------------------------사고내역------------------------------------------------------------------------------------------------");
		System.out.printf("%10s %10s %20s %20s %10s %15s %15s %15s %15s %15s", "가입자명", "연락처", "사고날짜", "사고내용", "총비용",
				"손해정도", "비용종류", "지급여부", "책입비율", "책임비용");
		System.out.println();
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------------------------------");
		for (Accident accident : accidentList.getAll()) {

			System.out.format("%5s %15s %10s %10s %10s %10s %10s %10s %10s %10s",
					accident.getNum() + "." + accident.getCustomerName(), accident.getPhoneNum(),
					accident.getAccidentDate(), accident.getContent(), accident.getTotalCost(), accident.getDamagePer(),
					accident.getKindOfCost(), accident.isPayCompleted(), accident.getLiablityRate(),
					accident.getLiablityCost());
			System.out.println();

		}
	}

	public String[] veiwSearch(Scanner scanner) {
		String[] inser = new String[4];
		System.out.println("<사고내역을 출력할 가입자명과 사고날짜를 입력해 주세요.>");
		System.out.println("가입자명 :");
		String customerName_inser = scanner.next();
		System.out.println("---사고날짜 입력란---");
		System.out.println("년도 : ");
		String year = scanner.next();
		System.out.println("월 : ");
		String monty = scanner.next();
		System.out.println("일 : ");
		String date = scanner.next();
		System.out.println(customerName_inser);
		inser[0] = customerName_inser;
		inser[1] = year;
		inser[2] = monty;
		inser[3] = date;

		return inser;

	}

	public int viewUpdateNum(Scanner scanner) {
		System.out.println("<수정할 보험을 선택해주세요.>");
		int num;
		num = scanner.nextInt();
		return num;

	}

	public void viewUpdateList(Scanner scanner, Accident accident) {
		System.out.println("<수정할 항목을 선택해주세요.>");
		System.out.println("1. 사고날짜 : " + accident.getAccidentDate());
		System.out.println("2. 사고내용 :" + accident.getContent());
		System.out.println("3. 총비용 : " + accident.getTotalCost());
		System.out.println("4. 손해정도 : " + accident.getDamagePer());
		System.out.println("5. 비용종류 : " + accident.getKindOfCost());
		System.out.println("6. 책임비용 :" + accident.getLiablityCost());
		System.out.println("7. 책임비율 : " + accident.getLiablityRate());

	}

	public void viewcomapansate(Accident accident) {
		System.out.println(" 가입자명 : " + accident.getCustomerName() + "\n 연락처   : " + accident.getPhoneNum() + "\n 사고내용: "
				+ accident.getContent() + "\n 책임비용: " + accident.getLiablityCost());
		System.out.println("---------------------------------------------------------------------");
		System.out.println("위의 사고의 책임비용" + accident.getLiablityCost() + "원을 지급하겠습니까? \n 지급을 원하시면 1번, 취소를 원하시면 2번을 입력해주세요.");
	}

	public void viewCompansate() {
		System.out.println("1. 보상금 지급, 2. 수정");

	}

	public int[] viewUpdateDate(Scanner scanner) {
		int[] date = new int[3];
		System.out.println("<수정할 사고날짜를 입력해주세요>");
		System.out.println("년도 : ");
		date[0] = scanner.nextInt();
		System.out.println("월 : ");
		date[1] = scanner.nextInt();
		System.out.println("일 : ");
		date[2] = scanner.nextInt();

		return date;

	}

	public void viewUpdateContent() {
		System.out.println("<수정할 사고내용을 입력해주세요>");

	}

	public void viewUpdateTotalCost() {
		System.out.println("<수정할 총비용을 입력해주세요>");

	}

	public void viewUpdateDamagePer() {
		System.out.println("<수정할 손해정도를 입력해주세요>");

	}

	public void viewUpdateKindOfCost() {
		System.out.println("<수정할 비용종류를 입력해주세요>");

	}

	public void viewUpdateLiablityCost() {
		System.out.println("<수정할 책임비용을 입력해주세요>");

	}

	public void viewUpdateLiablityRate() {
		System.out.println("<수정할 책임비율을 입력해주세요>");

	}

	public void viewUpdateAccident(Accident accident) {
		System.out.println("<<사고정보가 변경되었습니다.>>");
		System.out.println(
				"-----------------------------------------------변경 된 사고정보 확인------------------------------------------------------------------------------------------------");
		System.out.printf("%10s %10s %20s %20s %15s %15s %15s %15s %15s %15s", "가입자명", "연락처", "사고날짜", "사고내용", "총비용",
				"손해정도", "비용종류", "지급여부", "책입비율", "책임비용");
		System.out.println();
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------------------------------");

		System.out.format("%5s %20s %12s %8s %11s %8s %10s %10s %12s %10s",
				accident.getNum() + "." + accident.getCustomerName(), accident.getPhoneNum(),
				accident.getAccidentDate(), accident.getContent(), accident.getTotalCost(), accident.getDamagePer(),
				accident.getKindOfCost(), accident.isPayCompleted(), accident.getLiablityRate(),
				accident.getLiablityCost());
		System.out.println();
		System.out.println("-------------------홈화면으로 돌아갑니다.-------------------");
	}


	public void viewNewProvision(Provision provision) {
		// 지급ID, 고객ID, 가입자명, 연락처, 계좌번호,은행명, 보상금액, 지급날짜, 보험이름, 장기여부, 보험종류, 계약ID를 저장.
		System.out.println("<<보험금 지급이 완료되었습니다.>>");
		System.out.println(
				"---------------------------------------------------새로 기록된 지급내역-------------------------------------------------------------------------------------------------");
		System.out.printf("%10s %10s %20s %20s %15s %15s %15s %15s %15s", "가입자명", "연락처", "계좌번호", "은행명", "보상금액", "지급날짜",
				"보험이름", "장기여부", "보험종류");
		System.out.println();
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------------------------------");

		System.out.format("%10s %15s %12s %8s %15s %15s %5s %10s %10s", provision.getCustomerName(),
				provision.getPhoneNum(), provision.getAccountNum(), provision.getBankName(),
				provision.getCompensation(), provision.getCompensationDate(), provision.getInsuranceName(),
				provision.isLongTerm(), provision.getInsuranceType());
		System.out.println();
		System.out.println("-------------------홈화면으로 돌아갑니다.-------------------");
	}

	public void viewOverCost() {
		System.out.println("<<담보액을 넘는 금액입니다. 담보액까지만 지급합니다.>>");

	}

	public void viewselectNumContract() {
		System.out.println("<사고를 추가할 계약을 선택해주세요.>");

	}

	public int[] viewAccidentinsert(Scanner scanner) {
		int[] inser = new int[3];
		System.out.println("<사고날짜, 사고내용, 종류, 손해정도, 총비용, 책임 비용, 책임비율을 입력하세요>");
		System.out.println("----사고날짜 입력란----");

		System.out.println("년도 : ");
		inser[0] = scanner.nextInt();
		System.out.println("월 : ");
		inser[1] = scanner.nextInt();
		System.out.println("일 : ");
		inser[2] = scanner.nextInt();

		return inser;
	}

	public String[] viewToString(Scanner scanner) {
		String[] toStirng = new String[2];
		System.out.println("사고내용 :");
		toStirng[0] = scanner.next();

		System.out.println("종류 : ");
		toStirng[1] = scanner.next();
		return toStirng;
	}

	public int[] viewToInt(Scanner scanner) {
		int[] toInt = new int[4];
		System.out.println("(숫자)손해정도: ");
		toInt[0] = scanner.nextInt();

		System.out.println("(숫자)총 비용 :");
		toInt[1] = scanner.nextInt();

		System.out.println("(숫자)책임 비용");
		toInt[2] = scanner.nextInt();

		System.out.println("(숫자)책임 비율");
		toInt[3] = scanner.nextInt();
		return toInt;
	}

	public void viewNewAccident(Accident accident) {
		System.out.println("<<사고정보가 추가되었습니다.>>");
		System.out.println(
				"------------------------------------------------------새로 기록된 사고내역------------------------------------------------------------------------------------------");
		System.out.printf("%10s %10s %20s %20s %15s %15s %15s %15s %15s %15s", "가입자명", "연락처", "사고날짜", "사고내용", "총비용",
				"손해정도", "비용종류", "지급여부", "책입비율", "책임비용");
		System.out.println();
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------------------------------");

		System.out.format("%5s %20s %12s %8s %10s %10s %10s %10s %10s %10s",
				accident.getNum() + "." + accident.getCustomerName(), accident.getPhoneNum(),
				accident.getAccidentDate(), accident.getContent(), accident.getTotalCost(), accident.getDamagePer(),
				accident.getKindOfCost(), accident.isPayCompleted(), accident.getLiablityRate(),
				accident.getLiablityCost());
		System.out.println();
		System.out.println();
		System.out.println("-------------------홈화면으로 돌아갑니다.-------------------");

	}

}
