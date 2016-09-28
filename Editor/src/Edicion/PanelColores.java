/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Edicion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.colorchooser.AbstractColorChooserPanel;

/**
 *
 * @author HoLeX
 */
public class PanelColores extends JPanel implements ActionListener
{
    private JPanel panelPrincipal;
    
    private PanelColorMiniatura rojo;
    private PanelColorMiniatura azul;
    private PanelColorMiniatura verde;
    private PanelColorMiniatura amarillo;
    private PanelColorMiniatura gris;
    private PanelColorMiniatura cafe;
    
    private PanelColorMiniatura rosado;
    private PanelColorMiniatura celeste;
    private PanelColorMiniatura verdeOscuro;
    private PanelColorMiniatura cafeOscuro;
    private PanelColorMiniatura blanco;
    private PanelColorMiniatura negro;
    
    private PanelColorMiniatura magenta;
    private PanelColorMiniatura morado;
    private PanelColorMiniatura cyan;
    private PanelColorMiniatura naranjo;
    private PanelColorMiniatura azulRaro;
    private PanelColorMiniatura fucsia;

    private JButton boton;
    private PanelColorMiniatura colorActualBorde;
    private PanelColorMiniatura colorActualRelleno;
    private JButton botonQuitarRelleno;
    
    private JColorChooser colorChooser;
    private AbstractColorChooserPanel panelRGB;   
    
    private PanelFormas panelFormas;
    private int seleccionPanel;
    
    public PanelColores(PanelFormas panelFormas)
    {
        this.panelFormas = panelFormas;
        this.seleccionPanel = 1;
        this.colorChooser = new JColorChooser(new Color(0,0,0));

        AbstractColorChooserPanel[] paneles = this.colorChooser.getChooserPanels();
        for(AbstractColorChooserPanel panel : paneles)
        {
            if(panel.getDisplayName().equals("RGB"))
            {
                this.panelRGB = panel;
            }  
        }
        
        this.setLayout(new BorderLayout()); 
        TitledBorder borde = BorderFactory.createTitledBorder(" Colores ");
        borde.setTitleJustification(TitledBorder.CENTER);
        this.setBorder(borde);
        this.panelPrincipal = new JPanel();
        this.panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        this.panelPrincipal.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 3));

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
        JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
        JPanel panel5 = new JPanel();
        panel5.setLayout(new BoxLayout(panel5, BoxLayout.X_AXIS));
        JPanel panel6 = new JPanel(new BorderLayout());
        
        this.rojo = new PanelColorMiniatura(255, 0, 0, this);
        this.azul = new PanelColorMiniatura(0, 0, 255, this);
        this.verde = new PanelColorMiniatura(0, 255, 0, this);
        this.amarillo = new PanelColorMiniatura(255, 255, 0, this);
        this.gris = new PanelColorMiniatura(128, 128, 128, this);
        this.cafe = new PanelColorMiniatura(185, 122, 87, this);
        
        this.rosado = new PanelColorMiniatura(255, 128, 255, this);
        this.celeste = new PanelColorMiniatura(0, 255, 255, this);
        this.verdeOscuro = new PanelColorMiniatura(0, 128, 0, this);
        this.cafeOscuro = new PanelColorMiniatura(128, 128, 64, this);
        this.blanco = new PanelColorMiniatura(255, 255, 255, this);
        this.negro = new PanelColorMiniatura(0, 0, 0, this);
        
        this.magenta = new PanelColorMiniatura(128, 0, 128, this);
        this.morado = new PanelColorMiniatura(64, 0, 128, this);
        this.cyan = new PanelColorMiniatura(14, 184, 180, this);
        this.naranjo = new PanelColorMiniatura(255, 128, 0, this);
        this.azulRaro = new PanelColorMiniatura(128, 128, 255, this);
        this.fucsia = new PanelColorMiniatura(255, 0, 128, this);  
        
        panel1.add(rojo);
        panel1.add(Box.createRigidArea(new Dimension(5, 0)));
        panel1.add(azul);
        panel1.add(Box.createRigidArea(new Dimension(5, 0)));
        panel1.add(verde);
        panel1.add(Box.createRigidArea(new Dimension(5, 0)));
        panel1.add(amarillo);
        panel1.add(Box.createRigidArea(new Dimension(5, 0)));
        panel1.add(gris);
        panel1.add(Box.createRigidArea(new Dimension(5, 0)));
        panel1.add(cafe);
        
        panel2.add(rosado);
        panel2.add(Box.createRigidArea(new Dimension(5, 0)));
        panel2.add(celeste);
        panel2.add(Box.createRigidArea(new Dimension(5, 0)));
        panel2.add(verdeOscuro);
        panel2.add(Box.createRigidArea(new Dimension(5, 0)));
        panel2.add(cafeOscuro);
        panel2.add(Box.createRigidArea(new Dimension(5, 0)));
        panel2.add(blanco);
        panel2.add(Box.createRigidArea(new Dimension(5, 0)));
        panel2.add(negro);
        
        panel3.add(magenta);
        panel3.add(Box.createRigidArea(new Dimension(5, 0)));
        panel3.add(morado);
        panel3.add(Box.createRigidArea(new Dimension(5, 0)));
        panel3.add(cyan);
        panel3.add(Box.createRigidArea(new Dimension(5, 0)));
        panel3.add(naranjo);
        panel3.add(Box.createRigidArea(new Dimension(5, 0)));
        panel3.add(azulRaro);
        panel3.add(Box.createRigidArea(new Dimension(5, 0)));
        panel3.add(fucsia);
        
        JLabel labelBorde = new JLabel("Borde: ");
        this.colorActualBorde = new PanelColorMiniatura(0, 0, 0, this);
        this.colorActualBorde.isSeleccionado(true);
        
        JLabel labelRelleno = new JLabel("Relleno: ");
        this.colorActualRelleno = new PanelColorMiniatura(this.getBackground().getRed(), this.getBackground().getGreen(), this.getBackground().getBlue(), this);
        
        this.botonQuitarRelleno = new JButton("Quitar");
        this.botonQuitarRelleno.addActionListener(this);
        
        panel5.add(labelBorde);
        panel5.add(colorActualBorde);
        panel5.add(Box.createRigidArea(new Dimension(5, 0)));
        panel5.add(labelRelleno);
        panel5.add(colorActualRelleno);
        panel5.add(Box.createRigidArea(new Dimension(5, 0)));
        panel5.add(botonQuitarRelleno);
        
        this.boton = new JButton("Mas Colores...");
        this.boton.addActionListener(this);
        panel6.add(boton, BorderLayout.CENTER);
        
        this.panelPrincipal.add(panel1);
        this.panelPrincipal.add(Box.createRigidArea(new Dimension(0, 5)));
        this.panelPrincipal.add(panel2);
        this.panelPrincipal.add(Box.createRigidArea(new Dimension(0, 5)));
        this.panelPrincipal.add(panel3);
        this.panelPrincipal.add(Box.createRigidArea(new Dimension(0, 5)));
        this.panelPrincipal.add(panel5);
        this.panelPrincipal.add(Box.createRigidArea(new Dimension(0, 5)));
        this.panelPrincipal.add(panel6);
        
        this.add(panelPrincipal, BorderLayout.CENTER);
    }  

    public void pintarColorActual(Color color)
    {
        if(this.seleccionPanel == 1)
        {
            this.colorActualBorde.setColorPanel(color);
        }
        else
        {
            this.colorActualRelleno.setColorPanel(color);
        }
 
        this.panelFormas.getPanelFiltro().getPanelEdicion().getPanelImagen().setColorBorde(colorActualBorde.getColorPanel());
        
        if( !colorActualRelleno.getColorPanel().equals(this.getBackground()))
        {
            this.panelFormas.getPanelFiltro().getPanelEdicion().getPanelImagen().setColorRelleno(colorActualRelleno.getColorPanel());        
        }
        else
        {
            this.panelFormas.getPanelFiltro().getPanelEdicion().getPanelImagen().setColorRelleno(null);
        }
    }
    
    public void detectarPanel(PanelColorMiniatura panelMiniatura)
    {
        if(panelMiniatura.equals(this.colorActualBorde))
        {
            this.seleccionPanel = 1;
            this.colorActualBorde.isSeleccionado(true);
            this.colorActualRelleno.isSeleccionado(false);
        }
        else if(panelMiniatura.equals(this.colorActualRelleno))
        {
            this.seleccionPanel = 2;
            this.colorActualBorde.isSeleccionado(false);
            this.colorActualRelleno.isSeleccionado(true);            
        }
    }

    public void desactivarColores(boolean desactivar)
    {
        this.boton.setEnabled(desactivar);
        this.botonQuitarRelleno.setEnabled(desactivar);
    }

    public Color getColorActualBorde()
    {
        return colorActualBorde.getColorPanel();
    }

    public Color getColorActualRelleno()
    {
        return colorActualRelleno.getColorPanel();
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == boton)
        {
            this.panelRGB.getColorSelectionModel().setSelectedColor(this.colorActualBorde.getColorPanel());
            JOptionPane.showMessageDialog(null, panelRGB, "Seleccione el color..." ,JOptionPane.PLAIN_MESSAGE);
        
            Color color = this.panelRGB.getColorSelectionModel().getSelectedColor();
            this.pintarColorActual(new Color(color.getRed(), color.getGreen(), color.getBlue()));
        }
        else if(e.getSource() == this.botonQuitarRelleno)
        {
            this.colorActualRelleno.setColorPanel(this.getBackground());
            this.panelFormas.getPanelFiltro().getPanelEdicion().getPanelImagen().setColorRelleno(null);
        }
    }
    
}
