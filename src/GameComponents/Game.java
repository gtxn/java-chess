package GameComponents;

import Pieces.King;
import Pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Game implements MouseListener {
    public static int WallOfShameHeight = Board.DIMENSIONS * 3;
    private JFrame frame;
    private Board board;
    private Graphics g;
    private WallOfShame wallOfShame;
    private ChoosePiece piecePicker;
    private Character currentTurn = 'w';
    private ArrayList<int[]> coloredSq = new ArrayList<>();
    private String gameState = "ONGOING";
    private char winner;

    private ArrayList<Piece> whiteEaten = new ArrayList<>();
    private ArrayList<Piece> blackEaten = new ArrayList<>();

    public Game() {
        JFrame frame = new JFrame("Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.board = new Board();
        this.g = new Graphics(this);
        this.wallOfShame = new WallOfShame(this);
        this.piecePicker = new ChoosePiece(this);

        frame.setLayout(new BorderLayout());
        frame.add(g, BorderLayout.CENTER);
        frame.add(wallOfShame, BorderLayout.SOUTH);
        frame.add(piecePicker, BorderLayout.CENTER);
        frame.setSize(Board.BOARDWIDTH * Board.DIMENSIONS, (Board.BOARDWIDTH) * Board.DIMENSIONS + this.WallOfShameHeight);
        frame.setVisible(true);
    }

    public Board getBoard() {
        return this.board;
    }

    public Character getCurrentTurn() {
        return currentTurn;
    }

    public ArrayList<int[]> getColoredSq() {
        return coloredSq;
    }

    public void changeTurn() {
        this.currentTurn = (this.currentTurn == 'w') ? 'b' : 'w';
        this.board.flipBoard();
        this.coloredSq.clear();
    }

    private boolean isCoordInColSq(int row, int col) {
        for (int[] sq : this.coloredSq) {
            if (sq[0] == row && sq[1] == col) {
                return true;
            }
        }
        return false;
    }

    private void pawnChoosePiece(int row, int col) {
        this.piecePicker.setVisible(true);
        this.piecePicker.setPawnCoords(row, col);
        this.g.setVisible(false);
    }

    public void pawnChange(int row, int col, Piece piece) {
        this.piecePicker.setVisible(false);
        this.g.setVisible(true);
        board.changePawn(row, col, piece);
        this.changeTurn();
    }

    public String getGameState() {
        return gameState;
    }

    public char getWinner() {
        return winner;
    }

    public void setWinner(char winner) {
        this.winner = winner;
    }

    public void setGameState(String gameState) {
        this.gameState = gameState;
    }

    public ArrayList<Piece> getWhiteEaten() {
        return whiteEaten;
    }

    public void addWhiteEaten(Piece whitePiece) {
        whiteEaten.add(whitePiece);
    }

    public ArrayList<Piece> getBlackEaten() {
        return blackEaten;
    }

    public void addBlackEaten(Piece blackPiece) {
        blackEaten.add(blackPiece);
    }

    public WallOfShame getWallOfShame() {
        return wallOfShame;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int col = (int)(e.getX() / Board.DIMENSIONS);
        int row = (int)(e.getY() / Board.DIMENSIONS);
        Piece pieceClicked = this.board.getBoardArr()[row][col];

        // MOVE pieces
        if (this.isCoordInColSq(row, col)) {
            // Check for castle
            Piece currentPiece = board.getCurrentSelectedPiece();
            if (currentPiece.getClass().getSimpleName().equals("King")) {
                King kingPiece = (King) currentPiece;
                int oldCol = board.getCurrentSelectedPieceCoords()[1];

                if (oldCol - col == 2 && kingPiece.getLrAvailCastle()[0]) {
                    kingPiece.setLrDidCastle(new boolean[] {true, false});
                    board.movePiece(7, 3, 7, 0);
                }
                else if (col - oldCol == 2 && kingPiece.getLrAvailCastle()[1]) {
                    kingPiece.setLrDidCastle(new boolean[] {false, true});
                    board.movePiece(7, 5, 7, 7);
                }
            }

            // Move pieces normally
            board.movePiece(row, col, this);
            this.coloredSq.clear();

            // Check if pawn at the end
            if (board.getCurrentSelectedPiece().getClass().getSimpleName().equals("Pawn") && row == 0) {
                this.pawnChoosePiece(row, col);
            } else {
                // Handle turn end only if not changing pawn
                // If pawn change, change turn only AFTER the change has been done
                this.changeTurn();
            }

            board.setCurrentSelectedPiece(null);
            board.setCurrentSelectedPieceCoords(null);
        }

        // When a piece is being selected
        else if (pieceClicked != null) {
            this.coloredSq.clear();
            if (pieceClicked.getColor() == this.currentTurn) {
                board.setCurrentSelectedPiece(pieceClicked);
                board.setCurrentSelectedPieceCoords(new int[]{row, col});
                for (int[] coord : pieceClicked.getSquares(this.board, row, col)) {
                    this.coloredSq.add(coord);
                }
            }
        }

        g.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
