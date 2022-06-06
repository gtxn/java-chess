package GameComponents;

import Pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class WallOfShame extends JPanel {
    private Game game;
    private final int whiteStartPoint = Board.DIMENSIONS / 2;
    private final int blackStartPoint = (Board.BOARDWIDTH + 1) * Board.DIMENSIONS / 2;
    private final int textWidth = 100;
    private final int textHeight = 20;
    private final int piecesWidth = (Board.DIMENSIONS / 2) * 6;
    private final int onePieceWidth = Board.DIMENSIONS / 2;

    WallOfShame(Game game) {
        this.game = game;

        JLabel whiteLabel = new JLabel();
        whiteLabel.setText("White");
        whiteLabel.setPreferredSize(new Dimension(this.piecesWidth + whiteStartPoint, textHeight));

        JLabel blackLabel = new JLabel();
        blackLabel.setText("Black");
        blackLabel.setPreferredSize(new Dimension(this.piecesWidth + whiteStartPoint, textHeight));

        this.add(whiteLabel, BorderLayout.WEST);
        this.add(blackLabel, BorderLayout.EAST);

        this.setVisible(true);
        this.setPreferredSize(new Dimension((Board.BOARDWIDTH) * Board.DIMENSIONS, 2  * Board.DIMENSIONS));

        repaint();
    }

    public void paintComponent(java.awt.Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        ArrayList<Piece> whiteEaten = game.getWhiteEaten();
        ArrayList<Piece> blackEaten = game.getBlackEaten();

        for (int i=0; i<whiteEaten.size(); i++) {
            g2d.drawImage(whiteEaten.get(i).getImage(),
                    piecesWidth + whiteStartPoint + (i * onePieceWidth) % (piecesWidth),
                    textHeight + ((i * onePieceWidth) / piecesWidth) * onePieceWidth,
                    onePieceWidth,
                    onePieceWidth,
                    null);
        }

        for (int i=0; i<blackEaten.size(); i++) {
            g2d.drawImage(blackEaten.get(i).getImage(),
                    (i * onePieceWidth) % (piecesWidth ),
                    textHeight + ((i * onePieceWidth ) / piecesWidth) * onePieceWidth,
                    onePieceWidth,
                    onePieceWidth,
                    null);
        }
    }
}
