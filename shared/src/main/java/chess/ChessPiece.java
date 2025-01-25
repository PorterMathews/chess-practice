package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {
    private final ChessGame.TeamColor pieceColor;
    private final ChessPiece.PieceType pieceType;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessPiece that = (ChessPiece) o;
        return pieceColor == that.pieceColor && pieceType == that.pieceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceColor, pieceType);
    }

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.pieceColor = pieceColor;
        this.pieceType = type;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return pieceColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return pieceType;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> pieceMoves = new ArrayList<>();

        switch (pieceType) {
            case KING:

            case QUEEN:
                QueenMoveCalc queenMoveCalc = new QueenMoveCalc(board,myPosition);
                return queenMoveCalc.pieceMoves(board, myPosition);

            case BISHOP:
                BishopMoveCalc bishopMoveCalc = new BishopMoveCalc(board, myPosition);
                return bishopMoveCalc.pieceMoves(board, myPosition);

            case KNIGHT:
                KnightPieceMovesCalc knightPieceMovesCalc = new KnightPieceMovesCalc();
                return knightPieceMovesCalc.pieceMoves(board, myPosition);

            case ROOK:
                RookMoveCalc rookMoveCalc = new RookMoveCalc(board, myPosition);
                return rookMoveCalc.pieceMoves(board, myPosition);

            case PAWN:

            default:
                throw new RuntimeException("Not a valid piece type");

        }

    }
}
