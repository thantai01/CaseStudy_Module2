package sample.Service;

import sample.Model.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountManager {
    private static List<Account> accountList;

    public AccountManager() {
        accountList = new ArrayList<>();
    }

    public static List<Account> getAccountList() {
        return accountList;
    }

    public static void setAccountList(List<Account> accountList) {
        AccountManager.accountList = accountList;
    }


    public void signup(String id,String password) {
        Account account = new Account(id,password);
        accountList.add(account);
    }

}

