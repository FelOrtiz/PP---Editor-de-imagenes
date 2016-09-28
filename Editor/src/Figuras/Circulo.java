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
public class Circulo extends Figura
{

    Ellipse2D elipse;
    private double radio;
    private int radioProporcion;
    private int xProporcion, yProporcion;

    public Circulo(int x, int y, double radio, Color colorBorde, Color colorRelleno,
            int tamanio)
    {
        super(x, y, colorBorde, colorRelleno, tamanio);
        this.radio = radio;
        this.radioProporcion = 0;
        this.xProporcion = 0;
        this.yProporcion = 0;
        this.iniciar();
    }
    
    public void iniciar()
    {
        this.elipse = new Ellipse2D.Float(getX() - (int) radio, getY()
                - (int) radio, (int) radio * 2, (int) radio * 2);
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

    public int getRadioProporcion()
    {
        return radioProporcion;
    }

    public void setRadioProporcion(int radioProporcion)
    {
        this.radioProporcion = radioProporcion;
    }

    @Override
    public boolean estaDentro(int x, int y)
    {
        if (Math.sqrt(((getX() - x) * (getX() - x))
                + ((getY() - y) * (getY() - y))) < getRadio()) {
            return true;
        }
        return false;
    }

    @Override
    public void setPosicion(int x, int y)
    {
        super.setInicio(x, y);
    }

    public double getRadio()
    {
        return radio;
    }

    public void setRadio(double radio)
    {
        this.radio = radio;
    }

    @Override
    public void dibujar(Graphics g)
    {
        Graphics2D g2;
        Stroke bordeFigura;

        this.elipse = new Ellipse2D.Float(getX() - (int) radio, getY()
                - (int) radio, (int) radio * 2, (int) radio * 2);
        
        
        if (getColorRelleno() != null) {
            g.setColor(getColorRelleno());
            g.fillOval(getX() - (int) radio, getY()
                    - (int) radio, (int) radio * 2, (int) radio * 2);
        }
        this.elipse = new Ellipse2D.Float(getX() - (int) radio, getY()
                - (int) radio, (int) radio * 2, (int) radio * 2);
        g2 = (Graphics2D) g;
        bordeFigura = new BasicStroke(getTamanio(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
        g2.setColor(getColorBorde());
        g2.setStroke(bordeFigura);
        g2.draw(this.elipse);
    }

    public Ellipse2D getElipse()
    {
        return elipse;
    }
    
    
}
