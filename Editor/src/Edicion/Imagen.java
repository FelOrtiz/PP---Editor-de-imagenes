/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Edicion;

import com.jhlabs.image.EmbossFilter;
import com.jhlabs.image.GaussianFilter;
import com.jhlabs.image.PinchFilter;
import com.jhlabs.image.SwimFilter;
import com.jhlabs.image.ThresholdFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import static javax.swing.SwingConstants.CENTER;

/**
 *
 * @author HoLeX
 */
public class Imagen extends JLabel
{
    private ImageIcon imagen;  
    private boolean tieneFiltro;
    
    public Imagen(ImageIcon imagen)
    {        
        this.imagen = imagen;
        this.tieneFiltro = false;
    }   

    public ImageIcon getImagen()
    {
        return imagen;
    }

    public void setImagen(ImageIcon imagen)
    {
        this.imagen = imagen;
        this.revalidate();
    }

    public boolean isTieneFiltro()
    {
        return tieneFiltro;
    }

    public void setTieneFiltro(boolean tieneFiltro)
    {
        this.tieneFiltro = tieneFiltro;
    }
    
    public ImageIcon grises(ImageIcon imagenAProcesar)
    {
        int r,g,b;
        BufferedImage ima = this.imageIconToBufferedImage(imagenAProcesar.getImage());
        BufferedImage aux = this.imageIconToBufferedImage(imagenAProcesar.getImage());
        for(int i=0;i<ima.getWidth(this);i++)
        {
            for(int j=0;j<ima.getHeight(this);j++)
            {
                Color color = new Color(ima.getRGB(i, j) );
                r = color.getRed();
                g = color.getGreen();
                b = color.getBlue();
                int media = (r+ g+ b)/3;
                aux.setRGB(i, j, new Color( media,media,media ).getRGB() );
            }
        }
        return this.toBufferedImageIcon(aux);
    }
    
    public ImageIcon sepia(ImageIcon imagenAProcesar)
    {
        int r,g,b;
        BufferedImage ima = this.imageIconToBufferedImage(imagenAProcesar.getImage());
        BufferedImage aux = this.imageIconToBufferedImage(imagenAProcesar.getImage());
        for(int i=0;i<ima.getWidth(this);i++)
        {
            for(int j=1;j<ima.getHeight(this)-1;j++)
            {
                Color color = new Color(ima.getRGB(i, j) );
                r = (int)( color.getRed()* 0.393 + color.getGreen() * 0.769 + color.getBlue()* 0.189);
                g = (int)( color.getRed()* 0.349 + color.getGreen() * 0.686 + color.getBlue()* 0.168);
                b = (int)( color.getRed()* 0.272 + color.getGreen() * 0.534 + color.getBlue()* 0.131);
                color = new Color((r>255)?255:r, (g>255)?255:g, (b>255)?255:b);
                aux.setRGB(i, j, color.getRGB() );
            }
        }
        return this.toBufferedImageIcon(aux);
    }
    
    public ImageIcon aplicaBinarizacion(int umbral, ImageIcon imagenAProcesar)
    {        
        BufferedImage imagenNueva = this.imageIconToBufferedImage(imagenAProcesar.getImage());
        BufferedImage imagenFinal = this.imageIconToBufferedImage(imagenAProcesar.getImage());
        
        Color[][] pix = new Color[imagenNueva.getHeight()][imagenNueva.getWidth()];

        for (int fila = 0; fila < imagenNueva.getHeight(); fila++)
        {
            for(int columna = 0; columna < imagenNueva.getWidth(); columna++)
            {
                int valorPixel = (imagenNueva.getRGB(columna, fila)*-1);
                
                if (valorPixel >= umbral)
                {
                    pix[fila][columna] = Color.WHITE;
                }
                else 
                {
                    pix[fila][columna] = Color.BLACK;
                }
                imagenFinal.setRGB(columna, fila, pix[fila][columna].getRGB());
            }
        }
        return this.toBufferedImageIcon(imagenFinal);
    }    
    
    public ImageIcon suavizado(ImageIcon imagenAProcesar)
    {
        BufferedImage imagen = this.imageIconToBufferedImage(imagenAProcesar.getImage());
        GaussianFilter filtro = new GaussianFilter();
        BufferedImage filter = filtro.filter(this.imageIconToBufferedImage(imagenAProcesar.getImage()), imagen);
        return this.toBufferedImageIcon(filter);
    }
    
    public ImageIcon binarizacion(int valor, ImageIcon imagenAProcesar)
    {
        BufferedImage imagen = this.imageIconToBufferedImage(imagenAProcesar.getImage());
        ThresholdFilter filtro = new ThresholdFilter();
        filtro.setLowerThreshold(valor);
        BufferedImage filter = filtro.filter(this.imageIconToBufferedImage(imagenAProcesar.getImage()), imagen);
        return this.toBufferedImageIcon(filter);
    }    
    
    public ImageIcon sobel(ImageIcon imagenAProcesar)
    {
        BufferedImage imagen = this.imageIconToBufferedImage(imagenAProcesar.getImage());
        EmbossFilter filtro = new EmbossFilter();
        filtro.setElevation((float) 7.5);
        filtro.setBumpHeight((float) 5.5); //que tan gris es
        BufferedImage filter = filtro.filter(this.imageIconToBufferedImage(imagenAProcesar.getImage()), imagen);
        return this.toBufferedImageIcon(filter);
    }

    public ImageIcon agua(ImageIcon imagenAProcesar)
    {
        BufferedImage imagen = this.imageIconToBufferedImage(imagenAProcesar.getImage());
        SwimFilter filtro = new SwimFilter();
        filtro.setAmount(3);
        filtro.setAngle(45);
        BufferedImage filter = filtro.filter(this.imageIconToBufferedImage(imagenAProcesar.getImage()), imagen);
        return this.toBufferedImageIcon(filter);        
    }
    
    public ImageIcon enroscar(ImageIcon imagenAProcesar)
    {
        BufferedImage imagen = this.imageIconToBufferedImage(imagenAProcesar.getImage());
        PinchFilter filtro =  new PinchFilter();
        filtro.setRadius(this.getWidth());
        filtro.setAngle((float) Math.toRadians(45));
        BufferedImage filter = filtro.filter(this.imageIconToBufferedImage(imagenAProcesar.getImage()), imagen);
        return this.toBufferedImageIcon(filter);          
    }
    
    public void giroHorizontal()
    {
       BufferedImage ima = this.imageIconToBufferedImage(this.imagen.getImage());
       int ancho = ima.getWidth();
       int alto = ima.getHeight();
       BufferedImage aux = new BufferedImage(ancho, alto, ima.getType());  
       Graphics2D g = aux.createGraphics();  
       g.drawImage(ima, 0, 0, ancho, alto, ancho, 0, 0, alto, null);  
       g.dispose();
       this.imagen = this.toBufferedImageIcon(aux);
    }
    
    public void giroVertical()
    {
       BufferedImage ima = this.imageIconToBufferedImage(this.imagen.getImage());
       int ancho = ima.getWidth();
       int alto = ima.getHeight();
       BufferedImage aux = new BufferedImage(ancho, alto, ima.getColorModel().getTransparency());    
       Graphics2D g = aux.createGraphics();  
       g.drawImage(ima, 0, 0, ancho, alto, 0, alto, ancho, 0, null);  
       g.dispose();
       this.imagen = this.toBufferedImageIcon(aux);
       repaint();
    }
    
    public void girarImagen(float angulo)
    {
        BufferedImage ima = this.imageIconToBufferedImage(this.imagen.getImage());
        int ancho = ima.getWidth();
        int alto = ima.getHeight();  
        BufferedImage aux = new BufferedImage( ancho, alto, ima.getType());  
        Graphics2D g = aux.createGraphics();  
        g.rotate(Math.toRadians(angulo), ancho/2, alto/2);  
        g.drawImage( ima, null, 0, 0);
        this.imagen = this.toBufferedImageIcon(aux);
        
    }
    
    public BufferedImage imageIconToBufferedImage(Image im) 
    {
        BufferedImage bi = new BufferedImage(im.getWidth(null),im.getHeight(null),BufferedImage.TYPE_INT_RGB);
        Graphics bg = bi.getGraphics();
        bg.drawImage(im, 0, 0, null);
        bg.dispose();
        return bi;
    }
    
    public ImageIcon toBufferedImageIcon(BufferedImage image) 
    {
        return new ImageIcon(image);
    }
    
    public ImageIcon EscalarImagen(boolean flag)
    {
        int alto = this.imagen.getIconHeight();
        int ancho = this.imagen.getIconWidth();
        
        Image image = null;
        
        if(ancho > alto)
        {
            int x = ((alto*100)/ancho);
            if(flag)
            {
                image = this.imagen.getImage().getScaledInstance(100, x, Image.SCALE_DEFAULT);
            }
            else
            {
                image = this.imagen.getImage().getScaledInstance(100, x, Image.SCALE_SMOOTH);
            }
        }
        else if(alto > ancho)
        {
            int x = ((ancho*100)/alto);
            if(flag)
            {
                image = this.imagen.getImage().getScaledInstance(x, 100, Image.SCALE_DEFAULT);  
            }
            else
            {
                image = this.imagen.getImage().getScaledInstance(x, 100, Image.SCALE_SMOOTH);
            }
        }
        else
        {
            if(flag)
            {
                image = this.imagen.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            }
            else
            {
                image = this.imagen.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            }
        }
        ImageIcon imagenEscalada = new ImageIcon(image);
        return imagenEscalada;
    }    
    
    public Imagen crearMiniatura(Imagen imagen)
    {
        int ancho = imagen.getImagen().getIconWidth();
        int alto = imagen.getImagen().getIconHeight();

        int porcentajeAncho = (int) Math.round((double)((double)100/((double)(ancho))*100));
        int porcentajeAlto = (int) Math.round((double)(((double)100/((double)(alto)))*100));    
        
        if(ancho < 100 && alto < 100)
        {
            return new Imagen(imagen.ZoomImagen(100));
        }
        else if(porcentajeAncho >= porcentajeAlto)
        {
            return new Imagen(imagen.ZoomImagen(porcentajeAlto));
        }
        else
        {
            return new Imagen(imagen.ZoomImagen(porcentajeAncho));
        }        
    }
    
    public ImageIcon EscalarImagenPrincipal(int anchoPanel, int altoPanel, Imagen imagen)//ancho, alto
    {
        Image image = null;
    
        int anchoImagen = imagen.getImagen().getIconWidth();
        int altoImagen = imagen.getImagen().getIconHeight();
        
        if(anchoPanel >= altoPanel && anchoImagen > anchoPanel)
        {
            double x = ((double)(anchoPanel-2)/(double)anchoImagen);
            int y = (int) (altoImagen*x);
            
            if(y > (altoPanel-2))
            {
                int nuevoAncho = (int) (((double)(altoPanel-2)*(double)(anchoPanel-2))/(double)y);                
                image = this.imagen.getImage().getScaledInstance(nuevoAncho, (altoPanel-2), Image.SCALE_AREA_AVERAGING);          
            }
            else
            {
                image = this.imagen.getImage().getScaledInstance((anchoPanel-2), y, Image.SCALE_AREA_AVERAGING);            
            }
        }
        else if(anchoPanel >= altoPanel && altoImagen > altoPanel)
        {
            double x = ((double)(altoPanel-2)/(double)altoImagen);
            int y = (int) (anchoImagen*x);
            
            if(y > (anchoPanel-2))
            {
                int nuevoAlto = (int) (((double)(anchoPanel-2)*(double)(altoPanel-2))/(double)y);                
                image = this.imagen.getImage().getScaledInstance((anchoPanel-2), nuevoAlto, Image.SCALE_AREA_AVERAGING);          
            }
            else
            {
                image = this.imagen.getImage().getScaledInstance(y, (altoPanel-2), Image.SCALE_AREA_AVERAGING);            
            }
        }
        else
        {
            image = this.imagen.getImage();
        }
        
        ImageIcon imagenEscalada = new ImageIcon(image);
        return imagenEscalada;
    }
    
    public ImageIcon ZoomImagen(int porcentaje)
    {
        int alto = this.imagen.getIconHeight();
        int ancho = this.imagen.getIconWidth();
        
        double porcentajeListo = (double) porcentaje/100;

        if(porcentaje < 100)
        {           
            return new ImageIcon(this.imagen.getImage().getScaledInstance((int)(ancho*porcentajeListo), (int)(alto*porcentajeListo), Image.SCALE_REPLICATE));
        }
        else if(porcentaje > 100)
        {
           return new ImageIcon(this.imagen.getImage().getScaledInstance((int)(ancho*porcentajeListo), (int)(alto*porcentajeListo), Image.SCALE_DEFAULT));
        }
        else
        {
            return this.imagen;
        }
    }
    
    @Override
    public void paint(Graphics g) 
    {
        if (imagen != null) 
        {
            this.setIcon(imagen);
            this.setHorizontalAlignment(CENTER);
            
            //g.drawImage(imagen.getImage(), 0, 0, getWidth(), getHeight(), this);
            //setOpaque(false);
        } 
        else 
        {
            setOpaque(true);
        }
        super.paintComponent(g);
    }    
}
