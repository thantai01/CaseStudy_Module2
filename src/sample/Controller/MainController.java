package sample.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.FileIO.ReadFile;
import sample.FileIO.WriteFile;
import sample.Model.Account;
import sample.Model.Expense;
import sample.Model.Fund;
import sample.SceneController;

import javax.swing.*;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.XMLFormatter;

public class MainController implements Initializable {
    ObservableList<Fund> fundObservableList = FXCollections.observableArrayList();
    ObservableList<Expense> expenseObservableList = FXCollections.observableArrayList();

    public MainController() {
    }

    @FXML
    private TableView<Fund> fundTableView;

    @FXML
    private TableColumn<Fund, String> colFundName;

    @FXML
    private TableColumn<Fund, String> colCreatedDate;

    @FXML
    private TableColumn<Fund, String> colBalance;

    @FXML
    private TableColumn<Fund, Integer> colTotalSpending;

    @FXML
    private TableColumn<Fund, Integer> colTotalDeposited;

    @FXML
    private TableColumn<Fund, Integer> colLastDeposited;

    @FXML
    private TableColumn<Fund, String> colLastDepositedTime;

    @FXML
    private TextField tfEventName;

    @FXML
    private TextField tfEventCost;

    @FXML
    private Button btnAddToFund;

    @FXML
    private ChoiceBox<Fund> tfExpenseDestination;

    @FXML
    private TextField tfNewFundName;

    @FXML
    private TextField tfCommand;

    @FXML
    private Button btnCreateFund;

    @FXML
    private TextField tfDeposited;

    @FXML
    private ChoiceBox<Fund> tfFundDestination;

    @FXML
    private Button btnDeposited;

    @FXML
    private Label lbUserName;

    @FXML
    private TableColumn<Fund, Button> colBtnDelete;

    public void clearText() {
        tfNewFundName.clear();
        tfDeposited.clear();
        tfEventName.clear();
        tfEventCost.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectionFundView();
        fundTableView.setEditable(true);
        expenseTableView.setEditable(true);
        setEditableFundName();
        loadFundTable();
        loadExpenseTable();
        setEditableEvent();
        setTfFilter();
        setChoiceBox();

    }
    public void initialize(SortEvent<TableView<Fund>> tableViewSortEvent) {
    }

    public void selectionFundView() {
        tfFundDestination.setItems(fundObservableList);
        tfExpenseDestination.setItems(fundObservableList);
    }

    public void loadFundTable() {
        colFundName.setCellValueFactory(new PropertyValueFactory<>("fundName"));
        colCreatedDate.setCellValueFactory(new PropertyValueFactory<>("createdTime"));
        colBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        colTotalSpending.setCellValueFactory(new PropertyValueFactory<>("totalSpending"));
        colTotalDeposited.setCellValueFactory(new PropertyValueFactory<>("totalDeposited"));
        colLastDeposited.setCellValueFactory(new PropertyValueFactory<>("deposited"));
        colLastDepositedTime.setCellValueFactory(new PropertyValueFactory<>("lastRechargeDate"));
        fundTableView.setItems(fundObservableList);
    }

    public void setEditableFundName() {
        colFundName.setEditable(true);
        colFundName.setCellFactory(TextFieldTableCell.<Fund>forTableColumn());
        colFundName.setOnEditCommit((TableColumn.CellEditEvent<Fund, String> event) -> {
            TablePosition<Fund, String> pos = event.getTablePosition();
            String newFundName = event.getNewValue();
            int row = pos.getRow();
            Fund fund = event.getTableView().getItems().get(row);
            fund.setFundName(newFundName);
        });
    }

    public void setBtnCreateFund(ActionEvent actionEvent) {
        fundObservableList.add(new Fund(tfNewFundName.getText()));
        clearText();
    }

    public void setBtnDeposited(ActionEvent actionEvent) {
        for (Fund funds : fundObservableList) {
            if (funds.getFundName().equals(tfFundDestination.getValue().getFundName())) {
                int last = funds.getDeposited();
                funds.setDeposited(Integer.parseInt(tfDeposited.getText()));
                funds.setTotalDeposited(funds.getDeposited() + last);
                funds.setBalance(+funds.getTotalDeposited() - funds.getTotalSpending());
                funds.setLastRechargeDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            }
        }
        fundTableView.refresh();
        clearText();
    }

    @FXML
    private TableView<Expense> expenseTableView;

    @FXML
    private TableColumn<Expense, String> colOfFundName;

    @FXML
    private TableColumn<Expense, String> colEventTime;

    @FXML
    private TableColumn<Expense, String> colEventName;

    @FXML
    private TableColumn<Expense, Integer> colEventCost;


    public void loadExpenseTable() {
        colOfFundName.setCellValueFactory(new PropertyValueFactory<>("fundName"));
        colEventName.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        colEventCost.setCellValueFactory(new PropertyValueFactory<>("eventCost"));
        colEventTime.setCellValueFactory(new PropertyValueFactory<>("eventTime"));
        expenseTableView.setItems(expenseObservableList);
    }

    public void setEditableEvent() {
        colEventName.setEditable(true);
        colEventName.setCellFactory(TextFieldTableCell.<Expense>forTableColumn());
        colEventName.setOnEditCommit((TableColumn.CellEditEvent<Expense, String> event) -> {
            TablePosition<Expense, String> pos = event.getTablePosition();
            String newEventName = event.getNewValue();
            int row = pos.getRow();
            Expense expense = event.getTableView().getItems().get(row);
            expense.setEventName(newEventName);
        });
        colEventCost.setEditable(true);
    }

    public void setBtnAddToFund(ActionEvent actionEvent) {
        for (Fund fund : fundObservableList) {
            if (tfExpenseDestination.getValue().getFundName().equals(fund.getFundName())) {
                fund.getExpenseList().add(new Expense(fund.getFundName(), tfEventName.getText(),
                        Integer.parseInt(tfEventCost.getText())));
                int last =fund.getTotalSpending();
                fund.setTotalSpending(Integer.parseInt(tfEventCost.getText())+last);
                fund.setBalance(fund.getTotalDeposited()- fund.getTotalSpending());
                expenseObservableList.add(new Expense(fund.getFundName(), tfEventName.getText(),
                        Integer.parseInt(tfEventCost.getText())));
            }
            System.out.println("---" + fund + fund.getExpenseList().toString());
        }
        fundTableView.refresh();
        expenseTableView.refresh();
        clearText();
    }


    @FXML
    private TextField tfDelete;
    @FXML
    private Button btnDelete;

    public void setBtnDelete(ActionEvent actionEvent) {
        fundObservableList.removeIf(fund -> tfDelete.getText().equals(fund.getFundName()));
    }

    public void deleteFund(ActionEvent actionEvent) {
        ObservableList<Fund> allFund,SingleFund;
        allFund = fundTableView.getItems();
        SingleFund = fundTableView.getSelectionModel().getSelectedItems();
        SingleFund.forEach(allFund::remove);
    }


    @FXML
    private TextField tfFilter;

    public void setTfFilter() {
        FilteredList<Expense> expenseFilteredList = new FilteredList<>(expenseObservableList,b-> true);
        tfFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            expenseFilteredList.setPredicate(expense ->  {
                if(newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if(expense.getFundName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                else if(expense.getEventName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                else if(String.valueOf(expense.getEventCost()).contains(lowerCaseFilter))
                    return true;
                else
                    return false;
            });
        });
        SortedList<Expense> expenseSortedList = new SortedList<>(expenseFilteredList);
        expenseSortedList.comparatorProperty().bind(expenseTableView.comparatorProperty());
        expenseTableView.setItems(expenseSortedList);
    }

    @FXML
    private Button btnImport;
    @FXML
    private TextField tfImport;
    @FXML
    private TextField tfImport1;
    @FXML
    private Button btnExport;
    @FXML
    private TextField tfExport;
    @FXML
    private ChoiceBox<String> choiceBox1;
    @FXML
    private ChoiceBox<String> choiceBox2;
    @FXML
    private ChoiceBox<String> choiceBox3;

    public void setChoiceBox() {
        ObservableList<String> choice1 = FXCollections.observableArrayList();
        choice1.add("Expense List");
        choice1.add("Fund List");
        choiceBox1.setItems(choice1);
        ObservableList<String> choice2 = FXCollections.observableArrayList();
        choice2.add("To CSV File");
        choiceBox2.setItems(choice2);
        ObservableList<String> choice3 = FXCollections.observableArrayList();
        choice3.add(".csv");
        choiceBox3.setItems(choice3);
    }

    public void exportToFile(ActionEvent actionEvent) throws Exception {
        if(choiceBox1.getValue().equals("Expense List")
                && choiceBox2.getValue().equals("To CSV File")) {
            WriteFile.writeExpenseToStringFile(tfExport.getText(),expenseObservableList);
        } else if(choiceBox1.getValue().equals("Fund List")
                && choiceBox2.getValue().equals("To CSV File")) {
            WriteFile.writeFundsToStringFile(tfExport.getText(),fundObservableList);
        }
    }
    public void readFromStringCSV(ActionEvent actionEvent) throws Exception {
        if(choiceBox3.getValue().equals(".csv")) {
            fundObservableList = ReadFile.readFundCSVFile(tfImport.getText());
            expenseObservableList = ReadFile.readExpenseCSVFile(tfImport1.getText());
            for(Fund fund: fundObservableList) {
                for(Expense expense : expenseObservableList) {
                    if(fund.getFundName().equals(expense.getFundName())) {
                        fund.getExpenseList().add(expense);
                    }
                }
            }
            fundTableView.setItems(fundObservableList);
            fundTableView.refresh();
            expenseTableView.setItems(expenseObservableList);
            expenseTableView.refresh();
            selectionFundView();
            setTfFilter();
        }
    }

    @FXML
    private DatePicker fromDate;
    @FXML
    private DatePicker toDate;
    @FXML
    private Button btnTimeSearch;
    @FXML
    private Button btnTimeReset;

    public void setBtnTimeSearch(ActionEvent actionEvent) {
        ObservableList<Expense> timeFilterList = FXCollections.observableArrayList();
        for(Expense expense:expenseObservableList) {
            LocalDate expenseTime = LocalDate.parse(expense.getEventTime(),DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            if(expenseTime.compareTo(fromDate.getValue())>=0
                    && toDate.getValue().compareTo(expenseTime)>=0) {
                timeFilterList.add(expense);
            }
        }
        expenseTableView.setItems(timeFilterList);
        expenseTableView.refresh();
    }

    public void setBtnTimeReset(ActionEvent actionEvent) {
        expenseTableView.setItems(expenseObservableList);
        expenseTableView.refresh();
        setTfFilter();
    }

    @FXML
    private TextField fromCost;
    @FXML
    private TextField toCost;
    @FXML
    private Button btnCostSearch;
    @FXML
    private Button btnCostReset;

    public void setBtnCostSearch(ActionEvent actionEvent) {
        ObservableList<Expense> costFilterList = FXCollections.observableArrayList();
        for (Expense expense: expenseObservableList) {
            if(expense.getEventCost()>=Integer.parseInt(fromCost.getText())
                    && expense.getEventCost()<= Integer.parseInt(toCost.getText())) {
                costFilterList.add(expense);
            }
        }
        expenseTableView.setItems(costFilterList);
        expenseTableView.refresh();
    }

    public void setBtnCostReset(ActionEvent actionEvent) {
        expenseTableView.setItems(expenseObservableList);
        expenseTableView.refresh();
        setTfFilter();
    }

    @FXML
    private Button btnExit;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button btnLogout;

    public void setBtnExit(ActionEvent actionEvent) {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        System.out.println("You successfully exit");
        stage.close();
    }
    public void setBtnLogout(ActionEvent actionEvent) throws Exception {
//        Stage stage = (Stage) anchorPane.getScene().getWindow();
        SceneController.logout(actionEvent);
        System.out.println("You successfully logout");
    }
}