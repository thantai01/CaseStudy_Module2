package sample.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Fund {
    private String fundName;
    private int balance;
    private int totalSpending;
    private int recharge;
    private LocalDateTime lastRechargeDate;
    private List<Expense> expenseList;

    public Fund(String fundName) {
        this.fundName = fundName;
        this.balance = 0;
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

    public LocalDateTime getLastRechargeDate() {
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

    public void setLastRechargeDate(LocalDateTime lastRechargeDate) {
        this.lastRechargeDate = lastRechargeDate;
    }

    public void setExpenseList(List<Expense> expenseList) {
        this.expenseList = expenseList;
    }

    @Override
    public String toString() {
        return "Fund {" +
                "Name:'" + fundName + '\'' +
                ", Balance=" + balance +
                ", TotalSpending=" + totalSpending +
                ", Recharge=" + recharge +
                ", LastRechargeDate=" + lastRechargeDate +
                '}';
    }
}
