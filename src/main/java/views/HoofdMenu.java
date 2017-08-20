package views;

import org.beryx.textio.*;

import controller.AccountController;

public class HoofdMenu {

	public static void main(String[] args) {
		System.out.println("Welkom!");
		HoofdMenu.login();
	}
	public static void login(){
		TextIO textIO = TextIoFactory.getTextIO();
		TextTerminal<?> terminal = textIO.getTextTerminal();
		String user = textIO.newStringInputReader()
		        .withDefaultValue("admin")
		        .read("Username");

		String password = textIO.newStringInputReader()
		        .withMinLength(3)
		        .withInputMasking(true)
		        .read("Password");
	       if(AccountController.validateLogin(user,password)){
	           terminal.println("Correct Login Credentials");
	           start();
	       }
	       else{
	           terminal.println("Incorrect Login Credentials");
	           login();
	        }
	}

	public static void start() {
		TextIO textIO = TextIoFactory.getTextIO();
		TextTerminal<?> terminal = textIO.getTextTerminal();
		terminal.printf("[hoofdmenu]"
				+ "\n Wat wilt u doen "
				+ "\n 1: artikelmenu bekijken"
				+ "\n 2: bestellingmenu bekijken"
				+ "\n 3: klantmenu bekijken"
				+ "\n 4: accountmenu bekijken"
				+ "\n 5: adresmenu bekijken"
				+ "\n 6: afsluiten"
				+ "\n");
		
		int keuze = textIO.newIntInputReader()
		        .withDefaultValue(0)
		        .read("Keuze");

		switch(keuze){
		case 1: new ArtikelMenu().start();
				break;
		case 2: new BestellingMenu().start();
								
			
		}
		
	}
	

}
