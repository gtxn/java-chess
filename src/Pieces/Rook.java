package Pieces;

import GameComponents.Board;

import java.util.ArrayList;

public class Rook extends Piece{
    public Rook(Character color) {
        super(color, "/Users/glenda/Coding/learning/java/chess/src/Pieces/images/" + color + "r.png");
    }

    public ArrayList<int[]> getSquares(Board board, int currRow, int currCol) {
        int[][] directions = {
                {0, 1},
                {0, -1},
                {1, 0},
                {-1, 0},
        };

        return super.getSqFromContinuousDirection(directions, currRow, currCol, board);
    }
}
