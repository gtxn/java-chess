package Pieces;

import GameComponents.Board;

import java.util.ArrayList;

public class Queen extends Piece{
    public Queen(Character color) {
        super(color, "/Users/glenda/Coding/learning/java/chess/src/Pieces/images/" + color + "q.png");
    }

    public ArrayList<int[]> getSquares(Board board, int currRow, int currCol) {
        int[][] directions = {
                {1, 1},
                {1, -1},
                {-1, 1},
                {-1, -1},
                {0, 1},
                {0, -1},
                {1, 0},
                {-1, 0}
        };

        return super.getSqFromContinuousDirection(directions, currRow, currCol, board);
    }
}
