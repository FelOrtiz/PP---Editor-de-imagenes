/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Figuras;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 *
 * @author Yerco
 */
public class SegmentoSprite extends Figura
{
    private int finX;
    private int finY;
    private ArrayList<PuntoSprite> puntos;
    
    public SegmentoSprite(int inicioX, int inicioY, int finX, int finY, Point2D inicioZona, Point2D finZona, Color colorBorde, Color colorRelleno,  int tamanio){
        super(inicioX, inicioY, colorBorde, colorRelleno, tamanio);
        this.finX = 0;
        this.finY = 0;
        this.puntos = new ArrayList<PuntoSprite>();
    }
    
    public void agregarPunto(PuntoSprite punto)
    {
        this.puntos.add(punto);
    }
    
    
    @Override
    public void dibujar(Graphics g)
    {
        
        for( PuntoSprite p: this.puntos )
        {
            p.dibujar(g);
        }
    }

    @Override
    public boolean estaDentro(int x, int y)
    {
        return false;
    }

    @Override
    public void setPosicion(int x, int y)
    {
    }

    public int getFinX()
    {
        return finX;
    }

    public void setFinX(int finX)
    {
        this.finX = finX;
    }

    public int getFinY()
    {
        return finY;
    }

    public void setFinY(int finY)
    {
        this.finY = finY;
    }
    
    public ArrayList<PuntoSprite> getPuntos()
    {
        return this.puntos;
    }
    
}
