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

// This class is a base class for all npcs in the game -- all npcs should extend from it
public class Timers extends MapEntity {
    private int timeInSeconds;
    private Timer timer;
    private SpriteFont timerLabel;
    protected SpriteFont message;

    public Timers(float x, float y, SpriteSheet spriteSheet, String startingAnimation) {
        super(x, y, spriteSheet, startingAnimation);
        this.message = createMessage(message);

    }

    public Timers(float x, float y, HashMap<String, Frame[]> animations, String startingAnimation) {
        super(x, y, animations, startingAnimation);

    }

    public Timers(float x, float y, Frame[] frames) {
        super(x, y, frames);

    }

    public Timers(float x, float y, Frame frame) {
        super(x, y, frame);

    }

    public Timers(float x, float y) {
        super(x, y);

    }

    protected SpriteFont createMessage(SpriteFont message) {
        this.message = message;
        return message;
    }

    public void update(Player player) {
        super.update();
        if (intersects(player)) {
            this.mapEntityStatus = MapEntityStatus.REMOVED;
        }
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}