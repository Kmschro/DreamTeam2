package Level;

import javax.swing.*;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import SpriteFont.SpriteFont;
import Utils.Point;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.TimerTask;
import java.awt.*;
import Engine.GraphicsHandler;
import java.util.Timer;

public class MapTimer extends Timers {
    private int timeInSeconds;
    private Timer timer;
    private SpriteFont timerLabel;
    protected LevelState levelState;


    public MapTimer(Point location, int time) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("TimerBackground.png"), 20, 19), "DEFAULT");
        this.timeInSeconds = time;
        timerLabel = new SpriteFont(String.valueOf(timeInSeconds), (int) getX(), (int) getY(), "Arial", 50, Color.BLACK);
        timerLabel.setOutlineColor(Color.BLACK);
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timeInSeconds--;
                if (timeInSeconds >= 0) {
                    timerLabel.setText(String.valueOf(timeInSeconds));
                } else {
                    timer.cancel();
                    // Perform necessary actions when the timer ends
                }
            }
        }, 1000, 1000);
    }

    public SpriteFont createMessage(SpriteFont timerLabel)
    {
        this.timerLabel = timerLabel;
        return timerLabel;
    }
    @Override
    public void update(Player player) {
        super.update(player);
        timerLabel.setX(getX()); // Update the label position if the MapTimer position changes.
        timerLabel.setY(getY());
        if (timeInSeconds == 0) {
            levelState = levelState.PLAYER_DEAD;
        }

        
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {
            {
                put("DEFAULT", new Frame[]{
                        new FrameBuilder(spriteSheet.getSprite(0, 0))
                                .withScale(3)
                                .withBounds(1, 1, 5, 5)
                                .build()
                });
            }
        };
    
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        timerLabel.draw(graphicsHandler);
    }
}
