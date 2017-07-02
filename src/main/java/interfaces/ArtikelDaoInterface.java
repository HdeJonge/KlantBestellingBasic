package interfaces;

import java.util.List;
import pojo.*;

public interface ArtikelDaoInterface {
	public Integer createArtikel(Artikel artikel);
	public Artikel getArtikel(Integer id);
	public List<Artikel> getAlleArtikelen();
	public void updateArtikel(Artikel artikel);
	public boolean deleteArtikel(Artikel artikel);
	public boolean deleteArtikel(Integer id);
}
