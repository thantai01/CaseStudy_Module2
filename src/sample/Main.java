package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.FileIO.ReadFile;
import sample.Service.AccountManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Mơ nì cờn trô lơ");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        AccountManager startManager = new AccountManager();
        startManager = ReadFile.readAccountList("C:\\Users\\84936\\Desktop\\New folder\\accountList.txt");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
