package com.kingrainbow44.crafttools.plugin;

import com.kingrainbow44.crafttools.utils.CraftUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.craftbukkit.v1_18_R1.CraftServer;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Set;

public abstract class ExtendedPlugin extends JavaPlugin {

    @SuppressWarnings("unchecked")
    @Override
    public final void onEnable() {
        try {
            CraftUtils.setPlugin(this);
        } catch (IllegalStateException ignored) {}

        enable();

        if(getCommandPath() != null) {
            if(!getCommandPath().equals("")) {
                Set<?> classes = CraftUtils.getClasses(getCommandPath(), Command.class);
                if(classes != null) {
                    Set<Class<? extends Command>> commands = (Set<Class<? extends Command>>) classes;
                    for(Class<? extends Command> command : commands) {
                        try {
                            registerCommand(command.getDeclaredConstructor().newInstance());
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                }
            }
        }

        if(getListenerPath() != null) {
            if(!getListenerPath().equals("")) {
                Set<?> classes = CraftUtils.getClasses(getListenerPath(), Listener.class);
                if(classes != null) {
                    Set<Class<? extends Listener>> listeners = (Set<Class<? extends Listener>>) classes;
                    for(Class<? extends Listener> listener : listeners) {
                        try {
                            registerListener(listener.getDeclaredConstructor().newInstance());
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    @Override
    public final void onDisable() {
        disable();
    }

    @Override
    public final void onLoad() {
        init();
    }

    /**
     * Register commands using the Command Map.
     * @param command The command which extends the {@link Command} class.
     */
    protected final void registerCommand(Command command) {
        CommandMap commandMap = ((CraftServer) getServer()).getCommandMap();
        commandMap.register(getName().toLowerCase(), command);
    }

    /**
     * Register listeners using the Plugin Manager.
     * @param listener The listener which implements the {@link Listener} interface.
     */
    protected final void registerListener(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, this);
    }

    /**
     * Replacement for {@link JavaPlugin#onLoad()}.
     */
    protected void init() {}

    /**
     * Replacement for {@link JavaPlugin#onEnable()}.
     */
    protected void enable() {}

    /**
     * Replacement for {@link JavaPlugin#onDisable()}.
     */
    protected void disable() {}

    /**
     * Used for automatically registering commands.
     * @return The package for commands. Return 'null' or '""' to ignore.
     */
    protected abstract String getCommandPath();

    /**
     * Used for automatically registering listeners.
     * @return The package for listeners. Return 'null' or '""' to ignore.
     */
    protected abstract String getListenerPath();
}
