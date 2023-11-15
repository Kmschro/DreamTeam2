package MapEditor;

import Level.Map;
import Maps.*;


import java.util.ArrayList;

public class EditorMaps {
    public static ArrayList<String> getMapNames() {
        return new ArrayList<String>() {{
            add("FinalLevel");
            add("TitleScreen");
            add("LabMap");
            add("LevelTwo");
            add("LevelThree");
            add("LevelFour");
            add("LevelFive");
        }};
    }

    public static Map getMapByName(String mapName) {
        switch(mapName) {
            case "FinalLevel":
                return new FinalLevel();
            case "TitleScreen":
                return new TitleScreenMap();
            case "TestMap2":
                return new LabMap();
            case "LabMap":
                return new LabMap();
            case "LevelTwo":
                return new LevelTwo();
            case "LevelThree":
                return new LevelThree();
            case "LevelFour":
                return new LevelFour();
            case "LevelFive":
                return new LevelFive();
            default:
                throw new RuntimeException("Unrecognized map name");
        }
    }
}
