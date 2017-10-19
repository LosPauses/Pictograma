package pkg11_actividad10;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;   // import totes les classes necesàries
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Pictograma extends JFrame implements MouseListener { // implement un MouseListener

    private Panell pa;
    private javax.swing.JMenu JMenu;
    private javax.swing.JMenuBar JBarra;
    private javax.swing.JMenuItem JSortir;
    private javax.swing.JMenuItem JBorrar;
    private javax.swing.JMenuItem JGuardar;
    public static boolean borde = true; // variable per controlar la quadricula del panell
    BufferedImage bi;

    public Pictograma() { // declar es meu constructor
        setTitle("Pictograma");
        this.setDefaultCloseOperation(Pictograma.EXIT_ON_CLOSE);
        this.setResizable(false);
        initComponets();
    }

    private void initComponets() {
        pa = new Panell(); // declar el panell a la classe principal
        JMenu = new JMenu();
        JBarra = new JMenuBar();
        JSortir = new JMenuItem(); // declar totes les components de menu
        JBorrar = new JMenuItem();
        JGuardar = new JMenuItem();

        JSortir.setText("Tanca"); // segons el botó del menú que apreti fa una cosa: tanca la finestra
        JSortir.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                sortir(evt);
            }
        });

        JGuardar.setText("Guarda"); // segons el botó del menú que apreti fa una cosa: guarda el pictograma
        JGuardar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                guardar();
            }
        });

        JBorrar.setText("Borra"); // segons el botó del menú que apreti fa una cosa: borra el panell
        JBorrar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                borrar(evt);
            }
        });

        JMenu.setText("Opcions");

        JMenu.add(JGuardar);
        JMenu.add(JBorrar);
        JMenu.add(JSortir);

        JBarra.add(JMenu);

        setJMenuBar(JBarra); // creo el menú
        JBarra.setBounds(0, 0, 1000, 20);

        this.setLayout(new BorderLayout());
        pa.addMouseListener(this);
        this.getContentPane().add(pa, BorderLayout.CENTER);
        this.setSize(pa.getPreferredSize());
        this.pack();

    }

    public void sortir(ActionEvent evt) { // metode per sortir
        System.exit(0);
    }

    public void borrar(ActionEvent evt) { // metode per borrar
        for (int i = 0; i < 500; i++) {
            for (int j = 0; j < 500; j++) {
                pa.posarColor(i, j, Color.white);
                repaint();
            }
        }
    }

    public void guardar() { // metode per guardar les imatges
        borde = false;
        toBufferedImage(pa);
        borde = true;
        Image imagen = bi.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        imagetoBuffered(imagen);
        JFileChooser fileChooser = new JFileChooser();
        Component JPanel = pa;
        try {
        if (fileChooser.showSaveDialog(JPanel) == JFileChooser.APPROVE_OPTION) {
            File outputfile = new File(fileChooser.getSelectedFile() + ".jpg");
            ImageIO.write(bi, "jpeg", outputfile);
        }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

    }

    public static void main(String[] args) {
        Pictograma p = new Pictograma();
        p.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        if (me.getButton() == MouseEvent.BUTTON1) { // si s'apreta el botó esquerra es pinta negre 
            pa.posarColor(me.getX(), me.getY(), Color.black);
            repaint();
        }
        if (me.getButton() == MouseEvent.BUTTON3) { // si s'apreta el botó dret es pinta blanc
            pa.posarColor(me.getX(), me.getY(), Color.white);
            repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    public BufferedImage toBufferedImage(JPanel panell) { // metode per passar de JPanel a BufferedImage
        int w = panell.getWidth();
        int h = panell.getHeight();
        bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        panell.paint(g);
        return bi;
    }

    private BufferedImage imagetoBuffered(Image img) { // metode per passar de Image a BufferedImage
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }
        bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics2D bGr = bi.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        return bi;
    }

}
