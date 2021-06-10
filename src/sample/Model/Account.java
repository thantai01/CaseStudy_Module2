package sample.Model;

import sample.Service.Manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Account implements Manager<Fund> {
    private Map<String, String> accountInfo;
    private List<Fund> fundList;


    public Account(String accID, String pass) {
        this.accountInfo = new HashMap<>();
        this.accountInfo.put(accID,pass);
        this.fundList = new ArrayList<>();
    }

    public Map<String, String> getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(Map<String, String> accountInfo) {
        this.accountInfo = new HashMap<>();
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
                ", fundList=" + fundList +
                '}';
    }

    @Override
    public void add(Fund fund) {
        fundList.add(fund);
    }

    @Override
    public void print() {

    }

    @Override
    public void deleteItem() {

    }

    @Override
    public void editItem() {

    }
}
