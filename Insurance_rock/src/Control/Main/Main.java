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
import View.MainView.MainTui;
import exception.DBAcceptException;
import exception.WrongInputException;

public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws WrongInputException {
		MainTui mainTui = new MainTui();
		boolean continueSelect = false;
		while (!continueSelect) {
			Scanner scanner = new Scanner(System.in);
			mainTui.showSelectTeam();
			String selectNum = scanner.next();
			switch (selectNum) {
			case "1":
				selectContractTeam(mainTui, scanner);
				break;
			case "2":
				selectFinancialDirector(mainTui, scanner);
				break;
			case "3":
				selectSalesTeam(mainTui, scanner);
				break;
			case "4":
				selectCompensateTeam(mainTui, scanner);
				break;
			case "5":
				selectPolicyholder(mainTui, scanner);
				break;
			default:
				// while문 안먹음 사
				throw new WrongInputException();

			}
		}
	}

	private static void selectContractTeam(MainTui mainTui, Scanner scanner) throws DBAcceptException {
		boolean continueSelect = false;
		while (!continueSelect) {
			mainTui.showSelectContractTeam();
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
				continueSelect = contractManagement.searchContract(scanner);
				break;
			case "0":
				mainTui.showClose();
				System.exit(0);
				break;
			default:
				mainTui.showWrongInput();
				break;
			}
		}
		mainTui.showClose();
	}

	private static void selectFinancialDirector(MainTui mainTui, Scanner scanner) {

		boolean continueSelect = false;
		while (!continueSelect) {
			mainTui.showSelectFinancialDirector();
			String selectNum = scanner.next();

			switch (selectNum) {
			case "1":
				InsuranceJudge insuranceJudge = new InsuranceJudge();
				insuranceJudge.selectJudge();
				break;
			case "0":
				mainTui.showClose();
				System.exit(0);
				break;
			default:
				mainTui.showWrongInput();
				break;
			}
		}
		mainTui.showClose();
	}

	private static void selectSalesTeam(MainTui mainTui, Scanner scanner) {
		boolean continueSelect = false;
		while (!continueSelect) {
			mainTui.showSalesTeam();
			String selectNum = scanner.next();

			switch (selectNum) {
			case "1":
				InsuranceSales insuranceSales = new InsuranceSales();
				insuranceSales.selectInsuranceType();
				break;
			case "2":
				CustomerManagement customerManagement = new CustomerManagement();
				customerManagement.searchCustomer();
				break;
			case "3":
				ChannelManagement channelManagement = new ChannelManagement();
				channelManagement.viewChannel();
				break;
			case "0":
				mainTui.showClose();
				System.exit(0);
				break;
			default:
				mainTui.showWrongInput();
				break;
			}
		}
		mainTui.showClose();
	}

	private static void selectPolicyholder(MainTui mainTui, Scanner scanner) {
		boolean continueSelect = false;
		
		while(!continueSelect) {
			mainTui.showPolicyholder();
			
			String selectNum = scanner.next();
			switch (selectNum) {
			case "1":
				FeePayment feePayment = new FeePayment();
				feePayment.start();
				break;
			case "0":
				mainTui.showClose();
				System.exit(0);
				break;
			default:
				mainTui.showWrongInput();
				break;
			}

		}
		mainTui.showClose();
	
	}

	private static boolean selectCompensateTeam(MainTui mainTui, Scanner scanner) {
		boolean continueSelect = false;

		while (!continueSelect) {
			mainTui.showCompensateTeam();
			String selectNum = scanner.next();
			switch (selectNum) {
			case "1":
				DamageAssessment damageAssessment = new DamageAssessment();
				return continueSelect = damageAssessment.selectAccidentMenagement(scanner);

			case "0":
				mainTui.showClose();
				System.exit(0);
				break;
			default:
				mainTui.showWrongInput();
				break;
			}
		}
		return continueSelect;
	}
}
