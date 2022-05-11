package Contract;

import java.util.ArrayList;

/**
 * @version 1.0
 * @created 08-5-2022 ���� 10:34:18
 */
public interface ContractList {


	
	public boolean add(Contract contract);
	public boolean delete(String contractID);
	public ArrayList<Contract> get(String customerName, String contractID);
	
//	public boolean update(){
//
//	}
}//end ContractList