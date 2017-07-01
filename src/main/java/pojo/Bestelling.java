package pojo;

import java.math.*;
import java.util.*;

public class Bestelling {
	Integer id;
	Klant klant;
	BigDecimal totaalPrijs;
	List<BestelRegel> bestelRegels;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Klant getKlant() {
		return klant;
	}
	public void setKlant(Klant klant) {
		this.klant = klant;
	}
	public BigDecimal getTotaalPrijs() {
		return totaalPrijs;
	}
	public void setTotaalPrijs(BigDecimal totaalPrijs) {
		this.totaalPrijs = totaalPrijs;
	}
	public List<BestelRegel> getBestelRegels() {
		return bestelRegels;
	}
	public void setBestelRegels(List<BestelRegel> bestelRegels) {
		this.bestelRegels = bestelRegels;
	}
}
