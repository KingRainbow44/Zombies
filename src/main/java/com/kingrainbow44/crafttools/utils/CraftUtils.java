package com.kingrainbow44.crafttools.utils;

import com.kingrainbow44.crafttools.player.PlayerListener;
import com.kingrainbow44.crafttools.runnable.UpdateRunnable;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.util.Set;

public final class CraftUtils {

    private static JavaPlugin plugin = null;

    public static Set<?> getClasses(String packagePath, Class<?> lookFor) {
        Reflections reflector = new Reflections(packagePath);
        if(lookFor == null) return null;

        try {
            return reflector.getSubTypesOf(lookFor);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return null;
    }

    public static String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static void setPlugin(JavaPlugin plugin) {
        if(CraftUtils.plugin == null) {
            CraftUtils.plugin = plugin;
            plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new UpdateRunnable(), 0L, 1L);
            plugin.getServer().getPluginManager().registerEvents(new PlayerListener(), plugin);
        } else {
            throw new IllegalStateException("A CraftTools instance has already been loaded by " + CraftUtils.plugin.getName() + ".");
        }
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }
}

