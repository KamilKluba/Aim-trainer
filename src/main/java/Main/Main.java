package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class Main extends Application{
    public static void main(String args[]){
        launch(args);
    }


    public void start(Stage primaryStage) throws Exception {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Window.fxml"));
        loader.setResources(resourceBundle);
        TabPane tabPane = loader.load();

        Scene scene = new Scene(tabPane);

        primaryStage.setMinWidth(1024);
        primaryStage.setMinHeight(576);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
