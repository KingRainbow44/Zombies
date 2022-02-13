package games.nightraid.zombies.game;

import com.google.gson.Gson;
import games.nightraid.zombies.Zombies;
import games.nightraid.zombies.utils.config.MapConfiguration;
import lombok.AllArgsConstructor;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@AllArgsConstructor
public final class ZombiesGameFlags {
    public final MapConfiguration mapConfig;
    public final Difficulty difficulty;
    
    public static ZombiesGameFlags defaultFlags(String map) throws IOException {
        File configFile = Zombies.getInstance().getPathTo("maps/" + map + ".json");
        MapConfiguration parsedConfig = new Gson().fromJson(new FileReader(configFile), MapConfiguration.class);
        
        return new ZombiesGameFlags(parsedConfig,
                Difficulty.NORMAL
        );
    }
    
    public enum Difficulty {
        NORMAL, HARD, RIP
    }
}