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
	public static void start() {
		ArtikelMenu menu = new ArtikelMenu();
		menu.terminal.printf("[artikelmenu]" 
				+ "\n Wat wilt u doen " 
				+ "\n 1: alle artikelen bekijken" 
				+ "\n 2: een artikel bekijken"
				+ "\n 3: een artikel toevoegen" 
				+ "\n 4: een artikel toevoegen" 
				+ "\n 6: afsluiten" + "\n");

		int keuze = menu.textIO.newIntInputReader().withDefaultValue(0).read("Keuze");

		switch (keuze) {
		case 1: toonAlleArtikelen();
		case 2: toonArtikel();
		case 3: voegArtikelToe();
		}
	}
	public static void toonAlleArtikelen(){
		ArtikelMenu menu = new ArtikelMenu();
		List<Artikel> artikelen = ArtikelController.getAlleArtikelen();
		for(Artikel a: artikelen){
			menu.terminal.println(a.toString());
		}
		start();
	}
	public static void toonArtikel(){
		ArtikelMenu menu = new ArtikelMenu();
		menu.terminal.println("voer een id in");
		int keuze = menu.textIO.newIntInputReader()
				.withDefaultValue(0)
				.read("Keuze");
		Artikel artikel = ArtikelController.getArtikel(keuze);
		menu.terminal.println(artikel.toString());
		start();
	}
	public static void voegArtikelToe(){
		ArtikelMenu menu = new ArtikelMenu();
		String naam = menu.textIO.newStringInputReader()
				.withDefaultValue("naam")
				.read("voor een naam in");
		Double prijs = menu.textIO.newDoubleInputReader()
				.withDefaultValue(0.0)
				.read("voor een prijs in");
		int voorraad = menu.textIO.newIntInputReader()
				.withDefaultValue(0)
				.read("voor de voorraad in");
		Artikel artikel = new Artikel(naam,new BigDecimal(prijs),voorraad);
		int id = ArtikelController.createArtikel(artikel);
		start();
	}
}
