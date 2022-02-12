package games.nightraid.zombies.item.data;

import games.nightraid.zombies.item.ZombiesItemType;
import lombok.AllArgsConstructor;
import org.bukkit.Material;

@AllArgsConstructor
public final class ZombiesBaseItemData {
    public final String itemId, itemName, itemDescription;
    public final Material baseItem;
    public final ZombiesItemType itemType;
    public final int maxAmmo, maxClipAmmo, maxRefine, fireRate, distance, reloadTime;
}
