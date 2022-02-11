package games.nightraid.zombies.entity;

import com.kingrainbow44.crafttools.CraftEntity;
import org.bukkit.entity.LivingEntity;

/**
 * Wrap entities using this class to give it AI & other features.
 */
public final class ZombieEntity extends CraftEntity {
    /**
     * An entity constructor to do more stuff with entities.
     *
     * @param entity The entity to attach this CraftEntity instance to.
     */
    public ZombieEntity(LivingEntity entity) {
        super(entity);
    }
}
