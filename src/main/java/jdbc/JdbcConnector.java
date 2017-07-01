package jdbc;

import java.sql.*;
import org.slf4j.*;
import parser.DomXmlParser;

public class JdbcConnector {
	String driver;
	String user;
	String password;
	String url;
	public static void main(String[]args) throws ClassNotFoundException, SQLException{
		JdbcConnector jc = new JdbcConnector();
		jc.getConnection();
	}
	public JdbcConnector(){
		DomXmlParser parser = new DomXmlParser();
		driver = parser.getDriver();
		user = parser.getUser();
		password = parser.getPassword();
		url = parser.getUrl();

	}
	public void getConnection() throws ClassNotFoundException, SQLException{
		Logger logger = LoggerFactory.getLogger(JdbcConnector.class);
		Class.forName(driver);
		logger.info("Driver loaded");
		Connection connection = DriverManager.getConnection(url,user,password);
		logger.info("Database connected");

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from klant");
		
		while(resultSet.next()){
			System.out.println(
					resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3));
		}
		
		connection.close();
		logger.info("Connection closed");
		
	}
}
