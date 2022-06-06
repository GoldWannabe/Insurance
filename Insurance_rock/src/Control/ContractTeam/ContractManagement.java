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
			String phoneNum = inser[1];
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
					this.contractList.add(contract);
					num++;
				}
				if (this.contractList.getcheck(customerName, phoneNum) != null) {
					throw new NonContractException();
				} else {
					isSearch = false;
				}
				if(currDate != LocalDate.now()) {
				}
			} catch (SQLException e) {
				
				System.out.println("날짜가 변경 되었습니다. 해당 계약은 이전의 날짜를 기준으로 한 정보이니 새로운 정보를 얻으려면 재접속 해주시기 바랍니다");
				throw new DBAcceptException();
			} // 계약ID, 고객ID, 가입자명, 연락처, 보험ID, 보험이름, 납부방식, 보험료, 미납액, 담보액, 지급액, 가입일, 만료일
			catch (NonContractException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return isSearch;

	}

	private void cancelContract(Scanner scanner) {
		ResultSet resultSet = this.customer.retriveCustomer(this.contract.getCustomerID());
		try {
			resultSet.next();
			this.customer.setInsuranceNum(resultSet.getDouble("insuranceNum") + 1);
			this.customer.setName((resultSet.getString("Name")));
			this.customer.updateInsuranceNum();
			boolean cancelCheck = this.contract.deleteContract();
			if (this.customer.getInsuranceNum() <= 0 && cancelCheck) {
				System.out.println(this.contract.getInsuranceName() + "삭제 후 \n 고객명 : " + this.customer.getName()
						+ "\n 연락처 : " + this.contract.getPhoneNum() + "\n 위의 고객님이 계약  중인 보험이 없어 고객 데이터를 정상적으로 삭제했습니다.");
			} else {
				System.out
						.println(this.customer.getName() + "님이 가입한 " + this.contract.getInsuranceName() + "를 삭제했습니다.");
			}
		} catch (SQLException e) {
			System.out.println("이보험에 계약된 고객이 조회되지 않았습니다.");
			System.out.println("DB오류");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void renewContract(Scanner scanner) {
		System.out.println("갱신할 내용에 해당하는 번호를 입력하세요. ");
		System.out.println("1. 등급 갱신, 2. 그외계약내용 갱신");// 선택이 아닌 계약내용을 갱신하면~ 거기에 해당하는 등급과 사고이력도 다시 적도록! 그리고
														// 기존에 이력 다 없애기. 사고이력은 갱신한다고 안바뀜.
		String selectNum = scanner.next();
		if (selectNum.equals("1")) {
			createRank(scanner);
		} else if (selectNum.equals("2")) {
			createBesidesContract(scanner);
			showRenewContract();
		} else {
			System.out.println("잘못입력하셨습니다 다시 입력해주세요.");
		}
	}

	private void showRenewContract() {
		ResultSet resultSet = this.contract.retriveRenewContract();
		int num = 1;
		try {
			resultSet.next();
			Contract contract = new Contract();
			contract.setNum(num);
			contract.setContractID(resultSet.getString("contractID"));
			contract.setCustomerID(resultSet.getString("customerID"));
			contract.setInsuranceID(resultSet.getString("insuranceID"));
			contract.setPaymentCycle(resultSet.getInt("paymentCycle"));
			contract.setInsuranceFee(resultSet.getInt("insuranceFee"));
			contract.setSecurityFee(resultSet.getInt("securityFee"));
			contract.setPeriod(resultSet.getInt("period"));

			System.out.println("-------------------------추가된 계약 신청 확인란---------------------------------");
			System.out.printf("%15s %15s %15s %20s ", "납부방식", "보험료", "담보액", "가입기간");
			System.out.println();
			System.out.println("--------------------------------------------------------------------");
			System.out.format("%10s %10s %15s %8s", contract.getPaymentCycle(), contract.getInsuranceFee(),
					contract.getSecurityFee(), contract.getPeriod());
			System.out.println();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// create Rank
		System.out.println("등급을 다시 기입해주십시오");

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
			System.out.println("계약 갱신 신청이 완료되었습니다.");
		} else {
			System.out.println("DB오류");
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
		System.out.println("갱신할 계약의 내용을 새롭게 입력해주세요");
		// 계약번호는 그대로
		System.out.println("담보액    : ");
		int securityFee = scanner.nextInt();
		this.contract.setSecurityFee(securityFee);
		System.out.println("보험료    : ");
		int insuranceFee = scanner.nextInt();
		this.contract.setInsuranceFee(insuranceFee);
		System.out.println("납부방식 : ");
		int paymentCycle = scanner.nextInt();
		this.contract.setPaymentCycle(paymentCycle);
		System.out.println("갱신기간 : ");
		int period = scanner.nextInt();
		this.contract.setPeriod(period);
		if (period == 0 || period < 6) {
			System.out.println("갱신 기간이 올바르지 않습니다. 6개월 이상으로 입력하여 주십시오.");
		}
		boolean createCheck = this.contract.createBesidesConstract();
		if (!createCheck) {
			System.out.println("DB문제");
		} else {
			System.out.println("계약 갱신신청이 완료되었습니다.");
			// 홈화면으로 가기. or 종료
		}
		boolean deleteCheck = this.contract.deleteContract();
		if (!deleteCheck) {
			System.out.println("DB문제");
		} else {
			System.out.println("기존 계약이 삭제되었습니다.");
		}
	}

}
