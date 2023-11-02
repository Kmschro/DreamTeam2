package Tilesets;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import Level.TileType;
import Level.Tileset;
import Utils.SlopeTileLayoutUtils;
import java.util.ArrayList;


// This class represents The Lab Tileset found in LabTileset.png
public class LabTileset extends Tileset {

    public LabTileset() {
        super(ImageLoader.load("LabTileset.png"), 16, 16, 3);
    }

    @Override
    public ArrayList<MapTileBuilder> defineTiles() {
        ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();

        // floor
        Frame floorFrame = new FrameBuilder(getSubImage(0, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder floorTile = new MapTileBuilder(floorFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(floorTile);

        // backWall (formerly Sky)
        Frame backWallFrame = new FrameBuilder(getSubImage(0, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder backWallTile = new MapTileBuilder(backWallFrame);

        mapTiles.add(backWallTile);

        // dirt (havent replaced it yet)
        Frame dirtFrame = new FrameBuilder(getSubImage(0, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder dirtTile = new MapTileBuilder(dirtFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(dirtTile);

        // Alarm
        Frame[] AlarmFrames = new Frame[]{
                new FrameBuilder(getSubImage(2, 0), 50)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(2, 1), 50)
                        .withScale(tileScale)
                        .build()
        };

        MapTileBuilder AlarmTile = new MapTileBuilder(AlarmFrames);

        mapTiles.add(AlarmTile);

        // tree trunk with full hole
        Frame treeTrunkWithFullHoleFrame = new FrameBuilder(getSubImage(2, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder treeTrunkWithFullHoleTile = new MapTileBuilder(treeTrunkWithFullHoleFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(treeTrunkWithFullHoleTile);

        // left end branch
        Frame leftEndBranchFrame = new FrameBuilder(getSubImage(1, 5))
                .withScale(tileScale)
                .withBounds(0, 6, 16, 4)
                .build();

        MapTileBuilder leftEndBranchTile = new MapTileBuilder(leftEndBranchFrame)
                .withTileType(TileType.JUMP_THROUGH_PLATFORM);

        mapTiles.add(leftEndBranchTile);

        // right end branch
        Frame rightEndBranchFrame = new FrameBuilder(getSubImage(1, 5))
                .withScale(tileScale)
                .withBounds(0, 6, 16, 4)
                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                .build();

        MapTileBuilder rightEndBranchTile = new MapTileBuilder(rightEndBranchFrame)
                .withTileType(TileType.JUMP_THROUGH_PLATFORM);

        mapTiles.add(rightEndBranchTile);

        // tree trunk
        Frame treeTrunkFrame = new FrameBuilder(getSubImage(1, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder treeTrunkTile = new MapTileBuilder(treeTrunkFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(treeTrunkTile);

        // tree top leaves
        Frame treeTopLeavesFrame = new FrameBuilder(getSubImage(1, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder treeTopLeavesTile = new MapTileBuilder(treeTopLeavesFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(treeTopLeavesTile);

        // yellow flower
        Frame[] yellowFlowerFrames = new Frame[] {
                new FrameBuilder(getSubImage(1, 2), 65)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(1, 3), 65)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(1, 2), 65)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(1, 4), 65)
                        .withScale(tileScale)
                        .build()
        };

        MapTileBuilder yellowFlowerTile = new MapTileBuilder(yellowFlowerFrames);

        mapTiles.add(yellowFlowerTile);

        // purple flower
        Frame[] purpleFlowerFrames = new Frame[] {
                new FrameBuilder(getSubImage(0, 3), 65)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(0, 4), 65)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(0, 3), 65)
                        .withScale(tileScale)
                        .build(),
                new FrameBuilder(getSubImage(0, 5), 65)
                        .withScale(tileScale)
                        .build()
        };

        MapTileBuilder purpleFlowerTile = new MapTileBuilder(purpleFlowerFrames);

        mapTiles.add(purpleFlowerTile);

        // middle branch
        Frame middleBranchFrame = new FrameBuilder(getSubImage(2, 3))
                .withScale(tileScale)
                .withBounds(0, 6, 16, 4)
                .build();

        MapTileBuilder middleBranchTile = new MapTileBuilder(middleBranchFrame)
                .withTileType(TileType.JUMP_THROUGH_PLATFORM);

        mapTiles.add(middleBranchTile);

        // tree trunk hole top
        Frame treeTrunkHoleTopFrame = new FrameBuilder(getSubImage(2, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder treeTrunkHoleTopTile = new MapTileBuilder(treeTrunkHoleTopFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(treeTrunkHoleTopTile);

        // tree trunk hole bottom
        Frame treeTrunkHoleBottomFrame = new FrameBuilder(getSubImage(2, 5))
                .withScale(tileScale)
                .build();

        MapTileBuilder treeTrunkHoleBottomTile = new MapTileBuilder(treeTrunkHoleBottomFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(treeTrunkHoleBottomTile);

        // top water
        Frame topWaterFrame = new FrameBuilder(getSubImage(3, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder topWaterTile = new MapTileBuilder(topWaterFrame);

        mapTiles.add(topWaterTile);

        // water
        Frame waterFrame = new FrameBuilder(getSubImage(3, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder waterTile = new MapTileBuilder(waterFrame)
                .withTileType(TileType.WATER);

        mapTiles.add(waterTile);
        // crate
        Frame crateFrame = new FrameBuilder(getSubImage(3, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder crateTile = new MapTileBuilder(crateFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(crateTile);
       
        //hoverPlatformLeft
        Frame hoverPlatformLeftFrame = new FrameBuilder(getSubImage(4, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder hoverPlatformLeft = new MapTileBuilder(hoverPlatformLeftFrame)
                .withTileType(TileType.JUMP_THROUGH_PLATFORM);

        mapTiles.add(hoverPlatformLeft);

        //hoverPlatformMiddle
        Frame hoverPlatformMiddleFrame = new FrameBuilder(getSubImage(4, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder hoverPlatformMiddle = new MapTileBuilder(hoverPlatformMiddleFrame)
                .withTileType(TileType.JUMP_THROUGH_PLATFORM);

        mapTiles.add(hoverPlatformMiddle);

        //hoverPlatformRight
        Frame hoverPlatformRightFrame = new FrameBuilder(getSubImage(4, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder hoverPlatformRight = new MapTileBuilder(hoverPlatformRightFrame)
                .withTileType(TileType.JUMP_THROUGH_PLATFORM);

        mapTiles.add(hoverPlatformRight);



        // left 45 degree slope
        Frame leftSlopeFrame = new FrameBuilder(getSubImage(3, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder leftSlopeTile = new MapTileBuilder(leftSlopeFrame)
                .withTileType(TileType.SLOPE)
                .withTileLayout(SlopeTileLayoutUtils.createLeft45SlopeLayout(spriteWidth, (int) tileScale));

        mapTiles.add(leftSlopeTile);

        // right 45 degree slope
        Frame rightSlopeFrame = new FrameBuilder(getSubImage(3, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder rightSlopeTile = new MapTileBuilder(rightSlopeFrame)
                .withTileType(TileType.SLOPE)
                .withTileLayout(SlopeTileLayoutUtils.createRight45SlopeLayout(spriteWidth, (int) tileScale));

        mapTiles.add(rightSlopeTile);

        // left 30 degree slope bottom
        Frame leftStairsBottomFrame = new FrameBuilder(getSubImage(4, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder leftStairsBottomTile = new MapTileBuilder(leftStairsBottomFrame)
                .withTileType(TileType.SLOPE)
                .withTileLayout(SlopeTileLayoutUtils.createBottomLeft30SlopeLayout(spriteWidth, (int) tileScale));

        mapTiles.add(leftStairsBottomTile);

        // left 30 degree slope top
        Frame leftStairsTopFrame = new FrameBuilder(getSubImage(4, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder leftStairsTopTile = new MapTileBuilder(leftStairsTopFrame)
                .withTileType(TileType.SLOPE)
                .withTileLayout(SlopeTileLayoutUtils.createTopLeft30SlopeLayout(spriteWidth, (int) tileScale));

        mapTiles.add(leftStairsTopTile);

        // LethalTile
        Frame deathBeamStartFrame = new FrameBuilder(getSubImage(4, 5))
                .withScale(tileScale)
                .build();

        MapTileBuilder deathBeamStart = new MapTileBuilder(deathBeamStartFrame)
                .withTileType(TileType.LETHAL);

        mapTiles.add(deathBeamStart);
        

        Frame deathBeamMidFrame = new FrameBuilder(getSubImage(4,6))
        .withScale(tileScale)
        .build();

        MapTileBuilder deathBeamMid = new MapTileBuilder(deathBeamMidFrame)
                .withTileType(TileType.LETHAL);

        mapTiles.add(deathBeamMid);

        Frame deathBeamEndFrame = new FrameBuilder(getSubImage(4,7))
        .withScale(tileScale)
        .build();

        MapTileBuilder deathBeamEnd = new MapTileBuilder(deathBeamEndFrame)
                .withTileType(TileType.LETHAL);
        mapTiles.add(deathBeamEnd);



        //Lethal Ceiling Tiles
        Frame deathBeamStartFrameUpper = new FrameBuilder(getSubImage(3, 5))
                .withScale(tileScale)
                .build();

        MapTileBuilder deathBeamStartUpper = new MapTileBuilder(deathBeamStartFrameUpper)
                .withTileType(TileType.LETHAL);

        mapTiles.add(deathBeamStartUpper);
        

        Frame deathBeamMidFrameUpper = new FrameBuilder(getSubImage(3,6))
        .withScale(tileScale)
        .build();

        MapTileBuilder deathBeamMidUpper = new MapTileBuilder(deathBeamMidFrameUpper)
                .withTileType(TileType.LETHAL);

        mapTiles.add(deathBeamMidUpper);

        Frame deathBeamEndFrameUpper = new FrameBuilder(getSubImage(3,7))
        .withScale(tileScale)
        .build();

        MapTileBuilder deathBeamEndUpper = new MapTileBuilder(deathBeamEndFrameUpper)
                .withTileType(TileType.LETHAL);
        mapTiles.add(deathBeamEndUpper);

        //Lethal Tile Other Joins
        Frame deathBeamJointTurnRightFrame = new FrameBuilder(getSubImage(2, 6))
                .withScale(tileScale)
                .build();

        MapTileBuilder deathBeamJointTurnRight = new MapTileBuilder(deathBeamJointTurnRightFrame)
                .withTileType(TileType.LETHAL);
        mapTiles.add(deathBeamJointTurnRight);

        Frame deathBeamJointTurnLeftFrame = new FrameBuilder(getSubImage(2, 7))
                .withScale(tileScale)
                .build();

        MapTileBuilder deathBeamJointTurnLeft = new MapTileBuilder(deathBeamJointTurnLeftFrame)
                .withTileType(TileType.LETHAL);
        mapTiles.add(deathBeamJointTurnLeft);



        //Lethal Vertical Tiles
                //TypeA
        Frame deathBeamFrameTypeA = new FrameBuilder(getSubImage(0, 6))
                .withScale(tileScale)
                .build();

        MapTileBuilder deathBeamTypeA = new MapTileBuilder(deathBeamFrameTypeA)
                .withTileType(TileType.LETHAL);
        mapTiles.add(deathBeamTypeA);

        Frame deathBeamFrameTypeAConnectorTop = new FrameBuilder(getSubImage(1, 8))
                .withScale(tileScale)
                .build();

        MapTileBuilder deathBeamTypeAConnectorTop = new MapTileBuilder(deathBeamFrameTypeAConnectorTop)
                .withTileType(TileType.LETHAL);
        mapTiles.add(deathBeamTypeAConnectorTop);

        Frame deathBeamFrameTypeAConnectorBottom = new FrameBuilder(getSubImage(1, 6))
                .withScale(tileScale)
                .build();

        MapTileBuilder deathBeamTypeAConnectorBottom = new MapTileBuilder(deathBeamFrameTypeAConnectorBottom)
                .withTileType(TileType.LETHAL);
        mapTiles.add(deathBeamTypeAConnectorBottom);

                //TypeB
        Frame deathBeamFrameTypeB = new FrameBuilder(getSubImage(0, 7))
                .withScale(tileScale)
                .build();

        MapTileBuilder deathBeamTypeB = new MapTileBuilder(deathBeamFrameTypeB)
                .withTileType(TileType.LETHAL);
        mapTiles.add(deathBeamTypeB);

        Frame deathBeamFrameTypeBConnectorTop = new FrameBuilder(getSubImage(0, 8))
                .withScale(tileScale)
                .build();

        MapTileBuilder deathBeamTypeBConnectorTop = new MapTileBuilder(deathBeamFrameTypeBConnectorTop)
                .withTileType(TileType.LETHAL);
        mapTiles.add(deathBeamTypeBConnectorTop);

        Frame deathBeamFrameTypeBConnectorBottom = new FrameBuilder(getSubImage(1, 7))
                .withScale(tileScale)
                .build();

        MapTileBuilder deathBeamTypeBConnector = new MapTileBuilder(deathBeamFrameTypeBConnectorBottom)
                .withTileType(TileType.LETHAL);
        mapTiles.add(deathBeamTypeBConnector);



        //Hover Crate
        //Left Top
        Frame hoverCrateLeftTopFrame = new FrameBuilder(getSubImage(3, 8))
        .withScale(tileScale)
        .build();

        MapTileBuilder hoverCrateLeftTop = new MapTileBuilder(hoverCrateLeftTopFrame)
                        .withTileType(TileType.NOT_PASSABLE);
        

        mapTiles.add(hoverCrateLeftTop);
        //LeftBottom
        Frame hoverCrateLeftBottomFrame = new FrameBuilder(getSubImage(4, 8))
        .withScale(tileScale)
        .build();

        MapTileBuilder hoverCrateLeftBottom = new MapTileBuilder(hoverCrateLeftBottomFrame)
                        .withTileType(TileType.NOT_PASSABLE);
        
        mapTiles.add(hoverCrateLeftBottom);
        //Middle Top
        Frame hoverCrateMiddleTopFrame = new FrameBuilder(getSubImage(3, 9))
        .withScale(tileScale)
        .build();
        

     
        MapTileBuilder hoverCrateMiddleTop = new MapTileBuilder(hoverCrateMiddleTopFrame)
                        .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(hoverCrateMiddleTop);
        
        //Middle Bottom
        Frame hoverCrateMiddleBottomFrame = new FrameBuilder(getSubImage(4, 9))
        .withScale(tileScale)
        .build();
        

        MapTileBuilder hoverCrateMiddleBottom = new MapTileBuilder(hoverCrateMiddleBottomFrame)
                        .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(hoverCrateMiddleBottom);
        
        //Right Top
        Frame hoverCrateRightTopFrame = new FrameBuilder(getSubImage(3, 10))
        .withScale(tileScale)
        .build();
        
        
        MapTileBuilder hoverCrateRightTop = new MapTileBuilder(hoverCrateRightTopFrame)
                        .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(hoverCrateRightTop);
        //Right Bottom
        Frame hoverCrateRightBottomFrame = new FrameBuilder(getSubImage(4, 10))
        .withScale(tileScale)
        .build();
        
        MapTileBuilder hoverCrateRightBottom = new MapTileBuilder(hoverCrateRightBottomFrame)
                        .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(hoverCrateRightBottom);

        
        return mapTiles;
    }
}
