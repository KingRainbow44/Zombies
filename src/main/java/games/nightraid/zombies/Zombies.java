package games.nightraid.zombies;

import com.kingrainbow44.crafttools.entity.CraftEntityManager;
import com.kingrainbow44.crafttools.player.CraftPlayerManager;
import com.kingrainbow44.crafttools.plugin.ExtendedPlugin;
import games.nightraid.zombies.entity.ZombieEntityTrait;
import games.nightraid.zombies.player.ZombiesPlayer;
import games.nightraid.zombies.player.ZombiesPlayerData;
import org.ipvp.canvas.MenuFunctionListener;

public final class Zombies extends ExtendedPlugin {
    private static Zombies instance;

    public static Zombies getInstance() {
        return instance;
    }
    
    @Override
    protected void init() {
        instance = this;

        CraftPlayerManager.setPlayerClass(ZombiesPlayer.class);
        CraftPlayerManager.setDataClass(ZombiesPlayerData.class);
        CraftEntityManager.traitClass = ZombieEntityTrait.class;
    }

    @Override
    protected void enable() {
        // External listeners.
        registerListener(new MenuFunctionListener());
        
        this.getLogger().info("Enabled Zombies by Nightraid.");
    }

    @Override
    protected String getCommandPath() {
        return null; /* "games.nightraid.zombies.commands" */
    }

    @Override
    protected String getListenerPath() {
        return "games.nightraid.zombies.listeners";
    }
}
