package Control.ContractTeam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

import Model.Contract.Contract;
import Model.Contract.ContractList;
import Model.Contract.ContractListImpl;
import Model.Insurance.GeneralInsurance;
import Model.Insurance.HouseInsurance;
import Model.Insurance.Insurance;

public class Underwriting {

	Contract contract; 
	ContractList contractList;
	Insurance insurance;
	
	public boolean selectUnderwrite() {
//		this.contract = new Contract();
//		this.contractList = new ContractListImpl();
//		
//		Scanner scanner = new Scanner(System.in);
//		boolean correctInput = false;
//		String select;
//		System.out.println("인수심사할 계약 방식을 고르세요.");
//		while (!correctInput) {
//			System.out.println("1. 신규 2. 갱신");
//			select = scanner.next();
//			if (select.equals("1") || select.equals("신규")) {
//				
//				return selectApply(scanner);
//			} else if (select.equals("2") || select.equals("갱신")) {
//				
//				return selectRenew(scanner);
//			} else {
//				System.out.println("형식에 맞지 않습니다. 다시 입력해주세요.");
//			}
//		}
//		
		return false;
	}

//	private boolean selectApply(Scanner scanner) {
//		ResultSet resultSet = this.contract.getApply();
//		try {
//			while (resultSet.next()) {
//				Contract applyContract = null;
//				applyContract.setContractID(resultSet.getString("contractID"));
//				applyContract.setCustomerID(resultSet.getString("customerID"));
//				applyContract.setCustomerName(resultSet.getString("customerName"));
//				applyContract.setPhoneNum(resultSet.getString("customerPhoneNum"));
//				applyContract.setInsuranceID(resultSet.getString("insuranceID"));
//				applyContract.setInsuranceName(resultSet.getString("insuranceName"));
//				applyContract.setPaymentCycle(resultSet.getInt("paymentCycle"));
//				applyContract.setInsuranceFee(resultSet.getInt("insuranceFee"));
//				applyContract.setSecurityFee(resultSet.getInt("securityFee"));
//				applyContract.setPeriod(resultSet.getInt("period"));
//				this.contractList.add(applyContract);
//			}
//			
//			if(this.contractList.getAll().isEmpty()) {
//				System.out.println("심사할 신규 계약이 없습니다.");
//				return true;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		int length = showApply();
//		
//		System.out.println("심사할 계약 번호를 입력해 주세요.");
//		int select=checkNum(scanner);
//		
//		while(length < select || select < 0) {
//			System.out.println("해당 번호는 존재하지 않습니다. 다시 입력해 주세요.");
//			select=checkNum(scanner);
//		}
//		
//		
//		this.contract = this.contractList.get(select);
//		System.out.println("계약 정보 입력 예정");
//		
//		
//		System.out.println("검증을 하시려면 1을 입력해주세요.(다른 버튼을 입력하면 검증이 취소 됩니다.)");
//		
//		if(scanner.next().equals("1")) {
//		verifyPremium();
//		
//		permitApply();
//		
//		} else {
//		System.out.println("검증이 취소되었습니다. 메인화면으로 돌아갑니다.");
//		return true;
//		}
//	}
//
//	private boolean verifyPremium() {
//		this.insurance = new GeneralInsurance();
//		ResultSet resultSet = this.insurance.getInsurance();
//		if(resultSet.getString("insuranceType").equals("house")) {
//		this.insurance = new HouseInsurance();	
//		}
//		
//		this.insurance.setInsuranceID(resultSet.getString("insuranceID"));
//		this.insurance.setInsuranceName(resultSet.getString("insuranceName"));
//		this.insurance.setStandardFee(resultSet.getInt("StandradFee"));
//		this.insurance.setSpecialContract(resultSet.getString("specialContract"));
//		this.insurance.setLongTerm(resultSet.getBoolean("longTerm"));
//		this.insurance.setApplyCondition(resultSet.getString("applyCondition"));
//		this.insurance.setCompensateCondition(resultSet.getString("compensateCondition"));
//		this.insurance.setExplanation(resultSet.getString("explanation"));
//		//+장기여부 비교
//		boolean a =this.insurance.verifyPremium(this.contract.getSecurityFee(),this.contract.getInsuranceFee());
//		selectPermit(a);
//		
//	}
//
//	private int checkNum(Scanner scanner) {
//		while(!scanner.hasNextInt()) {
//			scanner.next();
//			System.out.println("숫자를 입력해주세요.");
//		}
//		return scanner.nextInt();
//	}
//
//	private int showApply() {
//		int length = 0; 
//		System.out.println("---------------------------------------------------------------------------------------");
//		System.out.printf("%10s %10s %10s", "보험명", "보험종류", "기준보험료");
//		System.out.println();
//		System.out.println("---------------------------------------------------------------------------------------");
//		for (Contract contract : this.contractList.getAll()) {
//			System.out.format("%10s %12s %12d", contract.getInsuranceName(), contract.getCustomerID(),contract.getPeriod());
//			System.out.println();
//			length++;
//		}		
//		return length;
//	}
//
//	private boolean selectRenew(Scanner scanner) {
//		//ResultSet resultSet = this.contract.getRenew();
//		return false;
//	}


}
