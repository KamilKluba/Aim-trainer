package controllers;

import Main.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PlayWindowController {
    private Main main;
    private Stage stage;
    double windowWidth;
    double windowHeigth;

    @FXML private Button buttonMinimalize;
    @FXML private Button buttonFullscreen;
    @FXML private Button buttonExit;

    public void initialize(){
    }

    private void placeButtons(){
        buttonExit.setLayoutX(windowWidth - 40);
        buttonExit.setLayoutY(10);
        buttonFullscreen.setLayoutX(windowWidth - 80);
        buttonFullscreen.setLayoutY(10);
        buttonMinimalize.setLayoutX(windowWidth - 120);
        buttonMinimalize.setLayoutY(10);
    }

    public void actionExit(){
        stage.close();
    }

    public void actionMinimalize(){
        stage.setIconified(true);
    }

    public void setStage(Stage stage, Main main) {
        this.stage = stage;
        this.main = main;
    }

    public void setSizes(double windowWidth, double windowHeigth){
        this.windowWidth = windowWidth;
        this.windowHeigth = windowHeigth;
        placeButtons();
    }
}
