package sample.Model;


import java.util.ArrayList;
import java.util.List;

public class Account {
    private String ID;
    private String password;
    private List<Fund> fundList;

    public Account(String ID, String password) {
        this.ID = ID;
        this.password = password;
        this.fundList = new ArrayList<>();
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
                "ID='" + ID + '\'' +
                ", password='" + password + '\'' +
                ", fundList=" + fundList +
                '}';
    }
}