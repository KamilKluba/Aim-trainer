package controllers;

import Main.Main;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PlayWindowController {
    private Main main;
    private Stage stage;
    private double windowWidth;
    private double windowHeigth;

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
        AnchorPane ap = ((AnchorPane)main.scenePlayWindow.getRoot());

        final Canvas canvas = new Canvas(750,500);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.WHITE);
        gc.fillRect(0,0,750,500);
        gc.setFill(Color.BLACK);
        gc.fillOval(250, 250, 1,1);
        ap.getChildren().add(canvas);
        ap.setOnMouseMoved(event -> System.out.println(event.getX() + " " + event.getY()));
    }

    public void setSizes(double windowWidth, double windowHeigth){
        this.windowWidth = windowWidth;
        this.windowHeigth = windowHeigth;
        placeButtons();
    }
}
