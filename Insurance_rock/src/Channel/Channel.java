package Channel;

import java.util.Date;

/**
 * @author ansm6
 * @version 1.0
 * @created 08-5-2022 ���� 10:34:18
 */
public class Channel {

	private String channelID;
	private String channelName;
	private int monthlyExpense;
	private int numOfRegister;
	private Date registerDate;
	private int sumOfExpense;

	public Channel(){

	}

	public void finalize() throws Throwable {

	}
	public void add(){

	}

	public void delete(){

	}

	public void edit(){

	}

	public void search(){

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

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public int getSumOfExpense() {
		return sumOfExpense;
	}

	public void setSumOfExpense(int sumOfExpense) {
		this.sumOfExpense = sumOfExpense;
	}
	
}//end Channel