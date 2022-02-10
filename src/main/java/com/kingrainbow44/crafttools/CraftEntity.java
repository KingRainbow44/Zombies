package com.kingrainbow44.crafttools;

import com.kingrainbow44.crafttools.entity.CraftEntityManager;
import com.kingrainbow44.crafttools.utils.EntityName;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_18_R1.entity.CraftLivingEntity;
import org.bukkit.entity.Creature;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

import static com.kingrainbow44.crafttools.utils.CraftUtils.colorize;
import static com.kingrainbow44.crafttools.utils.CraftUtils.getPlugin;

public class CraftEntity {

    protected final LivingEntity entity;
    protected final NPC citizensEntity;

    protected String friendlyName;

    protected double entityHealth;
    protected boolean alreadyRegistered = false;

    protected final Map<String, Object> attributes = new HashMap<>();

    /**
     * An entity constructor to do more stuff with entities.
     * @param entity The entity to attach this CraftEntity instance to.
     */
    public CraftEntity(LivingEntity entity) {
        if(getPlugin() == null)
            throw new IllegalStateException("Unable to locate a valid CraftTools installation.");

        if(CraftEntityManager.self().getCitizensUsage()) {
            citizensEntity = CitizensAPI.getNPCRegistry().createNPC(entity.getType(), entity.getCustomName());
            this.entity = (LivingEntity) citizensEntity.getEntity();

            if(CraftEntityManager.traitClass != null)
                citizensEntity.addTrait(CraftEntityManager.traitClass);
        } else {
            this.entity = entity;
            citizensEntity = null;
        }

        try {
            friendlyName = EntityName.valueOf(entity.getType().name()).getName();
        } catch (IllegalArgumentException ignored) {
            friendlyName = entity.getName();
        }

        entityHealth = entity.getHealth();
        entity.setCustomName(colorize("&c" + friendlyName));

        if(entity.getAttribute(Attribute.GENERIC_FOLLOW_RANGE) != null) //noinspection ConstantConditions
            entity.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(40);
    }

    /**
     * Call to register the CraftEntity to the EntityManager.
     */
    public void register() {
        if(alreadyRegistered) return; alreadyRegistered = true;
        CraftEntityManager.self().registerEntity(this);
    }

    /**
     * Called every tick.
     */
    public void tick() {
        if(entity.isDead()) {
            CraftEntityManager.self().deregisterEntity(this);
        }

        entityHealth = entity.getHealth();
    }

    /*
     * Post Methods
     */

    @SuppressWarnings("ConstantConditions")
    public final void setMaxHealth(double maxHealth) {
        if(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH) != null)
            entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth);

        tick();
    }

    @SuppressWarnings("ConstantConditions")
    public final void setDefense(double defense) {
        if(entity.getAttribute(Attribute.GENERIC_ARMOR) != null)
            entity.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(defense);
    }

    @SuppressWarnings("ConstantConditions")
    public final void setDamage(double damage) {
        if(entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE) != null)
            entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(damage);
    }

    @SuppressWarnings("ConstantConditions")
    public final void setSpeed(float speed) {
        if(entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED) != null)
            entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(speed);

        if(entity.getAttribute(Attribute.GENERIC_FLYING_SPEED) != null)
            entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(speed);
    }

    public final void setTarget(LivingEntity entity) {
        if(this.entity instanceof Creature) {
            ((Creature) this.entity).setTarget(entity);
            ((Creature) this.entity).setAware(true);
        }

        if(citizensEntity != null) {
            citizensEntity.getNavigator().setTarget(entity, true);
        }
    }

    public final void addAttribute(String key, Object value) {
        attributes.put(key, value);
    }

    public final void removeAttribute(String key) {
        attributes.remove(key);
    }

    /*
     * Get Methods
     */

    public final double getCurrentHealth() {
        return entityHealth;
    }

    public final double getMaxHealth() {
        // noinspection ConstantConditions
        return entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
    }

    @NotNull
    public final CraftLivingEntity getHandle() {
        return ((CraftLivingEntity) entity);
    }

    @Nullable
    public final NPC getCitizensEntity() {
        return citizensEntity;
    }

    public final Object getAttribute(String key) {
        return attributes.get(key);
    }

    public final String getFriendlyName() {
        return friendlyName;
    }

}
