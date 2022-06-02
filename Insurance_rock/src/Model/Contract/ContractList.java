package Model.Contract;

import java.util.ArrayList;

public interface ContractList {


	
	public boolean add(Contract contract);
	public boolean delete(String contractID);
	public ArrayList<Contract> get(String customerName, String contractID);
//	public boolean update(){
//
//	}
}//end ContractList