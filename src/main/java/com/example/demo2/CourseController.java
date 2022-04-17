package com.example.demo2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class CourseController {
    private String username;
    private Stage stage;
    private String role;

    public void setUsername(String Username) {
        username = Username;
    }
    public void setRole(String Role){
        role = Role;
        //System.out.println(role);
        if(role.equals("Teacher"))
        {
            addCourseButton.setVisible(true);
        }
        else
        {
            joinCourseButton.setVisible(true);
        }
    }

    @FXML
    private Button addCourseButton;

    @FXML
    private Button joinCourseButton;
    @FXML
    public void backButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Homepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        HomepageController homePage = fxmlLoader.getController();
        homePage.setUsername(username);
        homePage.setRole(role);
        this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        this.stage.setScene(scene);
        this.stage.show();
    }
    @FXML
    public void addButtonOnAction(ActionEvent event) throws  IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AddCourse.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        AddCourseController addCourseController = fxmlLoader.getController();
        addCourseController.setUsername(username);
        addCourseController.setRole(role);
        this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        this.stage.setScene(scene);
        this.stage.show();

    }

    @FXML
    public void joinButtonOnAction(ActionEvent event) throws  IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("JoinCourse.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        JoinCourseController joinCoursecontroller = fxmlLoader.getController();
        joinCoursecontroller.setUsername(username);
        joinCoursecontroller.setRole(role);
        this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        this.stage.setScene(scene);
        this.stage.show();

    }
}
