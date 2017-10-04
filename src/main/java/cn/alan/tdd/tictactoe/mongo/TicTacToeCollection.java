package cn.alan.tdd.tictactoe.mongo;


import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.jongo.Jongo;
import org.jongo.MongoCollection;


public class TicTacToeCollection {
    private MongoCollection mongoCollection;

    public TicTacToeCollection() {
        DB db = new MongoClient().getDB("tic-tac-toe");
        mongoCollection = new Jongo(db).getCollection("game");

    }

    public MongoCollection getMongoCollection() {
        return mongoCollection;
    }

    public boolean saveMove(TicTacToeEntity entity) {
        try {
            getMongoCollection().save(entity);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    public boolean drop() {
        try {
            getMongoCollection().drop();
            return true;
        } catch (Exception ex){
            return false;
        }
    }
}
