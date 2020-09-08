package game;

public class Layer {
    private final Chunk[][] chunks;

    public Layer(int width, int height) {
        this.chunks = new Chunk[width][height];
    }

    public void setChunk(int x, int y, Chunk chunk) {
        chunks[x][y] = chunk;
    }

    public Chunk getChunk(int x, int y) {
        return chunks[x][y];
    }
}
