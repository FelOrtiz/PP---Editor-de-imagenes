/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Collage;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 *
 * @author HoLeX
 */
public class ImagenCollage
{
    private int coordenadaX;
    private int coordenadaY;
    private BufferedImage imagen;
    private BufferedImage imagenOriginal;
    private File file;
    private int width;
    private int height;
    private double rotacion;
    private boolean recortada;
    private int porcentajeRedimension;
    
    public ImagenCollage(BufferedImage imagen, int coordenadaX, int coordenadaY, int width, int height, double rotacion, File file, int porcentaje, BufferedImage imagenOriginal)
    {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.imagen = imagen;
        this.width = width;
        this.height = height;
        this.file = file;
        this.rotacion = 0;
        this.recortada = false;
        this.porcentajeRedimension = porcentaje;
        this.imagenOriginal = imagenOriginal;
    }

    public double getRotacion()
    {
        return rotacion;
    }

    public void setRotacion(double rotacion)
    {
        this.rotacion = rotacion;
    }
    
    public void setCoordenadaX(int coordenadaX)
    {
        this.coordenadaX = coordenadaX;
    }

    public void setCoordenadaY(int coordenadaY)
    {
        this.coordenadaY = coordenadaY;
    }

    public int getCoordenadaX()
    {
        return coordenadaX;
    }

    public int getCoordenadaY()
    {
        return coordenadaY;
    }
    
    public int getWidth()
    {
        return width;
    }

    public int getPorcentajeRedimension()
    {
        return porcentajeRedimension;
    }

    public void setPorcentajeRedimension(int porcentajeRedimension)
    {
        this.porcentajeRedimension = porcentajeRedimension;
    }

    public int getHeight()
    {
        return height;
    }

    public BufferedImage getImagen()
    {
        return imagen;
    }

    public File getFile()
    {
        return file;
    }

    public void setImagen(BufferedImage imagen)
    {
        this.imagen = imagen;
    }

    public void setFile(File file)
    {
        this.file = file;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public BufferedImage getImagenOriginal()
    {
        return imagenOriginal;
    }

    public void setImagenOriginal(BufferedImage imagenOriginal)
    {
        this.imagenOriginal = imagenOriginal;
    }

    public boolean isRecortada()
    {
        return recortada;
    }

    public void setRecortada(boolean recortada)
    {
        this.recortada = recortada;
    }
    
    public boolean estado(int x, int y)
    {
        
        if(x >= this.coordenadaX && x <= (this.coordenadaX+this.width) && y >= this.coordenadaY && y <= (this.coordenadaY+this.height))
        {    
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public void dibujar(Graphics g)
    { 
        if(rotacion == 0)
        {        
            g.drawImage(imagen, coordenadaX, coordenadaY, width, height, null);
        }
        else if (rotacion > 0)
        {
            double radians = Math.toRadians(this.rotacion);
            
            AffineTransform af = new AffineTransform();
            af.rotate(radians, coordenadaX+(width/2), coordenadaY+(height/2));
            ((Graphics2D)g).setTransform(af);
            g.drawImage(imagen, coordenadaX, coordenadaY, width, height, null);
            af.rotate(-radians, coordenadaX+(width/2), coordenadaY+(height/2));
            ((Graphics2D)g).setTransform(af);
        }        
    }
}
