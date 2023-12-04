package Maps;

import Enemies.BugEnemy;
import Enemies.DinosaurEnemy;
import Enemies.FlyingBug;
import Enemies.InvisibleSlime;
import Enemies.Spider;
import Engine.ImageLoader;
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
import Screens.PlayLevelScreen;
import Powerups.Checkpoint;
import Utils.Direction;
import Players.*;



import java.util.ArrayList;

// Represents a test map to be used in a level
public class LevelTwo extends Map {
    public LevelTwo() {
        super("LevelTwo.txt", new LabTileset());
        this.playerStartPosition = getMapTile(4, 4).getLocation();
    }

    @Override
    public ArrayList<Powerups> loadPowerups() {

        ArrayList<Powerups> powerups = new ArrayList<>();
    
        FireballPU fbPU = new FireballPU(getMapTile(31, 5).getLocation());
        powerups.add(fbPU);

        //Checkpoint checkpoint2 = new Checkpoint(getMapTile(7, 3).getLocation());
        //powerups.add(checkpoint2);

        Boost boost = new Boost((getMapTile(7, 4).getLocation()));
        powerups.add(boost);
        return powerups;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();
        

        EndLevelBox endLevelBox = new EndLevelBox(getMapTile(145, 6).getLocation());
        enhancedMapTiles.add(endLevelBox);

        Portal portal = new Portal(getMapTile(149, 7).getLocation());
        enhancedMapTiles.add(portal);

        Key key = new Key(getMapTile(86, 6).getLocation());
        enhancedMapTiles.add(key);



        return enhancedMapTiles;
    }
    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();


        DinosaurEnemy dinosaurEnemy1 = new DinosaurEnemy(getMapTile(35, 6).getLocation(), getMapTile(40, 5).getLocation(), Direction.LEFT);
        enemies.add(dinosaurEnemy1);
        
        InvisibleSlime slime1 = new InvisibleSlime(getMapTile(52, 9).getLocation(), getMapTile(59, 9).getLocation(), Direction.LEFT);
        enemies.add(slime1);

        Spider spider1 = new Spider(getMapTile(66, 9).getLocation(), getMapTile(74, 9).getLocation(), Direction.LEFT);
        enemies.add(spider1);

        DinosaurEnemy dinosaurEnemy2 = new DinosaurEnemy(getMapTile(119, 7).getLocation(), getMapTile(124, 7).getLocation(), Direction.LEFT);
        enemies.add(dinosaurEnemy2);

        FlyingBug fly1 = new FlyingBug(getMapTile(117, 4).getLocation(), getMapTile(125, 4).getLocation(), Direction.LEFT);
        enemies.add(fly1);

        DinosaurEnemy dinosaurEnemy3 = new DinosaurEnemy(getMapTile(125, 7).getLocation(), getMapTile(130, 7).getLocation(), Direction.LEFT);
        enemies.add(dinosaurEnemy3);

        return enemies;
    }
    
    
}

