package chess;

import java.util.ArrayList;
import java.util.Collection;

public interface pieceMovesCalc {
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position);
}

public class KnightPieceMovesCalc implements pieceMovesCalc {

    public KnightPieceMovesCalc(ChessBoard board, ChessPosition myPosition) {

    }
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        Collection<ChessMove> pieceMoves = new ArrayList<>();

        int [][] possibilities = {
                {1, -2}, {2, -1}, {2, 1}, {1, 2},
                {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}

        };

        for int[] possible: possibilities:

        }

    }

}
