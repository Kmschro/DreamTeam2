package Maps;

import Enemies.BugEnemy;
import Enemies.DinosaurEnemy;
import Engine.ImageLoader;
import EnhancedMapTiles.EndLevelBox;
import EnhancedMapTiles.HorizontalMovingPlatform;
import GameObject.Rectangle;
import Level.*;
import Powerups.Checkpoint;
import Tilesets.CommonTileset;
import Tilesets.LabTileset;
import Utils.Point;

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

        EndLevelBox endLevelBox = new EndLevelBox(getMapTile(147, 5).getLocation());
        enhancedMapTiles.add(endLevelBox);

        

        return enhancedMapTiles;
    }

    
    public ArrayList<Powerups> loadPowerups() {
        ArrayList<Powerups> powerups = new ArrayList<>();


       // Checkpoint checkpoint = this.setCP(new Point(56, 6));
        Checkpoint checkpoint = new Checkpoint(getMapTile(56, 6).getLocation().subtractY(13));
        powerups.add(checkpoint);

        return powerups;

        
    }
}
