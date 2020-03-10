package ticTacToe;


import java.util.InputMismatchException;
import java.util.Scanner;


public class MainModification {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Scanner stringScanner;
        int c;
        while (true) {
            System.out.println("Enter number of players");
            stringScanner = new Scanner(in.nextLine());
            try {
                c = stringScanner.nextInt();
                if (c <= 0 || c % 2 != 0){
                    System.out.println("Number of players must be even and >= 2");
                    continue;
                }
                break;
            } catch (InputMismatchException ignored) {
                System.out.println("Number must be Integer");
            }
        }
        int m, n, k;
        while (true) {
            System.out.println("Enter yours board parameters: m, n, k");
            stringScanner = new Scanner(in.nextLine());
            try {
                m = stringScanner.nextInt();
                n = stringScanner.nextInt();
                k = stringScanner.nextInt();
                if (m <= 0 || n <= 0 || k <= 0) {
                    System.out.println("Arguments must be >= 1");
                    continue;
                }
                if (k > Math.max(m, n)) {
                    System.out.println("Result always will be DRAW");
                    continue;
                }
                break;
            } catch (InputMismatchException ignored) {
                System.out.println("Parameters must be Integers");
            }
        }

        Player[] players = new Player[c];
        for (int i = 0; i < c; i++) {
            players[i] = new RandomPlayer(m, n);
        }

        RoundRollingGame game = new RoundRollingGame(false, players);

        int[] result = game.play(new MnkBoard(m, n, k));

        Pair[][] gameTable = game.getGameTable();
        for (Pair[] round : gameTable) {
            for (Pair meeting : round) {
                System.out.print(meeting.toString());
            }
            System.out.println();
        }
        System.out.println();

        for (int i = 0; i < c; i++) {
            System.out.println("Player number " + (i+1) + " gets " + result[i] + " point");
        }
    }
}
