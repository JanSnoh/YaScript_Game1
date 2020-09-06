package game;

import java.util.Collection;
import java.util.HashSet;

public class Chunk {

    private int xpos, ypos;
    private boolean loaded;

    private HashSet<WorldEntity> myEntities;

    public HashSet<WorldEntity> getEntities() {
        return myEntities;
    }

    public void addEntity(WorldEntity entity) {
        myEntities.add(entity);
        if (loaded) {
            entity.load();
        }
    }

    public HashSet<WorldEntity> load() {
        HashSet<WorldEntity> newlyLoaded = new HashSet<>();
        for (WorldEntity we : myEntities) {
            if (we.load()) {
                newlyLoaded.add(we);
            }
        }
        loaded = true;
        return newlyLoaded;
    }

    public HashSet<WorldEntity> unload() {
        HashSet<WorldEntity> trulyUnloaded = new HashSet<>();
        for (WorldEntity we : myEntities) {
            if (we.unload()) {
                trulyUnloaded.add(we);
            }
        }
        loaded = false;
        return trulyUnloaded;
    }
}
