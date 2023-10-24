package Screens;

import Engine.GraphicsHandler;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.LevelState;
import Level.Map;
import Level.Player;
import Level.PlayerListener;
import Maps.TestMap;
import Maps.LabMap;
import Maps.LabMap;
import Players.Greg;
import Powerups.Coin;
import SpriteFont.SpriteFont;
import java.awt.*;
import Utils.Point;
import Utils.AudioPlayer;

// This class is for when the platformer game is actually being played
public class PlayLevelScreen extends Screen implements PlayerListener {
    protected ScreenCoordinator screenCoordinator;
    protected Map map;
    protected Player player;
    protected PlayLevelScreenState playLevelScreenState;
    protected int screenTimer;
    protected LevelClearedScreen levelClearedScreen;
    protected LevelLoseScreen levelLoseScreen;
    protected boolean levelCompletedStateChangeStart;

    // protected SpriteFont coinLabel;
    // protected SpriteFont coinCountLabel;
    protected int coinCount;

    private AudioPlayer menuMusic = new AudioPlayer();

    public PlayLevelScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    public void initialize() {
        // define/setup map
        //this.map = new LabMap();
        this.map = new LabMap();
        // setup player
        this.player = new Greg(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        this.player.setMap(map);
        this.player.addListener(this);
        Point playerStartPosition = map.getPlayerStartPosition();
        this.player.setLocation(playerStartPosition.x, playerStartPosition.y);

        levelClearedScreen = new LevelClearedScreen();
        levelLoseScreen = new LevelLoseScreen(this);

        this.playLevelScreenState = PlayLevelScreenState.RUNNING;

        // coinLabel = new SpriteFont("COINS:", 0, 0, "Comic Sans", 15, Color.white);
        // coinLabel.setOutlineColor(Color.black);
        // coinLabel.setOutlineThickness(3);

        // coinCountLabel = new SpriteFont("0", 60, 0, "Comic Sans", 15, Color.white);
        // coinCountLabel.setOutlineColor(Color.black);
        // coinCountLabel.setOutlineThickness(3);

        try {
            menuMusic.load("Resources/Music/WAV/Fresh Start FULL.wav");
            menuMusic.playLooped();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void update() {

        
        

        // based on screen state, perform specific actions
        switch (playLevelScreenState) {
            // if level is "running" update player and map to keep game logic for the platformer level going
            case RUNNING:
                player.update();
                map.update(player);       
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
            // wait on level lose screen to make a decision (either resets level or sends player back to main menu)
            case LEVEL_LOSE:
                levelLoseScreen.update();
                break;
                    
        }

    }

    // public void printCoinCount(){

    //     coinCount++;
    //     String countString = Integer.toString(coinCount);
    //     coinCountLabel = new SpriteFont(countString, 60, 0, "Comic Sans", 15, Color.white);
    // }

    public void draw(GraphicsHandler graphicsHandler) {
        // based on screen state, draw appropriate graphics
        switch (playLevelScreenState) {
            case RUNNING:
                map.draw(graphicsHandler);
                player.draw(graphicsHandler);
                break;
            case LEVEL_COMPLETED:
                levelClearedScreen.draw(graphicsHandler);
                break;
            case LEVEL_LOSE:
                levelLoseScreen.draw(graphicsHandler);
                break;
        }

        // coinLabel.draw(graphicsHandler);
        // coinCountLabel.draw(graphicsHandler);
    }

    public PlayLevelScreenState getPlayLevelScreenState() {
        return playLevelScreenState;
    }

    /*
        //if (player == level_two)
        //load that screen
        if (player.stateWin == true) {
            this.player.levelTwo();
            this.map = new TestMap2();
            this.player = new Greg(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
            Point playerStartPosition = map.getPlayerStartPosition();
            this.player.setLocation(playerStartPosition.x, playerStartPosition.y);
            levelClearedScreen = new LevelClearedScreen();
            levelLoseScreen = new LevelLoseScreen(this);
            this.playLevelScreenState = PlayLevelScreenState.RUNNING;
            player.update();
            map.update(player);
        }
        */

        //counter for all levels
        protected int counter = 2;
    //change here for level two
    @Override
    public void onLevelCompleted() {
        if (playLevelScreenState != PlayLevelScreenState.LEVEL_COMPLETED && counter >= 2) {
            playLevelScreenState = PlayLevelScreenState.RUNNING;
            this.map = new LabMap();
            this.player.levelTwo();
            this.player = new Greg(4, 4);
            Point playerStartPosition = map.getPlayerStartPosition();
            this.player.setLocation(playerStartPosition.x, playerStartPosition.y);
            //player.update(); //causes error for some reason
            //map.update(player);
        }
        else if (playLevelScreenState != PlayLevelScreenState.LEVEL_COMPLETED && counter < 2) {
            playLevelScreenState = PlayLevelScreenState.LEVEL_COMPLETED;
            levelCompletedStateChangeStart = true;
        }
    }

    @Override
    public void onDeath() {
        if (playLevelScreenState != PlayLevelScreenState.LEVEL_LOSE) {
            playLevelScreenState = PlayLevelScreenState.LEVEL_LOSE;
        }
    }

    public void resetLevel() {
        initialize();
    }

    public void goBackToMenu() {
        screenCoordinator.setGameState(GameState.MENU);
    }

    // This enum represents the different states this screen can be in
    private enum PlayLevelScreenState {
        RUNNING, LEVEL_COMPLETED, LEVEL_LOSE
    }

    public void exit() {
        menuMusic.stop();
    }
    
}