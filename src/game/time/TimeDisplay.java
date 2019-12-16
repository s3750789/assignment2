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

package game.time;

import javafx.animation.PathTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class TimeDisplay extends Pane {
    private PathTransition path ;
    private Rectangle rect;
    public TimeDisplay(){
        timeDisplayBar();
    }

    private void timeDisplayBar(){
        rect = new Rectangle(30,30);
        rect.setFill(Color.WHITE);
        Line line = new Line();
        line.setStartX(190);
        line.setEndX(90);
        line.setStrokeWidth(30);
        line.setStroke(Color.RED);
        rect.setVisible(false);

        path = new PathTransition(Duration.minutes(2),line,rect);

        Label label = new Label("Time");
        label.setFont(Font.font("Arial",30));
        label.setStyle("-fx-background-color: white; -fx-fill: black");
        getChildren().addAll(label,line,rect);
    }

    public void runBarTime(){
        path.play();
        rect.setVisible(true);
    }

    public void pauseBarTime(){
        path.pause();
    }
}
