package ticTacToe;

import java.util.Arrays;
import java.util.Map;


public class MnkBoard implements Board, Position {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );

    private Cell[][] cells;
    private int freePositionCounter;
    private final int m, n, k;
    private Cell turn;

    public MnkBoard(int m, int n, int k) {
        this.cells = new Cell[n][m];
        this.m = m;
        this.n = n;
        this.k = k;
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        freePositionCounter = m * n;
        turn = Cell.X;
    }

    @Override
    public Position getPosition() {
        return this.clone();
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }

        cells[move.getRow()][move.getColumn()] = move.getValue();
        freePositionCounter--;

        int[][] directions = {{0, 1}, {1, 1}, {1, 0}, {1, -1}};

        for (int[] direction : directions) {
            int r, c, cnt = -1;

            r = move.getRow();
            c = move.getColumn();
            while (isValid(r, c) && cells[r][c] == turn) {
                r += direction[0];
                c += direction[1];
                cnt += 1;
            }

            r = move.getRow();
            c = move.getColumn();
            while (isValid(r, c) && cells[r][c] == turn) {
                r -= direction[0];
                c -= direction[1];
                cnt += 1;
            }

            if (cnt == k) {
                return Result.WIN;
            } else if (freePositionCounter == 0) {
                return Result.DRAW;
            }
        }
        turn = turn == Cell.X ? Cell.O : Cell.X;
        return Result.UNKNOWN;
    }

    private boolean isValid(int r, int c) {
        return 0 <= r && r < n
                && 0 <= c && c < m;
    }

    @Override
    public boolean isValid(final Move move) {
        return isValid(move.getRow(), move.getColumn())
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == getCell();
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("  ");
        for (int c = 0; c < m; c++) {
            sb.append(c);
        }
        for (int r = 0; r < n; r++) {
            sb.append("\n");
            sb.append(r).append(" ");
            for (int c = 0; c < m; c++) {
                sb.append(SYMBOLS.get(cells[r][c]));
            }
        }
        return sb.toString();
    }

    @Override
    public MnkBoard clone() {
        try {
            MnkBoard mnkBoard = (MnkBoard) super.clone();
            mnkBoard.cells = cells.clone();
            for (int i = 0; i < n; i++) {
                mnkBoard.cells[i] = mnkBoard.cells[i].clone();
            }
            return mnkBoard;
        }
        catch(CloneNotSupportedException e) {
            throw new InternalError();
        }
    }
}

