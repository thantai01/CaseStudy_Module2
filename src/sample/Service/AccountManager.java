package sample.Service;

import sample.Model.Account;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AccountManager implements Serializable {

    private List<Account> accountList;

    public AccountManager() {
        accountList = new ArrayList<>();
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }
    public void signUp(String id, String password) {
       Account account = new Account(id,password);
       accountList.add(account);
    }


}

