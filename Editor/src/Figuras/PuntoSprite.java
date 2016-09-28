/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Figuras;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Yerco
 */
public class PuntoSprite  extends Circulo
{    
    private int cantidadDeCirculos;
    private ArrayList<Point2D> puntosCirculos;
    private ArrayList<Point2D> randomXY;
    private int radioPequeño;
    
    public PuntoSprite(  int x, int y, double radio, int cantidadDeCirculos, Color colorBorde, Color colorRelleno, int tamanio)
    {
        super( x, y,  radio, colorBorde, colorRelleno, tamanio );
        
        this.cantidadDeCirculos = cantidadDeCirculos;
        this.puntosCirculos = new ArrayList<Point2D>();
        this.randomXY = new ArrayList<Point2D>();
        setRadio( getRadio()+2 );
        this.iniciar();
    }
   
    @Override
    public void iniciar(  )
    {
        double brushRadius = getTamanio()*2;
        Random r = new Random();
        int cantidad = 0;
        this.radioPequeño = (int)Math.round((double)getRadio()/(double)8);
        while(cantidad<this.cantidadDeCirculos)
        {
            double rX = r.nextGaussian()*brushRadius ;
            double rY = r.nextGaussian()*brushRadius;
            
            int x = (int)Math.round(getX() + rX);
            int y =  (int)Math.round( getY() + rY );
            if(estaDentro(x, y) )
            {
                cantidad++;
                this.puntosCirculos.add(  new Point2D.Double(x, y) );
                this.randomXY.add(new Point2D.Double( rX, rY ));
            }
        } 
    }
   
    @Override
    public void dibujar(Graphics g)
    {
        g.setColor( this.getColorBorde() );
        Graphics2D g2 = (Graphics2D)g;
        for( Point2D c: this.puntosCirculos )
        {
            Ellipse2D e = new Ellipse2D.Double(c.getX() - (int) radioPequeño, c.getY()
                - (int) radioPequeño, (int) radioPequeño * 2, (int) radioPequeño * 2);
            g2.fill(e);
        }
    }
 
    public void configurarPuntos(  )
    {
        for(int i=0;i<this.randomXY.size();i++)
        {
            this.puntosCirculos.set( i, new Point2D.Double( getX()+this.randomXY.get(i).getX(), getY()+this.randomXY.get(i).getY() ) );
        }
    }
    
    /*
    private ArrayList<Point2D> puntos;
    
    public PuntoSprite(int x, int y, double radio, Color colorBorde, Color colorRelleno, int tamanio)
    {
        super(x, y, radio, colorBorde, colorRelleno, tamanio);
        
        this.puntos = new ArrayList<Point2D>();
        this.comenzar();
    }
    
    public void comenzar()
    {
        int cantidadPuntos = (int)Math.round((double)2*(double)getRadio());
        
        Random r = new Random();
        
        for (int i = 0; i < cantidadPuntos; i++)
        {
            int xPunto = getX()+r.nextInt((int) getRadio());
            int yPunto = getY()+r.nextInt((int) getRadio());
            
            this.puntos.add(new Point2D.Double(xPunto, yPunto));
        }
    }
  
   @Override
    public void dibujar(Graphics g)
    {
        
        g.setColor(this.getColorBorde());
        for(Point2D p: this.puntos)
        {
            g.fillOval( (int)p.getX(), (int)p.getY(), (int)getRadio()/8, (int)getRadio()/8);
        }
    } */   
}
