package games.nightraid.zombies.game;

import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.*;

public final class ZombiesGameManager {
    private static Map<UUID, ZombiesGameInstance> games = new HashMap<>();
    
    public static ZombiesGameInstance newGame(String map, Player... players) throws IOException {
        ZombiesGameFlags flags = ZombiesGameFlags.defaultFlags(map);
        ZombiesGameInstance game = new ZombiesGameInstance(flags, List.of(players));
        UUID gameId = UUID.randomUUID(); game.assignId(gameId);
        games.put(gameId, game); return game;
    }
    
    public static ZombiesGameInstance getGame(UUID gameId) {
        return games.get(gameId);
    }
    
    public static void removeGame(UUID gameId) {
        games.remove(gameId);
    }
}
