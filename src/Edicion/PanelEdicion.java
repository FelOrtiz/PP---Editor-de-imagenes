/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Edicion;

import Visual.VentanaPrincipal;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author HoLeX
 */
public class PanelEdicion extends JPanel 
{
    private VentanaPrincipal ventana;
    private JSplitPane panelDivisorFiltroImagen;
    private JSplitPane panelDivisorImagenTira; 
    
    private JScrollPane scrollPaneImagen;
    private JPanel panelImagenZoom;
    private PanelZoom panelZoom;
    private JScrollPane panelTira;
    private PanelImagen panelImagen;
    private Imagen imagen;
    private PanelFiltro panelFiltro;
    private PanelTiraImagenes panelTiraImagenes;
    
    private String rutaActual;
    
    public PanelEdicion(VentanaPrincipal ventana)
    {
        this.setLayout(new BorderLayout());
        this.ventana = ventana;
        this.panelTiraImagenes = new PanelTiraImagenes(this.ventana.getRuta(), this);
 
        this.panelTira = new JScrollPane(panelTiraImagenes);
        this.panelTira.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.panelTira.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);      
        this.panelTira.setPreferredSize(new Dimension(210,0));
       
        this.panelImagen = new PanelImagen(this);
        this.scrollPaneImagen = new JScrollPane(panelImagen);
        this.scrollPaneImagen.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.scrollPaneImagen.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); 
        this.scrollPaneImagen.setPreferredSize(new Dimension(0, 310));
        
        this.panelZoom = new PanelZoom(100, this);
        this.panelZoom.setPreferredSize(new Dimension(269, 41));
        this.panelImagenZoom = new JPanel(new BorderLayout());
        this.panelImagenZoom.add(scrollPaneImagen, BorderLayout.CENTER);
        this.panelImagenZoom.add(panelZoom, BorderLayout.SOUTH);        
        
        this.panelDivisorImagenTira = new JSplitPane();
        this.panelDivisorImagenTira.setEnabled(false);
        this.panelDivisorImagenTira.setOrientation(JSplitPane.VERTICAL_SPLIT);
        this.panelDivisorImagenTira.setTopComponent(panelImagenZoom);
        this.panelDivisorImagenTira.setBottomComponent(panelTira);
        this.panelDivisorImagenTira.setResizeWeight(0.55);
        
        this.panelFiltro = new PanelFiltro(this);
        this.panelFiltro.setPreferredSize(new Dimension(220,0));
        
        this.panelDivisorFiltroImagen = new JSplitPane();
        this.panelDivisorFiltroImagen.setEnabled(false);
        this.panelDivisorFiltroImagen.setLeftComponent(panelFiltro);
        this.panelDivisorFiltroImagen.setRightComponent(panelDivisorImagenTira);
        this.panelDivisorFiltroImagen.setResizeWeight(.1);
        
        this.add(panelDivisorFiltroImagen, BorderLayout.CENTER);
    }
    
    public void setImagen(Imagen imagen, String rutaActual)
    {     
        this.imagen = imagen;
        this.rutaActual = rutaActual;
        
        if(rutaActual.toLowerCase().endsWith("gif"))
        {
            this.activarEdicion(false);
        }
        else
        {
            this.activarEdicion(true);
        }
        int ancho = this.imagen.getImagen().getIconWidth();
        int alto = this.imagen.getImagen().getIconHeight();

        int porcentajeAncho = (int) Math.round((double)((double)1032/((double)(ancho))*100));//1064
        int porcentajeAlto = (int) Math.round((double)(((double)470/((double)(alto)))*100));//489
        
        if(ancho < 1032 && alto < 472)
        {
            this.ZoomearImagen(100);
            this.panelZoom.setInit(100);
        }
        else if(porcentajeAncho >= porcentajeAlto)
        {
            this.ZoomearImagen(porcentajeAlto);
            this.panelZoom.setInit(porcentajeAlto);
        }
        else
        {
            this.ZoomearImagen(porcentajeAncho);
            this.panelZoom.setInit(porcentajeAncho);
        }
        /*
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(this.imagen.getImagen().getIconWidth(), this.imagen.getImagen().getIconHeight()));
        panel.add(this.imagen, BorderLayout.CENTER);
        JOptionPane.showMessageDialog(null, panel);
                */
        this.panelFiltro.getPanelProcesamiento().setImagenFiltro(imagen);
    
    }    
    
    public PanelImagen getPanelImagen()
    {
        return this.panelImagen;
    }
    
    public Imagen getImagen()
    {
        return this.imagen;
    }

    public PanelTiraImagenes getPanelTiraImagenes()
    {
        return panelTiraImagenes;
    }
    
    public String getRutaActual()
    {
        return this.rutaActual;
    }
    
    public VentanaPrincipal getVentana()
    {
        return this.ventana;
    }
    
    public PanelZoom getPanelZoom()
    {
        return this.panelZoom;
    }

    public PanelFiltro getPanelFiltro()
    {
        return panelFiltro;
    }

    public void ZoomearImagen(int porcentaje)
    {
        Imagen imagenZoomeada = new Imagen(this.imagen.ZoomImagen(porcentaje));
        this.panelImagen.setImagen(imagenZoomeada);
    }
    
    public void activarEdicion(boolean desactivar)
    {
        this.panelFiltro.desactivarEdicion(desactivar);
    }
    
    public void guardarImagen(String ruta)
    {        
        File outputFile = new File(ruta);
        BufferedImage imagenFinalGuardar = this.imagen.imageIconToBufferedImage(this.imagen.getImagen().getImage());
        if(outputFile.exists())
        {
            int opcion = JOptionPane.showConfirmDialog(null, "Desea sobreescribir la imagen?", "Guardar como...", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            
            if(opcion == 0)
            {

                try
                {
                    ImageIO.write(imagenFinalGuardar,"jpg", outputFile);
                }
                catch (IOException e)
                {
                    JOptionPane.showMessageDialog(null, "Error al guardar la imagen", "Error", JOptionPane.ERROR_MESSAGE);
                }  
            }
        }
        else
        {
            try
            {
                ImageIO.write(imagenFinalGuardar,"jpg", outputFile);
            }
            catch (IOException e)
            {
                JOptionPane.showMessageDialog(null, "Error al guardar la imagen", "Error", JOptionPane.ERROR_MESSAGE);
            }             
        }
    }
    
    
    
    
}
