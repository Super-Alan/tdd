package cn.alan.tdd.tictactoe.mongo;

import cn.alan.tdd.tictactoe.refactor.TicTacToe;
import org.junit.Test;
import static org.junit.Assert.*;

import java.net.UnknownHostException;

public class TicTacToeInteg {
    @Test
    public void giveMongoDbIsRunningWhenPlayThenNoException()
             throws UnknownHostException{
        TicTacToe ticTacToe = new TicTacToe();
        assertEquals(TicTacToe.NO_WINNER, ticTacToe.play(1, 1));

    }
}
