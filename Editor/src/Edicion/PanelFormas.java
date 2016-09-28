/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Edicion;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author HoLeX
 */
public class PanelFormas extends JPanel
{
    private JPanel panelPrincipal;
    private PanelHerramientaLinea panelHerramientaLinea;
    private PanelColores panelColores;
    private PanelGrosor panelGrosor;
    private PanelFiguras panelFiguras;
    private PanelHerramientaDibujo panelHerramientaDibujo;
    private PanelPuntos panelPuntos;
    
    private PanelFiltro panelFiltro;
    
    public PanelFormas(PanelFiltro panelFiltro, PanelEdicion panelEdicion )
    {
        this.panelFiltro = panelFiltro;
        
        this.setLayout(new BorderLayout());
        
        this.panelPrincipal = new JPanel();
        this.panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        
        this.panelGrosor = new PanelGrosor(this);
        this.panelHerramientaLinea = new PanelHerramientaLinea(this, panelEdicion);
        this.panelColores = new PanelColores(this);
        this.panelFiguras = new PanelFiguras(panelEdicion);
        this.panelHerramientaDibujo = new PanelHerramientaDibujo(panelEdicion);
        this.panelPuntos = new PanelPuntos(this);   
        
        this.panelPrincipal.add(panelHerramientaDibujo);
        this.panelPrincipal.add(panelFiguras);
        this.panelPrincipal.add(panelPuntos);
        this.panelPrincipal.add(panelGrosor);
        this.panelPrincipal.add(panelHerramientaLinea);
        this.panelPrincipal.add(panelColores);
        
        this.add(panelPrincipal, BorderLayout.NORTH);
    }              
    
    public void desactivarColores(boolean desactivar)
    {
        this.panelColores.desactivarColores(desactivar);
    }
    public void desactivarGrosor(boolean desactivar)
    {
        this.panelGrosor.desactivarGrosor(desactivar);
    }
    public void desactivarHerramientaLinea(boolean desactivar)
    {
        this.panelHerramientaLinea.desactivarHerramientaLinea(false);
    }
    public void desactivarFiguras(boolean desactivar)
    {
        this.panelFiguras.desactivarFiguras(desactivar); 
    }

    public void desactivarBotonesFiguras()
    {
        this.panelFiguras.desactivarBotones(); 
    }
    
    public void desactivarPuntos(boolean desactivar)
    {
        this.panelPuntos.desactivarPuntos(desactivar);
    }
    
    public void desactivarBotonesHerramientaDibujo()
    {
        this.panelHerramientaDibujo.desactivarBotonesHerramientas(); 
    }
    
    public void desactivarHerramientaDibujo(boolean desactivar)
    {
        this.panelHerramientaDibujo.desactivarHerramientas(desactivar); 
    }
    
    public PanelHerramientaLinea getPanelHerramientaLinea()
    {
        return panelHerramientaLinea;
    }

    public PanelHerramientaDibujo getPanelHerramientaDibujo()
    {
        return panelHerramientaDibujo;
    }

    public PanelPuntos getPanelPuntos()
    {
        return panelPuntos;
    }

    
    
    
    
    public PanelFiltro getPanelFiltro()
    {
        return panelFiltro;
    }
   
}