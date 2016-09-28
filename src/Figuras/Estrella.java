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
public class Estrella extends Rectangulo
{
    private Polygon poligono;   
    
    public Estrella(int x, int y, int ancho, int alto, Color colorBorde, Color colorRelleno,
            int tamanhio){
        super(x, y, ancho, alto, colorBorde, colorRelleno, tamanhio);
        this.iniciar();
    }
   
    public void iniciar()
    {
        /*int x = getX();
        int y = getY();
        int pedazoY = this.getAlto()/3;
        int pedazoY5 = this.getAlto()/5;
        int pedazoX = this.getAncho()/5;
        int pedazoX7 = this.getAncho()/7;
        
        int puntosX[] = { x+(getAncho()/2) , x+(pedazoX7*3), x, x+(pedazoX7*2), x+pedazoX, x+(getAncho()/2), x+(pedazoX*4), x+(pedazoX7*5), x+getAncho(), x+(pedazoX7*4)};
        int puntosY[] = {y+0, y+pedazoY, y+pedazoY, y+(pedazoY*2), y+ (getAlto() / 2 ), y+(pedazoY*2)+(pedazoY/2), y+getAlto(), y+(pedazoY*2), y+pedazoY, y+pedazoY};
        
        this.poligono = new Polygon(puntosX, puntosY, 10);*/
        
        int x = getX();
        int y = getY();
        int puntosX[] = {(int) Math.round((double)((double)x+((double)getAncho()/(double)2))), (int)Math.round((double)((double)x+( (double)getAncho()/(double)2 )+((double)(((double)getAlto()/(double)3)/(double)2)))), (int)Math.round((double)((double)x+((double)getAncho()*((double)7/(double)8)))), 
                        (int) Math.round((double)((double)x+( (double)getAncho()*((double)5/(double)8) ))), (int)Math.round((double)((double)x+( (double)getAncho()*((double)23/(double)32) ))), (int)Math.round((double)((double)x+( (double)getAncho()/(double)2 ))),
                        (int) Math.round((double)((double)x + ( (double)getAncho()*((double)7/(double)32) ))), (int)Math.round((double)((double)x+( (double)getAncho()*((double)3/(double)8) ))), (int)Math.round((double)((double)x+( (double)getAncho()/(double)8 ))),
                        (int) Math.round((double)((double)x+( (double)getAncho()/(double)2 )-((double)(((double)getAlto()/(double)3)/(double)2)))) };
        
        int puntosY[] = {y, (int) Math.round((double)((double)y+((double)((double)getAlto()/(double)3)))), (int) Math.round((double)((double)y+((double)((double)getAlto()/(double)3)))), (int)Math.round((double)((double)y+((double)( (double)getAlto()*((double)19/(double)36))))), 
                        y+getAlto(), (int)Math.round((double)((double)y+ ((double)( (double)getAlto()*((double)2/(double)3))))), y+getAlto(), (int)Math.round((double)((double)y+((double)( (double)getAlto()*((double)19/(double)36))))),(int) Math.round((double)((double)y+((double)((double)getAlto()/(double)3)))),(int) Math.round((double)((double)y+((double)((double)getAlto()/(double)3))))};
        
        this.poligono = new Polygon(puntosX, puntosY, 10);
        
        
    }
    
    @Override
    public void dibujar(Graphics g)
    {
        
        int x = getX();
        int y = getY();
        int puntosX[] = {(int) Math.round((double)((double)x+((double)getAncho()/(double)2))), (int)Math.round((double)((double)x+( (double)getAncho()/(double)2 )+((double)(((double)getAlto()/(double)3)/(double)2)))), (int)Math.round((double)((double)x+((double)getAncho()*((double)7/(double)8)))), 
                        (int) Math.round((double)((double)x+( (double)getAncho()*((double)5/(double)8) ))), (int)Math.round((double)((double)x+( (double)getAncho()*((double)23/(double)32) ))), (int)Math.round((double)((double)x+( (double)getAncho()/(double)2 ))),
                        (int) Math.round((double)((double)x + ( (double)getAncho()*((double)7/(double)32) ))), (int)Math.round((double)((double)x+( (double)getAncho()*((double)3/(double)8) ))), (int)Math.round((double)((double)x+( (double)getAncho()/(double)8 ))),
                        (int) Math.round((double)((double)x+( (double)getAncho()/(double)2 )-((double)(((double)getAlto()/(double)3)/(double)2)))) };
        
        int puntosY[] = {y, (int) Math.round((double)((double)y+((double)((double)getAlto()/(double)3)))), (int) Math.round((double)((double)y+((double)((double)getAlto()/(double)3)))), (int)Math.round((double)((double)y+((double)( (double)getAlto()*((double)19/(double)36))))), 
                        y+getAlto(), (int)Math.round((double)((double)y+ ((double)( (double)getAlto()*((double)2/(double)3))))), y+getAlto(), (int)Math.round((double)((double)y+((double)( (double)getAlto()*((double)19/(double)36))))),(int) Math.round((double)((double)y+((double)((double)getAlto()/(double)3)))),(int) Math.round((double)((double)y+((double)((double)getAlto()/(double)3))))};
        
        this.poligono = new Polygon(puntosX, puntosY, 10);
        
        
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
        
    }

    public Polygon getPoligono()
    {
        return poligono;
    }
    
    
}
