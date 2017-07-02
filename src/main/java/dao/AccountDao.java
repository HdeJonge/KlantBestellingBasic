package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import interfaces.AccountDaoInterface;
import jdbc.JdbcConnector;
import pojo.*;

public class AccountDao implements AccountDaoInterface{

	protected Connection connection;

	public Integer createAccount(Account account) {
		String sql = "insert into Account(user, wachtwoord) values (?,?)";
		try {
			Connection connection = JdbcConnector.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, account.getUser());
			stmt.setString(2, account.getWachtwoord());
			stmt.executeUpdate();
			ResultSet resultSet = stmt.getGeneratedKeys();
			if (resultSet.isBeforeFirst()) {
				resultSet.next();
				account.setId(resultSet.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return account.getId();

	}
	public Account getAccount(Integer id) {
		String sql = "select * from account where id=?";
		Account account = new Account();
		try {
			Connection connection = JdbcConnector.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setObject(1, id);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			if (rs.isBeforeFirst()) {
				rs.next();
				account.setId(rs.getInt(1));
				account.setUser(rs.getString(2));
				account.setWachtwoord(rs.getString(3));
			}
            else{
            	System.err.println("Geen account gevonden!");
            }

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return account;
	}

	public List<Account> getAlleAccounts() {
		String sql = "select * from account";
		List<Account> accounts = new ArrayList<Account>();
		try {
			Connection connection = JdbcConnector.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			while(rs.next()){
				Account account = new Account();
				account.setId(rs.getInt(1));
				account.setUser(rs.getString(2));
				account.setWachtwoord(rs.getString(3));
				accounts.add(account);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accounts;
	}

	public void updateAccount(Account account) {
		String sql = "Update account set user= ?, wachtwoord=? where id = ?";
		try {
			Connection connection = JdbcConnector.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, account.getUser());
			stmt.setString(2, account.getWachtwoord());
			stmt.setInt(3, account.getId());
			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean deleteAccount(Account account) {
		return deleteAccount(account.getId());
	}
	public boolean deleteAccount(Integer id) {
		String sql = "DELETE FROM account WHERE id = ?";
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
