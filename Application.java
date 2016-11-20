
import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Application {
    private Game game;
    private Board board;

    // Graphics objects
    private JFrame frame;
    private CRGraphics graphics;

    public Application() {
        game = new Game(2);
        board = new Board(10, 8);
        prepareGUI();
        initEventListener();
        startGUI();
    }

    private void prepareGUI() {
        frame = new JFrame("Chain Reaction Deluxe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        graphics = new CRGraphics(board);
        graphics.setPreferredSize(new Dimension(400, 400));

        frame.getContentPane().add(graphics);
        frame.pack();
    }

    private void initEventListener() {
        graphics.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                CellCoords coords = graphics.getCellIn(x, y);
                if (!board.isValidCell(coords.row, coords.col)) return;

                board.makeMove(0, coords.row, coords.col);
                graphics.refresh();

            }
        });
    }

    private void startGUI() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Application app = new Application();
    }
}