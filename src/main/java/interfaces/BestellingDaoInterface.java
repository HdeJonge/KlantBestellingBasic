package interfaces;

import java.util.List;
import pojo.*;

public interface BestellingDaoInterface {
	public Integer createBestelling(Bestelling bestelling);
	public Bestelling getBestelling(Integer id);
	public List<Bestelling> getAlleBestellings();
	public void updateBestelling(Bestelling bestelling);
	public boolean deleteBestelling(Bestelling bestelling);
	public boolean deleteBestelling(Integer id);
}
