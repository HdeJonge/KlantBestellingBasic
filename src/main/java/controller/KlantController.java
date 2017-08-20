package controller;

import dao.KlantDao;
import interfaces.KlantDaoInterface;
import pojo.Klant;

public class KlantController {
	public static Klant getKlant(Integer id){
		KlantDaoInterface dao = new KlantDao();
		return dao.getKlant(id);
	}

}
