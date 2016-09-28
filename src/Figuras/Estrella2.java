/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Figuras;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 *
 * @author Yerco
 */
public class Estrella2 extends Circulo
{
    private StarPolygon estrella;
    public Estrella2( int x, int y, double radio, Color colorBorde, Color colorRelleno,
            int tamanio )
    {
        super( x, y, radio, colorBorde, colorRelleno, tamanio );
        this.estrella = new StarPolygon( x, y, (int)getRadio(), (int)Math.round((double)getRadio()/(double)2 ), 5, (double)60);
    }
    
    public StarPolygon getEstrella()
    {
        return estrella;
    }
    
    @Override
    public void dibujar( Graphics g )
    {
        Graphics2D g2d = (Graphics2D) g;
        Stroke bordeFigura;
        this.estrella = new StarPolygon( getX(), getY(), (int)getRadio(), (int)Math.round((double)getRadio()/(double)2 ), 5, (double)60);
    
        if(getRotacion() == 0)
        {        
            if(getColorRelleno()!=null)
            {
                g.setColor(getColorRelleno());
                g.fillPolygon(this.estrella);
            }

            if(getColorBorde()!=null)
            {
                g2d.setColor(getColorBorde());
                bordeFigura = new BasicStroke(getTamanio(),  BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
                g2d.setStroke(bordeFigura);
                g2d.draw(this.estrella);
            }
       
        }
        else if (getRotacion() > 0)
        {
            double radians = Math.toRadians(getRotacion());
            AffineTransform af = new AffineTransform();
            af.rotate(radians, getX(), getY());
            ((Graphics2D)g).setTransform(af);
            
            if(getColorRelleno()!=null)
            {
                g.setColor(getColorRelleno());
                g.fillPolygon(this.estrella);
            }

            if(getColorBorde()!=null)
            {
                g2d.setColor(getColorBorde());
                bordeFigura = new BasicStroke(getTamanio(),  BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
                g2d.setStroke(bordeFigura);
                g2d.draw(this.estrella);
            }
            
            af.rotate( -radians, getX(), getY() );
            ((Graphics2D)g).setTransform(af);
        }
    }
    
}
