package chess;

import java.util.ArrayList;
import java.util.Collection;

public class MoveHelper {

    public MoveHelper(ChessBoard board, ChessPosition myPosition) {

    }

    public Collection<ChessMove> diagMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> pieceMoves = new ArrayList<>();

        int[][] directions = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        ChessPiece myPiece = board.getPiece(myPosition);
        int steps = 1;

        for (int[] direction : directions) {
            steps = 1;
            while (true) {
                int row = myPosition.getRow() + direction[0]*steps;
                int col = myPosition.getColumn() + direction[1]*steps;

                ChessPosition possiblePosition = new ChessPosition(row, col);

                if (!board.isInBounds(possiblePosition)) {
                    break;
                };

                if (board.validMove(possiblePosition, myPiece)) {
                    pieceMoves.add(new ChessMove(myPosition, possiblePosition, null));
                };

                if (board.isSpaceOccupied(possiblePosition)){
                        break;
                };
                steps++;
            };

        };
        return pieceMoves;
    }

    public Collection<ChessMove> straightMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> pieceMoves = new ArrayList<>();

        int[][] directions = {{0, 1}, {1, 0}, {-1,0}, {0, -1}};
        ChessPiece myPiece = board.getPiece(myPosition);
        int steps = 1;

        for (int[] direction : directions) {
            steps = 1;
            while (true) {
                int row = myPosition.getRow() + direction[0]*steps;
                int col = myPosition.getColumn() + direction[1]*steps;

                ChessPosition possiblePosition = new ChessPosition(row, col);

                if (!board.isInBounds(possiblePosition)) {
                    break;
                };

                if (board.validMove(possiblePosition, myPiece)) {
                    pieceMoves.add(new ChessMove(myPosition, possiblePosition, null));
                };

                if (board.isSpaceOccupied(possiblePosition)){
                    break;
                };
                steps++;
            };

        };
        return pieceMoves;

    }

}
