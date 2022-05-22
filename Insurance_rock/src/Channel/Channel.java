package Channel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Channel {

	private String channelID;
	private String channelName;
	private LocalDate registerDate;
	private int numOfRegister;
	private int monthlyExpense;
	private int sumOfExpense;
	private ChannelList channelList;

	public Channel() {

	}

	public void showChannel() {
		if (!(this.channelList == null)) {
			System.out
					.println("---------------------------------------------------------------------------------------");
			System.out.printf("%10s %10s %10s %10s %15s %15s", "채널ID", "채널명", "등록일", "가입자수", "월지출", "총지출");
			System.out.println();
			System.out
					.println("---------------------------------------------------------------------------------------");
			for (Channel channel : this.channelList.getAll()) {
				System.out.format("%10s %10s %15s %10d %15d %15d", channel.getChannelID(), channel.getChannelName(),
						channel.getRegisterDate().toString(), channel.getNumOfRegister(), channel.getMonthlyExpense(),
						channel.getSumOfExpense());
				System.out.println();
			}
		}

	}

	public void setChnnel() {
		this.channelList = new ChannelListImpl();
		try {
			File file = new File(".//DB//Channel.txt");

			@SuppressWarnings("resource")
			Scanner fileScanner = new Scanner(file);

			while (fileScanner.hasNextLine()) {
				Channel tempChannel = new Channel();
				tempChannel.setChannelID(fileScanner.next());
				tempChannel.setChannelName(fileScanner.next());
				tempChannel.setRegisterDate(LocalDate.parse(fileScanner.next()));
				tempChannel.setNumOfRegister(fileScanner.nextInt());
				tempChannel.setMonthlyExpense(fileScanner.nextInt());
				tempChannel.setSumOfExpense(fileScanner.nextInt());
				fileScanner.nextLine();
				this.channelList.add(tempChannel);
			}

		} catch (IOException e) {
			System.out.println(
					"파일 접근 중 문제가 생겨 보험 정보를 불러오지 못했습니다. 잠시후 다시 실행 해주십시오. 해당 문제가 계속 발생할 시에는 사내 시스템 관리팀(1234-5678)에게 문의 주시기 바랍니다.");
			e.printStackTrace();
		}
	}

	public void addChannel() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("채널 번호(ID)를 입력해주세요.");
		this.channelID = scanner.next();
		while (searchID(this.channelID) != null) {
			System.out.println("채널번호가 중복됩니다. 다른 번호로 지정해 주시기 바랍니다.");
			this.channelID = scanner.next();
		}

		System.out.println("이름을 입력해주세요.");
		this.channelName = scanner.next();
		int year = 2000;
		int month = 1;
		int day = 1;
		System.out.println("등록날짜을 입력해주세요.");

		boolean correctDate = false;
		while (!correctDate) {
			System.out.println("등록 연도를 입력해주세요");
			year = checkInt(scanner);
			System.out.println("등록월를 입력해주세요");
			month = checkInt(scanner);
			System.out.println("등록일을 입력해주세요");
			day = checkInt(scanner);
			correctDate = checkDate(year + "-" + month + "-" + day);
		}
		this.registerDate = LocalDate.of(year, month, day);

		System.out.println("가입자 수를 입력해주세요.");
		this.numOfRegister = checkInt(scanner);
		System.out.println("월지출을 입력해주세요.");
		this.monthlyExpense = checkInt(scanner);
		System.out.println("총 지출을 입력해주세요.");
		this.sumOfExpense = checkInt(scanner);
		while (this.monthlyExpense > this.sumOfExpense) {
			System.out.println("월지출이 총지출보다 많습니다. 총지출을 다시 입력해주세요.");
			this.sumOfExpense = checkInt(scanner);
		}

	}

	public Channel searchID(String ID) {

		return this.channelList.get(ID);

	}

	private boolean checkDate(String date) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			dateFormat.setLenient(false);
			dateFormat.parse(date);

			return true;
		} catch (ParseException e) {
			System.out.println("해당하는 날짜는 존재하지 않습니다. 다시 입력해주세요.");
			return false;
		}
	}

	private int checkInt(Scanner scanner) {
		while (!scanner.hasNextInt()) {
			scanner.next();
			System.out.println("숫자를 입력해 주세요!");
		}
		return scanner.nextInt();
	}

	public void saveChannel() {
		try {
			File file = new File(".//DB//Channel.txt");
			FileWriter fileWriter = new FileWriter(file, true);
			fileWriter.write(this.channelID + " " + this.channelName + " " + this.registerDate + " "
					+ this.numOfRegister + " " + this.monthlyExpense + " " + this.sumOfExpense + "\n");
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			System.out.println("DB 접근 오류: 정보 접근에 실패하였습니다. 해당 문제가 계속 발생할 시에는 사내 시스템 관리팀(1234-5678)에게 문의 주시기 바랍니다.");
			e.printStackTrace();
		}
	}

	public void deleteChannel(String ID) {
		this.channelList.delete(ID);
		try {
			File file = new File(".//DB//Channel.txt");
			FileWriter fileWriter = new FileWriter(file);
			for (Channel channel : this.channelList.getAll()) {
				fileWriter.write(channel.getChannelID() + " " + channel.getChannelName() + " "
						+ channel.getRegisterDate() + " " + channel.getNumOfRegister() + " "
						+ channel.getMonthlyExpense() + " " + channel.getSumOfExpense() + "\n");
				fileWriter.flush();

			}
			fileWriter.close();
		} catch (IOException e) {
			System.out.println("DB 접근 오류: 정보 접근에 실패하였습니다. 해당 문제가 계속 발생할 시에는 사내 시스템 관리팀(1234-5678)에게 문의 주시기 바랍니다.");
			e.printStackTrace();
		}
	}

	public void editChannel(Channel tempChannel) {
		Scanner scanner = new Scanner(System.in);
		this.channelID = tempChannel.getChannelID();
		this.channelName = tempChannel.getChannelName();
		this.registerDate = tempChannel.getRegisterDate();
		this.numOfRegister = tempChannel.getNumOfRegister();
		this.monthlyExpense = tempChannel.getMonthlyExpense();
		this.sumOfExpense = tempChannel.getSumOfExpense();
		System.out.println("1. 채널ID: " + this.channelID + "\n" + "2. 채널명: " + this.channelName + "\n" + "3. 등록일: "
				+ this.registerDate + "\n" + "4. 가입자수: " + this.numOfRegister + "\n" + "5. 월지출: " + this.monthlyExpense
				+ "\n" + "6. 총지출: " + this.sumOfExpense + "\n" + "그 이외의 번호. 취소");

		System.out.println("수정할 항목의 번호를 입력해주세요.");
		while (!scanner.hasNextInt()) {
			scanner.next();
			System.out.println("번호를 입력해주세요.");
		}
		switch (scanner.nextInt()) {
		case 1:
			System.out.println("채널 번호(ID)를 입력해주세요.");
			String tempID = scanner.next();
			while ((searchID(tempID) != null) || this.channelID.equals(tempID)) {
				System.out.println("채널번호가 중복됩니다. 다른 번호로 지정해 주시기 바랍니다.");
				tempID = scanner.next();
			}
			tempChannel.setChannelID(tempID);
			break;
		case 2:
			System.out.println("이름을 입력해주세요.");
			tempChannel.setChannelName(scanner.next());
			
			break;
		case 3:
			int year = 2000;
			int month = 1;
			int day = 1;
			System.out.println("등록날짜을 입력해주세요.");

			boolean correctDate = false;
			while (!correctDate) {
				System.out.println("등록 연도를 입력해주세요");
				year = checkInt(scanner);
				System.out.println("등록월를 입력해주세요");
				month = checkInt(scanner);
				System.out.println("등록일을 입력해주세요");
				day = checkInt(scanner);
				correctDate = checkDate(year + "-" + month + "-" + day);
			}
			
			tempChannel.setRegisterDate(LocalDate.of(year, month, day));
			break;
		case 4:
			System.out.println("가입자 수를 입력해주세요.");
			tempChannel.setNumOfRegister(checkInt(scanner));
			break;
		case 5:
			System.out.println("월지출을 입력해주세요.");
			tempChannel.setMonthlyExpense(checkInt(scanner));
			break;
		case 6:
			System.out.println("총 지출을 입력해주세요.");
			int sumOfTempExpense = checkInt(scanner);
			while (this.monthlyExpense > sumOfTempExpense) {
				System.out.println("월지출이 총지출보다 많습니다. 총지출을 다시 입력해주세요.");
				sumOfTempExpense = checkInt(scanner);
			}
			tempChannel.setSumOfExpense(sumOfTempExpense);
			break;
		default:
			System.out.println("취소 되었습니다.");
			break;
		}
		try {
			File file = new File(".//DB//Channel.txt");
			FileWriter fileWriter = new FileWriter(file);
			for (Channel channel : this.channelList.getAll()) {
				fileWriter.write(channel.getChannelID() + " " + channel.getChannelName() + " "
						+ channel.getRegisterDate() + " " + channel.getNumOfRegister() + " "
						+ channel.getMonthlyExpense() + " " + channel.getSumOfExpense() + "\n");
				fileWriter.flush();

			}
			fileWriter.close();
		} catch (IOException e) {
			System.out.println("DB 접근 오류: 정보 접근에 실패하였습니다. 해당 문제가 계속 발생할 시에는 사내 시스템 관리팀(1234-5678)에게 문의 주시기 바랍니다.");
			e.printStackTrace();
		}
		System.out.println("수정이 완료되었습니다.");
	}

	public boolean searchChannel(String name) {
		ArrayList<Channel> tempChannelList = this.channelList.getName(name);
		if (!(tempChannelList == null)) {
			System.out
					.println("---------------------------------------------------------------------------------------");
			System.out.printf("%10s %10s %10s %10s %15s %15s", "채널ID", "채널명", "등록일", "가입자수", "월지출", "총지출");
			System.out.println();
			System.out
					.println("---------------------------------------------------------------------------------------");
			for (Channel channel : tempChannelList) {
				System.out.format("%10s %10s %15s %10d %15d %15d", channel.getChannelID(), channel.getChannelName(),
						channel.getRegisterDate().toString(), channel.getNumOfRegister(), channel.getMonthlyExpense(),
						channel.getSumOfExpense());
				System.out.println();
			}
			return true;
		} else {
			System.out.println("검색된 채널이 없습니다. 정확하게 입력하여 주시기 바랍니다");
			return false;
		}
	}

	public String getChannelID() {
		return channelID;
	}

	public void setChannelID(String channelID) {
		this.channelID = channelID;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public int getMonthlyExpense() {
		return monthlyExpense;
	}

	public void setMonthlyExpense(int monthlyExpense) {
		this.monthlyExpense = monthlyExpense;
	}

	public int getNumOfRegister() {
		return numOfRegister;
	}

	public void setNumOfRegister(int numOfRegister) {
		this.numOfRegister = numOfRegister;
	}

	public LocalDate getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(LocalDate registerDate) {
		this.registerDate = registerDate;
	}

	public int getSumOfExpense() {
		return sumOfExpense;
	}

	public void setSumOfExpense(int sumOfExpense) {
		this.sumOfExpense = sumOfExpense;
	}

}// end Channel