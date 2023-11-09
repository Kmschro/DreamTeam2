package Enemies;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.AbilityListenerManager;
import Level.Enemy;
import Level.LevelState;
import Level.MapEntity;
import Level.MapEntityStatus;
import Level.Player;
import Utils.Direction;
import Utils.Point;

import java.util.HashMap;

// This class is for the fireball enemy that the DinosaurEnemy class shoots out
// it will travel in a straight line (x axis) for a set time before disappearing
// it will disappear early if it collides with a solid map tile
public class RadioactiveFireball extends Enemy {
    private float movementSpeed;
    private int existenceFrames;
    private float speedY;

    

    public RadioactiveFireball(Point location, float movementSpeed, int existenceFrames) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("Fireball.V3.png"), 7, 7), "DEFAULT");
        this.movementSpeed = movementSpeed;

        // how long the fireball will exist for before disappearing
        this.existenceFrames = existenceFrames;

        
    }
    @Override
    public void initialize() {
        // Add the firewisp as an enemy to listen for elemental abilities
        //AbilityListenerManager.addEnemyListener(this);
        super.initialize();
    }
    @Override
    public void update(Player player) {
        // if timer is up, set map entity status to REMOVED
        // the camera class will see this next frame and remove it permanently from the map
        if (existenceFrames == 0) {
            this.mapEntityStatus = MapEntityStatus.REMOVED;
        } else {
            // move fireball forward
            moveXHandleCollision(movementSpeed);
            super.update(player);
        }
        existenceFrames--;
        
    }

    @Override
    public void onEndCollisionCheckX(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
        // if fireball collides with anything solid on the x axis, it is removed
        if (hasCollided) {
            this.mapEntityStatus = MapEntityStatus.REMOVED;
                   // Check if the entity collided with is an instance of Fireball
        if (entityCollidedWith instanceof Fireball) {
                // Remove the collided fireball as well
                entityCollidedWith.mapEntityStatus = MapEntityStatus.REMOVED;
            }
        }
    }
    
    
    @Override
    public void touchedPlayer(Player player) {
        // if fireball touches player, it disappears
        super.touchedPlayer(player);
        this.mapEntityStatus = MapEntityStatus.REMOVED;
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("DEFAULT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 0))
                            .withScale(4)
                            .withBounds(1, 1, 5, 5)
                            .build()
            });
        }};
    }
}
