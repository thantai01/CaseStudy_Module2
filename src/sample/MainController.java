package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Model.Account;
import sample.Model.Fund;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    ObservableList<Fund> fundObservableList = FXCollections.observableArrayList();

    public MainController() {
        fundObservableList.add(new Fund("Daily Paid"));
        fundObservableList.add(new Fund("Study Paid"));
        fundObservableList.add(new Fund("Subsistence Paid"));
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
    private TableColumn<?,?> colTotalDeposited;

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

    public void setBtnCreateFund(ActionEvent actionEvent) {
        fundObservableList.add(new Fund(tfNewFundName.getText()));
    }

    public void setBtnDeposited(ActionEvent actionEvent) {
        for(Fund funds : fundObservableList) {
            if(funds.getFundName().equals(tfFundDestination.getText())) {
                funds.setBalance(Integer.parseInt(tfDeposited.getText()));
                funds.setLastRechargeDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            }
        }
        fundTableView.refresh();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colFundName.setCellValueFactory(new PropertyValueFactory<>("fundName"));
        colCreatedDate.setCellValueFactory(new PropertyValueFactory<>("createdTime"));
        colBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        colTotalSpending.setCellValueFactory(new PropertyValueFactory<>("totalSpending"));
        colTotalDeposited.setCellValueFactory(new PropertyValueFactory<>("totalDeposited"));
        colLastRecharge.setCellValueFactory(new PropertyValueFactory<>("lastRechargeDate"));
        fundTableView.setItems(fundObservableList);
        fundTableView.setEditable(true);
    }

    public void initialize(SortEvent<TableView<Fund>> tableViewSortEvent) {
        colFundName.setCellValueFactory(new PropertyValueFactory<>("fundName"));
        colCreatedDate.setCellValueFactory(new PropertyValueFactory<>("createdTime"));
        colBalance.setCellValueFactory(new PropertyValueFactory<>("fundBalance"));
        colTotalSpending.setCellValueFactory(new PropertyValueFactory<>("totalSpending"));
        colLastRecharge.setCellValueFactory(new PropertyValueFactory<>("lastRechargeDate"));
        fundTableView.setItems(fundObservableList);
    }



}