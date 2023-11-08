package Level;

import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import GameObject.GameObject;
import GameObject.Sprite;
import GameObject.SpriteSheet;
import Players.Greg;
import Players.PlayerFireball;
import Utils.AirGroundState;
import Utils.AudioPlayer;
import Utils.Direction;

import java.awt.Color;
import java.nio.file.FileVisitOption;
import java.util.ArrayList;

import Level.TileType;
public abstract class Player extends GameObject {
    // values that affect player movement
    // these should be set in a subclass
    protected float walkSpeed = 3;
    protected float gravity = 0;
    protected float gravityX = 0;
    protected float jumpHeight = 0;
    protected float jumpDegrade = 0; 
    protected float terminalVelocityY = 0;
    protected float momentumYIncrease = 0;
    protected float terminalVelocityX = 0;
    protected float momentumXIncrease = 0;
    protected float normalWalkSpeed;
    protected float fireballSpeed;

    // values used to handle player movement
    protected float jumpForce = 0;
    protected float momentumY = 0;
    protected float momentumX = 0;
    protected float moveAmountX, moveAmountY;
    protected float lastAmountMovedX, lastAmountMovedY;
    protected boolean isFlipped = false;
    protected boolean isFlippedX = false;

    // values used to keep track of player's current state
    protected PlayerState playerState;
    protected PlayerState previousPlayerState;
    protected Direction facingDirection;
    protected AirGroundState airGroundState;
    protected AirGroundState previousAirGroundState;
    protected LevelState levelState;
    

    // classes that listen to player events can be added to this list
    protected ArrayList<PlayerListener> listeners = new ArrayList<>();

    // define keys
    protected KeyLocker keyLocker = new KeyLocker();
    protected Key MOVE_LEFT_KEY = Key.A;
    protected Key MOVE_RIGHT_KEY = Key.D;

    protected Key SPEED_UP_KEY = Key.SHIFT;
    protected Key SHOOT_KEY = Key.F;
    protected Key SUICIDE = Key.L;

    protected Key FLIP_KEY = Key.W;
    boolean R_Key_Pressed = false;
    public boolean hasKey = false;

    private AudioPlayer playerSFX = new AudioPlayer();

    // flags
    protected boolean isInvincible = false; // if true, player cannot be hurt by enemies (good for testing)
    protected boolean haveFBPowerup;
    private int coinCount;
    protected boolean fireballOnCooldown = false; // Whether fireball is on cooldown
    protected boolean waveOnCooldown = false; // Whether wave is on cooldown
    protected static int cooldownCounter; // Time for the fireball/wave to be on cooldown

    //for checkpoint
    protected boolean hasCP = false;

    public Player(SpriteSheet spriteSheet, float x, float y, String startingAnimationName) {
        super(spriteSheet, x, y, startingAnimationName);
        facingDirection = Direction.RIGHT;
        airGroundState = AirGroundState.AIR;
        previousAirGroundState = airGroundState;
        playerState = PlayerState.STANDING;
        previousPlayerState = playerState;
        levelState = LevelState.RUNNING;
        normalWalkSpeed = walkSpeed;
    }

    public void update() {
        moveAmountX = 0;
        moveAmountY = 0;
        // if player is currently playing through level (has not won or lost) NOTE FROM THOMAS: THIS IF STATEMENT THAT CALLS THE applyGravity(): FUNCTION MAY CAUSE FUTURE PROBLEMS WHEN WE INSTIUTE OUR GRAVITY FLIP
        if (levelState == LevelState.RUNNING) {
            applyGravity();

            // update player's state and current actions, which includes things like
            // determining how much it should move each frame and if its walking or jumping
            do {
                previousPlayerState = playerState;
                handlePlayerState();
            } while (previousPlayerState != playerState);

            previousAirGroundState = airGroundState;

            // move player with respect to map collisions based on how much player needs to
            // move this frame
            lastAmountMovedX = super.moveXHandleCollision(moveAmountX);
            lastAmountMovedY = super.moveYHandleCollision(moveAmountY);

            handlePlayerAnimation();

            updateLockedKeys();

            // update player's animation
            super.update();
        }

        // if player has beaten level
        else if (levelState == LevelState.LEVEL_COMPLETED) {
            updateLevelCompleted();
        }

        // if player has lost level
        else if (levelState == LevelState.PLAYER_DEAD) {
            updatePlayerDead();
        }
        //Suicide Button for testing purposes, no im not a sadist; SHOUTOUT TO RILEY FOR HELPING GREG END HIS SUFFERING
        if (Keyboard.isKeyDown(SUICIDE)) {
            levelState = LevelState.PLAYER_DEAD;
        }
        if (Keyboard.isKeyDown(FLIP_KEY) && !keyLocker.isKeyLocked(FLIP_KEY)) {
            flipWorld();
            keyLocker.lockKey(FLIP_KEY);

            try {
                playerSFX.load("Resources/Music/WAV/swing4.wav");
                playerSFX.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void flipWorld() {
        if (isFlipped) {
            isFlipped = false;
            gravity = -gravity;
            terminalVelocityY = -terminalVelocityY;
            //Reset JumpForce and momentumY
            jumpForce = 0;
            momentumY = 10;

        } else {
            isFlipped = true;
            R_Key_Pressed = true;
            // Reverse gravity
            gravity = -gravity;
            // Reverse terminal velocity
            terminalVelocityY = -terminalVelocityY;
            momentumY = -10;
        }
    }

    protected void flipWorldX() {
        if (isFlippedX) {
            isFlippedX = false;
            gravityX = -gravityX;
            terminalVelocityX = -terminalVelocityX;
            //Reset JumpForce and momentumY
            jumpForce = 0;
            momentumX = 10;

        } else {
            isFlipped = true;
            R_Key_Pressed = true;
            // Reverse gravity
            gravityX = -gravityX;
            // Reverse terminal velocity
            terminalVelocityX = -terminalVelocityX;
            momentumX = -10;
        }

        // Update player state based on new gravity direction
        if (airGroundState == AirGroundState.AIR) {
            //playerState = PlayerState.JUMPING;
        } else {
            playerState = PlayerState.STANDING;
        }
    }
    
    
    // add gravity to player, which is a downward force
    protected void applyGravity() {
        moveAmountY += gravity + momentumY;
    }

    protected void applyXGravity() {
        moveAmountX += gravityX + momentumX;
    }

    // based on player's current state, call appropriate player state handling
    // method
    protected void handlePlayerState() {
        switch (playerState) {
            case STANDING:
                playerStanding();
                break;
            case WALKING:
                playerWalking();
                break;
        }
    }


    

    // player STANDING state logic
    protected void playerStanding() {
        // if walk left or walk right key is pressed, player enters WALKING state
        if (Keyboard.isKeyDown(MOVE_LEFT_KEY) || Keyboard.isKeyDown(MOVE_RIGHT_KEY)) {
            playerState = PlayerState.WALKING;
        }

    }

    // player WALKING state logic
    protected void playerWalking() {
        // if walk left key is pressed, move player to the left
        if (Keyboard.isKeyDown(MOVE_LEFT_KEY)) {
            moveAmountX -= walkSpeed;
            facingDirection = Direction.LEFT;
        }

        // if walk right key is pressed, move player to the right
        else if (Keyboard.isKeyDown(MOVE_RIGHT_KEY)) {
            moveAmountX += walkSpeed;
            facingDirection = Direction.RIGHT;
        } else if (Keyboard.isKeyUp(MOVE_LEFT_KEY) && Keyboard.isKeyUp(MOVE_RIGHT_KEY)) {
            playerState = PlayerState.STANDING;
        }

        // if the SPEED_UP_KEY is pressed, set walkSpeed to 2x value
        if (Keyboard.isKeyDown(SPEED_UP_KEY)) {
            walkSpeed = normalWalkSpeed * 2.5f;
        } else {
            walkSpeed = normalWalkSpeed; // reset the walkSpeed to normal when SPEED_UP_KEY is not pressed
        }
    }
   
    // while player is in air, this is called, and will increase momentumY by a set
    // amount until player reaches terminal velocity
    protected void increaseMomentum() {
        momentumY += momentumYIncrease;
        if (momentumY > terminalVelocityY) {
            momentumY = terminalVelocityY;
        }
    }

    protected void updateLockedKeys() {

        if (Keyboard.isKeyUp(FLIP_KEY))
        {
            keyLocker.unlockKey(FLIP_KEY);
        }
    }
    // anything extra the player should do based on interactions can be handled here
    protected void handlePlayerAnimation() {
        if (playerState == PlayerState.STANDING) {
            // sets animation to a STAND animation based on which way player is facing
            if (isFlipped == true) {
                this.currentAnimationName = facingDirection == Direction.RIGHT ? "PLAYER_FLIPPED_STANDING_RIGHT" : "PLAYER_FLIPPED_STANDING_LEFT";
            } else if (isFlipped == false) {
                this.currentAnimationName = facingDirection == Direction.RIGHT ? "STAND_RIGHT" : "STAND_LEFT";
            }

        }
        if (playerState == PlayerState.WALKING) {
            if (isFlipped) {
                this.currentAnimationName = facingDirection == Direction.RIGHT ? "PLAYER_FLIPPED_WALK_RIGHT" : "PLAYER_FLIPPED_WALK_LEFT";
            } else {
                // sets animation to a WALK animation based on which way player is facing
                this.currentAnimationName = facingDirection == Direction.RIGHT ? "WALK_RIGHT" : "WALK_LEFT";
            }

        }
        // handles putting goggles on when standing in water
        // checks if the center of the player is currently touching a water tile
        int centerX = Math.round(getBounds().getX1()) + Math.round(getBounds().getWidth() / 2f);
        int centerY = Math.round(getBounds().getY1()) + Math.round(getBounds().getHeight() / 2f);
        MapTile currentMapTile = map.getTileByPosition(centerX, centerY);
        if (currentMapTile != null && currentMapTile.getTileType() == TileType.WATER) {
            this.currentAnimationName = facingDirection == Direction.RIGHT ? "STAND_RIGHT" : "STAND_LEFT";
        }
        
        if ((Keyboard.isKeyDown(SHOOT_KEY)) && (fireballOnCooldown == false) && (isInvincible == false) && getFBPowerup() == true){
            createFireball(getX(), getY(), getFacingDirection());
        }
    }

    

    @Override
    public void onEndCollisionCheckY(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
        // if player collides with a map tile below it, it is now on the ground
        // if player does not collide with a map tile below, it is in air

        if (entityCollidedWith instanceof MapTile)
        {
            MapTile mapTiles = (MapTile) entityCollidedWith;
            if (mapTiles != null && mapTiles.getTileType() == TileType.LETHAL) {
                levelState = LevelState.PLAYER_DEAD;
            }
        }
        
        if (direction == Direction.DOWN) {
            if (hasCollided) {
                momentumY = 0;
                airGroundState = AirGroundState.GROUND;
                if (isFlipped) {
                    playerState = PlayerState.STANDING; // Player stands on ceiling when flipped
                }
            } else {
                airGroundState = AirGroundState.AIR;
            }
        }
    
        // if player collides with map tile upwards, it means it was jumping and then
        // hit into a ceiling -- immediately stop upwards jump velocity
        else if (direction == Direction.UP) {
            if (hasCollided) {
                jumpForce = 0;
            }
        }
    }
    

    // other entities can call this method to hurt the player
    public void hurtPlayer(MapEntity mapEntity) {
        if (!isInvincible) {
            // if map entity is an enemy, kill player on touch
            if (mapEntity instanceof Enemy) {
                levelState = LevelState.PLAYER_DEAD;
            }
        }
    }

    // other entities can call this to tell the player they beat a level
    public void completeLevel() {
        levelState = LevelState.LEVEL_COMPLETED;
    }

    // if player has beaten level, this will be the update cycle
    public void updateLevelCompleted() {
        // if player is not on ground, player should fall until it touches the ground
        if (airGroundState != AirGroundState.GROUND && map.getCamera().containsDraw(this)) {
            currentAnimationName = "FALL_RIGHT";
            applyGravity();
            increaseMomentum();
            super.update();
            moveYHandleCollision(moveAmountY);
        }
        // move player to the right until it walks off screen
        else if (map.getCamera().containsDraw(this)) {
            currentAnimationName = "WALK_RIGHT";
            super.update();
            moveXHandleCollision(walkSpeed);
        } else {
            // tell all player listeners that the player has finished the level
            for (PlayerListener listener : listeners) {
                listener.onLevelCompleted();
            }
        }
    }

    // if player has died, this will be the update cycle
    public void updatePlayerDead() {
        // change player animation to DEATH
        if (!currentAnimationName.startsWith("DEATH")) {
            if (facingDirection == Direction.RIGHT) {
                currentAnimationName = "DEATH_RIGHT";
            } else {
                currentAnimationName = "DEATH_LEFT";
            }
            super.update();
        }
        // if death animation not on last frame yet, continue to play out death
        // animation
        else if (currentFrameIndex != getCurrentAnimation().length - 1) {
            super.update();
        }
        // if death animation on last frame (it is set up not to loop back to start),
        // player should continually fall until it goes off screen
        else if (currentFrameIndex == getCurrentAnimation().length - 1) {
            if (map.getCamera().containsDraw(this)) {
                moveY(3);
            } else {
                // tell all player listeners that the player has died in the level
                for (PlayerListener listener : listeners) {
                    listener.onDeath();
                }
            }
        }
    }

    public PlayerState getPlayerState() {
        return playerState;
    }

    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState;
    }

    public AirGroundState getAirGroundState() {
        return airGroundState;
    }

    public Direction getFacingDirection() {
        return facingDirection;
    }

    public void setFacingDirection(Direction facingDirection) {
        this.facingDirection = facingDirection;
    }

    public void setLevelState(LevelState levelState) {
        this.levelState = levelState;
    }

    public void addListener(PlayerListener listener) {
        listeners.add(listener);
    }

    public void setFBPowerup(boolean haveFBPowerup) {
        this.haveFBPowerup = haveFBPowerup;
    }
     public void createFireball(float x, float y, Direction direction){
        float movementSpeed;
        float spawnX =x;
        float spawnY = y;
        int existenceFrames = 50;
        if (direction == Direction.RIGHT){
            spawnX+= 30;
            movementSpeed = 4;
        }
        else {
            spawnX += 10;
            movementSpeed = -4;
        }
        // Create a fireball and add it to the map
        PlayerFireball fireball = new PlayerFireball(spawnX, spawnY, movementSpeed, existenceFrames);
        map.addEnemy(fireball);

        // Set the cooldown here (cooldown is in frames, remember 60 fps so 60 = 1 second)
        cooldownCounter = 250;
        fireballOnCooldown = true;
    }
    public boolean getFBPowerup() {
        return haveFBPowerup;
    }
    public LevelState getLevelState() {
        return levelState;
    }
    public void levelTwo() {
    }
    public void collectKey(EnhancedMapTiles.Key key) {
        hasKey = true;
    }
    
    // public void setCP(boolean hasCP) {
    //     this.hasCP = hasCP;
    // }

    // public boolean getCP() {
    //     return hasCP;
    // }
    
}
