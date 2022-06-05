package Control.ContractTeam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

import Model.Contract.Contract;
import Model.Contract.ContractList;
import Model.Contract.ContractListImpl;
import Model.Customer.Customer;
import Model.Insurance.GeneralInsurance;
import Model.Insurance.HouseInsurance;
import Model.Insurance.Insurance;
import View.Team.ContractTeamTui;
import exception.DBAcceptException;
import exception.WrongInputException;

public class Underwriting {

	public Contract contract;
	public ContractList contractList;
	public Insurance insurance;
	public ContractTeamTui contractTeamTui;
	public Customer customer;

	public Underwriting() {
		this.contractTeamTui = new ContractTeamTui();
		this.contract = new Contract();
		this.contractList = new ContractListImpl();
	}

	@SuppressWarnings("resource")
	public boolean selectUnderwrite() {

		Scanner scanner = new Scanner(System.in);
		int flag = -1;
		String selectList[] = new String[] { "1", "신규", "2", "갱신", "0", "취소" };
		this.contractTeamTui.showSelectContractMethods();
		while (flag == -1) {
			try {
				flag = getflag(selectList, scanner.next());

				if (flag == 1) {

					return selectApply(scanner);
				} else if (flag == 2) {

					return selectRenew(scanner);
				} else if (flag == 0) {
					this.contractTeamTui.showCancel();
					return true;
				}

			} catch (WrongInputException e) {
				System.err.println(e.getMessage());
			}
		}
		return false;

	}

	private int getflag(String[] selectList, String select) throws WrongInputException {
		for (int i = 0; i < selectList.length; i = i + 2) {
			if (selectList[i].equals(select) || selectList[i + 1].equals(select)) {
				return Integer.parseInt(selectList[i]);
			}
		}
		throw new WrongInputException();
	}

	private boolean selectApply(Scanner scanner) {
		ResultSet resultSet = this.contract.getApply();
		try {
			while (resultSet.next()) {
				Contract applyContract = new Contract();
				applyContract.setContractID(resultSet.getString("contractID"));
				applyContract.setCustomerID(resultSet.getString("customerID"));
				applyContract.setCustomerName(resultSet.getString("customerName"));
				applyContract.setPhoneNum(resultSet.getString("customerPhoneNum"));
				applyContract.setInsuranceID(resultSet.getString("insuranceID"));
				applyContract.setInsuranceName(resultSet.getString("insuranceName"));
				applyContract.setPaymentCycle(resultSet.getInt("paymentCycle"));
				applyContract.setInsuranceFee(resultSet.getInt("insuranceFee"));
				applyContract.setSecurityFee(resultSet.getInt("securityFee"));
				applyContract.setPeriod(resultSet.getInt("period"));
				this.contractList.add(applyContract);
			}

			if (this.contractList.getAll().isEmpty()) {
				this.contractTeamTui.showNoApplyContract();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		int length = showApply();
		this.contractTeamTui.showSelectApplyContract();

		int select = -1;

		while (length < select || select < 1) {
			try {
				select = checkNum(scanner);
			} catch (WrongInputException e) {
				System.err.println(e.getMessage());
			}
		}

		this.contract = this.contractList.getCount(select - 1);
		return selectVerify(scanner);


	}

	private int showApply() {
		this.contractTeamTui.showConractColumn();
		int length = 1;

		for (Contract contract : this.contractList.getAll()) {
			this.contractTeamTui.showContracts(length, contract);
			length++;
		}
		return length;
	}

	private int checkNum(Scanner scanner) throws WrongInputException {
		if (scanner.hasNextInt()) {
			return scanner.nextInt();
		}
		scanner.next();
		throw new WrongInputException();
	}

	private boolean selectVerify(Scanner scanner) {
		this.contractTeamTui.showSelectVerification();
		int flag = -1;
		String selectList[] = new String[] { "1", "검증", "2", "취소" };

		while (flag == -1) {
			try {
				flag = getflag(selectList, scanner.next());
			} catch (WrongInputException e) {
				System.err.println(e.getMessage());
			}
		}

		if (flag == 1) {
			return verifyInsurance();
		} else if (flag == 2) {
			return true;
		}
		return false;
	}

	private boolean verifyInsurance() {
		if (!getInsurance() || !getCustomer()) {
			throw new DBAcceptException();
		}

		if (verifyPremium() && verifyPeriod()) {
			return false;
		}
		return false;
	}

	private boolean verifyPremium() {
		return false;
	}

	private boolean getCustomer() {
		this.customer = new Customer();
		ResultSet resultSet = this.customer.getCustomerByID(this.contract.getCustomerID());
		ResultSet rankResultSet = this.customer.setRank(this.contract.getCustomerID());
		try {
			resultSet.next();
			this.customer.setCustomerID(resultSet.getString("customerID"));
			this.customer.setName(resultSet.getString("Name"));
			this.customer.setSSN(resultSet.getString("SSN"));
			this.customer.setSex(resultSet.getString("Sex"));
			this.customer.setPhoneNum(resultSet.getString("phoneNum"));
			this.customer.setAddress(resultSet.getString("address"));
			this.customer.setBankName(resultSet.getString("bankName"));
			this.customer.setAccountNum(resultSet.getString("accountNum"));
			this.customer.setInsuranceNum(resultSet.getDouble("insuranceNum"));
			
			while(rankResultSet.next()) {
				this.customer.addCustomerIDRankID(rankResultSet.getString("contractID"), rankResultSet.getString("RankID"));
			}
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	private boolean getInsurance() {
		this.insurance = new GeneralInsurance();
		ResultSet resultSet = this.insurance.getInsuranceByID(this.contract.getInsuranceID());

		try {
			resultSet.next();
			if (resultSet.getString("insuranceType").equals("house")) {
				this.insurance = new HouseInsurance();
			}

			this.insurance.setInsuranceID(resultSet.getString("insuranceID"));
			this.insurance.setInsuranceName(resultSet.getString("insuranceName"));
			this.insurance.setStandardFee(resultSet.getInt("StandradFee"));
			this.insurance.setSpecialContract(resultSet.getString("specialContract"));
			this.insurance.setLongTerm(resultSet.getBoolean("longTerm"));
			this.insurance.setApplyCondition(resultSet.getString("applyCondition"));
			this.insurance.setCompensateCondition(resultSet.getString("compensateCondition"));
			this.insurance.setExplanation(resultSet.getString("explanation"));
			this.insurance.getRate(this.contract.getInsuranceID());

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean verifyPeriod() {

		if ((this.contract.getPeriod() >= 36) && (this.insurance.isLongTerm() != true)) {
			String reason = "장기 계약 불가능한 보험";

			return saveFailContract(reason);
		}

//		boolean a = this.insurance.verifyPremium(this.contract.getSecurityFee(), this.contract.getInsuranceFee());
//		selectPermit(a);

		return true;
	}

	private boolean saveFailContract(String reason) {
		return false;
		// this.contract.saveFailContract();
	}

	private boolean selectRenew(Scanner scanner) {
		// ResultSet resultSet = this.contract.getRenew();
		return false;
	}

}
