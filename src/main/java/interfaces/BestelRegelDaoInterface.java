package interfaces;

import java.util.List;
import pojo.*;

public interface BestelRegelDaoInterface {
	public Integer createBestelRegel(BestelRegel bestelRegel);
	public BestelRegel getBestelRegel(Integer id);
	public List<BestelRegel> getAlleBestelRegels();
	public List<BestelRegel> getBestelRegelsBestelling(Integer bestellingid);
	public void updateBestelRegel(BestelRegel bestelRegel);
	public boolean deleteBestelRegel(BestelRegel bestelRegel);
	public boolean deleteBestelRegel(Integer id);
}
