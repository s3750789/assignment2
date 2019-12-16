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

package game.sound;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;

import java.io.File;

public class SoundController extends Button{

    private AudioClip audio = new AudioClip(
            new File("src/sfx/jingle.mp3").toURI().toString());

    public SoundController(){
        playMusic();
    }

    private void playMusic() {
        audio.play();
        ImageView play = new ImageView("sound/play.png");
        play.setFitHeight(15);
        play.setFitWidth(15);
        ImageView mute = new ImageView("sound/mute.png");
        mute.setFitHeight(15);
        mute.setFitWidth(15);
        setText("");
        setGraphic(play);
        setOnMouseClicked(event -> {
            if (getGraphic() == mute ){
                audio.play();
                setGraphic(play);
            }
            else {
                audio.stop();
                setGraphic(mute);
            }
        });
    }
}
