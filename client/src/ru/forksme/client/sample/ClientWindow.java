package ru.forksme.client.sample;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientWindow extends Application  {

    /*
    private static final String IP_ADDR = "172.20.10.8";
    private static final int PORT = 8189;

    private TCPConnection tcpConnection;
*/


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
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


  /*
        textAreaDialog.setEditable(false);

        textAreaDialog.setWrapText(true);

        writeMessageInDialog1.setOnAction((EventHandler<ActionEvent>) this);

        textAreaDialog.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ENTER) {
                    String text = textAreaDialog.getText();
                }
            }
        });
*/