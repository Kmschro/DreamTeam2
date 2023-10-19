package Maps;

import Enemies.BugEnemy;
import Enemies.DinosaurEnemy;
import Engine.ImageLoader;
import EnhancedMapTiles.EndLevelBox;
import EnhancedMapTiles.HorizontalMovingPlatform;
import EnhancedMapTiles.Key;
import GameObject.Rectangle;
import Level.*;
import Tilesets.CommonTileset;
import Tilesets.LabTileset;
import Utils.Direction;

import java.util.ArrayList;

// Represents a test map to be used in a level
public class LabMap extends Map {
    public LabMap() {
        super("TestMap2.txt", new LabTileset());
        this.playerStartPosition = getMapTile(1, 8).getLocation();
    }

    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();

        return enemies;
    }
   

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        EndLevelBox endLevelBox = new EndLevelBox(getMapTile(147, 4).getLocation());
        enhancedMapTiles.add(endLevelBox);

        Key key = new Key(getMapTile(13, 4).getLocation());
        enhancedMapTiles.add(key);

        return enhancedMapTiles;
    }

    
}