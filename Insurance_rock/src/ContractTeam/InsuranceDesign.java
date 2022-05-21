package ContractTeam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import Insurance.GeneralInsurance;
import Insurance.HouseInsurance;
import Insurance.Insurance;

public class InsuranceDesign {

	public Insurance insurance;

	public InsuranceDesign() {

	}

	private boolean getTempInsurance(Scanner scanner) {
		try {
			File file = new File(".//DB//tempInsurance.txt");

			@SuppressWarnings("resource")
			Scanner fileScanner = new Scanner(file);

			if (fileScanner.nextInt() == 1) {
				System.out.println("이전에 작업하던 설계가 있습니다. 이어서 하시길 원하면 1을 입력해주세요.");
				if (scanner.next().equals("1")) {

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
					this.insurance.setSpecialContract(fileScanner.nextLine());
					this.insurance.setLongTerm(Boolean.parseBoolean(fileScanner.nextLine()));
					this.insurance.setApplyCondition(fileScanner.nextLine());
					this.insurance.setCompensateCondition(fileScanner.nextLine());
					this.insurance.setExplanation(fileScanner.nextLine());

					FileWriter fileWriter = new FileWriter(file);
					fileWriter.write("0");
					fileWriter.flush();
					
					return true;

				}
			} else {
				return false;
			}
		} catch (IOException e) {
			System.out.println(
					"파일 접근 중 문제가 생겨 보험 정보를 불러오지 못했습니다. 잠시후 다시 실행 해주십시오. 해당 문제가 계속 발생할 시에는 사내 시스템 관리팀(1234-5678)에게 문의 주시기 바랍니다.");
			e.printStackTrace();
		}
		return false;

	}

	public boolean design() {
		String type;
		String longterm;
		boolean IsLongTerm = false;
		Scanner scanner = new Scanner(System.in);

		if(getTempInsurance(scanner)) {
			return register();
		}

		System.out.println("장기여부와 보험 종류를 선택해 주세요.");
		boolean correctInput = false;

		while (!correctInput) {
			System.out.println("장기여부를 입력해주세요.");
			System.out.println("1. 장기 2. 단기 0. 취소");
			longterm = scanner.next();

			if (longterm.equals("1") || longterm.equals("장기")) {
				IsLongTerm = true;
				correctInput = true;
			} else if (longterm.equals("2") || longterm.equals("단기")) {
				correctInput = true;
			} else if (longterm.equals("0") || longterm.equals("취소")) {
				System.out.println("취소되었습니다. 선택창으로 돌아갑니다.");
				return true;
			} else {
				System.out.println("형식에 맞지 않습니다. 다시 입력해주세요.");
			}
		}

		correctInput = false;
		while (!correctInput) {
			System.out.println("보험 종료를 입력해주세요.");
			System.out.println("1. 일반보험 2. 주택보험 0. 취소");
			type = scanner.next();
			if (type.equals("1") || type.equals("일반보험")) {
				this.insurance = new GeneralInsurance(IsLongTerm);
				correctInput = true;
			} else if (type.equals("2") || type.equals("일반보험")) {
				this.insurance = new HouseInsurance(IsLongTerm);
				correctInput = true;
			} else if (type.equals("0") || type.equals("취소")) {
				System.out.println("취소되었습니다. 선택창으로 돌아갑니다.");
				return true;
			} else {
				System.out.println("형식에 맞지 않습니다. 다시 입력해주세요.");
			}
		}

		this.insurance.design();
		this.insurance.measureStandardFee();
		return register();

	}

	private boolean register() {

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		System.out.println("보험명: " + this.insurance.getInsuranceName() + "\n" + "보험종류: "
				+ this.insurance.getInsuranceType() + "\n" + "기준보험료: " + this.insurance.getStandardFee() + "특약: "
				+ this.insurance.getSpecialContract() + "장기여부: " + this.insurance.isLongTerm() + "\n" + "가입조건: "
				+ this.insurance.getApplyCondition() + "\n" + "보상조건: " + this.insurance.getCompensateCondition() + "\n"
				+ "설명: " + this.insurance.getExplanation());

		boolean correctInput = false;
		String select;
		while (!correctInput) {
			System.out.println("1. 등록 2. 취소");
			select = scanner.next();
			if (select.equals("1") || select.equals("등록")) {
				this.insurance.register();
				System.out.println("보험이 심사 등록 되었습니다.");
				return false;
			} else if (select.equals("2") || select.equals("취소")) {
				this.insurance.saveTempInsurance();
				return true;
			} else {
				System.out.println("형식에 맞지 않습니다. 다시 입력해주세요.");
			}
		}

		return true;
	}

}
