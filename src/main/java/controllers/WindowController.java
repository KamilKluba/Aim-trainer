package controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
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
    @FXML
    private Label labelPrecisionModeInfo;
    @FXML
    private Label labelSpeedModeInfo;

    EventHandler<MouseEvent> eventHandlerSetLabel = event -> setLabelInfo(((Button)event.getSource()).getId());
    EventHandler<MouseEvent> eventHandlerClearLabel = event -> clearLabelInfo();

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

        for(Button b : arrayListButtons) b.setOnMouseEntered(eventHandlerSetLabel);
        for(Button b : arrayListButtons) b.setOnMouseExited(eventHandlerClearLabel);

        labelReflexModeInfo.setText("\n\n");
        labelPrecisionModeInfo.setText("\n\n");
        labelSpeedModeInfo.setText("\n\n");
    }

    public void resizeButtons(double windowWidth, double windowHeight) {
        for(Button b : arrayListButtons) {
            b.setPrefSize(windowWidth / defaultWidth * 200, windowHeight / defaultHeight * 100);
            b.setFont(new Font("Bank Gothic Medium BT", 5 + (windowHeight + windowWidth) / 150));
            labelReflexModeInfo.setFont(new Font("Bank Gothic Medium BT", 5 + (windowHeight + windowWidth) / 150));
            labelPrecisionModeInfo.setFont(new Font("Bank Gothic Medium BT", 5 + (windowHeight + windowWidth) / 150));
            labelSpeedModeInfo.setFont(new Font("Bank Gothic Medium BT", 5 + (windowHeight + windowWidth) / 150));
        }

        for(HBox h : arrayListHBoxes)h.setSpacing((windowWidth - defaultWidth) / 21 + 30);
    }



    private void setLabelInfo(String buttonType) {
        if(buttonType.contains("buttonReflexInfo")){
            if(buttonType.contains("1")) labelReflexModeInfo.setText(bundle.getString("buttonReflex1Info"));
            else if(buttonType.contains("2")) labelReflexModeInfo.setText(bundle.getString("buttonReflex2Info"));
            else if(buttonType.contains("3")) labelReflexModeInfo.setText(bundle.getString("buttonReflex3Info"));
            else if(buttonType.contains("4")) labelReflexModeInfo.setText(bundle.getString("buttonReflex4Info"));
            else if(buttonType.contains("5")) labelReflexModeInfo.setText(bundle.getString("buttonReflex5Info"));
            else if(buttonType.contains("6")) labelReflexModeInfo.setText(bundle.getString("buttonReflex6Info"));
            else if(buttonType.contains("7")) labelReflexModeInfo.setText(bundle.getString("buttonReflex7Info"));
            else if(buttonType.contains("8")) labelReflexModeInfo.setText(bundle.getString("buttonReflex8Info"));
        }
        else if(buttonType.contains("buttonPrecision")){
            if(buttonType.contains("1")) labelPrecisionModeInfo.setText(bundle.getString("buttonPrecision1Info"));
            else if(buttonType.contains("2")) labelPrecisionModeInfo.setText(bundle.getString("buttonPrecision2Info"));
            else if(buttonType.contains("3")) labelPrecisionModeInfo.setText(bundle.getString("buttonPrecision3Info"));
            else if(buttonType.contains("4")) labelPrecisionModeInfo.setText(bundle.getString("buttonPrecision4Info"));
            else if(buttonType.contains("5")) labelPrecisionModeInfo.setText(bundle.getString("buttonPrecision5Info"));
            else if(buttonType.contains("6")) labelPrecisionModeInfo.setText(bundle.getString("buttonPrecision6Info"));
            else if(buttonType.contains("7")) labelPrecisionModeInfo.setText(bundle.getString("buttonPrecision7Info"));
            else if(buttonType.contains("8")) labelPrecisionModeInfo.setText(bundle.getString("buttonPrecision8Info"));
        }
        else{
            if(buttonType.contains("1")) labelSpeedModeInfo.setText(bundle.getString("buttonSpeed1Info"));
            else if(buttonType.contains("2")) labelSpeedModeInfo.setText(bundle.getString("buttonSpeed2Info"));
            else if(buttonType.contains("3")) labelSpeedModeInfo.setText(bundle.getString("buttonSpeed3Info"));
            else if(buttonType.contains("4")) labelSpeedModeInfo.setText(bundle.getString("buttonSpeed4Info"));
            else if(buttonType.contains("5")) labelSpeedModeInfo.setText(bundle.getString("buttonSpeed5Info"));
            else if(buttonType.contains("6")) labelSpeedModeInfo.setText(bundle.getString("buttonSpeed6Info"));
            else if(buttonType.contains("7")) labelSpeedModeInfo.setText(bundle.getString("buttonSpeed7Info"));
            else if(buttonType.contains("8")) labelSpeedModeInfo.setText(bundle.getString("buttonSpeed8Info"));
        }
    }

    private void clearLabelInfo(){
        labelReflexModeInfo.setText("\n\n");
        labelPrecisionModeInfo.setText("\n\n");
        labelSpeedModeInfo.setText("\n\n");
    }

    public void actionExited(){
        labelReflexModeInfo.setText(bundle.getString("speed"));
    }
}
