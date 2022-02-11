package games.nightraid.zombies.utils.config;

import org.bukkit.Material;
import org.bukkit.World;

public final class Block {
    public Position location;
    public String blockId;
    
    public static void placeBlock(World world, Block block) {
        world.getBlockAt(
                Math.round(block.location.x),
                Math.round(block.location.y),
                Math.round(block.location.z)
        ).setType(Material.valueOf(block.blockId));
    }
}
