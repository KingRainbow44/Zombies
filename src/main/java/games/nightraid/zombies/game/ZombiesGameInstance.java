package games.nightraid.zombies.game;

import games.nightraid.zombies.player.ZombiesPlayer;
import games.nightraid.zombies.player.ZombiesPlayerFlags;
import org.bukkit.entity.Player;

import java.util.*;

public final class ZombiesGameInstance {
    private final ZombiesGameFlags flags;
    private final Map<UUID, Player> players = new HashMap<>();
    
    private UUID gameId;
    private boolean hasStarted = false;
    
    public ZombiesGameInstance(ZombiesGameFlags flags, List<Player> players) {
        this.flags = flags; players.forEach(this::addPlayer);
    }
    
    /*
     * Data Methods.
     */
    
    public ZombiesGameFlags getFlags() {
        return this.flags;
    }
    
    public Collection<Player> getPlayers() {
        return this.players.values();
    }
    
    /*
     * Start & Stop Methods.
     */
    
    public void start() {
        if(this.hasStarted)
            return;
        this.hasStarted = true;
        
        // Set players to playing.
        this.getPlayers().forEach(player -> {
            ZombiesPlayer zombiesPlayer = ZombiesPlayer.get(player);
            zombiesPlayer.setFlag(ZombiesPlayerFlags.IN_GAME, true);
            zombiesPlayer.setGame(this);
        });
    }
    
    /*
     * Utility Methods.
     */
    
    public void assignId(UUID uuid) {
        if(this.gameId != null) return;
        this.gameId = uuid;
    }
    
    private void addPlayer(Player player) {
        if(this.hasStarted) return;
        this.players.put(player.getUniqueId(), player);
    }
}
