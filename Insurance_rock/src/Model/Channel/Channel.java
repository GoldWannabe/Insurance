package Model.Channel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import Model.Contract.Contract;
import Model.DB.ChannelDao;

public class Channel {

	private String channelID;
	private String channelName;
	private LocalDate registerDate;
	private int numOfRegister;
	private int monthlyExpense;
	private int sumOfExpense;
	private ChannelDao channelDao;
	
	public Channel() {
		this.channelDao = new ChannelDao();
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



	public ResultSet retrive() {
		return this.channelDao.retrive();
	}



	public boolean create() {
		return this.channelDao.create(this);
		
	}



	public boolean delete(String iD) {
		return this.channelDao.delete(iD);
		
	}



	public boolean updateID() {
		return this.channelDao.updateID(this);
	}



	public boolean updateName() {
		return this.channelDao.updateName(this);
	}



	public boolean updateDate() {
		return this.channelDao.updateDate(this);
	}



	public boolean updateNumOf() {
		return this.channelDao.updateNumOf(this);
	}



	public boolean updateMonthlyExpense() {
		return this.channelDao.updateMonthlyExpense(this);
	}



	public boolean updateSumOfExpense() {
		return this.channelDao.updateSumOfExpense(this);
	}



	

}// end Channel