import java.util.LinkedList;

public class Board {
    // Mechanics config
    private static int[] dx = new int[]{0, 0, -1, 1};
    private static int[] dy = new int[]{1, -1, 0, 0};

    private int rows;
    private int cols;

    private Cell[][] board;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        board = new Cell[rows][cols]; // Initialize board with all empty cells
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                board[r][c] = new Cell(this, r, c, capacity(r, c));
            }
        }
    }

    public Board(Board b) { // Constructor w deep copy
        this.rows = b.getRows();
        this.cols = b.getCols();

        board = new Cell[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                board[r][c] = b.getCell(r, c);
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public Cell getCell(int r, int c) {
        if (!isValidCell(r, c)) return null;
        return board[r][c];
    }

    public boolean makeMove(int player, int row, int col) {
        if (!isValidCell(row, col)) return false;
        
        Cell site = board[row][col];

        // A different player has this square claimed already
        if (site.isOccupied() && site.getPlayer() != player) return false; 
        
        if (!site.isOccupied()) {
            site.initializePlayerBlob(player);
        }
        site.addBlob(); 
        return true;
    }

    public Iterable<Cell> getNeighbors(int r, int c) {
        LinkedList<Cell> neighbors = new LinkedList<Cell>();
        for (int d = 0; d < dx.length; d++) {
            int nx = r + dx[d], ny = c + dy[d];
            if (isValidCell(nx, ny)) {
                if (board[nx][ny] == null) continue;
                neighbors.add(board[nx][ny]);
            }
        }
        return neighbors;
    }

    public boolean isValidCell(int row, int col) {
        if (row < 0 || col < 0 || row >= rows || col >= cols) return false;
        return true;
    }

    private int capacity(int row, int col) { // Returns capacity of cell
        if ((row == 0 || row == rows - 1) && (col == 0 || col == cols - 1)) { // Corner
            return 2;
        }
        if (row == 0 || row == rows - 1 || col == 0 || col == cols - 1) { // Edge
            return 3;
        }
        return 4;
    }

    public void printBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(board[i][j].getSize());
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {

    }
}