package games.nightraid.zombies.listeners;

import com.kingrainbow44.crafttools.CraftEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public final class EntityListener implements Listener {
    @EventHandler
    public void onSpawn(CreatureSpawnEvent event) {
        LivingEntity creature = event.getEntity();
        if(event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.CUSTOM)
            return; // This means the entity was spawned by the plugin.
        
        if(creature instanceof Player)
            return; // Do not make players zombies.
        CraftEntity craftEntity = new CraftEntity(creature);
        craftEntity.register(); // Register the entity.
    }
}
