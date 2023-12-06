package Maps;

import Enemies.BugEnemy;
import Enemies.DinosaurEnemy;
import Enemies.FlyingBug;
import Enemies.MadScientist2;
import Enemies.RadioactiveCat;
import Engine.ImageLoader;
import EnhancedMapTiles.EndLevelBox;
import EnhancedMapTiles.HorizontalMovingPlatform;
import EnhancedMapTiles.Key;
import EnhancedMapTiles.Portal;
import EnhancedMapTiles.Door;
import GameObject.Rectangle;
import Level.*;
import Powerups.Boost;
import Powerups.Checkpoint;
import Tilesets.CommonTileset;
import Tilesets.LabTileset;
import Utils.Point;
import Utils.Direction;
import Powerups.Coin;
import Powerups.FireballPU;
import Screens.PlayLevelScreen;
import Utils.Direction;

import java.util.ArrayList;

// Represents a test map to be used in a level
public class LabMap extends Map {
    public LabMap() {
        super("LabMap.txt", new LabTileset());
        this.playerStartPosition = getMapTile(2, 8).getLocation();
    }


    @Override
    public ArrayList<Powerups> loadPowerups() {
        ArrayList<Powerups> coins = new ArrayList<>();

        Coin coin1 = new Coin(getMapTile(3, 4).getLocation(), null);
        coins.add(coin1);

        Coin coin2 = new Coin(getMapTile(16, 4).getLocation(), null);
        coins.add(coin2);

        Coin coin3 = new Coin(getMapTile(23, 8).getLocation(), null);
        coins.add(coin3);

        // Coin coin4 = new Coin(getMapTile(42, 8).getLocation(), null);
        // coins.add(coin4);

        Coin coin5 = new Coin(getMapTile(32, 2).getLocation(), null);
        coins.add(coin5);

        Coin coin6 = new Coin(getMapTile(57, 6).getLocation(), null);
        coins.add(coin6);

        FireballPU fbPU = new FireballPU(getMapTile(7, 4).getLocation());
        coins.add(fbPU);

        Boost boost = new Boost((getMapTile(11, 7).getLocation()));
        coins.add(boost);

        // Checkpoint checkpoint = this.setCP(new Point(56, 6));
        ArrayList<Powerups> checkpoints = new ArrayList<>();
        Checkpoint checkpoint = new Checkpoint(getMapTile(56, 6).getLocation());
        checkpoints.add(checkpoint);

        Boost boost = new Boost((getMapTile(11, 7).getLocation()));
        coins.add(boost);

        return coins;
        
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();
        EndLevelBox endLevelBox = new EndLevelBox(getMapTile(144, 6).getLocation());
        Portal portal = new Portal(getMapTile(149, 5).getLocation());
        enhancedMapTiles.add(portal);
        enhancedMapTiles.add(endLevelBox);

        
        Key key = new Key(getMapTile(21, 2).getLocation());
        enhancedMapTiles.add(key);

        
        Door door1 = new Door(
        ImageLoader.load("Door.png"),
        getMapTile(26, 7).getLocation(),
        getMapTile(26, -0).getLocation(),
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

        DinosaurEnemy dinosaurEnemy1 = new DinosaurEnemy(getMapTile(13, 8).getLocation(),
                getMapTile(18, 8).getLocation(), Direction.LEFT);
        enemies.add(dinosaurEnemy1);
        FlyingBug fb1 = new FlyingBug(getMapTile(31, 2).getLocation(),
                getMapTile(38, 2).getLocation(), Direction.LEFT);
        enemies.add(fb1);

        /*
         * RadioactiveCat cat = new RadioactiveCat(getMapTile(42, 8).getLocation(),
         * getMapTile(45, 8).getLocation(), Direction.LEFT);
         * >>>>>>> 20a7c260ab60776591d9ed0c36f161641715e41d
         * enemies.add(cat);
         */
        MadScientist2 sci2 = new MadScientist2(getMapTile(65, 5).getLocation(),
                getMapTile(70, 5).getLocation(), Direction.LEFT);
        enemies.add(sci2);

        BugEnemy bug1 = new BugEnemy(getMapTile(86, 7).getLocation(),
                getMapTile(89, 7).getLocation(), Direction.LEFT);
        enemies.add(bug1);
        return enemies;
    }

}
