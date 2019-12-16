/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 2019C
  Assessment: Assignment 2
  Author: Tran Mach So Han
  ID: s3750789
  Created  date: 16/12/2019
  Last modified: 16/12/2019
  Acknowledgement: If you use any resources, acknowledge here. Failure to do so will be considered as plagiarism.
*/

package game;

import game.game.GameMain;
import game.level.LevelDisplay;
import game.sound.SoundController;
import game.time.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Main extends Application {
    private Stage window;
    //Set window stage to make it called in several method
    private static GameMain gameMain = new GameMain();
    private static TimeController timeController = new TimeController();
    private static SoundController soundController = new SoundController();
    private static TimeDisplay timeDisplay = new TimeDisplay();
    private AnimationTimer timer;

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        //Set window is primaryStage of main
        window.setTitle("Assignment 2");
        window.show();
        mainScene();
    }

    private void mainScene(){
        Pane pane = new Pane();
                    //Set in the main scene
        pane.setPrefSize(500,400);
        pane.setBackground(new Background(new BackgroundFill(Color.BLANCHEDALMOND,
                new CornerRadii(1),new Insets(0.0,0.0,0.0,0.0))));

        Label start = new Label("START");
        start.setFont(Font.font("Arial",FontWeight.EXTRA_BOLD,FontPosture.REGULAR,70));
        start.setTextFill(Color.BLUEVIOLET);
        start.setTranslateY(150);
        start.setTranslateX(150);
        pane.getChildren().add(start);

        Label label = new Label("PRESS to ");
        label.setTranslateX(150);
        label.setTranslateY(120);
        label.setFont(Font.font("Times New Roman", FontWeight.BOLD,
                FontPosture.ITALIC,20));
        pane.getChildren().add(label);

        pane.setOnMousePressed(e -> window.setScene(chooseLevel()));

        Scene mainScene = new Scene(pane, 500, 400);
        window.setScene(mainScene);
    }

    private Scene chooseLevel(){
        VBox pane = new VBox(20);
        pane.setBackground(new Background(new BackgroundFill(Color.WHITE,
                new CornerRadii(1),new Insets(0.0,0.0,0.0,0.0))));
        pane.setPadding(new Insets(15,5,5,5));
        pane.setStyle("-fx-background-color: lightgreen");
        Label label = new Label("Level");
        label.setFont(Font.font("Times New Roman", 50));
        pane.getChildren().add(label);

        Button[] levelOption = {new Button("Level 1"), new Button("Level 2"),
                new Button("Level 3")};
        for (Button btn : levelOption){
            btn.setAlignment(Pos.CENTER);
            btn.translateXProperty().bind(pane.widthProperty().divide(2));
            pane.getChildren().add(btn);
            btn.setOnAction(e-> {
                Scene sceneGame = new Scene(
                        addAll(btn.getText()),1000,700);
                window.setScene(sceneGame);
            });
        }
        return new Scene(pane, 500, 400);
    }

    private GridPane addAll(String text){
        GridPane gamePane = new GridPane();
        LevelDisplay levelDisplay = new LevelDisplay(text);
        gameMain.setLevel(Integer.parseInt(text.replaceAll("[\\D]","")));
        gamePane.add(levelDisplay,3,0);
        gamePane.add(gameMain,0,1);
        gamePane.add(timeDisplay,0,3);
        gamePane.add(timeController,0,2);
        gamePane.add(soundController,2,2);
        gamePane.setPadding(new Insets(0,0,30,0));
        gamePane.setHgap(40);
        gamePane.setVgap(40);
        BackgroundImage myBF = new BackgroundImage(new Image("image/background.jpeg",1250,
                800,false,true), BackgroundRepeat.REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        gamePane.setBackground(new Background(myBF));
        gamePane.add(gameStart(),0,0);
        gameStop();
        return gamePane;
    }

    private Button gameStart(){
        final boolean[] press = {false};
        Button start = new Button("Start Game");
        start.setStyle("-fx-fill: yellow");
        start.setOnAction(event -> {
            if (!press[0]){
                timeController.runTime();
                timeDisplay.runBarTime();
                gameMain.startPoint();
                gameMain.clickAndTurn();
                press[0] = true;
            }
        });
        return start;
    }

    private void gameStop(){
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (gameMain.isMatch()){
                    timeController.stopTime();
                    timeDisplay.pauseBarTime();
                    displayMessage("Congrtulation","You win!");
                    timer.stop();
                }
                if (timeController.isTimeOver()){
                    displayMessage("Time is up","Too bad ! You lose");
                    timer.stop();
                }
            }
        };
        timer.start();
    }

    private void displayMessage(String title, String message){
        Stage alertBox = new Stage();

        alertBox.initModality(Modality.APPLICATION_MODAL);
        alertBox.setTitle(title);
        alertBox.setMinWidth(250);

        Label label = new Label(message);

        HBox hBox = new HBox(30);
        Button closeButton = new Button("Quit");
        closeButton.setOnAction(e -> alertBox.close());

        Button continueButton = new Button("New Game");
        continueButton.setOnAction(e -> {
            alertBox.close();
            window.setScene(chooseLevel());
        });
        hBox.getChildren().addAll(closeButton, continueButton);

        VBox vBox = new VBox(20);
        vBox.getChildren().addAll(label,hBox);
        alertBox.setScene(new Scene(vBox));
        alertBox.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
