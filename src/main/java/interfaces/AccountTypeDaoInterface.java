package interfaces;

import java.util.List;
import pojo.*;

public interface AccountTypeDaoInterface {
	public Integer createAccountType(AccountType accountType);
	public AccountType getAccountType(Integer id);
	public List<AccountType> getAlleAccountTypes();
	public void updateAccountType(AccountType accountType);
	public boolean deleteAccountType(AccountType accountType);
	public boolean deleteAccountType(Integer id);
}
