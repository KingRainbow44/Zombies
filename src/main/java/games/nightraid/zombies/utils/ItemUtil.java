package games.nightraid.zombies.utils;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import games.nightraid.zombies.item.data.ZombiesItemData;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;
import java.util.Objects;

import static com.kingrainbow44.crafttools.utils.CraftUtils.colorize;

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
    
    public static class ItemBuilder {
        private final Material material;
        
        private int count = 1;
        private String name = null;
        
        public ItemBuilder(Material material) {
            this.material = material;
        }
        
        public ItemBuilder count(int count) {
            this.count = count; return this;
        }
        
        public ItemBuilder name(@Nullable String name) {
            this.name = name; return this;
        }
        
        public ItemStack build() {
            ItemStack itemStack = new ItemStack(material, count);
            ItemMeta itemMeta = itemStack.getItemMeta(); assert itemMeta != null;
            
            if(name != null) 
                itemMeta.setDisplayName(colorize(name));
            
            itemStack.setItemMeta(itemMeta); return itemStack;
        }
    }
}
