package games.nightraid.zombies.player;

import com.kingrainbow44.crafttools.CraftPlayer;
import com.kingrainbow44.crafttools.player.CraftPlayerManager;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public final class ZombiesPlayer extends CraftPlayer {
    private final Map<ZombiesPlayerFlags, Boolean> flags = new HashMap<>();
    
    public boolean getFlag(ZombiesPlayerFlags flag) {
        return getFlag(flag, false);
    }
    
    public boolean getFlag(ZombiesPlayerFlags flag, boolean fallback) {
        return this.flags.getOrDefault(flag, fallback);
    }
    
    public void setFlag(ZombiesPlayerFlags flag, boolean value) {
        this.flags.put(flag, value);
    }
    
    public static ZombiesPlayer get(Player player) {
        return CraftPlayerManager.self()
                .getCraftPlayer(player, ZombiesPlayer.class);
    }
}
