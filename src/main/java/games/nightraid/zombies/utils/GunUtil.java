package games.nightraid.zombies.utils;

import games.nightraid.zombies.item.data.ZombiesItemMutableData;
import games.nightraid.zombies.utils.writers.ItemWriter;
import lombok.AllArgsConstructor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Consumer;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public final class GunUtil {
    public static boolean canShoot(ItemStack itemStack) {
        ItemWriter itemWriter = new ItemWriter(itemStack);
        return itemWriter.getMutableItemData().getClipAmmo() >= 1;
    }
    
    public static boolean canReload(ItemStack itemStack) {
        ItemWriter itemWriter = new ItemWriter(itemStack);
        return itemWriter.getMutableItemData().getAmmoReserve() > 0 && 
            itemWriter.getMutableItemData().getClipAmmo() < itemWriter.getItem().getMaxClipAmmo(itemStack);
    }
    
    public static void shootLogic(ItemStack itemStack) {
        ItemWriter itemWriter = new ItemWriter(itemStack);
        itemWriter.getMutableItemData().modifyClipAmmo(-1);
        itemWriter.write();
    }
    
    public static void reloadLogic(ItemStack itemStack) {
        ItemWriter itemWriter = new ItemWriter(itemStack);
        ZombiesItemMutableData mutableItemData = itemWriter.getMutableItemData();
        int reloadAmount = Math.min(
                itemWriter.getItem().getMaxAmmo(itemStack) - mutableItemData.getClipAmmo(),
                itemWriter.getItem().getMaxAmmo(itemStack)
        );
        
        mutableItemData.modifyClipAmmo(reloadAmount);
        mutableItemData.modifyAmmoReserve(-reloadAmount);
        itemWriter.write();
    }
    
    public static void shootBeam(int distance, Player player, Particle particle, Consumer<List<LivingEntity>> callback) {
        shootBeam(BulletInfo.def(distance), player, particle, callback);
    }
    
    public static void shootBeam(BulletInfo info, Player player, Particle particle, Consumer<List<LivingEntity>> callback) {
        final World world = player.getWorld();
        final Location startLoc = player.getEyeLocation();
        final Location particleLoc = startLoc.clone();
        
        final Vector direction = startLoc.getDirection();
        final Vector offset = direction.clone().multiply(0.5);
        
        final List<LivingEntity> entities = new ArrayList<>();
        for(int i = 0; i < info.distance; i++) {
            for(final Entity entity : world.getNearbyEntities(particleLoc, info.rangeX, info.rangeY, info.rangeZ)) {
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
    
    @AllArgsConstructor
    public static class BulletInfo {
        public int distance;
        public double rangeX, rangeY, rangeZ;
        
        public static BulletInfo def(int distance) {
            return new BulletInfo(distance, 0.5, 0.5, 0.5);
        }
    }
}
