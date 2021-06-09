package sample.Model;

import java.util.Map;
import java.util.Set;

public class Account {
    private Map<String,String> accountManager;

    public Account(Map<String, String> accountManager) {
        this.accountManager = accountManager;
    }

    public Account() {
    }

    public Map<String, String> getAccountManager() {
        return accountManager;
    }

    public void setAccountManager(Map<String, String> accountManager) {
        this.accountManager = accountManager;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountManager=" + accountManager +
                '}';
    }
}
