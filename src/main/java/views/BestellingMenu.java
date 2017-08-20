package views;

import java.math.BigDecimal;
import java.util.List;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

import controller.BestellingController;
import controller.KlantController;
import pojo.*;

public class BestellingMenu {
	TextIO textIO = TextIoFactory.getTextIO();
	TextTerminal<?> terminal = textIO.getTextTerminal();

	public void start() {
		terminal.print("\n [bestellingmenu]" 
				+ "\n Wat wilt u doen " 
				+ "\n 1: toon bestelling van klant"
				+ "\n 2: maak bestelling aan" 
				+ "\n 3: kies een bestelling" 
				+ "\n 4: terug naar hoofdmenu" 
				+ "\n 5: afsluiten" + "\n");

		int keuze = textIO.newIntInputReader().withDefaultValue(0).read("Keuze");
		switch (keuze) {
		case 1:
			toonBestellingVanKlant();
			break;
		case 2:
			maakBestellingAan();
			break;
		case 3:
			toonBestelling();
			break;
		case 4:
			HoofdMenu.start();
		case 5:
			HoofdMenu.start();
		default:
			terminal.print("fout invoer");
			start();
		}
		start();
	}

	private void toonBestelling() {
		int id = textIO.newIntInputReader().withDefaultValue(0).read("Bestelling Id");
		Bestelling bestelling = BestellingController.getBestelling(id);
		terminal.print("bestelling->" + bestelling.toString());
		for(BestelRegel regel: bestelling.getBestelRegels()){
			terminal.printf("\n%10s %10s(%2s) %10s", 
					regel.getId(),
					regel.getArtikel().getNaam(),
					regel.getArtikel().getId(),
					regel.getAantal() );
		}
		new BestelRegelMenu().start(bestelling);
	}

	private void maakBestellingAan() {
		terminal.print("\n Voor klantId in");
		int id = textIO.newIntInputReader().withDefaultValue(0).read("\nId");
		Klant klant = KlantController.getKlant(id);
		Bestelling bestelling = new Bestelling();
		bestelling.setTotaalPrijs(new BigDecimal(0));
		bestelling.setKlant(klant);
		BestellingController.createBestelling(bestelling);
		new BestelRegelMenu().start(bestelling);
	}

	private void toonBestellingVanKlant() {
		terminal.print("\n Voor klantId in");
		int id = textIO.newIntInputReader().withDefaultValue(0).read("\nId");
		printBestellingen(id);
	}

	private void printBestellingen(int id) {
		List<Bestelling> bestellingen = BestellingController.getBestellingenKlant(id);
		terminal.print("bestellingen van klant\n");
		for(Bestelling bestelling : bestellingen){
			terminal.println(bestelling.toString());
		}
	}
	public void toonAlleBestellingen() {
		List<Bestelling> artikelen = BestellingController.getAlleBestellingen();
		for (Bestelling a : artikelen) {
			terminal.println("->" + a.toString());
		}
		start();
	}
	

}
