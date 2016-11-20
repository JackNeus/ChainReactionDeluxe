import java.util.LinkedList;

public class Board {
    // Mechanics config
    private static int[] dx = new int[]{0, 0, -1, 1};
    private static int[] dy = new int[]{1, -1, 0, 0};

    private int rows;
    private int cols;

    private Entity[][] board;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        board = new Entity[rows][cols]; // Initialize board with all empty cells
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                board[r][c] = new PlayerBlob(capacity(r, c));
            }
        }
    }

    public Board(Board b) { // Constructor w deep copy
        this.rows = b.getRows();
        this.cols = b.getCols();

        board = new Entity[rows][cols];
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

    public Entity getCell(int r, int c) {
        if (!isValidCell(r, c)) return null;
        return board[r][c];
    }

    public boolean makeMove(int player, int row, int col) {
        if (!isValidCell(row, col)) return false;
        
        Entity site = board[row][col];

        // A different player has this square claimed already
        //if (site.isOccupied() && site.getPlayer() != player) return false; 
        
        if (!(site instanceof PlayerBlob)) return false; // Can't place here

        PlayerBlob blob = (PlayerBlob) site;

        if (blob.size() == 0) {
            blob.setPlayer(player);
        }

        blob.addBlob(); 
        if (blob.shouldExplode()) explode();

        return true;
    }

    public void explode() {
        boolean changeMade;
        do {
            changeMade = false;
            
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    if (board[r][c].shouldExplode()) {
                        int playerId = board[r][c].getPlayer();
                        board[r][c].explode();

                        changeMade = true;

                        NeighborRules rules = board[r][c].getNeighborRules();
                        for(int d = 0; d < rules.dx.length; d++) {
                            int nr = r + rules.dy[d], nc = c + rules.dx[d];
                            if (isValidCell(nr, nc)) {

                                board[nr][nc].setPlayer(playerId);
                                board[nr][nc].addBlob();
                            }
                        }
                    }
                }
            }
        } while (changeMade);
    }

    /*
    public Iterable<Entity> getNeighbors(int r, int c) {
        LinkedList<Entity> neighbors = new LinkedList<Entity>();
        for (int d = 0; d < dx.length; d++) {
            int nx = r + dx[d], ny = c + dy[d];
            if (isValidCell(nx, ny)) {
                if (board[nx][ny] == null) continue;
                neighbors.add(board[nx][ny]);
            }
        }
        return neighbors;
    }
    */
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
                System.out.print(((PlayerBlob)board[i][j]).size());
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {

    }
}