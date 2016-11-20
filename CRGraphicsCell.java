import java.awt.*;
import javax.swing.JPanel;

public class CRGraphicsCell extends JPanel {
    private Cell cell;

    public CRGraphicsCell(Cell c) {
        super();
        this.cell = c;
    }

    public void paintComponent(Graphics g) {
        int cellSize = 0;
        cell.render(g, cellSize);
    }
}