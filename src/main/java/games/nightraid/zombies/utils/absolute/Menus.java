package games.nightraid.zombies.utils.absolute;

import games.nightraid.zombies.utils.ItemUtil;
import games.nightraid.zombies.utils.writers.ItemWriter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.ipvp.canvas.type.AbstractMenu;
import org.ipvp.canvas.type.ChestMenu;

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
                ) player.sendMessage("&cYou've reached the max refinement rank for this item!"); 
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
}
