package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class SceneController {
    Stage stage;
    Scene scene;
    Parent root;

    public void switchToScene1(ActionEvent actionEvent) throws Exception {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sceneToSwitch.fxml")));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
