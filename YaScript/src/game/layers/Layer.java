package game.layers;

import game.Chunk;
import math.Vector;

/**
 * Layer containes Chunks. Map of the world.
 */
public class Layer {
    private final Chunk[][] map;

    /**
     * Creates a new Layer with given dimensions.
     * @param width number of chunks in x dir.
     * @param height number of cunks in y dir.
     */
    public Layer(int width, int height) {
        this.map = new Chunk[width][height];
    }

    /**
     * Places Chunk into Map
     * @param x x position
     * @param y y position
     * @param chunk Chunk to add at given position.
     */
    public void setChunk(int x, int y, Chunk chunk) {
        map[x][y] = chunk;
        chunk.setXY(x, y);
    }

    /**
     * Gets chunk at given position.
     * @param x x position;
     * @param y y position;
     * @return chunk at given position.
     */
    public Chunk getChunk(int x, int y) {
        return map[x][y];
    }

    /**
     * Determines which Chunk is at given coordinates
     * @param position position to check for Chunk
     * @return Chunk at position
     */
    public Chunk calculateChunk(Vector position) {
        int x = (int) position.get(0) / Chunk.width;
        int y = (int) position.get(1) / Chunk.width;
        return getChunk(x, y);
    }
}
