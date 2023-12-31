package Enemies;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.AbilityListenerManager;
import Level.Enemy;
import Level.MapEntity;
import Level.MapEntityStatus;
import Level.Player;
import Utils.AirGroundState;
import Utils.Direction;
import Utils.Point;

import java.util.HashMap;

// This class is for the green dinosaur enemy that shoots fireballs
// It walks back and forth between two set points (startLocation and endLocation)
// Every so often (based on shootTimer) it will shoot a Beaker enemy
public class MadScientist2 extends Enemy {

    // start and end location defines the two points that it walks between
    // is only made to walk along the x axis and has no air ground state logic, so
    // make sure both points have the same Y value
    protected Point startLocation;
    protected Point endLocation;

    protected float movementSpeed = 1.5f;
    private Direction startFacingDirection;
    protected Direction facingDirection;
    protected AirGroundState airGroundState;

    // timer is used to determine how long dinosaur freezes in place before shooting
    // beaker
    protected int shootWaitTimer;

    // timer is used to determine when a beaker is to be shot out
    protected int shootTimer;

    // can be either WALK or SHOOT based on what the enemy is currently set to do
    protected Scientist2State scientistState;
    protected Scientist2State previousScientistState;

    public MadScientist2(Point startLocation, Point endLocation, Direction facingDirection) {
        super(startLocation.x, startLocation.y, new SpriteSheet(ImageLoader.load("madScientistWithBeaker.png"), 24, 24),
                "WALK_RIGHT");
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.startFacingDirection = facingDirection;
        this.initialize();
    }

    @Override
    public void initialize() {
        AbilityListenerManager.addEnemyListener(this);
        super.initialize();
        scientistState = Scientist2State.WALK;
        previousScientistState = scientistState;
        facingDirection = startFacingDirection;
        if (facingDirection == Direction.RIGHT) {
            currentAnimationName = "WALK_RIGHT";
        } else if (facingDirection == Direction.LEFT) {
            currentAnimationName = "WALK_LEFT";
        }
        airGroundState = AirGroundState.GROUND;

        // every certain number of frames, the beaker will be shot out
        shootWaitTimer = 65;
    }

    @Override
    public void update(Player player) {
        float startBound = startLocation.x;
        float endBound = endLocation.x;

        // if shoot timer is up and dinosaur is not currently shooting, set its state to
        // SHOOT
        if (shootWaitTimer == 0 && scientistState != Scientist2State.SHOOT_WAIT) {
            scientistState = Scientist2State.SHOOT_WAIT;
        } else {
            shootWaitTimer--;
        }

        // if dinosaur is walking, determine which direction to walk in based on facing
        // direction
        if (scientistState == Scientist2State.WALK) {
            if (facingDirection == Direction.RIGHT) {
                currentAnimationName = "WALK_RIGHT";
                moveXHandleCollision(movementSpeed);
            } else {
                currentAnimationName = "WALK_LEFT";
                moveXHandleCollision(-movementSpeed);
            }

            // if dinosaur reaches the start or end location, it turns around
            // dinosaur may end up going a bit past the start or end location depending on
            // movement speed
            // this calculates the difference and pushes the enemy back a bit so it ends up
            // right on the start or end location
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

        // if dinosaur is waiting to shoot, it first turns read for a set number of
        // frames
        // after this waiting period is over, the beaker is actually shot out
        if (scientistState == Scientist2State.SHOOT_WAIT) {
            if (previousScientistState == Scientist2State.WALK) {
                shootTimer = 65;
                currentAnimationName = facingDirection == Direction.RIGHT ? "SHOOT_RIGHT" : "SHOOT_LEFT";
            } else if (shootTimer == 0) {
                scientistState = Scientist2State.SHOOT;
            } else {
                shootTimer--;
            }
        }

        // this is for actually having the dinosaur spit out the beaker
        if (scientistState == Scientist2State.SHOOT) {
            // define where beaker will spawn on map (x location) relative to dinosaur
            // enemy's location
            // and define its movement speed
            int beakerX;
            float movementSpeed;
            if (currentAnimationName == "SHOOT_RIGHT") {
                beakerX = Math.round(getX()) + getWidth() - 30;
                movementSpeed = 2f;
            } else {
                beakerX = Math.round(getX() - 35);
                movementSpeed = -2f;
            }


            int beakerY = Math.round(getY()) - 20;

            float heightY = -2f;

            Beaker beaker = new Beaker(new Point(beakerX, beakerY), movementSpeed, heightY);



            map.addEnemy(beaker);

            scientistState = Scientist2State.WALK;

            shootWaitTimer = 130;
        }
        previousScientistState = scientistState;
        super.update(player);
        if (activeFireball != null) {
            if (intersects(activeFireball)) {
                killEnemy(this);
                // broadcast so the beaker disappears
                AbilityListenerManager.fireballKilledEnemy();
            }
        }
        if (activeBeaker != null) {
            if (intersects(activeBeaker)) {
                killEnemy(this);
                // broadcast so the beaker disappears
                AbilityListenerManager.beakerKilledEnemy();
            }
        }

    }

    @Override
    public void onEndCollisionCheckX(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
        // if dinosaur enemy collides with something on the x axis, it turns around and
        // walks the other way
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
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {
            {
                put("WALK_LEFT", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(0, 0), 14)
                                .withScale(3)
                                .withBounds(4, 2, 5, 13)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(0, 1), 14)
                                .withScale(3)
                                .withBounds(4, 2, 5, 13)
                                .build()
                });

                put("WALK_RIGHT", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(0, 0), 14)
                                .withScale(3)
                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                .withBounds(4, 2, 5, 13)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(0, 1), 14)
                                .withScale(3)
                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                .withBounds(4, 2, 5, 13)
                                .build()
                });

                put("SHOOT_LEFT", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(1, 0))
                                .withScale(3)
                                .withBounds(4, 2, 5, 13)
                                .build(),
                });

                put("SHOOT_RIGHT", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(1, 0))
                                .withScale(3)
                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                .withBounds(4, 2, 5, 13)
                                .build(),
                });
            }
        };
    }

    public enum Scientist2State {
        WALK, SHOOT_WAIT, SHOOT
    }
}
