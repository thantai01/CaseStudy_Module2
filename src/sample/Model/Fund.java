package sample.Model;

import javafx.scene.control.TextField;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Fund {
    private String fundName;
    private int balance;
    private int totalSpending;
    private int recharge;
    private String lastRechargeDate;
    private String createdTime;
    private List<Expense> expenseList;


    public Fund(String fundName) {
        this.fundName = fundName;
        this.balance = 0;
        this.totalSpending = 0;
        this.recharge = 0;
        this.createdTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        this.lastRechargeDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        this.expenseList = new ArrayList<>();
    }

    public Fund() {
    }

    public String getFundName() {
        return fundName;
    }

    public int getBalance() {
        return balance;
    }

    public int getTotalSpending() {
        return totalSpending;
    }

    public int getRecharge() {
        return recharge;
    }

    public String getLastRechargeDate() {
        return lastRechargeDate;
    }

    public List<Expense> getExpenseList() {
        return expenseList;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setTotalSpending(int totalSpending) {
        this.totalSpending = totalSpending;
    }

    public void setRecharge(int recharge) {
        this.recharge = recharge;
    }

    public void setLastRechargeDate(String lastRechargeDate) {
        this.lastRechargeDate = lastRechargeDate;
    }

    public void setExpenseList(List<Expense> expenseList) {
        this.expenseList = expenseList;
    }

    public String getLastExpense() {
        return createdTime;
    }

    public void setLastExpense(String lastExpense) {
        this.createdTime = lastExpense;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return "Fund{" +
                "fundName='" + fundName + '\'' +
                ", balance=" + balance +
                ", totalSpending=" + totalSpending +
                ", recharge=" + recharge +
                ", lastRechargeDate=" + lastRechargeDate +
                ", createdTime=" + createdTime +
                ", expenseList=" + expenseList +
                '}';
    }
}
