package Engine;

import java.io.IOException;

// Base Screen class
// This game engine runs off the idea of "screens", which are classes that contain their own update/draw methods for a particular piece of the game
// For example, there may be a "MenuScreen" or a "PlayGameScreen"
public abstract class Screen {
    public abstract void initialize();
    public abstract void update() throws IOException;
    public abstract void draw(GraphicsHandler graphicsHandler);
}
