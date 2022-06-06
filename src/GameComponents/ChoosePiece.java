package GameComponents;

import Pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoosePiece extends JPanel implements ActionListener {
    private JComboBox comboBox;
    private JButton submitButton;
    private Game game;
    private int row;
    private int col;
    private Piece pieceChosen;

    public ChoosePiece(Game game) {
        this.game = game;
        comboBox = new JComboBox(new String[] {
                "Queen", "Rook", "Bishop", "Knight"
        });
        comboBox.setBounds(Board.DIMENSIONS * 2 , Board.DIMENSIONS * 4, (Board.BOARDWIDTH - 4) * Board.DIMENSIONS, Board.DIMENSIONS);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        submitButton.setBounds(Board.DIMENSIONS * 2, Board.DIMENSIONS * 5, (Board.BOARDWIDTH - 4) * Board.DIMENSIONS, Board.DIMENSIONS);

        JLabel label = new JLabel();
        label.setText("Choose piece to change");
        label.setVerticalTextPosition(JLabel.TOP);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setBounds(Board.DIMENSIONS * 2, Board.DIMENSIONS * 3, (Board.BOARDWIDTH - 4) * Board.DIMENSIONS, Board.DIMENSIONS);

        this.add(label);
        this.add(comboBox);
        this.add(submitButton);
        this.setLayout(null);
        this.setBackground(Color.white);
        this.setSize(Board.DIMENSIONS * 7, Board.DIMENSIONS * 3);
        this.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String item = (String) comboBox.getSelectedItem();
            switch (item) {
                case "Queen": {
                    this.pieceChosen = new Queen(this.game.getCurrentTurn());
                };
                break;
                case "Rook": {
                    this.pieceChosen = new Rook(this.game.getCurrentTurn());
                };
                break;
                case "Bishop": {
                    this.pieceChosen = new Bishop(this.game.getCurrentTurn());
                };
                break;
                case "Knight": {
                    this.pieceChosen = new Knight(this.game.getCurrentTurn());
                };
                break;
                default: {
                    this.pieceChosen = new Pawn(this.game.getCurrentTurn());
                };
                break;
            }

            game.pawnChange(this.row, this.col, pieceChosen);
        }
    }

    public void setPawnCoords(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
