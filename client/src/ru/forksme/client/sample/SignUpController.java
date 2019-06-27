package ru.forksme.client.sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField signUpPswUser;

    @FXML
    private TextField signUpLoginUser;

    @FXML
    private TextField signUpNameUser;

    @FXML
    private TextField signUpLastNameUser;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField signUpCountry;

    @FXML
    private CheckBox signUpChecBoxMale;

    @FXML
    private CheckBox signUpChecBoxFemale;

    @FXML
    private TextField signUpPhone;

    @FXML
    private Button authButton;

    @FXML
    private Button backInAuth;

    @FXML
    void initialize() {
        backInAuth.setOnAction(event -> {

            openNewScene("/ru/forksme/client/sample/sample.fxml");
        });

        signUpButton.setOnAction(event -> {

            SignUpNewUser();
            

        });

    }

    private void SignUpNewUser() {
        DatabaseHandler dbHandler = new DatabaseHandler();

        String firstName = signUpNameUser.getText();
        String lastName = signUpLastNameUser.getText();
        String loginUser = signUpLoginUser.getText();
        String passUser = signUpPswUser.getText();
        String gender = "";
        String countryUser = signUpCountry.getText();
        String phoneUser = signUpPhone.getText();
        if(signUpChecBoxMale.isSelected()) {
            gender = "Мужской";
        } else gender = "Женский";

        User user = new User(firstName, lastName, loginUser, passUser, gender, countryUser, phoneUser);

        dbHandler.signUpUser(user);
    }

    public void openNewScene(String window) {

        signUpButton.getScene().getWindow().hide();

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

