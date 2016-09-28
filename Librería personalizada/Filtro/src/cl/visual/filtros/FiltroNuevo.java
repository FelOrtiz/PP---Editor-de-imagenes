package cl.visual.filtros;

import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import cl.visual.Filtro;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HoLeX
 */
public class FiltroNuevo implements Filtro
{
    @Override
    public BufferedImage filtro(BufferedImage imagen)
    {
        int r,g,b;
        BufferedImage ima = imagen;
        BufferedImage aux = imagen;
        
        for(int i = 0; i < ima.getWidth() ;i++)
        {
            for(int j = 0;j < ima.getHeight() ;j++)
            {
                Color color = new Color( ima.getRGB(i, j) );
                r = color.getRed();
                g = color.getGreen();
                b = color.getBlue();
                aux.setRGB(i, j, new Color( 255-r,255-g,255-b ).getRGB() );
            }
        }
        return aux;
    }    

    @Override
    public String getName()
    {
        return "Negativo";
    }
}
