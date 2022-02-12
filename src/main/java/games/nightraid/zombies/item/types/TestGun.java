package games.nightraid.zombies.item.types;

import games.nightraid.zombies.item.ZombiesItem;
import games.nightraid.zombies.item.data.ZombiesBaseItemData;
import games.nightraid.zombies.item.ZombiesItemType;
import games.nightraid.zombies.utils.ItemUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public final class TestGun extends ZombiesItem {
    public TestGun() {
        super(new ZombiesBaseItemData(
                "test", "Test Gun", 
                "Shoots literally nothing.", Material.SPYGLASS,
                ZombiesItemType.GUN, 1000, 1000, 4,
                60, 30, 30
        ));
    }

    @Override
    protected int getDamage(ItemStack item) {
        return switch (ItemUtil.getRefinementFromItem(item)) {
            case 0 -> 1;
            case 1 -> 5;
            case 2 -> 10;
            case 3 -> 20;
            case 4 -> 50;
            default -> throw new IllegalStateException("Unexpected value: " + ItemUtil.getRefinementFromItem(item));
        };
    }
}
