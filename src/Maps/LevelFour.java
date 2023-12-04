package Maps;

import Enemies.BugEnemy;
import Enemies.DinosaurEnemy;
import Enemies.InvisibleSlime;
import Enemies.MadScientist2;
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
public class LevelFour extends Map {
    public LevelFour() {
        super("LevelFour.txt", new LabTileset());
        this.playerStartPosition = getMapTile(3, 3).getLocation();
    }

    @Override
    public ArrayList<Powerups> loadPowerups() {
        ArrayList<Powerups> powers = new ArrayList<>();

        //FireballPU fbPU = new FireballPU(getMapTile(101, 3).getLocation());
        //powers.add(fbPU);

        //Boost boost = new Boost((getMapTile(7, 3).getLocation()));
        //powerups.add(boost);

        return powers;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();
        
        EndLevelBox endLevelBox = new EndLevelBox(getMapTile(145, 4).getLocation());
        enhancedMapTiles.add(endLevelBox);

        Portal portal = new Portal(getMapTile(149, 5).getLocation());
        enhancedMapTiles.add(portal);

        Key key = new Key(getMapTile(55, 8).getLocation());
        enhancedMapTiles.add(key);

        return enhancedMapTiles;
    }

    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();

        DinosaurEnemy dinosaurEnemy1 = new DinosaurEnemy(getMapTile(40, 6).getLocation(), getMapTile(45, 6).getLocation(), Direction.LEFT);
        enemies.add(dinosaurEnemy1);

        DinosaurEnemy dinosaurEnemy2 = new DinosaurEnemy(getMapTile(66, 9).getLocation(), getMapTile(70, 9).getLocation(), Direction.LEFT);
        enemies.add(dinosaurEnemy2);

        MadScientist2 dinosaurEnemy3 = new MadScientist2(getMapTile(65, 5).getLocation(), getMapTile(70, 5).getLocation(), Direction.LEFT);
        enemies.add(dinosaurEnemy3);

        InvisibleSlime slime1 = new InvisibleSlime(getMapTile(129, 3).getLocation(), getMapTile(132, 3).getLocation(), Direction.LEFT);
        enemies.add(slime1);
        return enemies;
    }
}
    
