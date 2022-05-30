package Contract;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import Accident.Accident;
import Channel.Channel;
import dao.ContractDao;

public class ContractListImpl implements ContractList {
	private ArrayList<Contract> contractList = new ArrayList<Contract>();
	private Contract contract;
	private ContractDao contractDAO;
	

	public ContractListImpl(){
		this.contract = new Contract();
		this.contractDAO = new ContractDao();
//		this.setContract();

	}
	

	private void setContract() {	
		ResultSet resultSet = contractDAO.retrive();
		
		try {
			while (resultSet.next()) {
				Contract contract = new Contract();
				contract.setContractID(resultSet.getString("contractID"));
				contract.setCustomerID(resultSet.getString("customerID"));
				contract.setCustomerName(resultSet.getString("customerName"));
				contract.setPhoneNum(resultSet.getString("customerPhoneNum"));
				contract.setInsuranceID("insuranceID");
				contract.setInsuranceName(resultSet.getString("insuranceName"));
				contract.setPaymentCycle(resultSet.getInt("paymentCycle"));
				contract.setInsuranceFee(resultSet.getInt("insuranceFee"));
				contract.setUnpaidFee(resultSet.getInt("unpaidFee"));
				contract.setSecurityFee(resultSet.getInt("securityFee"));
				contract.setProvisionFee(resultSet.getInt("provisionFee"));
				contract.setStartDate(LocalDate.parse(resultSet.getString("startDate")));
				contract.setEndDate(LocalDate.parse(resultSet.getString("endDate")));
				
				contractList.add(contract);
			}
		} catch (SQLException e) {
			System.out.println(
					"파일 접근 중 문제가 생겨 계약정보를 불러오지 못했습니다. 잠시후 다시 실행 해주십시오. 해당 문제가 계속 발생할 시에는 사내 시스템 관리팀(1234-5678)에게 문의 주시기 바랍니다.");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub

	}
	

	public void finalize() throws Throwable {

	}
	public boolean add(Contract contract){
		if(this.contractList.add(contract)) return true;
		return false;

	}

	@SuppressWarnings("unlikely-arg-type")
	public boolean delete(String contractID){
		if(this.contractList.remove(contractID)) return true;
		return false;
	}

	public ArrayList<Contract> get(String customerName, String phoneNum){
		ArrayList<Contract> tempContract = new ArrayList<Contract>();
		for(Contract contract: this.contractList) {
			if(contract.getPhoneNum().equals(phoneNum) && contract.getCustomerName().equals(customerName)) {
				tempContract.add(contract);
			}
		}
		
		if(!(tempContract.isEmpty())) {
			return tempContract;
		} 
		return null;
		
	}

	public ArrayList<Contract> getAll() {
		return this.contractList;
	}


	public Contract get(String contractID) {
		for (Contract contract : this.contractList) {
			if (contract.getContractID().equals(contractID)) {
				return contract;
			}
		}
		return null;
	}


	public Contract getcheck(String customerName_inser, String phoneNum_inser) {
		for (Contract contract : this.contractList) {
			if (contract.getCustomerName().equals(customerName_inser) && contract.getPhoneNum().equals(phoneNum_inser)) {
				return contract;
			}
		}
		return null;
	}

//	public boolean update(){
//
//	}
}//end ContractListImpl