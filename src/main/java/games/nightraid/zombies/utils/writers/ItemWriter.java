package games.nightraid.zombies.utils.writers;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import games.nightraid.zombies.item.data.ZombiesBaseItemData;
import games.nightraid.zombies.item.data.ZombiesItemData;
import games.nightraid.zombies.item.ZombiesItemManager;
import games.nightraid.zombies.item.data.ZombiesItemMutableData;
import games.nightraid.zombies.utils.ItemUtil;
import org.bukkit.inventory.ItemStack;

public final class ItemWriter {
    private final ZombiesItemData itemData;
    private final ZombiesBaseItemData baseItemData;
    private final ZombiesItemMutableData mutableItemData;
    private final ItemStack itemStack;

    /**
     * Allows for easy reading & writing to a {@link games.nightraid.zombies.item.ZombiesItem}.
     * Provides quick access to {@link ZombiesBaseItemData} and {@link ZombiesItemData}.
     * Also provides a way to modify NBT data.
     */
    public ItemWriter(ItemStack item) {
        this.itemStack = item;
        this.itemData = ItemUtil.getItemData(item);
        if(this.itemData == null) throw new NullPointerException("Item does not have item data.");

        NBTItem itemNbt = new NBTItem(item);
        NBTCompound itemData = itemNbt.getCompound("itemData");
        String itemId = itemData.getString("identifier");
        this.baseItemData = ZombiesItemManager.getItem(itemId).getBaseData();
        this.mutableItemData = new ZombiesItemMutableData(itemNbt);
    }
    
    public ZombiesItemData getItemData() {
        return this.itemData;
    }
    
    public ZombiesBaseItemData getBaseItemData() {
        return this.baseItemData;
    }
    
    public ZombiesItemMutableData getMutableItemData() {
        return this.mutableItemData;
    }
    
    public ItemStack getItemStack() {
        return this.itemStack;
    }
    
    public void write() {
        NBTItem itemNbt = new NBTItem(this.itemStack, true);
        NBTCompound itemData = itemNbt.getCompound("itemData");
        itemData.setString("data", this.itemData.toString());
    }
}
