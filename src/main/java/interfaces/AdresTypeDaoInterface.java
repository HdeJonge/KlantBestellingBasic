package interfaces;

import java.util.List;
import pojo.*;

public interface AdresTypeDaoInterface {
	public Integer createAdresType(AdresType adresType);
	public AdresType getAdresType(Integer id);
	public List<AdresType> getAlleAdresTypes();
	public void updateAdresType(AdresType adresType);
	public boolean deleteAdresType(AdresType adresType);
	public boolean deleteAdresType(Integer id);
}
