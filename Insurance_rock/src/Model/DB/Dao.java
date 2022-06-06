package Model.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import exception.DBAcceptException;

public class Dao {
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	public void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/insurance_rock?serverTimezone=UTC&useSSL=true", "root", "1234");
		} catch (SQLException | ClassNotFoundException e) {
			throw new DBAcceptException();
		}
	}

	public boolean create(String query) {
		try {
			statement = connect.createStatement();
			if (!statement.execute(query))
				return true;
		} catch (SQLException e) {
			throw new DBAcceptException();
		}
		return false;
	}

	public ResultSet retrive(String query) {
		try {
			statement = connect.createStatement();
			this.resultSet = statement.executeQuery(query);

			return resultSet;
		} catch (SQLException e) {
			throw new DBAcceptException();
		}
	}

	public boolean update(String query) {
		try {
			statement = connect.createStatement();
			if (!statement.execute(query))
				return true;
		} catch (SQLException e) {
			throw new DBAcceptException();
		}
		return false;
	}

	public boolean delete(String query) {
		try {
			statement = connect.createStatement();
			if (!statement.execute(query))
				return true;
		} catch (SQLException e) {
			throw new DBAcceptException();
		}
		return false;
	}
}
