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

package game.level;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class LevelDisplay extends Label {
    //Create a label display level that user chooses

    public LevelDisplay(String text){
        addLevel(text);
        setPadding(new Insets(10,10,0,10));
    }

    private void addLevel(String text) {
        setText(text);
        setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD,
                FontPosture.REGULAR,40));
        setStyle("-fx-background-color: lightblue;");
        setAlignment(Pos.CENTER);
    }
}
