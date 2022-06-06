package Control.Policyholder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.ResultSet;
import View.Team.PolicyHolderTui;
import exception.BankfileAcceptException;
import exception.ChangedDateException;
import exception.DBAcceptException;
import exception.LackCustomerBank;
import exception.PaymentFailedException;
import exception.WrongInputException;
import exception.fileAcceptException;

import java.sql.SQLException;
import java.time.LocalDate;
//import java.util.Arrays;
import java.util.Scanner;

import Model.Payment.*;
import Model.Provision.*;
import Model.Contract.*;
import Model.Insurance.Insurance.EInsurance;

public class FeePayment {
	PolicyHolderTui policyholderTUI;
	public Payment payment;
	public PaymentList paymentList = new PaymentListImpl();

	private Provision provision;
	private ProvisionList provisionList = new ProvisionListImpl();

	private Contract contract;
	private ContractList contractList = new ContractListImpl();
	
	
	public FeePayment() {
		this.policyholderTUI = new PolicyHolderTui();
	}
	private boolean setDB(String purpose) {
		Scanner scanner = new Scanner(System.in);
		this.paymentList = new PaymentListImpl();
		this.payment = new Payment();
		
		this.contractList = new ContractListImpl();
		this.contract = new Contract();
		
		this.provisionList = new ProvisionListImpl();
		this.provision = new Provision();
		
		//list로 보여주기
		ResultSet resultSet = this.payment.getPayment();
		ResultSet resultSet2 = this.contract.getContract();
		ResultSet resultSet3 = this.provision.getProvision();
		
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
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		int num = 1;
		try {
			while(resultSet2.next()) { 
				Contract tempContract = new Contract();
				tempContract.setContractID(resultSet2.getString("contractID"));
				tempContract.setCustomerID(resultSet2.getString("customerID"));
				tempContract.setCustomerName(resultSet2.getString("customerName"));
				tempContract.setPhoneNum(resultSet2.getString("customerPhoneNum"));
				tempContract.setInsuranceID(resultSet2.getString("insuranceID"));
				tempContract.setInsuranceName(resultSet2.getString("insuranceName"));
				tempContract.setPaymentCycle(resultSet2.getInt("paymentCycle"));
				tempContract.setInsuranceFee(resultSet2.getInt("insuranceFee"));
				tempContract.setUnpaidFee(resultSet2.getInt("unpaidFee"));
				tempContract.setSecurityFee(resultSet2.getInt("securityFee"));
				tempContract.setProvisionFee(resultSet2.getInt("provisionFee"));
				tempContract.setStartDate(LocalDate.parse(resultSet2.getDate("startDate").toString()));
				tempContract.setEndDate(LocalDate.parse(resultSet2.getDate("endDate").toString()));
				tempContract.setNum(num);
				num ++;
				this.contractList.add(tempContract);
			}
		
//없는거 처리 @@	if(this.paymentList.getAll)
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		try {
			while (resultSet3.next()) {
				
				Provision tempProvision = new Provision();
				tempProvision.setProvisionID(resultSet3.getString("provisionID"));
				tempProvision.setInsuranceName(resultSet3.getString("insuranceName"));
				tempProvision.setAccountNum(resultSet3.getString("accountNum"));
				tempProvision.setBankName(resultSet3.getString("bankName"));
				tempProvision.setCompensation(resultSet3.getInt("compensation"));
				tempProvision.setPhoneNum(resultSet3.getString("customerPhoneNum"));
				tempProvision.setCompensationDate(LocalDate.parse(resultSet3.getDate("compensationDate").toString()));
				tempProvision.setCustomerName(resultSet3.getString("customerName"));
				tempProvision.setLongTerm(resultSet3.getBoolean("longTerm"));	
				tempProvision.setInsuranceType(EInsurance.valueOf(resultSet3.getString("insuranceType").toString()));
				tempProvision.setContractID(resultSet3.getString("contractID"));
//				tempProvision.setProvisionID(resultSet.getString("provisionID"));

				this.provisionList.add(tempProvision);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();	
		}
		if(purpose.equals("forpay") || purpose.equals("forpaymentRecord") || purpose.equals("forProvisionRecord")) {
			return true;
		}

		return false;
	}
	
	public boolean start() {
		Scanner scanner = new Scanner(System.in);
		this.policyholderTUI.showStartMenu();
		String num = scanner.next();
		if(num.equals("1")) {
			checkFee();
		}
		else if(num.equals("2")) {
			checkPayment();
		}
		else if(num.equals("3")) {
			checkProvision();
		}
		else {
			this.policyholderTUI.showWrongInput();
			start();
		}
	return false;
		
		
	}
	public boolean checkFee() {
		Scanner scanner = new Scanner(System.in);
		try{
			if(setDB("forpay")) {
				return selectCustomer(scanner);
				
		}
			else {
				throw new DBAcceptException();
		}
		}catch (DBAcceptException e){
			e.printStackTrace();
	}
		scanner.close();
		return false;
	}
	//여기 fileacceptexception못햇어. 
	//아 customer.....
	private boolean selectCustomer(Scanner scanner) {
		try{
			LocalDate currDate = LocalDate.now();
			this.policyholderTUI.enterCustomerName();
			String name = scanner.next();
			this.policyholderTUI.enterPhoneNum();
			String phoneNum = scanner.next();
			this.payment = this.paymentList.get(name, phoneNum);			
			if(this.payment == null) {
				this.policyholderTUI.showNopaymentFee();
				selectCustomer(scanner);
			}
			if(currDate == LocalDate.now()) {
				throw new ChangedDateException();
			}
			this.policyholderTUI.showPaymentFee(this.contractList, name, phoneNum);
		} catch (fileAcceptException e) {
			e.printStackTrace();
		} catch (ChangedDateException e) {
			e.printStackTrace();
		} 
			return selectInsurance(scanner);
	}
	private boolean selectInsurance(Scanner scanner) {
		this.policyholderTUI.showSelectInsurance();
		int num = scanner.nextInt();
		if(num == 1) {
			this.contract = contractList.get(num);
			System.out.println(this.contract.getInsuranceFee());
		}
		else if(num == 2) {
			this.contract = contractList.get(num);
			System.out.println(this.contract.getInsuranceFee());
		}
		else {
			this.policyholderTUI.showWrongInput();
			selectInsurance(scanner);
		}
		
		return selectMenu(scanner);
	}
	private boolean selectMenu(Scanner scanner) {
		this.policyholderTUI.showSelectMenu();
		String selectNum = scanner.next();
		if(selectNum.equals("1")) {
			return choosePaymentMethod(scanner);
		}
		else if(selectNum.equals("2")) {
			this.policyholderTUI.cancel();
		}
		else {
			this.policyholderTUI.showWrongInput();
			selectMenu(scanner);
		}
		return false;
	}
	
	int balances = 0;
	File file = new File(".//File//CustomerBank.txt");

	private boolean choosePaymentMethod(Scanner scanner) {
		this.policyholderTUI.selectMethod();
		String method = scanner.next();
		this.policyholderTUI.selectBankName();
		String name = scanner.next();
		this.policyholderTUI.selectAccountNum();
		String num = scanner.next();
		this.policyholderTUI.showMethod(method, name, num);

		//통장 잔고 확인
		try {
			
			Scanner fs = new Scanner(file);
			
			while (fs.hasNextInt()) {
				int balances = fs.nextInt();
				if(balances <= 1000) {
					throw new LackCustomerBank();
				}
				else if(balances >1000) {
					this.policyholderTUI.showBalanceCheck();
				}
				else throw new BankfileAcceptException();
				
			}
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (LackCustomerBank e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BankfileAcceptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		case "2" : 
			return editPayment(scanner);
		case "3" : 
			this.policyholderTUI.cancel();
		}
//true로
		return true;
	}

	public boolean checkPayment() {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		try {
			if(setDB("forpaymentRecord")) {
				return selectCustomerForPayment(scanner);
			}
			else {
				throw new DBAcceptException();
			}
		} catch(DBAcceptException e) {
			e.printStackTrace();
		}
		scanner.close();
		return false;
	}
	
	private boolean selectCustomerForPayment(Scanner scanner) {
		this.policyholderTUI.enterCustomerName();
		String name = scanner.next();
		this.policyholderTUI.enterPhoneNum();
		String phoneNum = scanner.next();
		this.payment = this.paymentList.get(name, phoneNum);
//		this.contract = this.contractList.g
		this.policyholderTUI.showPaymentRecords(this.paymentList, name, phoneNum);
		return false;
	}
	

	private boolean payInsuranceFee(Scanner scanner){
		this.policyholderTUI.selectPayTerm();
		String term = scanner.next();
		//일시불 납부
		
		try {
			if (term.equals("1")) {
			//통장 갱신은 나중에. 
//		//남은 금액 갱신, 납부 금액 추가, 해당하는 보험의 미납료 갱신, 납부내역 저장
//			FileWriter fw = new FileWriter(file); 
//			fw.write(balances - this.payment.getInsuranceFee());
//          fw.close();
            //해당하는 보험의 미납료 갱신. 
			this.contract.updateUnpaidFee(this.contract.getUnpaidFee()-this.contract.getInsuranceFee(), this.contract.getContractID());
			
			if(this.payment.addPayment()) {
				this.policyholderTUI.showCompleted();
				
				String selectNum = scanner.next();
				if(selectNum.equals("1")) {
					this.policyholderTUI.showPaymentCheck(this.payment);
				}
				else if(selectNum.equals("2")) {
					this.policyholderTUI.cancel();
				}
				else {
					this.policyholderTUI.showWrongInput();;
					payInsuranceFee(scanner);
				}
			}
			else throw new PaymentFailedException();		
		}
	
		//할부 납부
			//남은 금액 갱신, 납부금액추가,미납료 갱신, 납부내역 저장
//			"납부가 완료 되었습니다. 미납액:(미납액)"라는 알람이 뜨고 "납부 확인서 출력" 버튼이 뜬다(A2)
			else if(term.equals("2")) {
				this.policyholderTUI.enterMonthlyFee();
				int monthlyFee = scanner.nextInt();
				this.payment.setInsuranceFee(monthlyFee);
				int unpaidFee = this.contract.getUnpaidFee() - monthlyFee;
				this.contract.updateUnpaidFee(unpaidFee, this.contract.getContractID());
				if( this.payment.addPayment()) {
					this.policyholderTUI.showMonthlyCompleted(unpaidFee);
					
					String selected = scanner.next();
					if(selected.equals("1")) {
						this.policyholderTUI.showPaymentCheck(this.payment);
					}
					else if(selected.equals("2")) {
						this.policyholderTUI.cancel();
					}
					else {
						this.policyholderTUI.showWrongInput();;
						payInsuranceFee(scanner);
					}
				
				}
				else throw new PaymentFailedException();
				
			
				
			}
		} catch (PaymentFailedException e) {
			e.printStackTrace();
		} 
	
		return false;
	}
	private boolean editPayment(Scanner scanner) {
		return choosePaymentMethod(scanner);
	}


	public boolean checkProvision() {
		// TODO Auto-generated method stub
		Scanner scanner= new Scanner(System.in);
		try {
			if(setDB("forProvisionRecord")) {
				return selectCustomerForProvision(scanner);
		}
			else {
				throw new DBAcceptException();
			}

		}catch (DBAcceptException e) {
			e.printStackTrace();
		}
		scanner.close();
		return false;

	}


	private boolean selectCustomerForProvision(Scanner scanner) {
		// TODO Auto-generated method stub
		this.policyholderTUI.enterCustomerName();
		String name = scanner.next();
		this.policyholderTUI.enterPhoneNum();
		String phoneNum = scanner.next();
		this.provision = this.provisionList.get(name, phoneNum);
		this.policyholderTUI.showProvisionRecords(this.provisionList, name, phoneNum);
		return false;
	}

	
}



		

	
