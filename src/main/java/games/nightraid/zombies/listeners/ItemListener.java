package games.nightraid.zombies.listeners;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import games.nightraid.zombies.item.ZombiesItemManager;
import games.nightraid.zombies.item.ZombiesItemType;
import games.nightraid.zombies.player.ZombiesPlayer;
import games.nightraid.zombies.player.ZombiesPlayerFlags;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

/**
 * TODO: Optimize code for efficiency & storage.
 */
public final class ItemListener implements Listener {
    @EventHandler
    public void onInteract(final PlayerInteractEvent event) {
        if(event.getAction().name().contains("RIGHT"))
            this.shoot(event);
        else if(event.getAction().name().contains("LEFT"))
            this.reload(event);
    }
    
    private void shoot(final PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        final ZombiesPlayer zombiesPlayer = ZombiesPlayer.get(player);

        if(!zombiesPlayer.getFlag(ZombiesPlayerFlags.IN_GAME))
            return;
        final ItemStack item = event.getItem();
        if(item == null || item.getType() == Material.AIR)
            return;
        final NBTItem itemNbt = new NBTItem(item);

        // Perform item checks.
        if(!itemNbt.hasKey("itemData")) return;
        final NBTCompound itemData = itemNbt.getCompound("itemData");

        if(!Objects.equals(itemData.getString("weaponType"), ZombiesItemType.GUN.name()))
            return;
        final String itemId = itemData.getString("identifier");
        if(!zombiesPlayer.canUseGun(itemId))
            return;

        // Execute item.
        ZombiesItemManager.getItem(itemId).execute(item, zombiesPlayer);
    }
    
    private void reload(final PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        final ZombiesPlayer zombiesPlayer = ZombiesPlayer.get(player);

        if(!zombiesPlayer.getFlag(ZombiesPlayerFlags.IN_GAME))
            return;
        final ItemStack item = event.getItem();
        if(item == null || item.getType() == Material.AIR)
            return;
        final NBTItem itemNbt = new NBTItem(item);

        // Perform item checks.
        if(!itemNbt.hasKey("itemData")) return;
        final NBTCompound itemData = itemNbt.getCompound("itemData");

        if(!Objects.equals(itemData.getString("weaponType"), ZombiesItemType.GUN.name()))
            return;
        final String itemId = itemData.getString("identifier");

        // Execute item.
        ZombiesItemManager.getItem(itemId).reload(item);
    }
}
