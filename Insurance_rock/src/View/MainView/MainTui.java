package View.MainView;

public class MainTui {

	public void showSelectTeam() {
		System.out.println("원하시는 메뉴에 해당하는 번호를 입력해주세요.");
		System.out.println("1. 계약팀, 2. 금감원, 3. 마켓팅/영업팀, 4. 보상팀, 5.고객");

	}

	public void showSelectContractTeam() {
		System.out.println("원하시는 메뉴에 해당하는 번호를 입력해주세요.");
		System.out.println("1. 설계, 2. 인수심사,  3. 계약관리 0. 종료");

	}

	public void showWrongInput() {
		System.out.println("입력이 잘못 되었습니다. 다시 입력해주세요.");

	}

	public void showSelectFinancialDirector() {
		System.out.println("원하시는 메뉴에 해당하는 번호를 입력해주세요.");
		System.out.println("1. 보험 심사, 0. 종료");

	}

	public void showClose() {
		System.out.println("시스템을 종료 합니다.");
	}

	public void showSalesTeam() {
		System.out.println("원하시는 메뉴에 해당하는 번호를 입력해주세요.");
		System.out.println("1. 보험 판매, 2. 고객 정보 관리, 3. 판매채널 관리, 0. 종료");
		
	}

	public void showCompensateTeam() {
		System.out.println("원하시는 메뉴에 해당하는 번호를 입력해주세요.");
		System.out.println("1. 손해사정, 0. 취소 ");

	}

}
