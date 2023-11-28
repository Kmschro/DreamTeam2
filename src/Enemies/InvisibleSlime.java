package Enemies;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.AbilityListener;
import Level.AbilityListenerManager;
import Level.Enemy;
import Level.MapEntity;
import Level.MapEntityStatus;
import Level.Player;
import Level.PlayerState;
import Utils.AirGroundState;
import Utils.Direction;
import Utils.Point;

import java.util.HashMap;

// This class is for the Angry Slime that throws sticks
// It walks back and forth between two set points (startLocation and endLocation)
// Every so often (based on shootTimer) it will throw a stick enemy
public class InvisibleSlime extends Enemy implements AbilityListener {

    // start and end location defines the two points that it walks between
    // is only made to walk along the x axis and has no air ground state logic, so
    // make sure both points have the same Y value
    protected Point startLocation;
    protected Point endLocation;
    private float gravity = .5f;
    protected float movementSpeed = 1f;
    private Direction startFacingDirection;
    protected Direction facingDirection;
    protected AirGroundState airGroundState;

    // Flag
    protected boolean isInvincible = false; // if true, player cannot be hurt by enemies (good for testing)
    protected int isInvincibleCounter; // Invincible for a couple seconds after being hit

    // can be either WALK or SHOOT based on what the enemy is currently set to do
    protected SlimeState slimeState;
    protected SlimeState previousSlimeState;
    protected PlayerState playerState;
    protected String verticalFlip;

    public InvisibleSlime(Point startLocation, Point endLocation, Direction facingDirection) {
        super(startLocation.x, startLocation.y, new SpriteSheet(ImageLoader.load("SlimeEnemy.png"), 31, 24),
                "WALK_RIGHT");
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.startFacingDirection = facingDirection;
        this.initialize();
    }

    public InvisibleSlime(Point startLocation, Point endLocation, Direction facingDirection, String verticalFlip) {
        super(startLocation.x, startLocation.y, new SpriteSheet(ImageLoader.load("SlimeEnemy.png"), 31, 24),
                "UPSIDEDOWN_WALK_RIGHT");
        this.verticalFlip = verticalFlip;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.startFacingDirection = facingDirection;
        this.initialize();
    }

    @Override
    public void initialize() {
        // Add the Slime enemy as an enemy to listen for elemental abilities
        
        AbilityListenerManager.addEnemyListener(this);
        super.initialize();
        facingDirection = startFacingDirection;
        if (facingDirection == Direction.RIGHT) {
            currentAnimationName = "WALK_RIGHT";
        } else if (facingDirection == Direction.LEFT) {
            currentAnimationName = "WALK_LEFT";
        }
        airGroundState = AirGroundState.GROUND;
    }

    @Override
    public void update(Player player) {
        float startBound = startLocation.x;
        float endBound = endLocation.x;
        float moveAmountX = 0;
        float moveAmountY = 0;

        // add gravity (if in air, this will cause bug to fall)
        moveAmountY += gravity;

        // if on ground, walk forward based on facing direction
        if (airGroundState == AirGroundState.GROUND) {
            if (facingDirection == Direction.RIGHT) {
                currentAnimationName = "WALK_RIGHT";
                moveAmountX += movementSpeed;
            } else {
                currentAnimationName = "WALK_LEFT";
                moveAmountX -= movementSpeed;
            }

            if (getX1() + getWidth() >= endBound) {
                float difference = endBound - (getX2());
                moveXHandleCollision(-difference);
                facingDirection = Direction.LEFT;
            } else if (getX1() <= startBound) {
                float difference = startBound - getX1();
                moveXHandleCollision(difference);
                facingDirection = Direction.RIGHT;
            }
        }

        // move bug
        moveYHandleCollision(moveAmountY);
        moveXHandleCollision(moveAmountX);

        super.update(player);
        if (activeFireball != null){
            if (intersects(activeFireball)){
                killEnemy(this);
                // broadcast so the fireball disappears
                AbilityListenerManager.fireballKilledEnemy();
            }
        }
        if (activeBeaker != null){
            if (intersects(activeBeaker)){
                killEnemy(this);
                // broadcast so the fireball disappears
                AbilityListenerManager.beakerKilledEnemy();
            }
        }
    }

    @Override
    public void onEndCollisionCheckX(boolean hasCollided, Direction direction,  MapEntity entityCollidedWith) {
        // if bug has collided into something while walking forward,
        // it turns around (changes facing direction)
        if (hasCollided) {
            if (direction == Direction.RIGHT) {
                facingDirection = Direction.LEFT;
                currentAnimationName = "WALK_LEFT";
            } else {
                facingDirection = Direction.RIGHT;
                currentAnimationName = "WALK_RIGHT";
            }
        }
    }

    @Override
    public void onEndCollisionCheckY(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
        // if bug is colliding with the ground, change its air ground state to GROUND
        // if it is not colliding with the ground, it means that it's currently in the air, so its air ground state is changed to AIR
        if (direction == Direction.DOWN) {
            if (hasCollided) {
                airGroundState = AirGroundState.GROUND;
            } else {
                airGroundState = AirGroundState.AIR;
            }
        }
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {
            {
                put("WALK_LEFT", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(0, 0), 8)
                                .withScale(2)
                                .withBounds(5, 8, 20, 14)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(0, 1), 8)
                                .withScale(2)
                                .withBounds(5, 8, 20, 14)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(0, 2), 8)
                                .withScale(2)
                                .withBounds(5, 8, 20, 14)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(0, 3), 8)
                                .withScale(2)
                                .withBounds(5, 8, 20, 14)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(0, 4), 8)
                                .withScale(2)
                                .withBounds(5, 8, 20, 14)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(0, 5), 8)
                                .withScale(2)
                                .withBounds(5, 8, 20, 14)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(0, 6), 8)
                                .withScale(2)
                                .withBounds(5, 8, 20, 14)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(0, 7), 8)
                                .withScale(2)
                                .withBounds(5, 8, 20, 14)
                                .build()
                });

                put("WALK_RIGHT", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(0, 0), 8)
                                .withScale(2)
                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                .withBounds(5, 8, 20, 14)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(0, 1), 8)
                                .withScale(2)
                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                .withBounds(5, 8, 20, 14)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(0, 2), 8)
                                .withScale(2)
                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                .withBounds(5, 8, 20, 14)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(0, 3), 8)
                                .withScale(2)
                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                .withBounds(5, 8, 20, 14)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(0, 4), 8)
                                .withScale(2)
                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                .withBounds(5, 8, 20, 14)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(0, 5), 8)
                                .withScale(2)
                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                .withBounds(5, 8, 20, 14)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(0, 6), 8)
                                .withScale(2)
                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                .withBounds(5, 8, 20, 14)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(0, 7), 8)
                                .withScale(2)
                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                .withBounds(5, 8, 20, 14)
                                .build()
                });
            }
        };
    }

    public enum SlimeState {
        WALK
    }

}
