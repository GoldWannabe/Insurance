package Payment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Payment {

	private String paymentID;
	private String customerID;
	private String accountNum;
	private String cardOrBankName;
	private int insuranceFee;
	private String insuranceID;
	private LocalDate paidDate;
	
	private enum EInsuranceType{
		genralInsurance,
		houseInsurance
	}
	private EInsuranceType insuranceType;
	private PaymentList paymentList;

	public Payment(){
////아아아!!! 보험종류 안넣었다 넣기!!!!!!! 출력에
		
	}
//	setPayment만듦
	public void setPayment() {
		this.paymentList = new PaymentListImpl();
		try { 
			File file = new File(".//DB//Payment.txt");
			
			Scanner fileScanner = new Scanner(file);
			
			while(fileScanner.hasNextLine()) {
				Payment tempPayment = new Payment();
				tempPayment.setPaymentID(fileScanner.next());
				tempPayment.setCustomerID(fileScanner.next());
				tempPayment.setAccountNum(fileScanner.next());
				tempPayment.setCardOrBankName(fileScanner.next());
				tempPayment.setInsuranceFee(fileScanner.nextInt());
				tempPayment.setInsuranceID(fileScanner.next());
				tempPayment.setPaidDate(LocalDate.parse(fileScanner.next()));
//	와우 이거땜에 오류남. 			fileScanner.nextLine();
				this.paymentList.add(tempPayment);
			}
		}catch(FileNotFoundException e) {
			System.out.println(
					"납부 기록이 없습니다. 문제가 있을 시에는 사내 시스템 관리팀(1234-5678)에게 문의 주시기 바랍니다.");
			e.getStackTrace();
		}
		
	}
	

	public void checkPayment(){
		//납부 기록(가입자명, 연락처, 보험이름, 보험종류, 카드사/은행명, 카드/계좌번호, 납부금액, 납부일)을 출력한다
		if(!(this.paymentList == null)) {
			System.out.println("-----------------------------------------------------------------------------------------------------");
			System.out.printf("%10s %10s %10s %10s %15s %15s %15s %15s", 
					"가입자명", "연락처", "보험이름", "보험종류", "카드사/은행명", "카드/계좌번호", "납부금액", "납부일");
			System.out.println();
			System.out.println("-----------------------------------------------------------------------------------------------------");
			for(Payment payment : this.paymentList.getAll()) {
				System.out.format("%10s %10s %12s %12s %15s %15s %15s %15s", 
						payment.getCustomerID(), payment.getCustomerID(), payment.getInsuranceID(), payment.getInsuranceID(),
						payment.getCardOrBankName(),payment.getCardOrBankName(), payment.getInsuranceFee(), payment.getPaidDate());
				System.out.println();
			}
			
		}
	}

	public String getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(String paymentID) {
		this.paymentID = paymentID;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public String getCardOrBankName() {
		return cardOrBankName;
	}

	public void setCardOrBankName(String cardOrBankName) {
		this.cardOrBankName = cardOrBankName;
	}

	public int getInsuranceFee() {
		return insuranceFee;
	}

	public void setInsuranceFee(int insuranceFee) {
		this.insuranceFee = insuranceFee;
	}

	public String getInsuranceID() {
		return insuranceID;
	}

	public void setInsuranceID(String insuranceID) {
		this.insuranceID = insuranceID;
	}

	public LocalDate getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(LocalDate paidDate) {
		this.paidDate = paidDate;
	}

	public EInsuranceType getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(String insuranceType) {
		// set logic
		this.insuranceType = EInsuranceType.genralInsurance;
	}
	
	public void finalize() throws Throwable {

	}
	
}//end Payment