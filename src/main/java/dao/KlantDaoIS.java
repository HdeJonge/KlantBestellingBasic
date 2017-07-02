package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcConnector;
import pojo.Klant;

public class KlantDaoIS implements KlantDaoInterface {

	protected Connection connection;
	public KlantDaoIS(Connection connection){
		this.connection = connection;
	}
	public Integer createKlant(Klant klant) {
		String sql = "insert into klant(voornaam, achternaam, tussenvoegsel) values (?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, klant.getVoornaam());
			stmt.setString(2, klant.getAchternaam());
			stmt.setString(3, klant.getTussenvoegsel());
			boolean isStatementExcecuted = stmt.execute();
			ResultSet resultSet = stmt.getGeneratedKeys();
			if (resultSet.isBeforeFirst()) {
				resultSet.next();
				klant.setId(resultSet.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return klant.getId();

	}
	public Klant getKlant(Integer id) {
		String sql = "select * from klant where id=?";
		Klant klant = new Klant();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setObject(1, id);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			if (rs.isBeforeFirst()) {
				rs.next();
				klant.setId(rs.getInt(1));
				klant.setVoornaam(rs.getString(2));
				klant.setAchternaam(rs.getString(3));
				klant.setTussenvoegsel(rs.getString(4));
			}
            else{
            	System.err.println("Geen klant gevonden!");
            }

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return klant;
	}

	public List<Klant> getAlleKlanten() {
		String sql = "select * from klant";
		List<Klant> klanten = new ArrayList<Klant>();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			while(rs.next()){
				Klant klant = new Klant();
				klant.setId(rs.getInt(1));
				klant.setVoornaam(rs.getString(2));
				klant.setAchternaam(rs.getString(3));
				klant.setTussenvoegsel(rs.getString(4));
				klanten.add(klant);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return klanten;
	}

	public void updateKlant(Klant klant) {
		String sql = "Update klant set voornaam = ?, achternaam=?,tussenvoegsel=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, klant.getVoornaam());
			stmt.setString(2, klant.getAchternaam());
			stmt.setString(3, klant.getTussenvoegsel());
			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean deleteKlant(Klant klant) {
		return deleteKlant(klant.getId());
	}
	public boolean deleteKlant(Integer id) {
		String sql = "DELETE FROM klant WHERE id = ?";
		int rows = -1;
		try{
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
