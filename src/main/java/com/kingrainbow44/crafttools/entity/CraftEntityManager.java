package com.kingrainbow44.crafttools.entity;

import com.kingrainbow44.crafttools.CraftEntity;
import com.kingrainbow44.crafttools.utils.CraftUtils;
import org.bukkit.entity.LivingEntity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class CraftEntityManager {

    private final static CraftEntityManager instance = new CraftEntityManager();
    private final Map<UUID, CraftEntity> craftEntity = new HashMap<>();

    public static Class<? extends CraftEntityTrait> traitClass = null;

    private boolean useCitizens = false;

    public void registerEntity(CraftEntity entity) {
        craftEntity.put(
                entity.getHandle().getUniqueId(),
                entity
        );
    }

    public void deregisterEntity(Object entity) {
        if(entity instanceof UUID) {
            craftEntity.remove(entity);
        } else if (entity instanceof CraftEntity) {
            craftEntity.remove(((CraftEntity) entity).getHandle().getUniqueId());
        } else if (entity instanceof LivingEntity) {
            craftEntity.remove(((LivingEntity) entity).getUniqueId());
        } else throw new NullPointerException("Entity cannot be unregistered because it is not registered.");
    }
    
    public <T> T getEntity(Object object, Class<T> clazz) {
        return clazz.cast(getEntity(object));
    }

    public CraftEntity getEntity(Object entity) {
        if(entity instanceof UUID) {
            return craftEntity.get(entity);
        } else if (entity instanceof CraftEntity) {
            return craftEntity.get(((CraftEntity) entity).getHandle().getUniqueId());
        } else if (entity instanceof LivingEntity) {
            return craftEntity.get(((LivingEntity) entity).getUniqueId());
        } else throw new NullPointerException("Entity cannot be retrieved because it is not registered.");
    }

    public void setCitizensUsage(boolean useCitizens) {
        this.useCitizens = useCitizens;
    }

    public boolean getCitizensUsage() {
        if(CraftUtils.getPlugin() != null)
            if(CraftUtils.getPlugin().getServer().getPluginManager().isPluginEnabled("Citizens"))
                throw new RuntimeException("Unable to attach to Citizens while 'useCitizens' is enabled.");

        return useCitizens;
    }

    public Collection<CraftEntity> getEntities() {
        return craftEntity.values();
    }

    public static CraftEntityManager self() {
        return CraftEntityManager.instance;
    }
}
