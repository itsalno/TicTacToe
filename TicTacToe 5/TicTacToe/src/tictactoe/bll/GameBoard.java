package tictactoe.bll;

public class GameBoard implements IGameModel {

    private char[][] board;
    private int currentPlayer;
    private int winner;
    private boolean gameOver;

    public GameBoard() {
        board = new char[3][3];
        currentPlayer = 0;
        winner = -1;
        gameOver = false;
        initializeBoard();
    }

    private void initializeBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = ' ';
            }
        }
    }

    @Override
    public int getNextPlayer() {
        return currentPlayer;
    }

    @Override
    public boolean play(int col, int row) {
        if (!gameOver && col >= 0 && col < 3 && row >= 0 && row < 3 && board[row][col] == ' ') {
            board[row][col] = (currentPlayer == 0) ? 'X' : 'O';
            if (checkWin(row, col)) {
                winner = currentPlayer;
                gameOver = true;
                currentPlayer=-1;
            } else if (isBoardFull()) {
                gameOver = true;
            } else {
                currentPlayer = 1 - currentPlayer; // to switch between players
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    @Override
    public int getWinner() {
        return winner;
    }

    @Override
    public void newGame() {
        initializeBoard();
        currentPlayer = 0;
        winner = -1;
        gameOver = false;
    }

    private boolean checkWin(int row, int col) {
        char symbol = board[row][col];

        // for the rows
        if (board[row][0] == symbol && board[row][1] == symbol && board[row][2] == symbol)
            return true;

        // for the columns
        if (board[0][col] == symbol && board[1][col] == symbol && board[2][col] == symbol)
            return true;

        // for the diagonals
        if (row == col && board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol)
            return true;
        if (row + col == 2 && board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol)
            return true;

        return false;
    }

    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}
