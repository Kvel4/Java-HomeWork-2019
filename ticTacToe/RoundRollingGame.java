package ticTacToe;

import java.util.ArrayList;
import java.util.List;

public class RoundRollingGame extends Game {
    private final Player[] players;
    private final Pair[][] gameTable;
    private final int n;

    RoundRollingGame(boolean log, Player[] players) {
        super(log);
        this.n = players.length;
        this.players = players;
        this.gameTable = createTable();
    }

    private Pair[][] createTable() {
        int fixedPosition = 1;
        Pair[][] gameTable = new Pair[n - 1][n / 2];
        List<Integer> loop = new ArrayList<>();

        for (int i = 2; i <= n; i++) {
            loop.add(i);
        }

        for (int i = 0; i < n - 1; i++) {
            gameTable[i][0] = new Pair(fixedPosition, loop.get(loop.size() - 1));
            for (int j = 0; j < (n - 1) / 2; j++) {
                gameTable[i][j + 1] = new Pair(loop.get(j), loop.get(loop.size() - 2 - j));
            }
            loop.add(0, loop.remove(loop.size() - 1));
        }
        return gameTable;
    }

    public Pair[][] getGameTable() {
        return createTable();
    }

    public int[] play(Board board) {
        Board boardClone = board.clone();
        int[] scoreTable = new int[n];
        for (Pair[] round : gameTable) {
            for (Pair meeting : round) {
                board = boardClone.clone();
                int firstPlayerIndex = meeting.getFirst() - 1, secondPlayerIndex = meeting.getSecond() - 1;
                int result = playMeeting(board, players[firstPlayerIndex], players[secondPlayerIndex]);
                if (result == 1) {
                    scoreTable[firstPlayerIndex] += 3;
                } else if (result == 2){
                    scoreTable[secondPlayerIndex] += 3;
                } else {
                    scoreTable[firstPlayerIndex] += 1;
                    scoreTable[secondPlayerIndex] += 1;
                }
            }
        }
        return scoreTable;
    }

    private int playMeeting(Board board, Player player1, Player player2) {
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
