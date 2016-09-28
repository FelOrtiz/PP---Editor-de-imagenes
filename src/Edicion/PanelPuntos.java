/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;

/**
 *
 * @author HoLeX
 */
public class PanelPuntos extends JPanel implements ActionListener
{
    private JPanel panelPrincipal;
    
    private JToggleButton botonCuadrado;
    private JToggleButton botonCirculo;
    private JToggleButton botonEstrella;
    private JToggleButton botonRombo;
    
    private JPanel panelBox;
    private JComboBox boxSize;
    
    private PanelFormas panelFormas;
    
    public PanelPuntos(PanelFormas panelFormas)
    {
        this.panelFormas = panelFormas;
        
        this.setLayout(new BorderLayout());
        TitledBorder borde = BorderFactory.createTitledBorder(" Puntos ");
        borde.setTitleJustification(TitledBorder.CENTER);
        this.setBorder(borde);        
        
        this.panelPrincipal = new JPanel();
        this.panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        this.panelPrincipal.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        this.botonCuadrado = new JToggleButton();
        this.botonCirculo = new JToggleButton();
        this.botonEstrella = new JToggleButton();
        this.botonRombo = new JToggleButton();
        
        this.botonCuadrado.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/rectanguloV.png"))));
        this.botonCirculo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/circuloV.png"))));
        this.botonEstrella.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/estrellaV.png"))));
        this.botonRombo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/romboV.png"))));
        
        this.botonCuadrado.addActionListener(this);
        this.botonCirculo.addActionListener(this);
        this.botonEstrella.addActionListener(this);
        this.botonRombo.addActionListener(this);        
        
        this.panelBox = new JPanel(new BorderLayout());
        this.panelBox.setBorder(BorderFactory.createEmptyBorder(0,15,0,18));
        
        Object[] items = 
        {
            "3", "4", "5", "7", "9", "10"
        };        
        this.boxSize = new JComboBox(items);
        this.boxSize.addActionListener(this);
        this.boxSize.setSelectedIndex(2);
        
        JLabel label = new JLabel("Tamaño de puntos: ");
        
        this.panelBox.add(label, BorderLayout.WEST);
        this.panelBox.add(boxSize, BorderLayout.CENTER);
        
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
        
        panel1.add(botonCuadrado);
        panel1.add(Box.createRigidArea(new Dimension(5,0)));
        panel1.add(botonCirculo);
        panel1.add(Box.createRigidArea(new Dimension(5,0)));
        panel1.add(botonEstrella);
        panel1.add(Box.createRigidArea(new Dimension(5,0)));
        panel1.add(botonRombo);
        
        this.panelPrincipal.add(panel1);
        this.panelPrincipal.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelPrincipal.add(this.panelBox);
         
        
        this.add(panelPrincipal, BorderLayout.CENTER);
        
        this.panelFormas.getPanelFiltro().getPanelEdicion().getPanelImagen().setTamanioPunto( Integer.parseInt( (String)this.boxSize.getSelectedItem() ) );
    }

    public void desactivarPuntos(boolean desactivar)
    {
        this.botonCirculo.setEnabled(desactivar);
        this.botonCuadrado.setEnabled(desactivar);
        this.botonRombo.setEnabled(desactivar);
        this.botonEstrella.setEnabled(desactivar);
        this.boxSize.setEnabled(desactivar);
    }
    
    public void desactivarBotones()
    {
        this.botonCirculo.setSelected(false);
        this.botonCuadrado.setSelected(false);
        this.botonRombo.setSelected(false);
        this.botonEstrella.setSelected(false);
    }
    
    
    public void activarModo(JToggleButton boton)
    {
        if(boton.equals(this.botonCirculo))
        {
            this.panelFormas.getPanelFiltro().getPanelEdicion().getPanelImagen().setModoDibujo(13);
            this.botonCuadrado.getModel().setSelected(false);
            this.botonRombo.getModel().setSelected(false);
            this.botonEstrella.getModel().setSelected(false);
            
        }
        else if( boton.equals(this.botonCuadrado) )
        {
            this.panelFormas.getPanelFiltro().getPanelEdicion().getPanelImagen().setModoDibujo(14);
            this.botonCirculo.getModel().setSelected(false);
            this.botonRombo.getModel().setSelected(false);
            this.botonEstrella.getModel().setSelected(false);
            
        }
        else if( boton.equals(this.botonRombo) )
        {
            this.panelFormas.getPanelFiltro().getPanelEdicion().getPanelImagen().setModoDibujo(15);
            this.botonCirculo.getModel().setSelected(false);
            this.botonCuadrado.getModel().setSelected(false);
            this.botonEstrella.getModel().setSelected(false);
            
        }
        else if( boton.equals(this.botonEstrella) )
        {
            this.panelFormas.getPanelFiltro().getPanelEdicion().getPanelImagen().setModoDibujo(16);
            this.botonCirculo.getModel().setSelected(false);
            this.botonCuadrado.getModel().setSelected(false);
            this.botonRombo.getModel().setSelected(false);
           
        }
        
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if( e.getSource() instanceof  JToggleButton )
        {
            JToggleButton botonPresionado = (JToggleButton)e.getSource();
            if( botonPresionado.getModel().isSelected())
            {
                this.panelFormas.getPanelFiltro().getPanelEdicion().getPanelImagen().setFiguraSeleccionada( null );
                this.panelFormas.getPanelFiltro().getPanelFormas().desactivarBotonesHerramientaDibujo();
                this.panelFormas.getPanelFiltro().getPanelFormas().getPanelHerramientaLinea().desactivarHerramientaLinea(false);
                this.panelFormas.getPanelFiltro().getPanelFormas().desactivarBotonesFiguras();
                this.activarModo(botonPresionado);
            }
            else
            {
                this.panelFormas.getPanelFiltro().getPanelEdicion().getPanelImagen().setModoDibujo(0);
            }
            
        }
        else if ( e.getSource() instanceof  JComboBox )
        {
            JComboBox c = (JComboBox)e.getSource();
            this.panelFormas.getPanelFiltro().getPanelEdicion().getPanelImagen().setTamanioPunto(c.getSelectedIndex());
        }
        
    }

    public JToggleButton getBotonCirculo()
    {
        return botonCirculo;
    }

    public JToggleButton getBotonCuadrado()
    {
        return botonCuadrado;
    }

    public JToggleButton getBotonEstrella()
    {
        return botonEstrella;
    }

    public JToggleButton getBotonRombo()
    {
        return botonRombo;
    }
    
    
    public int tamanio()
    {
        return this.boxSize.getSelectedIndex();
    }
    
}
