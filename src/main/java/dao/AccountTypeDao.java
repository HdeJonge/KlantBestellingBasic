package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import interfaces.AccountTypeDaoInterface;
import jdbc.JdbcConnector;
import pojo.AccountType;

public class AccountTypeDao implements AccountTypeDaoInterface {

	protected Connection connection;

	public Integer createAccountType(AccountType accountType) {
		String sql = "insert into accountType(type) values (?)";
		try {
			Connection connection = JdbcConnector.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, accountType.getType());
			stmt.executeUpdate();
			ResultSet resultSet = stmt.getGeneratedKeys();
			if (resultSet.isBeforeFirst()) {
				resultSet.next();
				accountType.setId(resultSet.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accountType.getId();

	}
	public AccountType getAccountType(Integer id) {
		String sql = "select * from accountType where id=?";
		AccountType accountType = new AccountType();
		try {
			Connection connection = JdbcConnector.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setObject(1, id);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			if (rs.isBeforeFirst()) {
				rs.next();
				accountType.setId(rs.getInt(1));
				accountType.setType(rs.getString(2));
			}
            else{
            	System.err.println("Geen accountType gevonden!");
            }

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accountType;
	}

	public List<AccountType> getAlleAccountTypes() {
		String sql = "select * from accountType";
		List<AccountType> accountTypeen = new ArrayList<AccountType>();
		try {
			Connection connection = JdbcConnector.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			while(rs.next()){
				AccountType accountType = new AccountType();
				accountType.setId(rs.getInt(1));
				accountType.setType(rs.getString(2));
				accountTypeen.add(accountType);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accountTypeen;
	}

	public void updateAccountType(AccountType accountType) {
		String sql = "Update accountType set type = ? where id=?";
		try {
			Connection connection = JdbcConnector.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, accountType.getType());
			stmt.setInt(4, accountType.getId());
			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean deleteAccountType(AccountType accountType) {
		return deleteAccountType(accountType.getId());
	}
	public boolean deleteAccountType(Integer id) {
		String sql = "DELETE FROM accountType WHERE id = ?";
		int rows = -1;
		try{
			Connection connection = JdbcConnector.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setObject(1, id);
			rows = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows > 0;
	}
	public void close(){
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
