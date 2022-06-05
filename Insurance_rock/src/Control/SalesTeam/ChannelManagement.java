package Control.SalesTeam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import Model.Channel.Channel;
import Model.Channel.ChannelListImpl;
import View.Team.SalesTeamTui;
import exception.DBAcceptException;
import exception.OverlapNumException;
import exception.WrongInputChannel;
import exception.WrongInputException;

public class ChannelManagement {

	private Channel channel;
	private ChannelListImpl channelList;
	private SalesTeamTui salesTeamTui;

	public ChannelManagement() {
		this.channel = new Channel();
		this.channelList = new ChannelListImpl();
		this.salesTeamTui = new SalesTeamTui();
		this.setChnnel();
	}

	public void viewChannel() {

		try {
			showChannel();
			selectChannelManagement();
		} catch (WrongInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void selectChannelManagement() throws WrongInputException {
		Scanner scanner = new Scanner(System.in);
		boolean correctInput = false;

		try {
			while (!correctInput) {
				this.salesTeamTui.viewSelectChannel();

				String select = scanner.next();
				if (select.equals("1") || select.equals("검색")) {
					searchChannel();
				} else if (select.equals("2") || select.equals("추가")) {
					addsaveChannel();
				} else if (select.equals("3") || select.equals("수정")) {
					editChannel();
				} else if (select.equals("4") || select.equals("삭제")) {
					delete();
				} else if (select.equals("0") || select.equals("취소")) {
					this.salesTeamTui.showCancel();
					correctInput = true;
				} else {
					throw new WrongInputException();
				}
			}
		} catch (WrongInputChannel e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OverlapNumException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void delete() throws WrongInputChannel {
		Scanner scanner = new Scanner(System.in);
		String channelID = null;
		boolean isSearch = false;
		while (!isSearch) {
			this.salesTeamTui.viewDeleteNum();
			channelID = scanner.next();
			if (this.searchID(channelID) != null) {
				isSearch = true;
			} else {
				throw new WrongInputChannel();
			}
		}
		this.salesTeamTui.viewDelete(channelID);
		if (scanner.next().equals("1")) {
			this.deleteChannel(channelID);
		} else {
			this.salesTeamTui.viewCancelMenu();
		}
	}

	private void editChannel() throws WrongInputChannel, OverlapNumException {
		Scanner scanner = new Scanner(System.in);
		boolean isSearch = false;
		String channelID = null;
		Channel tempChannel = null;
		while (!isSearch) {
			this.salesTeamTui.viewEditID();
			channelID = scanner.next();
			tempChannel = this.searchID(channelID);
			if (tempChannel != null) {
				isSearch = true;
				this.editChannel(tempChannel);
			} else {
				throw new WrongInputChannel();
			}
		}

	}

	private void addsaveChannel() {
		this.addChannel();
		this.saveChannel();
	}

	private void searchChannel() throws WrongInputChannel {
		Scanner scanner = new Scanner(System.in);
		boolean isSearch = false;
		String channelName;
		while (!isSearch) {
			this.salesTeamTui.viewChannelName();
			
			channelName = scanner.next();
			isSearch = this.searchChannel(channelName);
		}

	}

	public void showChannel() {
		this.salesTeamTui.viewChannel(this.channelList);
	}

	public void setChnnel() {
		ResultSet resultSet = this.channel.retrive();
		try {
			while (resultSet.next()) {
				Channel channel = new Channel();
				channel.setChannelID(resultSet.getString("channelID"));
				channel.setChannelName(resultSet.getString("channelName"));
				channel.setRegisterDate(LocalDate.parse(resultSet.getString("registerDate")));
				channel.setNumOfRegister(resultSet.getInt("numOfRegister"));
				channel.setMonthlyExpense(resultSet.getInt("monthlyExpense"));
				channel.setSumOfExpense(resultSet.getInt("sumOfExpense"));
				this.channelList.add(channel);
			}
		} catch (SQLException e) {
			throw new DBAcceptException();
		}

	}

	public void addChannel() {
		Scanner scanner = new Scanner(System.in);
		this.salesTeamTui.viewChannelID();
		String channelID = scanner.next();
		this.channel.setChannelID(channelID);
		while (searchID(this.channel.getChannelID()) != null) {
			
			channelID = scanner.next();
			this.channel.setChannelID(channelID);
		}

		this.salesTeamTui.viewInputName();
		String channelName = scanner.next();
		this.channel.setChannelName(channelName);
		int year = 2000;
		int month = 1;
		int day = 1;
		this.salesTeamTui.viewDate();
		boolean correctDate = false;
		while (!correctDate) {
			this.salesTeamTui.viewInputYear();
			year = checkInt(scanner);
			this.salesTeamTui.viewInputMonth();
			month =  checkInt(scanner);
			this.salesTeamTui.viewInputDay();
			day = checkInt(scanner);

			
			correctDate = checkDate(year + "-" + month + "-" + day);
		}
		LocalDate registerDate = LocalDate.of(year, month, day);
		this.channel.setRegisterDate(registerDate);

		this.salesTeamTui.viewInputNumOf();
		int numOfRegister = checkInt(scanner);
		this.channel.setNumOfRegister(numOfRegister);
		
		this.salesTeamTui.viewInputMonthlyExpense();
		int monthlyExpense = checkInt(scanner);
		this.channel.setMonthlyExpense(monthlyExpense);
		
		this.salesTeamTui.viewInputSumOf();
		int sumOfExpense = checkInt(scanner);
		this.channel.setSumOfExpense(sumOfExpense);
		
		while (this.channel.getMonthlyExpense() > this.channel.getSumOfExpense()) {
			this.salesTeamTui.viewOverExpense();
			sumOfExpense = checkInt(scanner);
			this.channel.setSumOfExpense(sumOfExpense);
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
			this.salesTeamTui.viewNonExistDate();
			return false;
		}
	}

	private int checkInt(Scanner scanner) {
		while (!scanner.hasNextInt()) {
			scanner.next();
			this.salesTeamTui.viewOnlyNumber();
		}
		return scanner.nextInt();
	}

	public void saveChannel() {
		boolean saveCompleted = this.channel.create();
		if (saveCompleted) {
			this.salesTeamTui.viewAddChannel();
		} else {
			throw new DBAcceptException();
		}
	}

	public void deleteChannel(String ID) {
		boolean deleteCompleted = false;
		this.channelList.delete(ID);
		deleteCompleted = this.channel.delete(ID);
		if (deleteCompleted) {
			this.salesTeamTui.viewDelteChannel();
		} else {
			throw new DBAcceptException();
		}
	}

	public void editChannel(Channel tempChannel) throws OverlapNumException {// null인 새로운 채널
		boolean updateCompleted = false;
		Scanner scanner = new Scanner(System.in);
		this.channel.setChannelID(tempChannel.getChannelID());
		this.channel.setChannelName(tempChannel.getChannelName());
		this.channel.setRegisterDate(tempChannel.getRegisterDate());
		this.channel.setNumOfRegister(tempChannel.getNumOfRegister());
		this.channel.setMonthlyExpense(tempChannel.getMonthlyExpense());
		this.channel.setSumOfExpense(tempChannel.getSumOfExpense());
		
		this.salesTeamTui.viewUpdateChannel(this.channel);
		
		while (!scanner.hasNextInt()) {
			scanner.next();
			this.salesTeamTui.viewOnlyNumber();
		}
		switch (scanner.nextInt()) {
		case 1:
			this.salesTeamTui.viewChannelID();
			String tempID = scanner.next();
			while ((searchID(tempID) != null) || this.channel.getChannelID().equals(tempID)) {
				throw new OverlapNumException();
			}
			while ((searchID(tempID) != null) || this.channel.getChannelID().equals(tempID)) {
				tempID = scanner.next();
			}
			tempChannel.setChannelID(tempID);
			this.channel.setChannelID(tempChannel.getChannelID());
			updateCompleted = this.channel.updateID();
			break;
		case 2:
			this.salesTeamTui.viewChannelName();
			tempChannel.setChannelName(scanner.next());
			this.channel.setChannelName(tempChannel.getChannelName());
			updateCompleted = this.channel.updateName();
			break;
		case 3:
			int year = 2000;
			int month = 1;
			int day = 1;
			this.salesTeamTui.viewDate();

			boolean correctDate = false;
			while (!correctDate) {
				this.salesTeamTui.viewInputYear();
				year = checkInt(scanner);
				this.salesTeamTui.viewInputMonth();
				month = checkInt(scanner);
				this.salesTeamTui.viewInputDay();
				day = checkInt(scanner);
				correctDate = checkDate(year + "-" + month + "-" + day);
			}

			tempChannel.setRegisterDate(LocalDate.of(year, month, day));
			this.channel.setRegisterDate(tempChannel.getRegisterDate());
			updateCompleted = this.channel.updateDate();

			break;
		case 4:
			this.salesTeamTui.viewInputNumOf();
			tempChannel.setNumOfRegister(checkInt(scanner));
			this.channel.setNumOfRegister(tempChannel.getNumOfRegister());

			updateCompleted = this.channel.updateNumOf();

			break;
		case 5:
			this.salesTeamTui.viewInputMonthlyExpense();
			tempChannel.setMonthlyExpense(checkInt(scanner));
			this.channel.setMonthlyExpense(tempChannel.getMonthlyExpense());
			updateCompleted = this.channel.updateMonthlyExpense();

			break;
		case 6:
			this.salesTeamTui.viewInputSumOf();
			int sumOfTempExpense = checkInt(scanner);
			while (this.channel.getMonthlyExpense() > sumOfTempExpense) {
				this.salesTeamTui.viewOverExpense();
				sumOfTempExpense = checkInt(scanner);
			}
			tempChannel.setSumOfExpense(sumOfTempExpense);
			this.channel.setSumOfExpense(tempChannel.getSumOfExpense());

			updateCompleted = this.channel.updateSumOfExpense();
			break;
		default:
			this.salesTeamTui.viewCancelMenu();
			break;
		}
		if (updateCompleted) {
			this.salesTeamTui.viewUpdateCompleted();
			
		} 

	}

	public boolean searchChannel(String name) {
		ArrayList<Channel> tempChannelList = this.channelList.getName(name);
		if (!(tempChannelList == null)) {
			this.salesTeamTui.viewSearchChannel(tempChannelList);
			
			return true;
		} else {
			this.salesTeamTui.viewNonSearch();
			return false;
		}
	}

}
