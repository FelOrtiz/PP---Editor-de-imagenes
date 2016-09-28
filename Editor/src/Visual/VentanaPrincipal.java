/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Visual;

import Administracion.PanelAdministrar;
import Collage.PanelCollage;
import Edicion.Imagen;
import Edicion.PanelEdicion;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author HoLeX
 */
public class VentanaPrincipal extends JFrame implements WindowStateListener
{
 
    private BarraSuperior barra;

    private JPanel panelPrincipal;
    
    private PanelAdministrar panelAdministrar;
    private PanelEdicion panelEdicion;
    private PanelCollage panelCollage;

    private String ruta;
    
    public VentanaPrincipal()
    {
        
        //---------------------- Detalles de la ventana ------------------------
        super("Visual - Gestor de imágenes");
        super.setIconImage (Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/Icono.png")));
        this.setSize(new Dimension(800,600));   
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(1360,700));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //-------------------------- Barra Principal ---------------------------
        this.barra = new BarraSuperior(this);       
        this.setJMenuBar(barra);
        //----------------------------------------------------------------------
        //----------------------- Paneles Principales -------------------------- 
        this.panelPrincipal = new JPanel(new BorderLayout());
        this.add(panelPrincipal, BorderLayout.CENTER);
        
        this.panelAdministrar = new PanelAdministrar(this);
        this.panelPrincipal.add(panelAdministrar);
        //----------------------------------------------------------------------
        super.addWindowStateListener(this);
        
    }

    public String getRuta()
    {
        return ruta;
    }

    public void setRuta(String ruta)
    {
        this.ruta = ruta;
    }
    
    public BarraSuperior getBarra()
    {
        return this.barra;
    }
    
    public void repintarPanelEdicion(boolean isGIF)
    {
        this.panelPrincipal.removeAll();
        this.panelPrincipal.revalidate();
        this.panelEdicion = new PanelEdicion(this);
        
        if(isGIF) //Si la imagen es un gif, estonces deshabilitamos la edicion (figuras, lineas, etc)
        {
            this.panelEdicion.activarEdicion(false);
        }
        else
        {
            this.panelEdicion.activarEdicion(true);
        }
        
        this.panelPrincipal.add(panelEdicion, BorderLayout.CENTER);
        
        if(ruta.endsWith("bmp") || ruta.endsWith("BMP")) //Si la imagen es BMP la lee de manera distinta
        {
            BufferedImage imagen = null;
            try 
            {
                imagen = ImageIO.read(new File(ruta));
            }
            catch (IOException ex) 
            {
                
            }
            this.panelEdicion.setImagen(new Imagen(new ImageIcon(imagen)), ruta);
        }
        else
        {
            this.panelEdicion.setImagen(new Imagen(new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getRuta()))), ruta);
        }
    }

    public void repintarPanelAdministrar()
    {
        this.panelPrincipal.removeAll();
        this.panelPrincipal.revalidate();
        this.panelAdministrar = new PanelAdministrar(this);
        this.panelPrincipal.add(panelAdministrar);
        this.panelAdministrar.setImagenPrincipal(new Imagen(new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.panelEdicion.getRutaActual()))));
        this.panelAdministrar.mostrarInformacion(new File(this.panelEdicion.getRutaActual()));
        this.ruta = this.panelEdicion.getRutaActual();
    }    
    
    public void repintarPanelCollage()
    {
        this.panelPrincipal.removeAll();
        this.panelPrincipal.revalidate();
        this.ruta = this.panelEdicion.getRutaActual();
        this.panelCollage = new PanelCollage(this);
        this.panelPrincipal.add(panelCollage);
    }

    public PanelEdicion getPanelEdicion() 
    {
        return panelEdicion;
    }

    public PanelCollage getPanelCollage()
    {
        return panelCollage;
    }
    
    @Override
    public void windowStateChanged(WindowEvent e)
    {
        this.revalidate();
        this.repaint();
        if(this.panelCollage!=null)
        {
            this.panelCollage.repaint();
        }
    }   
}
