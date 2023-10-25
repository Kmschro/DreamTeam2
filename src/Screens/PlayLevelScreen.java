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
import Utils.Point;
import Utils.AudioPlayer;
import Powerups.Checkpoint;

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
    protected boolean hasCP;

    private AudioPlayer menuMusic = new AudioPlayer();

    public PlayLevelScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
        hasCP = false;
        //cp = new Checkpoint(new Point(56, 6));
    }

    //set up checkpoint
    


    public void initialize() {
      
        

        // define/setup map
        //this.map = new LabMap();
        this.map = new LabMap();
        // setup player
        this.player = new Greg(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        /*if (map.getCp()) {
            System.out.println("TEST");
            this.player = new Greg(56, 6);
        }
        else {
        this.player = new Greg(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        }
        */
        this.player.setMap(map);
        this.player.addListener(this);
        Point playerStartPosition = map.getPlayerStartPosition();
        this.player.setLocation(playerStartPosition.x, playerStartPosition.y);

        levelClearedScreen = new LevelClearedScreen();
        levelLoseScreen = new LevelLoseScreen(this);

        this.playLevelScreenState = PlayLevelScreenState.RUNNING;

        try {
            menuMusic.load("Resources/Music/WAV/Fresh Start FULL.wav");
            menuMusic.playLooped();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void update() {

        /* 
        if(cp.getCP()) {
            hasCP = true;
            //System.out.print("true");
        }
        */

        // if (map.getCp()) {
        //     this.player = new Greg(56, 6);

        //     this.player.setMap(map);
        //     this.player.addListener(this);
        // }
        // else {
        // this.player = new Greg(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        // }
        

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

            this.player.setMap(map);
            this.player.addListener(this);

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


    

    //and !Map.hasCP vvvvv
    @Override
    public void onDeath() {
        if (playLevelScreenState != PlayLevelScreenState.LEVEL_LOSE) {
            //playLevelScreenState = PlayLevelScreenState.LEVEL_LOSE;

            if (map.getCp()) {
                // this.player = new Greg(56, 6);
        
                // this.player.setMap(map);
                // this.player.addListener(this);

                System.out.print("line 207");

                Point point = map.getMapTile(56, 6).getLocation().subtractY(13);
                
                this.player.setLocation(point.x, point.y);
            }
            else {
            System.out.print("line 211");
            this.player.setLocation(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
            }
            this.player.setLevelState(LevelState.RUNNING);
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