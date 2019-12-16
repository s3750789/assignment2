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

class GameController {
    private GameModel model;
    private GameTheme theme;

    GameController(GameModel model, GameTheme theme) {
        this.model = model;
        this.theme = theme;
    }

    void setName(String name){
        model.setName(name);
    }

    void setRowIndex(short rowIndex){
        model.setRowIndex(rowIndex);
    }

    void setColIndex(short colIndex){
        model.setColIndex(colIndex);
    }

    void updateTheme(){
        theme.addImage(model.getName(),model.getColIndex(),model.getRowIndex());
    }


}
