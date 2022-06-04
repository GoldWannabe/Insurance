package Model.Contract;

import java.util.ArrayList;

public interface ContractList {


	
	public boolean add(Contract contract);
	public boolean delete(String contractID);
	public ArrayList<Contract> getAll();
	public Contract getcheck(String customerName_inser, String phoneNum_inser);
	public Contract get(int num);
//	public boolean update(){
//
//	}
}//end ContractList