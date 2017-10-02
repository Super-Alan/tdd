package cn.alan.tdd.tictactoe.refactor;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;

public class TIcTacToeRTests {

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

    @Test
    public void giveFirstTurnWhenNextPlayerTHenX(){
        assertEquals('X',ticTacToe.nextPlayer());
    }

    @Test
    public void giveLastTurnWasXWhenNextPlayThenO(){
        ticTacToe.play(1,1);
        assertEquals('O',ticTacToe.nextPlayer());
    }

    @Test
    public void whenPlayThenNoWinner(){
        String actual = ticTacToe.play(1,1);
        assertEquals("No winner",actual);
    }

    @Test
    public void whenPlayAndWholeHorizontalLineThenWinner(){
        ticTacToe.play(1,1);//x
        ticTacToe.play(1,2);//o
        ticTacToe.play(2,1);//x
        ticTacToe.play(2,2);//o
        String actual = ticTacToe.play(3,1);//x
        assertEquals("X is the winner",actual);
    }
}
