package Collage;

import Visual.VentanaPrincipal;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;


/**
 *
 * @author HoLeX
 */
public class PanelCollage extends JPanel
{
    private String rutaActual;
     
    private PanelAreaTrabajo panelAreaTrabajo;
    private BarraHerramientas barraHerramientas;
            
    private VentanaPrincipal ventana;
    
    public PanelCollage(VentanaPrincipal ventana)
    {
        this.ventana = ventana;
        
        this.setLayout(new BorderLayout());
        this.rutaActual = ventana.getRuta();
        this.panelAreaTrabajo = new PanelAreaTrabajo(this);
        this.panelAreaTrabajo.setPreferredSize(new Dimension(1366, 634));
        this.barraHerramientas = new BarraHerramientas(panelAreaTrabajo);

        this.add(barraHerramientas, BorderLayout.NORTH);      
        this.add(panelAreaTrabajo, BorderLayout.CENTER);
    }

    public String getRutaActual()
    {
        return rutaActual;
    }

    public VentanaPrincipal getVentana()
    {
        return ventana;
    }

    public BarraHerramientas getBarraHerramientas()
    {
        return barraHerramientas;
    }

    public void guardarImagen(String ruta)
    {
        this.panelAreaTrabajo.guardarImagen(ruta);
    }
    
    @Override
    public void paint( Graphics g )
    {
        super.paint(g);
        
    }
}