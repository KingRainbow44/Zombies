package games.nightraid.zombies.item;

import games.nightraid.zombies.item.types.Pistol;
import games.nightraid.zombies.item.types.TestGun;

import java.util.*;

public final class ZombiesItemManager {
    private static final Map<String, ZombiesItem> items = new HashMap<>(); 
    
    public static void initialize() {
        registerItem(new TestGun());
        registerItem(new Pistol());
    }
    
    public static ZombiesItem getItem(String itemId) {
        return items.get(itemId);
    }
    
    public static List<ZombiesItem> getItems() {
        return new ArrayList<>(items.values());
    }
    
    private static void registerItem(ZombiesItem item) {
        items.put(item.getBaseData().itemId, item);
    }
}
