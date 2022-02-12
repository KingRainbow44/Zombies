package games.nightraid.zombies;

import com.kingrainbow44.crafttools.entity.CraftEntityManager;
import com.kingrainbow44.crafttools.player.CraftPlayerManager;
import com.kingrainbow44.crafttools.plugin.ExtendedPlugin;
import games.nightraid.zombies.commands.WindowCommand;
import games.nightraid.zombies.entity.ZombieEntityTrait;
import games.nightraid.zombies.item.ZombiesItemManager;
import games.nightraid.zombies.player.ZombiesPlayer;
import games.nightraid.zombies.player.ZombiesPlayerData;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.trait.TraitInfo;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.ipvp.canvas.MenuFunctionListener;

public final class Zombies extends ExtendedPlugin {
    private static Zombies instance;

    public static Zombies getInstance() {
        return instance;
    }
    
    @Override
    protected void init() {
        instance = this;
        
        // Setup CraftTools.
        CraftPlayerManager.setPlayerClass(ZombiesPlayer.class);
        CraftPlayerManager.setDataClass(ZombiesPlayerData.class);
        CraftEntityManager.self().setCitizensUsage(false);
        CraftEntityManager.traitClass = ZombieEntityTrait.class;
    }

    @Override
    protected void enable() {
        // External listeners.
        registerListener(new MenuFunctionListener());
        // Commands. (auto-loader doesn't work)
        registerCommand(new WindowCommand());

        // Register the zombie trait.
        CitizensAPI.getTraitFactory().registerTrait(TraitInfo.create(ZombieEntityTrait.class));
        
        // Initialize managers.
        ZombiesItemManager.initialize();
        
        this.getLogger().info("Enabled Zombies by Nightraid.");
    }

    @Override
    protected void disable() {
        // Remove entities & NPCs.
        CitizensAPI.getNPCRegistry().deregisterAll();
        for(World world : Bukkit.getWorlds())
            world.getEntities().forEach(Entity::remove);
        
        this.getLogger().info("Disabled Zombies by Nightraid.");
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
