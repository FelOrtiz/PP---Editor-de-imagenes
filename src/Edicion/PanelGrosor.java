/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Edicion;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 *
 * @author HoLeX
 */
public class PanelGrosor extends JPanel implements ActionListener
{
    private JPanel panelPrincipal;

    private JComboBox combo;
    
    private PanelFormas panelHerramientasDibujo;
    
    public PanelGrosor(PanelFormas panelHerramientasDibujo)
    {
        this.panelHerramientasDibujo = panelHerramientasDibujo;
        
        this.setLayout(new BorderLayout());
        TitledBorder borde = BorderFactory.createTitledBorder(" Grosor ");
        borde.setTitleJustification(TitledBorder.CENTER);
        this.setBorder(borde);
        
        this.panelPrincipal = new JPanel();
        this.panelPrincipal.setLayout(new BoxLayout(this.panelPrincipal, BoxLayout.Y_AXIS));
        this.panelPrincipal.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        Object[] items = 
        {
            new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/1.png"))),
            new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/2.png"))),
            new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/3.png"))),
            new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/4.png"))),
            new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/5.png")))  
        };
        this.combo = new JComboBox(items);
        this.combo.setSelectedIndex(3);
        this.combo.addActionListener(this);
        
        this.panelPrincipal.add(combo);
        this.add(panelPrincipal, BorderLayout.CENTER);
    }

    public void desactivarGrosor(boolean desactivar)
    {
        this.combo.setEnabled(desactivar);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == this.combo)
        {
            if(this.combo.getSelectedIndex() == 0)
            {
                this.panelHerramientasDibujo.getPanelFiltro().getPanelEdicion().getPanelImagen().setTamanioBorde(2);
            }
            else if(this.combo.getSelectedIndex() == 1)
            {
                this.panelHerramientasDibujo.getPanelFiltro().getPanelEdicion().getPanelImagen().setTamanioBorde(3);
            }
            else if(this.combo.getSelectedIndex() == 2)
            {
                this.panelHerramientasDibujo.getPanelFiltro().getPanelEdicion().getPanelImagen().setTamanioBorde(5);
            }
            else if(this.combo.getSelectedIndex() == 3)
            {
                this.panelHerramientasDibujo.getPanelFiltro().getPanelEdicion().getPanelImagen().setTamanioBorde(7);
            }
            else if(this.combo.getSelectedIndex() == 4)
            {
                this.panelHerramientasDibujo.getPanelFiltro().getPanelEdicion().getPanelImagen().setTamanioBorde(9);
            }
        }
    }
}
