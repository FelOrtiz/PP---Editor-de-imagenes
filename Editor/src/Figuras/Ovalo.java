/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Figuras;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

/**
 *
 * @author Yerco
 */
public class Ovalo extends Rectangulo
{
    
    private Ellipse2D elipse;
    public Ovalo(int x, int y, int ancho, int alto, Color colorBorde, Color colorRelleno,
            int tamanhio){
        super(x, y, ancho, alto, colorBorde, colorRelleno, tamanhio);
        this.iniciar();
    }
   
    public void iniciar()
    {
        this.elipse = new Ellipse2D.Float( getX(), getY(),
                        getAncho(), getAlto());
    }
    
    @Override
    public void dibujar(Graphics g){
        // Objetos
        Graphics2D g2;
        Stroke bordeFigura;
        
        double radians = Math.toRadians(getRotacion());
        AffineTransform af = new AffineTransform();
        if(getRotacion() == 0)
        {        
            // Si el color del relleno es null significa que no tiene relleno
            if(getColorRelleno() != null){
            g.setColor(getColorRelleno());
            g.fillOval( getX(), getY(),
                        getAncho(), getAlto());
            }
            this.elipse = new Ellipse2D.Float( getX(), getY(),
                            getAncho(), getAlto());
            g2 = (Graphics2D)g;
            bordeFigura = new BasicStroke(getTamanio(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
            g2.setColor(getColorBorde());
            g2.setStroke(bordeFigura);
            g2.draw(this.elipse);
       
        }
        else if (getRotacion() > 0)
        {
            radians = Math.toRadians(getRotacion());
            af.rotate(radians, getX()+(int) Math.round( (double) ( (double)getAncho() / (double)2 ) ), getY()+(int) Math.round( (double) ( (double)getAlto() / (double)2 ) ));
            ((Graphics2D)g).setTransform(af);
            if(getColorRelleno() != null){
                g.setColor(getColorRelleno());
                g.fillOval( getX(), getY(),
                            getAncho(), getAlto());
            }
            this.elipse = new Ellipse2D.Float( getX(), getY(),
                            getAncho(), getAlto());
            g2 = (Graphics2D)g;
            bordeFigura = new BasicStroke(getTamanio(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
            g2.setColor(getColorBorde());
            g2.setStroke(bordeFigura);
            g2.draw(this.elipse);

            af.rotate(-radians, getX()+(int) Math.round( (double) ( (double)getAncho() / (double)2 ) ), getY()+(int) Math.round( (double) ( (double)getAlto() / (double)2 ) ));
            ((Graphics2D)g).setTransform(af);
        }
        
        
        
    }

    public Ellipse2D getElipse()
    {
        return elipse;
    }
    
    
}
