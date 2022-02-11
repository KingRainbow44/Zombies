package games.nightraid.zombies.item;

import games.nightraid.zombies.item.types.TestGun;

import java.util.HashMap;
import java.util.Map;

public final class ZombiesItemManager {
    private static final Map<String, ZombiesItem> items = new HashMap<>(); 
    
    public static void initialize() {
        registerItem(new TestGun());
    }
    
    public static ZombiesItem getItem(String itemId) {
        return items.get(itemId);
    }
    
    private static void registerItem(ZombiesItem item) {
        items.put(item.getBaseData().itemName, item);
    }
}
