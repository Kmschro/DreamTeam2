package Maps;

import Enemies.BugEnemy;
import Enemies.DinosaurEnemy;
import Engine.ImageLoader;
import EnhancedMapTiles.EndLevelBox;
import EnhancedMapTiles.HorizontalMovingPlatform;
import GameObject.Rectangle;
import Level.*;
import Tilesets.CommonTileset;
import Tilesets.LabTileset;
import Utils.Direction;
import Powerups.Coin;

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

        Coin coin = new Coin(getMapTile(2, 8).getLocation());
        coins.add(coin);

        return coins;
    }
   

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        EndLevelBox endLevelBox = new EndLevelBox(getMapTile(147, 5).getLocation());
        enhancedMapTiles.add(endLevelBox);

        return enhancedMapTiles;
    }

    
}