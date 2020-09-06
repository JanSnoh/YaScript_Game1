package game;

import java.util.HashSet;

public class Chunkloader {
    private HashSet<WorldEntity> loadedEntities;
    private HashSet<Chunk> loadedChunks;

    void load(Chunk chunk) {
        loadedEntities.addAll(chunk.load());
        loadedChunks.add(chunk);
    }

    void unload(Chunk chunk) {
        loadedEntities.removeAll(chunk.unload());
        loadedChunks.remove(chunk);
    }

    void clear() {
        for (Chunk chunk : loadedChunks) {
            chunk.unload();
        }
        loadedChunks.clear();
    }

}
