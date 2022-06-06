package Control.SalesTeam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import Model.Contract.Contract;
import Model.Contract.ContractList;
import Model.Contract.ContractListImpl;
import Model.Customer.Customer;
import Model.Customer.CustomerList;
import Model.Customer.CustomerListImpl;
import Model.Customer.Rank;
import Model.Insurance.GeneralInsurance;
import Model.Insurance.HouseInsurance;
import Model.Insurance.Insurance;
import Model.Insurance.Insurance.EInsurance;
import Model.Insurance.InsuranceList;
import Model.Insurance.InsuranceListImpl;
import View.Team.SalesTeamTui;
import exception.DBAcceptException;
import exception.WrongInputException;

public class InsuranceSales {
	public SalesTeamTui salesTeamTui;

	private Insurance insurance;
	private InsuranceList insuranceList = new InsuranceListImpl();

	private Customer customer;
	private CustomerList customerList = new CustomerListImpl();

	private Contract contract;
	private Rank rank;

	private ContractRejoin contractRejoin;
	
	public InsuranceSales() {
		this.salesTeamTui = new SalesTeamTui();
	}

	public boolean selectInsuranceType() {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			try {
				this.salesTeamTui.showInsuranceType();
				int flag = (getflag(scanner));
				if (flag == 1) {
					this.insurance = new GeneralInsurance();
					return getInsuranceList(scanner, EInsurance.general);

				} else if (flag == 2) {
					this.insurance = new HouseInsurance();
					return getInsuranceList(scanner, EInsurance.house);

				} else if (flag == 0) {
					this.salesTeamTui.showCancel();
					return true;
				}
			} catch (WrongInputException e) {
				System.err.println(e.getMessage());
			}
		}

	}

	private boolean getInsuranceList(Scanner scanner, EInsurance insuranceType) {
		ResultSet resultSet = this.insurance.getInsurance();
		try {
			while (resultSet.next()) {
				Insurance insurance = null;
				if (insuranceType.equals(EInsurance.general)) {
					insurance = new GeneralInsurance();
				} else if (insuranceType.equals(EInsurance.house)) {
					insurance = new HouseInsurance();
				}
				insurance.setInsuranceID(resultSet.getString("insuranceID"));
				insurance.setInsuranceName(resultSet.getString("insuranceName"));
				insurance.setStandardFee(resultSet.getInt("StandradFee"));
				insurance.setSpecialContract(resultSet.getString("specialContract"));
				insurance.setLongTerm(resultSet.getBoolean("longTerm"));
				insurance.setApplyCondition(resultSet.getString("applyCondition"));
				insurance.setCompensateCondition(resultSet.getString("compensateCondition"));
				insurance.setExplanation(resultSet.getString("explanation"));
				this.insuranceList.add(insurance);
			}

			if (this.insuranceList.getAll().isEmpty()) {
				throw new DBAcceptException();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DBAcceptException e) {
			System.err.println(e.getMessage());
		}

		this.salesTeamTui.showInsurance(this.insuranceList);
		return selectInsurance(insuranceType);
	}

	private boolean selectInsurance(EInsurance insuranceType) {
		Scanner scanner = new Scanner(System.in);
		this.salesTeamTui.showEnterInsuranceName();
		String insuranceName = scanner.next();
		this.insurance = this.insuranceList.get(insuranceName);

		while (this.insurance == null) {
			this.salesTeamTui.showSearchFail();
			this.salesTeamTui.showEnterInsuranceName();
			this.insurance = this.insuranceList.get(scanner.next());
		}
		this.salesTeamTui.showInsuranceDetail(this.insurance);

		while (true) {
			try {
				this.salesTeamTui.showSelectInsurance(this.insurance.getInsuranceName());
				int flag = getflag(scanner);
				if (flag == 1) {
					return selectCustomer(scanner, this.insurance);
				} else if (flag == 2) {
					if(rejoinContract(scanner, this.insurance)) {
						return true;
					}
				} else if (flag == 0) {
					this.salesTeamTui.showCancel();
					return true;
				}
			} catch (WrongInputException e) {
				e.printStackTrace();
			}
		}
	}

	private boolean rejoinContract(Scanner scanner, Insurance insurance) {
		this.contractRejoin = new ContractRejoin(this.salesTeamTui, insurance);
		return this.contractRejoin.rejoinStart(scanner);
	}

	public boolean selectCustomer(Scanner scanner, Insurance insurance) {
		this.customer = new Customer();
		while (true) {
			try {
				this.salesTeamTui.showSelectCustomer();
				int flag = getflag(scanner);
				if (flag == 1) {
					this.customer = newCustomer();
					return newContract(scanner);
				} else if (flag == 2) {
					this.customer = searchCustomer();
					return newContract(scanner);
				} else if (flag == 0) {
					this.salesTeamTui.showCancel();
					return true;
				}
			} catch (WrongInputException e) {
				System.err.println(e.getMessage());
			}
		}
	}

	private boolean newContract(Scanner scanner) {
		this.contract = new Contract();
		this.rank = new Rank();

		this.contract.setCustomerID(customer.getCustomerID());
		//this.rank.setCustomerID(customer.getCustomerID());

		this.contract.setInsuranceName(insurance.getInsuranceName());
		this.contract.setInsuranceID(insurance.getInsuranceID());
		this.contract.setCustomerName(this.customer.getName());
		this.contract.setPhoneNum(this.customer.getPhoneNum());

		this.salesTeamTui.showEnterSecurityFee();
		this.contract.setSecurityFee(checkInt(scanner));
		this.salesTeamTui.showEnterInsuranceFee();
		this.contract.setInsuranceFee(checkInt(scanner));
		this.salesTeamTui.showEnterPaymentCycle();
		this.contract.setPaymentCycle(checkInt(scanner));
		this.salesTeamTui.showEnterPeriod();
		this.contract.setPeriod(checkInt(scanner));

		this.salesTeamTui.showEnterFireFacilities();
		this.rank.setFireFacilities(checkFloat(scanner));
		this.salesTeamTui.showEnterScale();
		this.rank.setScale(checkInt(scanner));
		this.salesTeamTui.showEnterSurroundingFacilities();
		this.rank.setSurroundingFacilities(checkFloat(scanner));
		this.salesTeamTui.showEnterHeight();
		this.rank.setHeight(checkBoolean(scanner));
		this.salesTeamTui.showEnterMaterial();
		this.rank.setMaterial(scanner.next());
		this.salesTeamTui.showEnterPurpose();
		this.rank.setPurpose(scanner.next());

		// DB write
		this.contract.registerApplyContract();
		this.rank.register();

		// DB update
		this.customer.setInsuranceNum(this.customer.getInsuranceNum() + 0.1);
		this.customer.updateInsuranceNum();
		return false;
	}

	private Customer searchCustomer() {
		// set customerList with DAO(DB)
		ResultSet resultSet = this.customer.getCustomer();
		try {
			while (resultSet.next()) {
				Customer customer = new Customer();
				customer.setCustomerID(resultSet.getString("customerID"));
				customer.setName(resultSet.getString("Name"));
				customer.setSSN(resultSet.getString("SSN"));
				customer.setSex(resultSet.getString("Sex"));
				customer.setPhoneNum(resultSet.getString("phoneNum"));
				customer.setAddress(resultSet.getString("address"));
				customer.setBankName(resultSet.getString("bankName"));
				customer.setAccountNum(resultSet.getString("accountNum"));
				customer.setInsuranceNum(resultSet.getDouble("insuranceNum"));
				this.customerList.add(customer);
			}
			if (this.customerList.equals(null))
				throw new DBAcceptException();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DBAcceptException e) {
			System.err.println(e.getMessage());
		}

		// search start
		this.salesTeamTui.showSearchCustomerStart();
		while (true) {
			Scanner scanner = new Scanner(System.in);
			this.salesTeamTui.showEnterCustomerName();
			String customerName = scanner.next();
			this.salesTeamTui.showEnterCustomerPhoneNum();
			String phoneNum = scanner.next();

			if (this.customerList.search(customerName, phoneNum) == null) {
				this.salesTeamTui.showSearchFail();
			} else {
				this.customer = this.customerList.search(customerName, phoneNum);
				this.salesTeamTui.showSearchCustomerSuccess(customerName);
				break;
			}
		}
		return customer;
	}

	private Customer newCustomer() {
		this.salesTeamTui.showNewCustomerStart();
		Scanner scanner = new Scanner(System.in);
		Customer customer = new Customer();

		this.salesTeamTui.showEnterCustomerName();
		customer.setName(scanner.next());
		this.salesTeamTui.showEnterSSN();
		customer.setSSN(scanner.next());
		this.salesTeamTui.showEnterCustomerPhoneNum();
		customer.setPhoneNum(scanner.next());
		this.salesTeamTui.shoeEnterAddress();
		customer.setAddress(scanner.next());
		this.salesTeamTui.showEnterSex();
		customer.setSex(checkESex(scanner));
		this.salesTeamTui.showEnterBankName();
		customer.setBankName(scanner.next());
		this.salesTeamTui.showEnterAccountNum();
		customer.setAccountNum(scanner.next());

		customer.setInsuranceNum((double) 0);

		ArrayList<String> nullArray = new ArrayList<String>();
		nullArray.add("null");

		customer.setContractID(nullArray);
		customer.setRankID(nullArray);

		// add customerList
		this.customerList.add(customer);

		// save Customer DB
		customer.register();

		this.salesTeamTui.showNewCustomerEnd();
		return customer;
	}

	private int getflag(Scanner scanner) throws WrongInputException {
		String flag = scanner.next();

		if (flag.equals("1")) {
			return 1;
		} else if (flag.equals("2")) {
			return 2;
		} else if (flag.equals("0")) {
			return 0;
		} else {
			throw new WrongInputException();
		}
	}

	private boolean checkBoolean(Scanner scanner) {
		while (true) {
			try {
				if (!scanner.hasNextBoolean()) {
					String temp = scanner.next();
					throw new WrongInputException();
				}else {
					return scanner.nextBoolean();
				}
			} catch (WrongInputException e) {
				System.err.println(e.getMessage());
			}
			this.salesTeamTui.showReInput();
		}
	}
	
	private Integer checkInt(Scanner scanner) {
		while(true) {
			try {
				if(!scanner.hasNextInt()) {
					String temp = scanner.next();
					throw new WrongInputException();
				}else {
					return scanner.nextInt();
				}
			}catch(WrongInputException e) {
				System.err.println(e.getMessage());
			}
			this.salesTeamTui.showReInput();
		}
		
	}
	
	private float checkFloat(Scanner scanner) {
		while(true) {
			try {
				if(!scanner.hasNextFloat()) {
					String temp = scanner.next();
					throw new WrongInputException();
				}else {
					return scanner.nextFloat();
				}
			}catch(WrongInputException e) {
				System.err.println(e.getMessage());
			}
			this.salesTeamTui.showReInput();
		}
	}

	private String checkESex(Scanner scanner) {
		while (true) {
			try {
				String input = scanner.next();
				if (input.equals("male") || input.equals("female") || input.equals("none")) {
					return input;
				} else {
					throw new WrongInputException();
				}
			} catch (WrongInputException e) {
				System.err.println(e.getMessage());
			}
			this.salesTeamTui.showEnterSex();
		}
	}
}
