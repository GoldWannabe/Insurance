package Contract;

import java.util.ArrayList;

/**
 * @author ansm6
 * @version 1.0
 * @created 08-5-2022 ���� 10:34:18
 */
public class ContractListImpl implements ContractList {

	private ArrayList<Contract> contractList = new ArrayList<Contract>();

	public ContractListImpl(){

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

//	public boolean update(){
//
//	}
}//end ContractListImpl