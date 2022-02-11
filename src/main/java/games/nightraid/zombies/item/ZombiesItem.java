package games.nightraid.zombies.item;

import com.kingrainbow44.crafttools.entity.CraftEntityManager;
import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import games.nightraid.zombies.entity.ZombieEntity;
import games.nightraid.zombies.utils.GunUtil;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
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
     *               
     * This is a generic method and can be overridden.
     */
    public void execute(ItemStack item, Player player) {
        GunUtil.shoot(this.baseData.distance, player, Particle.CRIT, entities -> {
            for(final LivingEntity entity : entities) {
                final ZombieEntity zombie =
                        CraftEntityManager.self().getEntity(entity, ZombieEntity.class);
                zombie.getHandle().damage(getDamage(item));
            }
        });
    }

    /**
     * Calculate the damage based on the refinement rank.
     * @return The damage to do.
     */
    protected abstract int getDamage(ItemStack item);
    
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
        dataTag.setString("data", ZombiesItemData.empty().toString());
        
        // Modify item meta.
        ItemMeta itemMeta = item.getItemMeta();
        assert itemMeta != null;
        // Add item data.
        itemMeta.setDisplayName(colorize("&r" + this.baseData.itemName));

        // Set item meta & return.
        item.setItemMeta(itemMeta); return item;
    }
}
