package games.nightraid.zombies.item.types;

import games.nightraid.zombies.item.data.ZombiesBaseItemData;
import games.nightraid.zombies.item.ZombiesItem;
import games.nightraid.zombies.item.ZombiesItemType;
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
}
