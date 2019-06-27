package ru.forksme.client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.forksme.network.TCPConnection;
import ru.forksme.network.TCPConnectionListener;

import java.io.IOException;

public class ClientWindow  extends Application  {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("clientWindow1.fxml"));
        primaryStage.setTitle("People Forks");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);

       Platform.runLater(new Runnable() {
            @Override
            public void run() {
                new ClientWindow();
            }
        });


    }



}