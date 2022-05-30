package Contract;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ContractListImpl implements ContractList {

	private ArrayList<Contract> contractList;

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