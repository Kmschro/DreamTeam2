package Maps;

import Enemies.BugEnemy;
import Enemies.DinosaurEnemy;
import Engine.ImageLoader;
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
import Screens.PlayLevelScreen;
import Powerups.Checkpoint;
import Utils.Direction;

import java.util.ArrayList;

// Represents a test map to be used in a level
public class LevelTwo extends Map {
    public LevelTwo() {
        super("LevelTwo.txt", new LabTileset());
        this.playerStartPosition = getMapTile(4, 4).getLocation();
    }

    @Override
    public ArrayList<Powerups> loadPowerups() {
        ArrayList<Powerups> powers = new ArrayList<>();
        if(PlayLevelScreen.counter == 2) {
        Checkpoint checkpoint = new Checkpoint(getMapTile(7, 3).getLocation());
        powers.add(checkpoint);
        }
        return powers;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();
        

        EndLevelBox endLevelBox = new EndLevelBox(getMapTile(145, 6).getLocation());
        enhancedMapTiles.add(endLevelBox);

        Key key = new Key(getMapTile(86, 6).getLocation());
        enhancedMapTiles.add(key);




        return enhancedMapTiles;
    }
    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();


        DinosaurEnemy dinosaurEnemy1 = new DinosaurEnemy(getMapTile(35, 5).getLocation(), getMapTile(40, 5).getLocation(), Direction.LEFT);
        enemies.add(dinosaurEnemy1);

        DinosaurEnemy dinosaurEnemy2 = new DinosaurEnemy(getMapTile(119, 7).getLocation(), getMapTile(124, 7).getLocation(), Direction.LEFT);
        enemies.add(dinosaurEnemy2);

        DinosaurEnemy dinosaurEnemy3 = new DinosaurEnemy(getMapTile(125, 7).getLocation(), getMapTile(130, 7).getLocation(), Direction.LEFT);
        enemies.add(dinosaurEnemy3);

        return enemies;
    }
    
    
}

