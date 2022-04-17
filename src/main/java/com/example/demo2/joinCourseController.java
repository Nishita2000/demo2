package com.example.demo2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class joinCourseController {
    private String username;
    private Stage stage;
    private String role;

    public void setUsername(String Username) {
        username = Username;
    }
    public void setRole(String Role)
    {
        role=Role;
    }
    @FXML
    private Button backButton;

    @FXML
    private PasswordField classCodeField;

    @FXML
    private Button joinButton;


    @FXML
    public void backButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Course.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        CourseController coursePage = fxmlLoader.getController();
        coursePage.setUsername(String.valueOf(username));
        coursePage.setRole(String.valueOf(role));
        this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        this.stage.setScene(scene);
        this.stage.show();
    }
}
