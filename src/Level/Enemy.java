package Level;

import GameObject.Frame;
import GameObject.SpriteSheet;
import Players.PlayerFireball;
import Level.AbilityListener;
import java.util.HashMap;

import Enemies.DinosaurEnemy;
import Enemies.Fireball;

// This class is a base class for all enemies in the game -- all enemies should extend from it
public class Enemy extends MapEntity {
    public boolean hitbyFB;
    protected PlayerFireball activeFireball = null;
    

    public Enemy(float x, float y, SpriteSheet spriteSheet, String startingAnimation) {
        super(x, y, spriteSheet, startingAnimation);
    }

    public Enemy(float x, float y, HashMap<String, Frame[]> animations, String startingAnimation) {
        super(x, y, animations, startingAnimation);
    }

    public Enemy(float x, float y, Frame[] frames) {
        super(x, y, frames);
    }

    public Enemy(float x, float y, Frame frame) {
        super(x, y, frame);
    }

    public Enemy(float x, float y) {
        super(x, y);
    }

    @Override
    public void initialize() {
        super.initialize();
    }

    public void update(Player player) {
        super.update();
        if (intersects(player)) {
            touchedPlayer(player);
        }
        if (activeFireball != null){
            if (intersects(activeFireball)){
                hitByFireball(this);
            }
        }
        /*if (intersects(this))
        {
            hitByFireball();
        }*/
    }

    public void hitByFireball(Enemy enemy){
        // This makes the enemy dissapear
        enemy.mapEntityStatus = MapEntityStatus.REMOVED;
    }

    // A subclass can override this method to specify what it does when it touches
    // the player
    public void touchedPlayer(Player player) {
        player.hurtPlayer(this);
    }
    
}
