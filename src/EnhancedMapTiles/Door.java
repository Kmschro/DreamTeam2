package EnhancedMapTiles;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import GameObject.Rectangle;
import Level.EnhancedMapTile;
import Level.Player;
import Level.TileType;
import Utils.AirGroundState;
import Utils.Direction;
import Utils.Point;

import java.awt.image.BufferedImage;

// This class is for a horizontal moving platform
// the platform will move back and forth between its start location and end location
// if the player is standing on top of it, the player will be moved the same amount as the platform is moving (so the platform will not slide out from under the player)
public class Door extends EnhancedMapTile {
    private Point startLocation;
    private Point endLocation;
    private float movementSpeed = 1f;
    private Direction startDirection;
    private Direction direction;

    public Door(BufferedImage image, Point startLocation, Point endLocation, TileType tileType, float scale, Rectangle bounds, Direction startDirection) {
        super(startLocation.x, startLocation.y, new FrameBuilder(image).withBounds(bounds).withScale(scale).build(), tileType);
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.startDirection = startDirection;
        this.initialize();
    }

    @Override
    public void initialize() {
        super.initialize();
        direction = startDirection;
    }

    @Override
    public void update(Player player) {
        float startBound = startLocation.x;
        float endBound = endLocation.x;

        // move platform left or right based on its current direction
        int moveAmountY = 0;
        if (player.hasKey == true) {
            moveAmountY += movementSpeed;
            moveY(moveAmountY);
        }
        //moveY(moveAmountY);
        super.update(player);
    }

    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }

}
