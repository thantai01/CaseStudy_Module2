package sample.FileIO;

import javafx.collections.ObservableList;
import sample.Model.Account;
import sample.Model.Expense;
import sample.Model.Fund;
import sample.Service.AccountManager;

import java.io.*;
import java.util.List;

public class WriteFile {
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String EXPENSE_FILE_HEADER = "";
    private static final String FUND_FILE_HEADER = "FundName,Balance,TotalSpending,Deposited,TotalDeposited,CreatedTime,LastRechargeTime,ExpenseList";

    public static void writeExpenseToStringFile(String path, ObservableList<Expense> expenseList) throws Exception {
        FileWriter fw = new FileWriter(path);
        BufferedWriter bfw = new BufferedWriter(fw);
        fw.append(EXPENSE_FILE_HEADER);
        fw.append(NEW_LINE_SEPARATOR);
        for(Expense expense: expenseList) {
            fw.write(expense.getFundName() + COMMA_DELIMITER
                    + expense.getEventTime() + COMMA_DELIMITER
                    + expense.getEventName() + COMMA_DELIMITER
                    + expense.getEventCost()
                    + NEW_LINE_SEPARATOR);
        }
        System.out.println("Success Write Expense List FILE to Destination");
        fw.flush();
        bfw.close();
        fw.close();
    }
    public static void writeExpenseObject(String path, ObservableList<Expense> expenseList) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path));
        objectOutputStream.writeObject(expenseList);
        objectOutputStream.flush();
        objectOutputStream.close();
        System.out.println("Success write Expense object to binary File");
    }
    public static void writeFundsToStringFile(String path, ObservableList<Fund> fundList) throws Exception {
        FileWriter fw = new FileWriter(path);
        BufferedWriter bfw = new BufferedWriter(fw);
        fw.append(FUND_FILE_HEADER);
        fw.append(NEW_LINE_SEPARATOR);
        for(Fund fund: fundList) {
            fw.write(fund.getFundName() + COMMA_DELIMITER
                    + fund.getBalance() + COMMA_DELIMITER
                    + fund.getTotalSpending() + COMMA_DELIMITER
                    + fund.getDeposited() + COMMA_DELIMITER
                    + fund.getTotalDeposited() + COMMA_DELIMITER
                    + fund.getCreatedTime() + COMMA_DELIMITER
                    + fund.getLastRechargeDate() + COMMA_DELIMITER
                    + NEW_LINE_SEPARATOR
            );
        }
        System.out.println("Successful write Fund List FILE to Destination ");
        fw.flush();
        bfw.close();
        fw.close();
    }

    public static void writeFundObject(String path, ObservableList<Fund> fundList) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path));
        objectOutputStream.writeObject(fundList);
        objectOutputStream.flush();
        objectOutputStream.close();
        System.out.println("Success write Funds object to binary File");
    }
    public static void writeAccountObject(String path, AccountManager accountList) throws IOException {
        ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream(path));
        oss.writeObject(accountList);
        oss.flush();
        oss.close();
        System.out.println("Success write Account Data");
    }
}
