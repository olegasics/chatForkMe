package ru.forksme.client.sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller {

    private String nickname;
    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }



    private double xOffset;
    private double yOffset;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField passwordFiled;

    @FXML
    private TextField loginFiled;

    @FXML
    private Button authButton;

    @FXML
    private Button loginButton;

    @FXML
    private AnchorPane titleBar;

    private String loginText;

    @FXML
    void initialize() {
        authButton.setOnAction(event -> {
             loginText = loginFiled.getText().trim();
            String passText = passwordFiled.getText().trim();
            setNickname(loginText);
            if(!loginText.isEmpty() && !passText.isEmpty()) {
                loginUser(loginText, passText);



            } else {
                System.out.println("Вы ввели пустое значение");
            }
        });

        loginButton.setOnAction(event -> {
            openNewScene("/ru/forksme/client/sample/SignUp.fxml");
        });

    }


    private void loginUser(String loginText, String passText) {
        DatabaseHandler dbHandler = new DatabaseHandler();

        User user = new User();

        user.setUserName(loginText);
        user.setUserPassword(passText);
        //setNickname(user.getUserName());
        ResultSet resultSet = dbHandler.getUser(user);

        int count = 0;
        try {
            while (resultSet.next()) {
                count++;

            }
        }catch (SQLException e ) {
            e.printStackTrace();
        }

        if(count>=1) {
            //openNewScene("/sample/app.fxml");
            openNewScene("/ru/forksme/client/clientWindow1.fxml");
        } else System.out.println("Ввели что-то не то");
    }

    public void openNewScene(String window) {

        authButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));

        try {
            loader.load();
        }catch (IOException e ) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();

    }
}


