package games.nightraid.zombies.utils;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import games.nightraid.zombies.item.ZombiesItemData;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.Objects;

public final class ItemUtil {
    @Nullable
    public static ZombiesItemData getItemData(ItemStack item) {
        final NBTItem itemNbt = new NBTItem(item);

        // Perform item checks.
        if(!itemNbt.hasKey("itemData")) return null;
        final NBTCompound itemData = itemNbt.getCompound("itemData");

        return ZombiesItemData.toObject(itemData.getString("data"));
    }
    
    public static int getRefinementFromItem(final ItemStack item) {
        return Objects.requireNonNull(getItemData(item)).refinementRank;
    }
}
