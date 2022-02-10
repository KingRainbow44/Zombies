package com.kingrainbow44.crafttools.player;

import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class CraftPlayerManager {

    private final static CraftPlayerManager instance = new CraftPlayerManager();
    private static final Map<UUID, ICraftPlayer> craftPlayer = new HashMap<>();

    private static Class<? extends ICraftPlayer> playerClass = null;
    private static Class<? extends CraftPlayerData> dataClass = null;

    public static void setPlayerClass(Class<? extends ICraftPlayer> playerClass) {
        CraftPlayerManager.playerClass = playerClass;
    }

    public static void setDataClass(Class<? extends CraftPlayerData> dataClass) {
        CraftPlayerManager.dataClass = dataClass;
    }

    public void registerPlayer(Player player) {
        try {
            ICraftPlayer constructedPlayer = playerClass.getDeclaredConstructor().newInstance();
            constructedPlayer.setBukkitPlayer(player);
            if(dataClass != null)
                constructedPlayer.setPlayerData(dataClass.getDeclaredConstructor().newInstance());

            craftPlayer.put(
                    player.getUniqueId(),
                    constructedPlayer
            );

            constructedPlayer.onRegister(player);
        } catch (InvocationTargetException |
                InstantiationException |
                IllegalAccessException |
                NoSuchMethodException e
        ) {
            e.printStackTrace();
            System.out.println("Unable to register player " + player.getName());
        }
    }

    public void deregisterPlayer(Player player) {
        craftPlayer.get(player.getUniqueId()).onUnregister(player);
        craftPlayer.remove(player.getUniqueId());
    }

    public ICraftPlayer getCraftPlayer(Player player) {
        return craftPlayer.get(player.getUniqueId());
    }
    
    public <T> T getCraftPlayer(Player player, Class<T> clazz) {
        return clazz.cast(craftPlayer.get(player.getUniqueId()));
    }

    public static CraftPlayerManager self() {
        return CraftPlayerManager.instance;
    }
}
