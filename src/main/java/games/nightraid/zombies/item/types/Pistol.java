package games.nightraid.zombies.item.types;

import games.nightraid.zombies.item.data.ZombiesBaseItemData;
import games.nightraid.zombies.item.ZombiesItem;
import games.nightraid.zombies.item.ZombiesItemType;
import games.nightraid.zombies.utils.ItemUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public final class Pistol extends ZombiesItem {
    public Pistol() {
        super(new ZombiesBaseItemData(
                "pistol", "Pistol",
                "Shoots literally nothing.", Material.WOODEN_HOE,
                ZombiesItemType.GUN, 300, 10, 1,
                10, 20, 30
        ));
    }

    @Override
    protected int getDamage(ItemStack item) {
        return 6;
    }

    @Override
    public int getMaxAmmo(ItemStack item) {
        int refinementRank = ItemUtil.getRefinementFromItem(item);
        return this.valueFromRefinementRank(refinementRank, 300, 448);
    }

    @Override
    public int getMaxClipAmmo(ItemStack item) {
        int refinementRank = ItemUtil.getRefinementFromItem(item);
        return this.valueFromRefinementRank(refinementRank, 10, 14);
    }

    @Override
    public int getFireRate(ItemStack item) {
        int refinementRank = ItemUtil.getRefinementFromItem(item);
        return this.valueFromRefinementRank(refinementRank, 10, 8);
    }

    @Override
    public int getReloadTime(ItemStack item) {
        int refinementRank = ItemUtil.getRefinementFromItem(item);
        return this.valueFromRefinementRank(refinementRank, 30, 20);
    }
}
