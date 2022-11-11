package View.Team;

import Model.Payment.*;
import Model.Provision.Provision;
import Model.Provision.*;
import Model.Contract.*;

public class PolicyHolderTui {


	public void enterCustomerName() {
		// TODO Auto-generated method stub
		System.out.println("-------고객 확인--------");
		System.out.println("성함과 연락처를 입력해주십시오. ");
		System.out.print("성함 : ");
	}

	public void enterPhoneNum() {
		// TODO Auto-generated method stub
		System.out.print("연락처 : ");
	}

	public void showNopaymentFee() {
		// TODO Auto-generated method stub
		//'시스템의 문제로 납부 금액을 불러오지 못했습니다. 잠시후 다시 실행 해주십시오. 해당 문제가 계속 발생할 시에는 사내 시스템 관리팀(1234-5678)에게 문의 주시기 바랍니다.' 라는 팝업창을 띄운다
		System.out.println("해당 고객님의 납부금액정보가 존재하지 않습니다. 제대로 입력해주세요.");
	}
	public void showNoProvisionName() {
		// TODO Auto-generated method stub
		System.out.println("해당 고객님의 지급기록이 존재하지 않습니다. 제대로 입력해주세요.");
	}
	public void showWrongInput() {
		// TODO Auto-generated method stub
		System.out.println("제대로 입력해주세요. ");
	}
	public void showPaymentFee(ContractList contractList, String name, String phoneNum) {
		//납부 금액 정보(보험 이름, 분납/일시불 여부, 보험료, 납부 한 금액,미납액) 화면과 납부 방법, 납부 기록, 지급 기록 버튼을 출력한다
		// TODO Auto-generated method stub
		System.out.println("--------------------------고객님의 납부금액 정보------------------------------");
		System.out.printf("%10s %10s %9s %10s %10s", "보험 이름", "일시불/할부 여부", "보험료", "납부 완료 금액", "미납액" );
		System.out.println();
		System.out.println("---------------------------------------------------------------------------");
		for( Contract contract : contractList.getContractByNameAndNUm(name, phoneNum)) {
			
			System.out.format(contract.getNum() + "." + "%8s %10s %14s %11s %13s", contract.getInsuranceName(), contract.getPaymentCycle(), contract.getInsuranceFee(), contract.getInsuranceFee() - contract.getUnpaidFee(), contract.getUnpaidFee());
			System.out.println();
		}
		System.out.println();	
	}
	
	public void showSelectInsurance() {
		System.out.println("납부할 보험의 번호를 선택해주세요. ");
	}
	public void showStartMenu() {
		System.out.println("1. 보험료 납부 2. 납부 내역 확인 3. 지급 내역 확인");
	}
	public void showSelectMenu() {
		// TODO Auto-generated method stub
		System.out.println("1. 납부 방법 선택   2. 취소 ");
		
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
	public void showBalanceCheck() {
		System.out.println("(고객님의 통장잔고 확인 결과, 납부가 가능합니다. )");
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
		System.out.println("1. 납부방법 수정" + "\n" + "2. 납부 카드사/은행명 수정" + "\n" + "3. 납부 카드/계좌번호 수정" + "\n");
	}
	
	public void editMethod() {
		System.out.print(" 납부방법(카드/통장) : ");
	}
	public void editBankName() {
		System.out.println(" 납부 카드사/은행명 : ");
	}

	
	
	public void showCompleted() {
		// TODO Auto-generated method stub
		System.out.println("납부가 완료되었습니다. 납부확인서를 출력하시겠습니까?" + "\n" + "1. 예   2. 아니오");

	}
	public void showMonthlyCompleted(int unpaid) {
		// TODO Auto-generated method stub
		System.out.println("납부가 완료되었습니다. 미납액 : " + unpaid + "원 "+ "\n" +"납부확인서를 출력하시겠습니까?" + "\n" + "1. 예   2. 아니오");
		
	}

	public void showPaymentCheck(Payment payment) {
		// TODO Auto-generated method stub
//		//보험납부정보(보험이름, 보험종류, 카드사/은행명, 카드/계좌번호, 납부금액, 납부일)을 출력한다.
		System.out.println("------------------------------------납부확인서------------------------------------");
		System.out.println();
		System.out.printf("%10s %10s %10s %10s %10s %10s", "고객명", "보험 이름", "카드사/은행명", "카드/계좌번호", "납부 금액", "납부일" );
		System.out.println();
		System.out.format("%10s %10s %13s %11s %13s %13s", payment.getCustomerName(), payment.getInsuranceName(), payment.getCardOrBankName(), payment.getAccountNum(), payment.getInsuranceFee(), payment.getPaidDate());
		System.out.println();
	}

	public void cancel() {
		// TODO Auto-generated method stub
		System.out.println("취소되었습니다. 홈 화면으로 돌아갑니다." +"\n" );
	}

	public void showPaymentRecords(PaymentList paymentList, String name, String phoneNum) {
		// TODO Auto-generated method stub
//		납부 기록(가입자명, 연락처, 보험이름, 보험종류, 카드사/은행명, 카드/계좌번호, 납부금액, 납부일)을 출력한다
		System.out.println("-----------------------------고객님의 납부기록---------------------------------");
		System.out.printf("%10s %10s %10s %10s %10s %10s", "납부자명","보험이름", "카드사/은행명", "카드/계좌번호" , "납부금액", "납부일");
		System.out.println();
		System.out.println("---------------------------------------------------------------------------");
		for(Payment payment : paymentList.getPaymentBySearch(name, phoneNum)) {
			System.out.format("%10s %10s %13s %11s %10s %12s ", payment.getCustomerName(), payment.getInsuranceName(), payment.getCardOrBankName(), payment.getAccountNum(), payment.getInsuranceFee(), payment.getPaidDate());
			System.out.println();
		}
		System.out.println();
	}
	

	public void showProvisionRecords(ProvisionList provisionList, String name, String phoneNum) {
		// TODO Auto-generated method stub
//		지급 정보(보험이름,보험종류,장기여부,은행명,계좌번호,보상금액,보상일)를 출력한다		
		System.out.println("-----------------------------------고객님의 지급 기록 정보---------------------------------------");
		System.out.printf("%10s %10s %10s %10s %10s %10s %10s", "보험 이름", "보험종류", "장기여부", "은행명", "계좌번호", "보상금액", "보상일" );
		System.out.println();
		System.out.println("-------------------------------------------------------------------------------------------");
		for(Provision provision : provisionList.getProvisionBySearch(name, phoneNum)) {
			System.out.format("%10s %10s %13s %11s %13s %13s %13s", provision.getInsuranceName(), provision.getInsuranceType(), provision.isLongTerm(), provision.getBankName(), provision.getAccountNum(), provision.getCompensation(), provision.getCompensationDate());
			System.out.println();
		}
		System.out.println("\n");
		
	}

	public void showEditCompleted(String string) {
		// TODO Auto-generated method stub
		System.out.println(string + "(으)로 수정이 완료되었습니다.");
	}

	public void enterMonthlyFee() {
		// TODO Auto-generated method stub
		System.out.println("납부하실 금액을 입력해주세요.");
	}




	
	

	

}
