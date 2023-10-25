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

interface CoinListener {
    void onCoinCollected(int coins);
}


public class Coin extends Powerups {
    
    //CoinListener field
    private CoinListener coinListener;

    //variables to be used
    protected SpriteFont coinNumber;
    public static int coinCount;

    public Coin(Point location, CoinListener coinListener){
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("Coin.png"), 24, 24), "COIN_FRONT");

        // coinLabel = new SpriteFont("COINS:", 0, 0, "Comic Sans", 15, Color.white);
        // coinLabel.setOutlineColor(Color.black);
        // coinLabel.setOutlineThickness(3);
        this.coinListener = coinListener;

        coinCount = 0;
    }

    @Override
    public void update(Player player) {
        super.update();
        //logic to update coin count and make it disappear
        //logic to check for player collision
        if (this.intersects(player)) {
            coinCount += 1;

            // Notify the listener about the coin collected
            if (coinListener != null) {
                coinListener.onCoinCollected(coinCount);
            }

            this.mapEntityStatus = MapEntityStatus.REMOVED;
            //printCoinCount();
        }

    }

    public void setCoinCount(int coinCount) {
        this.coinCount = coinCount;
    }

    public static int getCoinCount() {
        return coinCount;
    }

    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {
            {
                put("COIN_FRONT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0,0),5)
                    .withScale(3)
                    .withBounds(14, 14, 14, 14)
                    .build(),
                    new FrameBuilder(spriteSheet.getSprite(0,1),5)
                    .withScale(3)
                    .withBounds(14, 14, 14, 14)
                    .build(),
                    new FrameBuilder(spriteSheet.getSprite(0,2),5)
                    .withScale(3)
                    .withBounds(14, 14, 14, 14)
                    .build(),
                    new FrameBuilder(spriteSheet.getSprite(0,3),5)
                    .withScale(3)
                    .withBounds(14, 14, 14, 14)
                    .build(),
                    new FrameBuilder(spriteSheet.getSprite(0,4),5)
                    .withScale(3)
                    .withBounds(14, 14, 14, 14)
                    .build(),
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
