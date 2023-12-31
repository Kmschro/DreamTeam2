package Maps;

import Enemies.BugEnemy;
import Enemies.DinosaurEnemy;
import Engine.ImageLoader;
import EnhancedMapTiles.EndLevelBox;
import EnhancedMapTiles.HorizontalMovingPlatform;
import EnhancedMapTiles.Key;
import GameObject.Rectangle;
import Level.*;
import Powerups.Boost;
import Powerups.FireballPU;
import Tilesets.CommonTileset;
import Utils.Direction;

import java.util.ArrayList;

// Represents a test map to be used in a level
public class FinalLevel extends Map {

    public FinalLevel() {
        super("FinalLevel.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(2, 11).getLocation();
    }

    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();

        BugEnemy bugEnemy = new BugEnemy(getMapTile(13, 10).getLocation().subtractY(25),
                getMapTile(18, 10).getLocation(), Direction.RIGHT);
        enemies.add(bugEnemy);

        DinosaurEnemy dinosaurEnemy = new DinosaurEnemy(
                getMapTile(18, 9).getLocation(), // Adjusted Y-coordinate here
                getMapTile(22, 9).getLocation(), // Adjusted Y-coordinate here
                Direction.RIGHT);
        enemies.add(dinosaurEnemy); 

        return enemies;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        HorizontalMovingPlatform hmp = new HorizontalMovingPlatform(
                ImageLoader.load("GreenPlatform.png"),
                getMapTile(24, 6).getLocation(),
                getMapTile(27, 6).getLocation(),
                TileType.JUMP_THROUGH_PLATFORM,
                3,
                new Rectangle(0, 6, 16, 4),
                Direction.RIGHT);
        enhancedMapTiles.add(hmp);

        EndLevelBox endLevelBox = new EndLevelBox(getMapTile(149, 1).getLocation());
        enhancedMapTiles.add(endLevelBox);

        Key key = new Key(getMapTile(21, 2).getLocation());
        enhancedMapTiles.add(key);

        return enhancedMapTiles;
    }

    @Override
    public ArrayList<Powerups> loadPowerups() {
        ArrayList<Powerups> npcs = new ArrayList<>();

        FireballPU fireballPU = new FireballPU(getMapTile(30, 10).getLocation().subtractY(13));
        npcs.add(fireballPU);

        //Boost boost = new Boost((getMapTile(7, 4).getLocation()));
        //powerups.add(boost);

        return npcs;
    }

}
