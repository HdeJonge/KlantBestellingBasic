package pojo;

public class BestelRegel {
	Integer id;
	Bestelling bestelling;
	Artikel artikel;
	Integer aantal;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Bestelling getBestelling() {
		return bestelling;
	}
	public void setBestelling(Bestelling bestelling) {
		this.bestelling = bestelling;
	}
	public Artikel getArtikel() {
		return artikel;
	}
	public void setArtikel(Artikel artikel) {
		this.artikel = artikel;
	}
	public Integer getAantal() {
		return aantal;
	}
	public void setAantal(Integer aantal) {
		this.aantal = aantal;
	}

}
