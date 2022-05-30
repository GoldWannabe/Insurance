package ContractTeam;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
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
					this.insurance.setStandardRate(rate);
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
			System.out.println(
					"파일 접근 중 문제가 생겨 보험 정보를 불러오지 못했습니다. 잠시후 다시 실행 해주십시오. 해당 문제가 계속 발생할 시에는 사내 시스템 관리팀(1234-5678)에게 문의 주시기 바랍니다.");
			e.printStackTrace();
		}
		return false;

	}

	private int getflag(Scanner scanner) {
		String flag = scanner.next();
		while (true) {
			
			if (flag.equals("1")) {
				return 1;
			} else if (flag.equals("2")) {
				return 2;
			} else if (flag.equals("0")) {
				System.out.println("취소되었습니다. 선택창으로 돌아갑니다.");
				return 0;
			} else {
				System.out.println("해당하는 번호가 없습니다. 다시 입력해주세요.");
				flag = scanner.next();
			}
		}
	}

	public boolean design() {
		boolean IsLongTerm = false;
		Scanner scanner = new Scanner(System.in);
		int flag = -1;
		if (getTempInsurance(scanner)) {
			return register(scanner);
		}

		System.out.println("장기여부와 보험 종류를 선택해 주세요.");

		System.out.println("장기여부를 선택해주세요.");
		System.out.println("1. 장기 2. 단기 0. 취소");

		flag = getflag(scanner);
		if (flag == 1) {
			IsLongTerm = true;
		} else if (flag == 2) {
			IsLongTerm = false;
		} else if (flag == 0) {
			System.out.println("취소되었습니다. 선택창으로 돌아갑니다.");
			return true;
		}

		System.out.println("보험 종료를 선택해주세요.");
		System.out.println("1. 일반보험 2. 주택보험 0. 취소");
		flag = getflag(scanner);

		if (flag == 1) {
			this.insurance = new GeneralInsurance(IsLongTerm);
		} else if (flag == 2) {
			this.insurance = new HouseInsurance(IsLongTerm);
		} else if (flag == 0) {
			System.out.println("취소되었습니다. 선택창으로 돌아갑니다.");
			return true;
		}

		this.insurance.design();
		
		System.out.println("이름을 입력해주세요.");
		this.insurance.setInsuranceName(scanner.next());
		while (this.insurance.checkName()) {
			System.out.println("중복되는 이름이 존재합니다. 다시 입력해주세요."); 
			this.insurance.setInsuranceName(scanner.next());
		}

		System.out.println("특약을 입력해주세요.");
		this.insurance.setSpecialContract(scanner.next());
		System.out.println("가입조건을 입력해주세요.");
		this.insurance.setApplyCondition(scanner.next());
		System.out.println("보상 조건을 입력해주세요.");
		this.insurance.setCompensateCondition(scanner.next());
		System.out.println("설명을 입력해주세요.");
		this.insurance.setExplanation(scanner.next());

		System.out.println("기준 요율을 사용하시겠습니까? 기존요율: [1등급, 2등급, 3등급]" + Arrays.toString(this.insurance.getStandardRate()));
		System.out.println("1. 예 2. 아니오 0. 취소");
		flag = getflag(scanner);

		if (flag == 1) {
			this.insurance.measureStandardFee();
		} else if (flag == 2) {

			double rate[] = new double[] { 0, 0, 0 };

			while (checkRate(rate)) {
				System.out.println("등급 별 요율을 입력해주세요.");
				System.out.println("1급");
				rate[0] = checkDouble(scanner);
				System.out.println("2급");
				rate[1] = checkDouble(scanner);
				System.out.println("3급");
				rate[2] = checkDouble(scanner);
			}
			this.insurance.setStandardRate(rate);
			this.insurance.measureStandardFee();
		} else if (flag == 0) {
			System.out.println("취소되었습니다. 선택창으로 돌아갑니다.");
			return true;
		}

		return register(scanner);

	}

	private boolean checkRate(double[] rate) {
		if (rate[0] <= 0) {
			System.out.println("양수로 입력해주세요.");
			return true;
		} else if (rate[0] > rate[1]) {
			System.out.println("1급의 요율이 2급보다 높습니다. 다시 적어주세요.");
		} else if (rate[1] > rate[2]) {
			System.out.println("2급의 요율이 3급보다 높습니다. 다시 적어주세요.");
			return true;
		}
		return false;
	}

	private double checkDouble(Scanner scanner) {
		@SuppressWarnings("unused")
		String trash;
		while (!scanner.hasNextDouble()) {
			trash = scanner.next();
			System.out.println("소수를 넣어주세요.");
		}
		return scanner.nextDouble();
	}

	private boolean register(Scanner scanner) {

		System.out.println("보험명: " + this.insurance.getInsuranceName() + "\n" + "보험종류: "
				+ this.insurance.getInsuranceType() + "\n" + "기준보험료: " + this.insurance.getStandardFee() + "\n"+ "특약: "
				+ this.insurance.getSpecialContract()+ "\n" + "장기여부: " + this.insurance.isLongTerm() + "\n" + "가입조건: "
				+ this.insurance.getApplyCondition() + "\n" + "보상조건: " + this.insurance.getCompensateCondition() + "\n"
				+ "설명: " + this.insurance.getExplanation()+"\n"+"요율: [1등급, 2등급, 3등급]" + Arrays.toString(this.insurance.getStandardRate()));

		boolean correctInput = false;
		String select;
		while (!correctInput) {
			System.out.println("1. 등록 2. 취소");
			select = scanner.next();
			if (select.equals("1") || select.equals("등록")) {
				this.insurance.registerRate();
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
