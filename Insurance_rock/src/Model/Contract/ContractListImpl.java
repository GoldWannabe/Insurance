package Model.Contract;

import java.util.ArrayList;

public class ContractListImpl implements ContractList {
	private ArrayList<Contract> contractList;
	
	public ContractListImpl(){
		this.contractList = new ArrayList<Contract>();

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


	@Override
	public Contract getCount(int select) {

		return this.contractList.get(select);
	}

//	public boolean update(){
//
//	}
}//end ContractListImpl