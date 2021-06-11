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
    private TextField tfExpenseDestination;

    @FXML
    private TextField tfNewFundName;

    @FXML
    private Button btnCreateFund;

    @FXML
    private TextField tfDeposited;

    @FXML
    private TextField tfFundDestination;

    @FXML
    private Button btnDeposited;

    @FXML
    private Label lbUserName;

    @FXML
    private TableColumn<Button,Fund> colBtnDelete;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fundTableView.setEditable(true);

        colFundName.setCellValueFactory(new PropertyValueFactory<>("fundName"));
        colFundName.setEditable(true);
        colFundName.setCellFactory(TextFieldTableCell.<Fund>forTableColumn());
        colFundName.setOnEditCommit((TableColumn.CellEditEvent<Fund, String> event) -> {
            TablePosition<Fund, String> pos = event.getTablePosition();
            String newFundName = event.getNewValue();
            int row = pos.getRow();
            Fund fund = event.getTableView().getItems().get(row);
            fund.setFundName(newFundName);
        });

        colCreatedDate.setCellValueFactory(new PropertyValueFactory<>("createdTime"));
        colBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        colTotalSpending.setCellValueFactory(new PropertyValueFactory<>("totalSpending"));
        colTotalDeposited.setCellValueFactory(new PropertyValueFactory<>("totalDeposited"));
        colLastDeposited.setCellValueFactory(new PropertyValueFactory<>("deposited"));
        colLastDepositedTime.setCellValueFactory(new PropertyValueFactory<>("lastRechargeDate"));

        fundTableView.setItems(fundObservableList);
        fundTableView.setEditable(true);

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
            if(funds.getFundName().equals(tfFundDestination.getText())) {
                int last = funds.getDeposited();
                funds.setDeposited(Integer.parseInt(tfDeposited.getText()));
                funds.setTotalDeposited(funds.getDeposited()+last);
                funds.setBalance(+ funds.getTotalDeposited() - funds.getTotalSpending());
                funds.setLastRechargeDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            }
        }
        fundTableView.refresh();
    }

    @FXML
    private TextArea taPrint;
    public void setBtnAddExpenseToFund(ActionEvent actionEvent) {
        for (Fund funds:fundObservableList) {
            if(funds.getFundName().equals(tfExpenseDestination.getText())) {
                funds.getExpenseList().add(
                        new Expense(tfEventName.getText(),Integer.parseInt(tfEventCost.getText())));
            }
            System.out.println(funds + "\n");
        }
    }


}