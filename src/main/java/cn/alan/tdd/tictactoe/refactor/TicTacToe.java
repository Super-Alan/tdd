package cn.alan.tdd.tictactoe.refactor;

public class TicTacToe {

    private  Character[][] board =
            {{'\0','\0','\0'},{'\0','\0','\0'},{'\0','\0','\0'}};

    private final static int SIZE = 3;
    private char lastPlayer;

    public TicTacToe() {
        this.lastPlayer = '\0';
    }

    public String play(int x, int y){
        checkAxis(x);
        checkAxis(y);
        lastPlayer = nextPlayer();
        setBox(x,y,lastPlayer);
        return "No winner";
    }

    private void checkAxis(int axis) {
        if(axis < 1 || axis > 3){
            throw new RuntimeException("location is outside board");
        }
    }


    /**
     * set piece on board
     * @param x
     * @param y
     */
    private void setBox(int x,int y,char player){
        if(board[x-1][y-1] != '\0'){
            throw new RuntimeException("Box is occcupied!");
        } else {
            board[x-1][y-1] = player;
        }
    }

    public char nextPlayer() {
        if(lastPlayer == 'X'){
            return 'O';
        }
        return 'X';
    }

    private boolean isWinner(){
     
    }
}
