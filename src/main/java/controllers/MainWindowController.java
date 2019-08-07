package controllers;

import Data.I18N;
import Data.Question;
import Data.WindowSize;
import Main.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.Locale;

public class MainWindowController {
    private Main main;
    private Stage stage;
    private PlayWindowController playWindowController;
    private ArrayList<Button> arrayListButtons = new ArrayList<>();
    private ArrayList<Button> arrayListOptionsButtons = new ArrayList<>();
    private ArrayList<HBox> arrayListHBoxes = new ArrayList<>();
    private ArrayList<VBox> arrayListVBoxes = new ArrayList<>();
    private ArrayList<HBox> arrayListHBoxesOptions = new ArrayList<>();
    private ArrayList<VBox> arrayListVBoxesOptions = new ArrayList<>();
    private ArrayList<Label> arrayListLabels = new ArrayList<>();
    private ArrayList<Question> arrayListQuestions = new ArrayList<>();
    public WindowSize windowSize = new WindowSize(1024, 576);
    private boolean languagePolish = true;

    @FXML private TabPane tabPane;
    @FXML private Button buttonReflex1;
    @FXML private Button buttonReflex2;
    @FXML private Button buttonReflex3;
    @FXML private Button buttonReflex4;
    @FXML private Button buttonReflex5;
    @FXML private Button buttonReflex6;
    @FXML private Button buttonReflex7;
    @FXML private Button buttonReflex8;
    @FXML private HBox hBoxReflex1;
    @FXML private HBox hBoxReflex2;
    @FXML private VBox vBoxReflex;
    @FXML private Button buttonPrecision1;
    @FXML private Button buttonPrecision2;
    @FXML private Button buttonPrecision3;
    @FXML private Button buttonPrecision4;
    @FXML private Button buttonPrecision5;
    @FXML private Button buttonPrecision6;
    @FXML private Button buttonPrecision7;
    @FXML private Button buttonPrecision8;
    @FXML private HBox hBoxPrecision1;
    @FXML private HBox hBoxPrecision2;
    @FXML private VBox vBoxPrecision1;
    @FXML private Button buttonSpeed1;
    @FXML private Button buttonSpeed2;
    @FXML private Button buttonSpeed3;
    @FXML private Button buttonSpeed4;
    @FXML private Button buttonSpeed5;
    @FXML private Button buttonSpeed6;
    @FXML private Button buttonSpeed7;
    @FXML private Button buttonSpeed8;
    @FXML private HBox hBoxSpeed1;
    @FXML private HBox hBoxSpeed2;
    @FXML private VBox vBoxSpeed1;
    @FXML private Label labelReflexModeInfo;
    @FXML private Label labelPrecisionModeInfo;
    @FXML private Label labelSpeedModeInfo;
    @FXML private Button buttonExit;
    @FXML private Button buttonFullscreen;
    @FXML private Button buttonMinimize;
    @FXML private ComboBox<WindowSize> comboBoxWindowSize;
    @FXML private TableView<Question> tableViewFAQ;
    @FXML private TableColumn<Question, String> tableColumnQuestions;
    @FXML private Label labelFAQAnswer;
    @FXML private Label labelLanguage;
    @FXML private Label labelWindowSize;
    @FXML private Label labelFAQ;
    @FXML private HBox hBoxOptions0;
    @FXML private VBox vBoxOptions00;
    @FXML private VBox vBoxOptions000;
    @FXML private HBox hBoxOptions0000;
    @FXML private VBox vBoxOptions001;
    @FXML private HBox hBoxOptions0010;
    @FXML private VBox vBoxOptions01;
    @FXML private VBox vBoxOptions010;
    @FXML private HBox hBoxOptions0100;
    @FXML private VBox vBoxOptions02;
    @FXML private RadioButton radioButtonPolish;
    @FXML private RadioButton radioButtonEnglish;
    @FXML private Label labelPolishFlag;
    @FXML private Label labelEnglishFlag;
    @FXML private Button buttonOptionsApply;
    @FXML private Button buttonOptionsOk;
    @FXML private Button buttonOptionsCancel;
    @FXML private Label tabReflexLabel;
    @FXML private Label tabPrecisionLabel;
    @FXML private Label tabSpeedLabel;
    @FXML private Label tabOptionsLabel;

    public void initialize(){
        tabPane.setOnKeyPressed(event -> {if(event.isControlDown()) main.setCtrlDown(true);});
        tabPane.setOnKeyReleased(event -> {if(!event.isControlDown()) main.setCtrlDown(false);});
        tabPane.setOnMousePressed(event -> {
            if(main.isCtrlDown()) {
                main.setxOffset(event.getSceneX());
                main.setyOffset(event.getSceneY());
            }
        });
        tabPane.setOnMouseDragged(event -> {
            if(main.isCtrlDown()) {
                stage.setX(event.getScreenX() - main.getxOffset());
                stage.setY(event.getScreenY() - main.getyOffset());
            }
        });

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
        buttonReflex1.textProperty().bind(I18N.createStringBinding("buttonReflex1"));
        buttonReflex2.textProperty().bind(I18N.createStringBinding("buttonReflex2"));
        buttonReflex3.textProperty().bind(I18N.createStringBinding("buttonReflex3"));
        buttonReflex4.textProperty().bind(I18N.createStringBinding("buttonReflex4"));
        buttonReflex5.textProperty().bind(I18N.createStringBinding("buttonReflex5"));
        buttonReflex6.textProperty().bind(I18N.createStringBinding("buttonReflex6"));
        buttonReflex7.textProperty().bind(I18N.createStringBinding("buttonReflex7"));
        buttonReflex8.textProperty().bind(I18N.createStringBinding("buttonReflex8"));
        tabReflexLabel.textProperty().bind(I18N.createStringBinding("tabReflex"));

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
        arrayListVBoxes.add(vBoxPrecision1);
        buttonPrecision1.textProperty().bind(I18N.createStringBinding("buttonPrecision1"));
        buttonPrecision2.textProperty().bind(I18N.createStringBinding("buttonPrecision2"));
        buttonPrecision3.textProperty().bind(I18N.createStringBinding("buttonPrecision3"));
        buttonPrecision4.textProperty().bind(I18N.createStringBinding("buttonPrecision4"));
        buttonPrecision5.textProperty().bind(I18N.createStringBinding("buttonPrecision5"));
        buttonPrecision6.textProperty().bind(I18N.createStringBinding("buttonPrecision6"));
        buttonPrecision7.textProperty().bind(I18N.createStringBinding("buttonPrecision7"));
        buttonPrecision8.textProperty().bind(I18N.createStringBinding("buttonPrecision8"));
        tabPrecisionLabel.textProperty().bind(I18N.createStringBinding("tabPrecision"));

        tabOptionsLabel.textProperty().bind(I18N.createStringBinding("tabOptions"));
        labelLanguage.textProperty().bind(I18N.createStringBinding("labelLanguage"));
        labelWindowSize.textProperty().bind(I18N.createStringBinding("labelWindowSize"));
        buttonOptionsApply.textProperty().bind(I18N.createStringBinding("buttonApply"));
        buttonOptionsOk.textProperty().bind(I18N.createStringBinding("buttonOk"));
        buttonOptionsCancel.textProperty().bind(I18N.createStringBinding("buttonCancel"));

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
        arrayListVBoxes.add(vBoxSpeed1);
        buttonSpeed1.textProperty().bind(I18N.createStringBinding("buttonSpeed1"));
        buttonSpeed2.textProperty().bind(I18N.createStringBinding("buttonSpeed2"));
        buttonSpeed3.textProperty().bind(I18N.createStringBinding("buttonSpeed3"));
        buttonSpeed4.textProperty().bind(I18N.createStringBinding("buttonSpeed4"));
        buttonSpeed5.textProperty().bind(I18N.createStringBinding("buttonSpeed5"));
        buttonSpeed6.textProperty().bind(I18N.createStringBinding("buttonSpeed6"));
        buttonSpeed7.textProperty().bind(I18N.createStringBinding("buttonSpeed7"));
        buttonSpeed8.textProperty().bind(I18N.createStringBinding("buttonSpeed8"));
        tabSpeedLabel.textProperty().bind(I18N.createStringBinding("tabSpeed"));

        arrayListLabels.add(labelFAQAnswer);
        arrayListLabels.add(labelPrecisionModeInfo);
        arrayListLabels.add(labelReflexModeInfo);
        arrayListLabels.add(labelSpeedModeInfo);
        arrayListLabels.add(labelFAQ);
        arrayListLabels.add(labelLanguage);
        arrayListLabels.add(labelWindowSize);
        arrayListHBoxesOptions.add(hBoxOptions0);
        arrayListHBoxesOptions.add(hBoxOptions0000);
        arrayListHBoxesOptions.add(hBoxOptions0010);
        arrayListHBoxesOptions.add(hBoxOptions0100);
        arrayListVBoxesOptions.add(vBoxOptions00);
        arrayListVBoxesOptions.add(vBoxOptions000);
        arrayListVBoxesOptions.add(vBoxOptions001);
        arrayListVBoxesOptions.add(vBoxOptions01);
        arrayListVBoxesOptions.add(vBoxOptions010);
        arrayListOptionsButtons.add(buttonOptionsApply);
        arrayListOptionsButtons.add(buttonOptionsOk);
        arrayListOptionsButtons.add(buttonOptionsCancel);

        for(Button b : arrayListButtons) {
            b.setOnMouseEntered(event -> setLabelInfo(((Button)event.getSource()).getId()));
            b.setOnMouseExited(event -> clearLabelInfo());
            b.setOnMouseClicked(evt -> {
                main.changeScene();
                playWindowController.prepareMode(b.getId());
            });
        }

        labelReflexModeInfo.setText("\n\n");
        labelPrecisionModeInfo.setText("\n\n");
        labelSpeedModeInfo.setText("\n\n");

        comboBoxWindowSize.getItems().add(new WindowSize(1024, 576));
        comboBoxWindowSize.getItems().add(new WindowSize(1280, 720));
        comboBoxWindowSize.getItems().add(new WindowSize(1366, 768));
        comboBoxWindowSize.getItems().add(new WindowSize(1600, 900));
        comboBoxWindowSize.getItems().add(new WindowSize(1920, 1080));
        comboBoxWindowSize.getItems().add(new WindowSize(2048, 1152));
        comboBoxWindowSize.getItems().add(new WindowSize(2560, 1440));
        comboBoxWindowSize.getSelectionModel().select(0);

        arrayListQuestions.add(new Question("messageAuthor", 0));
        arrayListQuestions.add(new Question("messageWindow", 1));
        arrayListQuestions.add(new Question("messageWhatIsFPSMode", 2));
        arrayListQuestions.add(new Question("messageExitFPSMode", 3));
        ObservableList<Question> ol = FXCollections.observableList(arrayListQuestions);
        tableViewFAQ.setItems(ol);
        tableColumnQuestions.setCellValueFactory(new PropertyValueFactory<>("question"));
        tableViewFAQ.getStyleClass().add("table-view_no_header");
        tableViewFAQ.setRowFactory(tableView -> {
            final TableRow<Question> row = new TableRow<>();
            row.hoverProperty().addListener((observable) -> {
               Question question = row.getItem();
               if(row.isHover() && question != null)
                   setLabelFAQAnswer(question.getQuestionNumber());
               else
                   setLabelFAQAnswer(-1);
            });
            return row;
        });
    }

    public void myInitialize(Main main){
        this.main = main;
        this.stage = main.getStage();
        this.windowSize = main.getWindowSize();
        this.playWindowController = main.getPlayWindowController();
    }

    public void resizeWindow(double windowWidth, double windowHeight) {
        windowSize.setHeight(windowHeight);
        windowSize.setWidth(windowWidth);
        tabPane.setPrefSize(windowWidth, windowHeight);

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

        buttonExit.setLayoutX(windowWidth - 40);
        buttonExit.setLayoutY(10);
        buttonFullscreen.setLayoutX(windowWidth - 80);
        buttonFullscreen.setLayoutY(10);
        buttonMinimize.setLayoutX(windowWidth - 120);
        buttonMinimize.setLayoutY(10);

        for(HBox h : arrayListHBoxes)h.setSpacing((windowWidth - 1024) / 21 + 30);
        for(Button b : arrayListButtons) {
            b.setPrefSize(windowWidth / 1024 * 200, windowHeight / 576 * 100);
            b.setFont(new Font("Bank Gothic Medium BT", fontSize));
        }
        for(Button b : arrayListOptionsButtons) {
            b.setFont(new Font("Bank Gothic Medium BT", fontSize));
        }

        for(Label l : arrayListLabels)
            l.setFont(new Font("Bank Gothic Medium BT", fontSize));

        labelLanguage.setPadding(new Insets(padH50, 0, 0, padW50));
        hBoxOptions0000.setPadding(new Insets(0, 0, 0, padW50));
        radioButtonPolish.setPadding(new Insets(0, 0, 0, padW50));
        labelPolishFlag.setPrefSize(flagSizes, flagSizes);
        radioButtonEnglish.setPadding(new Insets(0, 0, 0, padW30));
        labelEnglishFlag.setPrefSize(flagSizes, flagSizes);
        vBoxOptions001.setPadding(new Insets(padH50, 0, 0, 0));
        labelWindowSize.setPadding(new Insets(0, 0, 0, padW50));
        hBoxOptions0010.setPadding(new Insets(padH15, 0, 0, padW100));
        comboBoxWindowSize.setPrefWidth(padW150);
        comboBoxWindowSize.setStyle("-fx-font: " + (fontSize - 2) + "\"Bank Gothic Medium BT\";");
        comboBoxWindowSize.setCellFactory(new Callback<ListView<WindowSize>, ListCell<WindowSize>>() {
            @Override
            public ListCell<WindowSize> call(ListView<WindowSize> param) {
                return new ListCell<WindowSize>() {
                    @Override
                    public void updateItem(WindowSize windowSize1, boolean empty) {
                        super.updateItem(windowSize1, empty);
                        setPrefHeight(padH25);
                        getListView().setPrefWidth(padW150);
                        if (!empty) {
                            setText(windowSize1.toString());
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });
        vBoxOptions01.setPadding(new Insets(padH50, 0, 0, padW50));
        labelFAQ.setPadding(new Insets(0, 0, 0, padW50));
        vBoxOptions010.setPadding(new Insets(0, 0, 0, padW100));
        tableViewFAQ.setPrefSize(tableW, tableH);
        tableColumnQuestions.setPrefWidth(tableW);
        tableViewFAQ.setStyle("-fx-font: " + (fontSize - 2) + "\"Bank Gothic Medium BT\";");
        vBoxOptions02.setSpacing(padH15);
        vBoxOptions02.setPadding(new Insets(0, 0, padW50, 0));
        buttonOptionsApply.setPrefSize(padW100 + padW30, padH30);
        buttonOptionsOk.setPrefSize(padW100 + padW30, padH30);
        buttonOptionsCancel.setPrefSize(padW100 + padW30, padH30);
    }



    private void setLabelInfo(String buttonType) {
        if(buttonType.contains("buttonReflex")){
            if(buttonType.contains("1")) labelReflexModeInfo.textProperty().bind(I18N.createStringBinding("buttonReflex1Info"));
            else if(buttonType.contains("2")) labelReflexModeInfo.textProperty().bind(I18N.createStringBinding("buttonReflex2Info"));
            else if(buttonType.contains("3")) labelReflexModeInfo.textProperty().bind(I18N.createStringBinding("buttonReflex3Info"));
            else if(buttonType.contains("4")) labelReflexModeInfo.textProperty().bind(I18N.createStringBinding("buttonReflex4Info"));
            else if(buttonType.contains("5")) labelReflexModeInfo.textProperty().bind(I18N.createStringBinding("buttonReflex5Info"));
            else if(buttonType.contains("6")) labelReflexModeInfo.textProperty().bind(I18N.createStringBinding("buttonReflex6Info"));
            else if(buttonType.contains("7")) labelReflexModeInfo.textProperty().bind(I18N.createStringBinding("buttonReflex7Info"));
            else if(buttonType.contains("8")) labelReflexModeInfo.textProperty().bind(I18N.createStringBinding("buttonReflex8Info"));
        }
        else if(buttonType.contains("buttonPrecision")){
            if(buttonType.contains("1")) labelPrecisionModeInfo.textProperty().bind(I18N.createStringBinding("buttonPrecision1Info"));
            else if(buttonType.contains("2")) labelPrecisionModeInfo.textProperty().bind(I18N.createStringBinding("buttonPrecision2Info"));
            else if(buttonType.contains("3")) labelPrecisionModeInfo.textProperty().bind(I18N.createStringBinding("buttonPrecision3Info"));
            else if(buttonType.contains("4")) labelPrecisionModeInfo.textProperty().bind(I18N.createStringBinding("buttonPrecision4Info"));
            else if(buttonType.contains("5")) labelPrecisionModeInfo.textProperty().bind(I18N.createStringBinding("buttonPrecision5Info"));
            else if(buttonType.contains("6")) labelPrecisionModeInfo.textProperty().bind(I18N.createStringBinding("buttonPrecision6Info"));
            else if(buttonType.contains("7")) labelPrecisionModeInfo.textProperty().bind(I18N.createStringBinding("buttonPrecision7Info"));
            else if(buttonType.contains("8")) labelPrecisionModeInfo.textProperty().bind(I18N.createStringBinding("buttonPrecision8Info"));
        }
        else{
            if(buttonType.contains("1")) labelSpeedModeInfo.textProperty().bind(I18N.createStringBinding("buttonSpeed1Info"));
            else if(buttonType.contains("2")) labelSpeedModeInfo.textProperty().bind(I18N.createStringBinding("buttonSpeed2Info"));
            else if(buttonType.contains("3")) labelSpeedModeInfo.textProperty().bind(I18N.createStringBinding("buttonSpeed3Info"));
            else if(buttonType.contains("4")) labelSpeedModeInfo.textProperty().bind(I18N.createStringBinding("buttonSpeed4Info"));
            else if(buttonType.contains("5")) labelSpeedModeInfo.textProperty().bind(I18N.createStringBinding("buttonSpeed5Info"));
            else if(buttonType.contains("6")) labelSpeedModeInfo.textProperty().bind(I18N.createStringBinding("buttonSpeed6Info"));
            else if(buttonType.contains("7")) labelSpeedModeInfo.textProperty().bind(I18N.createStringBinding("buttonSpeed7Info"));
            else if(buttonType.contains("8")) labelSpeedModeInfo.textProperty().bind(I18N.createStringBinding("buttonSpeed8Info"));
        }
    }

    private void clearLabelInfo(){
        labelReflexModeInfo.textProperty().unbind();
        labelPrecisionModeInfo.textProperty().unbind();
        labelSpeedModeInfo.textProperty().unbind();
        labelReflexModeInfo.setText("\n\n");
        labelPrecisionModeInfo.setText("\n\n");
        labelSpeedModeInfo.setText("\n\n");
    }

    private void setLabelFAQAnswer(int questionNumber){
        labelFAQAnswer.getStyleClass().clear();
        if(questionNumber == -1){
            labelFAQAnswer.textProperty().unbind();
            labelFAQAnswer.setText("");
        }
        else if(questionNumber == 0){
            labelFAQAnswer.setText("                              \n\n\n\n\n\n\n\n\n\n");
            labelFAQAnswer.getStyleClass().add("label_logo");
        }
        else if(questionNumber == 1) labelFAQAnswer.textProperty().bind(I18N.createStringBinding("messageWindowAnswer"));
        else if(questionNumber == 2) labelFAQAnswer.textProperty().bind(I18N.createStringBinding("messageWhatIsFPSModeAnswer"));
        else if(questionNumber == 3) labelFAQAnswer.textProperty().bind(I18N.createStringBinding("messageExitFPSModeAnswer"));
    }

    public void actionOptionsApply(){
        WindowSize size = comboBoxWindowSize.getSelectionModel().getSelectedItem();
        if(size.getWidth() != windowSize.getWidth()) {
            windowSize.setWidth(size.getWidth());
            windowSize.setHeight(size.getHeight());
            stage.setWidth(size.getWidth());
            stage.setHeight(size.getHeight());

            Rectangle2D screenBounds = Screen.getPrimary().getBounds();
            stage.setX((screenBounds.getMaxX() - size.getWidth()) / 2);
            stage.setY((screenBounds.getMaxY() - size.getHeight()) / 2);

            comboBoxWindowSize.getSelectionModel().select(size);
            comboBoxWindowSize.show();
            comboBoxWindowSize.hide();

            main.setFullscreen(false);
        }

        ArrayList<Question> buffer = new ArrayList<>();
        for (Question q: arrayListQuestions)
            buffer.add(new Question(q.getKey(), q.getQuestionNumber()));

        tableViewFAQ.getItems().clear();
        arrayListQuestions = buffer;
        tableViewFAQ.setItems(FXCollections.observableList(buffer));

        if(radioButtonPolish.isSelected() != languagePolish)
            I18N.setLocale(Locale.ENGLISH);
        else
            I18N.setLocale(new Locale("pl"));
    }

    public void actionOptionsOk(){
        actionOptionsApply();
        tabPane.getSelectionModel().select(0);
    }

    public void actionOptionsCancel(){
        comboBoxWindowSize.getSelectionModel().select(windowSize);
        if(languagePolish) radioButtonPolish.fire();
        tabPane.getSelectionModel().select(0);
    }

    public void actionExit(){
        stage.close();
    }

    public void actionFullscreen(){
        stage.setFullScreen(!stage.isFullScreen());
        if(stage.isFullScreen()) {
            comboBoxWindowSize.getSelectionModel().select(4);
            main.setFullscreen(true);
        }
        else {
            comboBoxWindowSize.getSelectionModel().select(windowSize);
            main.setFullscreen(false);
        }
        comboBoxWindowSize.show();
        comboBoxWindowSize.hide();
    }

    public void actionMinimize(){
        stage.setIconified(true);
    }
}
