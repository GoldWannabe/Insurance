package Control.FinancialDirector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Model.Insurance.GeneralInsurance;
import Model.Insurance.HouseInsurance;
import Model.Insurance.Insurance;
import Model.Insurance.InsuranceList;
import Model.Insurance.InsuranceListImpl;
import View.Team.FinancialDirectorTui;
import exception.WrongInputException;

public class InsuranceJudge {

	FinancialDirectorTui financialDirectorTui;
	Insurance insurance;
	InsuranceList insuranceList;

	public InsuranceJudge() {
		this.financialDirectorTui = new FinancialDirectorTui();
	}

	@SuppressWarnings("resource")
	public boolean selectJudge() {
		Scanner scanner = new Scanner(System.in);
		this.insuranceList = new InsuranceListImpl();
		this.insurance = new GeneralInsurance();

		ResultSet resultSet = this.insurance.getRegisterInsurance();
		try {
			while (resultSet.next()) {
				Insurance registerInsurance = null;
				if (resultSet.getString("insuranceType").equals("general")) {
					registerInsurance = new GeneralInsurance();
				} else if (resultSet.getString("insuranceType").equals("house")) {
					registerInsurance = new HouseInsurance();
				}
				registerInsurance.setInsuranceID(resultSet.getString("insuranceID"));
				registerInsurance.setInsuranceName(resultSet.getString("insuranceName"));
				registerInsurance.setStandardFee(resultSet.getInt("StandradFee"));
				registerInsurance.setSpecialContract(resultSet.getString("specialContract"));
				registerInsurance.setLongTerm(resultSet.getBoolean("longTerm"));
				registerInsurance.setApplyCondition(resultSet.getString("applyCondition"));
				registerInsurance.setCompensateCondition(resultSet.getString("compensateCondition"));
				registerInsurance.setExplanation(resultSet.getString("explanation"));
				this.insuranceList.add(registerInsurance);
			}

			if (this.insuranceList.getAll().isEmpty()) {
				this.financialDirectorTui.showNoRegisterInsurance();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return selectInsurance(scanner);
	}

	private void showInsurance() {
		for (Insurance insurance : this.insuranceList.getAll()) {
			this.financialDirectorTui.showInsurance(insurance);
		}
	}

	private boolean selectInsurance(Scanner scanner) {
		this.financialDirectorTui.showInsuranceColumn();
		showInsurance();
		this.financialDirectorTui.showEnterInsuranceName();

		this.insurance = this.insuranceList.get(scanner.next());

		while (this.insurance == null) {
			this.financialDirectorTui.ShowNoInsuranceName();
			this.insurance = this.insuranceList.get(scanner.next());
		}

		this.insurance.setRate();

		this.financialDirectorTui.showInsuranceAttribute(this.insurance);

		boolean correctInput = false;
		String select;
		this.financialDirectorTui.showSelectPermit();

		while (!correctInput) {

			try {
				select = scanner.next();
				if (select.equals("1") || select.equals("승인")) {
					
					return permitInsurance();
					
				} else if (select.equals("2") || select.equals("비승인")) {
					return notPermitInsurance();
					
				} else if (select.equals("3") || select.equals("보류")) {
					this.financialDirectorTui.showPostponePermit(this.insurance.getInsuranceName());
					
					
					return true;
				} else {
					throw new WrongInputException();
				}
			} catch (WrongInputException e) {
				System.err.println(e.getMessage());
			}
		}
		return false;
	}

	private boolean notPermitInsurance() {
if(this.insurance.notPermitRate()) {
	this.financialDirectorTui.showCompletePermit();
	return true;
}
		return false;

	}

	private boolean permitInsurance() {
		if(this.insurance.permitRate()) {
			this.financialDirectorTui.showNoCompletePermit();
			return true;
		} 
		return false; 
		
	}

}
