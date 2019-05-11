package controllers;

import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class WindowController {

    private float defaultWidth = 1024;
    private float defaultHeight = 576;
    private ArrayList<Button> arrayListButtons = new ArrayList<>();
    private ArrayList<HBox> arrayListHBoxes = new ArrayList<>();
    private ArrayList<VBox> arrayListVBoxes = new ArrayList<>();
    private ResourceBundle bundle = ResourceBundle.getBundle("messages");

    @FXML
    private Tab tabReflex;
    @FXML
    private Tab tabPrecision;
    @FXML
    private Tab tabSpeed;
    @FXML
    private Button buttonReflex1;
    @FXML
    private Button buttonReflex2;
    @FXML
    private Button buttonReflex3;
    @FXML
    private Button buttonReflex4;
    @FXML
    private Button buttonReflex5;
    @FXML
    private Button buttonReflex6;
    @FXML
    private Button buttonReflex7;
    @FXML
    private Button buttonReflex8;
    @FXML
    private HBox hBoxReflex1;
    @FXML
    private HBox hBoxReflex2;
    @FXML
    private VBox vBoxReflex;
    @FXML
    private Button buttonPrecision1;
    @FXML
    private Button buttonPrecision2;
    @FXML
    private Button buttonPrecision3;
    @FXML
    private Button buttonPrecision4;
    @FXML
    private Button buttonPrecision5;
    @FXML
    private Button buttonPrecision6;
    @FXML
    private Button buttonPrecision7;
    @FXML
    private Button buttonPrecision8;
    @FXML
    private HBox hBoxPrecision1;
    @FXML
    private HBox hBoxPrecision2;
    @FXML
    private VBox vBoxPrecision;
    @FXML
    private Button buttonSpeed1;
    @FXML
    private Button buttonSpeed2;
    @FXML
    private Button buttonSpeed3;
    @FXML
    private Button buttonSpeed4;
    @FXML
    private Button buttonSpeed5;
    @FXML
    private Button buttonSpeed6;
    @FXML
    private Button buttonSpeed7;
    @FXML
    private Button buttonSpeed8;
    @FXML
    private HBox hBoxSpeed1;
    @FXML
    private HBox hBoxSpeed2;
    @FXML
    private VBox vBoxSpeed;
    @FXML
    private Label labelReflexModeInfo;

    int halo1 = 0, halo2 = 0;


    public void initialize(){
        arrayListButtons.add(buttonReflex1);
        arrayListButtons.add(buttonReflex2);
        arrayListButtons.add(buttonReflex3);
        arrayListButtons.add(buttonReflex4);
        arrayListButtons.add(buttonReflex5);
        arrayListButtons.add(buttonReflex6);
        arrayListButtons.add(buttonReflex7);
        arrayListButtons.add(buttonReflex8);
        arrayListHBoxes.add(hBoxReflex1);
        arrayListHBoxes.add(hBoxReflex2);
        arrayListVBoxes.add(vBoxReflex);

        arrayListButtons.add(buttonPrecision1);
        arrayListButtons.add(buttonPrecision2);
        arrayListButtons.add(buttonPrecision3);
        arrayListButtons.add(buttonPrecision4);
        arrayListButtons.add(buttonPrecision5);
        arrayListButtons.add(buttonPrecision6);
        arrayListButtons.add(buttonPrecision7);
        arrayListButtons.add(buttonPrecision8);
        arrayListHBoxes.add(hBoxPrecision1);
        arrayListHBoxes.add(hBoxPrecision2);
        arrayListVBoxes.add(vBoxPrecision);

        arrayListButtons.add(buttonSpeed1);
        arrayListButtons.add(buttonSpeed2);
        arrayListButtons.add(buttonSpeed3);
        arrayListButtons.add(buttonSpeed4);
        arrayListButtons.add(buttonSpeed5);
        arrayListButtons.add(buttonSpeed6);
        arrayListButtons.add(buttonSpeed7);
        arrayListButtons.add(buttonSpeed8);
        arrayListHBoxes.add(hBoxSpeed1);
        arrayListHBoxes.add(hBoxSpeed2);
        arrayListVBoxes.add(vBoxSpeed);

        //buttonReflex1.getStylesheets().add("/CSS/Window/Window.css");
    }

    public void resizeButtons(double windowWidth, double windowHeight) {
        for(Button b : arrayListButtons) {
            b.setPrefSize(windowWidth / defaultWidth * 200, windowHeight / defaultHeight * 100);
            b.setFont(new Font("Bank Gothic Medium BT", 5 + (windowHeight + windowWidth) / 150));
        }

        for(HBox h : arrayListHBoxes)h.setSpacing((windowWidth - defaultWidth) / 21 + 30);
    }



    public void actionEntered() {
        labelReflexModeInfo.setText(bundle.getString("precision"));
    }

    public void actionExited(){
        labelReflexModeInfo.setText(bundle.getString("speed"));
    }
}
