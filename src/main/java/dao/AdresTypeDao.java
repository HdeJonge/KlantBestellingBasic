package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import interfaces.AdresTypeDaoInterface;
import jdbc.JdbcConnector;
import pojo.AdresType;

public class AdresTypeDao implements AdresTypeDaoInterface {

	protected Connection connection;

	public Integer createAdresType(AdresType adresType) {
		String sql = "insert into adresType(type) values (?)";
		try {
			Connection connection = JdbcConnector.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, adresType.getType());
			stmt.executeUpdate();
			ResultSet resultSet = stmt.getGeneratedKeys();
			if (resultSet.isBeforeFirst()) {
				resultSet.next();
				adresType.setId(resultSet.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return adresType.getId();

	}
	public AdresType getAdresType(Integer id) {
		String sql = "select * from adresType where id=?";
		AdresType adresType = new AdresType();
		try {
			Connection connection = JdbcConnector.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setObject(1, id);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			if (rs.isBeforeFirst()) {
				rs.next();
				adresType.setId(rs.getInt(1));
				adresType.setType(rs.getString(2));
			}
            else{
            	System.err.println("Geen adresType gevonden!");
            }

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return adresType;
	}

	public List<AdresType> getAlleAdresTypes() {
		String sql = "select * from adresType";
		List<AdresType> adresTypeen = new ArrayList<AdresType>();
		try {
			Connection connection = JdbcConnector.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			while(rs.next()){
				AdresType adresType = new AdresType();
				adresType.setId(rs.getInt(1));
				adresType.setType(rs.getString(2));
				adresTypeen.add(adresType);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return adresTypeen;
	}

	public void updateAdresType(AdresType adresType) {
		String sql = "Update adresType set type = ? where id=?";
		try {
			Connection connection = JdbcConnector.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, adresType.getType());
			stmt.setInt(4, adresType.getId());
			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean deleteAdresType(AdresType adresType) {
		return deleteAdresType(adresType.getId());
	}
	public boolean deleteAdresType(Integer id) {
		String sql = "DELETE FROM adresType WHERE id = ?";
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
