package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Model.Account;
import sample.Service.AccountManager;

import javax.swing.*;
import java.util.Objects;

public class SceneController {
    static Stage stage;
    static Scene scene;
    static Parent root;
    AccountManager accountManager = new AccountManager();

    public static void login(ActionEvent actionEvent) throws Exception {
        root = FXMLLoader.load(Objects.requireNonNull(SceneController.class.getResource("main.fxml")));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public static void logout(ActionEvent actionEvent) throws Exception {
        root = FXMLLoader.load(Objects.requireNonNull(SceneController.class.getResource("login.fxml")));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    private Button btnLogin;

    @FXML
    private Button btnSignUp;

    @FXML
    private TextField tfUserID;
    @FXML
    private PasswordField tfUserPass;
    @FXML
    private TextField tfDialog;

    public void signUp(ActionEvent actionEvent) {
        accountManager.signUp(tfUserID.getText(),tfUserPass.getText());
        System.out.println(" Successfully!" +
                " Account [ID:" + tfUserID.getText() + ",password:" +tfUserPass.getText()+"]");
        System.out.println(accountManager);
        tfDialog.setText("Successfully create account");
    }

    public void signIn(ActionEvent actionEvent) throws Exception {
        if (tfUserID.getText()!=null && tfUserPass.getText()!=null) {
            for (Account account: accountManager.getAccountList())
                if(tfUserID.getText().equals(account.getID())
                        && tfUserPass.getText().equals(account.getPassword())) {
                    login(actionEvent);
                } else {
                    tfDialog.setText("Wrong ID or Password");
                }
        } else if(tfUserID.getText()==null || tfUserPass.getText() ==null) {
            tfDialog.setText("Please fill up  a blank input");
        }
    }
}
