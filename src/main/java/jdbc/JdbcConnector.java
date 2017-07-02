package jdbc;

import java.sql.*;
import org.slf4j.*;
import parser.*;

public class JdbcConnector {
	private Connection connection;
	private String driver;
	private String user;
	private String password;
	private String url;
	
	private static final JdbcConnector instance = new JdbcConnector();
	public JdbcConnector(){
		//DomXmlParser parser = new DomXmlParser();
		Dom4JParser parser = new Dom4JParser();
		driver = parser.getDriver();
		user = parser.getUser();
		password = parser.getPassword();
		url = parser.getUrl();
	}
	public void createConnection(){
		Logger logger = LoggerFactory.getLogger(JdbcConnector.class);
		try {
			Class.forName(driver);
			logger.info("Driver loaded");
			connection = DriverManager.getConnection(url,user,password);
			logger.info("Database connected");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection(){
		try {
			if(instance.connection == null){
				instance.createConnection();
			}
			else if(instance.connection.isClosed()){
				instance.createConnection();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return instance.connection;
	}
}