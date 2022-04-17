package com.example.demo2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.lambdaworks.crypto.SCryptUtil;

public class SignUpController implements Initializable {
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField emailField;
    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private Label signUpSuccessMsg;
    private Stage stage;
    private final String[] option = {"Teacher", "Student"};

    @FXML
    void signUpButtonOnAction(ActionEvent event) throws IOException {
        String name = this.nameField.getText();
        String username = this.usernameField.getText();
        String password = this.passwordField.getText();
        String generatedSecuredPasswordHash = SCryptUtil.scrypt(password, 16, 16, 16);
        String email = this.emailField.getText();
        String role = this.choiceBox.getValue();
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDb = connectNow.getConnection();

        try {
            PreparedStatement stmt = connectDb.prepareStatement("insert into useraccounts(Name,Username,Password,Email,Role) values(?,?,?,?,?);");
            stmt.setString(1, name);
            stmt.setString(2, username);
            stmt.setString(3, generatedSecuredPasswordHash);
            stmt.setString(4, email);
            stmt.setString(5, role);
            int status = stmt.executeUpdate();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 568, 400);
            this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (SQLException e) {
            signUpSuccessMsg.setText("Username already taken");
            System.out.println("Error while connecting to the database.Exception code: " + e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox.getItems().addAll(option);
    }
}
