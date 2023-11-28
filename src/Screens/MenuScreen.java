package Screens;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.Map;
import Maps.TitleScreenMap;
import SpriteFont.SpriteFont;
import Utils.AudioPlayer;
import javax.sound.sampled.FloatControl;

import java.awt.*;
import java.io.IOException;

// This is the class for the main menu screen
public class MenuScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected int currentMenuItemHovered = 0; // current menu item being "hovered" over
    protected int menuItemSelected = -1;
    protected SpriteFont playGame;
    protected SpriteFont credits;
    protected SpriteFont quit;
    protected SpriteFont confirmExit;
    protected Map background;
    protected int keyPressTimer;
    protected int pointerLocationX, pointerLocationY;
    protected KeyLocker keyLocker = new KeyLocker();
    private int TextXAlign = 450;
    private AudioPlayer menuMusic = new AudioPlayer();
    private AudioPlayer menuSFX = new AudioPlayer();

    public MenuScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    @Override
    public void initialize() {
        playGame = new SpriteFont("PLAY GAME", TextXAlign, 119, "Comic Sans", 30, new Color(49, 207, 240));
        playGame.setOutlineColor(Color.black);
        playGame.setOutlineThickness(3);
        credits = new SpriteFont("CREDITS", TextXAlign, 219, "Comic Sans", 30, new Color(49, 207, 240));
        credits.setOutlineColor(Color.black);
        credits.setOutlineThickness(3);
        quit = new SpriteFont("EXIT", TextXAlign, 319, "Comic Sans", 30, new Color(49, 207, 240));
        quit.setOutlineColor(Color.black);
        quit.setOutlineThickness(3);
        confirmExit = new SpriteFont("Press Enter to Confirm", TextXAlign, 319, "Comic Sans", 30, new Color(49, 207, 240));
        confirmExit.setOutlineColor(Color.black);
        confirmExit.setOutlineThickness(3);
        background = new TitleScreenMap();
        background.setAdjustCamera(false);
        keyPressTimer = 0;
        menuItemSelected = -1;
        keyLocker.lockKey(Key.ENTER);

        try {
            menuMusic.load("Resources/Music/WAV/Adventure Awaits FULL.wav");
            menuMusic.playLooped();

            FloatControl volumeControl = (FloatControl) menuMusic.getAudioClip().getControl(FloatControl.Type.MASTER_GAIN);
            float maxVolume = volumeControl.getMaximum();
            float minVolume = volumeControl.getMinimum();
            float range = maxVolume - minVolume;
            float volume = minVolume + (range * 0.75f); // 50% volume
            volumeControl.setValue(volume);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        
    
    public void update() {
        // update background map (to play tile animations)
        background.update(null);

        // if down or up is pressed, change menu item "hovered" over (blue square in front of text will move along with currentMenuItemHovered changing)
        if (Keyboard.isKeyDown(Key.DOWN) &&  keyPressTimer == 0) {
            keyPressTimer = 14;
            currentMenuItemHovered++;
            try {
                menuSFX.load("Resources/Music/WAV/button-124476.wav");
                menuSFX.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (Keyboard.isKeyDown(Key.UP) &&  keyPressTimer == 0) {
            keyPressTimer = 14;
            currentMenuItemHovered--;
            try {
                menuSFX.load("Resources/Music/WAV/button-124476.wav");
                menuSFX.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            if (keyPressTimer > 0) {
                keyPressTimer--;
            }
        }

        // if down is pressed on last menu item or up is pressed on first menu item, "loop" the selection back around to the beginning/end
        if (currentMenuItemHovered > 2) {
            currentMenuItemHovered = 0;
        } else if (currentMenuItemHovered < 0) {
            currentMenuItemHovered = 2;
        }

        // sets location for blue square in front of text (pointerLocation) and also sets color of spritefont text based on which menu item is being hovered
        if (currentMenuItemHovered == 0) {
            playGame.setColor(new Color(255, 215, 0));
            credits.setColor(new Color(49, 207, 240));
            quit.setColor(new Color(49, 217, 240));
            pointerLocationX = TextXAlign - 40;
            pointerLocationY = 130;
        } else if (currentMenuItemHovered == 1) {
            playGame.setColor(new Color(49, 207, 240));
            credits.setColor(new Color(255, 215, 0));
            quit.setColor(new Color(49, 207, 240));
            pointerLocationX = TextXAlign - 40;
            pointerLocationY = 230;
        } else if (currentMenuItemHovered == 2) {
            playGame.setColor(new Color(49, 207, 240));
            credits.setColor(new Color(49, 207, 240));
            quit.setColor(new Color(255, 215, 0));
            pointerLocationX = TextXAlign - 40;
            pointerLocationY = 330;
        }

        // if enter is pressed on menu item, change to appropriate screen based on which menu item was chosen
        if (Keyboard.isKeyUp(Key.ENTER)) {
            keyLocker.unlockKey(Key.ENTER);
        }
        if (!keyLocker.isKeyLocked(Key.ENTER) && Keyboard.isKeyDown(Key.ENTER)) {
            menuItemSelected = currentMenuItemHovered;
            if (menuItemSelected == 0) {
                exit();
                screenCoordinator.setGameState(GameState.LEVEL);
            } else if (menuItemSelected == 1) {
                exit();
                screenCoordinator.setGameState(GameState.CREDITS);
            } } else if (menuItemSelected == 2) {
                System.exit(0);
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        background.draw(graphicsHandler);
        playGame.draw(graphicsHandler);
        credits.draw(graphicsHandler);
        quit.draw(graphicsHandler);
        graphicsHandler.drawFilledRectangleWithBorder(pointerLocationX, pointerLocationY, 20, 20, new Color(49, 207, 240), Color.black, 2);
 
        if(menuItemSelected == 2) {
            graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(), new Color(0, 0, 0, 100));
			graphicsHandler.drawFilledRectangleWithBorder(pointerLocationX, pointerLocationY, 15, 15, new Color(49, 207, 240), Color.black, 2);
            confirmExit.draw(graphicsHandler);
        }
    }

    public void exit() {
        menuMusic.stop();
    }
}
