package Control.ContractTeam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

import Model.Contract.Contract;
import Model.Customer.Customer;
import Model.Insurance.GeneralInsurance;
import Model.Insurance.HouseInsurance;
import Model.Insurance.Insurance;
import View.Team.ContractTeamTui;
import exception.DBAcceptException;
import exception.WrongInputException;

public class UnderwritingRenew {
	public Contract contract;
	public ContractTeamTui contractTeamTui;
	public Customer customer;
	public Insurance insurance;
	public Contract originContract;
	public int accidentNum;
	
	public UnderwritingRenew() {
		this.contractTeamTui = new ContractTeamTui();
	}

	@SuppressWarnings("resource")
	public boolean verifyInsurance(Contract contract) {
		this.contract = contract;

		if (!getContract() || !getInsurance() || !getCustomer()) {
			throw new DBAcceptException();
		}

		if (!verifyPeriod() || !verifyPremium() || !checkRiseFee()) {
			return false;
		}

		this.contractTeamTui.showDetailRenewContract(this.contract, this.originContract);
		this.contractTeamTui.showDetailInsurance(this.insurance);
		this.contractTeamTui.showDetailRank(this.customer.getRank());

		int flag = -1;
		String selectList[] = new String[] { "1", "갱신", "2", "반려", "0", "취소" };
		Scanner scanner = new Scanner(System.in);
		this.contractTeamTui.showSelectRenewPermit();
		while (flag == -1) {
			try {
				flag = getflag(selectList, scanner.next());

				if (flag == 1) {
					return permitContract();
				} else if (flag == 2) {
					return FailContract(scanner);
				} else if (flag == 0) {
					this.contractTeamTui.showCancel();
					return true;
				}

			} catch (WrongInputException e) {
				System.err.println(e.getMessage());
			}
		}
		return false;
	}

	private boolean checkRiseFee() {
		int minFee = (int) (this.originContract.getInsuranceFee()*(1.1+0.1*this.accidentNum));
		if(this.contract.getInsuranceFee() <= minFee) {
		return false;
		} else {
			return true;
		}
	}

	private int getflag(String[] selectList, String select) throws WrongInputException {
		for (int i = 0; i < selectList.length; i = i + 2) {
			if (selectList[i].equals(select) || selectList[i + 1].equals(select)) {
				return Integer.parseInt(selectList[i]);
			}
		}
		throw new WrongInputException();
	}

	private boolean getContract() {
		this.originContract = new Contract();
		ResultSet resultSet = this.originContract.getContractByID(this.contract.getContractID());
		ResultSet accidentResultSet = this.originContract.getAccidentNum();
		try {
			resultSet.next();
			this.originContract.setContractID(resultSet.getString("contractID"));
			this.originContract.setCustomerID(resultSet.getString("customerID"));
			this.originContract.setInsuranceID(resultSet.getString("insuranceID"));
			this.originContract.setPaymentCycle(resultSet.getInt("paymentCycle"));
			this.originContract.setInsuranceFee(resultSet.getInt("insuranceFee"));
			this.originContract.setSecurityFee(resultSet.getInt("securityFee"));
			this.originContract.setPeriod(resultSet.getInt("period"));
			accidentResultSet.next();
			this.accidentNum = accidentResultSet.getInt("accidentNum");
				
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean getCustomer() {
		this.customer = new Customer();
		ResultSet resultSet = this.customer.getCustomerByID(this.contract.getCustomerID());
		ResultSet rankResultSet = this.customer.getRankSet(this.contract.getCustomerID());
		try {
			resultSet.next();
			this.customer.setCustomerID(resultSet.getString("customerID"));
			this.customer.setName(resultSet.getString("Name"));
			this.customer.setSSN(resultSet.getString("SSN"));
			this.customer.setSex(resultSet.getString("Sex"));
			this.customer.setPhoneNum(resultSet.getString("phoneNum"));
			this.customer.setAddress(resultSet.getString("address"));
			this.customer.setBankName(resultSet.getString("bankName"));
			this.customer.setAccountNum(resultSet.getString("accountNum"));
			this.customer.setInsuranceNum(resultSet.getDouble("insuranceNum"));

			while (rankResultSet.next()) {
				this.customer.addCustomerIDRankID(rankResultSet.getString("contractID"),
						rankResultSet.getString("RankID"));
			}

			this.customer.setRankByID(this.contract.getContractID());

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	private boolean getInsurance() {
		this.insurance = new GeneralInsurance();
		ResultSet resultSet = this.insurance.getInsuranceByID(this.contract.getInsuranceID());

		try {
			resultSet.next();
			if (resultSet.getString("insuranceType").equals("house")) {
				this.insurance = new HouseInsurance();
			}

			this.insurance.setInsuranceID(resultSet.getString("insuranceID"));
			this.insurance.setInsuranceName(resultSet.getString("insuranceName"));
			this.insurance.setStandardFee(resultSet.getInt("StandradFee"));
			this.insurance.setSpecialContract(resultSet.getString("specialContract"));
			this.insurance.setLongTerm(resultSet.getBoolean("longTerm"));
			this.insurance.setApplyCondition(resultSet.getString("applyCondition"));
			this.insurance.setCompensateCondition(resultSet.getString("compensateCondition"));
			this.insurance.setExplanation(resultSet.getString("explanation"));
			this.insurance.getRate(this.contract.getInsuranceID());

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean permitContract() {
		this.originContract.setPaymentCycle(this.contract.getPaymentCycle());
		this.originContract.setInsuranceFee(this.contract.getInsuranceFee());
		this.originContract.setSecurityFee(this.contract.getSecurityFee());
		LocalDate endDate = this.originContract.getEndDate().plusMonths(this.contract.getPeriod());
		this.originContract.setEndDate(endDate);

		return this.originContract.permitRenew();

	}

	private boolean FailContract(Scanner scanner) {
		this.contractTeamTui.showEnterReason();
		String reason = scanner.next();
		return deleteFailRenewContract(reason);
	}

	private boolean deleteFailRenewContract(String reason) {
		this.contractTeamTui.showFailRenewReason(reason);
		
		return this.contract.failRenew();
	}

	private boolean verifyPeriod() {

		if ((this.contract.getPeriod() >= 36) && (this.insurance.isLongTerm() != true)) {
			String reason = "장기 계약 불가능한 보험";

			return deleteFailRenewContract(reason);
		}

		return true;
	}

	private boolean verifyInsuranceFee(double rank) {

		double rate;
		if (rank < 1.5) {
			rate = this.insurance.getPremiumRate(0);
		} else if (rank < 2.5) {
			rate = this.insurance.getPremiumRate(1);
		} else {
			rate = this.insurance.getPremiumRate(2);
		}

		int standardFee = (int) (this.contract.getSecurityFee() * rate / 100);

		if (standardFee > this.contract.getInsuranceFee()) {
			String reason = "보험료가 기준보험료 보다 적습니다.";
			return deleteFailRenewContract(reason);
		} else {

			return true;
		}
	}

	private boolean verifyPremium() {

		double rank = getMaterialRank();

		switch (this.customer.getRank().getPurpose()) {
		case "living":
			rank = verifyLiving(rank);
			break;
		case "factory":
			rank = verifyFactory(rank);
			break;
		case "culturalAsset":
			rank = verifyCulturalAsset(rank);
			break;
		case "store":
			rank = verifyStore(rank);
			break;
		case "office":
			rank = verifyOffice(rank);
			break;
		case "carPark":
			rank = verifyCarPark(rank);
			break;
		case "warehouse":
			rank = verifyWarehouse(rank);
			break;

		}

		return verifyInsuranceFee(rank);
	}

	private double getMaterialRank() {
		switch (this.customer.getRank().getMaterial()) {
		case "rock":
			return 1;

		case "iron":
			return 1;

		case "brick":
			return 1.5;

		case "concrete":
			return 2;

		case "wood":
			return 3;
		}
		return 0;
	}

	private double verifyLiving(double rank) {
		double totalRank = rank;
		totalRank = totalRank + checkFirefacilities1();
		totalRank = totalRank + checkSurroundingFacilities1();
		if (this.customer.getRank().isHeight()) {
			totalRank = totalRank + 0.5;
		}
		if (this.customer.getRank().getScale() > 30) {
			totalRank = totalRank + 0.5;
		}
		return totalRank;
	}

	private double verifyFactory(double rank) {
		double totalRank = rank;
		totalRank = totalRank + checkFirefacilities2();
		totalRank = totalRank + checkSurroundingFacilities2();
		if (this.customer.getRank().isHeight()) {
			totalRank = totalRank + 0.5;
		}
		if (this.customer.getRank().getScale() > 200) {
			totalRank = totalRank + 0.5;
		}
		if (this.customer.getRank().getMaterial().equals("wood")) {
			totalRank = totalRank + 1.0;
		}
		return totalRank;
	}

	private double verifyCulturalAsset(double rank) {
		double totalRank = rank;
		totalRank = totalRank + checkFirefacilities2();
		totalRank = totalRank + checkSurroundingFacilities2();
		if (this.customer.getRank().getScale() > 60) {
			totalRank = totalRank + 0.5;
		}
		if (this.customer.getRank().getMaterial().equals("wood")) {
			totalRank = totalRank + 0.5;
		}
		return totalRank;
	}

	private double verifyStore(double rank) {
		double totalRank = rank;
		totalRank = totalRank + checkFirefacilities1();
		totalRank = totalRank + checkSurroundingFacilities1();
		if (this.customer.getRank().isHeight()) {
			totalRank = totalRank + 0.5;
		}
		if (this.customer.getRank().getScale() > 20) {
			totalRank = totalRank + 0.5;
		}

		return totalRank;
	}

	private double verifyOffice(double rank) {
		double totalRank = rank;
		totalRank = totalRank + checkFirefacilities1();
		totalRank = totalRank + checkSurroundingFacilities1();
		if (this.customer.getRank().isHeight()) {
			totalRank = totalRank + 0.5;
		}
		if (this.customer.getRank().getScale() > 20) {
			totalRank = totalRank + 0.5;
		}
		return totalRank;
	}

	private double verifyCarPark(double rank) {
		double totalRank = rank;
		totalRank = totalRank + checkFirefacilities1();
		totalRank = totalRank + checkSurroundingFacilities1();
		if (this.customer.getRank().getScale() > 200) {
			totalRank = totalRank + 0.5;
		}
		return totalRank;
	}

	private double verifyWarehouse(double rank) {
		double totalRank = rank;
		totalRank = totalRank + checkFirefacilities2();
		totalRank = totalRank + checkSurroundingFacilities2();
		if (this.customer.getRank().isHeight()) {
			totalRank = totalRank + 0.5;
		}
		if (this.customer.getRank().getScale() > 200) {
			totalRank = totalRank + 0.5;
		}
		if (this.customer.getRank().getMaterial().equals("wood")) {
			totalRank = totalRank + 1.0;
		}
		return totalRank;
	}

	private double checkFirefacilities1() {
		double facilities = this.customer.getRank().getFireFacilities();
		if (facilities > 4) {
			return -0.6;
		} else if (facilities > 2) {
			return -0.4;
		} else if (facilities > 1) {
			return -0.2;
		} else {
			return 0;
		}
	}

	private double checkFirefacilities2() {
		double facilities = this.customer.getRank().getFireFacilities();
		if (facilities > 4) {
			return -0.5;
		} else if (facilities > 2) {
			return -0.2;
		} else if (facilities > 1) {
			return 0.3;
		} else {
			return 0.5;
		}
	}

	private double checkSurroundingFacilities1() {
		double facilities = this.customer.getRank().getSurroundingFacilities();
		if (facilities < 2) {
			return 0.2;
		} else if (facilities < 4) {
			return 0.5;
		} else if (facilities < 5) {
			return 0.8;
		} else {
			return 1;
		}
	}

	private double checkSurroundingFacilities2() {
		double facilities = this.customer.getRank().getSurroundingFacilities();
		if (facilities < 2) {
			return 0.1;
		} else if (facilities < 4) {
			return 0.6;
		} else if (facilities < 5) {
			return 0.9;
		} else {
			return 1.1;
		}
	}

}
