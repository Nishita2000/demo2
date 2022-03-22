package com.example.demo2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ChoiceBox<String> choiceBox;
    private Stage stage;
    private final String[] option = {"Teacher", "Student"};

    @FXML
    void signUpButtonOnAction(ActionEvent event) throws IOException {
        String username = this.usernameField.getText();
        String password = this.passwordField.getText();
        String role = this.choiceBox.getValue();
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDb = connectNow.getConnection();

        try {
            PreparedStatement stmt = connectDb.prepareStatement("insert into useraccount(Username,Password,Role) values(?,?,?);");
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, role);
            int status = stmt.executeUpdate();
            System.out.println("Registered Successfully");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 568, 400);
            this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (SQLException var11) {
            System.out.println("Error while connecting to the database.Exception code: " + var11);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox.getItems().addAll(option);
    }
}