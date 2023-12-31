package Level;

import Engine.GraphicsHandler;
import Engine.Key;
import Engine.Keyboard;
import GameObject.Frame;
import GameObject.SpriteSheet;
import SpriteFont.SpriteFont;

import java.util.HashMap;

// This class is a base class for all npcs in the game -- all npcs should extend from it
public class Powerups extends MapEntity implements PlayerListener {
    protected int talkedToTime; // how long after talking to NPC will textbox stay open -- use negative number
                                // to have it be infinite time
    protected int timer;
    private LevelState levelState;

    public Powerups(float x, float y, SpriteSheet spriteSheet, String startingAnimation) {
        super(x, y, spriteSheet, startingAnimation);

    }

    public Powerups(float x, float y, HashMap<String, Frame[]> animations, String startingAnimation) {
        super(x, y, animations, startingAnimation);

    }

    public Powerups(float x, float y, Frame[] frames) {
        super(x, y, frames);

    }

    public Powerups(float x, float y, Frame frame) {
        super(x, y, frame);

    }

    public Powerups(float x, float y) {
        super(x, y);

    }

    protected SpriteFont createMessage() {
        return null;
    }

    public void update(Player player) {
        levelState = player.getLevelState();
        super.update();
        if (intersects(player)) {
            this.mapEntityStatus = MapEntityStatus.REMOVED;
        
        }
        

    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }

    @Override
    public void onLevelCompleted() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onLevelCompleted'");
    }

    @Override
    public void onDeath() {
        this.mapEntityStatus = MapEntityStatus.ACTIVE;
    }
}