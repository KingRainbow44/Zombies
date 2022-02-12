package games.nightraid.zombies.commands;

import games.nightraid.zombies.utils.GenericCommand;
import games.nightraid.zombies.utils.absolute.Menus;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.ipvp.canvas.type.AbstractMenu;

import java.util.Collections;

import static com.kingrainbow44.crafttools.utils.CraftUtils.colorize;

public final class WindowCommand extends GenericCommand {
    public WindowCommand() {
        super("window", "Open a window.", "/window <window name>", Collections.emptyList());
    }

    @Override
    protected void run(CommandSender sender, String[] args) {
        sender.sendMessage(colorize("&cYou must be a player to use this command."));
    }

    @Override
    protected void run(Player player, String[] args) {
        if(args.length < 1) {
            player.sendMessage(colorize("&cUsage: &7" + getUsage()));
            return;
        }
        
        switch(args[0].toLowerCase()) {
            default:
                player.sendMessage(colorize("&cUnknown window: &7" + args[0]));
                break;
            case "debug":
                AbstractMenu menu = Menus.debugMenu();
                menu.open(player);
                break;
        }
    }
}
