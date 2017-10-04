package cn.alan.tdd.tictactoe.mongo;

import com.mongodb.MongoException;
import org.jongo.MongoCollection;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TicTacToeCollectionTests {

    private TicTacToeCollection collection;
    private MongoCollection mongoCollection;
    private   TicTacToeEntity entity;
    @Before
    public void setup(){
      collection = spy(new TicTacToeCollection());
      entity = new TicTacToeEntity(3,2,1,'X');
      mongoCollection = mock(MongoCollection.class);
    }

    @Test
    public void whenInstantiatedThenMongoHasDbNameTicTacToe(){
        assertEquals("tic-tac-toe",collection.getMongoCollection()
                                                        .getDBCollection().getDB().getName());
    }

    @Test
    public void whenInstantiatedThenMongoCollectionHasNameGame(){
        assertEquals("game",collection.getMongoCollection().getName());
    }

    @Test
    public void whenSaveMoveThenInvokeMongoCollectionSave(){

        doReturn(mongoCollection).when(collection).getMongoCollection();
        assertTrue(collection.saveMove(entity));
        verify(mongoCollection,times(1)).save(entity);
    }

    @Test
    public void givenExceptionWhenSaveMoveThenReturnFalse(){
        doThrow(new MongoException("save game play data"))
                .when(mongoCollection)
                .save(any(TicTacToeEntity.class));
        doReturn(mongoCollection).when(collection).getMongoCollection();
        assertFalse(collection.saveMove(entity));

    }

    @Test
    public void whenDropThenInvokeMongoCollectionDrop(){
        doReturn(mongoCollection).when(collection).getMongoCollection();
        collection.drop();
        verify(mongoCollection).drop();
    }

    @Test
    public void givenExceptionWhenDropThenReturnFalse(){
        doThrow(new MongoException("Drop failed!"))
                .when(mongoCollection)
                .drop();
        doReturn(mongoCollection)
                .when(collection)
                .getMongoCollection();
        assertFalse(collection.drop());
    }
}
