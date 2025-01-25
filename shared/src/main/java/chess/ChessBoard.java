package chess;

import java.util.Arrays;
import java.util.Objects;

/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {
    private final ChessPiece[][] chessBoard = new ChessPiece[9][9];

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessBoard that = (ChessBoard) o;
        return Objects.deepEquals(chessBoard, that.chessBoard);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(chessBoard);
    }

    public ChessBoard() {
        
    }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece   the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {
        chessBoard[position.getRow()][position.getColumn()] = piece;
    }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {
        return chessBoard[position.getRow()][position.getColumn()];
    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        for (int col = 0; col <= 8; col++){
            for (int row = 0; row <= 8; row++) {
                chessBoard[row][col]  = null;
            }

            col = 1;
            while (col <= 8) {
                addPiece(new ChessPosition(2, col), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN));
                addPiece(new ChessPosition(7, col), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN));
                col ++;
            }

            //White pieces
            addPiece(new ChessPosition(1, 1), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK));
            addPiece(new ChessPosition(1, 2), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT));
            addPiece(new ChessPosition(1,3), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP));
            addPiece(new ChessPosition(1, 4), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.QUEEN));
            addPiece(new ChessPosition(1,5), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KING));
            addPiece(new ChessPosition(1,6), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP));
            addPiece(new ChessPosition(1, 7), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT));
            addPiece(new ChessPosition(1, 8), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK));

            //Black pieces
            addPiece(new ChessPosition(8, 1), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK));
            addPiece(new ChessPosition(8, 2), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT));
            addPiece(new ChessPosition(8,3), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP));
            addPiece(new ChessPosition(8, 4), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.QUEEN));
            addPiece(new ChessPosition(8,5), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KING));
            addPiece(new ChessPosition(8,6), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP));
            addPiece(new ChessPosition(8, 7), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT));
            addPiece(new ChessPosition(8, 8), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK));

        }
    }


    /**
     *
     * @param position the position you're trying to move to
     * @return true if it is not off the map
     */
    public boolean isInBounds(ChessPosition position) {
        if (position == null) {
            return false;
        }
        if (position.getRow() <= 0 || position.getRow() > 8 || position.getColumn() <= 0 || position.getColumn() > 8) {
            return false;
        } else {
            return true;
        }
    }

    /**
     *
     * @param position The position you are moving to
     * @param piece The piece you are moving
     * @return true if there is an enemy in position or it is empty
     */
    public boolean isSpaceOccupiedByEnemy(ChessPosition position, ChessPiece piece) {
        if (piece == null) {
            throw new RuntimeException("No moving piece found");
        }
        if (getPiece(position) == null) {
            return true;
        }
        ChessPiece destinationPiece = getPiece(position);
        return destinationPiece.getTeamColor() != piece.getTeamColor();
    }

    public boolean isPawnSpaceOccupiedByEnemy(ChessPosition position, ChessPiece piece) {
        if (piece == null) {
            throw new RuntimeException("No moving piece found");
        }
        if (getPiece(position) == null) {
            return false;
        }
        ChessPiece destinationPiece = getPiece(position);
        return destinationPiece.getTeamColor() != piece.getTeamColor();
    }

    /**
     *
     * @param position The position that you're checking if it is occupied
     * @return True if the space is occupied
     */
    public boolean isSpaceOccupied(ChessPosition position) {
        return getPiece(position) != null;
    }

    /**
     *
     * @param position position you're moving to
     * @param piece the piece you are attempting to move
     * @return returns true if the space is on the board or occupied by an enemy
     */
    public boolean validMove(ChessPosition position, ChessPiece piece) {
        return isInBounds(position) && isSpaceOccupiedByEnemy(position, piece);
    }

    /**
     *
     * @param position The Position you're moving to
     * @return ture if it is in bounds and not occupied by anyone
     */
    public boolean validMovePawn(ChessPosition position) {
        return isInBounds(position) && !isSpaceOccupied(position);
    }

    public boolean validAttackPawn(ChessPosition position, ChessPiece myPiece) {
        return isInBounds(position) && isPawnSpaceOccupiedByEnemy(position, myPiece);
    }
}