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
import Powerups.Coin;
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

        Coin coin1 = new Coin(getMapTile(3, 4).getLocation());
        coins.add(coin1);

        Coin coin2 = new Coin(getMapTile(16, 4).getLocation());
        coins.add(coin2);

        Coin coin3 = new Coin(getMapTile(21, 2).getLocation());
        coins.add(coin3);

        Coin coin4 = new Coin(getMapTile(42, 8).getLocation());
        coins.add(coin4);

        Coin coin5 = new Coin(getMapTile(32, 2).getLocation());
        coins.add(coin5);

        Coin coin6 = new Coin(getMapTile(57, 6).getLocation());
        coins.add(coin6);

        return coins;
    }
   

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        EndLevelBox endLevelBox = new EndLevelBox(getMapTile(147, 5).getLocation());
        enhancedMapTiles.add(endLevelBox);

        Key key = new Key(getMapTile(13, 4).getLocation());
        enhancedMapTiles.add(key);

        return enhancedMapTiles;
    }

    
}