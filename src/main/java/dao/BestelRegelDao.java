package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import interfaces.BestelRegelDaoInterface;
import jdbc.JdbcConnector;
import pojo.*;

public class BestelRegelDao implements BestelRegelDaoInterface{

	protected Connection connection;

	public Integer createBestelRegel(BestelRegel bestelRegel) {
		String sql = "insert into BestelRegel(aantal) values (?)";
		try {
			Connection connection = JdbcConnector.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, bestelRegel.getAantal());
			stmt.executeUpdate();
			ResultSet resultSet = stmt.getGeneratedKeys();
			if (resultSet.isBeforeFirst()) {
				resultSet.next();
				bestelRegel.setId(resultSet.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bestelRegel.getId();

	}
	public BestelRegel getBestelRegel(Integer id) {
		String sql = "select * from bestelRegel where id=?";
		BestelRegel bestelRegel = new BestelRegel();
		try {
			Connection connection = JdbcConnector.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setObject(1, id);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			if (rs.isBeforeFirst()) {
				rs.next();
				bestelRegel.setId(rs.getInt(1));
				bestelRegel.setAantal(rs.getInt(2));
			}
            else{
            	System.err.println("Geen bestelRegel gevonden!");
            }

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bestelRegel;
	}

	public List<BestelRegel> getAlleBestelRegels() {
		String sql = "select * from bestelRegel";
		List<BestelRegel> bestelRegels = new ArrayList<BestelRegel>();
		try {
			Connection connection = JdbcConnector.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			while(rs.next()){
				BestelRegel bestelRegel = new BestelRegel();
				bestelRegel.setId(rs.getInt(1));
				bestelRegel.setAantal(rs.getInt(2));
				bestelRegels.add(bestelRegel);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bestelRegels;
	}

	public void updateBestelRegel(BestelRegel bestelRegel) {
		String sql = "Update bestelRegel set aantal= ? where id=?";
		try {
			Connection connection = JdbcConnector.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, bestelRegel.getAantal());
			stmt.setInt(2, bestelRegel.getId());
			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean deleteBestelRegel(BestelRegel bestelRegel) {
		return deleteBestelRegel(bestelRegel.getId());
	}
	public boolean deleteBestelRegel(Integer id) {
		String sql = "DELETE FROM bestelRegel WHERE id = ?";
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
