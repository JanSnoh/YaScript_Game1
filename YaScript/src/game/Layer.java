package game;

public class Layer {
    private final Chunk[][] chunks;

    public Layer(int width, int height) {
        this.chunks = new Chunk[width][height];
    }
}
