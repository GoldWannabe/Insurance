package Control.Policyholder;

import java.sql.ResultSet;
import View.Team.PolicyHolderTui;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

import Model.Payment.*;
import Model.Provision.*;
import Model.Customer.*;
//나 customer왜 가져옴?

public class FeePayment {
	PolicyHolderTui policyholderTUI;
	public Payment payment;
	public PaymentList paymentList = new PaymentListImpl();

//	public Contract contract;
	private Provision provision;
	private ProvisionList provisionList = new ProvisionListImpl();

	private Customer customer;
	private CustomerList customerList = new CustomerListImpl();
	
//하......납부해야할 금액을 확인한다 usecase 너무어색함...고쳐야해
	
	public FeePayment() {
		this.policyholderTUI = new PolicyHolderTui();
	}
	
	public boolean checkFee() {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		this.paymentList = new PaymentListImpl();
		this.payment = new Payment();
		//아아아아아아아앗 payment 고객 에 두개의 payment잇네 아놔 . . . 좀따. 이거 처리하기
		ResultSet resultSet = this.payment.getPayment();
		
		try {
			while (resultSet.next()) {
				Payment tempPayment = new Payment();
				tempPayment.setPaymentID(resultSet.getString("paymentID"));
				tempPayment.setCustomerID(resultSet.getString("customerID"));
				tempPayment.setCustomerName(resultSet.getString("customerName"));
				tempPayment.setCustomerPhoneNum(resultSet.getString("customerPhoneNum"));
				tempPayment.setAccountNum(resultSet.getString("accountNum"));
				tempPayment.setCardOrBankName(resultSet.getString("cardOrBankName"));
				tempPayment.setInsuranceFee(resultSet.getInt("insuranceFee"));
				tempPayment.setInsuranceName(resultSet.getString("insuranceName"));
				tempPayment.setPaidDate(LocalDate.parse(resultSet.getDate("paidDate").toString()));
				tempPayment.setContractID(resultSet.getString("contractID"));
				this.paymentList.add(tempPayment);
			}
//없는거 처리 @@	if(this.paymentList.getAll)
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		return selectCustomer(scanner);
	}
	
	private boolean selectCustomer(Scanner scanner) {
		this.policyholderTUI.enterCustomerName();
		String name = scanner.next();
		this.policyholderTUI.enterPhoneNum();
		String phoneNum = scanner.next();
		this.payment = this.paymentList.get(name, phoneNum);
		//여기 오류 처리 다시 !!
		if ( this.payment == null) {
			this.policyholderTUI.showNopaymentName();
			this.selectCustomer(scanner);
		}	
		this.policyholderTUI.showPaymentFee(this.payment);
		return selectMenu(scanner);
		

	}
	private boolean selectMenu(Scanner scanner) {
		this.policyholderTUI.showSelectMenu();
		String selectNum = scanner.next();
		switch(selectNum) {
		case "1" : 
			 return choosePaymentMethod(scanner);
//			 break;
		case "2" : 
			return checkPayment(scanner);
//			break;
		case "3" : 
			this.policyholderTUI.cancel();
		}
		//다른 숫자넣었을 때..
		return false;
	}
	

	private boolean choosePaymentMethod(Scanner scanner) {
		this.policyholderTUI.selectMethod();
		String method = scanner.next();
		this.policyholderTUI.selectBankName();
		String name = scanner.next();
		this.policyholderTUI.selectAccountNum();
		String num = scanner.next();
		this.policyholderTUI.showMethod(method, name, num);
		//통장금액 요청
		//통장에돈잇음
		this.payment.createPayment();
		this.payment.setAccountNum(num);
		this.payment.setCardOrBankName(name);
		this.payment.setInsuranceFee(payment.getInsuranceFee());
		this.payment.setCustomerName(payment.getCustomerName());
		this.payment.setInsuranceName(payment.getInsuranceName());
		this.payment.setCustomerID(payment.getCustomerID());
		this.payment.setCustomerPhoneNum(payment.getCustomerPhoneNum());
		this.payment.setContractID(payment.getContractID());
		
		String selectNum = scanner.next();
		switch(selectNum) {
		case "1" :
			return payInsuranceFee(scanner);
		}
		return false;
	}

	private boolean checkPayment(Scanner scanner) {
		// TODO Auto-generated method stub
		this.policyholderTUI.showPaymentRecords(this.payment);
		return false;
	}
	
	private boolean payInsuranceFee(Scanner scanner) {
		this.policyholderTUI.selectPayTerm();
		String term = scanner.next();
		if (term.equals("1")) {
//			ResultSet resultSet2 = this.payment.getPayment();
//		//남은 금액 갱신, 납부 금액 추가, 해당하는 보험의 미납료 갱신, 납부내역 저장
			this.policyholderTUI.showCompleted();
			String selectNum = scanner.next();
			if(selectNum.equals("1")) {
				this.payment.addPayment();
				this.policyholderTUI.showPaymentCheck(this.payment);
		}
		else if(selectNum.equals("2")) {
			this.policyholderTUI.cancel();
		}
//	}
//	else if(selectNum.equals("3")) { 
//		// A1 일부납부 . 아직!
//	}
//	}
		}
		return false;
	}

	public void checkProvision() {
		// TODO Auto-generated method stub
		//지급 정보(보험이름,보험종류,장기여부,은행명,계좌번호,보상금액,보상일)를 출력한다
		this.provisionList = new ProvisionListImpl();
		this.provision = new Provision();
		//아아아아아아아앗 payment 고객 에 두개의 payment잇네 아놔 . . . 좀따. 이거 처리하기
		ResultSet resultSet = this.provision.getProvision();
		
		try {
			while (resultSet.next()) {
				Provision tempProvision = new Provision();
				tempProvision.setProvisionID(resultSet.getString("provisionID"));
				tempProvision.setAccountNum(resultSet.getString("accountNum"));
				tempProvision.setBankName(resultSet.getString("bankName"));
				tempProvision.setCompensation(resultSet.getInt("compensation"));
				tempProvision.setCompensationDate(LocalDate.parse(resultSet.getDate("compensationDate").toString()));
				tempProvision.setCustomerName(resultSet.getString("customerName"));
//				tempProvision.setInsuranceID(resultSet.getInt("insuranceFee"));
				tempProvision.setLongTerm(resultSet.getBoolean("longTerm"));
				//setInsuracneType 어캐하노
//				tempProvision.setInsuranceType(resultSet.getS);
				tempProvision.setContractID(resultSet.getString("contractID"));
//				this.paymentList.add(tempProvision);
			}
//없는거 처리 @@	if(this.paymentList.getAll)
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
	}
	
	
}

//		
//		
//			System.out.println("1. 납부 방법 선택 2. 납부 기록 확인 3. 지급 기록 확인 4. 취소");
////			String selectNum = scanner.next();
////			switch(selectNum) {
////			case "1" : 
////				choosePaymentMethod(scanner);
////				break;
////			case "2" : 
//////				payment.checkPayment();
////				//납부 기록(가입자명, 연락처, 보험이름, 보험종류, 카드사/은행명, 카드/계좌번호, 납부금액, 납부일)을 출력한다
////				break;
////			case "3" : 
//////				provision.checkProvision();
//////				//지급 정보(보험이름,보험종류,장기여부,은행명,계좌번호,보상금액,보상일)를 출력한다
////				break;
////			case "4" : 
////				//���-Ȩȭ�� �ҷ�����
////			}
//		}

	
//	public void choosePaymentMethod(Scanner scanner) {
//		System.out.println("납부방법(카드/통장)을 선택하십시오." + "\n" + "1. 카드  2. 통장");
//		String method = scanner.next();
//		System.out.println("카드사/은행명과 카드/계좌번호를 입력해주십시오. ");
//		System.out.print("카드사/은행명 : ");
//		String bankname = scanner.next();
//		System.out.print("카드/계좌번호 : ");
//		String accountname = scanner.next();
//		System.out.println(bankname + accountname + "이게 아니고 account에 통장 금액 요청. ");
//		//통장금액 요청해서 통장 금액 제공하기 (통장에 돈 있는지 확인용도)-??질문 . 여기서?
//		//통장에 돈있으면 E1 E2 땜ㄴ에이슨거엿음.. 알려주기도해야돼. 
//		System.out.println();
//		System.out.println(" 납부방법 : " + method + "\n" +
//					"1. 납부 2. 수정 3. 취소");		
//		
//		String selectNum = scanner.next();
//		switch(selectNum) {
//		case "1" :
//			//payInsuranceFee class
//			break;
//		case "2" :
////			customer.edit;
//			break;
//		case "3" :
////			취소;set? 했던거 다시 원상태로. 
//			break;
//		
//		}
//	}

//public void payInsuranceFee() {
//	Scanner scanner = new Scanner(System.in);
//	System.out.println("1. 일시불 납부   2. 할부 납부");
//	String selectNum = scanner.next();
//	if (selectNum.equals("1")) {
//		//남은 금액 갱신, 납부 금액 추가, 해당하는 보험의 미납료 갱신, 납부내역 저장
//		System.out.println("납부가 완료되었습니다. 납부확인서를 출력하시겠습니까?" + "\n" + "1. 예   2. 아니오");
//		selectNum = scanner.next();
//		if(selectNum.equals("1")) {
//			//보험납부정보(보험이름, 보험종료, 카드사/은행명, 카드/계좌번호, 납부금액, 납부일)을 출력한다.
//			//payment.get머시기			
//		}
//		else if(selectNum.equals("2")) {
//			//취소하고 홈화면
//		}
//	}
//	else if(selectNum.equals("2")) { 
//		// A1 일부납부 . 아직!
//	}
//	
//}


		

	
