package game;

import java.util.HashSet;

public class WorldManager {
    private HashSet<WorldEntity> loadedEntities;
    private HashSet<Chunk> loadedChunks;

    /**
     * Loads the Chunk and adds itself to the loadedChunks and all newly added Entities to the loadedEntities HashSet.
     * NOTE: chunk.load() works through Side Effects and both returns a Set of newly loaded Entities aswell as load the Chunk.
     * @param chunk
     */
    void load(Chunk chunk) {
        loadedEntities.addAll(chunk.load());
        loadedChunks.add(chunk);
    }
    
    /**
     * Unloads the Chunk and removes itself from the loadedChunks and all newly unloaded Entities from the loadedEntities HashSet.
     * NOTE: chunk.unloadsload() works through Side Effects and both returns a Set of newly unloaded Entities aswell as unload the Chunk.
     * @param chunk
     */
    void unload(Chunk chunk) {
        loadedEntities.removeAll(chunk.unload());
        loadedChunks.remove(chunk);
    }
    
    /**
     * Flushes all Chunks
     */
    void clear() {
        for (Chunk chunk : loadedChunks) {
            chunk.unload();
        }
        loadedChunks.clear();
    }

}
