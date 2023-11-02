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


public class Checkpoint extends Powerups{
    
    public boolean hasCP;

    public Checkpoint(Point location) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("Checkpointv2.png"), 24, 24), "DEFAULT");
        hasCP = false;
    }

    @Override
    public void update(Player player) {

        super.update(player);
        

       
        // Check for player collision
        if (this.intersects(player)) {
            // tells player it reached checkpoint
            hasCP = true;
            map.setCp(true);
            
            System.out.print("true1");
            
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

    public boolean getCP() {
        return hasCP;
    }

    public Point getLoc() {
        return this.getLocation();
    }
}
