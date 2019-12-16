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

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class TimeController extends Label{
    private static final int MILI_SECONDS_PER_MINUTE = 60000;
    private static final int MILLIES = 1000;
    private Duration duration;
    private AnimationTimer timer;
    private long lastTimerCall;
    private boolean timeOver = false;
    private int remainingMili;

    public TimeController(){
        setText("00:00:00");
        setTextFill(Color.WHITE);
        displayTime();
    }

    private void setRemainingMili(int remainingMili) {
        this.remainingMili = remainingMili;
    }

    private void displayTime() {
        duration = Duration.minutes(1);             //Limit time 2 minutes
        lastTimerCall = System.nanoTime();
        timer = new AnimationTimer() {
            @Override
            public void handle(final long now) {
                if (now > lastTimerCall + 1_000_0001){
                    duration = duration.subtract(Duration.millis(16));
                    setRemainingMili((int) duration.toMillis());
                    int m = (remainingMili / MILI_SECONDS_PER_MINUTE);
                    int s = ((remainingMili - m * 60 * 1000 ) / MILLIES);
                    int mi = ((remainingMili - m * 60 * 1000 ) % MILLIES);

                    if (m == 0 && s == 0 && mi == 0){
                        setText("00:00:00");
                        timer.stop();
                        timeOver = true;
                    }
                    lastTimerCall = now;
                    if (s < 10)
                        setText("0" + m + ":" + "0"+s + ":" + mi/10);
                    else
                        setText("0" + m + ":" + s + ":" + mi/10);
                }
            }
        };
    }

    public void runTime(){
        timer.start();
    }

    public void stopTime(){
        timer.stop();
    }

    public boolean isTimeOver(){
        return timeOver;
    }
}
