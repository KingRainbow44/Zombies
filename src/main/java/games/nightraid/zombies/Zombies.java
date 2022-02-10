package games.nightraid.zombies;

import com.kingrainbow44.crafttools.player.CraftPlayerManager;
import com.kingrainbow44.crafttools.plugin.ExtendedPlugin;
import games.nightraid.zombies.player.ZombiesPlayer;
import games.nightraid.zombies.player.ZombiesPlayerData;

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
    }

    @Override
    protected void enable() {
        this.getLogger().info("Enabled Zombies by Nightraid.");
    }

    @Override
    protected String getCommandPath() {
        return "games.nightraid.zombies.commands";
    }

    @Override
    protected String getListenerPath() {
        return "games.nightraid.zombies.listeners";
    }
}
