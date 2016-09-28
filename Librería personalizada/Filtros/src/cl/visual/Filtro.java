/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.visual;

import java.awt.image.BufferedImage;

/**
 *
 * @author HoLeX
 */
public interface Filtro
{
    public BufferedImage filtro(BufferedImage imagen);
    
    public String getName();
}
