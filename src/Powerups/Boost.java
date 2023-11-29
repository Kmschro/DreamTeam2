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
import Utils.AudioPlayer;
import Utils.Point;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class Boost extends Powerups{
    
    public Boost(Point location) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("BoostPowerUpV2.png"), 11, 11), "DEFAULT");
    }

    @Override
    public void update(Player player) {
        if (this.intersects(player)) {
            player.setBoost(true);
            this.mapEntityStatus = MapEntityStatus.REMOVED;
        }
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
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
}
