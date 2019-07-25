package Main;

import controllers.MainWindowController;
import controllers.PlayWindowController;
import javafx.application.Application;
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
    private double width = 1024;
    private double height = 576;
    private double xOffset = 0;
    private double yOffset = 0;
    private boolean ctrlDown = false;
    private Stage stage;
    private Scene sceneMainWindow;
    public Scene scenePlayWindow;

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
        MainWindowController mainWindowController = loaderMainWindow.getController();
        sceneMainWindow = new Scene(pane);
        sceneMainWindow.getStylesheets().add(getClass().getResource("/CSS/Window/MainWindow.css").toExternalForm());

        FXMLLoader loaderPlayWindow = new FXMLLoader(getClass().getResource("/fxml/PlayWindow.fxml"));
        loaderPlayWindow.setResources(resourceBundle);
        AnchorPane anchorPane = loaderPlayWindow.load();
        PlayWindowController playWindowController = loaderPlayWindow.getController();
        scenePlayWindow = new Scene(anchorPane);
        scenePlayWindow.getStylesheets().add(getClass().getResource("/CSS/Window/MainWindow.css").toExternalForm());

        Image image = new Image("CSS/Window/crosshair.png");
        Cursor cursor = new ImageCursor(image, 256, 256);

        scenePlayWindow.setCursor(cursor);
        sceneMainWindow.setCursor(cursor);

        stage = primaryStage;
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.widthProperty().addListener((observable, oldValue, newValue) -> {
            width = (double)newValue;
            mainWindowController.resizeButtons(width, height);
            playWindowController.setSizes(width, height);
        });
        primaryStage.heightProperty().addListener((observable, oldValue, newValue) -> {
            height = (double)newValue;
            mainWindowController.resizeButtons(width, height);
            playWindowController.setSizes(width, height);
        });
        primaryStage.setScene(sceneMainWindow);

        tabPane.setOnKeyPressed(event -> {if(event.isControlDown()) ctrlDown = true;});
        tabPane.setOnKeyReleased(event -> {if(!event.isControlDown()) ctrlDown = false;});
        tabPane.setOnMousePressed(event -> {
            if(ctrlDown) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        tabPane.setOnMouseDragged(event -> {
            if(ctrlDown) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });

        mainWindowController.setStage(primaryStage, this);
        playWindowController.setStage(primaryStage, this);
        primaryStage.show();
    }

    public void changeScene(){
        stage.setScene(scenePlayWindow);
    }
}
