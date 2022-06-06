package View.Team;

import java.util.ArrayList;
import java.util.Arrays;

import Model.Channel.Channel;
import Model.Channel.ChannelListImpl;
import Model.Contract.Contract;
import Model.Contract.ContractList;
import Model.Customer.Customer;
import Model.Customer.CustomerList;
//github.com/GoldWannabe/Insurance.git
import Model.Insurance.Insurance;
import Model.Insurance.InsuranceList;

public class SalesTeamTui {

	public void showInsuranceType() {
		System.out.println("가입하실 보험을 선택해주십시오.");
		System.out.println("1. 일반보험 2. 주택보험 0. 취소");
	}

	public void showCancel() {
		System.out.println("취소되었습니다. 선택창으로 돌아갑니다.");
	}

	public void showInsurance(InsuranceList insuranceList) {
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.printf("%10s %10s", "보험명", "보험종류");
		System.out.println();
		System.out.println("---------------------------------------------------------------------------------------");
		for (Insurance insurance : insuranceList.getAll()) {
			System.out.format("%10s %12s", insurance.getInsuranceName(), insurance.getInsuranceType().toString());
			System.out.println();
		}
	}

	public void showEnterInsuranceName() {
		System.out.println("보험 이름을 입력하여 주십시오.");
		System.out.print("보험 이름: ");
	}

	public void showSearchFail() {
		System.out.println("해당하는 정보가 존재하지 않습니다. 다시 입력해주세요.");
	}

	public void showInsuranceDetail(Insurance insurance) {
		System.out.println("보험명: " + insurance.getInsuranceName() + "\n" + "보험종류: " + insurance.getInsuranceType()
				+ "\n" + "기준보험료: " + insurance.getStandardFee() + "\n" + "특약: " + insurance.getSpecialContract() + "\n"
				+ "장기여부: " + insurance.isLongTerm() + "\n" + "가입조건: " + insurance.getApplyCondition() + "\n" + "보상조건: "
				+ insurance.getCompensateCondition() + "\n" + "설명: " + insurance.getExplanation() + "\n"
				+ "요율: [1등급, 2등급, 3등급]" + Arrays.toString(insurance.getPremiumRate()));
	}

	public void showSelectInsurance(String insuranceName) {
		System.out.println(insuranceName + " 보험에 가입하시겠습니까?");
		System.out.println("1. 예 2. 재가입 신청 0. 취소");
	}

	public void showSelectCustomer() {
		System.out.println("가입자 선택");
		System.out.println("1. 신규 고객 2. 기존 고객 0. 취소");
	}

	public void showEnterSecurityFee() {
		System.out.print("담보액: ");
	}

	public void showEnterInsuranceFee() {
		System.out.print("보험료: ");
	}

	public void showEnterPaymentCycle() {
		System.out.print("납부 방식(paymentCycle): ");
	}
	
	public void showEnterPaymentCycle(int paymentCycle) {
		System.out.println("기존 납부 방법: "+paymentCycle);
		System.out.print("신규 납부 방법: ");
	}

	public void showEnterPeriod() {
		System.out.print("가입 기간: ");
	}

	public void showEnterFireFacilities() {
		System.out.print("소화시설(Float): ");
	}

	public void showEnterScale() {
		System.out.print("스케일(Int): ");
	}

	public void showEnterSurroundingFacilities() {
		System.out.print("주변 시설(Float): ");
	}

	public void showEnterHeight() {
		System.out.println("높이가 15층 이상입니까? [true or false]");
		System.out.print("높이(boolean): ");
	}

	public void showEnterMaterial() {
		System.out.print("건물 재질(rock, wood etc.): ");
		System.out.println("1.wood 2.rock 3.concrete 4.iron 5.brick");
	}

	public void showEnterPurpose() {
		System.out.print("건물 목적(living, factory etc.): ");
		System.out.println("1.living 2.factory 3.culturalAsset 4.store 5.office 6.carPark 7.warehouse");
	}

	public void showSearchCustomerStart() {
		System.out.println("-----회원 조회-----");
	}

	public void showEnterCustomerName() {
		System.out.print("고객 이름: ");
	}

	public void showEnterCustomerPhoneNum() {
		System.out.print("전화번호: ");
	}

	public void showExistBlankInput() {
		System.out.println("이름과 전화번호 모두 입력하여 주십시오.");
	}

	public void showSearchCustomerSuccess(String customerName) {
		System.out.println("환영합니다, " + customerName + "님. 보험 가입을 진행합니다.");
		System.out.println("-----고객 조회 완료-----");
	}

	public void showNewCustomerStart() {
		System.out.println("신규 고객으로 회원가입을 진행합니다.");
	}

	public void showEnterSSN() {
		System.out.print("주민/사업자 번호: ");
	}

	public void shoeEnterAddress() {
		System.out.print("주소: ");
	}

	public void showEnterSex() {
		System.out.println("성별: [male, female, none]");
		System.out.print("선택: ");
	}

	public void showEnterBankName() {
		System.out.print("은행 이름: ");
	}

	public void showEnterAccountNum() {
		System.out.print("계좌번호: ");
	}

	public void showNewCustomerEnd() {
		System.out.println("신규 회원가입이 완료되었습니다.");
	}

	public void showReInput() {
		System.out.println("재입력: ");
	}
	
	public void showFailContract(Contract contract) {
		System.out.println("-------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%10s %10s %10s %10s %10s %10s %10s %10s", "가입자 이름", "연락처", "보험 이름", "납부 방식", "보험료", "담보액", "가입기간", "탈락 이유");
		System.out.println();
		System.out.println("-------------------------------------------------------------------------------------------------------------------");
			System.out.format("%10s %15s %8s %12s %10s %15s %8s %10s",
					contract.getCustomerName(), contract.getPhoneNum(), contract.getInsuranceName(), contract.getPaymentCycle(),
					contract.getInsuranceFee(), contract.getSecurityFee(), contract.getPeriod(), contract.getReason());
			System.out.println();
	}
	
	
	public void showEditStart() {
		System.out.println("수정할 항목을 선택하여 주십시오.");
		System.out.println("1. 납부방법 2. 보험료 3. 담보액 4. 주기 0. 수정 완료");
		System.out.print("선택: ");
	}

	public void showEditSuccess() {
		System.out.println("수정 완료");
	}
	

	public void showNochanges() {
		System.out.println("수정사항이 존재하지 않습니다. 수정사항을 입력하여 주십시오.");
		
	}
	
	public void showReRegisterSuccess() {
		System.out.println("재신청 완료");
	}

	public void viewSelectChannel() {
		System.out.println("판매 채널 관리 메뉴를 선택해주세요.");
		System.out.println("1. 검색 2. 추가 3. 수정 4. 삭제 0. 취소");

	}

	public void viewDeleteNum() {

		System.out.println("삭제할 채널번호(ID)을 입력해주세요.");

	}

	public void viewDelete(String channelID) {
		System.out.println("채널ID: " + channelID + "를 정말 삭제 하시겠습니까? 삭제를 원하면 1을 입력해주시고 아니면 아무키나 눌러주세요.");

	}

	public void viewCancelMenu() {
		System.out.println("삭제가 취소되었습니다. 판메채널 관리 메뉴로 돌아갑니다.");
	}

	public void viewEditID() {
		System.out.println("수정할 채널번호(ID)을 입력해주세요.");
	}


	public void viewChannel(ChannelListImpl channelList) {
		if (!(channelList == null)) {
			System.out
					.println("---------------------------------------------------------------------------------------");
			System.out.printf("%10s %10s %15s %20s %20s %20s", "채널ID", "채널명", "등록일", "가입자수", "월지출", "총지출");
			System.out.println();
			System.out
					.println("---------------------------------------------------------------------------------------");
			for (Channel channel : channelList.getAll()) {
				System.out.format("%5s %10s %20s %10d %15d %15d", channel.getChannelID(), channel.getChannelName(),
						channel.getRegisterDate().toString(), channel.getNumOfRegister(), channel.getMonthlyExpense(),
						channel.getSumOfExpense());
				System.out.println();
			}
		}

	}

	public void viewChannelID() {
		System.out.println("채널 번호(ID)를 입력해주세요.");

	}
	public void viewInputName() {
		System.out.println("채널이름을 입력해주세요.");

	}

	public void viewOverlapID() {
		System.out.println("채널번호가 중복됩니다. 다른 번호로 지정해 주시기 바랍니다.");

	}

	public void viewDate() {
		System.out.println("등록날짜을 입력해주세요.");
	}

	public void viewInputYear() {

		System.out.println("등록 연도를 입력해주세요");
	}

	public void viewInputMonth() {
		System.out.println("등록월를 입력해주세요");

	}

	public void viewInputDay() {
		System.out.println("등록일을 입력해주세요");
	}

	public void viewInputNumOf() {
		System.out.println("가입자 수를 입력해주세요.");
	}

	public void viewInputMonthlyExpense() {
		System.out.println("월지출을 입력해주세요.");
	}

	public void viewInputSumOf() {
		System.out.println("총 지출을 입력해주세요.");
	}

	public void viewOverExpense() {
		System.out.println("월지출이 총지출보다 많습니다. 총지출을 다시 입력해주세요.");
	}

	public void viewNonExistDate() {
		System.out.println("해당하는 날짜는 존재하지 않습니다. 다시 입력해주세요.");
	}

	public void viewOnlyNumber() {
		System.out.println("숫자를 입력해 주세요!");
	}

	public void viewAddChannel() {
		System.out.println("판매채널이 추가되었습니다");
	}

	public void viewDelteChannel() {
		System.out.println("채널삭제가 완료되었습니다.");
	}

	public void viewUpdateChannel(Channel channel) {
		System.out.println("1. 채널ID: " + channel.getChannelID() + "\n" + "2. 채널명: " + channel.getChannelName() + "\n"
				+ "3. 등록일: " + channel.getRegisterDate() + "\n" + "4. 가입자수: " + channel.getNumOfRegister() + "\n"
				+ "5. 월지출: " + channel.getMonthlyExpense() + "\n" + "6. 총지출: " + channel.getSumOfExpense() + "\n"
				+ "그 이외의 번호. 취소");

		System.out.println("수정할 항목의 번호를 입력해주세요.");
	}

	public void viewUpdateCompleted() {
		System.out.println("수정이 완료되었습니다!");
	}

	public void viewSearchChannel(ArrayList<Channel> tempChannelList) {
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.printf("%10s %10s %15s %20s %20s %20s", "채널ID", "채널명", "등록일", "가입자수", "월지출", "총지출");
		System.out.println();
		System.out.println("---------------------------------------------------------------------------------------");
		for (Channel channel : tempChannelList) {
			System.out.format("%10s %10s %15s %10d %15d %15d", channel.getChannelID(), channel.getChannelName(),
					channel.getRegisterDate().toString(), channel.getNumOfRegister(), channel.getMonthlyExpense(),
					channel.getSumOfExpense());
			System.out.println();
		}
	}

	public void viewNonSearch() {//이건 에러중 하나인데... false로 줘서 다시 입력하게 만들었길래 그냥 여기로 뺌. 다른거는 에러로 줌
		System.out.println("검색된 채널이 없습니다. 정확하게 입력하여 주시기 바랍니다");
		System.out.print("재입력: ");

	}
	
	public void viewCustomerInfo(Customer customer) {
		System.out.println("--------------------------------------------------------------------------------------------");
		System.out.printf("%10s %13s %10s %10s %10s %10s %10s %10s", "가입자명", "주민/사업자번호", "연락처", "주소", "성별", "가입 보험 개수", "은행명", "계좌번호");
		System.out.println("--------------------------------------------------------------------------------------------");
		System.out.format("%10s %13s %15s %10s %10s %10s %10s %10s",customer.getName(), customer.getSSN(), customer.getPhoneNum(), customer.getAddress(), customer.getSex(), customer.getInsuranceNum(), customer.getBankName(), customer.getAccountNum());
	}
	public void viewButton() {
		System.out.println("1. 고객 정보 수정  2. 고객 정보 추가 3. 고객 정보 삭제" );
	}

	public void viewSelectEdit() {
		// TODO Auto-generated method stub
		
	}
	public void showRegisterSuccess() {
		System.out.println("보험 가입이 완료되었습니다.");
	}
}
