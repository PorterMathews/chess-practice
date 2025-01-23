package chess;

/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {
    private final ChessPiece[][] chessBoard = new ChessPiece[9][9];

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
        throw new RuntimeException("Not implemented");
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
     * @return true if there is an enemy in position
     */
    public boolean isSpaceOccupiedByEnemy(ChessPosition position, ChessPiece piece) {
        if (piece == null) {
            throw new RuntimeException("No moving piece found");
        }
        if (getPiece(position) == null) {
            return false;
        }
        ChessPiece destinationPiece = getPiece(position);
        return destinationPiece.getTeamColor() != piece.getTeamColor();
    }

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
}