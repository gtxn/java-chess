package Pieces;

import GameComponents.Board;

import java.util.ArrayList;

public class Bishop extends Piece{
    public Bishop(Character color) {
        super(color, "/Users/glenda/Coding/learning/java/chess/src/Pieces/images/" + color + "b.png");
    }

    public ArrayList<int[]> getSquares(Board board, int currRow, int currCol) {
        int[][] directions = {
                {1, 1},
                {1, -1},
                {-1, 1},
                {-1, -1},
        };

        return super.getSqFromContinuousDirection(directions, currRow, currCol, board);
    }
}
