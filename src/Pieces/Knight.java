package Pieces;

import GameComponents.Board;

import java.util.ArrayList;

public class Knight extends Piece{
    public Knight(Character color) {
        super(color, "/Users/glenda/Coding/learning/java/chess/src/Pieces/images/" + color + "n.png");
    }

    public ArrayList<int[]> getSquares(Board board, int currRow, int currCol) {
        int[][] directions = {
                {2, 1},
                {2, -1},
                {-2, 1},
                {-2, -1},
                {1, 2},
                {1, -2},
                {-1, 2},
                {-1, -2},
        };

        return super.getSqFromFixed(directions, currRow, currCol, board);
    }
}
