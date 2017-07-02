package controller;

import java.util.List;

import dao.ArtikelDao;
import interfaces.ArtikelDaoInterface;
import pojo.Artikel;

public class ArtikelController {
	public static List<Artikel> getAlleArtikelen(){
		ArtikelDaoInterface dao = new ArtikelDao();
		return dao.getAlleArtikelen();
	}
	public static Artikel getArtikel(Integer id){
		ArtikelDaoInterface dao = new ArtikelDao();
		return dao.getArtikel(id);
	}
	public static int createArtikel(Artikel artikel) {
		ArtikelDaoInterface dao = new ArtikelDao();
		return dao.createArtikel(artikel);
		
	}

}
