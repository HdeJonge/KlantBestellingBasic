package interfaces;

import java.util.List;
import pojo.*;

public interface AdresDaoInterface {
	public Integer createAdres(Adres adres);
	public Adres getAdres(Integer id);
	public List<Adres> getAlleAdressen();
	public void updateAdres(Adres adres);
	public boolean deleteAdres(Adres adres);
	public boolean deleteAdres(Integer id);
}
