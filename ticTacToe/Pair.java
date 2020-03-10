package ticTacToe;

public class Pair {
    private int first, second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object obj) {
        if (getClass() == obj.getClass()) {
            Pair tmp = (Pair) obj;
            return first == tmp.first && second == tmp.second;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.valueOf(first) + " " + String.valueOf(second) + " ";
    }
}
