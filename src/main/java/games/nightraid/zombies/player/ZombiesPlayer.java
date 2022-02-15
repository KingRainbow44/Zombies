package games.nightraid.zombies.player;

import com.kingrainbow44.crafttools.CraftPlayer;
import com.kingrainbow44.crafttools.player.CraftPlayerManager;
import games.nightraid.zombies.game.ZombiesGameInstance;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public final class ZombiesPlayer extends CraftPlayer {
    public static ZombiesPlayer get(Player player) {
        return CraftPlayerManager.self()
                .getCraftPlayer(player, ZombiesPlayer.class);
    }
    
    private final Map<ZombiesPlayerFlags, Boolean> flags = new HashMap<>();
    private final Map<String, Integer> gunCooldown = new HashMap<>();
    @Nullable private ZombiesGameInstance inGame;
    
    /*
     * Overridden Methods
     */

    @Override
    public void tick() {
        gunCooldown.forEach((gunId, ticksRemaining) -> {
            if(--ticksRemaining <= 0) {
                gunCooldown.remove(gunId);
            } else gunCooldown.put(gunId, ticksRemaining);
        });
    }

    /*
     * Flag Methods
     */
    
    public boolean getFlag(ZombiesPlayerFlags flag) {
        return getFlag(flag, false);
    }
    
    public boolean getFlag(ZombiesPlayerFlags flag, boolean fallback) {
        return this.flags.getOrDefault(flag, fallback);
    }
    
    public void setFlag(ZombiesPlayerFlags flag, boolean value) {
        this.flags.put(flag, value);
    }
    
    /*
     * Gun Methods
     */
    
    public void startGunCooldown(String gunId, int ticks) {
        this.gunCooldown.put(gunId, ticks);
    }
    
    public boolean canUseGun(String gunId) {
        return !this.gunCooldown.containsKey(gunId);
    }
    
    /*
     * Data Methods
     */
    
    @Nullable
    public ZombiesGameInstance getGame() {
        return this.inGame;
    }
    
    public void setGame(@Nullable ZombiesGameInstance game) {
        this.inGame = game;
    }
    
    /*
     * Utility methods.
     */
    
    public void reset() {
        
    }
}
