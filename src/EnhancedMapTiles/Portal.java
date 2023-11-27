package EnhancedMapTiles;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.EnhancedMapTile;
import Level.Player;
import Level.TileType;
import Utils.AudioPlayer;
import Utils.Point;

import java.util.HashMap;

// This class is for the end level gold box tile
// when the player touches it, it will tell the player that the level has been completed
public class Portal extends EnhancedMapTile {

    private AudioPlayer portalSFX = new AudioPlayer();

    public Portal(Point location) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("PortalTest.png"), 31, 47), TileType.PASSABLE);
    }

    @Override
    public void update(Player player) {
        super.update(player);
        
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("DEFAULT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 40)
                        .withScale(2)
                        .withBounds(1, 1, 14, 14)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), 40)
                        .withScale(2)
                        .withBounds(1, 1, 14, 14)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 40)
                        .withScale(2)
                        .withBounds(1, 1, 14, 14)
                        .build()
            });
        }};
    }
}
