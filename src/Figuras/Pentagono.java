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
public class Pentagono extends Rectangulo
{
    private Polygon poligono;
    public Pentagono(int x, int y, int ancho, int alto, Color colorBorde, Color colorRelleno,
            int tamanhio){
        super(x, y, ancho, alto, colorBorde, colorRelleno, tamanhio);
        this.iniciar();
    }
    
    public void iniciar()
    {
        int x = getX();
        int y = getY();
        int pedazoY = this.getAlto()/3;
        int pedazoX = this.getAncho()/5;
        int puntosX[] = { x+(int) Math.round( (double) ( (double)getAncho() / (double)2 ) ), x, x+pedazoX, x+(getAncho()-pedazoX), x+getAncho() };
        int puntosY[] = {y+0, y+pedazoY, y+getAlto(), y+getAlto(), y+pedazoY };
        
        this.poligono = new Polygon(puntosX, puntosY, 5);
    }
    
    @Override
    public void dibujar(Graphics g)
    {
        int x = getX();
        int y = getY();
        int pedazoY = this.getAlto()/3;
        int pedazoX = this.getAncho()/5;
        int puntosX[] = { x+(int) Math.round( (double) ( (double)getAncho() / (double)2 ) ), x, x+pedazoX, x+(getAncho()-pedazoX), x+getAncho() };
        int puntosY[] = {y+0, y+pedazoY, y+getAlto(), y+getAlto(), y+pedazoY };
        
        this.poligono = new Polygon(puntosX, puntosY, 5);
        
        Graphics2D g2d = (Graphics2D) g;
        Stroke bordeFigura;
        
        if(getRotacion() == 0)
        {        
            if(getColorRelleno()!=null)
            {
                g.setColor(getColorRelleno());
                g.fillPolygon(poligono);
            }
            if(getColorBorde()!=null)
            {
                g2d.setColor(getColorBorde());
                bordeFigura = new BasicStroke(getTamanio(),  BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
                g2d.setStroke(bordeFigura);
                g2d.draw(poligono);
            }
       
        }
        else if (getRotacion() > 0)
        {
            double radians = Math.toRadians(getRotacion());
            AffineTransform af = new AffineTransform();
            af.rotate(radians, getX()+(int) Math.round( (double) ( (double)getAncho() / (double)2 ) ), getY()+(int) Math.round( (double) ( (double)getAlto() / (double)2 ) ));
            ((Graphics2D)g).setTransform(af);
                
            if(getColorRelleno()!=null)
            {
                g.setColor(getColorRelleno());
                g.fillPolygon(poligono);
            }
            if(getColorBorde()!=null)
            {
                g2d.setColor(getColorBorde());
                bordeFigura = new BasicStroke(getTamanio(),  BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
                g2d.setStroke(bordeFigura);
                g2d.draw(poligono);
            }
            
            af.rotate(-radians, getX()+(int) Math.round( (double) ( (double)getAncho() / (double)2 ) ), getY()+(int) Math.round( (double) ( (double)getAlto() / (double)2 ) ));
            ((Graphics2D)g).setTransform(af);
        }  
        
        // dibujar estrella rellena
        
    }

    public Polygon getPoligono()
    {
        return poligono;
    }
    
    

}
