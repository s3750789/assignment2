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

class GameModel {
    private String name;
    private short colIndex, rowIndex;

    GameModel() {}

    void setName(String name) {
        this.name = "image/"+name+".png";
    }

    void setColIndex(short colIndex) {
        this.colIndex = colIndex;
    }

    void setRowIndex(short rowIndex) {
        this.rowIndex = rowIndex;
    }

    String getName() {
        return name;
    }

    short getColIndex() {
        return colIndex;
    }

    short getRowIndex() {
        return rowIndex;
    }



}
