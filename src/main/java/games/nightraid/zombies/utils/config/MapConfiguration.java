package games.nightraid.zombies.utils.config;

import java.util.List;

/**
 * Contains information about a Zombies map.
 */
public final class MapConfiguration {
    /*
     * Generic Data.
     */
    
    public String mapName, worldName;
    public Position spawnPosition;
    public Windows windows;
    public Positions positions;
    
    public static class Windows {
        public List<Block> windowBlocks;
    }
    
    public static class Positions {
        public Position teamSpawn;
        public Position ultimateMachine;
        public Position teamMachine;
        public List<Position> luckyChests;
        public List<Machine> powerUps;
    }
}
