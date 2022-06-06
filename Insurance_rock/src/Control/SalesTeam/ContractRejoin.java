package Control.SalesTeam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Model.Contract.Contract;
import Model.Contract.ContractList;
import Model.Contract.ContractListImpl;
import Model.Insurance.Insurance;
import View.Team.SalesTeamTui;
import exception.DBAcceptException;
import exception.WrongInputException;

public class ContractRejoin {
	
	private SalesTeamTui salesTeamTui;
	private Insurance insurance;
	
	
	public ContractRejoin(SalesTeamTui salesTeamTui, Insurance insurance) {
		this.salesTeamTui = salesTeamTui;
		this.insurance = insurance;
	}

	public boolean rejoinStart(Scanner scanner) {
		Contract searchContract = new Contract();
		this.salesTeamTui.showSearchCustomerStart();
		this.salesTeamTui.showEnterCustomerName();
		searchContract.setCustomerName(scanner.next());
		this.salesTeamTui.showEnterCustomerPhoneNum();
		searchContract.setPhoneNum(scanner.next());
		//set insuranceID
		searchContract.setInsuranceID(this.insurance.getInsuranceID());
		ResultSet resultSet = searchContract.getFailContract();
		try {
			if(resultSet.next()) {
				searchContract.setContractID(resultSet.getString("contractID"));
				searchContract.setCustomerID(resultSet.getString("customerID"));
				searchContract.setCustomerName(resultSet.getString("customerName"));
				searchContract.setPhoneNum(resultSet.getString("customerPhoneNum"));
				searchContract.setInsuranceID(resultSet.getString("insuranceID"));
				searchContract.setInsuranceName(resultSet.getString("insuranceName"));
				searchContract.setPaymentCycle(resultSet.getInt("paymentCycle"));
				searchContract.setInsuranceFee(resultSet.getInt("insuranceFee"));
				searchContract.setSecurityFee(resultSet.getInt("securityFee"));
				searchContract.setPeriod(resultSet.getInt("period"));
				searchContract.setReason(resultSet.getString("reason"));
			}else {
				this.salesTeamTui.showSearchFail();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DBAcceptException e) {
			System.err.println(e.getMessage());
			return false;
		}
		this.salesTeamTui.showFailContract(searchContract);
		return editFailContract(searchContract, scanner);
	}

	private boolean editFailContract(Contract contract, Scanner scanner) {
		Contract contractTemp = new Contract();
		contractTemp.setPaymentCycle(contract.getPaymentCycle());
		contractTemp.setInsuranceFee(contract.getInsuranceFee());
		contractTemp.setSecurityFee(contract.getSecurityFee());
		contractTemp.setPeriod(contract.getPeriod());
		
		while(true) {
			try {
				salesTeamTui.showEditStart();
				String flag = scanner.next();
				if(flag.equals("1")) {
					salesTeamTui.showEnterPaymentCycle(contract.getPaymentCycle());
					contract.setPaymentCycle(scanner.nextInt());
					salesTeamTui.showEditSuccess();
				}else if(flag.equals("2")){
					salesTeamTui.showEnterInsuranceFee();
					contract.setInsuranceFee(scanner.nextInt());
					salesTeamTui.showEditSuccess();
				}else if(flag.equals("3")) {
					salesTeamTui.showEnterSecurityFee();
					contract.setSecurityFee(scanner.nextInt());
					salesTeamTui.showEditSuccess();
				}else if(flag.equals("4")) {
					salesTeamTui.showEnterPeriod();
					contract.setPeriod(scanner.nextInt());
					salesTeamTui.showEditSuccess();
				}else if(flag.equals("0")) {
					if(equalContract(contractTemp, contract)) {
						salesTeamTui.showNochanges();
					}else {
						return reRegisterContract(contract);	
					}
				}else {
					throw new WrongInputException();
				}
			}catch(WrongInputException e) {
				System.err.println(e.getMessage());
			}
		}
	}

	private boolean equalContract(Contract contractTemp, Contract contract) {
		if(contractTemp.getPaymentCycle() == contract.getPaymentCycle()
				&& contractTemp.getInsuranceFee() == contract.getInsuranceFee()
				&& contractTemp.getSecurityFee() == contract.getSecurityFee()
				&& contractTemp.getPeriod() == contract.getPeriod()) {
			return true;
		}
		return false;
	}

	private boolean reRegisterContract(Contract contract) {
		try {
			if(contract.deleteFailContract() && contract.registerApplyContract()) {
				salesTeamTui.showReRegisterSuccess();
				return true;
			}else {
				throw new DBAcceptException();
			}
		}catch(DBAcceptException e) {
			System.err.println(e.getMessage());
			System.exit(0);
		}
		return false;
	}
}
