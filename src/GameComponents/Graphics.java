package GameComponents;

import Pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Graphics extends JPanel {
    private Board board;
    private Game game;
    public Graphics(Game game) {
        this.board = game.getBoard();
        this.game = game;
        this.addMouseListener(game);

        this.setVisible(true);
        this.setBounds(0, 0, Board.BOARDWIDTH * Board.DIMENSIONS, Board.BOARDWIDTH  * Board.DIMENSIONS);
        this.setPreferredSize(new Dimension((Board.BOARDWIDTH) * Board.DIMENSIONS, (Board.BOARDWIDTH)  * Board.DIMENSIONS));
        repaint();
    }

    public void paintComponent(java.awt.Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        if (this.game.getGameState() == "WON") {
            g2d.setPaint(Color.white);
            g2d.fillRect(0, 0, Board.BOARDWIDTH * Board.DIMENSIONS, Board.BOARDWIDTH * Board.DIMENSIONS);
            g2d.setPaint(Color.black);
            g2d.drawString("Game over. Winner is " + ((game.getWinner() == 'w') ? "white." : "black."),
                    (Board.BOARDWIDTH * Board.DIMENSIONS) / 2 - 90, (Board.BOARDWIDTH * Board.DIMENSIONS) / 2);
        } else if (this.game.getGameState() == "ONGOING") {
            paintBoard(g2d);
            paintPieces(g2d, this.board);
        }
    }

    private void paintBoard(Graphics2D g2d) {
        // Paint checkered board
        for(int i=0; i<Board.BOARDWIDTH; i++) {
            for (int j=0; j<Board.BOARDWIDTH; j++) {
                g2d.setPaint(((i % 2 == 1 || j % 2 == 1) && !(i % 2 == 1 && j % 2 == 1)) ? Color.black : Color.white);
                g2d.fillRect(Board.DIMENSIONS * i, Board.DIMENSIONS * j, Board.DIMENSIONS, Board.DIMENSIONS);
            }
        }

        // Highlight available squares
        ArrayList<int[]> coloredSquares = this.game.getColoredSq();
        for (int[] coloredSq : coloredSquares) {
            g2d.setPaint(Color.yellow);
            g2d.fillRect(Board.DIMENSIONS * coloredSq[1], Board.DIMENSIONS * coloredSq[0], Board.DIMENSIONS, Board.DIMENSIONS);
            g2d.setPaint(Color.gray);
            g2d.drawRect(Board.DIMENSIONS * coloredSq[1], Board.DIMENSIONS * coloredSq[0], Board.DIMENSIONS, Board.DIMENSIONS);
        }

        // Highlight selected piece
        int[] coords = board.getCurrentSelectedPieceCoords();
        if (coords != null) {
            g2d.setPaint(Color.lightGray);
            g2d.fillRect(coords[1] * Board.DIMENSIONS, coords[0] * Board.DIMENSIONS, Board.DIMENSIONS, Board.DIMENSIONS);
            g2d.setPaint(Color.red);
            g2d.drawRect(coords[1] * Board.DIMENSIONS, coords[0] * Board.DIMENSIONS, Board.DIMENSIONS, Board.DIMENSIONS);
        }
    }

    private void paintPieces(Graphics2D g2d, Board board) {
        Piece[][] boardArr = board.getBoardArr();
        for(int i=0; i<Board.BOARDWIDTH; i++) {
            for (int j=0; j<Board.BOARDWIDTH; j++) {
                Piece piece = boardArr[i][j];
                if (piece != null) {
                    g2d.drawImage(piece.getImage(), j * Board.DIMENSIONS, i * Board.DIMENSIONS, Board.DIMENSIONS, Board.DIMENSIONS, null);
                }
            }
        }
    }
}
