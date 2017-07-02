package views;

import org.beryx.textio.*;

public class HoofdMenu {

	public static void main(String[] args) {
		System.out.println("Welkom!");
		HoofdMenu.start();
	}

	public static void start() {
		TextIO textIO = TextIoFactory.getTextIO();
		TextTerminal<?> terminal = textIO.getTextTerminal();
		terminal.printf("[hoofdmenu]"
				+ "\n Wat wilt u doen "
				+ "\n 1: artikelen bekijken"
				+ "\n 2: bestellingen bekijken"
				+ "\n 3: klanten bekijken"
				+ "\n 4: accounts bekijken"
				+ "\n 5: adresen bekijken"
				+ "\n 6: afsluiten"
				+ "\n");
		
		int keuze = textIO.newIntInputReader()
		        .withDefaultValue(0)
		        .read("Keuze");

		switch(keuze){
		case 1: new ArtikelMenu().start();
				break;
		case 2: 
								
			
		}
		
	}

}
