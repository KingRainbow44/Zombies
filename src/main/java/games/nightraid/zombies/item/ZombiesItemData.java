package games.nightraid.zombies.item;

import com.google.gson.Gson;
import lombok.Setter;

@Setter
public final class ZombiesItemData {
    public int refinementRank;
    
    public String toString() {
        return new Gson().toJson(this);
    }
    
    public static ZombiesItemData empty() {
        return new ZombiesItemData();
    }
    
    public static ZombiesItemData toObject(final String json) {
        return new Gson().fromJson(json, ZombiesItemData.class);
    }
}
