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
                int row = myPosition.getRow() + direction[0] * steps;
                int col = myPosition.getColumn() + direction[1] * steps;

                ChessPosition possiblePosition = new ChessPosition(row, col);

                if (!board.isInBounds(possiblePosition)) {
                    break;
                }
                ;

                if (board.validMove(possiblePosition, myPiece)) {
                    pieceMoves.add(new ChessMove(myPosition, possiblePosition, null));
                }
                ;

                if (board.isSpaceOccupied(possiblePosition)) {
                    break;
                }
                ;
                steps++;
            }
            ;

        }
        ;
        return pieceMoves;
    }

    public Collection<ChessMove> straightMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> pieceMoves = new ArrayList<>();

        int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        ChessPiece myPiece = board.getPiece(myPosition);
        int steps = 1;

        for (int[] direction : directions) {
            steps = 1;
            while (true) {
                int row = myPosition.getRow() + direction[0] * steps;
                int col = myPosition.getColumn() + direction[1] * steps;

                ChessPosition possiblePosition = new ChessPosition(row, col);

                if (!board.isInBounds(possiblePosition)) {
                    break;
                }
                ;

                if (board.validMove(possiblePosition, myPiece)) {
                    pieceMoves.add(new ChessMove(myPosition, possiblePosition, null));
                }
                ;

                if (board.isSpaceOccupied(possiblePosition)) {
                    break;
                }
                ;
                steps++;
            }
            ;

        }
        ;
        return pieceMoves;

    }

    public Collection<ChessMove> PawnAttackCalc(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> pieceMoves = new ArrayList<>();
        int colorMult = 1;
        ChessPiece myPiece = board.getPiece(myPosition);
        if (myPiece.getTeamColor() == ChessGame.TeamColor.BLACK) {
            colorMult = -1;
        }

        ChessPiece.PieceType[] types = {
                ChessPiece.PieceType.QUEEN,
                ChessPiece.PieceType.BISHOP,
                ChessPiece.PieceType.ROOK,
                ChessPiece.PieceType.KNIGHT
        };

        int[][] directions = {
                {1, 1}, {1, -1}
        };

        for (int[] direction : directions) {
            int row = myPosition.getRow() + colorMult;
            int col = myPosition.getColumn() + direction[1];

            ChessPosition possibleAttack = new ChessPosition(row, col);

            if (board.validAttackPawn(possibleAttack, myPiece)) {
                if (possibleAttack.getRow() == 1 || possibleAttack.getRow() == 8) {
                    for (ChessPiece.PieceType type : types) {
                        pieceMoves.add(new ChessMove(myPosition, possibleAttack, type));
                    }
                } else {
                    pieceMoves.add(new ChessMove(myPosition, possibleAttack, null));
                }
            }
        }
        return pieceMoves;
    }

    public Collection<ChessMove> PawnForwardMove(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> pieceMoves = new ArrayList<>();
        int colorMult = 1;
        ChessPiece myPiece = board.getPiece(myPosition);
        if (myPiece.getTeamColor() == ChessGame.TeamColor.BLACK) {
            colorMult = -1;
        }
        ChessPosition march = new ChessPosition(
                myPosition.getRow() + colorMult,
                myPosition.getColumn()
        );

        ChessPosition fastMarch = new ChessPosition(
                myPosition.getRow() + 2 * colorMult,
                myPosition.getColumn()
        );

        ChessPiece.PieceType[] types = {
                ChessPiece.PieceType.QUEEN,
                ChessPiece.PieceType.BISHOP,
                ChessPiece.PieceType.ROOK,
                ChessPiece.PieceType.KNIGHT
        };

        if (board.validMovePawn(march)) {
            if (march.getRow() == 1 || march.getRow() == 8) {
                for (ChessPiece.PieceType type : types) {
                    pieceMoves.add(new ChessMove(myPosition, march, type));
                }
            } else {
                pieceMoves.add(new ChessMove(myPosition, march, null));
            }

            if (board.isInBounds(fastMarch) && ((colorMult == 1 && myPosition.getRow() == 2) ||
                        (colorMult == -1 && myPosition.getRow() == 7))) {
                    if (board.validMovePawn(fastMarch)) {
                        pieceMoves.add(new ChessMove(myPosition, fastMarch, null));
                    }
                }
            }
        return pieceMoves;
    }
}
