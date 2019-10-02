package controllers;

import Data.I18N;
import Data.ResizeValues;
import Data.WindowSize;
import Main.Main;
import Modes.Mode;
import Modes.Precision.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class PlayWindowController {
    private Main main;
    private Stage stage;
    private WindowSize windowSize;
    private String modeName;
    private Runnable start;
    private ExecutorService executor;
    private ArrayList<javafx.scene.Node> arrayListOptionsComponents = new ArrayList<>();
    private ArrayList<javafx.scene.Node> arrayListResultComponents = new ArrayList<>();
    private Mode chosenMode;
    private AtomicBoolean alive = new AtomicBoolean(false);

    @FXML private AnchorPane anchorPane;
    @FXML private Button buttonMinimalize;
    @FXML private Button buttonFullscreen;
    @FXML private Button buttonExit;
    @FXML private Canvas canvas;
    @FXML private Button buttonStart;
    @FXML private Button buttonCancel;
    @FXML private Label labelTimeLeft;
    @FXML private Slider sliderModeOptions1;
    @FXML private Label labelModeOptions1Info;
    @FXML private Label labelModeOptions1Value;
    @FXML private Slider sliderModeOptions2;
    @FXML private Label labelModeOptions2Info;
    @FXML private Label labelModeOptions2Value;
    @FXML private Slider sliderModeOptions3;
    @FXML private Label labelModeOptions3Info;
    @FXML private Label labelModeOptions3Value;
    @FXML private Label labelResult1Info;
    @FXML private Label labelResult1Value;
    @FXML private Label labelResult2Info;
    @FXML private Label labelResult2Value;
    @FXML private Label labelResult3Info;
    @FXML private Label labelResult3Value;
    @FXML private Label labelCountdown;

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
        executor = Executors.newFixedThreadPool(1);
        start = (() -> {
            int i = 60;
            while(i > 0 && alive.get()) {
                i--;
                int finalI = i;
                Platform.runLater(() -> labelTimeLeft.setText("" + finalI));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            clearMouseListeners();
            chosenMode.setAlive(false);
            for(javafx.scene.Node n : arrayListOptionsComponents) n.setDisable(false);
            buttonStart.setDisable(false);
        });
        arrayListOptionsComponents.add(sliderModeOptions1);
        arrayListOptionsComponents.add(labelModeOptions1Value);
        arrayListOptionsComponents.add(labelModeOptions1Info);
        arrayListOptionsComponents.add(sliderModeOptions2);
        arrayListOptionsComponents.add(labelModeOptions2Value);
        arrayListOptionsComponents.add(labelModeOptions2Info);
        arrayListOptionsComponents.add(sliderModeOptions3);
        arrayListOptionsComponents.add(labelModeOptions3Value);
        arrayListOptionsComponents.add(labelModeOptions3Info);
        arrayListResultComponents.add(labelResult1Info);
        arrayListResultComponents.add(labelResult1Value);
        arrayListResultComponents.add(labelResult2Info);
        arrayListResultComponents.add(labelResult2Value);
        arrayListResultComponents.add(labelResult3Info);
        arrayListResultComponents.add(labelResult3Value);
    }

    public void myInitialize(Main main){
        this.main = main;
        this.stage = main.getStage();
        this.windowSize = main.getWindowSize();
    }

    public void resizeWindow(double windowWidth, double windowHeight){
        ResizeValues resizeValues = new ResizeValues(windowWidth, windowHeight);

        windowSize.setWidth(windowWidth);
        windowSize.setHeight(windowHeight);
        anchorPane.setPrefSize(windowWidth, windowHeight);

        AnchorPane.setRightAnchor(buttonExit, resizeValues.getButtonExitPadW());
        AnchorPane.setTopAnchor(buttonExit, resizeValues.getButtonsBarPadH());
        AnchorPane.setRightAnchor(buttonFullscreen, resizeValues.getButtonMaximizePadW());
        AnchorPane.setTopAnchor(buttonFullscreen, resizeValues.getButtonsBarPadH());
        AnchorPane.setRightAnchor(buttonMinimalize, resizeValues.getButtonMinimizePadW());
        AnchorPane.setTopAnchor(buttonMinimalize, resizeValues.getButtonsBarPadH());

        canvas.setWidth(windowSize.getWidth() - resizeValues.getPadW150() - resizeValues.getPadW50());
        canvas.setHeight(windowSize.getHeight());

        buttonStart.setPrefSize(resizeValues.getPadW130(), resizeValues.getPadH30());
        buttonStart.setFont(new Font("Bank Gothic Medium BT", resizeValues.getFontSize()));
        AnchorPane.setRightAnchor(buttonStart, resizeValues.getPadW30());
        AnchorPane.setBottomAnchor(buttonStart, resizeValues.getPadH80());
        buttonCancel.setPrefSize(resizeValues.getPadW130(), resizeValues.getPadH30());
        buttonCancel.setFont(new Font("Bank Gothic Medium BT", resizeValues.getFontSize()));
        AnchorPane.setRightAnchor(buttonCancel, resizeValues.getPadW30());
        AnchorPane.setBottomAnchor(buttonCancel, resizeValues.getPadH30());

        AnchorPane.setRightAnchor(labelTimeLeft, resizeValues.getPadW50());
        AnchorPane.setTopAnchor(labelTimeLeft, resizeValues.getPadH50());

        AnchorPane.setLeftAnchor(labelCountdown, resizeValues.getPadW360());
        AnchorPane.setTopAnchor(labelCountdown, resizeValues.getPadH240());

        AnchorPane.setTopAnchor(sliderModeOptions1, resizeValues.getPadH130());
        AnchorPane.setRightAnchor(sliderModeOptions1, resizeValues.getPadW30());
        sliderModeOptions1.setPrefSize(resizeValues.getPadW150(), resizeValues.getPadH15());
        labelModeOptions1Info.setFont(new Font("Bank Gothic Medium BT", resizeValues.getFontSize() - 4));
        AnchorPane.setTopAnchor(labelModeOptions1Info, resizeValues.getPadH90());
        AnchorPane.setRightAnchor(labelModeOptions1Info,resizeValues.getPadW30());
        labelModeOptions1Value.setFont(new Font("Bank Gothic Medium BT", resizeValues.getFontSize() - 4));
        AnchorPane.setTopAnchor(labelModeOptions1Value, resizeValues.getPadH110());
        AnchorPane.setRightAnchor(labelModeOptions1Value, resizeValues.getPadW30());

        AnchorPane.setTopAnchor(sliderModeOptions2, resizeValues.getPadH210());
        AnchorPane.setRightAnchor(sliderModeOptions2, resizeValues.getPadW30());
        sliderModeOptions2.setPrefSize(resizeValues.getPadW150(), resizeValues.getPadH15());
        labelModeOptions2Info.setFont(new Font("Bank Gothic Medium BT", resizeValues.getFontSize() - 4));
        AnchorPane.setTopAnchor(labelModeOptions2Info, resizeValues.getPadH170());
        AnchorPane.setRightAnchor(labelModeOptions2Info,resizeValues.getPadW30());
        labelModeOptions2Value.setFont(new Font("Bank Gothic Medium BT", resizeValues.getFontSize() - 4));
        AnchorPane.setTopAnchor(labelModeOptions2Value, resizeValues.getPadH190());
        AnchorPane.setRightAnchor(labelModeOptions2Value, resizeValues.getPadW30());

        AnchorPane.setTopAnchor(sliderModeOptions3, resizeValues.getPadH290());
        AnchorPane.setRightAnchor(sliderModeOptions3, resizeValues.getPadW30());
        sliderModeOptions3.setPrefSize(resizeValues.getPadW150(), resizeValues.getPadH15());
        labelModeOptions1Info.setFont(new Font("Bank Gothic Medium BT", resizeValues.getFontSize() - 4));
        AnchorPane.setTopAnchor(labelModeOptions3Info, resizeValues.getPadH250());
        AnchorPane.setRightAnchor(labelModeOptions3Info,resizeValues.getPadW30());
        labelModeOptions3Value.setFont(new Font("Bank Gothic Medium BT", resizeValues.getFontSize() - 4));
        AnchorPane.setTopAnchor(labelModeOptions3Value, resizeValues.getPadH270());
        AnchorPane.setRightAnchor(labelModeOptions3Value, resizeValues.getPadW30());

        AnchorPane.setBottomAnchor(labelResult1Info,  resizeValues.getPadH130());
        AnchorPane.setRightAnchor(labelResult1Info, resizeValues.getPadW90());
        labelResult1Info.setFont(new Font("Bank Gothic Medium BT", resizeValues.getFontSize() - 4));
        AnchorPane.setBottomAnchor(labelResult1Value,  resizeValues.getPadH130());
        AnchorPane.setRightAnchor(labelResult1Value, resizeValues.getPadW30());
        labelResult1Value.setFont(new Font("Bank Gothic Medium BT", resizeValues.getFontSize() - 4));
        AnchorPane.setBottomAnchor(labelResult2Info,  resizeValues.getPadH150());
        AnchorPane.setRightAnchor(labelResult2Info, resizeValues.getPadW90());
        labelResult2Info.setFont(new Font("Bank Gothic Medium BT", resizeValues.getFontSize() - 4));
        AnchorPane.setBottomAnchor(labelResult2Value,  resizeValues.getPadH150());
        AnchorPane.setRightAnchor(labelResult2Value, resizeValues.getPadW30());
        labelResult2Value.setFont(new Font("Bank Gothic Medium BT", resizeValues.getFontSize() - 4));
        AnchorPane.setBottomAnchor(labelResult3Info,  resizeValues.getPadH170());
        AnchorPane.setRightAnchor(labelResult3Info, resizeValues.getPadW90());
        labelResult3Info.setFont(new Font("Bank Gothic Medium BT", resizeValues.getFontSize() - 4));
        AnchorPane.setBottomAnchor(labelResult3Value,  resizeValues.getPadH170());
        AnchorPane.setRightAnchor(labelResult3Value, resizeValues.getPadW30());
        labelResult3Value.setFont(new Font("Bank Gothic Medium BT", resizeValues.getFontSize() - 4));
    }

    public void actionExit(){
        stage.close();
        System.exit(0);
    }

    public void actionMinimize(){
        stage.setIconified(true);
    }

    public void actionCancel(){
        if(!alive.get()){
            main.changeScene();
        }
        if(chosenMode != null) {
            chosenMode.setAlive(false);
            alive.set(false);
        }

        for(javafx.scene.Node n : arrayListOptionsComponents){
            n.setDisable(false);
        }

        clearMouseListeners();
        buttonStart.setDisable(false);
    }

    public void actionStart(){
        new Thread(() -> {
            for (javafx.scene.Node n : arrayListOptionsComponents) {
                n.setDisable(true);
            }
            buttonStart.setDisable(true);
            buttonCancel.setDisable(true);

            labelCountdown.setVisible(true);
            int i = 3;
            while (i > 0) {
                int finalI = i;
                Platform.runLater(() -> labelCountdown.setText("" + finalI));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i--;
            }
            labelCountdown.setVisible(false);

            clearMouseListeners();

            switch (modeName) {
                case "buttonPrecision1":
                    chosenMode = new BasicAiming(this, sliderModeOptions1.getValue());
                    anchorPane.setOnMousePressed(event -> ((BasicAiming) chosenMode).checkIfHit(event.getX(), event.getY()));
                    break;
                case "buttonPrecision2":
                    chosenMode = new AimingOnTime(this, sliderModeOptions1.getValue(), sliderModeOptions2.getValue());
                    anchorPane.setOnMousePressed(event -> ((AimingOnTime) chosenMode).checkIfHit(event.getX(), event.getY()));
                    break;
                case "buttonPrecision3":
                    chosenMode = new GotYa(this, sliderModeOptions1.getValue(), sliderModeOptions2.getValue() * 10, sliderModeOptions3.getValue());
                    anchorPane.setOnMouseMoved(event -> ((GotYa) chosenMode).checkIfInside(event.getX(), event.getY()));
                    anchorPane.setOnMouseDragged(event -> ((GotYa) chosenMode).checkIfInside(-100, -100));
                    break;
                case "buttonPrecision4":
                    chosenMode = new AnotherOneBitesTheDust(this, sliderModeOptions1.getValue());
                    anchorPane.setOnMousePressed(event -> ((AnotherOneBitesTheDust) chosenMode).checkIfHit(event.getX(), event.getY()));
                    break;
                case "buttonPrecision5":
                    chosenMode = new Sniper(this, sliderModeOptions1.getValue(), sliderModeOptions2.getValue());
                    anchorPane.setOnMousePressed(event -> ((Sniper) chosenMode).checkIfHit(event.getX(), event.getY()));
                    break;
                case "buttonPrecision6":
                    chosenMode = new StayWithMe(this, sliderModeOptions1.getValue(), (int) sliderModeOptions2.getValue());
                    anchorPane.setOnMouseDragged(event -> ((StayWithMe) chosenMode).checkIfCircleIsOnLine(event.getX(), event.getY()));
                    anchorPane.setOnMousePressed(event -> ((StayWithMe) chosenMode).saveMousePosition(event.getX(), event.getY()));
                    anchorPane.setOnMouseReleased(event -> ((StayWithMe) chosenMode).resetCirclePosition());
                    break;
                case "buttonPrecision7":
                    chosenMode = new DontTouchMe(this, sliderModeOptions1.getValue(), (int) sliderModeOptions2.getValue());
                    anchorPane.setOnMouseDragged(event -> ((DontTouchMe) chosenMode).moveCircle(event.getX(), event.getY()));
                    anchorPane.setOnMousePressed(event -> ((DontTouchMe) chosenMode).saveMousePosition(event.getX(), event.getY()));
                    anchorPane.setOnMouseReleased(event -> ((DontTouchMe) chosenMode).resetCirclePosition());
                    break;
            }

            buttonCancel.setDisable(false);
            alive.set(true);
            executor.submit(start);
        }).start();
    }

    private void clearMouseListeners(){
        anchorPane.setOnMouseMoved(null);
        anchorPane.setOnMouseClicked(null);
        anchorPane.setOnMouseDragged(null);
        anchorPane.setOnMousePressed(null);
        anchorPane.setOnMouseReleased(null);

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
    }

    void prepareMode(String modeName){
        this.modeName = modeName;
        for(javafx.scene.Node n : arrayListOptionsComponents)
            n.setVisible(false);
        for(javafx.scene.Node n : arrayListResultComponents)
            n.setVisible(false);

        switch (modeName) {
            case "buttonPrecision1":
                //slider1 is responsible for circle spawn time
                sliderModeOptions1.setMin(500);
                sliderModeOptions1.setMax(1500);
                sliderModeOptions1.setValue(1000);
                sliderModeOptions1.setMajorTickUnit(250);
                sliderModeOptions1.setMinorTickCount(100);
                sliderModeOptions1.valueProperty().addListener((obs, oldValue, newValue) -> labelModeOptions1Value.setText("" + (newValue.intValue() - newValue.intValue() % 100)));
                sliderModeOptions1.setVisible(true);
                labelModeOptions1Value.setText("1000");
                labelModeOptions1Value.setVisible(true);
                labelModeOptions1Info.textProperty().bind(I18N.createStringBinding("labelCircleSpawnTimeInfo"));
                labelModeOptions1Info.setVisible(true);
                //labelR1 is responsible for total amount of circles
                labelResult1Info.textProperty().bind(I18N.createStringBinding("labelResultTotalCircles"));
                labelResult1Info.setVisible(true);
                labelResult1Value.setText("0");
                labelResult1Value.setVisible(true);
                //labelR2 is responsible for total missed hits
                labelResult2Info.textProperty().bind(I18N.createStringBinding("labelResultMissedHits"));
                labelResult2Info.setVisible(true);
                labelResult2Value.setText("0");
                labelResult2Value.setVisible(true);
                break;
            case "buttonPrecision2":
                //slider1 is responsible for circle spawn time
                sliderModeOptions1.setMin(500);
                sliderModeOptions1.setMax(1500);
                sliderModeOptions1.setValue(1000);
                sliderModeOptions1.setMajorTickUnit(250);
                sliderModeOptions1.valueProperty().addListener((obs, oldValue, newValue) -> labelModeOptions1Value.setText("" + (newValue.intValue() - newValue.intValue() % 100)));
                sliderModeOptions1.setVisible(true);
                labelModeOptions1Value.setText("1000");
                labelModeOptions1Value.setVisible(true);
                labelModeOptions1Info.textProperty().bind(I18N.createStringBinding("labelCircleSpawnTimeInfo"));
                labelModeOptions1Info.setVisible(true);
                //slider2 is responsible for circle life time
                sliderModeOptions2.setMin(1000);
                sliderModeOptions2.setMax(3000);
                sliderModeOptions2.setValue(2000);
                sliderModeOptions2.setMajorTickUnit(500);
                sliderModeOptions2.valueProperty().addListener((obs, oldValue, newValue) -> labelModeOptions2Value.setText("" + (newValue.intValue() - newValue.intValue() % 100)));
                sliderModeOptions2.setVisible(true);
                labelModeOptions2Value.setText("2000");
                labelModeOptions2Value.setVisible(true);
                labelModeOptions2Info.textProperty().bind(I18N.createStringBinding("labelCircleLifeTimeInfo"));
                labelModeOptions2Info.setVisible(true);
                //labelR1 is responsible for total amount of circles
                labelResult1Info.textProperty().bind(I18N.createStringBinding("labelResultTotalCircles"));
                labelResult1Info.setVisible(true);
                labelResult1Value.setText("0");
                labelResult1Value.setVisible(true);
                //labelR1 is responsible for amount of hit circles
                labelResult2Info.textProperty().bind(I18N.createStringBinding("labelResultHitCircles"));
                labelResult2Info.setVisible(true);
                labelResult2Value.setText("0");
                labelResult2Value.setVisible(true);
                //labelR3 is responsible for total missed hits
                labelResult3Info.textProperty().bind(I18N.createStringBinding("labelResultMissedHits"));
                labelResult3Info.setVisible(true);
                labelResult3Value.setText("0");
                labelResult3Value.setVisible(true);
                break;
            case "buttonPrecision3":
                //slider1 is responsible for circle speed
                sliderModeOptions1.setMin(1);
                sliderModeOptions1.setMax(5);
                sliderModeOptions1.setValue(3);
                sliderModeOptions1.setMajorTickUnit(1);
                sliderModeOptions1.valueProperty().addListener((obs, oldValue, newValue) -> labelModeOptions1Value.setText("" + newValue.intValue()));
                sliderModeOptions1.setVisible(true);
                labelModeOptions1Value.setText("3");
                labelModeOptions1Value.setVisible(true);
                labelModeOptions1Info.textProperty().bind(I18N.createStringBinding("labelCircleSpeedInfo"));
                labelModeOptions1Info.setVisible(true);
                //slider2 is responsible for circle agility
                sliderModeOptions2.setMin(1);
                sliderModeOptions2.setMax(9);
                sliderModeOptions2.setValue(5);
                sliderModeOptions2.setMajorTickUnit(1);
                sliderModeOptions2.valueProperty().addListener((obs, oldValue, newValue) -> labelModeOptions2Value.setText("" + newValue.intValue()));
                sliderModeOptions2.setVisible(true);
                labelModeOptions2Value.setText("5");
                labelModeOptions2Value.setVisible(true);
                labelModeOptions2Info.textProperty().bind(I18N.createStringBinding("labelCircleAgilityInfo"));
                labelModeOptions2Info.setVisible(true);
                //slider3 is responsible for circle size
                sliderModeOptions3.setMin(20);
                sliderModeOptions3.setMax(60);
                sliderModeOptions3.setValue(30);
                sliderModeOptions3.setMajorTickUnit(5);
                sliderModeOptions3.valueProperty().addListener((obs, oldValue, newValue) -> labelModeOptions3Value.setText("" + (newValue.intValue() - newValue.intValue() % 5)));
                sliderModeOptions3.setVisible(true);
                labelModeOptions3Value.setText("30");
                labelModeOptions3Value.setVisible(true);
                labelModeOptions3Info.textProperty().bind(I18N.createStringBinding("labelCircleSizeInfo"));
                labelModeOptions3Info.setVisible(true);
                //labelR1 is responsible for total amount of points;
                labelResult1Info.textProperty().bind(I18N.createStringBinding("labelResultTotalPoints"));
                labelResult1Info.setVisible(true);
                labelResult1Value.setText("0");
                labelResult1Value.setVisible(true);
                //labelR2 is responsible for the highest single gain;
                labelResult2Info.textProperty().bind(I18N.createStringBinding("labelResultHighestSingleGain"));
                labelResult2Info.setVisible(true);
                labelResult2Value.setText("0");
                labelResult2Value.setVisible(true);
                break;
            case "buttonPrecision4":
                //slider1 is responsible for circle size
                sliderModeOptions1.setMin(10);
                sliderModeOptions1.setMax(50);
                sliderModeOptions1.setValue(30);
                sliderModeOptions1.setMajorTickUnit(5);
                sliderModeOptions1.valueProperty().addListener((obs, oldValue, newValue) -> labelModeOptions1Value.setText("" + newValue.intValue()));
                sliderModeOptions1.setVisible(true);
                labelModeOptions1Value.setText("30");
                labelModeOptions1Value.setVisible(true);
                labelModeOptions1Info.textProperty().bind(I18N.createStringBinding("labelCircleSizeInfo"));
                labelModeOptions1Info.setVisible(true);
                //labelR1 is responsible for the amount of hit circles;
                labelResult1Info.textProperty().bind(I18N.createStringBinding("labelResultHitCircles"));
                labelResult1Info.setVisible(true);
                labelResult1Value.setText("0");
                labelResult1Value.setVisible(true);
                //labelR2 is responsible for total missed hits
                labelResult2Info.textProperty().bind(I18N.createStringBinding("labelResultMissedHits"));
                labelResult2Info.setVisible(true);
                labelResult2Value.setText("0");
                labelResult2Value.setVisible(true);
                break;
            case "buttonPrecision5":
                //slider1 is responsible for circle size
                sliderModeOptions1.setMin(10);
                sliderModeOptions1.setMax(50);
                sliderModeOptions1.setValue(30);
                sliderModeOptions1.setMajorTickUnit(5);
                sliderModeOptions1.valueProperty().addListener((obs, oldValue, newValue) -> labelModeOptions1Value.setText("" + newValue.intValue()));
                sliderModeOptions1.setVisible(true);
                labelModeOptions1Value.setText("30");
                labelModeOptions1Value.setVisible(true);
                labelModeOptions1Info.textProperty().bind(I18N.createStringBinding("labelCircleSizeInfo"));
                labelModeOptions1Info.setVisible(true);
                //slider2 is responsible for circle speed
                sliderModeOptions2.setMin(1);
                sliderModeOptions2.setMax(5);
                sliderModeOptions2.setValue(3);
                sliderModeOptions2.setMajorTickUnit(1);
                sliderModeOptions2.valueProperty().addListener((obs, oldValue, newValue) -> labelModeOptions2Value.setText("" + newValue.intValue()));
                sliderModeOptions2.setVisible(true);
                labelModeOptions2Value.setText("3");
                labelModeOptions2Value.setVisible(true);
                labelModeOptions2Info.textProperty().bind(I18N.createStringBinding("labelCircleSpeedInfo"));
                labelModeOptions2Info.setVisible(true);
                //labelR1 is responsible for the amount of hit circles;
                labelResult1Info.textProperty().bind(I18N.createStringBinding("labelResultHitCircles"));
                labelResult1Info.setVisible(true);
                labelResult1Value.setText("0");
                labelResult1Value.setVisible(true);
                //labelR2 is responsible for total missed hits
                labelResult2Info.textProperty().bind(I18N.createStringBinding("labelResultMissedHits"));
                labelResult2Info.setVisible(true);
                labelResult2Value.setText("0");
                labelResult2Value.setVisible(true);
                break;
            case "buttonPrecision6":
                //slider1 is responsible for circle size
                sliderModeOptions1.setMin(10);
                sliderModeOptions1.setMax(50);
                sliderModeOptions1.setValue(30);
                sliderModeOptions1.setMajorTickUnit(5);
                sliderModeOptions1.valueProperty().addListener((obs, oldValue, newValue) -> labelModeOptions1Value.setText("" + newValue.intValue()));
                sliderModeOptions1.setVisible(true);
                labelModeOptions1Value.setText("30");
                labelModeOptions1Value.setVisible(true);
                labelModeOptions1Info.textProperty().bind(I18N.createStringBinding("labelCircleSizeInfo"));
                labelModeOptions1Info.setVisible(true);
                //labelR1 is responsible for amount of loses
                labelResult1Info.textProperty().bind(I18N.createStringBinding("labelResultDefeatsAmount"));
                labelResult1Info.setVisible(true);
                labelResult1Value.setText("0");
                labelResult1Value.setVisible(true);
                //labelR2 is responsible for amount of wins
                labelResult2Info.textProperty().bind(I18N.createStringBinding("labelResultVictoriesAmount"));
                labelResult2Info.setVisible(true);
                labelResult2Value.setText("0");
                labelResult2Value.setVisible(true);
                //slider2 is responsible for lines amount
                sliderModeOptions2.setMin(5);
                sliderModeOptions2.setMax(15);
                sliderModeOptions2.setValue(10);
                sliderModeOptions2.setMajorTickUnit(2);
                sliderModeOptions2.valueProperty().addListener((obs, oldValue, newValue) -> labelModeOptions2Value.setText("" + newValue.intValue()));
                sliderModeOptions2.setVisible(true);
                labelModeOptions2Value.setText("10");
                labelModeOptions2Value.setVisible(true);
                labelModeOptions2Info.textProperty().bind(I18N.createStringBinding("labelLinesAmount"));
                labelModeOptions2Info.setVisible(true);
                break;
            case "buttonPrecision7":
                //slider1 is responsible for circle size
                sliderModeOptions1.setMin(10);
                sliderModeOptions1.setMax(50);
                sliderModeOptions1.setValue(30);
                sliderModeOptions1.setMajorTickUnit(5);
                sliderModeOptions1.valueProperty().addListener((obs, oldValue, newValue) -> labelModeOptions1Value.setText("" + newValue.intValue()));
                sliderModeOptions1.setVisible(true);
                labelModeOptions1Value.setText("30");
                labelModeOptions1Value.setVisible(true);
                labelModeOptions1Info.textProperty().bind(I18N.createStringBinding("labelCircleSizeInfo"));
                labelModeOptions1Info.setVisible(true);
                //slider2 is responsible for obstacles number
                sliderModeOptions2.setMin(1);
                sliderModeOptions2.setMax(5);
                sliderModeOptions2.setValue(3);
                sliderModeOptions2.setMajorTickUnit(1);
                sliderModeOptions2.valueProperty().addListener((obs, oldValue, newValue) -> labelModeOptions2Value.setText("" + newValue.intValue()));
                sliderModeOptions2.setVisible(true);
                labelModeOptions2Value.setText("3");
                labelModeOptions2Value.setVisible(true);
                labelModeOptions2Info.textProperty().bind(I18N.createStringBinding("labelObstaclesNumberInfo"));
                labelModeOptions2Info.setVisible(true);
                //labelR1 is responsible for the amount of hit circles;
                labelResult1Info.textProperty().bind(I18N.createStringBinding("labelResultDefeatsAmount"));
                labelResult1Info.setVisible(true);
                labelResult1Value.setText("0");
                labelResult1Value.setVisible(true);
                //labelR2 is responsible for total missed hits
                labelResult2Info.textProperty().bind(I18N.createStringBinding("labelResultVictoriesAmount"));
                labelResult2Info.setVisible(true);
                labelResult2Value.setText("0");
                labelResult2Value.setVisible(true);
                break;
        }
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public Label getLabelResult1Value() {
        return labelResult1Value;
    }

    public Label getLabelResult2Value() {
        return labelResult2Value;
    }

    public Label getLabelResult3Value() {
        return labelResult3Value;
    }
}
