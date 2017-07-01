package pojo;

import java.util.List;

public class Klant {
	String voornaam;
	String achternaam;
	String tussenvoegsel;
	List<Adres> adressenlijst;
	List<Bestelling> bestellingen;
	Account account;
	public String getVoornaam() {
		return voornaam;
	}
	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}
	public String getAchternaam() {
		return achternaam;
	}
	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}
	public String getTussenvoegsel() {
		return tussenvoegsel;
	}
	public void setTussenvoegsel(String tussenvoegsel) {
		this.tussenvoegsel = tussenvoegsel;
	}
	public List<Adres> getAdressenlijst() {
		return adressenlijst;
	}
	public void setAdressenlijst(List<Adres> adressenlijst) {
		this.adressenlijst = adressenlijst;
	}
	public List<Bestelling> getBestellingen() {
		return bestellingen;
	}
	public void setBestellingen(List<Bestelling> bestellingen) {
		this.bestellingen = bestellingen;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
}
