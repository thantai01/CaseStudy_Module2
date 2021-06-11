package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import sample.Model.Account;
import sample.Model.Expense;
import sample.Model.Fund;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    ObservableList<Fund> fundObservableList = FXCollections.observableArrayList();
    ObservableList<Expense> expenseObservableList = FXCollections.observableArrayList();

    public MainController() {
        fundObservableList.add(new Fund("Daily"));
        fundObservableList.add(new Fund("Study"));
        fundObservableList.add(new Fund("Subsistence"));

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
    private TextField txtFundName;

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectionFundView();
        fundTableView.setEditable(true);
        setEditableFundName();
        loadFundTable();
        loadExpenseTable();


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

    public void initialize(SortEvent<TableView<Fund>> tableViewSortEvent) {
//        colFundName.setCellValueFactory(new PropertyValueFactory<>("fundName"));
//        colCreatedDate.setCellValueFactory(new PropertyValueFactory<>("createdTime"));
//        colBalance.setCellValueFactory(new PropertyValueFactory<>("fundBalance"));
//        colTotalSpending.setCellValueFactory(new PropertyValueFactory<>("totalSpending"));
//        colTotalDeposited.setCellValueFactory(new PropertyValueFactory<>("totalDeposited"));
//        colLastDeposited.setCellValueFactory(new PropertyValueFactory<>("deposited"));
//        colLastDepositedTime.setCellValueFactory(new PropertyValueFactory<>("lastRechargeDate"));
//        fundTableView.setItems(fundObservableList);
    }

    public void setBtnCreateFund(ActionEvent actionEvent) {
        fundObservableList.add(new Fund(tfNewFundName.getText()));
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
    }

    public void setBtnAddToFund(ActionEvent actionEvent) {
        for (Fund fund : fundObservableList) {
            if (tfExpenseDestination.getValue().getFundName().equals(fund.getFundName())) {
                fund.getExpenseList().add(new Expense(fund.getFundName(), tfEventName.getText(),
                        Integer.parseInt(tfEventCost.getText())));
                expenseObservableList.add(new Expense(fund.getFundName(), tfEventName.getText(),
                        Integer.parseInt(tfEventCost.getText())));
            }
            System.out.println("---" + fund + fund.getExpenseList().toString());
        }
    }

    @FXML
    private TextField tfDelete;
    @FXML
    private Button btnDelete;

//    public void setBtnDelete(ActionEvent actionEvent) {
//        for(Fund fund: fundObservableList){
//            if(tfDelete.getText().equals(fund.getFundName())) {
//                fundObservableList.set()
//        }}
//    }

}