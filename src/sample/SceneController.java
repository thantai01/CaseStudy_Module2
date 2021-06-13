package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.Objects;

public class SceneController {
    static Stage stage;
    static Scene scene;
    static Parent root;

    public static void switchToScene1(ActionEvent actionEvent) throws Exception {
        root = FXMLLoader.load(Objects.requireNonNull(SceneController.class.getResource("main.fxml")));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public static void switchToSceneLogin(ActionEvent actionEvent) throws Exception {
        root = FXMLLoader.load(Objects.requireNonNull(SceneController.class.getResource("login.fxml")));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
