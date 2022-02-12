package games.nightraid.zombies.utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.List;

public abstract class GenericCommand extends Command {
    public GenericCommand(String name) {
        super(name);
    }

    public GenericCommand(String name, String description, String usageMessage, List<String> aliases) {
        super(name, description, usageMessage, aliases);
    }

    @Override
    public boolean execute(@Nonnull CommandSender sender, @Nonnull String commandLabel, @Nonnull String[] args) {
        if(sender instanceof Player)
            run((Player) sender, args);
        else run(sender, args);
        return true;
    }
    
    protected abstract void run(CommandSender sender, String[] args);
    protected abstract void run(Player player, String[] args);
}
