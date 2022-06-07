package Control.ContractTeam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import Model.Contract.Contract;
import Model.Contract.ContractListImpl;
import Model.Customer.Customer;
import Model.Customer.Rank;
import View.Team.ContractTeamTui;
import exception.ChangedDateException;
import exception.DBAcceptException;
import exception.NonContractException;
import exception.WrongInputException;

public class ContractManagement {

	private Contract contract;
	private ContractListImpl contractList;
	private ContractTeamTui contractTeamTui;
	private ArrayList<String> accidentHistoryArray;

	private Customer customer;

	private Rank rank;
	private LocalDate currDate = LocalDate.now();

	public ContractManagement() {
		this.contract = new Contract();
		this.contractList = new ContractListImpl();
		this.contractTeamTui = new ContractTeamTui();

		this.customer = new Customer();

	}
//검색, 갱신, 해지, 취소

	public boolean searchContract(Scanner scanner)  {
		boolean isSearch = true;
		this.contractTeamTui.viewSearch();
		String selectNum = scanner.next();
		try {
		switch (selectNum) {
		case "1":
			isSearch = search(scanner);
			if (isSearch) {
				contractTeamTui.viewContract(this.contractList);
				selectRenewCancel(scanner);
			} else {
				this.contractTeamTui.viewNotInquiry();
			}
		case "0":
			contractTeamTui.viewcancelHome();
			return false;
		default:
			throw new WrongInputException();
		} 
		}catch (WrongInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isSearch;
		
	}

	private boolean selectRenewCancel(Scanner scanner) throws WrongInputException{
		boolean historyCheck = false;
		this.contractTeamTui.veiwselectContract();
		
//		int length = showRenew();

		int num;
		num = scanner.nextInt();
		
		this.contract = contractList.getNum(num);

		historyCheck = searchAccidentHistory();// true면 없어져야 함.
		if (historyCheck) {
			this.contractTeamTui.viewAccidentHistory(this.accidentHistoryArray);
		} else {
			this.contractTeamTui.veiwNotAccidentHistory();

		}

		this.contractTeamTui.viewSelectRenewCacel();
		String selectNum = scanner.next();
		if (selectNum.equals("1")) {
			renewContract(scanner);
		} else if (selectNum.equals("2")) {
			cancelContract(scanner);
		} else if (selectNum.equals("0")) {
			return false;
		} else {
			throw new WrongInputException();
		}
		return historyCheck;
	}

	private boolean searchAccidentHistory() {
		boolean historyCheck = false;
		ResultSet resultAccidentHistory = this.contract.resultAccidentHistory();
		this.accidentHistoryArray = new ArrayList<String>();
		try {
			// 검색이 안될경우 false를 보내야 함.
			// 사고이력이 없는 경우.
			while (resultAccidentHistory.next()) {
				this.accidentHistoryArray.add(resultAccidentHistory.getString("contractID"));
				this.accidentHistoryArray.add(resultAccidentHistory.getString("accidentID"));
			}

			if (!accidentHistoryArray.isEmpty()) {
				historyCheck = true;
			} else {
				historyCheck = false;

			}
		} catch (SQLException e) {
			throw new DBAcceptException();
		}
		return historyCheck;
	}

	private boolean search(Scanner scanner) {
		boolean isSearch = true;
		if (isSearch) {
			String inser[] = this.contractTeamTui.viewInsertName(scanner);
			String customerName = inser[0];
			this.contract.setCustomerName(customerName);
			String phoneNum = inser[1];
			this.contract.setPhoneNum(phoneNum);
			ResultSet resultContract = this.contract.retrivecontract();
			int num = 1;
			try {
				while (resultContract.next()) {// 모든 정보 set
					Contract contract = new Contract();
					contract.setNum(num);
					contract.setContractID(resultContract.getString("contractID"));
					contract.setCustomerID(resultContract.getString("customerID"));
					contract.setCustomerName(resultContract.getString("customerName"));
					contract.setPhoneNum(resultContract.getString("customerPhoneNum"));
					contract.setInsuranceID(resultContract.getString("insuranceID"));
					contract.setInsuranceName(resultContract.getString("insuranceName"));
					contract.setPaymentCycle(resultContract.getInt("paymentCycle"));
					contract.setInsuranceFee(resultContract.getInt("insuranceFee"));
					contract.setUnpaidFee(resultContract.getInt("unpaidFee"));
					contract.setSecurityFee(resultContract.getInt("securityFee"));
					contract.setProvisionFee(resultContract.getInt("provisionFee"));
					contract.setStartDate(LocalDate.parse(resultContract.getString("startDate")));
					contract.setEndDate(LocalDate.parse(resultContract.getString("endDate")));
					num++;
					this.contractList.add(contract);
				}
				if (this.contractList.getcheck(customerName, phoneNum) != null) {
					isSearch = true;
				} else {
					isSearch = false;
				}
				if(!(LocalDate.now().equals(currDate))) {
					throw new ChangedDateException();
				}
			} catch (SQLException e) {
				throw new DBAcceptException();
			} // 계약ID, 고객ID, 가입자명, 연락처, 보험ID, 보험이름, 납부방식, 보험료, 미납액, 담보액, 지급액, 가입일, 만료일
			 catch (ChangedDateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return isSearch;

	}
	
//	private int showRenew() {
//		this.contractTeamTui.showRenewConractColumn();
//		int length = 1;
//
//		for (Contract contract : this.contractList.getAll()) {
//			this.contractTeamTui.showRenewContracts(length, contract);
//			length++;
//		}
//		return length;
//
//	}

	private void cancelContract(Scanner scanner) {
		ResultSet resultSet = this.customer.retriveCustomer(this.contract.getCustomerID());
		try {
			resultSet.next();
			this.customer.setInsuranceNum(resultSet.getDouble("insuranceNum") + 1);
			this.customer.setName((resultSet.getString("Name")));
			this.customer.updateInsuranceNum();
			boolean cancelCheck = this.contract.deleteContract();
			if (this.customer.getInsuranceNum() <= 0 && cancelCheck) {
				this.contractTeamTui.viewDeleteCustoemr(this.contract, this.customer);
				this.customer.deleteCustomer();
			} else {
				this.contractTeamTui.viewDeleteContract(this.contract, this.customer);
			}
		} catch (SQLException e) {
			throw new DBAcceptException();
		}

	}

	private void renewContract(Scanner scanner) throws WrongInputException {
		
		this.contractTeamTui.viewrenew();
		
		String selectNum = scanner.next();
		if (selectNum.equals("1")) {
			createRank(scanner);
		} else if (selectNum.equals("2")) {
			createBesidesContract(scanner);
			showRenewContract();
		} else {
			throw new WrongInputException();
		}
	}

	private void showRenewContract() {
		ResultSet resultSet = this.contract.retriveRenewContract();
		try {
			resultSet.next();
			Contract contract = new Contract();
			contract.setContractID(resultSet.getString("contractID"));
			contract.setCustomerID(resultSet.getString("customerID"));
			contract.setInsuranceID(resultSet.getString("insuranceID"));
			contract.setPaymentCycle(resultSet.getInt("paymentCycle"));
			contract.setInsuranceFee(resultSet.getInt("insuranceFee"));
			contract.setSecurityFee(resultSet.getInt("securityFee"));
			contract.setPeriod(resultSet.getInt("period"));

			this.contractTeamTui.viewNewRenewContract(contract);
			
		} catch (SQLException e) {
			throw new DBAcceptException();
		}

	}
	

	private void createRank(Scanner scanner) {
		// coustomer속 contract중 하나에 매치하는 irankID를 가져와서 다시 등급내용을 입력하도록 하기.

		String contractID = this.contract.getContractID();
		String custoemrID = this.contract.getCustomerID();

		ArrayList<String> arrayContractID = new ArrayList<String>();
		ArrayList<String> arrayRankID = new ArrayList<String>();

		try {
			ResultSet resultConstomerRank = this.customer.getRankSet(custoemrID);
			while (resultConstomerRank.next()) {// 모든 정보 set
				this.customer.setCustomerID(resultConstomerRank.getString("customerID"));
				arrayContractID.add(resultConstomerRank.getString("contractID"));
				this.customer.setContractID(arrayContractID);
				arrayRankID.add(resultConstomerRank.getString("RankID"));
				this.customer.setRankID(arrayRankID);
			}
		} catch (SQLException e) {
			throw new DBAcceptException();
		}

		// create Rank
		this.contractTeamTui.viewAgainRank();

		if (this.customer.setRankByID(contractID)) {
			this.rank = new Rank();
//				this.rank.delete(rankID);
			this.contractTeamTui.showEnterFireFacilities();
			this.rank.setFireFacilities(checkFloat(scanner));
			this.contractTeamTui.showEnterScale();
			this.rank.setScale(checkInt(scanner));
			this.contractTeamTui.showEnterSurroundingFacilities();
			this.rank.setSurroundingFacilities(checkFloat(scanner));
			this.contractTeamTui.showEnterHeight();
			this.rank.setHeight(checkBoolean(scanner));
			this.contractTeamTui.showEnterMaterial();
			this.rank.setMaterial(scanner.next());
			this.contractTeamTui.showEnterPurpose();
			this.rank.setPurpose(scanner.next());
		}
		this.rank.register(); // 새롭게 Rank만듦. 이떄 아이디 Rank에 Set됌.

		// 가져오기 rank ID
		ResultSet rausltSet = this.customer.retriveID(contractID);
		String rankID = null;
		try {
			while (rausltSet.next()) {
				Rank rank = new Rank();
				rank.setRankID(rausltSet.getString("RankID"));
				rankID = rank.getRankID();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 맵핑 테이블 CustomerRank의 Rank도 갱신함.
		boolean rankCheck = this.customer.updateCustomerRank(this.rank.getRankID(), rankID);
		if (rankCheck) {
			this.contractTeamTui.viewCompletedRenew();
		}
		// delete Rank
		this.customer.deleteRank(contractID);
	}

	private boolean checkBoolean(Scanner scanner) {
		while (true) {
			try {
				if (!scanner.hasNextBoolean()) {
					String temp = scanner.next();
					throw new WrongInputException();
				} else {
					return scanner.nextBoolean();
				}
			} catch (WrongInputException e) {
				System.err.println(e.getMessage());
			}
			this.contractTeamTui.showReInput();
		}
	}

	private Integer checkInt(Scanner scanner) {
		while (true) {
			try {
				if (!scanner.hasNextInt()) {
					String temp = scanner.next();
					throw new WrongInputException();
				} else {
					return scanner.nextInt();
				}
			} catch (WrongInputException e) {
				System.err.println(e.getMessage());
			}
			this.contractTeamTui.showReInput();
		}

	}

	private float checkFloat(Scanner scanner) {
		while (true) {
			try {
				if (!scanner.hasNextFloat()) {
					String temp = scanner.next();
					throw new WrongInputException();
				} else {
					return scanner.nextFloat();
				}
			} catch (WrongInputException e) {
				System.err.println(e.getMessage());
			}
			this.contractTeamTui.showReInput();
		}
	}

	private void createBesidesContract(Scanner scanner) {
		// 계약 선택한 상태
		this.contractTeamTui.viewNewRenew();
		// 계약번호는 그대로
		
		this.contractTeamTui.viewSecurityFee();
		int securityFee = scanner.nextInt();
		this.contract.setSecurityFee(securityFee);
		
		this.contractTeamTui.viewInsuranceFee();
		int insuranceFee = scanner.nextInt();
		this.contract.setInsuranceFee(insuranceFee);
		
		this.contractTeamTui.viewPaymentCycle();
		int paymentCycle = scanner.nextInt();
		this.contract.setPaymentCycle(paymentCycle);

		this.contractTeamTui.viewPeriod();
		int period = scanner.nextInt();
		this.contract.setPeriod(period);
		if (period == 0 || period < 6) {
			this.contractTeamTui.viewOverPeriod();
		}
		boolean createCheck = this.contract.createBesidesConstract();
		if (!createCheck) {
			throw new DBAcceptException();
		} else {
			this.contractTeamTui.viewCompletedRenew();
			// 홈화면으로 가기. or 종료
		}
		boolean deleteCheck = this.contract.deleteContract();
		if (!deleteCheck) {
			throw new DBAcceptException();
		} else {
			this.contractTeamTui.viewDelete();
		}
	}

}
