package ContractTeam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

import Accident.Accident;
import Accident.AccidentListImpl;
import Contract.Contract;
import Contract.ContractListImpl;
import dao.AccidentDao;
import dao.ContractDao;

public class ContractManagement {
	
	private Contract contract;
	private ContractListImpl contractList;
	private ContractDao contractDAO;
	
	public ContractManagement() {
		this.contractList = new ContractListImpl();
		
	}


	public boolean searchContract(Scanner scanner) {
		return false;
		// TODO Auto-generated method stub
		
	}

	public boolean viewContract(Scanner scanner) {
		showContract();
		searchContract(scanner);
		// TODO Auto-generated method stub
		return false;
	}

	private void showContract() {

		
	}
	

}
