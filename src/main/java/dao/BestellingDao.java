package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import interfaces.BestellingDaoInterface;
import jdbc.JdbcConnector;
import pojo.*;

public class BestellingDao implements BestellingDaoInterface{

	protected Connection connection;

	public Integer createBestelling(Bestelling bestelling) {
		String sql = "insert into Bestelling(totaalPrijs, klant_id) values (?,?)";
		try {
			Connection connection = JdbcConnector.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setBigDecimal(1, bestelling.getTotaalPrijs());
			stmt.setInt(2, bestelling.getKlant().getId());
			stmt.executeUpdate();
			ResultSet resultSet = stmt.getGeneratedKeys();
			if (resultSet.isBeforeFirst()) {
				resultSet.next();
				bestelling.setId(resultSet.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bestelling.getId();

	}
	public Bestelling getBestelling(Integer id) {
		String sql = "select * from bestelling where id=?";
		Bestelling bestelling = new Bestelling();
		try {
			Connection connection = JdbcConnector.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setObject(1, id);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			if (rs.isBeforeFirst()) {
				rs.next();
				bestelling.setId(rs.getInt(1));
				bestelling.setTotaalPrijs(rs.getBigDecimal(2));
			}
            else{
            	System.err.println("Geen bestelling gevonden!");
            }

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bestelling;
	}

	public List<Bestelling> getAlleBestellingen() {
		String sql = "select * from bestelling";
		List<Bestelling> bestellings = new ArrayList<Bestelling>();
		try {
			Connection connection = JdbcConnector.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			while(rs.next()){
				Bestelling bestelling = new Bestelling();
				bestelling.setId(rs.getInt(1));
				bestelling.setTotaalPrijs(rs.getBigDecimal(2));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bestellings;
	}
	public List<Bestelling> getBestellingenKlant(Integer id) {
		String sql = "select * from bestelling where klant_id=?";
		List<Bestelling> bestellingen = new ArrayList<Bestelling>();
		try {
			Connection connection = JdbcConnector.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setObject(1, id);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			while(rs.next()){
				Bestelling bestelling = new Bestelling();
				bestelling.setId(rs.getInt(1));
				bestelling.setTotaalPrijs(rs.getBigDecimal(2));
				bestellingen.add(bestelling);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bestellingen;
	}

	public void updateBestelling(Bestelling bestelling) {
		String sql = "Update bestelling set totaalprijs= ? where id =?";
		try {
			Connection connection = JdbcConnector.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setBigDecimal(1, bestelling.getTotaalPrijs());
			stmt.setInt(2, bestelling.getId());
			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean deleteBestelling(Bestelling bestelling) {
		return deleteBestelling(bestelling.getId());
	}
	public boolean deleteBestelling(Integer id) {
		String sql = "DELETE FROM bestelling WHERE id = ?";
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
