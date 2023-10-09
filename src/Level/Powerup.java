package Level;

import java.util.HashMap;

import GameObject.Frame;
import GameObject.SpriteSheet;
import Powerups.FireballPowerup;

public class Powerup extends MapEntity {
    protected int timer;
    
    public Powerup(float x, float y, SpriteSheet spriteSheet, String startingAnimation) {
        super(x, y, spriteSheet, startingAnimation);
    }

    public Powerup(float x, float y, HashMap<String, Frame[]> animations, String startingAnimation) {
        super(x, y, animations, startingAnimation);
    }

    public Powerup(float x, float y, Frame[] frames) {
        super(x, y, frames);
    }

    public Powerup(float x, float y, Frame frame) {
        super(x, y, frame);
    }

    public Powerup(float x, float y) {
        super(x, y);
    }

    @Override
    public void initialize() {
        super.initialize();
    }

    public void update(Player player) {
        super.update();
        if (intersects(player)) {
            applyPowerup(player);
        }
    }

    public void applyPowerup(Player player) {
        // Implement powerup logic here (e.g., increasing player's abilities)
        
        // This is a placeholder method; you can customize it for your game.
    }

}
