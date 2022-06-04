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


	public ArrayList<Contract> getAll() {
		return this.contractList;
	}


	


	public Contract getcheck(String customerName_inser, String phoneNum_inser) {
		for (Contract contract : this.contractList) {
			if (contract.getCustomerName().equals(customerName_inser) && contract.getPhoneNum().equals(phoneNum_inser)) {
				return contract;
			}
		}
		return null;
	}


	public Contract get(int num) {
		for (Contract contract : this.contractList) {
			if (contract.getNum() == num) {
				return contract;
			}
		}
		return null;
	}


//	public boolean update(){
//
//	}
}//end ContractListImpl