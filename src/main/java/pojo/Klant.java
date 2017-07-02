package pojo;

import java.util.List;

public class Klant {
	Integer id;
	String voornaam;
	String achternaam;
	String tussenvoegsel;
	String email;
	List<Adres> adressenlijst;
	List<Bestelling> bestellingen;
	Account account;
	public Klant(){
	}
	public Klant(String voornaam,String achternaam){
		this.voornaam = voornaam;
		this.achternaam = achternaam;
	}
	public String getVoornaam() {
		return voornaam;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getFullName(){
		return voornaam + " " + tussenvoegsel + " " + achternaam;
	}
	public int hashCode(){
		int hash = 1;
		hash *= 17 + id.hashCode();
		return hash;
	}
}
