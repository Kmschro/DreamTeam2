package Powerups;

import java.util.HashMap;
import java.awt.*;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import SpriteFont.SpriteFont;
import GameObject.Frame;
import GameObject.Sprite;
import GameObject.SpriteSheet;
import GameObject.AnimatedSprite;
import Level.MapEntityStatus;
import Level.Player;
import Level.Powerups;
import Utils.Point;
import Utils.Colors;

public class Coin extends Powerups  {
    
    //variables to be used
    //protected SpriteFont coinLabel;
    protected SpriteFont coinNumber;
    //protected int coinCount;

    public Coin(Point location){
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("Coin.png"), 24, 24), "COIN_FRONT");

        // coinLabel = new SpriteFont("COINS:", 0, 0, "Comic Sans", 15, Color.white);
        // coinLabel.setOutlineColor(Color.black);
        // coinLabel.setOutlineThickness(3);

        //coinCount = 0;
    }

    @Override
    public void update(Player player) {
        super.update();
        if (intersects(player)) {
            this.mapEntityStatus = MapEntityStatus.REMOVED;
            //coinCount++;
            //printCoinCount();
        }

    }

    // private void printCoinCount() {
    //     System.out.println(coinLabel + " " + coinCount);
    // }

    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {
            {
                put("COIN_FRONT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0,0),5)
                    .withScale(3)
                    .withBounds(14, 14, 14, 14)
                    .build()
                });

                put("COIN_QUART_SIDE", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0,1),5)
                    .withScale(3)
                    .withBounds(14, 14, 14, 14)
                    .build()
                });

                put("COIN_HALF_SIDE", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0,2),5)
                    .withScale(3)
                    .withBounds(14, 14, 14, 14)
                    .build()
                });

                put("COIN_FULL_SIDE", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0,3),5)
                    .withScale(3)
                    .withBounds(14, 14, 14, 14)
                    .build()
                });

                put("COIN_BACK", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0,4),5)
                    .withScale(3)
                    .withBounds(14, 14, 14, 14)
                    .build()
                });
            }
        };
    }

    
    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);

        //coinLabel.draw(graphicsHandler);
    }
}
