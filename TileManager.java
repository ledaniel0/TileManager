package Project1;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Name: Daniel Le
 * Section: CS143 7707
 * <p>
 * <p>
 * Description:
 * This class manages a list of Tile objects, providing methods for
 * adding, drawing, raising, lowering, deleting, deleting all, and shuffling
 * tiles on the screen. The addTile method adds a Tile object to the list of tiles.
 * The drawAll method draws all the tiles in the list using a Graphics object passed
 * as a parameter. The raise, lower, delete, and deleteAll methods allow the user to
 * interact with individual or all tiles. The raise method moves the tile clicked on
 * to the front of the other tiles, the lower method moves the tile clicked on behind
 * the other tiles, the delete method removes the tile clicked on, and the deleteAll
 * method removes all tiles from the screen. The shuffle method randomizes the x and y
 * coordinates of every tile when the user presses the S key. The insideTile method is
 * a helper method that checks if the user clicked on a tile and returns the tile object
 * if true, otherwise it returns an empty string.
 */

public class TileManager {
    private ArrayList<Tile> tileList;

    public TileManager() {
        this.tileList = new ArrayList<>();
    }

    /**
     * The addTile method adds the Tile given as a parameter to the list of tiles.
     *
     * @param rect
     */
    public void addTile(Tile rect) {
        this.tileList.add(rect);
    }

    /**
     * The drawAll method draws all the tiles in the list.
     *
     * @param g
     */
    public void drawAll(Graphics g) {
        for (Tile tiles : tileList) {
            tiles.draw(g);
        }
    }

    /**
     * The raise method is called when the user left-clicks on a tile
     * and moves the tile to the front.
     *
     * @param x
     * @param y
     */
    public void raise(int x, int y) {
        Object tile = insideTile(x, y);
        if (!tile.equals(null)) {
            this.tileList.remove(tile);
            this.tileList.add((Tile) tile);
        }
    }

    /**
     * The lower method gets called when the user shift-left-clicks on a tile and moves
     * the tile selected behind the other tiles.
     *
     * @param x
     * @param y
     */
    public void lower(int x, int y) {
        Object tile = insideTile(x, y);
        if (!tile.equals("")) {
            this.tileList.remove(tile);
            this.tileList.add(0, (Tile) tile);
        }
    }

    /**
     * The delete method gets called when the user right-clicks on a tile and deletes
     * the tile clicked on.
     *
     * @param x
     * @param y
     */
    public void delete(int x, int y) {
        Object tile = insideTile(x, y);
        if (!tile.equals("")) {
            this.tileList.remove(tile);
        }
    }

    /**
     * The deleteAll method removes all tiles from the screen
     * when the user shift-right-clicks.
     *
     * @param x
     * @param y
     */
    public void deleteAll(int x, int y) {
        this.tileList.removeAll(this.tileList);
    }

    /**
     * The shuffle method gets called when the user presses the S key.
     * It then randomizes the x and y coordinates of every tile.
     *
     * @param width
     * @param height
     */
    public void shuffle(int width, int height) {
        Collections.shuffle(this.tileList);
        for (Tile tile : tileList) {
            Random random = new Random();
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            tile.setY(y);
            tile.setX(x);
        }
    }

    /**
     * The insideTile method is a helper method that returns a tile
     * if the user clicked on a tile or an empty string if it isn't.
     *
     * @param x
     * @param y
     * @return
     */
    public Object insideTile(int x, int y) {
        for (int i = this.tileList.size() - 1; i >= 0; i--) {
            Tile tile = this.tileList.get(i);
            if ((x >= tile.getX()) && (x <= tile.getWidth() + tile.getX()) && (y >= tile.getY()) && (y <= tile.getHeight() + tile.getY())) {
                return tile;
            }
        }
        return "";
    }
}
