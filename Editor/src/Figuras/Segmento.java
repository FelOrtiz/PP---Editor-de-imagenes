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
public class Segmento extends Figura
{
    private int xFin, yFin;
    private ArrayList<Linea> lineas;
    private Point2D coordenadasInicioZonaDibujo;
    private Point2D coordenadasFinalZonaDibujo;
    
    public Segmento(int inicioX, int inicioY, int finX, int finY, Point2D inicioZona, Point2D finZona, Color colorBorde, Color colorRelleno,  int tamanio){
        super(inicioX, inicioY, colorBorde, colorRelleno, tamanio);
        this.xFin = finX;
        this.yFin = finY;
        this.coordenadasInicioZonaDibujo = inicioZona;
        this.coordenadasFinalZonaDibujo = finZona;
        this.lineas = new ArrayList<Linea>();
    }
    
    @Override
    public void dibujar(Graphics g)
    {
        int finX = 0;
        int finY = 0;
        for(Linea l: this.lineas)
        {
            l.dibujar(g);
        }
    }
    
    public void agregarLinea(Linea l)
    {
        this.lineas.add(l);
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

    public int getxFin()
    {
        return xFin;
    }

    public void setxFin(int xFin)
    {
        this.xFin = xFin;
    }

    public int getyFin()
    {
        return yFin;
    }

    public void setyFin(int yFin)
    {
        this.yFin = yFin;
    }

    public ArrayList<Linea> getLineas()
    {
        return lineas;
    }

    public void setLineas(ArrayList<Linea> lineas)
    {
        this.lineas = lineas;
    }
 
    
}
