package sample.FileIO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.Account;
import sample.Model.Expense;
import sample.Model.Fund;
import sample.Service.AccountManager;

import java.io.*;


public class ReadFile {
    public static ObservableList<Fund> readFundCSVFile(String path) throws IOException {
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);
        String content;
        ObservableList<Fund> newFundList = FXCollections.observableArrayList();
        br.readLine();
        while((content = br.readLine())!=null) {
            String[] line = content.split(",");
            newFundList.add(
                    new Fund(line[0],
                            Integer.parseInt(line[1]),
                            Integer.parseInt(line[2]),
                            Integer.parseInt(line[3]),
                            Integer.parseInt(line[4]),
                            line[5],
                            line[6]
                            )
            );
        }
        return  newFundList;
    }
    public static ObservableList<Expense> readExpenseCSVFile(String path) throws IOException {
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);
        String content;
        ObservableList<Expense> newExpenseList = FXCollections.observableArrayList();
        br.readLine();
        while ((content = br.readLine())!=null) {
            String[] line = content.split(",");
            newExpenseList.add(
                    new Expense(line[0],
                            line[1],
                            line[2],
                            Integer.parseInt(line[3])));
        }
        return newExpenseList;
    }
    public static AccountManager readAccountList(String path) throws IOException{
        ObjectInputStream ois = null;
        AccountManager accountManager = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(path));
            accountManager = (AccountManager) ois.readObject();
            System.out.println(accountManager);
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            assert ois != null;
            ois.close();
        }
        System.out.println("Successfully read File");
        return accountManager;
    }
}
