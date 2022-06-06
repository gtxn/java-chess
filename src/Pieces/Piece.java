package Pieces;

import GameComponents.Board;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class Piece {
    private Character color;
    private Image image;
    private boolean alreadyMoved = false;

    public Piece(Character color, String imagePath) {
        this.color = color;
        this.image = new ImageIcon(imagePath).getImage();
    }

    public Character getColor() {
        return color;
    }

    public Image getImage() {
        return image;
    }

    public boolean isAlreadyMoved() {
        return alreadyMoved;
    }

    public void setAlreadyMoved(boolean alreadyMoved) {
        this.alreadyMoved = alreadyMoved;
    }

    public ArrayList<int[]> getSquares(Board board, int currRow, int currCol) {
        return null;
    }

    protected ArrayList<int[]> getSqFromFixed(int[][] directions, int currRow, int currCol, Board board) {
        Piece[][] boardArr = board.getBoardArr();

        ArrayList<int[]> toReturn = new ArrayList<>();

        for (int[] direction : directions) {
            int[] coord = {currRow + direction[0], currCol + direction[1]};

            if ((coord[0] >= 0 && coord[1] >= 0 &&
                    coord[0] < Board.BOARDWIDTH && coord[1] < Board.BOARDWIDTH) &&
                    (boardArr[coord[0]][coord[1]] == null || boardArr[coord[0]][coord[1]].getColor() != this.color)){
                toReturn.add(coord);
            }

        }

        return toReturn;
    }

    protected ArrayList<int[]> getSqFromContinuousDirection(int[][] directions, int currRow, int currCol, Board board) {
        Piece[][] boardArr = board.getBoardArr();
        ArrayList<int[]> availSqs = new ArrayList<>();

        for (int[] direction : directions) {
            int newRow = currRow + direction[0];
            int newCol = currCol + direction[1];
            boolean stillInBoard = newRow < 8 && newRow >= 0 && newCol < 8 && newCol >= 0;

            while (stillInBoard && boardArr[newRow][newCol] == null) {
                int[] newCoord = {newRow, newCol};
                availSqs.add(newCoord);
                newRow += direction[0];
                newCol += direction[1];

                if (newRow >= 8 || newRow < 0 || newCol >= 8 || newCol < 0) {
                    stillInBoard = false;
                }
            }

            if (stillInBoard && boardArr[newRow][newCol] != null && boardArr[newRow][newCol].getColor() != this.color) {
                availSqs.add(new int[] {newRow, newCol});
            }
        }

        return availSqs;
    }
}
