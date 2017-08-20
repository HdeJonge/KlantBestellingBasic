package controller;

import dao.AccountDao;
import interfaces.AccountDaoInterface;

public class AccountController {
	public static boolean validateLogin(String user, String password){
		AccountDaoInterface dao = new AccountDao();
		return dao.validateLogin(user, password);
	}

}
