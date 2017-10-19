package pkg11_actividad10;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

public class Panell extends JPanel {

    private static final int DIMENSIO = 50;
    private static final int MAXIM = 500;
    private static final int COSTAT = MAXIM / DIMENSIO;
    private Casella c[][];

    public Panell() { // declar el constructor de panell, es un conjunt de caselles
        c = new Casella[DIMENSIO][DIMENSIO];
        int y = 0;
        for (int i = 0; i < DIMENSIO; i++) {
            int x = 0;
            for (int j = 0; j < DIMENSIO; j++) {
                Rectangle2D.Float r = new Rectangle2D.Float(x, y, COSTAT, COSTAT);
                c[i][j] = new Casella(r, Color.white);
                x += COSTAT;
            }
            y += COSTAT;
        }
    }

    @Override
    public void paintComponent(Graphics g) { // pinta les caselles i la quadricula
        for (int i = 0; i < DIMENSIO; i++) {
            for (int j = 0; j < DIMENSIO; j++) {
                c[i][j].paintComponent(g);
            }
        }
        if (Pictograma.borde) {
            int y = 10;
            while (y <= 500) {
                g.setColor(Color.black);
                g.drawLine(0, y, 500, y);
                g.drawLine(y, 0, y, 500);
                y = y + 10;
            }
        }
    }

    @Override
    public Dimension getPreferredSize() { // aquest mètode serveix per obrir la finestra a aquesta dimensio
        return new Dimension(MAXIM, MAXIM);
    }

    public void posarColor(int x, int y, Color col) { // aquest mètode serveix per posar el color que vulgui dins una casella
        x = x / COSTAT;
        y = y / COSTAT;
        c[y][x].setCol(col);
    }

}
