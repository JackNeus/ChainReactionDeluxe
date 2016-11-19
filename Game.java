import java.util.Scanner;

public class Game {
    // Game settings
    private static final int rows = 10;
    private static final int cols = 10;

    private int numPlayers;
    private int turn;
    private Board board;

    private Scanner input;

    public Game(int numPlayers) {
        this.numPlayers = numPlayers;
        this.turn = 0; // Set turn to first player
    
        this.board = new Board(rows, cols);

        this.input = new Scanner(System.in);
    }

    public void playGame() {
        while (true) { // TODO: Implement win condition
            System.out.println("Player " + turn + "'s turn.");
            turn = (turn + 1) % numPlayers;

            int row = input.nextInt();
            int col = input.nextInt();
            board.makeMove(turn, row, col);
            board.printBoard();
        }
    }

    public static void main(String[] args) {
        Game game = new Game(2);
        game.playGame();
    }
}