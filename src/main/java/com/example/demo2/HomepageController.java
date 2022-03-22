package com.example.demo2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;


public class HomepageController implements Initializable {
    @FXML
    private Label homepageLabel;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        homepageLabel.setText("Welcome!");
    }
}
