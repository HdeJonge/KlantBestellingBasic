package interfaces;

import java.util.List;
import pojo.*;

public interface AccountDaoInterface {
	public Integer createAccount(Account account);
	public Account getAccount(Integer id);
	public List<Account> getAlleAccounts();
	public void updateAccount(Account account);
	public boolean deleteAccount(Account account);
	public boolean deleteAccount(Integer id);
}
