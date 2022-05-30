package Policyholder;

import java.util.Scanner;
import Payment.Payment;
import Provision.Provision;
import Customer.Customer;
import Customer.CustomerList;
import Customer.CustomerListImpl;

public class FeePayment {
	//customer부분 지우고 다시 해야됨. 
	private CustomerList customerList = new CustomerListImpl();
	Payment payment;
	Provision provision;
	
	public FeePayment() {
		this.payment = new Payment();
		this.payment.setPayment();
		this.provision = new Provision();
		this.provision.setProvision();
	}
	
	public void checkFee(Scanner scanner) {
		// TODO Auto-generated method stub
		
			//customer search
//			System.out.println("---고객 확인---");
//			System.out.println("성함과 연락처를 입력해주십시오. ");
//			System.out.println("성함 : ");
//			String name = scanner.next();
//			System.out.println("연락처 : ");
//			String p_num = scanner.next();
//			Customer customer = this.customerList.search(name, p_num);
//			System.out.println(customer.getAddress());
//			//납부 금액 정보(보험이름, 분납/일시불 여부, 보험료, 납부 한 금약, 미납액)와 납부 방법, 납부 기록, 지급 기록 버튼을 출력한다. 
//			System.out.println("---"+customer.getName()+"님의 납부 금액 정보" +"---"+ "\n" +
//								"");
////			임의 TXT 홍길동 012345-678900
			System.out.println("1. 납부 방법 선택 2. 납부 기록 확인 3. 지급 기록 확인 4. 취소");
			String selectNum = scanner.next();
			switch(selectNum) {
			case "1" : 
				choosePaymentMethod(scanner);
				break;
			case "2" : 
				payment.checkPayment();
				//납부 기록(가입자명, 연락처, 보험이름, 보험종류, 카드사/은행명, 카드/계좌번호, 납부금액, 납부일)을 출력한다
				break;
			case "3" : 
				provision.checkProvision();
//				지급 정보(보험이름,보험종류,장기여부,은행명,계좌번호,보상금액,보상일)를 출력한다
				break;
			case "4" : 
				//취소-홈화면 불러오기
			}
		}
		
	public void choosePaymentMethod(Scanner scanner) {
		System.out.println("납부방법(카드/통장)을 선택하십시오." + "\n" + "1. 카드  2. 통장");
		String method = scanner.next();
		System.out.println("카드사/은행명과 카드/계좌번호를 입력해주십시오. ");
		System.out.print("카드사/은행명 : ");
		String bankname = scanner.next();
		System.out.print("카드/계좌번호 : ");
		String accountname = scanner.next();
		System.out.println(bankname + accountname + "이게 아니고 account에 통장 금액 요청. ");
		//통장금액 요청해서 통장 금액 제공하기 (통장에 돈 있는지 확인용도)-??질문 . 여기서?
		//통장에 돈있으면 E1 E2 땜ㄴ에이슨거엿음.. 알려주기도해야돼. 
		System.out.println();
		System.out.println(" 납부방법 : " + method + "\n" +
					"1. 납부 2. 수정 3. 취소");		
		
		String selectNum = scanner.next();
		switch(selectNum) {
		case "1" :
			//payInsuranceFee class
			break;
		case "2" :
//			customer.edit;
			break;
		case "3" :
//			취소;set? 했던거 다시 원상태로. 
			break;
		
		}
	}
	public void payInsuranceFee() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("1. 일시불 납부   2. 할부 납부");
		String selectNum = scanner.next();
		if (selectNum.equals("1")) {
			//남은 금액 갱신, 납부 금액 추가, 해당하는 보험의 미납료 갱신, 납부내역 저장
			System.out.println("납부가 완료되었습니다. 납부확인서를 출력하시겠습니까?" + "\n" + "1. 예   2. 아니오");
			selectNum = scanner.next();
			if(selectNum.equals("1")) {
				//보험납부정보(보험이름, 보험종료, 카드사/은행명, 카드/계좌번호, 납부금액, 납부일)을 출력한다.
				//payment.get머시기			
			}
			else if(selectNum.equals("2")) {
				//취소하고 홈화면
			}
		}
		else if(selectNum.equals("2")) { 
			// A1 일부납부 . 아직!
		}
		
	}
		

	}


