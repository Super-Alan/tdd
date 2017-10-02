package cn.alan.tdd.tictactoe;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TicTacToeTests {

    @Rule
    public ExpectedException expectedException =   ExpectedException.none();
    private TicTacToe ticTacToe;

    @Before
    public final void setup(){
        ticTacToe = new TicTacToe();
    }

    @Test
    public void whenXOutsideThenRuntimeException(){
        expectedException.expect(RuntimeException.class);
        ticTacToe.play(5,2);
    }

    @Test
    public void whenYOutsideThenRuntimeException(){
        expectedException.expect(RuntimeException.class);
        ticTacToe.play(3,5);
    }

    @Test
    public void whenOccupiedThenRuntimeException(){
        ticTacToe.play(2,1);
        expectedException.expect(RuntimeException.class);
        ticTacToe.play(2,1);
    }


}
