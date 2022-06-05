package Control.SalesTeam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Model.Contract.Contract;
import Model.Contract.ContractList;
import Model.Insurance.Insurance;
import View.Team.SalesTeamTui;
import exception.DBAcceptException;

public class RejoinContract {

	public boolean rejoinStart(SalesTeamTui salesTeamTui, ContractList contractFailList, Scanner scanner, Insurance insurance) {
		Contract searchContract = new Contract();

		salesTeamTui.showSearchCustomerStart();
		salesTeamTui.showEnterCustomerName();
		searchContract.setCustomerName(scanner.next());
		salesTeamTui.showEnterCustomerPhoneNum();
		searchContract.setPhoneNum(scanner.next());

		ResultSet resultSet = searchContract.getFailContract();
		try {
			while (resultSet.next()) {
				Contract contract = new Contract();
				contract.setContractID(resultSet.getString("contractID"));
				contract.setCustomerID(resultSet.getString("customerID"));
				contract.setCustomerName(resultSet.getString("customerName"));
				contract.setPhoneNum(resultSet.getString("customerPhoneNum"));
				contract.setInsuranceID(resultSet.getString("insuranceID"));
				contract.setInsuranceName(resultSet.getString("insuranceName"));
				contract.setPaymentCycle(resultSet.getInt("paymentCycle"));
				contract.setInsuranceFee(resultSet.getInt("insuranceFee"));
				contract.setSecurityFee(resultSet.getInt("securityFee"));
				contract.setPeriod(resultSet.getInt("period"));
				contract.setReason(resultSet.getString("reason"));
				contractFailList.add(contract);
			}

			if (contractFailList.getAll().isEmpty()) {
				salesTeamTui.showSearchFail();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//print failContract List (success select * from failContract)
		salesTeamTui.showFailContractList(contractFailList);
		// select edit & re-register contract
		salesTeamTui.showSelectContract();
		searchContract.setContractID(scanner.next());
		resultSet = searchContract.getFailContractID();
		// get re-register contract
		Contract selectContract = new Contract();
		try {
			selectContract.setContractID(resultSet.getString("contractID"));
			selectContract.setCustomerID(resultSet.getString("customerID"));
			selectContract.setCustomerName(resultSet.getString("customerName"));
			selectContract.setPhoneNum(resultSet.getString("customerPhoneNum"));
			selectContract.setInsuranceID(resultSet.getString("insuranceID"));
			selectContract.setInsuranceName(resultSet.getString("insuranceName"));
			selectContract.setPaymentCycle(resultSet.getInt("paymentCycle"));
			selectContract.setInsuranceFee(resultSet.getInt("insuranceFee"));
			selectContract.setSecurityFee(resultSet.getInt("securityFee"));
			selectContract.setPeriod(resultSet.getInt("period"));
			selectContract.setReason(resultSet.getString("reason"));
			
			if(selectContract.equals(null)) {
				throw new DBAcceptException();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DBAcceptException e) {
			System.err.println(e.getMessage());
		}	
		return editFailContract(selectContract);
	}

	private boolean editFailContract(Contract selectContract) {
		
		return false;
	}

}
