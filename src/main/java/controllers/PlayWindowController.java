package controllers;

import Data.Circle;
import Data.Point;
import Data.WindowSize;
import Main.Main;
import Modes.BasicSlide;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PlayWindowController {
    private Main main;
    private Stage stage;
    public WindowSize windowSize;
    private ArrayList<Point> arrayListPoints = new ArrayList<>();
    private GraphicsContext graphicsContext;

    @FXML private AnchorPane anchorPane;
    @FXML private Button buttonMinimalize;
    @FXML private Button buttonFullscreen;
    @FXML private Button buttonExit;
    @FXML private Canvas canvas;
    @FXML private Button buttonStart;
    @FXML private Button buttonCancel;

    public void initialize(){
        anchorPane.setOnKeyPressed(event -> {if(event.isControlDown()) main.setCtrlDown(true);});
        anchorPane.setOnKeyReleased(event -> {if(!event.isControlDown()) main.setCtrlDown(false);});
        anchorPane.setOnMousePressed(event -> {
            if(main.isCtrlDown()) {
                main.setxOffset(event.getSceneX());
                main.setyOffset(event.getSceneY());
            }
        });
        anchorPane.setOnMouseDragged(event -> {
            if(main.isCtrlDown()) {
                stage.setX(event.getScreenX() - main.getxOffset());
                stage.setY(event.getScreenY() - main.getyOffset());
            }
        });
        anchorPane.setOnMouseMoved(event -> {
            arrayListPoints.add(new Point(event.getX() - 50, event.getY() - 50));
        });

        graphicsContext = canvas.getGraphicsContext2D();
    }

    public void myInitialize(Main main){
        this.main = main;
        this.stage = main.getStage();
        this.windowSize = main.getWindowSize();
    }

    public void resizeWindow(double windowWidth, double windowHeight){
        windowSize.setWidth(windowWidth);
        windowSize.setHeight(windowHeight);
        anchorPane.setPrefSize(windowWidth, windowHeight);

        double fontSize = 5 + (windowHeight + windowWidth) / 150;
        double padW30 = windowWidth / 34.13;
        double padW50 = windowWidth / 20.48;
        double padW100 = windowWidth / 10.24;
        double padW150 = windowWidth / 6.827;
        double padH15 = windowHeight / 38.4;
        double padH25 = windowHeight / 23.04;
        double padH30 = windowHeight / 19.2;
        double padH50 = windowHeight / 11.52;
        double flagSizes = windowWidth / 16;
        double tableW = windowWidth / 2.56;
        double tableH = windowHeight / 1.92;

        buttonExit.setLayoutX(windowSize.getWidth() - 40);
        buttonExit.setLayoutY(10);
        buttonFullscreen.setLayoutX(windowSize.getWidth() - 80);
        buttonFullscreen.setLayoutY(10);
        buttonMinimalize.setLayoutX(windowSize.getWidth() - 120);
        buttonMinimalize.setLayoutY(10);

        canvas.setWidth(windowSize.getWidth() - padW100 - padW100);
        canvas.setHeight(windowSize.getHeight() - 100);

        buttonStart.setPrefSize(padW100 + padW30, padH30);
        buttonStart.setLayoutX(windowWidth - padW150);
        buttonStart.setLayoutY(windowHeight - padH50 - padH30 - padH30);
        buttonCancel.setPrefSize(padW100 + padW30, padH30);
        buttonCancel.setLayoutX(windowWidth - padW150);
        buttonCancel.setLayoutY(windowHeight - padH30 - padH30);

        anchorPane.setOnMouseMoved(event -> {
            arrayListPoints.add(new Point(event.getX() - 50, event.getY() - 50));
        });

    }

    public void actionExit(){
        stage.close();
        System.exit(0);
    }

    public void actionMinimalize(){
        stage.setIconified(true);
    }

    public void actionCancel(){
        main.changeScene();
    }

    public void actionStart(){
        BasicSlide basicSlide = new BasicSlide((int)canvas.getWidth(), (int)canvas.getHeight(), graphicsContext);
        anchorPane.setOnMouseClicked(event -> basicSlide.checkIfHit(event.getX(), event.getY()));
    }
}
