/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Edicion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author HoLeX
 */
public class PanelColorMiniatura extends JPanel
{
    private Color colorPanel;
    private JPanel panelInterior;
    private PanelColores panelColores;
    
    private Color colorDefault;
    
    public PanelColorMiniatura(int r, int g, int b, PanelColores panelColores)
    {
        this.panelColores = panelColores;
        this.colorDefault = this.getBackground();
        this.colorPanel = new Color(r,g,b);
        this.panelInterior = new JPanel();
        this.panelInterior.setBackground(colorPanel);
        this.panelInterior.setPreferredSize(new Dimension(20, 20));
        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        this.panelInterior.setToolTipText(r+", "+g+", "+b);
        this.panelInterior.addMouseListener(new MouseListener() 
        {

            @Override
            public void mouseClicked(MouseEvent e)
            {
            
            }

            @Override
            public void mousePressed(MouseEvent e)
            {
                eventoDeteccion();
                eventoPintar();
            }

            @Override
            public void mouseReleased(MouseEvent e)
            {
            
            }

            @Override
            public void mouseEntered(MouseEvent e)
            {
             
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                
            }
        });
        this.setLayout(new BorderLayout());
        this.add(panelInterior, BorderLayout.CENTER);
    }

    public void eventoPintar()
    {
        this.panelColores.pintarColorActual(this.colorPanel);
    }
    
    public void eventoDeteccion()
    {
        this.panelColores.detectarPanel(this);
    }
    
    public Color getColorPanel()
    {
        return colorPanel;
    }

    public void isSeleccionado(boolean seleccion)
    {
        if(seleccion)
        {
            this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        }
        else
        {
            this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        }
    }
    
    public void setColorPanel(Color colorPanel)
    {
        this.colorPanel = colorPanel;
        this.panelInterior.setBackground(colorPanel);
        this.panelInterior.setToolTipText(colorPanel.getRed()+", "+colorPanel.getGreen()+", "+colorPanel.getBlue());
        this.panelInterior.revalidate();
        this.panelInterior.repaint();
    }
    
}
