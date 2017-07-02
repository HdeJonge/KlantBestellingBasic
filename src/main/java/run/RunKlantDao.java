/**
 * 
 */
package run;

import java.sql.Connection;
import java.util.List;

import dao.*;
import jdbc.JdbcConnector;
import pojo.Klant;

/**
 * @author Herman
 *
 */
public class RunKlantDao {
	public static void main(String[] args) {
		addKlantFactoryDao();
	}
	
	public static void addKlantNormalDao(){
		
		KlantDao klantDao = new KlantDao();
		Klant klant = new Klant("Ian", "Nijhuis");
		Integer id = klantDao.createKlant(klant);
		System.out.println("Aangemaakt ID: " + id);
		Klant createdKlant = klantDao.getKlant(id);
		System.out.println("Klant uit database is: " + createdKlant.getFullName());
		klant.setTussenvoegsel("van");
		klantDao.updateKlant(klant);
		Klant updatedKlant = klantDao.getKlant(id);
		System.out.println("Klant na update is: " + updatedKlant.getFullName());
		boolean isKlantDeleted = klantDao.deleteKlant(id);
		System.out.println("Is klant gedelete? " + isKlantDeleted);
		Klant deletedKlant = klantDao.getKlant(id);
	}
	public static void addKlantISDao(){
		Connection conn = JdbcConnector.getConnection();
		KlantDaoInterface klantDao = new KlantDaoIS(conn);
		Klant klant = new Klant("Ian", "Nijhuis");
		Integer id = klantDao.createKlant(klant);
		System.out.println("Aangemaakt ID: " + id);
		Klant createdKlant = klantDao.getKlant(id);
		System.out.println("Klant uit database is: " + createdKlant.getFullName());
		klant.setTussenvoegsel("van");
		klantDao.updateKlant(klant);
		Klant updatedKlant = klantDao.getKlant(id);
		System.out.println("Klant na update is: " + updatedKlant.getFullName());
		boolean isKlantDeleted = klantDao.deleteKlant(id);
		System.out.println("Is klant gedelete? " + isKlantDeleted);
		Klant deletedKlant = klantDao.getKlant(id);
	}
	public static void addKlantFactoryDao(){
		DoaFactory daoFactory = new DoaFactory();
		KlantDaoIS klantDao = daoFactory.createKlantDao();
		Klant createdKlant = klantDao.getKlant(4);
		System.out.println("Klant uit database is: " + createdKlant.getFullName());
		klantDao.close();
	}
}
