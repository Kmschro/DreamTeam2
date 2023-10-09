package Powerups;

import Builders.FrameBuilder;
import Enemies.Fireball;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Enemy;
import Level.MapEntity;
import Level.MapEntityStatus;
import Level.Player;
import Level.Powerup;
import Utils.AirGroundState;
import Utils.Direction;
import Utils.Point;

import java.util.HashMap;

public class FireballPowerup extends Powerup {
    private int existenceFrames;
    private int movementSpeed;

    public FireballPowerup(Point location) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("Fireball.png"), 7, 7), "DEFAULT");
        initialize();
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
    }

    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void applyPowerup(Player player) {
        // Implement specific behavior for the fireball powerup
        // For example, you can allow the player to shoot fireballs.
        player.gainFireballAbility(); 
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
    public void onEndCollisionCheckY(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
        // if bug is colliding with the ground, change its air ground state to GROUND
        // if it is not colliding with the ground, it means that it's currently in the air, so its air ground state is changed to AIR
        if (direction == Direction.DOWN) {
            if (hasCollided) {
                //
            } else {
                //
            }
        }
    }
    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("DEFAULT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 0))
                            .withScale(3)
                            .withBounds(1, 1, 5, 5)
                            .build()
            });
        }};
    
    }
    
    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }

 }