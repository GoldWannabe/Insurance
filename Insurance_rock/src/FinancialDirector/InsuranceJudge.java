package FinancialDirector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

import Insurance.GeneralInsurance;
import Insurance.HouseInsurance;
import Insurance.Insurance;
import Insurance.InsuranceList;
import Insurance.InsuranceListImpl;

public class InsuranceJudge {

	Insurance insurance;
	InsuranceList insuranceList;

	@SuppressWarnings("resource")
	public boolean selectJudge() {
		Scanner scanner = new Scanner(System.in);
		this.insuranceList = new InsuranceListImpl();

		System.out.println("보험 종류를 선택해주세요.");
		System.out.println("1. 일반보험 2. 주택보험 0. 취소");
		String flag = scanner.next();
		boolean correctInput = false;
		while (!correctInput) {
			if (flag.equals("1")) {
				this.insurance = new GeneralInsurance();
				correctInput = true;
			} else if (flag.equals("2")) {
				this.insurance = new HouseInsurance();
				correctInput = true;
			} else if (flag.equals("0")) {
				System.out.println("취소되었습니다. 선택창으로 돌아갑니다.");
				return true;
			} else {
				System.out.println("해당하는 번호가 없습니다. 다시 입력해주세요.");
			}
		}
		ResultSet resultSet = this.insurance.getRegisterInsurance();
		try {
			while (resultSet.next()) {
				Insurance registerInsurance = null;
				if (flag.equals("1")) {
					registerInsurance = new GeneralInsurance();
				} else if (flag.equals("2")) {
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
			
			if(this.insuranceList.getAll().isEmpty()) {
				System.out.println("해당하는 종류의 심사할 보험이 없습니다.");
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		showInsurance();
		return selectInsurance(scanner);
	}

	private void showInsurance() {
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.printf("%10s %10s %10s", "보험명", "보험종류", "기준보험료");
		System.out.println();
		System.out.println("---------------------------------------------------------------------------------------");
		for (Insurance insurance : this.insuranceList.getAll()) {
			System.out.format("%10s %12s %12d", insurance.getInsuranceName(), insurance.getInsuranceType().toString(),
					insurance.getStandardFee());
			System.out.println();
		}
	}

	private boolean selectInsurance(Scanner scanner) {
		System.out.println("심사할 보험명을 입력해 주세요.");
		this.insurance = this.insuranceList.get(scanner.next());

		while (this.insurance == null) {
			System.out.println("해당하는 이름의 보험이 존재하지 않습니다. 다시 입력해주세요.");
			this.insurance = this.insuranceList.get(scanner.next());
		}

		this.insurance.setRate();

		System.out.println("보험명: " + this.insurance.getInsuranceName() + "\n" + "보험종류: "
				+ this.insurance.getInsuranceType() + "\n" + "기준보험료: " + this.insurance.getStandardFee() + "\n" + "특약: "
				+ this.insurance.getSpecialContract() + "\n" + "장기여부: " + this.insurance.isLongTerm() + "\n" + "가입조건: "
				+ this.insurance.getApplyCondition() + "\n" + "보상조건: " + this.insurance.getCompensateCondition() + "\n"
				+ "설명: " + this.insurance.getExplanation() + "\n" + "요율: [1등급, 2등급, 3등급]"
				+ Arrays.toString(this.insurance.getStandardRate()));

		boolean correctInput = false;
		String select;
		System.out.println("심사 결과를 입력해주세요.");
		System.out.println("1. 승인 2. 비승인 3. 보류");

		while (!correctInput) {
			select = scanner.next();

			if (select.equals("1") || select.equals("승인")) {
				return permitInsurance();
			} else if (select.equals("2") || select.equals("비승인")) {
				return notPermitInsurance();
			} else if (select.equals("3") || select.equals("보류")) {
				System.out.println(this.insurance.getInsuranceName() + "의 보험 심사가 보류되었습니다.");
				return true;
			} else {

			}
		}
		return false;
	}

	private boolean notPermitInsurance() {

		return this.insurance.notPermitRate();

	}

	private boolean permitInsurance() {
		return this.insurance.permitRate();

	}

}
