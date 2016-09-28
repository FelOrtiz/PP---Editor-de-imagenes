/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Edicion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Yerco
 */
public class PanelHerramientaDibujo extends JPanel implements ActionListener
{
 
    private JToggleButton seleccion, lapiz, sprite, goma;
    private PanelEdicion panelEdicion;
    private JPanel panelPrincipal;
    
    public PanelHerramientaDibujo( PanelEdicion panelEdicion )
    {
        this.panelEdicion = panelEdicion;
        
        this.setLayout(new BorderLayout()); 
        TitledBorder borde = BorderFactory.createTitledBorder(" Herramientas de Dibujo ");
        borde.setTitleJustification(TitledBorder.CENTER);
        this.setBorder(borde);
        
        this.panelPrincipal = new JPanel();
        this.panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        this.panelPrincipal.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        this.seleccion = new JToggleButton();
        this.lapiz = new JToggleButton();
        this.sprite = new JToggleButton();
        this.goma = new JToggleButton();
        
        this.seleccion.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/select.png"))));
        this.lapiz.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/lapiz.png"))));
        this.sprite.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/aerografo.png"))));
        this.goma.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/goma.png"))));
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        
        this.seleccion.addActionListener(this);
        this.lapiz.addActionListener(this);
        this.sprite.addActionListener(this);
        this.goma.addActionListener(this);

        panel.add(this.seleccion);
        panel.add(Box.createRigidArea(new Dimension(5,0)));
        panel.add(this.lapiz);
        panel.add(Box.createRigidArea(new Dimension(5,0)));
        panel.add(this.sprite);
        panel.add(Box.createRigidArea(new Dimension(5,0)));
        panel.add(this.goma);
        
        this.panelPrincipal.add(panel);
        
        super.add(panelPrincipal, BorderLayout.CENTER);
        
    }

    public void activarModo(JToggleButton boton )
    {
        if( boton.equals(this.seleccion)  )
        {
            this.panelEdicion.getPanelImagen().setModoDibujo(12);
            this.lapiz.getModel().setSelected(false);
            this.sprite.getModel().setSelected(false);
            this.goma.getModel().setSelected(false);
            
        }
        else if( boton.equals(this.lapiz)  )
        {
            this.panelEdicion.getPanelImagen().setModoDibujo(8);
            this.panelEdicion.getPanelImagen().setFiguraSeleccionada(null);
            this.seleccion.getModel().setSelected(false);
            this.sprite.getModel().setSelected(false);
            this.goma.getModel().setSelected(false);
        }
        else if( boton.equals(this.sprite)  )
        {
            this.panelEdicion.getPanelImagen().setModoDibujo(10);
            this.panelEdicion.getPanelImagen().setFiguraSeleccionada(null);
            this.lapiz.getModel().setSelected(false);
            this.seleccion.getModel().setSelected(false);
            this.goma.getModel().setSelected(false);
        }
        else if(boton.equals(this.goma) )
        {
            this.panelEdicion.getPanelImagen().setModoDibujo(9);
            this.panelEdicion.getPanelImagen().setFiguraSeleccionada(null);
            this.lapiz.getModel().setSelected(false);
            this.sprite.getModel().setSelected(false);
            this.seleccion.getModel().setSelected(false);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if( e.getSource() instanceof  JToggleButton )
        {
            JToggleButton botonPresionado = (JToggleButton)e.getSource();
            if( botonPresionado.getModel().isSelected() )
            {
                this.activarModo(botonPresionado);
                panelEdicion.getPanelFiltro().getPanelFormas().desactivarBotonesFiguras();
                this.panelEdicion.getPanelFiltro().getPanelFormas().getPanelPuntos().desactivarBotones();
                this.panelEdicion.getPanelFiltro().getPanelFormas().desactivarHerramientaLinea(false);
            }
            else
            {
                panelEdicion.getPanelImagen().setModoDibujo(0);
            }
        }
    }
    
    public void desactivarHerramientas(boolean desactivar)
    {
        this.lapiz.getModel().setEnabled(desactivar);
        this.sprite.getModel().setEnabled(desactivar);
        this.goma.getModel().setEnabled(desactivar);
        this.seleccion.getModel().setEnabled(desactivar);
    }
    
    public void desactivarBotonesHerramientas()
    {
        
        this.lapiz.getModel().setSelected(false);
        this.sprite.getModel().setSelected(false);
        this.goma.getModel().setSelected(false);
        this.seleccion.getModel().setSelected(false);
    }
    
}
