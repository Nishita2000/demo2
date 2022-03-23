package com.example.demo2;

import com.lambdaworks.crypto.SCryptUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class ChangePassController {
    @FXML
    private TextField usernameText;
    @FXML
    private PasswordField currentPass;
    @FXML
    private PasswordField newPass;
    @FXML
    private PasswordField confirmPass;
    @FXML
    private Label myLabel;
    private Stage stage;
    private String username;

    @FXML
    public void updateButtonOnAction(ActionEvent event) throws IOException {
        if (!usernameText.getText().isBlank() && !currentPass.getText().isBlank() && !newPass.getText().isBlank() && !confirmPass.getText().isBlank()) {
            validateUpdate();
        } else {
            myLabel.setText("Please enter necessary information");
        }
    }

    void validateUpdate() {
        String username = this.usernameText.getText();
        String currentPassword = this.currentPass.getText();
        String newPassword = this.newPass.getText();
        String confirmPassword = this.confirmPass.getText();
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDb = connectNow.getConnection();

        String verifyLogin = "SELECT count(1),Password FROM useraccounts WHERE Username = '" + username + "'";
        try {
            Statement statement = connectDb.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                if ((queryResult.getInt(1) == 1) && (SCryptUtil.check(currentPassword, queryResult.getString(2))) && (newPassword.equals(confirmPassword))) {
                    PreparedStatement stmt = connectDb.prepareStatement("UPDATE useraccounts SET Password = ? WHERE Username = '" + username + "'");
                    String generatedSecuredPasswordHash = SCryptUtil.scrypt(newPassword, 16, 16, 16);
                    stmt.setString(1, generatedSecuredPasswordHash);
                    int status = stmt.executeUpdate();
                    myLabel.setText("Password Updated!");
                } else {
                    myLabel.setText("Invalid information!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void backButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Homepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        this.stage.setScene(scene);
        this.stage.show();
    }

    public void setUsername(String Username) {
        username = Username;
    }

}


