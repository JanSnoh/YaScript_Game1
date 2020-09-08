package game;

import game.layers.Layer;
import javafx.util.Pair;
import math.Vector;
import game.entities.*;

import java.util.HashSet;

/**
 * Manages the game world, all things run through here.
 */
public class WorldManager {
    final private HashSet<WorldEntity> loadedEntities;
    final private HashSet<Chunk> loadedChunks;
    private Layer layer;

    public void setLayer(Layer layer) {
        this.layer = layer;
    }

    /**
     * Creates a World Manager
     */
    public WorldManager() {
        loadedEntities = new HashSet<>();
        loadedChunks = new HashSet<>();
    }

    /**
     * Loads the Chunk and adds itself to the loadedChunks and all newly added Entities to the loadedEntities HashSet.
     * NOTE: chunk.load() works through Side Effects and both returns a Set of newly loaded Entities aswell as load the Chunk.
     *
     * @param chunk to load
     */
    void load(Chunk chunk) {
        loadedEntities.addAll(chunk.load());
        loadedChunks.add(chunk);
    }

    /**
     * Unloads the Chunk and removes itself from the loadedChunks and all newly unloaded Entities from the loadedEntities HashSet.
     * NOTE: chunk.unloadsload() works through Side Effects and both returns a Set of newly unloaded Entities as well as unload the Chunk.
     *
     * @param chunk to unload
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

    /**
     * Searches for the closest entity and its distance.
     * @param point to measure distance from
     * @return a pair containing the closest entity and its distance
     */
    Pair<WorldEntity, Double> closestAndDist(Vector point) {
        WorldEntity closest = null;
        double min = Double.MAX_VALUE;
        for (WorldEntity we : loadedEntities) {
            double dist = we.getDist(point);
            if (dist < min) {
                min = dist;
                closest = we;
            }
        }
        return new Pair<>(closest, min);
    }

}
