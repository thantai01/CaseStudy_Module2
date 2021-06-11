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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    private TableColumn<Fund,Button> colBtnDelete;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fundTableView.setEditable(true);
        setEditableFundName();
        loadFundTable();
        loadExpenseTable();

        tfFundDestination.setItems(fundObservableList);
        tfExpenseDestination.setItems(fundObservableList);


    }
    public void selectionFundView() {
        ObservableList<String> fundNameList = FXCollections.observableArrayList();
        for (Fund fund : fundObservableList) {
            fundNameList.add(fund.getFundName());
        }
    }

    public void loadFundTable() {
        colFundName.setCellValueFactory(new PropertyValueFactory<>("fundName"));
        colCreatedDate.setCellValueFactory(new PropertyValueFactory<>("createdTime"));
        colBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        colTotalSpending.setCellValueFactory(new PropertyValueFactory<>("totalSpending"));
        colTotalDeposited.setCellValueFactory(new PropertyValueFactory<>("totalDeposited"));
        colLastDeposited.setCellValueFactory(new PropertyValueFactory<>("deposited"));
        colLastDepositedTime.setCellValueFactory(new PropertyValueFactory<>("lastRechargeDate"));
        colBtnDelete.setCellValueFactory(new PropertyValueFactory<Fund,Button>("Delete"));
        fundTableView.setItems(fundObservableList);
    }

    @FXML
    private TableView<Expense> expenseTableView;

    @FXML
    private TableColumn<Fund, String> colOfFundName;

    @FXML
    private TableColumn<Expense,String> colEventTime;

    @FXML
    private TableColumn<Expense,String> colEventName;

    @FXML
    private TableColumn<Expense,Integer> colEventCost;


    public void loadExpenseTable() {
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
        for(Fund funds : fundObservableList) {
            if(funds.getFundName().equals(tfFundDestination.getItems().getClass().getName())) {
                int last = funds.getDeposited();
                funds.setDeposited(Integer.parseInt(tfDeposited.getText()));
                funds.setTotalDeposited(funds.getDeposited()+last);
                funds.setBalance(+ funds.getTotalDeposited() - funds.getTotalSpending());
                funds.setLastRechargeDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            }
        }
        fundTableView.refresh();
    }

    public void setBtnAddExpenseToFund(ActionEvent actionEvent) {
       expenseObservableList.add(new Expense(tfEventName.getText(),
               Integer.parseInt(tfEventCost.getText())));
    }
    public void setBtnAddToFund(ActionEvent actionEvent) {
        for(Fund fund: fundObservableList) {
            if(txtFundName.getText().equals(fund.getFundName())){
                fund.getExpenseList().add(new Expense(tfEventName.getText(),
                        Integer.parseInt(tfEventCost.getText())));
                expenseObservableList.add (new Expense(tfEventName.getText(),
                        Integer.parseInt(tfEventCost.getText())));
            }
            System.out.println(fund);
        }
//        System.out.println(fundObservableList + "\n");
    }

}