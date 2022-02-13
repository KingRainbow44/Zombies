package games.nightraid.zombies.item;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import games.nightraid.zombies.item.data.ZombiesBaseItemData;
import games.nightraid.zombies.item.data.ZombiesItemData;
import games.nightraid.zombies.player.ZombiesPlayer;
import games.nightraid.zombies.utils.GunUtil;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static com.kingrainbow44.crafttools.utils.CraftUtils.colorize;

public abstract class ZombiesItem {
    private final ZombiesBaseItemData baseData;
    
    public ZombiesItem(ZombiesBaseItemData identifier) {
        this.baseData = identifier;
    }

    /**
     * Called when the player right-clicks with a gun-type weapon.
     * @param player The player who is running it.
     * @param item The weapon they are using.
     *               
     * This is a generic method and can be overridden.
     */
    public void execute(ItemStack item, ZombiesPlayer player) {
        if(!GunUtil.canShoot(item))
            return;
        GunUtil.shootBeam(this.baseData.distance, player.getBukkitPlayer(), Particle.CRIT, entities -> {
            for(final LivingEntity entity : entities) {
                if(entity.isDead())
                    continue;
                entity.damage(getDamage(item), player.getBukkitPlayer());
            }
        }); GunUtil.shootLogic(item);
        player.startGunCooldown(this.baseData.itemId, this.getFireRate(item));
    }

    /**
     * Called when the player left-clicks with a gun-type weapon.
     * @param item The weapon they are using.
     *             
     * This is a generic method and can be overridden.
     */
    public void reload(ItemStack item) {
        if(!GunUtil.canReload(item))
            return;
        GunUtil.reloadLogic(item);
    }

    /**
     * Calculate the damage based on the refinement rank.
     * @return The damage to do.
     */
    protected abstract int getDamage(ItemStack item);

    /**
     * Get the maximum ammo based on the refinement rank.
     * @return The maximum ammo for the weapon.
     */
    public int getMaxAmmo(ItemStack item) {
        return this.baseData.maxAmmo;
    }

    /**
     * Get the ammo stored in one load based on the refinement rank.
     * @return The ammo for one load.
     */
    public int getMaxClipAmmo(ItemStack item) {
        return this.baseData.maxClipAmmo;
    }

    /**
     * Get the rate at which the gun will fire based on the refinement rank.
     * @return The time in ticks between shots.
     */
    public int getFireRate(ItemStack item) {
        return this.baseData.fireRate;
    }

    /**
     * Get the time it takes to reload based on the refinement rank.
     * @return The time in ticks to reload.
     */
    public int getReloadTime(ItemStack item) {
        return this.baseData.reloadTime;
    }
    
    public final ZombiesBaseItemData getBaseData() {
        return baseData;
    }

    /**
     * Creates a new item stack with the item data.
     * @return A new item stack.
     */
    public final ItemStack build() {
        ItemStack item = new ItemStack(this.baseData.baseItem);
        NBTItem itemNbt = new NBTItem(item, true);
        
        // Add compound tag.
        NBTCompound dataTag = itemNbt.addCompound("itemData");
        // Append item data.
        dataTag.setString("identifier", this.baseData.itemId);
        dataTag.setString("weaponType", this.baseData.itemType.name());
        
        // Append mutable data.
        dataTag.setInteger("currentAmmo", this.baseData.maxAmmo);
        dataTag.setInteger("currentClip", this.baseData.maxClipAmmo);
        dataTag.setString("data", ZombiesItemData.empty().toString());
        
        // Modify item meta.
        ItemMeta itemMeta = item.getItemMeta();
        assert itemMeta != null;
        // Add item data.
        itemMeta.setDisplayName(colorize("&r" + this.baseData.itemName));

        // Set item meta & return.
        item.setItemMeta(itemMeta); return item;
    }
    
    protected final int valueFromRefinementRank(int refinementRank, int... values) {
        return values[refinementRank];
    }
}
