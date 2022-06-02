package Control.ContractTeam;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import Model.Insurance.GeneralInsurance;
import Model.Insurance.HouseInsurance;
import Model.Insurance.Insurance;
import View.Team.ContractTeamTui;
import exception.OverlapNameException;
import exception.WrongInputException;
import exception.WrongRateException;
import exception.fileAcceptException;

public class InsuranceDesign {

	public Insurance insurance;
	public ContractTeamTui contractTeamTui;

	public InsuranceDesign() {
		this.contractTeamTui = new ContractTeamTui();
	}

	private boolean getTempInsurance(Scanner scanner) {

		try {
			File file = new File(".//File//tempInsurance.txt");

			@SuppressWarnings("resource")
			Scanner fileScanner = new Scanner(file);

			if (fileScanner.nextInt() == 1) {
				this.contractTeamTui.showSelectTempInsurance();

				if (scanner.next().equals("1")) {
					double rate[] = new double[3];
					String id = fileScanner.next();
					String name = fileScanner.next();
					String type = fileScanner.next();
					if (type.equals("general")) {
						this.insurance = new GeneralInsurance();
					} else if (type.equals("house")) {
						this.insurance = new HouseInsurance();
					}
					this.insurance.setInsuranceID(id);
					this.insurance.setInsuranceName(name);
					this.insurance.setStandardFee(fileScanner.nextInt());
					fileScanner.nextLine();
					this.insurance.setSpecialContract(fileScanner.nextLine());
					this.insurance.setLongTerm(Boolean.parseBoolean(fileScanner.nextLine()));
					this.insurance.setApplyCondition(fileScanner.nextLine());
					this.insurance.setCompensateCondition(fileScanner.nextLine());
					this.insurance.setExplanation(fileScanner.nextLine());

					rate[0] = fileScanner.nextDouble();
					rate[1] = fileScanner.nextDouble();
					rate[2] = fileScanner.nextDouble();
					this.insurance.setPremiumRate(rate);
					@SuppressWarnings("resource")
					FileWriter fileWriter = new FileWriter(file);
					fileWriter.write("0");
					fileWriter.flush();

					return true;

				}
			} else {
				return false;
			}
		} catch (IOException e) {
			throw new fileAcceptException();
		}
		return false;
	}

	public boolean design() {
		Scanner scanner = new Scanner(System.in);
		if (getTempInsurance(scanner)) {
			this.insurance.design();
			return register(scanner);
		}

		boolean IsLongTerm = false;
		int flag = -1;

		this.contractTeamTui.showStart();
		while (flag == -1) {
			this.contractTeamTui.showSelectLongterm();
			try {
				flag = (getflag(scanner));
			} catch (WrongInputException e) {
				System.err.println(e.getMessage());
			}
		}

		if (flag == 1) {
			IsLongTerm = true;
		} else if (flag == 2) {
			IsLongTerm = false;
		} else if (flag == 0) {
			this.contractTeamTui.showCancel();
			return true;
		}

		flag = -1;
		while (flag == -1) {
			this.contractTeamTui.showSelectInsuranceType();
			try {
				flag = (getflag(scanner));
			} catch (WrongInputException e) {
				System.err.println(e.getMessage());
			}
		}

		if (flag == 1) {
			this.insurance = new GeneralInsurance(IsLongTerm);
		} else if (flag == 2) {
			this.insurance = new HouseInsurance(IsLongTerm);
		} else if (flag == 0) {
			this.contractTeamTui.showCancel();
			return true;
		}

		this.insurance.design();
		this.contractTeamTui.showEnterName();

		boolean overlapName = true;

		while (overlapName) {

			try {

				this.insurance.setInsuranceName(scanner.next());
				overlapName = checkName();

			} catch (OverlapNameException e) {
				System.err.println(e.getMessage());
			}
		}
		this.contractTeamTui.showEnterSpecialContract();
		this.insurance.setSpecialContract(scanner.next());

		this.contractTeamTui.showEnterApplyCondition();
		this.insurance.setApplyCondition(scanner.next());

		this.contractTeamTui.showEnterCompensateCondition();
		this.insurance.setCompensateCondition(scanner.next());

		this.contractTeamTui.showEnterExplanation();
		this.insurance.setExplanation(scanner.next());

		flag = -1;
		while (flag == -1) {
			this.contractTeamTui.showSelectRate(Arrays.toString(this.insurance.getPremiumRate()));

			try {
				flag = (getflag(scanner));
			} catch (WrongInputException e) {
				System.err.println(e.getMessage());
			}
		}
		if (flag == 1) {

			this.insurance.setStandardFee((int) (1000000000 * this.insurance.getPremiumRate()[0] / 100));
		} else if (flag == 2) {

			double rate[] = new double[] { 0, 0, 0 };
			boolean correctRate = false;
			while (!correctRate) {
				this.contractTeamTui.showEnterPremiumRate();
				rate[0] = checkDouble(scanner);
				rate[1] = checkDouble(scanner);
				rate[2] = checkDouble(scanner);
				correctRate = checkRate(rate);

			}
			this.insurance.setPremiumRate(rate);
			this.insurance.setStandardFee((int) (1000000000 * this.insurance.getPremiumRate()[0] / 100));
		} else if (flag == 0) {
			this.contractTeamTui.showCancel();
			return true;
		}

		return register(scanner);

	}

	private boolean checkName() throws OverlapNameException {

		if (this.insurance.checkName() || this.insurance.checkRegisterName()) {
			throw new OverlapNameException();
		}
		return false;
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

	private boolean checkRate(double[] rate) {
		try {
			if (rate[0] > rate[1]) {

				throw new WrongRateException(1, 2);
			} else if (rate[1] > rate[2]) {
				throw new WrongRateException(2, 3);
			}
		} catch (WrongRateException e) {
			System.err.println(e.getMessage());
		}

		return true;
	}

	private double checkDouble(Scanner scanner) {
		while (true) {

			try {
				if (!scanner.hasNextDouble()) {
					throw new WrongInputException();
				}

				double rate = scanner.nextDouble();
				if (rate < 0) {
					throw new WrongInputException();
				}
				return rate;
			} catch (WrongInputException e) {
				System.err.println(e.getMessage());
			}
		}

	}

	private boolean register(Scanner scanner) {

		this.contractTeamTui.showInsurance(this.insurance);

		boolean correctInput = false;
		String select;
		while (!correctInput) {
			try {

				this.contractTeamTui.showSelctRegister();
				select = scanner.next();
				if (select.equals("1") || select.equals("등록")) {
					if (this.insurance.registerRate())
						this.contractTeamTui.showSuccessRegister();

					return false;
				} else if (select.equals("2") || select.equals("취소")) {

					this.insurance.saveTempInsurance();
					return true;
				} else {
					throw new WrongInputException();
				}
			} catch (WrongInputException e) {
				System.err.println(e.getMessage());
			}
		}

		return true;
	}

}
