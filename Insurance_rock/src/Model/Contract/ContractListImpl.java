package Model.Contract;

import java.util.ArrayList;

public class ContractListImpl implements ContractList {
	private ArrayList<Contract> contractList;

	public ContractListImpl() {
		this.contractList = new ArrayList<Contract>();

	}

	public void finalize() throws Throwable {

	}

	public boolean add(Contract contract) {
		if (this.contractList.add(contract))
			return true;
		return false;

	}

	@SuppressWarnings("unlikely-arg-type")
	public boolean delete(String contractID) {
		if (this.contractList.remove(contractID))
			return true;
		return false;
	}

	public ArrayList<Contract> getAll() {
		return this.contractList;
	}

	public Contract getcheck(String customerName_inser, String phoneNum_inser) {
		for (Contract contract : this.contractList) {
			if (contract.getCustomerName().equals(customerName_inser)
					&& contract.getPhoneNum().equals(phoneNum_inser)) {
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
	public Contract getNum(int num) {
		for (Contract contract : this.contractList) {
			if (contract.getNum() == num) {
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
	   
	   public Contract get(String CustomerName, String CustomerPhoneNum) {
	      for(Contract contract : this.contractList) {
	         if(contract.getCustomerName().equals(CustomerName) && contract.getPhoneNum().equals(CustomerPhoneNum)) return contract;
	      }
	      return null;
	   }


	   @Override
	   public ArrayList<Contract> getContractByNameAndNUm(String CustomerName, String CustomerPhoneNum) {
	      // TODO Auto-generated method stub
	      ArrayList<Contract> contracts = new ArrayList<Contract>();
	      for(Contract contract : this.contractList) {
	         if(contract.getCustomerName().equals(CustomerName) && contract.getPhoneNum().equals(CustomerPhoneNum)) {
//	            return contractList;
	            contracts.add(contract);
	         }
	      }
	      if(!(contracts.isEmpty())) {
	         return contracts;
	      }
	      return null;
	   }



	
}// end ContractListImpl