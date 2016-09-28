/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Edicion;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author HoLeX
 */
public class PanelFiltro extends JPanel implements ChangeListener
{
    private PanelEdicion panelEdicion;
    private JTabbedPane panelPestanas;
    
    private PanelFormas panelFormas;
    private PanelProcesamiento panelProcesamiento;
    
    public PanelFiltro(PanelEdicion panelEdicion)
    {
        this.panelEdicion = panelEdicion;
        this.setLayout(new BorderLayout());
        this.panelPestanas = new JTabbedPane();
        this.panelPestanas.setFocusable(false);

        this.panelFormas = new PanelFormas(this, this.panelEdicion);
        this.panelProcesamiento = new PanelProcesamiento(this);
        
        JScrollPane scrollPanelFormas = new JScrollPane(panelFormas);
        scrollPanelFormas.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPanelFormas.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        JScrollPane scrollPanelProcesamiento = new JScrollPane(panelProcesamiento);
        scrollPanelProcesamiento.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPanelProcesamiento.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        this.panelPestanas.addTab("", new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/sun24.png"))), scrollPanelFormas,"Figuras y conectores");  
        this.panelPestanas.addTab("", new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/brush24.png"))), scrollPanelProcesamiento, "Procesamiento de imagen");

        this.panelPestanas.addChangeListener(this);
        
        this.add(panelPestanas, BorderLayout.CENTER);
        
    }

    public PanelProcesamiento getPanelProcesamiento()
    {
        return panelProcesamiento;
    }
    
    public PanelFormas getPanelFormas()
    {
        return this.panelFormas;
    }
    public PanelEdicion getPanelEdicion()
    {
        return this.panelEdicion;
    }
    
    public void desactivarEdicion(boolean desactivar)
    {
        this.panelFormas.desactivarColores(desactivar);
        this.panelFormas.desactivarGrosor(desactivar);
        this.panelFormas.desactivarHerramientaLinea(desactivar);
        this.panelFormas.desactivarBotonesFiguras();
        this.panelFormas.desactivarFiguras(desactivar);
        this.panelFormas.desactivarPuntos(desactivar);
        this.panelFormas.desactivarHerramientaDibujo(desactivar);
        
        this.panelProcesamiento.desactivarFiltros(desactivar);
    }

    @Override
    public void stateChanged(ChangeEvent e)
    {
        
        if(e.getSource() == this.panelPestanas && this.panelPestanas.getSelectedIndex() == 1)
        {
            this.panelEdicion.getVentana().getBarra().setSubPanelEdicionMostrado(2);

            if(this.panelEdicion.getPanelImagen().getFiguras().size() > 0)
            {
                int opcion = JOptionPane.showConfirmDialog(null, "Tiene figuras dibujadas, desea guardar la imagen y sus cambios?", "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            
                if(opcion == 0)
                {
                    this.panelEdicion.getPanelImagen().guardarImagen(this.panelEdicion.getRutaActual());
                }
                else
                {
                    this.panelEdicion.setImagen(new Imagen(new ImageIcon(panelEdicion.getRutaActual())), panelEdicion.getRutaActual());
                }                
                this.getPanelEdicion().getPanelImagen().getFiguras().clear();
                this.getPanelEdicion().getPanelImagen().setFiguraSeleccionada(null);
                this.getPanelEdicion().getPanelImagen().setModoDibujo(0);
            }
        }
        
        else if(e.getSource() == this.panelPestanas && this.panelPestanas.getSelectedIndex() == 0)
        {
            this.panelEdicion.getVentana().getBarra().setSubPanelEdicionMostrado(1);
            
            if(this.panelEdicion.getPanelFiltro().getPanelProcesamiento().isTieneFiltro())
            {
                int opcion = JOptionPane.showConfirmDialog(null, "Tiene filtro aplicados, desea guardar la imagen y sus cambios?", "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            
                if(opcion == 0)
                {
                    this.panelEdicion.getPanelImagen().guardarImagen(this.panelEdicion.getRutaActual());
                }
                else
                {
                    this.panelEdicion.setImagen(new Imagen(new ImageIcon(panelEdicion.getRutaActual())), panelEdicion.getRutaActual());
                }
                this.getPanelEdicion().getPanelFiltro().getPanelProcesamiento().setTieneFiltro(false);
            }

        }
              
    }
}

