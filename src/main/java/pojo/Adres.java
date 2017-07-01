package pojo;

public class Adres {
	Integer id;
	String straat;
	Integer huisnummer;
	String toevoeging;
	String postcode;
	AdresType adresType;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStraat() {
		return straat;
	}
	public void setStraat(String straat) {
		this.straat = straat;
	}
	public Integer getHuisnummer() {
		return huisnummer;
	}
	public void setHuisnummer(Integer huisnummer) {
		this.huisnummer = huisnummer;
	}
	public String getToevoeging() {
		return toevoeging;
	}
	public void setToevoeging(String toevoeging) {
		this.toevoeging = toevoeging;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public AdresType getAdresType() {
		return adresType;
	}
	public void setAdresType(AdresType adresType) {
		this.adresType = adresType;
	}
}
