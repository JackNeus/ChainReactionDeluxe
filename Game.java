
import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

public class Game {
    private Board board;

    // Graphics objects
    private JFrame frame;
    private CRGraphics graphics;
    private int windowHeight = 400;
    private int windowWidth = 400;
    private int margin = 20;

    public Game() {
        board = new Board(this, 10, 8);
        prepareGUI();
        initEventListener();
        startGUI();
    }

    private void prepareGUI() {
        frame = new JFrame("Chain Reaction Deluxe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JPanel container = new JPanel();
        container.setPreferredSize(new Dimension(windowWidth, windowHeight));
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.CENTER;
        gridbag.setConstraints(container, constraints);
        container.setLayout(gridbag);

        graphics = new CRGraphics(board);
        graphics.setPreferredSize(new Dimension(windowWidth - margin * 2 + 1, windowHeight - margin * 2 + 1));
        //graphics.setBorder(BorderFactory.createLineBorder(Color.red));
        container.add(graphics);

        frame.getContentPane().add(container);
        frame.pack();
    }

    private void startGUI() {
        frame.setVisible(true);
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

    public void explosionOccured(Explosion e) {
        graphics.animateExplosion(e);
    }

    public static void main(String[] args) {
        Game game = new Game();
    }
}