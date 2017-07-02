package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import interfaces.ArtikelDaoInterface;
import jdbc.JdbcConnector;
import pojo.*;

public class ArtikelDao implements ArtikelDaoInterface{

	protected Connection connection;

	public Integer createArtikel(Artikel artikel) {
		String sql = "insert into Artikel(naam, prijs, voorraad) values (?,?,?)";
		try {
			Connection connection = JdbcConnector.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, artikel.getNaam());
			stmt.setBigDecimal(2, artikel.getPrijs());
			stmt.setInt(3, artikel.getVoorraad());
			stmt.executeUpdate();
			ResultSet resultSet = stmt.getGeneratedKeys();
			if (resultSet.isBeforeFirst()) {
				resultSet.next();
				artikel.setId(resultSet.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return artikel.getId();

	}
	public Artikel getArtikel(Integer id) {
		String sql = "select * from artikel where id=?";
		Artikel artikel = new Artikel();
		try {
			Connection connection = JdbcConnector.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setObject(1, id);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			if (rs.isBeforeFirst()) {
				rs.next();
				artikel.setId(rs.getInt(1));
				artikel.setNaam(rs.getString(2));
				artikel.setPrijs(rs.getBigDecimal(3));
				artikel.setVoorraad(rs.getInt(4));
			}
            else{
            	System.err.println("Geen artikel gevonden!");
            }

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return artikel;
	}

	public List<Artikel> getAlleArtikelen() {
		String sql = "select * from artikel";
		List<Artikel> artikels = new ArrayList<Artikel>();
		try {
			Connection connection = JdbcConnector.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			while(rs.next()){
				Artikel artikel = new Artikel();
				artikel.setId(rs.getInt(1));
				artikel.setNaam(rs.getString(2));
				artikel.setPrijs(rs.getBigDecimal(3));
				artikel.setVoorraad(rs.getInt(4));
				artikels.add(artikel);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return artikels;
	}

	public void updateArtikel(Artikel artikel) {
		String sql = "Update artikel set naam= ?, prijs=?, voorraad=? where id =?";
		try {
			Connection connection = JdbcConnector.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, artikel.getNaam());
			stmt.setBigDecimal(2, artikel.getPrijs());
			stmt.setInt(3, artikel.getVoorraad());
			stmt.setInt(4, artikel.getId());
			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean deleteArtikel(Artikel artikel) {
		return deleteArtikel(artikel.getId());
	}
	public boolean deleteArtikel(Integer id) {
		String sql = "DELETE FROM artikel WHERE id = ?";
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
