package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcConnector;
import pojo.*;

public class AdresDao {
/*
	protected Connection connection;

	public Long createKlant(Adres adres) {
		String sql = "insert into Adres(straat, huisnummer, toevoeging, postcode) values 	(?,?,?,?)";
		try {
			Connection connection = JdbcConnector.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, adres.getStraat());
			stmt.setString(2, adres.getHuisnummer());
			stmt.setString(3, adres.getTussenvoegsel());
			stmt.setString(4, adres.getAchternaam());

			ResultSet resultSet = stmt.getGeneratedKeys();
			if (resultSet.isBeforeFirst()) {
				resultSet.next();
				klant.setId(resultSet.getLong(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return klant.getId();

	}
	public Klant getKlant(Long id) {
		String sql = "select voornaam, achternaam, tussenvoegsel, email from klant where id=?";
		Klant klant = new Klant();
		try {
			Connection connection = JdbcConnector.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt = connection.prepareStatement(sql);
			stmt.setObject(1, id);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			klant.setId(rs.getLong(1));
			klant.setVoornaam(rs.getString(2));
			klant.setAchternaam(rs.getString(3));
			klant.setTussenvoegsel(rs.getString(4));
			klant.setEmail(rs.getString(5));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return klant;
	}

	public List<Klant> getAlleKlanten() {
		String sql = "select * from klant";
		List<Klant> klanten = new ArrayList<Klant>();
		try {
			Connection connection = JdbcConnector.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt = connection.prepareStatement(sql);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			while(rs.next()){
				Klant klant = new Klant();
				klant.setId(rs.getLong(1));
				klant.setVoornaam(rs.getString(2));
				klant.setAchternaam(rs.getString(3));
				klant.setTussenvoegsel(rs.getString(4));
				klant.setEmail(rs.getString(5));
				klanten.add(klant);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return klanten;
	}

	public void updateKlant(Klant klant) {
		String sql = "Update klant set voornaam = ?, achternaam=?,tussenvoegsel=?,email=?";
		try {
			Connection connection = JdbcConnector.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, klant.getVoornaam());
			stmt.setString(2, klant.getAchternaam());
			stmt.setString(3, klant.getTussenvoegsel());
			stmt.setString(4, klant.getAchternaam());
			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void DeleteKlant(Klant klant) {
		DeleteKlant(klant.getId());
	}
	public void DeleteKlant(Long id) {
		String sql = "Delect from klant where id=?";
		try {
			Connection connection = JdbcConnector.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt = connection.prepareStatement(sql);
			stmt.setObject(1, id);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}*/
}
