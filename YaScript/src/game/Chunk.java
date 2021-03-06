package game;

import java.util.Collection;
import java.util.HashSet;

public class Chunk {

    private int xpos, ypos;
    private boolean loaded;

    /**
     * List of all Entities within Chunk
     */
    private HashSet<WorldEntity> myEntities;

    /** Entity getter
     * 
     * @return relevant Entities
     */
    public HashSet<WorldEntity> getEntities() {
        return myEntities;
    }
    
    /** Entity adder
     * 
     * 
     */
    public void addEntity(WorldEntity entity) {
        myEntities.add(entity);
        if (loaded) {
            entity.load();
        }
    }
    
    
    /**
     * Loads the Chunk and every Entity within it.
     * @return HashSet of newly loaded Entities
     */
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
    
    /**
     * Unloads the Chunk and all Entities that aren't loaded by another Chunk
     * @return HashSet of all successfully unloaded entities.
     */
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
