package Powerups;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Powerups;
import SpriteFont.SpriteFont;
import Level.MapEntityStatus;
import Level.Player;
import Utils.Point;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class FireballPU extends Powerups {

    public boolean hasFireballPowerup;
    protected Timer timer;

    public FireballPU(Point location) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("Fireball.V2.png"), 11, 11), "DEFAULT");

    }

    @Override
    public void update(Player player) {

        super.update(player);
        if (this.intersects(player) && !hasFireballPowerup) {
            player.setFBPowerup(true);
            hasFireballPowerup = true;
        }
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {
            {
                put("DEFAULT", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(0, 0))
                                .withScale(3)
                                .withBounds(1, 1, 5, 5)
                                .build()
                });
            }
        };
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}
