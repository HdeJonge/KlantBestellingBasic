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
		String sql = "insert into bestel_Regel(aantal, bestelling_id, artikel_id) values (?,?,?)";
		try {
			Connection connection = JdbcConnector.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, bestelRegel.getAantal());
			stmt.setInt(2, bestelRegel.getBestelling().getId());
			stmt.setInt(3, bestelRegel.getArtikel().getId());
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
		String sql = "select * from bestel_Regel where id=?";
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
		String sql = "select * from bestel_Regel";
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
	public List<BestelRegel> getAlleBestelRegelsKlant(Integer id) {
		String sql = "select id,aantal from bestel_Regel where klant_id=?";
		List<BestelRegel> bestelRegels = new ArrayList<BestelRegel>();
		try {
			Connection connection = JdbcConnector.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
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
		public List<BestelRegel> getBestelRegelsBestelling(Integer bestellingid) {
			String sql = "select id,aantal,artikel_id from bestel_Regel where bestelling_id=?";
			List<BestelRegel> bestelRegels = new ArrayList<BestelRegel>();
			try {
				Connection connection = JdbcConnector.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql);
				stmt.setInt(1, bestellingid);
				stmt.execute();
				ResultSet rs = stmt.getResultSet();
				while(rs.next()){
					BestelRegel bestelRegel = new BestelRegel();
					bestelRegel.setId(rs.getInt(1));
					bestelRegel.setAantal(rs.getInt(2));
					Artikel artikel = new Artikel();
					artikel.setId(rs.getInt(3));
					bestelRegel.setArtikel(artikel);
					bestelRegels.add(bestelRegel);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return bestelRegels;
	}

	public void updateBestelRegel(BestelRegel bestelRegel) {
		String sql = "Update bestel_Regel set aantal= ? where id=?";
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
		String sql = "DELETE FROM bestel_Regel WHERE id = ?";
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
