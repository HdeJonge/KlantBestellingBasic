package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import interfaces.AdresDaoInterface;
import jdbc.JdbcConnector;
import pojo.Adres;

public class AdresDao implements AdresDaoInterface {

	protected Connection connection;

	public Integer createAdres(Adres adres) {
		String sql = "insert into adres(straat, huisnummer, toevoeging, postcode) values (?,?,?)";
		try {
			Connection connection = JdbcConnector.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, adres.getStraat());
			stmt.setInt(2, adres.getHuisnummer());
			stmt.setString(3, adres.getToevoeging());
			stmt.setString(4, adres.getPostcode());
			stmt.executeUpdate();
			ResultSet resultSet = stmt.getGeneratedKeys();
			if (resultSet.isBeforeFirst()) {
				resultSet.next();
				adres.setId(resultSet.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return adres.getId();

	}
	public Adres getAdres(Integer id) {
		String sql = "select * from adres where id=?";
		Adres adres = new Adres();
		try {
			Connection connection = JdbcConnector.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setObject(1, id);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			if (rs.isBeforeFirst()) {
				rs.next();
				adres.setId(rs.getInt(1));
				adres.setStraat(rs.getString(2));
				adres.setHuisnummer(rs.getInt(3));
				adres.setToevoeging(rs.getString(4));
				adres.setPostcode(rs.getString(5));
			}
            else{
            	System.err.println("Geen adres gevonden!");
            }

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return adres;
	}

	public List<Adres> getAlleAdressen() {
		String sql = "select * from adres";
		List<Adres> adressen = new ArrayList<Adres>();
		try {
			Connection connection = JdbcConnector.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			while(rs.next()){
				Adres adres = new Adres();
				adres.setId(rs.getInt(1));
				adres.setStraat(rs.getString(2));
				adres.setHuisnummer(rs.getInt(3));
				adres.setToevoeging(rs.getString(4));
				adres.setPostcode(rs.getString(5));
				adressen.add(adres);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return adressen;
	}

	public void updateAdres(Adres adres) {
		String sql = "Update adres set straat= ?, huisnummer=?, toevoeging=?, postcode=? where id =?";
		try {
			Connection connection = JdbcConnector.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, adres.getStraat());
			stmt.setInt(2, adres.getHuisnummer());
			stmt.setString(3, adres.getToevoeging());
			stmt.setString(4, adres.getPostcode());
			stmt.setInt(5, adres.getId());
			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean deleteAdres(Adres adres) {
		return deleteAdres(adres.getId());
	}
	public boolean deleteAdres(Integer id) {
		String sql = "DELETE FROM adres WHERE id = ?";
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
