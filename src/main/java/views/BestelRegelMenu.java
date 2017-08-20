package views;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

import controller.*;
import java.math.BigDecimal;
import pojo.*;

public class BestelRegelMenu {
	TextIO textIO = TextIoFactory.getTextIO();
	TextTerminal<?> terminal = textIO.getTextTerminal();

	public void start(Bestelling bestelling) {
		terminal.print("\n [bestellingmenu]" 
						+ "\n Wat wilt u doen " 
						+ "\n 1: artikel toevoegen" 
						+ "\n 2: aantal aanpassen"
						+ "\n 3: artikel verwijderen" 
						+ "\n 4: terug naar bestellingenMenu" 
						+ "\n 5: afsluiten" + "\n");
		int keuze = textIO.newIntInputReader().withDefaultValue(0).read("Keuze");
		switch (keuze) {
		case 1: voegArtikelToe(bestelling);
				break;
		case 2: pasAantalAan(bestelling);
				break;
		}
	}
	private void pasAantalAan(Bestelling bestelling) {
		int id = textIO.newIntInputReader().withDefaultValue(0).read("regel Id");
		BestelRegel regel = BestellingController.getBestelRegel(id);
		int aantal = textIO.newIntInputReader().withDefaultValue(0).read("aantal");
		regel.setAantal(aantal);
		
	}
	private void voegArtikelToe(Bestelling bestelling) {
		int artikelId = textIO.newIntInputReader().withDefaultValue(0).read("Artikel Id");
		int aantal = textIO.newIntInputReader().withDefaultValue(0).read("aantal");
                Artikel artikel = ArtikelController.getArtikel(artikelId);
                //maak nieuwe bestelregel aan
		BestelRegel regel = new BestelRegel();
                //vul bestelregel
		regel.setBestelling(bestelling);
		regel.setArtikel(artikel);
		regel.setAantal(aantal);
		BestellingController.createBestelRegel(regel);
	}
}
