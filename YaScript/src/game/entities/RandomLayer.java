package game.entities;

import game.Chunk;
import game.Layer;
import math.Vector;

public class RandomLayer extends Layer {

    public RandomLayer(int width, int height) {
        super(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Chunk chunk = new Chunk();  double dx = Math.random();
                double dy = Math.random();
                double r = Math.random() * Chunk.width;
                dx = (dx + x) * Chunk.width;
                dy = (dy + y) * Chunk.width;
                switch (x + y % 3) {
                    case 0:
                        chunk.addEntity(new Circle(new Vector(dx, dy), r));
                        break;
                    case 1:
                        double rot = Math.random() * Math.PI;
                        chunk.addEntity(new Wall(new Vector(dx, dy), r, rot));
                        break;
                    default:
                }
                setChunk(x,y,chunk);
            }
        }
    }
}
