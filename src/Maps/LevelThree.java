package Maps;

import Enemies.BugEnemy;
import Enemies.DinosaurEnemy;
import Engine.ImageLoader;
import EnhancedMapTiles.Door;
import EnhancedMapTiles.EndLevelBox;
import EnhancedMapTiles.HorizontalMovingPlatform;
import EnhancedMapTiles.Key;
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
public class LevelThree extends Map {
    public LevelThree() {
        super("LevelThree.txt", new LabTileset());
        this.playerStartPosition = getMapTile(0, 0).getLocation();
    }

    @Override
    public ArrayList<Powerups> loadPowerups() {
        ArrayList<Powerups> powers = new ArrayList<>();


        return powers;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();
        
        EndLevelBox endLevelBox = new EndLevelBox(getMapTile(146, 4).getLocation());
        enhancedMapTiles.add(endLevelBox);

        Key key = new Key(getMapTile(81, 4).getLocation());
        enhancedMapTiles.add(key);


        

        return enhancedMapTiles;
    }
    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();

        DinosaurEnemy dinosaurEnemy1 = new DinosaurEnemy(getMapTile(81, 9).getLocation(), getMapTile(87, 9).getLocation(), Direction.LEFT);
        enemies.add(dinosaurEnemy1);

        DinosaurEnemy dinosaurEnemy2 = new DinosaurEnemy(getMapTile(13, 6).getLocation(), getMapTile(20, 6).getLocation(), Direction.LEFT);
        enemies.add(dinosaurEnemy2);

        return enemies;
    }
}
    
