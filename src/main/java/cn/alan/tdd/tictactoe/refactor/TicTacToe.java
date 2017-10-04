package cn.alan.tdd.tictactoe.refactor;

import cn.alan.tdd.tictactoe.mongo.TicTacToeCollection;
import cn.alan.tdd.tictactoe.mongo.TicTacToeEntity;

import java.net.UnknownHostException;

public class TicTacToe {

    public static final String NO_WINNER = "No winner" ;

    private  Character[][] board =
            {{'\0','\0','\0'},{'\0','\0','\0'},{'\0','\0','\0'}};

    private final static int SIZE = 3;
    private char lastPlayer;
    private int turn;
    private TicTacToeCollection ticTacToeCollection;


    public TicTacToe() throws UnknownHostException {
        this(new TicTacToeCollection());
        this.lastPlayer = '\0';
        this.turn = 0;
    }

    protected TicTacToe(TicTacToeCollection collection) {
        ticTacToeCollection = collection;
        if (!ticTacToeCollection.drop()) {
            throw new RuntimeException("Dropping DB collection failed");
        }
    }

    public String play(int x, int y){
        checkAxis(x);
        checkAxis(y);
        lastPlayer = nextPlayer();
        TicTacToeEntity ticTacToeEntity = new TicTacToeEntity(turn++,x,y,lastPlayer);
        setBox(ticTacToeEntity);
        if(isWinner()){
            return lastPlayer + " is the winner";
        } else if( isDraw()){
            return "The result is draw";
        }
        return NO_WINNER;
    }

    /**
     * 填满棋盘认为是平局
     * @return
     */
    private boolean isDraw() {
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                if (board[i][j] == '\0'){
                    return false;
                }
            }
        }
        return  true;
    }

    private void checkAxis(int axis) {
        if(axis < 1 || axis > 3){
            throw new RuntimeException("location is outside board");
        }
    }



//    private void setBox(int x,int y,char player){
//        if(board[x-1][y-1] != '\0'){
//            throw new RuntimeException("Box is occcupied!");
//        } else {
//            board[x-1][y-1] = player;
//        }
//    }


    public TicTacToeCollection getTicTacToeCollection() {
        return ticTacToeCollection;
    }

    private void setBox(TicTacToeEntity bean) {
        if (board[bean.getX() - 1][bean.getY() - 1] != '\0') {
            throw new RuntimeException("Box is occupied");
        } else {
            board[bean.getX() - 1][bean.getY() - 1] = lastPlayer;
//            getTicTacToeCollection().saveMove(bean);
            if (!getTicTacToeCollection().saveMove(bean)) {
                throw new RuntimeException("Saving to DB failed");
            }
        }
    }

    public char nextPlayer() {
        if(lastPlayer == 'X'){
            return 'O';
        }
        return 'X';
    }

    public boolean isWinner(){
        int playerTotal = lastPlayer * SIZE;
        char diagonal1 = '\0';
        char diagonal2 = '\0';
        for (int i = 0; i < SIZE; i++) {
            diagonal1 += board[i][i];
            diagonal2 += board[i][SIZE - i - 1];
            if ((board[0][i] + board[1][i] + board[2][i]) == playerTotal) {
                return true;
            } else if ((board[i][0] + board[i][1] + board[i][2]) == playerTotal) {
                return true;
            }
        }
        if (diagonal1 == playerTotal || diagonal2 == playerTotal) {
            return true;
        }
        return false;
    }


}
