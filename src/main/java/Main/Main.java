package Main;

import Data.WindowSize;
import controllers.MainWindowController;
import controllers.PlayWindowController;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ResourceBundle;

public class Main extends Application{
    private WindowSize windowSize = new WindowSize(1024,576);
    private double xOffset = 0;
    private double yOffset = 0;
    private boolean ctrlDown = false;
    private Stage stage;
    private Scene sceneMainWindow;
    private Scene scenePlayWindow;
    private MainWindowController mainWindowController;
    private PlayWindowController playWindowController;
    boolean fullscreen;

    public static void main(String args[]){
        launch(args);
    }


    public void start(Stage primaryStage) throws Exception {
        System.getProperty("file.encoding", "UTF-8");
        ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");

        FXMLLoader loaderMainWindow = new FXMLLoader(getClass().getResource("/fxml/MainWindow.fxml"));
        loaderMainWindow.setResources(resourceBundle);
        Pane pane = loaderMainWindow.load();
        TabPane tabPane = (TabPane) pane.getChildren().get(0);
        mainWindowController = loaderMainWindow.getController();
        sceneMainWindow = new Scene(pane);
        sceneMainWindow.getStylesheets().add(getClass().getResource("/CSS/Window/MainWindow.css").toExternalForm());

        FXMLLoader loaderPlayWindow = new FXMLLoader(getClass().getResource("/fxml/PlayWindow.fxml"));
        loaderPlayWindow.setResources(resourceBundle);
        AnchorPane anchorPane = loaderPlayWindow.load();
        playWindowController = loaderPlayWindow.getController();
        scenePlayWindow = new Scene(anchorPane);
        scenePlayWindow.getStylesheets().add(getClass().getResource("/CSS/Window/MainWindow.css").toExternalForm());

        stage = primaryStage;
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.widthProperty().addListener((observable, oldValue, newValue) -> {
            windowSize.setWidth((double)newValue);
            mainWindowController.resizeWindow(windowSize.getWidth(), windowSize.getHeight());
            playWindowController.resizeWindow(windowSize.getWidth(), windowSize.getHeight());
        });
        primaryStage.heightProperty().addListener((observable, oldValue, newValue) -> {
            windowSize.setHeight((double)newValue);
            mainWindowController.resizeWindow(windowSize.getWidth(), windowSize.getHeight());
            playWindowController.resizeWindow(windowSize.getWidth(), windowSize.getHeight());
        });
        primaryStage.setScene(sceneMainWindow);

        Image image = new Image("CSS/Window/crosshair.png");
        Cursor cursor = new ImageCursor(image, 256, 256);
        scenePlayWindow.setCursor(cursor);
        sceneMainWindow.setCursor(cursor);
        mainWindowController.myInitialize(this);
        playWindowController.myInitialize(this);

        primaryStage.show();
    }

    public void changeScene(){
        if(stage.getScene().equals(sceneMainWindow)) {
            stage.setScene(scenePlayWindow);
        }
        else {
            stage.setScene(sceneMainWindow);
        }

        stage.setFullScreen(fullscreen);
    }

    public Stage getStage() {
        return stage;
    }

    public Scene getSceneMainWindow() {
        return sceneMainWindow;
    }

    public Scene getScenePlayWindow() {
        return scenePlayWindow;
    }

    public WindowSize getWindowSize() {
        return windowSize;
    }

    public boolean isCtrlDown() {
        return ctrlDown;
    }

    public void setCtrlDown(boolean ctrlDown) {
        this.ctrlDown = ctrlDown;
    }

    public double getxOffset() {
        return xOffset;
    }

    public void setxOffset(double xOffset) {
        this.xOffset = xOffset;
    }

    public double getyOffset() {
        return yOffset;
    }

    public void setyOffset(double yOffset) {
        this.yOffset = yOffset;
    }

    public MainWindowController getMainWindowController() {
        return mainWindowController;
    }

    public PlayWindowController getPlayWindowController() {
        return playWindowController;
    }

    public boolean isFullscreen() {
        return fullscreen;
    }

    public void setFullscreen(boolean fullscreen) {
        this.fullscreen = fullscreen;
    }
}
