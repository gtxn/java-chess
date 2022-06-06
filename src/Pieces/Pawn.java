package Pieces;

import GameComponents.Board;

import java.util.ArrayList;

public class Pawn extends Piece {
    public Pawn(Character color) {
        super(color, "/Users/glenda/Coding/learning/java/chess/src/Pieces/images/" + color + "p.png");
    }

    public ArrayList<int[]> getSquares(Board board, int currRow, int currCol) {
        ArrayList<int[]> directions = new ArrayList<>();
        Piece[][] boardArr = board.getBoardArr();

        // If the pawn is at the first row, let it move twice
        if (currRow == 6 && board.getBoardArr()[currRow - 1][currCol] == null &&
                board.getBoardArr()[currRow - 2][currCol] == null) {
          directions.add(new int[] {-2, 0});
        }

        // Check that pawn can only go straight if no piece infront
        if (board.getBoardArr()[currRow - 1][currCol] == null) {
            directions.add(new int[] {-1, 0});
        }

        // Check if pawn can eat diagonally
        Piece rightDiag = null;
        Piece leftDiag = null;
        if (currRow > 0) {
            if (currCol != 7) {
                rightDiag = boardArr[currRow - 1][currCol + 1];
            }
            if (currCol != 0) {
                leftDiag = boardArr[currRow - 1][currCol - 1];
            }
        }

        if (leftDiag != null && leftDiag.getColor() != this.getColor()) {
            directions.add(new int[] {-1, -1});
        }
        if (rightDiag != null && rightDiag.getColor() != this.getColor()) {
            directions.add(new int[] {-1, 1});
        }

        int[][] directionsArr = new int[directions.size()][];
        directionsArr = directions.toArray(directionsArr);

        return super.getSqFromFixed(directionsArr, currRow, currCol, board);
    }
}
