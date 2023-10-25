package Powerups;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Powerups;
import Level.MapEntityStatus;
import Level.Player;
import Utils.Point;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class FireballPU extends Powerups {

    public boolean hasFireballPowerup = false;

    public FireballPU(Point location) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("Fireball.V2.png"), 11, 11), "DEFAULT");

    }

    @Override
    public void update(Player player) {

        super.update(player);
        player.setFBPowerup(false);
        // Check for player collision
        if (this.intersects(player)) {
            // Increase the player's fireball speed
            player.setFBPowerup(true);
            Timer powerupTimer = new Timer();
            powerupTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    // Timer task to remove the power-up effect after 30 seconds
                    // player.setFBPowerup(false);
                    mapEntityStatus = MapEntityStatus.REMOVED;
                    powerupTimer.cancel();
                }
            }, 30000); // 30 seconds in milliseconds
            player.setFBPowerup(false);
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
