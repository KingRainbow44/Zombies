package games.nightraid.zombies.utils;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Consumer;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public final class GunUtil {
    public static void shoot(int distance, Player player, Particle particle, Consumer<List<LivingEntity>> callback) {
        final World world = player.getWorld();
        final Location startLoc = player.getEyeLocation();
        final Location particleLoc = startLoc.clone();
        
        final Vector direction = startLoc.getDirection();
        final Vector offset = direction.clone().multiply(0.5);
        
        final List<LivingEntity> entities = new ArrayList<>();
        for(int i = 0; i < distance; i++) {
            for(final Entity entity : world.getNearbyEntities(particleLoc, 0.5, 0.5, 0.5)) {
                if(!(entity instanceof LivingEntity)) continue;
                if(entity == player) continue;
                final LivingEntity livingEntity = (LivingEntity) entity;

                final Vector particleMinVector = new Vector(
                        particleLoc.getX() - 0.25,
                        particleLoc.getY() - 0.25,
                        particleLoc.getZ() - 0.25);
                final Vector particleMaxVector = new Vector(
                        particleLoc.getX() + 0.25,
                        particleLoc.getY() + 0.25,
                        particleLoc.getZ() + 0.25);
                
                if(!entity.getBoundingBox().overlaps(particleMinVector, particleMaxVector)) continue;
                if(entities.contains(livingEntity)) continue;
                
                entities.add(livingEntity); // Add to the list.
            }
            
            particleLoc.add(offset); // Continue with the offset.
            world.spawnParticle(particle, particleLoc, 0);
        }
        
        callback.accept(entities); // Accept the callback.
    }
}
