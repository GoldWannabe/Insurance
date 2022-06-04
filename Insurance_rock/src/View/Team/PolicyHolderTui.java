package View.Team;

import Model.Payment.Payment;
import Model.Provision.Provision;

public class PolicyHolderTui {


	public void enterCustomerName() {
		// TODO Auto-generated method stub
		System.out.println("-------고객 확인--------");
		System.out.println("성함과 연락처를 입력해주십시오. ");
		System.out.print("성함 : " + "\n");
	}

	public void enterPhoneNum() {
		// TODO Auto-generated method stub
		System.out.print("연락처 : ");
	}

	public void showNopaymentName() {
		// TODO Auto-generated method stub
		System.out.println("해당 고객님의 납부기록이 존재하지 않습니다. 제대로 입력해주세요.");
	}
	public void showNoProvisionName() {
		// TODO Auto-generated method stub
		System.out.println("해당 고객님의 지급기록이 존재하지 않습니다. 제대로 입력해주세요.");
	}

	public void showPaymentFee(Payment payment) {
		// TODO Auto-generated method stub
		System.out.println("-----------------------------" + payment.getCustomerName()+" 님의 납부금액 정보---------------------------------");
		System.out.printf("%10s %10s %10s %10s %10s", "보험 이름", "일시불/할부 여부", "보험료", "납부 완료 금액", "미납액" );
		System.out.println();
		System.out.println("---------------------------------------------------------------------------");
		System.out.format("%10s %10s %13s %11s %13s", payment.getInsuranceName(), payment.getInsuranceFee(), payment.getInsuranceFee(), payment.getInsuranceFee(), payment.getInsuranceFee());
		System.out.println();
		//이거 줄띄우기 왜 안돼?
		
	}

	public void showSelectMenu() {
		// TODO Auto-generated method stub
		System.out.println("1. 납부 방법 선택 2. 납부 기록 확인 3. 취소 ");
		
	}

	public void selectMethod() {
		// TODO Auto-generated method stub
		System.out.println("납부방법(카드/통장)을 선택하십시오." + "\n" + "카드  /  통장");
	}

	public void selectBankName() {
		// TODO Auto-generated method stub
		System.out.println("카드사/은행명과 카드/계좌번호를 입력해주십시오. ");
		System.out.print("카드사/은행명 : ");
	}

	public void selectAccountNum() {
		// TODO Auto-generated method stub
		System.out.print("카드/계좌번호 : ");
	}

	public void showMethod(String method, String name, String num) {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println("납부방법 : " + method + "    | 납부 카드사/은행명 : " + name + "     | 납부 카드/계좌번호 : " + num);
		System.out.println();
		System.out.println("1. 납부   2. 수정   3. 취소");
		
	}

	public void selectPayTerm() {
		// TODO Auto-generated method stub
		System.out.println("1. 일시불 납부   2. 할부 납부");

	}
	
	public void showEdit() {
		System.out.println();
		System.out.println("1. 납부방법 수정" + "\n" + "1. 납부방법 수정" + "\n" + "3. 납부 카드사/은행명 수정" + "\n");
	}
	
	public void editMethod() {
		System.out.print(" 납부방법 : ");
	}

	
	
	public void showCompleted() {
		// TODO Auto-generated method stub
		System.out.println("납부가 완료되었습니다. 납부확인서를 출력하시겠습니까?" + "\n" + "1. 예   2. 아니오");

	}

	public void showPaymentCheck(Payment payment) {
		// TODO Auto-generated method stub
//		//보험납부정보(보험이름, 보험종류, 카드사/은행명, 카드/계좌번호, 납부금액, 납부일)을 출력한다.
		System.out.println("-------------------------------납부확인서-------------------------------");
		System.out.println();
		System.out.printf("%10s %10s %10s %10s %10s %10s", "고객명", "보험 이름", "카드사/은행명", "카드/계좌번호", "납부 완료 금액", "납부일" );
		System.out.println();
		System.out.format("%10s %10s %13s %11s %13s %13s", payment.getCustomerName(), payment.getInsuranceName(), payment.getCardOrBankName(), payment.getAccountNum(), payment.getInsuranceFee(), payment.getPaidDate());
		System.out.println();
	}

	public void cancel() {
		// TODO Auto-generated method stub
		System.out.println("취소되었습니다. 홈 화면으로 돌아갑니다.");
	}

	public void showPaymentRecords(Payment payment) {
		// TODO Auto-generated method stub
		System.out.println("-----------------------------" + payment.getCustomerName()+" 님의 납부기록---------------------------------");
		System.out.printf("%10s %10s %10s %10s %10s", "보험 이름", "일시불/할부 여부", "보험료", "납부 완료 금액", "미납액" );
		System.out.println();
		System.out.println("---------------------------------------------------------------------------");
		System.out.format("%10s %10s %13s %11s %13s", payment.getInsuranceName(), payment.getInsuranceFee(), payment.getInsuranceFee(), payment.getInsuranceFee(), payment.getInsuranceFee());
		System.out.println();
	}

	public void showProvisionRecords(Provision provision) {
		// TODO Auto-generated method stub
//		지급 정보(보험이름,보험종류,장기여부,은행명,계좌번호,보상금액,보상일)를 출력한다		System.out.println("-----------------------------" + provision.getCustomerName()+" 님의 납부기록---------------------------------");
		System.out.printf("%10s %10s %10s %10s %10s %10s %10s", "보험 이름", "보험종류", "장기여부", "은행명", "계좌번호", "보상금액", "보상일" );
		System.out.println();
		System.out.println("---------------------------------------------------------------------------");
		System.out.format("%10s %10s %13s %11s %13s %13s %13s", provision.getInsuranceName(), provision.getInsuranceType(), provision.isLongTerm(), provision.getBankName(), provision.getAccountNum(), provision.getCompensation(), provision.getCompensationDate());
		System.out.println();
	}

	public void showEditCompleted() {
		// TODO Auto-generated method stub
		System.out.println("수정이 완료 되었습니다.");
	}


	
	

	

}
