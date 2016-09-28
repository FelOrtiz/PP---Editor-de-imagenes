/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
*/
package Figuras;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Yerco
 */
public class Rectangulo extends Figura
{
    private int ancho, alto;
    private int anchoProporcion, altoProporcion;
    private int xProporcion, yProporcion;
    private Rectangle2D rectangulo;
    
    
    public Rectangulo(int x, int y, int ancho, int alto, Color colorBorde, Color colorRelleno,
            int tamanio){
        super(x, y, colorBorde, colorRelleno, tamanio);
        this.ancho = ancho;
        this.alto = alto;
        this.anchoProporcion = 0;
        this.altoProporcion = 0;
        this.xProporcion = 0;
        this.yProporcion = 0;
        this.iniciarRectangulo();
    }
    
    public void iniciarRectangulo()
    {
        this.rectangulo = new Rectangle2D.Float( getX(), getY(),
                getAncho(), getAlto());
    }

    public int getxProporcion()
    {
        return xProporcion;
    }

    public void setxProporcion(int xProporcion)
    {
        this.xProporcion = xProporcion;
    }

    public int getyProporcion()
    {
        return yProporcion;
    }

    public void setyProporcion(int yProporcion)
    {
        this.yProporcion = yProporcion;
    }

    
    
    public int getAltoProporcion()
    {
        return altoProporcion;
    }

    public void setAltoProporcion(int altoProporcion)
    {
        this.altoProporcion = altoProporcion;
    }

    public int getAnchoProporcion()
    {
        return anchoProporcion;
    }

    public void setAnchoProporcion(int anchoProporcion)
    {
        this.anchoProporcion = anchoProporcion;
    }

    
    
    public int getAlto()
    {
        return alto;
    }

    public void setAlto(int alto)
    {
        this.alto = alto;
    }

    public int getAncho()
    {
        return ancho;
    }

    public void setAncho(int ancho)
    {
        this.ancho = ancho;
    }
    
    
    @Override
    public boolean estaDentro(int x, int y)
    {
        if((x > getX()) && (x < ( getX() + getAncho() ) ) &&
                (y > getY()) && (y < ( getY() + getAlto()))){
            return true;
        }
        return false;
    }

    @Override
    public void setPosicion(int x, int y)
    {
        super.setInicio(x, y);
    }
    
    @Override
     public void dibujar(Graphics g){
        // Objetos
        Graphics2D g2;
        Stroke bordeFigura;
        
        if(getRotacion() == 0)
        {        
            // Si el color del relleno es null significa que no tiene relleno
            if(getColorRelleno() != null){
                g.setColor(getColorRelleno());
                g.fillRect( getX(), getY(),
                        getAncho(), getAlto());
            }
            this.rectangulo = new Rectangle2D.Float( getX(), getY(),
                    getAncho(), getAlto());
            g2 = (Graphics2D)g;
            bordeFigura = new BasicStroke(getTamanio(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
            g2.setColor(getColorBorde());
            g2.setStroke(bordeFigura);
            g2.draw(this.rectangulo);

        }
        else if (getRotacion() > 0)
        {
            double radians = Math.toRadians(getRotacion());
            AffineTransform af = new AffineTransform();
            af.rotate(radians, getX()+(int) Math.round( (double) ( (double)getAncho() / (double)2 ) ), getY()+(int) Math.round( (double) ( (double)getAlto() / (double)2 ) ));
            ((Graphics2D)g).setTransform(af);
            
            if(getColorRelleno() != null){
                g.setColor(getColorRelleno());
                g.fillRect( getX(), getY(),
                        getAncho(), getAlto());
            }
            this.rectangulo = new Rectangle2D.Float( getX(), getY(),
                    getAncho(), getAlto());
            g2 = (Graphics2D)g;
            bordeFigura = new BasicStroke(getTamanio(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
            g2.setColor(getColorBorde());
            g2.setStroke(bordeFigura);
            g2.draw(this.rectangulo);
            
            af.rotate(-radians, getX()+(int) Math.round( (double) ( (double)getAncho() / (double)2 ) ), getY()+(int) Math.round( (double) ( (double)getAlto() / (double)2 ) ));
            ((Graphics2D)g).setTransform(af);
        }
        
    }

    public Rectangle2D getRectangulo()
    {
        return rectangulo;
    }
    
    
}
