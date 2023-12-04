package Maps;

import Enemies.BugEnemy;
import Enemies.DinosaurEnemy;
import Enemies.FlyingBug;
import Enemies.MadScientist2;
import Enemies.RadioactiveCat;
import Engine.ImageLoader;
import EnhancedMapTiles.Door;
import EnhancedMapTiles.EndLevelBox;
import EnhancedMapTiles.HorizontalMovingPlatform;
import EnhancedMapTiles.Key;
import EnhancedMapTiles.Portal;
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
import Utils.Direction;

import java.util.ArrayList;

// Represents a test map to be used in a level
public class LevelFive extends Map {
    public LevelFive() {
        super("LevelFive.txt", new LabTileset());
        this.playerStartPosition = getMapTile(2, 4).getLocation();
    }

    @Override
    public ArrayList<Powerups> loadPowerups() {
        ArrayList<Powerups> powers = new ArrayList<>();

        //Boost boost = new Boost((getMapTile(7, 4).getLocation()));
        //powerups.add(boost);

        return powers;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        EndLevelBox endLevelBox = new EndLevelBox(getMapTile(145, 7).getLocation());
        enhancedMapTiles.add(endLevelBox);

        Portal portal = new Portal(getMapTile(149, 5).getLocation());
        enhancedMapTiles.add(portal);

        Key key = new Key(getMapTile(73, 8).getLocation());
        enhancedMapTiles.add(key);

        return enhancedMapTiles;
    }

    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();
        
        FlyingBug fly1 = new FlyingBug(getMapTile(63, 4).getLocation(), getMapTile(71, 4).getLocation(), Direction.LEFT);
        enemies.add(fly1);

        RadioactiveCat cat1 = new RadioactiveCat(getMapTile(77, 5).getLocation(), getMapTile(89, 5).getLocation(), Direction.LEFT);
        enemies.add(cat1);

        MadScientist2 scientist1 = new MadScientist2(getMapTile(77, 5).getLocation(), getMapTile(89, 5).getLocation(), Direction.LEFT);
        enemies.add(scientist1);
        return enemies;
    }
}
    
