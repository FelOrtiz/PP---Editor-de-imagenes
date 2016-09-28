/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Edicion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 *
 * @author HoLeX
 */
public class PanelFiguras extends JPanel implements ActionListener
{
    private JToggleButton circulo, ovalo, rectangulo, rombo, estrella, pentagono, linea;
    private PanelEdicion panelEdicion;
    private JPanel panelPrincipal;
    
    public PanelFiguras(PanelEdicion panelEdicion)
    {
        this.panelEdicion = panelEdicion;
        
        this.setLayout(new BorderLayout()); 
        TitledBorder borde = BorderFactory.createTitledBorder(" Figuras ");
        borde.setTitleJustification(TitledBorder.CENTER);
        this.setBorder(borde);
        
        this.panelPrincipal = new JPanel();
        this.panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        this.panelPrincipal.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        this.circulo = new JToggleButton();
        this.ovalo = new JToggleButton();
        this.rectangulo = new JToggleButton();
        this.rombo = new JToggleButton();
        this.estrella = new JToggleButton();
        this.pentagono = new JToggleButton();
        this.linea = new JToggleButton();
        
        this.circulo.addActionListener(this);
        this.ovalo.addActionListener(this);
        this.rectangulo.addActionListener(this);
        this.rombo.addActionListener(this);
        this.estrella.addActionListener(this);
        this.pentagono.addActionListener(this);
        this.linea.addActionListener(this);
        

        this.circulo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/circulo.png"))));
        this.ovalo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/elipse.png"))));
        this.rectangulo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/rectangulo.png"))));
        this.rombo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/rombo.png"))));
        this.estrella.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/estrella.png"))));
        this.pentagono.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/pentagono.png"))));
        this.linea.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/line.png"))));
   
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
   
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
        
        panel1.add(circulo);
        panel1.add(Box.createRigidArea(new Dimension(5,0)));
        panel1.add(ovalo);
        panel1.add(Box.createRigidArea(new Dimension(5,0)));
        panel1.add(rectangulo);
        panel1.add(Box.createRigidArea(new Dimension(5,0)));
        panel1.add(linea);
        
        panel2.add(estrella);
        panel2.add(Box.createRigidArea(new Dimension(5,0)));
        panel2.add(pentagono);
        panel2.add(Box.createRigidArea(new Dimension(5,0)));
        panel2.add(rombo);
        panel2.add(Box.createRigidArea(new Dimension(5,0)));
        
        this.panelPrincipal.add(panel1);
        this.panelPrincipal.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelPrincipal.add(panel2);
        
        this.add(panelPrincipal, BorderLayout.CENTER);
        
    }

    public void activarModo(JToggleButton boton)
    {
        if(boton.equals(this.circulo))
        {
            this.panelEdicion.getPanelImagen().setModoDibujo(1);
            this.ovalo.getModel().setSelected(false);
            this.rectangulo.getModel().setSelected(false);
            this.rombo.getModel().setSelected(false);
            this.estrella.getModel().setSelected(false);
            this.pentagono.getModel().setSelected(false);
            this.linea.getModel().setSelected(false);   
            this.panelEdicion.getPanelFiltro().getPanelFormas().getPanelHerramientaLinea().desactivarHerramientaLinea(false);
            this.panelEdicion.getPanelFiltro().getPanelFormas().getPanelPuntos().desactivarBotones();

        }
        else if(boton.equals(this.ovalo))
        {
            this.panelEdicion.getPanelImagen().setModoDibujo(2);
            this.circulo.getModel().setSelected(false);
            this.rectangulo.getModel().setSelected(false);
            this.rombo.getModel().setSelected(false);
            this.estrella.getModel().setSelected(false);
            this.pentagono.getModel().setSelected(false);
            this.linea.getModel().setSelected(false);    
            this.panelEdicion.getPanelFiltro().getPanelFormas().getPanelHerramientaLinea().desactivarHerramientaLinea(false);
            this.panelEdicion.getPanelFiltro().getPanelFormas().getPanelPuntos().desactivarBotones();

        }
        else if(boton.equals(this.rectangulo))
        {
            this.panelEdicion.getPanelImagen().setModoDibujo(3);
            this.circulo.getModel().setSelected(false);
            this.ovalo.getModel().setSelected(false);
            this.rombo.getModel().setSelected(false);
            this.estrella.getModel().setSelected(false);
            this.pentagono.getModel().setSelected(false);
            this.linea.getModel().setSelected(false);
            this.panelEdicion.getPanelFiltro().getPanelFormas().getPanelHerramientaLinea().desactivarHerramientaLinea(false);
            this.panelEdicion.getPanelFiltro().getPanelFormas().getPanelPuntos().desactivarBotones();

        }
        else if(boton.equals(this.rombo))
        {
            this.panelEdicion.getPanelImagen().setModoDibujo(4);
            this.circulo.getModel().setSelected(false);
            this.ovalo.getModel().setSelected(false);
            this.rectangulo.getModel().setSelected(false);
            this.estrella.getModel().setSelected(false);
            this.pentagono.getModel().setSelected(false);
            this.linea.getModel().setSelected(false);  
            this.panelEdicion.getPanelFiltro().getPanelFormas().getPanelHerramientaLinea().desactivarHerramientaLinea(false);
            this.panelEdicion.getPanelFiltro().getPanelFormas().getPanelPuntos().desactivarBotones();

        }
        else if(boton.equals(this.estrella))
        {
            this.panelEdicion.getPanelImagen().setModoDibujo(5);
            this.circulo.getModel().setSelected(false);
            this.ovalo.getModel().setSelected(false);
            this.rectangulo.getModel().setSelected(false);
            this.rombo.getModel().setSelected(false);
            this.pentagono.getModel().setSelected(false);
            this.linea.getModel().setSelected(false); 
            this.panelEdicion.getPanelFiltro().getPanelFormas().getPanelHerramientaLinea().desactivarHerramientaLinea(false);
            this.panelEdicion.getPanelFiltro().getPanelFormas().getPanelPuntos().desactivarBotones();

        }
        else if(boton.equals(this.pentagono))
        {
            this.panelEdicion.getPanelImagen().setModoDibujo(6);
            this.circulo.getModel().setSelected(false);
            this.ovalo.getModel().setSelected(false);
            this.rectangulo.getModel().setSelected(false);
            this.rombo.getModel().setSelected(false);
            this.estrella.getModel().setSelected(false);
            this.linea.getModel().setSelected(false);
            this.panelEdicion.getPanelFiltro().getPanelFormas().getPanelHerramientaLinea().desactivarHerramientaLinea(false);
            this.panelEdicion.getPanelFiltro().getPanelFormas().getPanelPuntos().desactivarBotones();

        }
        else if(boton.equals(this.linea))
        {
            this.panelEdicion.getPanelImagen().setModoDibujo(7);
            this.circulo.getModel().setSelected(false);
            this.ovalo.getModel().setSelected(false);
            this.rectangulo.getModel().setSelected(false);
            this.rombo.getModel().setSelected(false);
            this.estrella.getModel().setSelected(false);
            this.pentagono.getModel().setSelected(false);
            if( this.linea.getModel().isSelected() )
            {
                this.panelEdicion.getPanelFiltro().getPanelFormas().getPanelHerramientaLinea().desactivarHerramientaLinea(true);
                this.panelEdicion.getPanelFiltro().getPanelFormas().getPanelPuntos().desactivarBotones();
            }
            else
            {
                this.panelEdicion.getPanelFiltro().getPanelFormas().getPanelHerramientaLinea().desactivarHerramientaLinea(false);
            }
        }
    }
    
    public void desactivarFiguras(boolean desactivar)
    {
        this.circulo.setEnabled(desactivar);
        this.rectangulo.setEnabled(desactivar);
        this.ovalo.setEnabled(desactivar);
        this.rombo.setEnabled(desactivar);
        this.estrella.setEnabled(desactivar);
        this.pentagono.setEnabled(desactivar);
        this.linea.setEnabled(desactivar);
    }
    
    public void desactivarBotones()
    {
        this.circulo.setSelected(false);
        this.rectangulo.setSelected(false);
        this.ovalo.setSelected(false);
        this.rombo.setSelected(false);
        this.estrella.setSelected(false);
        this.pentagono.setSelected(false);
        this.linea.setSelected(false);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if( e.getSource() instanceof  JToggleButton )
        {
            JToggleButton botonPresionado = (JToggleButton)e.getSource();
            if( botonPresionado.getModel().isSelected())
            {
                this.activarModo(botonPresionado);
                this.panelEdicion.getPanelFiltro().getPanelFormas().desactivarBotonesHerramientaDibujo();
                this.panelEdicion.getPanelImagen().setFiguraSeleccionada(null);
            }
            else
            {
                this.activarModo(botonPresionado);
                this.panelEdicion.getPanelImagen().setModoDibujo(0);
            }
            
        }
    }
}
