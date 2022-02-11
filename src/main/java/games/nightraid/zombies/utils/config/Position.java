package games.nightraid.zombies.utils.config;

import org.bukkit.Location;

public final class Position {
    public float x, y, z;
    public float pitch, yaw;
    
    public Location deserialize(Position position) {
        return new Location(null, position.x, position.y, position.z, position.yaw, position.pitch);
    }
}
