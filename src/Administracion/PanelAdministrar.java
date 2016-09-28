/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Administracion;


import Edicion.Imagen;
import Visual.VentanaPrincipal;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author HoLeX
 */
public class PanelAdministrar extends JPanel
{
    private VentanaPrincipal ventana;
    private JPanel arbolDirectorio;
    private JScrollPane scrollArbol;
    
    private PanelCentral panelCentral;
    private JScrollPane scrollPanelCentral;
    private JSplitPane panelDivisorArbolImagen;
    private PanelMetadatos panelMetadatos;
    private JScrollPane scrollMetadatos;
    private JSplitPane panelDivisorImagenDatos;
    
    
    public PanelAdministrar(VentanaPrincipal ventana)
    {
        super.setLayout(new BorderLayout());
        this.ventana = ventana;
        
        this.arbolDirectorio = new PanelArbolDirectorio(System.getProperty("user.home"), this);        
        
        this.scrollArbol = new JScrollPane(arbolDirectorio);
        this.scrollArbol.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.scrollArbol.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.scrollArbol.setPreferredSize(new Dimension(0,250));
        
        this.panelCentral = new PanelCentral();
        
        this.scrollPanelCentral = new JScrollPane(panelCentral);
        this.scrollPanelCentral.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.scrollPanelCentral.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        this.panelDivisorArbolImagen = new JSplitPane();
        this.panelDivisorArbolImagen.setEnabled(false);
        this.panelDivisorArbolImagen.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        this.panelDivisorArbolImagen.setRightComponent(scrollPanelCentral);
        this.panelDivisorArbolImagen.setLeftComponent(scrollArbol);
        this.panelDivisorArbolImagen.setOneTouchExpandable(false);
        this.panelDivisorArbolImagen.setResizeWeight(.25);
        
        this.panelMetadatos = new PanelMetadatos(this.ventana);
        
        this.scrollMetadatos = new JScrollPane(panelMetadatos);
        this.scrollMetadatos.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.scrollMetadatos.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        this.scrollMetadatos.setPreferredSize(new Dimension(0,250));
        
        this.panelDivisorImagenDatos = new JSplitPane();
        this.panelDivisorImagenDatos.setEnabled(false);
        this.panelDivisorImagenDatos.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        this.panelDivisorImagenDatos.setLeftComponent(panelDivisorArbolImagen);
        this.panelDivisorImagenDatos.setRightComponent(scrollMetadatos);
        this.panelDivisorImagenDatos.setOneTouchExpandable(false);
        
        this.panelDivisorImagenDatos.setResizeWeight(.75);
        
        this.add(panelDivisorImagenDatos, BorderLayout.CENTER);
    }
    
    public void setRuta(String ruta)
    {
        this.ventana.setRuta(ruta);
    }
    
    /*
    Dependiendo de la imagen, deshabilito o no los botones de edicion como:
    si es un gif, deshabilito; sino los habilito
    */
    public void habilitarEdicion(String ruta, boolean flag)
    {
        this.ventana.getBarra().habilitarBotones(ruta, flag);
    }
    
    /*
    Esta funcion cambia la imagen del panelAdministrar
    */
    public void setImagenPrincipal(Imagen imagen)
    {
        Imagen imagenFinal = new Imagen(imagen.EscalarImagenPrincipal(760, 681, imagen));        
        this.panelCentral.setImagen(imagenFinal);
    }
    
    /*
    Esta funcion cambia la imagen cuando esta es BMP, ya que se hace de otra forma
    */
    public ImageIcon setImagenPrincipalBMP(File file)
    {
        try
        {
            BufferedImage imagen = ImageIO.read(file);//leo la imagen
            Imagen imagenCasiFinal = new Imagen(new ImageIcon(imagen));//paso la imagen a Imagen
            Imagen imagenFinal = new Imagen(imagenCasiFinal.EscalarImagenPrincipal(760, 681, imagenCasiFinal));//la escalo        
            this.panelCentral.setImagen(imagenFinal);//la setteo en el panel
            
            return new ImageIcon(imagen);
        }
        catch (IOException ex)
        {
        }
        return null;
    }
    
    /*
    Esta funcion muestra la informacion(metadatos) de la imagen cuando es BMP
    */
    public void mostrarInformacionBMP(File file, ImageIcon imagen)
    {
        this.panelMetadatos.mostrarAtributosBasicosBMP(file, imagen);
    }
    
    /*
    Esta funcion muestra un mensaje de error cuando el usuario selecciona algo 
    distinto a una imagen
    */
    public void mostrarError()
    {
        this.panelCentral.setError();
    }
    
    /*
    Esta funcion muestra un mensaje vacio cuando el usuario selecciona una carpeta
    o acceso directo a una carpeta
    */
    public void mostrarNada()
    {
        this.panelCentral.mostrarNada();        
    }
    
    /*
    Esta funcion muestra los metadatos de la imagen, todas excepto BMP(para estas hay otro metodo)
    */
    public void mostrarInformacion(File file)
    {
        this.panelMetadatos.setMetadatos(file);
    }

    /*
    Esta funcion muestra los metadatos vacios, cuando es usuario escoge todo, menos una imagen
    */
    public void mostrarNadaMetadatos()
    {
        this.panelMetadatos.mostrarNada();
    }
}
