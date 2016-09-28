/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Figuras;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Yerco
 */
public abstract class Figura
{
    //Coordenadas de Inicio
    private int x, y;
    //Color Del Borde
    private Color colorBorde;
    //Tamanio
    private int tamanio;
    private Color colorRelleno;    
    
    private double rotacion;
    
    
    public Figura(int x, int y, Color colorBorde, Color colorRelleno, int tamanio )
    {
        this.x = x;
        this.y = y;
        this.colorBorde = colorBorde;
        this.tamanio = tamanio;
        this.colorRelleno = colorRelleno; 
        this.rotacion = 0;
    }
    
    public void dibujar(Graphics g){}
    
    
    public abstract boolean estaDentro(int x, int y);
    
    public abstract void setPosicion(int x, int y);

    public Color getColorBorde()
    {
        return colorBorde;
    }

    public void setColorBorde(Color colorBorde)
    {
        this.colorBorde = colorBorde;
    }
    
    public void setInicio(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double getRotacion()
    {
        return rotacion;
    }

    public void setRotacion(double rotacion)
    {
        this.rotacion = rotacion;
    }

    
    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getTamanio()
    {
        return tamanio;
    }

    public void setTamanio(int tamanio)
    {
        this.tamanio = tamanio;
    }
    
    public Color getColorRelleno()
    {
        return colorRelleno;
    }

    public void setColorRelleno(Color colorRelleno)
    {
        this.colorRelleno = colorRelleno;
    }
    
}
