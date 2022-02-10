package com.kingrainbow44.crafttools.runnable;

import com.kingrainbow44.crafttools.CraftEntity;
import com.kingrainbow44.crafttools.entity.CraftEntityManager;

public final class UpdateRunnable implements Runnable {
    @Override
    public void run() {
        for(CraftEntity entity : CraftEntityManager.self().getEntities()) {
            entity.tick();
        }
    }
}
