package games.nightraid.zombies.utils;

import games.nightraid.zombies.entity.ZombieEntity;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

public final class EntityUtil {
    public static void spawnEntity(EntityType entityType, Location location) {
        assert location.getWorld() != null;
        
        Entity entity = location.getWorld().spawnEntity(location, entityType);
        if(!(entity instanceof LivingEntity)) return;

        ZombieEntity wrappedEntity = new ZombieEntity((LivingEntity) entity);
        wrappedEntity.register();
    }
}
