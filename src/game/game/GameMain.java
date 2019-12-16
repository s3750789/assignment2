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

package game.game;

import javafx.animation.*;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class GameMain extends Pane {
    private final static String[] PLAYER = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private final static int[] lEVELSEC = {3,2,1};
    private static String[] mazePic = new String[20];
    private static GameTheme theme = new GameTheme();
    private static GameModel model = new GameModel();
    private static GameController controller = new GameController(model, theme);
    private static short a = 0, b = 0, prev = 0;
    private static short pair = 10;
    private static int level ;


    public GameMain() {
        showBackImage();
        getChildren().add(theme);
    }

    public void setLevel(int level) {
        GameMain.level = level;
    }

    private void showBackImage() {
        arrayPic();
        showPlayer();
        short rowIndex = 0, colIndex = 0;
        for (int i = 0; i < 20; i++) {
            controller.setName("general");
            controller.setRowIndex(rowIndex);
            controller.setColIndex(colIndex);
            controller.updateTheme();
            colIndex++;
            if (colIndex > 4) {
                colIndex = 0;
                rowIndex++;
            }
        }
    }

    public void clickAndTurn() {
        for (short i = 20; i < 40; i++) {     //the front image 20->40
            SequentialTransition seqTransition = animation(theme.getChildren().get(i));
            short finalI = i;
            final int[] cardShow = {0};
            theme.getChildren().get(i).setOnMouseClicked(event -> {
                if (cardShow[0] != 2) {
                    seqTransition.play();
                    cardShow[0]++;
                    if (a == 0) {
                        a += Integer.parseInt(mazePic[finalI - 20]);
                        prev = finalI;
                    } else {
                        if (finalI != prev){
                            b += Integer.parseInt(mazePic[finalI - 20]);
                            if (a == b) {
                                theme.getChildren().get(finalI).setVisible(false);
                                theme.getChildren().get(prev).setVisible(false);
                                pair--;
                                cardShow[0] = 0;
                            }
                            a = 0;
                            b = 0;
                            prev = 0;
                        }
                    }
                } else cardShow[0] = 0;
            });
        }
    }

    private SequentialTransition animation(Node backNode) {

        FadeTransition fade = new FadeTransition(Duration.millis(1), backNode);
        fade.setFromValue(1.0);
        fade.setToValue(0);

        PauseTransition pause = new PauseTransition(Duration.millis(lEVELSEC[level-1]*1000));

        FadeTransition popUp = new FadeTransition(Duration.millis(1), backNode);
        popUp.setFromValue(0);
        popUp.setToValue(1.0);

        return (new SequentialTransition(fade, pause, popUp));
    }

    private void arrayPic() {
        for (int i = 0; i < mazePic.length; i++) {
            if (i > 9)
                mazePic[i] = PLAYER[i - 10];
            else
                mazePic[i] = PLAYER[i];
        }
        for (int index = mazePic.length - 1; index > 0; index--) {
            int rand = (int) (Math.random() * index);
            String attempt = mazePic[index];
            mazePic[index] = mazePic[rand];
            mazePic[rand] = attempt;
        }
    }

    private void showPlayer() {
        short rowIndex = 0, colIndex = 0;
        for (String s : mazePic) {
            controller.setName(s);
            controller.setRowIndex(rowIndex);
            controller.setColIndex(colIndex);
            controller.updateTheme();
            colIndex++;
            if (colIndex > 4) {
                colIndex = 0;
                rowIndex++;
            }
        }
    }

    public boolean isMatch() {
        if (pair == 0){
            pair = 10;
            return true;
        }
        else{
            pair = 10;
            return false;
        }
    }

    public void startPoint(){
        for (short i = 20; i < 40; i++){
            SequentialTransition sequentialTransition = animation(theme.getChildren().get(i));
            sequentialTransition.play();
        }
    }

}
