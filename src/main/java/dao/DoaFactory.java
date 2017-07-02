package dao;

import java.sql.Connection;

import jdbc.JdbcConnector;

public class DoaFactory {
	Connection conn = JdbcConnector.getConnection();
	public KlantDaoIS createKlantDao(){
		return new KlantDaoIS(conn);
	}
}
