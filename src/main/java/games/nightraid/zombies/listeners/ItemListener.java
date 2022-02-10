package games.nightraid.zombies.listeners;

import games.nightraid.zombies.player.ZombiesPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public final class ItemListener implements Listener {
    @EventHandler
    public void onInteract(final PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        final ZombiesPlayer zombiesPlayer = ZombiesPlayer.get(player);
    }
}
