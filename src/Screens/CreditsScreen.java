package Screens;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.Map;
import Maps.TitleScreenMap;
import SpriteFont.SpriteFont;

import java.awt.*;

// This class is for the credits screen
public class CreditsScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected Map background;
    protected KeyLocker keyLocker = new KeyLocker();
    protected SpriteFont creditsLabel;
    protected SpriteFont createdByLabel;
    protected SpriteFont returnInstructionsLabel;

    public CreditsScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    @Override
    public void initialize() {
        // setup graphics on screen (background map, spritefont text)
        background = new TitleScreenMap();
        background.setAdjustCamera(false);
        creditsLabel = new SpriteFont("Credits", 15, 7, "Times New Roman", 30, Color.black);
        createdByLabel = new SpriteFont("Created by DreamTeam 2.0", 330, 121, "Times New Roman", 20, Color.black);
        returnInstructionsLabel = new SpriteFont("Press Enter to return to the menu", 20, 532, "Times New Roman", 30, Color.black);
        keyLocker.lockKey(Key.ENTER);
    }

    public void update() {
        background.update(null);

        if (Keyboard.isKeyUp(Key.ENTER)) {
            keyLocker.unlockKey(Key.ENTER);
        }

        // if enter is pressed, go back to main menu
        if (!keyLocker.isKeyLocked(Key.ENTER) && Keyboard.isKeyDown(Key.ENTER)) {
            screenCoordinator.setGameState(GameState.MENU);
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        background.draw(graphicsHandler);
        creditsLabel.draw(graphicsHandler);
        createdByLabel.draw(graphicsHandler);
        returnInstructionsLabel.draw(graphicsHandler);
    }
}
