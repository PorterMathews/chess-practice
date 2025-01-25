package chess;

import java.util.ArrayList;
import java.util.Collection;

public interface pieceMovesCalc {
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position);
}

class KnightPieceMovesCalc implements pieceMovesCalc {

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> pieceMoves = new ArrayList<>();
        int[][] possibilities = {
                {1, -2}, {2, -1}, {2, 1}, {1, 2},
                {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}
        };
        ChessPiece myPiece = board.getPiece(myPosition);
        for (int[] possible: possibilities) {
            ChessPosition possiblePosition = new ChessPosition(
                    myPosition.getRow() + possible[0],
                    myPosition.getColumn() + possible[1]
            );
            if (board.validMove(possiblePosition, myPiece)) {
                pieceMoves.add(new ChessMove(myPosition, possiblePosition, null));
            };
        };
        return pieceMoves;
    }
}

class BishopMoveCalc extends MoveHelper implements pieceMovesCalc {

    public BishopMoveCalc(ChessBoard board, ChessPosition myPosition) {
        super(board, myPosition);
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> pieceMoves = new ArrayList<>();
        pieceMoves.addAll(diagMoves(board, myPosition));
        return pieceMoves;
    }
}

class RookMoveCalc extends MoveHelper implements pieceMovesCalc {

    public RookMoveCalc(ChessBoard board, ChessPosition myPosition) {
        super(board, myPosition);
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> pieceMoves = new ArrayList<>();
        pieceMoves.addAll(straightMoves(board, myPosition));
        return pieceMoves;
    }
}

class QueenMoveCalc extends MoveHelper implements pieceMovesCalc {

    public QueenMoveCalc(ChessBoard board, ChessPosition myPosition) {
        super(board, myPosition);
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> pieceMoves = new ArrayList<>();
        pieceMoves.addAll(straightMoves(board, myPosition));
        pieceMoves.addAll(diagMoves(board, myPosition));
        return pieceMoves;
    }
}

class KingMoveCalc extends MoveHelper implements pieceMovesCalc {

    public KingMoveCalc(ChessBoard board, ChessPosition myPosition) {
        super(board, myPosition);
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> pieceMoves = new ArrayList<>();
        int[][] possibilities = {
                {1,0}, {1,1}, {0,1}, {-1,1}, {-1,0}, {-1,-1}, {0, -1}, {1,-1}
        };
        ChessPiece myPiece = board.getPiece(myPosition);
        for (int[] possible: possibilities) {
            ChessPosition possiblePosition = new ChessPosition(
                    myPosition.getRow() + possible[0],
                    myPosition.getColumn() + possible[1]
            );
            if (board.validMove(possiblePosition, myPiece)) {
                pieceMoves.add(new ChessMove(myPosition, possiblePosition, null));
            };
        };
        return pieceMoves;
    }
}

class PawnMoveCalc extends MoveHelper implements pieceMovesCalc {

    public PawnMoveCalc(ChessBoard board, ChessPosition myPosition) {
        super(board, myPosition);
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> pieceMoves = new ArrayList<>();
            pieceMoves.addAll(PawnAttackCalc(board, myPosition));
            pieceMoves.addAll(PawnForwardMove(board, myPosition));
            return pieceMoves;
    }
}
