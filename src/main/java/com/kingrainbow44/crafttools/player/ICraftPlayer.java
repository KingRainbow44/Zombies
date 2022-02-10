package com.kingrainbow44.crafttools.player;

import org.bukkit.entity.Player;

public interface ICraftPlayer {
    Player getBukkitPlayer();
    void setBukkitPlayer(Player player);

    CraftPlayerData getPlayerData();
    void setPlayerData(CraftPlayerData playerData);

    void sendMessage(Object... message);

    void onRegister(Player player);

    void onUnregister(Player player);
}
