package Maps;

import Enemies.BugEnemy;
import Enemies.DinosaurEnemy;
import Engine.ImageLoader;
import EnhancedMapTiles.EndLevelBox;
import EnhancedMapTiles.HorizontalMovingPlatform;
import EnhancedMapTiles.Key;
import EnhancedMapTiles.Door;
import GameObject.Rectangle;
import Level.*;
import Powerups.Checkpoint;
import Tilesets.CommonTileset;
import Tilesets.LabTileset;
import Utils.Point;
import Utils.Direction;
import Powerups.Coin;
import Powerups.FireballPU;
import Utils.Direction;

import java.util.ArrayList;

// Represents a test map to be used in a level
public class LabMap extends Map {
    public LabMap() {
        super("TestMap2.txt", new LabTileset());
        this.playerStartPosition = getMapTile(1, 8).getLocation();
    }
    
    @Override
    public ArrayList<Powerups> loadPowerups() {
        ArrayList<Powerups> coins = new ArrayList<>();

        Coin coin1 = new Coin(getMapTile(3, 4).getLocation(), null);
        coins.add(coin1);

        Coin coin2 = new Coin(getMapTile(16, 4).getLocation(), null);
        coins.add(coin2);

        Coin coin3 = new Coin(getMapTile(23,8).getLocation(), null);
        coins.add(coin3);

        Coin coin4 = new Coin(getMapTile(42, 8).getLocation(), null);
        coins.add(coin4);

        Coin coin5 = new Coin(getMapTile(32, 2).getLocation(), null);
        coins.add(coin5);

        Coin coin6 = new Coin(getMapTile(57, 6).getLocation(), null);
        coins.add(coin6);

        FireballPU fbPU = new FireballPU(getMapTile(7, 4).getLocation());
        coins.add(fbPU);

        // Checkpoint checkpoint = this.setCP(new Point(56, 6));
        Checkpoint checkpoint = new Checkpoint(getMapTile(56, 6).getLocation().subtractY(13));
        coins.add(checkpoint);

        return coins;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();
        EndLevelBox endLevelBox = new EndLevelBox(getMapTile(147, 5).getLocation());
        enhancedMapTiles.add(endLevelBox);

        
        Key key = new Key(getMapTile(21, 2).getLocation());
        enhancedMapTiles.add(key);


        //Doors
        Door door1 = new Door(
        ImageLoader.load("Door.png"),
        getMapTile(26, 7).getLocation(),
        getMapTile(26, 1).getLocation(),
        TileType.NOT_PASSABLE,
        3,
        new Rectangle(0, 6, 16, 32),
        Direction.RIGHT);
        enhancedMapTiles.add(door1);


        return enhancedMapTiles;
    }
    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();

        DinosaurEnemy dinosaurEnemy1 = new DinosaurEnemy(getMapTile(13, 8).getLocation(), getMapTile(18, 8).getLocation(), Direction.LEFT);
        enemies.add(dinosaurEnemy1);

        return enemies;
    }
    
    
}
