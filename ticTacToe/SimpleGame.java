package ticTacToe;

public class SimpleGame extends Game {
    private Player player1, player2;

    SimpleGame(boolean log, Player player1, Player player2) {
        super(log);
        this.player1 = player1;
        this.player2 = player2;
    }

    int play(Board board) {
        while (true) {
            final int result1 = move(board, player1, 1);
            if (result1 != -1) {
                return result1;
            }
            final int result2 = move(board, player2, 2);
            if (result2 != -1) {
                return result2;
            }
        }
    }
}
