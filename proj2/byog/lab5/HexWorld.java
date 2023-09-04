package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 75;
    private static final int HEIGHT = 50;
    private static final Random RANDOM = new Random(114514);

    public static class IntVec2d {
        public int x;
        public int y;

        public IntVec2d(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static IntVec2d[] calcBegins(IntVec2d p, int size) {
        IntVec2d[] result = new IntVec2d[2 * size];
        for (int i = 0; i < size; i++) {
            result[i] = new IntVec2d(p.x + size - i - 1, p.y - i);
            result[result.length - 1 - i] = new IntVec2d(result[i].x, p.y - 2 * size + i + 1);
        }
        return result;
    }

    private static int[] calcLengths(IntVec2d p, int size) {
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = size + 2 * i;
        }
        return result;
    }

    private static void drawHexRow(TETile[][] world, IntVec2d begin, int length, TETile t) {
        for (int i = 0; i < length; i++) {
            world[begin.x + i][begin.y] = t;
        }
    }

    // the Position p specifies the upper left corner of the rectangular frame of hexagon
    public static void addHexagon(TETile[][] world, IntVec2d p, int size, TETile tile) {
        IntVec2d[] begins = calcBegins(p, size);
        int[] lengths = calcLengths(p, size);
        for (int i = 0; i < size; i++) {
            drawHexRow(world, begins[i], lengths[i], tile);
            drawHexRow(world, begins[begins.length - 1 - i], lengths[i], tile);
        }
    }

    // methods for drawing Tesselation of Hexagons
    private static IntVec2d findLeftUpperNeighbor(IntVec2d p, int size) {
        return new IntVec2d(p.x - 2 * size + 1, p.y + size);
    }

    private static IntVec2d findRightUpperNeighbor(IntVec2d p, int size) {
        return new IntVec2d(p.x + 2 * size - 1, p.y + size);
    }

    private static IntVec2d findUpperNeighbor(IntVec2d p, int size) {
        return new IntVec2d(p.x, p.y + 2 * size);
    }

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(3);
        switch (tileNum) {
            case 0:
                return Tileset.WALL;
            case 1:
                return Tileset.FLOWER;
            case 2:
                return Tileset.GRASS;
            default:
                return Tileset.GRASS;
        }
    }

    // p species the smaller hexagon at the bottom
    public static void drawTesselationHexagons(TETile[][] world, IntVec2d p, int size) {
        IntVec2d[] columnBegins = new IntVec2d[5];
        columnBegins[0] = p;
        columnBegins[1] = findRightUpperNeighbor(p, size);
        columnBegins[2] = findLeftUpperNeighbor(p, size);
        columnBegins[3] = findRightUpperNeighbor(columnBegins[1], size);
        columnBegins[4] = findLeftUpperNeighbor(columnBegins[2], size);
        int[] lengths = {5, 4, 4, 3, 3};
        for (int i = 0; i < columnBegins.length; i++) {
            addHexagon(world, columnBegins[i], size, randomTile());
            for (int j = 0; j < lengths[i]; j++) {
                columnBegins[i] = findUpperNeighbor(columnBegins[i], size);
                addHexagon(world, columnBegins[i], size, randomTile());
            }
        }
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        drawTesselationHexagons(world, new IntVec2d(37, 7), 3);

        ter.renderFrame(world);
    }
}
