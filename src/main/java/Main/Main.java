package Main;

import controllers.WindowController;
import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application{
    private double width = 1024;
    private double height = 576;

    public static void main(String args[]){
        launch(args);
    }


    public void start(Stage primaryStage) throws Exception {
        //Locale.setDefault(new Locale("en"));
        System.getProperty("file.encoding", "UTF-8");
        ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Window.fxml"));
        loader.setResources(resourceBundle);
        TabPane tabPane = loader.load();

        WindowController controller = loader.getController();

        Scene scene = new Scene(tabPane);
        scene.getStylesheets().add(getClass().getResource("/CSS/Window/Window.css").toExternalForm());

        primaryStage.setMinWidth(1024);
        primaryStage.setMinHeight(576);
        primaryStage.widthProperty().addListener((observable, oldValue, newValue) -> {
            width = (double)newValue;
            controller.resizeButtons(width, height);
        });
        primaryStage.heightProperty().addListener((observable, oldValue, newValue) -> {
            height = (double)newValue;
            controller.resizeButtons(width, height);
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
