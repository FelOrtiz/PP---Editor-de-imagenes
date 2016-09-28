/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Edicion;

import Figuras.Linea;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 *
 * @author HoLeX
 */
public class PanelHerramientaLinea extends JPanel implements ActionListener
{
    private JPanel panelPrincipal;
    private JComboBox boxTipoLinea;
    private JComboBox boxTipoCompuesta;
    private JComboBox boxTipoGuion;
    private JComboBox boxFlechaInicio;
    private JComboBox boxFlechaTermino;
    private JLabel labelTipoLinea;
    private JLabel labelTipoGuion;
    private JLabel labelFlechaInicio;
    private JLabel labelFlechaTermino;
    
    private PanelEdicion panelEdicion;
    private PanelFormas panelFormas;
    /*
    1. tipo lapiz
    2. tipo compuesta: lineas dobles paralelas, lineas triples paralelas
    3. tipo guion: solo guion, punto guion, solo punto
    4. tipo de flecha inicial: flecha rellena, flecha vacia, circulo, rombo, cuadrado
    5. tipo de flecha final: flecha rellena, flecha vacia, circulo, rombo, cuadrado
    *** se puede obtener una linea mezclando 2, 3, 4 y 5***
    6. tipo spray
    */
    
    private int valorTipoLinea;
    private int valorTipoGuion;
    private int valorFlechaInicio;
    private int valorFlechaTermino;
    
    public PanelHerramientaLinea(PanelFormas panelHerramientasDibujo, PanelEdicion panelEdicion )
    {
        this.panelFormas = panelHerramientasDibujo;
        this.panelEdicion = panelEdicion;
        
        this.setLayout(new BorderLayout());
        TitledBorder borde = BorderFactory.createTitledBorder(" Herramienta de Línea ");
        borde.setTitleJustification(TitledBorder.CENTER);
        this.setBorder(borde);
        
        this.panelPrincipal = new JPanel();
        this.panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.X_AXIS));
        this.panelPrincipal.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        
        this.labelTipoLinea = new JLabel("Tipo Linea: ");
        this.labelTipoGuion = new JLabel("Tipo Guión: ");
        this.labelFlechaInicio = new JLabel("Conector Inicio: ");
        this.labelFlechaTermino = new JLabel("Conector Término: ");
        
        this.boxTipoLinea = new JComboBox();
        this.boxTipoLinea.addItem("Linea Simple");
        this.boxTipoLinea.addItem("Línea doble paralela");
        this.boxTipoLinea.addItem("Línea triple paralela");
        this.boxTipoLinea.addActionListener(this);
        
        this.boxTipoGuion = new JComboBox();
        this.boxTipoGuion.addItem("Desactivado");
        this.boxTipoGuion.addItem("Sólo guión");
        this.boxTipoGuion.addItem("Guión y punto");
        this.boxTipoGuion.addItem("Sólo punto");
        this.boxTipoGuion.addActionListener(this);
        
        this.boxFlechaInicio = new JComboBox();
        this.boxFlechaInicio.addItem("Ninguno");
        this.boxFlechaInicio.addItem("Flecha rellena");
        this.boxFlechaInicio.addItem("Fleca vacía");
        this.boxFlechaInicio.addItem("Círculo");
        this.boxFlechaInicio.addItem("Rombo");
        this.boxFlechaInicio.addItem("Cuadrado");
        this.boxFlechaInicio.addActionListener(this);
        
        this.boxFlechaTermino = new JComboBox();
        this.boxFlechaTermino.addItem("Ninguno");
        this.boxFlechaTermino.addItem("Flecha rellena");
        this.boxFlechaTermino.addItem("Fleca vacía");
        this.boxFlechaTermino.addItem("Círculo");
        this.boxFlechaTermino.addItem("Rombo");
        this.boxFlechaTermino.addItem("Cuadrado");
        this.boxFlechaTermino.addActionListener(this);
        
        
        this.boxTipoGuion.setSelectedIndex(0);
        this.boxFlechaInicio.setSelectedIndex(0);
        this.boxFlechaTermino.setSelectedIndex(0);
        
        this.valorTipoLinea = this.boxTipoLinea.getSelectedIndex();
        this.valorTipoGuion = this.boxTipoGuion.getSelectedIndex();
        this.valorFlechaInicio = this.boxFlechaInicio.getSelectedIndex();
        this.valorFlechaTermino = this.boxFlechaTermino.getSelectedIndex();
        
        JPanel panel = new JPanel(new BorderLayout());
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));
        JPanel panelDerecho = new JPanel();
        panelDerecho.setLayout(new BoxLayout(panelDerecho, BoxLayout.Y_AXIS));
        
        panelIzquierdo.add(Box.createRigidArea(new Dimension(0,3)));
        panelIzquierdo.add(labelTipoLinea);
        //panelIzquierdo.add(Box.createRigidArea(new Dimension(0,10)));
        //panelIzquierdo.add(labelTipoCompuesta);
        panelIzquierdo.add(Box.createRigidArea(new Dimension(0,11)));
        panelIzquierdo.add(labelTipoGuion);
        panelIzquierdo.add(Box.createRigidArea(new Dimension(0,11)));
        panelIzquierdo.add(labelFlechaInicio);
        panelIzquierdo.add(Box.createRigidArea(new Dimension(0,11)));
        panelIzquierdo.add(labelFlechaTermino);
        panelIzquierdo.add(Box.createRigidArea(new Dimension(0,5)));
        
        panelDerecho.add(boxTipoLinea);
        panelDerecho.add(Box.createRigidArea(new Dimension(0,5)));
        //panelDerecho.add(boxTipoCompuesta);
        //panelDerecho.add(Box.createRigidArea(new Dimension(0,5)));
        panelDerecho.add(boxTipoGuion);
        panelDerecho.add(Box.createRigidArea(new Dimension(0,5)));
        panelDerecho.add(boxFlechaInicio);
        panelDerecho.add(Box.createRigidArea(new Dimension(0,5)));
        panelDerecho.add(boxFlechaTermino);
        panelDerecho.add(Box.createRigidArea(new Dimension(0,5)));
        
    
        
        panel.add(panelIzquierdo, BorderLayout.WEST);
        panel.add(panelDerecho, BorderLayout.CENTER);
        
        this.panelPrincipal.add(panel);
        this.desactivarHerramientaLinea(false);
        this.add(panelPrincipal, BorderLayout.CENTER);
        
    }

    public void activarOpcionesLapizNormal(boolean activar)
    {
        this.boxTipoGuion.setSelectedIndex(0);
        this.boxFlechaInicio.setSelectedIndex(0);
        this.boxFlechaTermino.setSelectedIndex(0);
        
        this.boxTipoGuion.setEnabled(activar);
        this.boxFlechaInicio.setEnabled(activar);
        this.boxFlechaTermino.setEnabled(activar);
        
        this.valorTipoGuion = this.boxTipoGuion.getSelectedIndex();
        this.valorFlechaInicio = this.boxFlechaInicio.getSelectedIndex();
        this.valorFlechaTermino = this.boxFlechaTermino.getSelectedIndex();        
    }
    
    public void desactivarHerramientaLinea(boolean desactivar)
    {
        this.boxTipoLinea.setEnabled(desactivar);
        this.boxTipoGuion.setEnabled(desactivar);
        this.boxFlechaInicio.setEnabled(desactivar);
        this.boxFlechaTermino.setEnabled(desactivar);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == this.boxTipoLinea)
        {
            this.valorTipoLinea = this.boxTipoLinea.getSelectedIndex();
            this.panelEdicion.getPanelImagen().configurarLinea(valorTipoLinea, valorTipoGuion, valorFlechaInicio, valorFlechaTermino);
            if( this.panelEdicion.getPanelImagen().getFiguraSeleccionada()!=null 
                    && this.panelEdicion.getPanelImagen().getFiguraSeleccionada() instanceof Linea )
            {
                Linea l = (Linea)this.panelEdicion.getPanelImagen().getFiguraSeleccionada();
                l.setTipoLinea( valorTipoLinea );
                this.panelEdicion.getPanelImagen().repaint();
            }
            
        }
        else if(e.getSource() == this.boxTipoGuion)
        {
            this.valorTipoGuion = this.boxTipoGuion.getSelectedIndex();
            this.panelEdicion.getPanelImagen().configurarLinea(valorTipoLinea, valorTipoGuion, valorFlechaInicio, valorFlechaTermino);
            if( this.panelEdicion.getPanelImagen().getFiguraSeleccionada()!=null 
                    && this.panelEdicion.getPanelImagen().getFiguraSeleccionada() instanceof Linea )
            {
                Linea l = (Linea)this.panelEdicion.getPanelImagen().getFiguraSeleccionada();
                l.setTipoGuion(valorTipoGuion);
                this.panelEdicion.getPanelImagen().repaint();
            }
        }
        else if(e.getSource() == this.boxFlechaInicio)
        {
            this.valorFlechaInicio = this.boxFlechaInicio.getSelectedIndex();
            this.panelEdicion.getPanelImagen().configurarLinea(valorTipoLinea, valorTipoGuion, valorFlechaInicio, valorFlechaTermino);
            if( this.panelEdicion.getPanelImagen().getFiguraSeleccionada()!=null 
                    && this.panelEdicion.getPanelImagen().getFiguraSeleccionada() instanceof Linea )
            {
                Linea l = (Linea)this.panelEdicion.getPanelImagen().getFiguraSeleccionada();
                l.setTipoConectorInicio(valorFlechaInicio);
                this.panelEdicion.getPanelImagen().repaint();
            }
        
        }
        else if(e.getSource() == this.boxFlechaTermino)
        {
            this.valorFlechaTermino = this.boxFlechaTermino.getSelectedIndex();
            this.panelEdicion.getPanelImagen().configurarLinea(valorTipoLinea, valorTipoGuion, valorFlechaInicio, valorFlechaTermino);
            if( this.panelEdicion.getPanelImagen().getFiguraSeleccionada()!=null 
                    && this.panelEdicion.getPanelImagen().getFiguraSeleccionada() instanceof Linea )
            {
                Linea l = (Linea)this.panelEdicion.getPanelImagen().getFiguraSeleccionada();
                l.setTipoConectorFinal(valorFlechaTermino);
                this.panelEdicion.getPanelImagen().repaint();
            }
        }
        
        this.panelEdicion.getVentana().setPreferredSize(new Dimension(this.panelEdicion.getVentana().getWidth()+1,this.panelEdicion.getVentana().getHeight()+1));
        this.panelEdicion.getVentana().setPreferredSize(new Dimension(this.panelEdicion.getVentana().getWidth()-1,this.panelEdicion.getVentana().getHeight()-1));
        this.panelEdicion.getPanelImagen().setPreferredSize(new Dimension(this.panelEdicion.getPanelImagen().getWidth()+1,this.panelEdicion.getPanelImagen().getHeight()+1));
        this.panelEdicion.getPanelImagen().setPreferredSize(new Dimension(this.panelEdicion.getPanelImagen().getWidth()-1,this.panelEdicion.getPanelImagen().getHeight()-1));
        
    }
}
