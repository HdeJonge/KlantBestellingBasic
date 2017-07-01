package pojo;
import java.math.BigDecimal;

public class Artikel {
	Integer id;
	String naam;
	BigDecimal price;
	Integer voorraad;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Integer getVoorraad() {
		return voorraad;
	}
	public void setVoorraad(Integer voorraad) {
		this.voorraad = voorraad;
	}

}
