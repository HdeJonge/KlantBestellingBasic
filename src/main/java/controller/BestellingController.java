package controller;

import java.util.List;

import dao.*;
import interfaces.*;
import java.math.BigDecimal;
import pojo.*;

public class BestellingController {

    public static List<Bestelling> getAlleBestellingen() {
        BestellingDaoInterface dao = new BestellingDao();
        return dao.getAlleBestellingen();
    }

    public static List<Bestelling> getBestellingenKlant(Integer id) {
        BestellingDaoInterface dao = new BestellingDao();
        return dao.getBestellingenKlant(id);
    }

    public static Bestelling getBestelling(Integer id) {
        BestellingDaoInterface dao = new BestellingDao();
        Bestelling bestelling = dao.getBestelling(id);
        //get bestelregel
        BestelRegelDaoInterface regelDao = new BestelRegelDao();
        List<BestelRegel> lijst = regelDao.getBestelRegelsBestelling(bestelling.getId());
        //get artikel
        ArtikelDaoInterface artikelDao = new ArtikelDao();
        for (BestelRegel br : lijst) {
            Artikel artikel = artikelDao.getArtikel(br.getArtikel().getId());
            br.setArtikel(artikel);
        }
        bestelling.setBestelRegels(lijst);
        return bestelling;
    }

    public static BestelRegel getBestelRegel(Integer id) {
        BestelRegelDaoInterface dao = new BestelRegelDao();
        return dao.getBestelRegel(id);
    }

    public static int createBestelling(Bestelling bestelling) {
        BestellingDaoInterface dao = new BestellingDao();
        return dao.createBestelling(bestelling);

    }

    public static int createBestelRegel(BestelRegel bestelRegel) {
        BestelRegelDaoInterface dao = new BestelRegelDao();
        updateTotaalPrijs(bestelRegel);
        return dao.createBestelRegel(bestelRegel);
    }

    public static void updateBestelling(Bestelling bestelling) {
        BestellingDaoInterface dao = new BestellingDao();
        dao.updateBestelling(bestelling);
    }

    public static void updateBestelRegel(BestelRegel bestelRegel) {
        BestelRegelDaoInterface dao = new BestelRegelDao();
        dao.updateBestelRegel(bestelRegel);
        updateTotaalPrijs(bestelRegel);
    }

    private static void updateTotaalPrijs(BestelRegel bestelRegel) {
        Bestelling bestelling = bestelRegel.getBestelling();
        BigDecimal totaalPrijs = bestelRegel.getArtikel().getPrijs()
                .multiply(new BigDecimal(bestelRegel.getAantal()));
        bestelling.setTotaalPrijs(bestelling.getTotaalPrijs().add(totaalPrijs));
        updateBestelling(bestelling);
    }

}
