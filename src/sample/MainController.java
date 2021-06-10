package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Model.Account;
import sample.Model.Fund;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    ObservableList<Fund> fundObservableList = FXCollections.observableArrayList();

    @FXML
    private TableView<Fund> fundTableView;

    @FXML
    private TableColumn<Fund, String> colFundName;

    @FXML
    private TableColumn<Fund, String> colCreatedDate;

    @FXML
    private TableColumn<Fund, Integer> colBalance;

    @FXML
    private TableColumn<Fund, Integer> colTotalSpending;

    @FXML
    private TableColumn<Fund, String> colLastRecharge;

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

    public void setBtnAddToFund(Account account, String fundName) {
        account.getFundList().add(new Fund(fundName));
        fundObservableList.add(new Fund(fundName));

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colFundName.setCellValueFactory(new PropertyValueFactory<>("fundName"));
        colCreatedDate.setCellValueFactory(new PropertyValueFactory<>("createdTime"));
        colBalance.setCellValueFactory(new PropertyValueFactory<>("fundBalance"));
        colTotalSpending.setCellValueFactory(new PropertyValueFactory<>("totalSpending"));
        colLastRecharge.setCellValueFactory(new PropertyValueFactory<>("lastRechargeDate"));
        fundTableView.setItems(fundObservableList);
    }
}