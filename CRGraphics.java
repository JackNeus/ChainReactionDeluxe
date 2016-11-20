// Controls graphics

import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;

public class CRGraphics extends JPanel {
    private Board board;

    private int cellSize;
    private int x0, x1, y0, y1;

    public CRGraphics(Board b) {
        super();
        this.board = b;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int rows = board.getRows(), cols = board.getCols();
        Dimension d = getSize();
        cellSize = (int)Math.min(d.getWidth() / (cols + 2), d.getHeight() / (rows + 2));
    
        x0 = cellSize;
        x1 = x0 + cols * cellSize;
        y0 = cellSize;
        y1 = y0 + rows * cellSize;

        drawGrid(g);

        for (int r = 0; r < board.getRows(); r++) {
            for (int c = 0; c < board.getCols(); c++) {
                Entity entity = board.getCell(r, c);
                entity.render(g, cellSize, (r + 1) * cellSize, (c + 1) * cellSize);
            }
        }
    }

    public void refresh() {
        repaint();
    }

    public CellCoords getCellIn(int x, int y) {
        int r = y / cellSize - 1;
        int c = x / cellSize - 1;
        CellCoords coords = new CellCoords(r, c);
        return coords;
    }

    private void drawGrid(Graphics g) {
        for (int r = 0; r < board.getRows() + 1; r++) {
            g.drawLine(x0, y0 + r * cellSize, x1, y0 + r * cellSize);
        }        
        for (int c = 0; c < board.getCols() + 1; c++) {
            g.drawLine(x0 + c * cellSize, y0, x0 + c * cellSize, y1);
        }
    }
}