package Model.DB;

import java.sql.ResultSet;

import Model.Channel.Channel;

public class ChannelDao  extends Dao {
	public ChannelDao() {
		super.connect();
	}

	public boolean create(Channel channel) {
		StringBuilder stringBuilder = new StringBuilder();

		String query = stringBuilder.append("insert into Channel values(")
				.append("\'" + channel.getChannelID() + "\',")
				.append("\'" +  channel.getChannelName()+ "\',")
				.append("\'" + channel.getRegisterDate() + "\',")
				.append( "\'" + channel.getNumOfRegister() + "\',")
				.append( "\'" + channel.getMonthlyExpense() + "\',")
				.append( "\'" + channel.getSumOfExpense() + "\')")
				.toString();

		System.out.println(query);
		return super.create(query);
	}

	public ResultSet retrive() {
		String query = "SELECT * FROM channel";

		System.out.println(query);
		return super.retrive(query);
	}
	public boolean delete(String ID) {
		StringBuilder stringBuilder = new StringBuilder();

		String query = stringBuilder.append("delete from channel")
				.append(" where channelID = \'"+ ID+"\'")
				.toString();

		System.out.println(query);
		return super.delete(query);
	}


	public boolean updateID(Channel channel) {
		StringBuilder stringBuilder = new StringBuilder();

		String query = stringBuilder.append("update channel set channelID = ")
				.append("\'"+ channel.getChannelID()+"\'")
				.append(" where channelID = \'" + channel.getChannelID() + "\'")
				.toString();

		System.out.println(query);
		return super.update(query);
	}

	
	public boolean updateName(Channel channel) {
		StringBuilder stringBuilder = new StringBuilder();

		String query = stringBuilder.append("update channel set channelName = ")
				.append("\'"+ channel.getChannelName()+"\'")
				.append(" where channelID = \'" + channel.getChannelID() + "\'")
				.toString();

		System.out.println(query);
		return super.update(query);
		
	}

	public boolean updateDate(Channel channel) {
		StringBuilder stringBuilder = new StringBuilder();

		String query = stringBuilder.append("update channel set registerDate = ")
				.append("\'"+ channel.getRegisterDate()+"\'")
				.append(" where channelID = \'" + channel.getChannelID() + "\'")
				.toString();

		System.out.println(query);
		return super.update(query);
		
		
	}

	public boolean updateNumOf(Channel channel) {
		StringBuilder stringBuilder = new StringBuilder();

		String query = stringBuilder.append("update channel set numOfRegister = ")
				.append("\'"+ channel.getNumOfRegister()+"\'")
				.append(" where channelID = \'" + channel.getChannelID() + "\'")
				.toString();

		System.out.println(query);
		return super.update(query);
		
	}

	public boolean updateMonthlyExpense(Channel channel) {
		StringBuilder stringBuilder = new StringBuilder();

		String query = stringBuilder.append("update channel set monthlyExpense = ")
				.append("\'"+ channel.getMonthlyExpense()+"\'")
				.append(" where channelID = \'" + channel.getChannelID() + "\'")
				.toString();

		System.out.println(query);
		return super.update(query);
		
	}

	public boolean updateSumOfExpense(Channel channel) {
		StringBuilder stringBuilder = new StringBuilder();

		String query = stringBuilder.append("update channel set sumOfExpense = ")
				.append("\'"+ channel.getSumOfExpense()+"\'")
				.append(" where channelID = \'" + channel.getChannelID() + "\'")
				.toString();

		System.out.println(query);
		return super.update(query);
		
	}


}
