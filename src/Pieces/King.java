package Pieces;

import GameComponents.Board;

import java.util.ArrayList;

public class King extends Piece {
    private boolean[] lrAvailCastle = {false, false};
    private boolean[] lrDidCastle = {false, false};

    public King(Character color) {
        super(color, "/Users/glenda/Coding/learning/java/chess/src/Pieces/images/" + color + "k.png");
    }

    public ArrayList<int[]> getSquares(Board board, int currRow, int currCol) {
        Piece[][] boardArr = board.getBoardArr();

        int[][] directions = new int[][]{
                {1, 0},
                {1, 1},
                {1, -1},
                {-1, 0},
                {-1, 1},
                {-1, -1},
                {0, 0},
                {0, 1},
                {0, -1}
        };

        ArrayList<int[]> selectedSq = super.getSqFromFixed(directions, currRow, currCol, board);

        // Checking for castle
        boolean leftCastle = !this.isAlreadyMoved();
        boolean rightCastle = !this.isAlreadyMoved();

        if (boardArr[7][7] == null || boardArr[7][7].getColor() != this.getColor() || boardArr[7][7].isAlreadyMoved()) {
            rightCastle = false;
        }

        if (boardArr[7][0] == null || boardArr[7][0].getColor() != this.getColor() || boardArr[7][0].isAlreadyMoved()){
            leftCastle = false;
        }

        if (rightCastle || leftCastle) {
            for (int i = currCol + 1; i < 7; i++) {
                if (boardArr[7][i] != null) {
                    rightCastle = false;
                    break;
                }
            }

            for (int i = 1; i < currCol; i++) {
                if (boardArr[7][i] != null) {
                    leftCastle = false;
                    break;
                }
            }
        }

        if (leftCastle) {
            selectedSq.add(new int[]{7, 2});
        }
        if (rightCastle) {
            selectedSq.add(new int[]{7, 6});
        }

        lrAvailCastle = new boolean[] {leftCastle, rightCastle};

        return selectedSq;
    }

    public boolean[] getLrAvailCastle() {
        return lrAvailCastle;
    }

    public void setLrAvailCastle(boolean[] lrAvailCastle) {
        this.lrAvailCastle = lrAvailCastle;
    }

    public boolean[] getLrDidCastle() {
        return lrDidCastle;
    }

    public void setLrDidCastle(boolean[] lrDidCastle) {
        this.lrDidCastle = lrDidCastle;
    }
}