package Screens;

import Engine.GraphicsHandler;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.LevelState;
import Level.Map;
import Level.MapEntityStatus;
import Level.Player;
import Level.PlayerListener;
import Level.Powerups;
import Maps.FinalLevel;
import Maps.LabMap;
import Maps.LevelFive;
import Maps.LevelFour;
import Maps.LevelThree;
import Maps.LevelTwo;
import Maps.LabMap;
import Players.Greg;
import SpriteFont.SpriteFont;
import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import Powerups.Coin;
import Powerups.FireballPU;
import Utils.Point;
import Utils.AudioPlayer;
import Powerups.Checkpoint;

interface CoinListener {
    void onCoinCollected(int coins);
}

// This class is for when the platformer game is actually being played
public class PlayLevelScreen extends Screen implements PlayerListener, CoinListener {
    protected ScreenCoordinator screenCoordinator;
    protected Map map;
    protected Powerups fbPU;
    protected Player player;
    protected PlayLevelScreenState playLevelScreenState;
    protected int screenTimer;
    protected LevelClearedScreen levelClearedScreen;
    protected LevelLoseScreen levelLoseScreen;
    protected boolean levelCompletedStateChangeStart;
    protected boolean hasCP;
    private int timeInSeconds;
    protected Timer timer;
    private int powerUpTimeInSeconds; // Set the initial time for the power-up to 30 seconds
    private Timer powerUpTimer;
    protected Point point2;
    protected Point point3;
    protected Point point4;
    protected String currentMap;
    protected SpriteFont coinLabel;
    protected SpriteFont levelTimer;
    protected SpriteFont powerupTimer;
    protected SpriteFont gameDirections;
    private int coinCount;
    private boolean isBackToMenu = false;

    private static AudioPlayer menuMusic = new AudioPlayer();

    public PlayLevelScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
        hasCP = false;
        // cp = new Checkpoint(new Point(56, 6));
    }

    // set up checkpoint

    public void onCoinCollected(int coins) {
        coinCount = coins;
    }

    public void initialize() {
        currentMap = "map1";
        // define/setup map
        // this.map = new LabMap();
        this.map = new LabMap();

        // setup player
        this.player = new Greg(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        player.setFBPowerup(false);
        /*
         * if (map.getCp()) {
         * System.out.println("TEST");
         * this.player = new Greg(56, 6);
         * }
         * else {
         * this.player = new Greg(map.getPlayerStartPosition().x,
         * map.getPlayerStartPosition().y);
         * }
         */
        this.player.setMap(map);
        initializeTimer();
        this.player.addListener(this);
        Point playerStartPosition = map.getPlayerStartPosition();
        this.player.setLocation(playerStartPosition.x, playerStartPosition.y);

        levelClearedScreen = new LevelClearedScreen();
        levelLoseScreen = new LevelLoseScreen(this);

        this.playLevelScreenState = PlayLevelScreenState.RUNNING;
        gameDirections = new SpriteFont(
                "W : flips gravity,      D : move right,       A : move left,      SHIFT Key : Sprint,      F : use powerup  ",
                75, 50, "Comic Sans", 15,
                Color.white);
        gameDirections.setOutlineColor(Color.black);
        gameDirections.setOutlineThickness(3);

        powerupTimer = new SpriteFont("POWERUP TIMER: 0", 500, 0, "Comic Sans", 25, Color.white);
        powerupTimer.setOutlineColor(Color.black);
        powerupTimer.setOutlineThickness(3);
        powerUpTimer = new Timer();

        try {
            menuMusic.load("Resources/Music/WAV/Fresh Start FULL.wav");
            menuMusic.playLooped();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (backToMenu()) {
            menuMusic.stop();
        }

    }

        public void initializelevelTwo() {

        // define/setup map
        // this.map = new LabMap();
        this.map = new LevelTwo();

        // setup player
        this.player = new Greg(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        player.setFBPowerup(false);
        /*
         * if (map.getCp()) {
         * System.out.println("TEST");
         * this.player = new Greg(56, 6);
         * }
         * else {
         * this.player = new Greg(map.getPlayerStartPosition().x,
         * map.getPlayerStartPosition().y);
         * }
         */
        this.player.setMap(map);
        initializeTimer();
        this.player.addListener(this);
        Point playerStartPosition = map.getPlayerStartPosition();
        this.player.setLocation(playerStartPosition.x, playerStartPosition.y);

        levelClearedScreen = new LevelClearedScreen();
        levelLoseScreen = new LevelLoseScreen(this);

        this.playLevelScreenState = PlayLevelScreenState.RUNNING;
        gameDirections = new SpriteFont(
                "W : flips gravity,      D : move right,       A : move left,      SHIFT Key : Sprint,      F : use powerup  ",
                75, 50, "Comic Sans", 15,
                Color.white);
        gameDirections.setOutlineColor(Color.black);
        gameDirections.setOutlineThickness(3);

        powerupTimer = new SpriteFont("POWERUP TIMER: 0", 500, 0, "Comic Sans", 25, Color.white);
        powerupTimer.setOutlineColor(Color.black);
        powerupTimer.setOutlineThickness(3);
        powerUpTimer = new Timer();

        try {
            menuMusic.load("Resources/Music/WAV/Fresh Start FULL.wav");
            menuMusic.playLooped();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (backToMenu()) {
            menuMusic.stop();
        }

    }
        public void initializelevelThree() {

        // define/setup map
        // this.map = new LabMap();
        this.map = new LevelThree();

        // setup player
        this.player = new Greg(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        player.setFBPowerup(false);
        /*
         * if (map.getCp()) {
         * System.out.println("TEST");
         * this.player = new Greg(56, 6);
         * }
         * else {
         * this.player = new Greg(map.getPlayerStartPosition().x,
         * map.getPlayerStartPosition().y);
         * }
         */
        this.player.setMap(map);
        initializeTimer();
        this.player.addListener(this);
        Point playerStartPosition = map.getPlayerStartPosition();
        this.player.setLocation(playerStartPosition.x, playerStartPosition.y);

        levelClearedScreen = new LevelClearedScreen();
        levelLoseScreen = new LevelLoseScreen(this);

        this.playLevelScreenState = PlayLevelScreenState.RUNNING;
        gameDirections = new SpriteFont(
                "W : flips gravity,      D : move right,       A : move left,      SHIFT Key : Sprint,      F : use powerup  ",
                75, 50, "Comic Sans", 15,
                Color.white);
        gameDirections.setOutlineColor(Color.black);
        gameDirections.setOutlineThickness(3);

        powerupTimer = new SpriteFont("POWERUP TIMER: 0", 500, 0, "Comic Sans", 25, Color.white);
        powerupTimer.setOutlineColor(Color.black);
        powerupTimer.setOutlineThickness(3);
        powerUpTimer = new Timer();

        try {
            menuMusic.load("Resources/Music/WAV/Fresh Start FULL.wav");
            menuMusic.playLooped();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (backToMenu()) {
            menuMusic.stop();
        }

    }

    public void initializelevelFour() {

        // define/setup map
        // this.map = new LabMap();
        this.map = new LevelFour();

        // setup player
        this.player = new Greg(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        player.setFBPowerup(false);
        /*
         * if (map.getCp()) {
         * System.out.println("TEST");
         * this.player = new Greg(56, 6);
         * }
         * else {
         * this.player = new Greg(map.getPlayerStartPosition().x,
         * map.getPlayerStartPosition().y);
         * }
         */
        this.player.setMap(map);
        initializeTimer();
        this.player.addListener(this);
        Point playerStartPosition = map.getPlayerStartPosition();
        this.player.setLocation(playerStartPosition.x, playerStartPosition.y);

        levelClearedScreen = new LevelClearedScreen();
        levelLoseScreen = new LevelLoseScreen(this);

        this.playLevelScreenState = PlayLevelScreenState.RUNNING;
        gameDirections = new SpriteFont(
                "W : flips gravity,      D : move right,       A : move left,      SHIFT Key : Sprint,      F : use powerup  ",
                75, 50, "Comic Sans", 15,
                Color.white);
        gameDirections.setOutlineColor(Color.black);
        gameDirections.setOutlineThickness(3);

        powerupTimer = new SpriteFont("POWERUP TIMER: 0", 500, 0, "Comic Sans", 25, Color.white);
        powerupTimer.setOutlineColor(Color.black);
        powerupTimer.setOutlineThickness(3);
        powerUpTimer = new Timer();

        try {
            menuMusic.load("Resources/Music/WAV/Fresh Start FULL.wav");
            menuMusic.playLooped();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (backToMenu()) {
            menuMusic.stop();
        }

    }

    public void initializelevelFive() {

        // define/setup map
        // this.map = new LabMap();
        this.map = new LevelFive();

        // setup player
        this.player = new Greg(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        player.setFBPowerup(false);
        /*
         * if (map.getCp()) {
         * System.out.println("TEST");
         * this.player = new Greg(56, 6);
         * }
         * else {
         * this.player = new Greg(map.getPlayerStartPosition().x,
         * map.getPlayerStartPosition().y);
         * }
         */
        this.player.setMap(map);
        initializeTimer();
        this.player.addListener(this);
        Point playerStartPosition = map.getPlayerStartPosition();
        this.player.setLocation(playerStartPosition.x, playerStartPosition.y);

        levelClearedScreen = new LevelClearedScreen();
        levelLoseScreen = new LevelLoseScreen(this);

        this.playLevelScreenState = PlayLevelScreenState.RUNNING;
        gameDirections = new SpriteFont(
                "W : flips gravity,      D : move right,       A : move left,      SHIFT Key : Sprint,      F : use powerup  ",
                75, 50, "Comic Sans", 15,
                Color.white);
        gameDirections.setOutlineColor(Color.black);
        gameDirections.setOutlineThickness(3);

        powerupTimer = new SpriteFont("POWERUP TIMER: 0", 500, 0, "Comic Sans", 25, Color.white);
        powerupTimer.setOutlineColor(Color.black);
        powerupTimer.setOutlineThickness(3);
        powerUpTimer = new Timer();

        try {
            menuMusic.load("Resources/Music/WAV/Fresh Start FULL.wav");
            menuMusic.playLooped();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (backToMenu()) {
            menuMusic.stop();
        }

    }

    private void initializeTimer() {

        timer = new Timer();
        timeInSeconds = 76;
        levelTimer = new SpriteFont("LEVEL TIMER: " + String.valueOf(timeInSeconds), 200, 0, "Comic Sans", 25,
                Color.white);
        levelTimer.setOutlineColor(Color.black);
        levelTimer.setOutlineThickness(3);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timeInSeconds--;
                if (timeInSeconds > 0) {
                    levelTimer.setText("LEVEL TIMER: " + String.valueOf(timeInSeconds));
                } else {
                    timer.cancel();
                    // Perform necessary actions when the timer ends
                }
            }
        }, 0, 1000); // Update the timer every 1000 milliseconds (1 second)
    }

    private void initializePowerupTimer() {
        powerUpTimer = new Timer();
        powerUpTimeInSeconds = 31;
        powerupTimer.setText("POWERUP TIMER: " + String.valueOf(powerUpTimeInSeconds));
        powerupTimer.setOutlineColor(Color.black);
        powerUpTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                powerUpTimeInSeconds--;
                if (powerUpTimeInSeconds >= 0) {
                    powerupTimer.setText("POWERUP TIMER: " + String.valueOf(powerUpTimeInSeconds));
                } else {
                    powerUpTimer.cancel();
                    player.setFBPowerup(false);
                    // Perform necessary actions when the power-up timer ends
                }
            }
        }, 0, 1000); // Update the power-up timer every 1000 milliseconds (1 second)

    }

    public void update() {
        // int coinCount = 0;// = getCoinCount();
        coinLabel = new SpriteFont("COINS: " + String.valueOf(coinCount), 0, 0, "Comic Sans", 25, Color.white);
        coinLabel.setOutlineColor(Color.black);
        coinLabel.setOutlineThickness(3);

        /*
         * if(cp.getCP()) {
         * hasCP = true;
         * //System.out.print("true");
         * }
         */

        // if (map.getCp()) {
        // this.player = new Greg(56, 6);

        // this.player.setMap(map);
        // this.player.addListener(this);
        // }
        // else {
        // this.player = new Greg(map.getPlayerStartPosition().x,
        // map.getPlayerStartPosition().y);
        // }
        if (timeInSeconds == 0) {
            playLevelScreenState = PlayLevelScreenState.LEVEL_LOSE;
        }

        if (player.getFBPowerup()) {
            if (powerUpTimer == null) {
                initializePowerupTimer();
            }
        } else {
            if (powerUpTimer != null) {
                powerUpTimer.cancel();
            }
            powerUpTimer = null;
        }
        // player.setFBPowerup(false);
        // Reset the power-up flag
        // based on screen state, perform specific actions
        switch (playLevelScreenState) {
            // if level is "running" update player and map to keep game logic for the
            // platformer level going
            case RUNNING:
            
                player.update();
                map.update(player);
                coinCount = Coin.getCoinCount();

                break;
            // if level has been completed, bring up level cleared screen
            case LEVEL_COMPLETED:
                if (levelCompletedStateChangeStart) {
                    screenTimer = 130;
                    levelCompletedStateChangeStart = false;
                } else {
                    levelClearedScreen.update();
                    screenTimer--;
                    if (screenTimer == 0) {

                        goBackToMenu();
                    }
                }

                exit();
                break;
            // wait on level lose screen to make a decision (either resets level or sends
            // player back to main menu)
            case LEVEL_LOSE:
                levelLoseScreen.update();
                exit();
                break;
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        // based on screen state, draw appropriate graphics
        switch (playLevelScreenState) {
            case RUNNING:
                map.draw(graphicsHandler);
                player.draw(graphicsHandler);
                coinLabel.draw(graphicsHandler);
                powerupTimer.draw(graphicsHandler);
                levelTimer.draw(graphicsHandler);
                gameDirections.draw(graphicsHandler);
                break;
            case LEVEL_COMPLETED:
                levelClearedScreen.draw(graphicsHandler);
                break;
            case LEVEL_LOSE:
                levelLoseScreen.draw(graphicsHandler);
                break;
        }

    }

    public PlayLevelScreenState getPlayLevelScreenState() {
        return playLevelScreenState;
    }

    /*
     * //if (player == level_two)
     * //load that screen
     * if (player.stateWin == true) {
     * this.player.levelTwo();
     * this.map = new TestMap2();
     * this.player = new Greg(map.getPlayerStartPosition().x,
     * map.getPlayerStartPosition().y);
     * Point playerStartPosition = map.getPlayerStartPosition();
     * this.player.setLocation(playerStartPosition.x, playerStartPosition.y);
     * levelClearedScreen = new LevelClearedScreen();
     * levelLoseScreen = new LevelLoseScreen(this);
     * this.playLevelScreenState = PlayLevelScreenState.RUNNING;
     * player.update();
     * map.update(player);
     * }
     */

    // counter for all levels
    public static int counter = 2;

    // change here for level two
    @Override
    public void onLevelCompleted() {
        if (playLevelScreenState != PlayLevelScreenState.LEVEL_COMPLETED && counter == 2) {
            playLevelScreenState = PlayLevelScreenState.RUNNING;
            this.map = new LevelTwo();
            this.player.levelTwo();
            currentMap = "map2";
            this.player = new Greg(4, 4);

            this.player.setMap(map);
            this.player.addListener(this);

            Point playerStartPosition = map.getPlayerStartPosition();
            this.player.setLocation(playerStartPosition.x, playerStartPosition.y);
            // player.update(); //causes error for some reason
            // map.update(player);

            counter = counter + 1;
            System.out.print(counter);
        } else if (playLevelScreenState != PlayLevelScreenState.LEVEL_COMPLETED && counter == 3) {
            playLevelScreenState = PlayLevelScreenState.RUNNING;
            this.map = new LevelThree();
            this.player.levelThree();
            currentMap = "map3";
            this.player = new Greg(4, 4);

            this.player.setMap(map);
            this.player.addListener(this);

            Point playerStartPosition = map.getPlayerStartPosition();
            this.player.setLocation(playerStartPosition.x, playerStartPosition.y);
            // player.update(); //causes error for some reason
            // map.update(player);

            counter = counter + 1;
        } else if (playLevelScreenState != PlayLevelScreenState.LEVEL_COMPLETED && counter < 2) {
            if (timer != null) {
                timer.cancel();
            }
            initializeTimer();
            playLevelScreenState = PlayLevelScreenState.LEVEL_COMPLETED;
            levelCompletedStateChangeStart = true;
        }
    }

    // and !Map.hasCP vvvvv
    @Override
    public void onDeath() {
        
        //this.map.loadEnemies();
        if (playLevelScreenState != PlayLevelScreenState.LEVEL_LOSE) {
           
            //map.loadPowerups();
            // playLevelScreenState = PlayLevelScreenState.LEVEL_LOSE;
            // FireballPU fbPU = new FireballPU(getMapTile(7, 4).getLocation());
            if (timer != null) {
                timer.cancel();
            }
            /*if (map.getCp()) {
                */ 
                // this.player = new Greg(56, 6);

                // this.player.setMap(map);
                // this.player.addListener(this);

                /*System.out.print("line 207");
                Point point = map.getMapTile(4, 3).getLocation();
                if (counter == 1) {
                    Point point2 = map.getMapTile(56, 6).getLocation();
                } else if (counter == 2) {
                    Point point3 = map.getMapTile(1, 3).getLocation();
                }
                timer.cancel();
                if (counter == 1) {
                    this.player.setLocation(point2.x, point2.y);
                } else if (counter == 2) {
                    this.player.setLocation(point.x, point.y);
                }
                */
                if (powerUpTimer != null) {
                    powerUpTimer.cancel();
                }

                System.out.print("line 211");
                this.player.setLocation(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
                powerUpTimeInSeconds = 0;
                player.setFBPowerup(false);
                powerupTimer.setText("POWERUP TIMER: " + String.valueOf(powerUpTimeInSeconds));
                exit();
                if (currentMap == "map1")
                {
                    initialize();
                }
                else if (currentMap == "map2")
                {
                    initializelevelTwo();
                }
                else if (currentMap == "map3")
                {
                    initialize();
                }
                else if (currentMap == "map4")
                {
                    initialize();
                }
                else if (currentMap == "map5")
                {
                    initialize();
                }
                else if (currentMap == "map6")
                {
                    initialize();
                }
            //} else {
                // resetLevel();
                //ArrayList<Powerups> powerups = this.map.loadPowerups();
                //map.addPowerups(powerups);

            //}

            this.player.setLevelState(LevelState.RUNNING);
            // playLevelScreenState = PlayLevelScreenState.LEVEL_LOSE;
            // reset the timer to the original time value
            // Also cancel the powerUpTimer if it's running
            /*
             * if (timer != null) {
             * timer.cancel();
             * }
             */
            

            //initializeTimer();

    }

    }
    public void resetLevel() {
        map.loadPowerups();
        initialize();

    }

    public void goBackToMenu() {
        screenCoordinator.setGameState(GameState.MENU);
        isBackToMenu = true;
    }

    // This enum represents the different states this screen can be in
    private enum PlayLevelScreenState {
        RUNNING, LEVEL_COMPLETED, LEVEL_LOSE
    }

    public static void exit() {
        menuMusic.stop();
    }

    public boolean backToMenu() {
        return isBackToMenu;
    }
}
