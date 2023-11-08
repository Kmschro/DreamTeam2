package MapEditor;

import Level.Map;
import Maps.*;


import java.util.ArrayList;

public class EditorMaps {
    public static ArrayList<String> getMapNames() {
        return new ArrayList<String>() {{
            add("TestMap");
            add("TitleScreen");
            add("LabMap");
            add("LevelTwo");
            add("LevelThree");
        }};
    }

    public static Map getMapByName(String mapName) {
        switch(mapName) {
            case "TestMap":
                return new TestMap();
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
            default:
                throw new RuntimeException("Unrecognized map name");
        }
    }
}
