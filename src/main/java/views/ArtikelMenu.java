package views;

import java.math.BigDecimal;
import java.util.List;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

import controller.ArtikelController;
import pojo.Artikel;

public class ArtikelMenu {
	TextIO textIO = TextIoFactory.getTextIO();
	TextTerminal<?> terminal = textIO.getTextTerminal();

	public void start() {
		terminal.print("\n [artikelmenu]" + "\n Wat wilt u doen " + "\n 1: alle artikelen bekijken"
				+ "\n 2: een artikel bekijken" + "\n 3: een artikel toevoegen" + "\n 4: een artikel aanpassen"
				+ "\n 5: terug naar hoofdmenu" + "\n 6: afsluiten" + "\n");

		int keuze = textIO.newIntInputReader().withDefaultValue(0).read("Keuze");
		switch (keuze) {
		case 1:
			toonAlleArtikelen();
			break;
		case 2:
			toonArtikel();
			break;
		case 3:
			voegArtikelToe();
			break;
		case 4:
			pasArtikelAan();
			break;
		case 5:
			HoofdMenu.start();
		default:
			terminal.print("fout invoer");
			start();
		}
		start();
	}

	private void pasArtikelAan() {
		terminal.print("\n Welk artikel wilt u aanpassen");
		int id = textIO.newIntInputReader().withDefaultValue(0).read("Id");
		Artikel artikel = ArtikelController.getArtikel(id);
		pasArtikelAttribuutAan(artikel);
		ArtikelController.updateArtikel(artikel);
	}

	private Artikel pasArtikelAttribuutAan(Artikel artikel) {
		boolean doorgaan = false;
		do {
			terminal.print("\n Wat wilt u aanpassen " 
					+ "\n 1: naam aanpassen" 
					+ "\n 2: prijs aanpassen"
					+ "\n 3: voorraad" 
					+ "\n 5: terug naar hoofdmenu" 
					+ "\n 6: afsluiten" 
					+ "\n");
			int keuze = textIO.newIntInputReader().withDefaultValue(0).read("Keuze");
			switch (keuze) {
			case 1:
				terminal.print("voer nieuwe naam in");
				artikel.setNaam(textIO.newStringInputReader().read("naam"));
				break;
			case 2:
				terminal.print("voer nieuwe prijs in");
				artikel.setPrijs(new BigDecimal(textIO.newStringInputReader().read("prijs")));
				break;
			case 3:
				terminal.print("voer nieuwe voorraad in");
				artikel.setVoorraad(textIO.newIntInputReader().read("voorraad"));
				break;
			}
			terminal.print("wilt u nog iets aanpassen? "
					+ "\n 1: nog iets aanpassen"
					+ "\n 2: verder gaan");
			doorgaan = textIO.newIntInputReader().read("keuze") == 1;
		} while (doorgaan);

		return artikel;
	}

	public void toonAlleArtikelen() {
		List<Artikel> artikelen = ArtikelController.getAlleArtikelen();
		for (Artikel a : artikelen) {
			terminal.println("->" + a.toString());
		}
		start();
	}

	public void toonArtikel() {
		terminal.println("voer een id in");
		int keuze = textIO.newIntInputReader().withDefaultValue(0).read("Keuze");
		Artikel artikel = ArtikelController.getArtikel(keuze);
		terminal.print("->" + artikel.toString());
		start();
	}

	public void voegArtikelToe() {
		String naam = textIO.newStringInputReader().withDefaultValue("naam").read("voor een naam in");
		Double prijs = textIO.newDoubleInputReader().withDefaultValue(0.0).read("voor een prijs in");
		int voorraad = textIO.newIntInputReader().withDefaultValue(0).read("voor de voorraad in");
		Artikel artikel = new Artikel(naam, new BigDecimal(prijs), voorraad);
		int id = ArtikelController.createArtikel(artikel);
		start();
	}
}
