package Control.Main;

import java.util.Scanner;

import Control.CompensateTeam.DamageAssessment;
import Control.ContractTeam.ContractManagement;
import Control.ContractTeam.InsuranceDesign;
import Control.ContractTeam.Underwriting;
import Control.FinancialDirector.InsuranceJudge;
import Control.Policyholder.FeePayment;
import Control.SalesTeam.ChannelManagement;
import Control.SalesTeam.CustomerManagement;
import Control.SalesTeam.InsuranceSales;
import View.Team.CompensateTeamTui;
import View.Team.ContractTeamTui;
import View.Team.FinancialDirectorTui;
import View.Team.PolicyHolderTui;
import View.Team.SalesTeamTui;

public class Main {

	public static void main(String[] args) {
		
		boolean continueSelect = false;
		while(!continueSelect) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("1. 계약팀, 2. 금감원, 3. 마켓팅/영업팀, 4. 보상팀, 5.고객");
			String selectNum = scanner.next();
			switch (selectNum) {
			case "1":
				selectContractTeam(scanner);
				break;
			case "2":
				selectFinancialDirector(scanner);
				break;
			case "3":
				selectSalesTeam(scanner);
				break;
			case "4":
				selectCompensateTeam(scanner);
				break;
			case "5":
				selectCustomer(scanner);
				break;
			default:
				//while문 안먹음 사
				System.out.println("제대로 입력해주세요.");
				break;
			}
		}
	}
	
	
	private static void selectContractTeam(Scanner scanner) {
		boolean continueSelect = true;

		
		while (continueSelect) {
			System.out.println("1. 설계, 2. 인수심사,  3. 계약관리 0. 종료");
			String selectNum = scanner.next();
			switch (selectNum) {
			case "1":
				InsuranceDesign insuranceDesign = new InsuranceDesign();
				continueSelect = insuranceDesign.design();
				break;
			case "2":
				Underwriting underwriting = new Underwriting();
				continueSelect = underwriting.selectUnderwrite();
				break;
			case "3":
				ContractManagement contractManagement = new ContractManagement();
				continueSelect = contractManagement.searchContract();
				break;
			case "0":
				System.exit(0);
				break;
			default:
				System.out.println("입력이 잘못 되었습니다. 다시 입력해주세요.");
				break;
			}
		}
	}

	private static void selectFinancialDirector(Scanner scanner) {
		System.out.println("1. 보험 심사");
		String selectNum = scanner.next();

		switch (selectNum) {
		case "1":
			InsuranceJudge insuranceJudge = new InsuranceJudge();
			insuranceJudge.selectJudge();
			break;

		default:
			System.out.println("선택 이상함");
			break;
		}
	}

	private static void selectSalesTeam(Scanner scanner) {
		System.out.println("1. 보험 판매, 2. 고객 정보 관리, 3. 판매채널 관리");
		String selectNum = scanner.next();

		switch (selectNum) {
		case "1":
			InsuranceSales insuranceSales = new InsuranceSales();
			insuranceSales.searchInsurance();
			break;
		case "2":
			CustomerManagement customerManagement = new CustomerManagement();
			customerManagement.searchCustomer();
			break;
		case "3":
			ChannelManagement channelManagement = new ChannelManagement();
			channelManagement.viewChannel();
			break;

		default:
			System.out.println("선택 이상함");
			break;
		}

	}

	private static void selectCustomer(Scanner scanner) {
		System.out.println("1. 보험료 납부");
		String selectNum = scanner.next();
		
			switch (selectNum) {
			case "1":
				FeePayment feePayment = new FeePayment();
				feePayment.checkFee();
				break;

			default:
				System.out.println("선택 이상함");
				break;
			}
		
		

	}

	private static boolean selectCompensateTeam(Scanner scanner) {
		boolean continueSelect = false;
		
		while (!continueSelect) {
			System.out.println("1. 손해사정, 0. 취소 ");
			String selectNum = scanner.next();
			switch (selectNum) {
			case "1":		
				DamageAssessment damageAssessment = new DamageAssessment();
				return continueSelect = damageAssessment.selectAccidentMenagement(scanner);
				
			case "0":
				System.out.println("취소되었습니다. 전 선택창으로 돌아갑니다");
				return true;
			default:
				System.out.println("선택 이상함");
				break;
			}
		}
		return continueSelect;
	}
}
