/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Edicion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author HoLeX
 */
public class PanelZoom extends JPanel implements ChangeListener
{
    private JSlider sliderZoom;
    private JTextField field;
    private PanelEdicion panelEdicion;
    
    private JSlider sliderRotacion;
    private JTextField fieldRotacion;
    
    public PanelZoom(int init, PanelEdicion panelEdicion)
    {
        this.panelEdicion = panelEdicion;
        super.setLayout(new BorderLayout());
        this.sliderZoom = new JSlider(JSlider.HORIZONTAL, 10, 300, init);
        this.sliderZoom.setMajorTickSpacing(25);
        this.sliderZoom.setPaintTicks(true);
        this.sliderZoom.setPaintTrack(true);
        this.sliderZoom.addChangeListener(this);
        this.sliderZoom.setFocusable(false);
        this.field = new JTextField(" 100% ");
        this.field.setEditable(false);

        this.sliderRotacion = new JSlider(0,360,0);
        this.sliderRotacion.setMajorTickSpacing(90);
        this.sliderRotacion.setPaintTicks(true);
        this.sliderRotacion.addChangeListener(this);
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
        
        this.fieldRotacion = new JTextField(" 0 °");
        this.fieldRotacion.setEditable(false);
        this.fieldRotacion.setPreferredSize(new Dimension(41, 20));
        
        panel2.add(new JLabel("Rotación Figura: "));
        panel2.add(Box.createRigidArea(new Dimension(5,0)));
        panel2.add(sliderRotacion);
        panel2.add(Box.createRigidArea(new Dimension(5,0)));
        panel2.add(fieldRotacion);
        
        panel.add(panel2, BorderLayout.WEST);
        
        this.add(panel, BorderLayout.WEST);
        
        JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
        panel3.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        panel3.add(new JLabel("Redimensión: "));
        panel3.add(Box.createRigidArea(new Dimension(5,0)));
        panel3.add(sliderZoom);
        panel3.add(Box.createRigidArea(new Dimension(5,0)));
        panel3.add(field);
        
        this.add(panel3, BorderLayout.EAST);
        
        this.activarRotacion(false);
    }
    
    public void setInit(int init)
    {
        this.sliderZoom.setValue(init);
    }
    
    public int getInit()
    {
        return this.sliderZoom.getValue();
    }

    public void activarRotacion(boolean valor)
    {
        this.sliderRotacion.setEnabled(valor);
        this.fieldRotacion.setText(" 0 °");
        this.fieldRotacion.setEnabled(valor);
    }
    
    public void setInitRotacion(int init)
    {
        this.sliderRotacion.setValue(init);
    }
    
    public int getInitRotacion()
    {
        return this.sliderRotacion.getValue();
    }
    
    @Override
    public void stateChanged(ChangeEvent e)
    {        
        if(e.getSource() == this.sliderZoom)
        {
            this.panelEdicion.ZoomearImagen(this.sliderZoom.getValue());
            this.field.setText(" "+this.sliderZoom.getValue()+"% ");
            this.field.setPreferredSize(new Dimension(41, 20));
        }
        else if(e.getSource() == this.sliderRotacion)
        {
            if(this.panelEdicion.getPanelImagen().getFiguraSeleccionada()!= null)
            {
                this.panelEdicion.getPanelImagen().getFiguraSeleccionada().setRotacion(sliderRotacion.getValue());
            }
            this.panelEdicion.getPanelImagen().repaint();
            this.fieldRotacion.setText(" "+this.sliderRotacion.getValue()+" °");
            this.fieldRotacion.setPreferredSize(new Dimension(41, 20));
        }
    }
}
