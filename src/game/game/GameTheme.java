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

import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

class GameTheme extends GridPane {

    void addImage(String string, short colIndex, short rowIndex){
        createPane(createImage(string),colIndex,rowIndex);
    }

    private ImageView createImage(String string) {
        ImageView imageView = new ImageView(string);
        imageView.setFitWidth(110);
        imageView.setFitHeight(110);
        imageView.setSmooth(true);
        return imageView;
    }

    private void createPane(ImageView imgv, short colIndex, short rowIndex){
        Pane pane = new Pane();
        pane.setStyle("-fx-border-color: black;");
        pane.getChildren().add(imgv);
        pane.setPadding(new Insets(0,0,0,0));
        add(pane,colIndex,rowIndex);
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(10,10,10,10));
    }
}
