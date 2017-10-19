package pkg11_actividad10;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

class Casella {

    private Rectangle2D.Float rec;
    private Color col;

    public Casella(Rectangle2D.Float r, Color c) { // una casella es una quadrat i un color
        this.rec = r;
        this.col = c;
    }

    public void paintComponent(Graphics g) { // pinta el color seleccionat per el programador
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.col);
        g2d.fill(this.rec);
    }

    public void setCol(Color col) { // posa una casella el color que el programador vol
        this.col = col;
    }


}
