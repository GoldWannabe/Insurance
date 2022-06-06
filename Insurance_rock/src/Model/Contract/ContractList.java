package Model.Contract;

import java.util.ArrayList;

public interface ContractList {


	
	public boolean add(Contract contract);
	public boolean delete(String contractID);

	public ArrayList<Contract> getAll();
	public Contract getCount(int select);
	public Contract getcheck(String customerName_inser, String phoneNum_inser);
	public Contract getNum(int num);
//이 세개 정의되어있지않음..
	public Contract get(String name, String phoneNum);
	public Contract get(int num);
	public ArrayList<Contract> getContractByNameAndNUm(String CustomerName, String CustomerPhoneNu);
}//end ContractList