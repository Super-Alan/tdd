package cn.alan.tdd.tictactoe.mongo;


import org.jongo.marshall.jackson.oid.MongoId;

public class TicTacToeEntity {
    @MongoId
    private int turn;
    private int x;
    private int y;
    private char player;

    public TicTacToeEntity(int turn, int x, int y, char player) {
        this.turn = turn;
        this.x = x;
        this.y = y;
        this.player = player;
    }

    public int getTurn() {
        return turn;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getPlayer() {
        return player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicTacToeEntity that = (TicTacToeEntity) o;

        if (turn != that.turn) return false;
        if (x != that.x) return false;
        if (y != that.y) return false;
        return player == that.player;
    }

    @Override
    public int hashCode() {
        int result = turn;
        result = 31 * result + x;
        result = 31 * result + y;
        result = 31 * result + (int) player;
        return result;
    }
}
