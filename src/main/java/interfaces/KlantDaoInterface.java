package interfaces;

import java.util.List;

import pojo.Klant;

public interface KlantDaoInterface {
	public Integer createKlant(Klant klant);
	public Klant getKlant(Integer id);
	public List<Klant> getAlleKlanten();
	public void updateKlant(Klant klant);
	public boolean deleteKlant(Klant klant);
	public boolean deleteKlant(Integer id);

}
