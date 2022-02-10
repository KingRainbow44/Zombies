package com.kingrainbow44.crafttools;

import com.kingrainbow44.crafttools.player.CraftPlayerData;
import com.kingrainbow44.crafttools.player.ICraftPlayer;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CraftPlayer implements ICraftPlayer {

    private Player player = null;
    protected CraftPlayerData playerData;

    public CraftPlayer() {

    }

    public CraftPlayer(Player player) {
        this.player = player;
    }

    public CraftPlayer(Player player, CraftPlayerData playerData) {
        this.player = player;
        this.playerData = playerData;
    }

    @Override
    public final Player getBukkitPlayer() {
        return player;
    }

    @Override
    public final void setBukkitPlayer(Player player) {
        if(this.player != null) return;
        this.player = player;
    }

    @Override
    public final void sendMessage(Object... message) {
        for(Object msg : message) {
            if(msg instanceof TextComponent) {
                player.spigot().sendMessage((TextComponent) msg);
            } else if (msg instanceof String) {
                player.sendMessage(
                        ChatColor.translateAlternateColorCodes('&', (String) msg)
                );
            } else {
                Bukkit.getLogger().warning("Unable to send message. It was not a recognised type.");
                System.out.println(msg);
            }
        }
    }

    @Override
    public void onRegister(Player player) { }

    @Override
    public void onUnregister(Player player) { }

    @Override
    public CraftPlayerData getPlayerData() {
        return null;
    }

    @Override
    public final void setPlayerData(CraftPlayerData playerData) {
        if(this.playerData != null) return;
        this.playerData = playerData;
    }
}
