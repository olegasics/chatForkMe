package ru.forksme.client;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import ru.forksme.client.sample.Controller;
import ru.forksme.client.sample.User;
import ru.forksme.network.TCPConnection;
import ru.forksme.network.TCPConnectionListener;
import ru.forksme.serverok.ChatServer;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMain implements TCPConnectionListener {



User user = new User();
Controller controller = new Controller();



    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView addDocumentsInChatButton;

    @FXML
    private TextArea textAreaDialog;

    @FXML
    private TextField textFileldNullMessageInDialog1;

    @FXML
    private TextField writeMessageInDialog1 = new TextField();

    @FXML
    private Button sendMessageButton1;

    @FXML
    private ImageView addDocumentsInChatButton1;

    @FXML
    private ImageView addSmileInMessageButton1;

    @FXML
    private ImageView sendAudioMessageButton1;

    @FXML
    private TextField textFieldNickNameClient;

    @FXML
    private TextField textFieldLastMessageInDialog;

    @FXML
    private TextField searchMessage;

    @FXML
    private ImageView menuButton;

    @FXML
    private TextField textFielfNickNameClientInDialog;

    @FXML
    private TextField textFiledLastOnlineTime;


    /*@FXML
    private VBox labelDialog;



    @FXML
    private javafx.scene.control.Label labelDialog = new javafx.scene.control.Label();

    @FXML
    private javafx.scene.control.Label chatMessage = new javafx.scene.control.Label();
*/
    @FXML
    private javafx.scene.control.TextArea  labelDialog = new javafx.scene.control.TextArea();


    @FXML
    private TextField test = new TextField();

    @FXML TextField textFieldText = new TextField();

    private TCPConnection tcpConnection;

    private static final String IP_ADDR = "109.234.37.174";
    private static final int PORT = 8189;



    @FXML
    void initialize() {
        //ChatServer chatServer = null;
        sendMessageButton1.setOnAction(event -> {
           // StringBuilder msg = new StringBuilder();
            String msg = writeMessageInDialog1.getText();

            // msg.append("\r\n");
            if(msg.equals("")) return;
            else {
                printMsg("Я: " + msg);
                tcpConnection.sendString(controller.getNickname() + " : " + msg);
                writeMessageInDialog1.setText(null);

            }
        });

        try {
            tcpConnection = new TCPConnection(this, IP_ADDR, PORT);
        } catch (IOException e ) {
            printMsg("Connection Exception: " + e);
        }
    }

    private synchronized void printMsg(String msg) {
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                ChatServer chatServer = new ChatServer(msg);
               // test.appendText(msg + "\n");
                textFieldNickNameClient.setText(user.getUserName());
            labelDialog.appendText(msg + "\n");
               // chatServer.sendToAllConnections(msg);
                //textFieldText.appendText(msg + "\r\n");

            }
        });
    }

    @Override
    public void onConnectionReady(TCPConnection tcpConnection) {
        printMsg("Connection ready...");

    }

    @Override
    public void onReceiveString(TCPConnection tcpConnection, String value) {
        printMsg(value);
        writeMessageInDialog1.getText();

    }

    @Override
    public void onDisconnect(TCPConnection tcpConnection) {
        printMsg("Connection close...");

    }

    @Override
    public void onException(TCPConnection tcpConnection, Exception e) {
        printMsg("Connection Exception: " + e);
    }
}

