package Level;

import GameObject.Frame;
import GameObject.SpriteSheet;
import Players.PlayerFireball;
import Level.AbilityListener;
import Level.AbilityListenerManager;
import java.util.HashMap;

import Enemies.Beaker;
import Enemies.DinosaurEnemy;
import Enemies.Fireball;

// This class is a base class for all enemies in the game -- all enemies should extend from it
public class Enemy extends MapEntity implements AbilityListener {
    public boolean hitbyFB;
    protected PlayerFireball activeFireball = null;
    
    public boolean hitbyBeaker;
    protected Beaker activeBeaker = null;
    // These come from the listener and let the enemy know whether or not there is a fireball active
    @Override
    public void fireballSpawned(PlayerFireball fireball){
        activeFireball = fireball;
    }
    @Override
    public void fireballDespawned(){
        activeFireball = null;
    }
    @Override
    public void fireballKilledEnemy(){}

    @Override
    public void beakerSpawned(Beaker beaker) {
        activeBeaker = beaker;
    }
    @Override
    public void beakerDespawned() {
        activeBeaker = null;
    }
    @Override
    public void beakerKilledEnemy() {
    }

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
                killEnemy(this);
            }
        }
        if (activeBeaker != null){
            if (intersects(activeBeaker)){
                killEnemy(this);
            }
        }
        /*if (intersects(this))
        {
            hitByFireball();
        }*/
    }

    public void killEnemy(Enemy enemy){
    
        // This makes the enemy dissapear
        enemy.mapEntityStatus = MapEntityStatus.REMOVED;
    }

    // A subclass can override this method to specify what it does when it touches
    // the player
    public void touchedPlayer(Player player) {
        player.hurtPlayer(this);
    }
    
}
