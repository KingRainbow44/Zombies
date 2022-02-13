package games.nightraid.zombies.utils.absolute;

import games.nightraid.zombies.item.ZombiesItem;
import games.nightraid.zombies.item.ZombiesItemManager;
import games.nightraid.zombies.utils.ItemUtil;
import games.nightraid.zombies.utils.writers.ItemWriter;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.ipvp.canvas.slot.Slot;
import org.ipvp.canvas.type.AbstractMenu;
import org.ipvp.canvas.type.ChestMenu;

import java.util.List;

import static com.kingrainbow44.crafttools.utils.CraftUtils.colorize;

public final class Menus {
    public static AbstractMenu debugMenu() {
        ChestMenu menu = ChestMenu.builder(1)
                .title("Debug Menu").build();
        menu.getSlot(4).setItem(new ItemUtil.ItemBuilder(Material.EMERALD)
                .name("&aRefine Item").build());
        menu.getSlot(4).setClickHandler((player, info) -> {
            ItemStack item = player.getInventory().getItemInMainHand();
            try {
                ItemWriter writer = new ItemWriter(item);
                if(
                        writer.getItemData().refinementRank >=
                        writer.getBaseItemData().maxRefine
                ) player.sendMessage(colorize("&cYou've reached the max refinement rank for this item!")); 
                else {
                    writer.getItemData().refinementRank++;
                    writer.write(); player.getInventory().setItemInMainHand(item);
                }
            } catch (Exception ignored) {
                player.sendMessage(colorize("&cOops! Something went wrong!"));
            }
        });
        return menu;
    }
    
    public static AbstractMenu itemMenu() {
        ChestMenu menu = ChestMenu.builder(6)
                .title("Items").build();
        List<ZombiesItem> items = ZombiesItemManager.getItems();
        for(int i = 0; i < items.size(); i++) {
            Slot slot = menu.getSlot(i);
            ItemStack item = items.get(i).build();
            slot.setItem(item);
            slot.setClickHandler((player, info) -> {
                if(info.getClickType() != ClickType.MIDDLE) {
                    player.getInventory().addItem(item);
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_TRADE, 1, 1);
                }
            });
        } return menu;
    }
    
    public static AbstractMenu gameMenu() {
        ChestMenu menu = ChestMenu.builder(6)
                .title("Game Management").build();
        return menu;
    }
}
