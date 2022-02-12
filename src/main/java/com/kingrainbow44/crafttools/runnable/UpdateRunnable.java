package com.kingrainbow44.crafttools.runnable;

import com.kingrainbow44.crafttools.CraftEntity;
import com.kingrainbow44.crafttools.entity.CraftEntityManager;
import com.kingrainbow44.crafttools.player.CraftPlayerManager;
import com.kingrainbow44.crafttools.player.ICraftPlayer;

import java.util.ConcurrentModificationException;

public final class UpdateRunnable implements Runnable {
    @Override
    public void run() {
        try {
            CraftEntityManager.self()
                    .getEntities().forEach(CraftEntity::tick);
            CraftPlayerManager.self()
                    .getPlayers().forEach(ICraftPlayer::tick);
        } catch (ConcurrentModificationException ignored) { }
    }
}
