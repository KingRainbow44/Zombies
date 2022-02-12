package games.nightraid.zombies.item.data;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.inventory.ItemStack;

public final class ZombiesItemMutableData {
    private final NBTItem itemNbt;
    private final NBTCompound itemData;
    
    public ZombiesItemMutableData(NBTItem itemNbt) {
        this.itemNbt = itemNbt;
        this.itemData = itemNbt.getCompound("itemData");
    }
    
    public int getAmmoReserve() {
        return this.itemData.getInteger("currentAmmo");
    }
    
    public int getClipAmmo() {
        return this.itemData.getInteger("currentClip");
    }
    
    public void reset(ZombiesBaseItemData baseData) {
        this.itemData.setInteger("currentAmmo", baseData.maxAmmo);
        this.itemData.setInteger("currentClip", baseData.maxClipAmmo);
    }

    /**
     * @param modifyBy Should be a negative number for subtracting, positive for adding.
     */
    public void modifyAmmoReserve(int modifyBy) {
        this.itemData.setInteger("currentAmmo", this.getAmmoReserve() + modifyBy);
    }
    
    /**
     * @param modifyBy Should be a negative number for subtracting, positive for adding.
     */
    public void modifyClipAmmo(int modifyBy) {
        this.itemData.setInteger("currentClip", this.getClipAmmo() + modifyBy);
    }
    
    public ItemStack exportAsItem() {
        return this.itemNbt.getItem();
    }
    
    public NBTItem exportAsNbt() {
        return this.itemNbt;
    }
}
