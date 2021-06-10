package sample.Model;

import java.util.List;
import java.util.Map;

public class Account {
    private Map<String,String> accountManager;
    private List<Fund> fundList;

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

    public List<Fund> getFundList() {
        return fundList;
    }

    public void setFundList(List<Fund> fundList) {
        this.fundList = fundList;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountManager=" + accountManager +
                '}';
    }
}
