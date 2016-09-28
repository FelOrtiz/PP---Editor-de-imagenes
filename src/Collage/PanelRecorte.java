/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Collage;

import Figuras.Rectangulo;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author HoLeX
 */
public class PanelRecorte extends JPanel implements MouseListener, MouseMotionListener
{
    private Image img;
    private BufferedImage imagenMemoria;
    private BufferedImage imagenRecortada;
    
    private JLabel label;

    private Graphics2D g2D;

    private float inicioX, inicioY, finX, finY;
    
    private float x = 0;
    private float y = 0;
    private float ancho = 0;
    private float alto = 0;

    public PanelRecorte(BufferedImage imagenNormal)
    {
        this.setPreferredSize(new Dimension(imagenNormal.getWidth(), imagenNormal.getHeight()));
        this.img = imagenNormal;
        this.inicioX = 0;
        this.inicioY = 0;
        this.finX = 0;
        this.finY = 0;
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) 
    {
        Graphics2D g2 = (Graphics2D)g;
        if(img != null)
        {
            imagenMemoria = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
            g2D = imagenMemoria.createGraphics();
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2D.drawImage(img, 0, 0, img.getWidth(this), img.getHeight(this), this);
            g2D.setStroke(new BasicStroke(2f));
            g2D.setColor(Color.CYAN);
           
            this.ancho = Math.abs(this.inicioX - this.finX);
            this.alto = Math.abs(this.inicioY - this.finY) ;
            this.x = Math.min( this.inicioX, this.finX );
            this.y = Math.min(this.inicioY, this.finY);
            Rectangle2D r2 = new Rectangle2D.Float( ((int)Math.min( this.inicioX, this.finX )),
                            (int)(Math.min(this.inicioY, this.finY)),
                            this.ancho,
                             this.alto);
            g2D.draw(r2);
            g2.drawImage(imagenMemoria, 0, 0, this);
        }
    }
    
    public void guardarRecorte()
    {
        if(x == 0 && y == 0 && ancho == 0 && alto == 0)
        {
            JOptionPane.showMessageDialog(null, "No ha seleccionado el área a recortar", "Atencion", JOptionPane.WARNING_MESSAGE);
        }
        else if(ancho < 20 || alto < 20)
        {
            JOptionPane.showMessageDialog(null, "El recorte no puede ser tan pequeño, intente nuevamente ", "Atencion", JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            imagenRecortada = ((BufferedImage)img).getSubimage((int)x,(int) y,(int) ancho,(int) alto);
        }
    }

    public BufferedImage getImagenRecortada()
    {
        return imagenRecortada;
    }
    
    @Override
    public void mouseDragged(MouseEvent e) 
    {
        ancho = e.getX()-x;
        alto = e.getY()-y;
        
        if(ancho < 0)
        {
            ancho = 0;
        }
        if(alto < 0) 
        {
            alto = 0;
        }
        if(x > this.getWidth())
        {
            x = this.getWidth() - ancho ;
        }
        if(y > this.getHeight())
        {
            y = this.getHeight() - alto ;
        } 
        
        this.finX = e.getX();
        this.finY = e.getY();
        this.repaint();
    }
    
    @Override
    public void mouseMoved(MouseEvent e) 
    {
    }
    
    @Override
    public void mouseClicked(MouseEvent e) 
    {
    }
    
    @Override
    public void mousePressed(MouseEvent e) 
    {
        x = e.getX();
        y = e.getY();
        this.inicioX = e.getX();
        this.inicioY = e.getY();
        
    }
    
    @Override
    public void mouseReleased(MouseEvent e) 
    {
    }
    
    
    @Override
    public void mouseEntered(MouseEvent e) 
    {
    }
    
    @Override
    public void mouseExited(MouseEvent e) 
    {
    }
}    
