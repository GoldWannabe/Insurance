package Control.CompensateTeam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

import Model.Accident.Accident;
import Model.Accident.AccidentListImpl;
import Model.Contract.Contract;
import Model.Contract.ContractListImpl;
import Model.Customer.Customer;
import Model.Insurance.GeneralInsurance;
import Model.Insurance.Insurance;
import Model.Insurance.Insurance.EInsurance;
import Model.Provision.Provision;
import View.Team.CompensateTeamTui;
import exception.AlreadyPayCompleted;
import exception.BankfileAcceptException;
import exception.DBAcceptException;
import exception.LackInsuranceBank;
import exception.NonExistAccident;
import exception.NonExistContract;
import exception.NotDamageAssessment;
import exception.WrongInputChannel;
import exception.WrongInputException;

public class DamageAssessment {

	private CompensateTeamTui compensateTeamTui;
	private ContractListImpl contractList;
	private AccidentListImpl accidentList;
	private Accident accident;
	private Contract contract;
	private Customer customer;

	public DamageAssessment() {
		this.accidentList = new AccidentListImpl();
		this.accident = new Accident();
		this.contractList = new ContractListImpl();
		this.contract = new Contract();
		this.compensateTeamTui = new CompensateTeamTui();
		this.customer = new Customer();

	}

	public boolean selectAccidentMenagement(Scanner scanner) {

		boolean isSearch = false;
		boolean overcheck = false;
		try {
			while (!isSearch) {
				compensateTeamTui.viewbasic();
				String selectNum = scanner.next();
				switch (selectNum) {
				case "1":
					overcheck = search();
					if (!overcheck) {
						compensateTeamTui.viewAccident(accidentList);
						return compansate(scanner);
					}
					break;
				case "2":
					overcheck = addcheck();
					if (!overcheck) {
						showConctarct(scanner);
						return add(scanner);
					}
					break;
				case "0":
					compensateTeamTui.cancelback();
					return true;
				default:
					throw new WrongInputException();
				}
			}
		} catch (NonExistAccident e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NonExistContract e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WrongInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WrongInputChannel e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isSearch;

	}

	private boolean compansate(Scanner scanner) throws WrongInputChannel {
		compensateTeamTui.viewCompansate();
		String selectNum = scanner.next();
		switch (selectNum) {
		case "1":
			return paycompansate(scanner);

		case "2":
			update(scanner);
			break;
		}
		return false;

	}

	private boolean paycompansate(Scanner scanner) throws WrongInputChannel {
		int num = compensateTeamTui.viewcomapansateNum(scanner);
		this.accident = accidentList.getNum(num);
		compensateTeamTui.viewcomapansate(this.accident);

		String selectNum = scanner.next();
		if (selectNum.equals("1")) {
			pay();
		} else if (selectNum.equals("2")) {
			compensateTeamTui.cancelhome();
			return true;
		}else {
			throw new WrongInputChannel();
		}
		return false;

	}

	private void pay() {
		this.customer.setCustomerID(this.accident.getCustomerID());
		ResultSet resultBank = this.customer.retrivecustomerBank();
		this.contract.setContractID(this.accident.getContractID());
		ResultSet resultlongtermFee = contract.retrivelongtermFee();
		int liablityCost = 0;

		try {
			File file = new File(".//File//InsuranceBank.txt");
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextInt()) {
				liablityCost = scanner.nextInt();
			}
			scanner.close();

			int result = liablityCost - this.accident.getLiablityCost();
			if (result <= 0) {
				throw new LackInsuranceBank();
			} else {
				FileWriter fileWriter = new FileWriter(file, false);
				String resultToString = Integer.toString(result);
				fileWriter.write(resultToString);
				fileWriter.flush();
				fileWriter.close();

				fileWriter = new FileWriter(".//File//CustomerBank.txt", false);
				String resultToString2 = Integer.toString(this.accident.getLiablityCost());
				fileWriter.write(resultToString2);
				fileWriter.flush();
				fileWriter.close();
				if (resultToString2 == null) {
					throw new BankfileAcceptException();
				}
			}
			if (resultBank != null && resultlongtermFee != null) {
				if (!(this.accident.isPayCompleted())) {
					this.accident.setPayCompleted(true);
					this.accident.updatePaycompleted();
					while (resultlongtermFee.next()) {
						this.contract.setInsuranceID(resultlongtermFee.getString("insuranceID"));
						this.contract.setInsuranceName(resultlongtermFee.getString("insuranceName"));
						this.contract.setProvisionFee(
								resultlongtermFee.getInt("provisionFee") + this.accident.getLiablityCost());
						this.contract.setSecurityFee(resultlongtermFee.getInt("securityFee"));
						this.contract.setStartDate(LocalDate.parse(resultlongtermFee.getString("startDate")));
						this.contract.setEndDate(LocalDate.parse(resultlongtermFee.getString("endDate")));
					}
					while (resultBank.next()) {
						this.customer.setBankName(resultBank.getString("bankName"));
						this.customer.setAccountNum(resultBank.getString("accountNum"));
					}
					// 사고이력없음.. 없데이트 불가능 나머지 지급액만 업데이트하겠음.
					this.contract.updateProvisionFee();

					LocalDate startDate = this.contract.getEndDate();
					int StartYear = startDate.getYear();
					LocalDate endDate = this.contract.getStartDate();
					int endYear = endDate.getYear();
					Insurance generalinsurance = new GeneralInsurance();
					ResultSet insuranceType = generalinsurance.retriveType(this.contract.getInsuranceName());

//					만료일-시작일이 3년이상이면 false 아니면 true 입력.
					Provision provision = new Provision();
					if (endYear - StartYear >= 3) {
						provision.setLongTerm(true);
					} else {
						provision.setLongTerm(false);
					}
					while (insuranceType.next()) {
						EInsurance e = EInsurance.valueOf(insuranceType.getString("insuranceType"));
						provision.setInsuranceType(e);
					} //
					int overCost = this.contract.getSecurityFee() - this.contract.getProvisionFee();
					if (overCost <= 0) {
						compensateTeamTui.viewOverCost();
						provision.setCompensation(this.contract.getSecurityFee());
					} else {
						provision.setCompensation(this.accident.getLiablityCost());
					}
					provision.setProvisionID(UUID.randomUUID().toString());
					provision.setCustomerID(this.customer.getCustomerID());
					provision.setContractID(this.accident.getContractID());
					provision.setCustomerName(this.accident.getCustomerName());
					provision.setPhoneNum(this.accident.getPhoneNum());
					provision.setInsuranceName(this.contract.getInsuranceName());
					provision.setBankName(this.customer.getBankName());
					provision.setAccountNum(this.customer.getAccountNum());
					provision.setCompensationDate(LocalDate.now());
					// 지급ID, 고객ID, 가입자명, 연락처, 계좌번호,은행명, 보상금액, 지급날짜.보험이름, 장기여부, 보험종류, 계약ID를 저장.
					provision.creatNew();

					compensateTeamTui.viewNewProvision(provision);
				} else {
					throw new AlreadyPayCompleted();
				}
			} else {
				throw new DBAcceptException();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlreadyPayCompleted e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LackInsuranceBank e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BankfileAcceptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void showConctarct(Scanner scanner) {
		// CosntractList.
		compensateTeamTui.viewContract(contractList);

	}

	private boolean addcheck() throws NonExistContract {

		// 가입자명, 사고날짜, 연락처가 해당 고객과 동일하다면 거기에 이어서 사고내역 추가하기 검색은 불러오기.
		Scanner scanner = new Scanner(System.in);
		// 계약 DB에서 가져옴.

		String[] inser = compensateTeamTui.viewaddcheck(scanner);

		contract.setCustomerName(inser[0]);
		contract.setPhoneNum(inser[1]);
		//
		ResultSet resultSet = contract.retrivecontract();
		// 사고번호ID, 계약ID ,고객ID,가입자명, 연락처,사고날짜,사고내용 ,총비용,손해정도,비용종류,지급여부,책임비율,책임비용
		int num = 1;
		try {
			while (resultSet.next()) {
				Contract contract = new Contract();
				contract.setNum(num);
				contract.setContractID(resultSet.getString("contractID"));
				contract.setCustomerID(resultSet.getString("customerID"));
				contract.setCustomerName(resultSet.getString("customerName"));
				contract.setPhoneNum(resultSet.getString("customerPhoneNum"));
				contract.setInsuranceID(resultSet.getString("insuranceID"));
				contract.setInsuranceName(resultSet.getString("insuranceName"));
				contract.setPaymentCycle(resultSet.getInt("paymentCycle"));
				contract.setInsuranceFee(resultSet.getInt("insuranceFee"));
				contract.setUnpaidFee(resultSet.getInt("unpaidFee"));
				contract.setSecurityFee(resultSet.getInt("securityFee"));
				contract.setProvisionFee(resultSet.getInt("provisionFee"));
				contract.setStartDate(LocalDate.parse(resultSet.getString("startDate")));
				contract.setEndDate(LocalDate.parse(resultSet.getString("endDate")));
				num++;
				contractList.add(contract);
			} // 계약ID, 고객ID, 가입자명, 연락처, 보험ID, 보험이름, 납부방식, 보험료, 미납액, 담보액, 지급액, 가입일, 만료일
		} catch (SQLException e) {
			throw new DBAcceptException();
		}

		boolean overcheck = false;
		while (!overcheck) {
			if (this.contractList.getcheck(inser[0], inser[1]) != null) {
				return overcheck = false;
			} else {
				overcheck = true;
				throw new NonExistContract();
			}
		}
		return overcheck;

	}

	// 사고정보(사고내용, 비용, 종류, 손해정도, 총비용, 책임 비용, 책임비율 )입력창과 계약 목록 선택창??과 추가, 취소버튼을 출력.

	private boolean search() throws NonExistAccident {
		this.accidentList.clear();
		Scanner scanner = new Scanner(System.in);
		boolean overcheck = true;

		String[] inser = compensateTeamTui.veiwSearch(scanner);
		String customerName_inser = inser[0];

		int[] dateArray = new int[inser.length];
		for (int i = 1; i < inser.length; i++) {
			dateArray[i] = Integer.parseInt(inser[i]);

		}
		LocalDate accidentDate_inser = LocalDate.of(dateArray[1], dateArray[2], dateArray[3]);
		accident.setCustomerName(customerName_inser);
		accident.setAccidentDate(accidentDate_inser);

		ResultSet resultSet = accident.retriveaccident();
		int num = 1;
		try {
			while (resultSet.next()) {
				Accident accident = new Accident();
				accident.setNum(num);
				accident.setID(resultSet.getString("accidentID"));
				accident.setContractID(resultSet.getString("contractID"));
				accident.setCustomerID(resultSet.getString("customerID"));
				accident.setCustomerName(resultSet.getString("customerName"));
				accident.setPhoneNum(resultSet.getString("customerPhoneNum"));
				accident.setAccidentDate(LocalDate.parse(resultSet.getString("accidentDate")));
				accident.setContent(resultSet.getString("content"));
				accident.setTotalCost(resultSet.getInt("totalCost"));
				accident.setDamagePer(resultSet.getInt("damagePer"));
				accident.setKindOfCost(resultSet.getString("kindOfCost"));
				accident.setPayCompleted(resultSet.getBoolean("payCompleted"));
				accident.setLiablityRate(resultSet.getInt("liablityRate"));
				accident.setLiablityCost(resultSet.getInt("liablityCost"));
				overcheck = false;
				num++;
				accidentList.add(accident);
			}
		} catch (SQLException e) {
			throw new DBAcceptException();
		}
		if (accidentList.get(customerName_inser, accidentDate_inser) != null) {
			for (Accident accident : accidentList.get(customerName_inser, accidentDate_inser)) {
				if (accident.isPayCompleted() != false && accident.isPayCompleted() != true
						|| accident.getDamagePer() == 0 || accident.getTotalCost() == 0
						|| accident.getLiablityRate() == 0) {
					throw new NotDamageAssessment();
				}
			}
			return overcheck;
		} else {
			throw new NonExistAccident();
		}
	}

//			isSearch = accident.search(customerName_inser, accidentDate_inser, isSearch);


	private void update(Scanner scanner) throws WrongInputChannel {
		boolean updateCompleted = false;
		int num = compensateTeamTui.viewUpdateNum(scanner);

		this.accident = accidentList.getNum(num);

		if (num != 0) {
			compensateTeamTui.viewUpdateList(scanner, this.accident);

			String selectNum = scanner.next();
			if (selectNum.equals("1")) {
				int date[] = compensateTeamTui.viewUpdateDate(scanner);

				LocalDate accidentdate = LocalDate.of(date[0], date[1], date[2]);
				this.accident.setAccidentDate(accidentdate);
				updateCompleted = this.accident.updatedate();
			} else if (selectNum.equals("2")) {
				compensateTeamTui.viewUpdateContent();
				String content = scanner.next();
				this.accident.setContent(content);
				updateCompleted = accident.updatecontent();
			} else if (selectNum.equals("3")) {
				compensateTeamTui.viewUpdateTotalCost();
				int totalCost = scanner.nextInt();
				this.accident.setTotalCost(totalCost);
				updateCompleted = accident.updatetotal();
			} else if (selectNum.equals("4")) {
				compensateTeamTui.viewUpdateDamagePer();
				int damagePer = scanner.nextInt();
				this.accident.setDamagePer(damagePer);
				updateCompleted = accident.updateDamage();
			} else if (selectNum.equals("5")) {
				compensateTeamTui.viewUpdateKindOfCost();
				String kindOfCost = scanner.next();
				this.accident.setKindOfCost(kindOfCost);
				updateCompleted = accident.updateKind();
			} else if (selectNum.equals("6")) {
				compensateTeamTui.viewUpdateLiablityCost();
				int liablityCost = scanner.nextInt();
				this.accident.setLiablityCost(liablityCost);
				updateCompleted = accident.updateLiablityCost();
			} else if (selectNum.equals("7")) {
				compensateTeamTui.viewUpdateLiablityRate();
				int liablityRate = scanner.nextInt();
				this.accident.setLiablityRate(liablityRate);
				updateCompleted = accident.updateLiablityRate();
			}else {
				throw new WrongInputChannel();
			}
			if (updateCompleted) {
				compensateTeamTui.viewUpdateAccident(this.accident);
			} else {
				throw new DBAcceptException();
			}
		}

	}

	private boolean add(Scanner scanner) throws NonExistContract {
		int num = 0;
		boolean isSearch = false;
		while (!isSearch) {
			compensateTeamTui.viewselectNumContract();
			num = scanner.nextInt();
			if (this.contractList.get(num) != null) {
				isSearch = true;
			} else {
				throw new NonExistContract();

			}
		}
		if (isSearch) {

			String content = null;
			String kindOfCost = null;
			int damagePer = 0;
			int totalCost = 0;
			int liablityCost = 0;
			int liablityRate = 0;

			int[] date = compensateTeamTui.viewAccidentinsert(scanner);
			LocalDate accidentDate_inser = LocalDate.of(date[0], date[1], date[2]);

			String[] toStirng = compensateTeamTui.viewToString(scanner);
			content = toStirng[0];
			kindOfCost = toStirng[1];

			int[] toInt = compensateTeamTui.viewToInt(scanner);
			damagePer = toInt[0];
			totalCost = toInt[1];
			liablityCost = toInt[2];
			liablityRate = toInt[3];

			// accidentList.get(customerName_inser, phoneNum_inser);
			Contract contract = this.contractList.get(num);

			accident.setID(UUID.randomUUID().toString());
			accident.setContractID(contract.getContractID());
			accident.setCustomerID(contract.getCustomerID());
			accident.setCustomerName(contract.getCustomerName());
			accident.setPhoneNum(contract.getPhoneNum());
			accident.setAccidentDate(accidentDate_inser);
			accident.setContent(content);
			accident.setKindOfCost(kindOfCost);
			accident.setDamagePer(damagePer);
			accident.setTotalCost(totalCost);
			accident.setLiablityCost(liablityCost);
			accident.setPayCompleted(false);
			accident.setLiablityRate(liablityRate);
			accidentList.add(accident);
		} else {
			compensateTeamTui.cancelhome();
		}
		// 사고번호ID, 계약ID ,고객ID,가입자명, 연락처,사고날짜,사고내용 ,총비용,손해정도,비용종류,지급여부,책임비율,책임비용
//				accidentList.addaccident(accidentDate_inser, content, kindOfCost, damagePer, totalCost, liablityCost, liablityRate);

		if (accident.addaccident()) {
			compensateTeamTui.viewNewAccident(accident);
		}

		return isSearch;

	}

}
