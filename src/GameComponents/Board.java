package GameComponents;

import Pieces.*;


public class Board {
    public static int BOARDWIDTH = 8;
    public static int DIMENSIONS = 50;

    private Piece currentSelectedPiece;
    private int[] currentSelectedPieceCoords; // row, col

    private Piece[][] boardArr = {
            {new Rook('b'), new Knight('b'), new Bishop('b'), new Queen('b'), new King('b'), new Bishop('b'), new Knight('b'), new Rook('b')},
            {new Pawn('b'), new Pawn('b'), new Pawn('b'), new Pawn('b'), new Pawn('b'), new Pawn('b'), new Pawn('b'), new Pawn('b')},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {new Pawn('w'), new Pawn('w'), new Pawn('w'), new Pawn('w'), new Pawn('w'), new Pawn('w'), new Pawn('w'), new Pawn('w')},
            {new Rook('w'), new Knight('w'), new Bishop('w'), new Queen('w'), new King('w'), new Bishop('w'), new Knight('w'), new Rook('w')},
    };

    public Board() {
    }

    // Move pieces normally
    public void movePiece(int newRow, int newCol, Game game) {
        Piece pieceMoved = this.boardArr[this.currentSelectedPieceCoords[0]][this.currentSelectedPieceCoords[1]];
        Piece pieceEaten = this.boardArr[newRow][newCol];
        if (pieceEaten != null) {
            if (pieceEaten.getClass().getSimpleName().equals("King")) {
                game.setWinner(game.getCurrentTurn());
                game.setGameState("WON");
            }
            if (pieceEaten.getColor() == 'w') {
                game.addWhiteEaten(pieceEaten);
            } else {
                game.addBlackEaten(pieceEaten);
            }

            game.getWallOfShame().repaint();
        }

        pieceMoved.setAlreadyMoved(true);

        this.boardArr[this.currentSelectedPieceCoords[0]][this.currentSelectedPieceCoords[1]] = null;
        this.boardArr[newRow][newCol] = this.currentSelectedPiece;
    }

    // Move piece manually for castling
    public void movePiece(int newRow, int newCol, int oldRow, int oldCol) {
        this.boardArr[newRow][newCol] = this.boardArr[oldRow][oldCol];
        this.boardArr[oldRow][oldCol] = null;

        this.boardArr[newRow][newCol].setAlreadyMoved(true);
    }

    public void flipBoard() {
        // Reverse board array when switching players
        for (int i=0; i < (int) (boardArr.length / 2); i++) {
            Piece[] tmp = boardArr[i];
            boardArr[i] = boardArr[boardArr.length - i - 1];
            boardArr[boardArr.length - i - 1] = tmp;
        }
    }

    // Change pawn to another piece
    public void changePawn(int currRow, int currCol, Piece piece) {
        this.boardArr[currRow][currCol] = piece;
    }

    public Piece getCurrentSelectedPiece() {
        return currentSelectedPiece;
    }

    public void setCurrentSelectedPiece(Piece currentSelectedPiece) {
        this.currentSelectedPiece = currentSelectedPiece;
    }

    public int[] getCurrentSelectedPieceCoords() {
        return currentSelectedPieceCoords;
    }

    public void setCurrentSelectedPieceCoords(int[] currentSelectedPieceCoords) {
        this.currentSelectedPieceCoords = currentSelectedPieceCoords;
    }

    public Piece[][] getBoardArr() {
        return boardArr.clone();
    }
}
