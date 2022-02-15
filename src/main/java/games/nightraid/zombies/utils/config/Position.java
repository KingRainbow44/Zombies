package games.nightraid.zombies.utils.config;

import org.bukkit.Location;

public final class Position {
    public float x, y, z;
    public float pitch, yaw;
    
    public Location deserialize() {
        return new Location(null, this.x, this.y, this.z, this.yaw, this.pitch);
    }
}
