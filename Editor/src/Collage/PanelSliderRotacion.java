/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Collage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author HoLeX
 */
public class PanelSliderRotacion extends JPanel implements ChangeListener
{
    private JSlider sliderRotacion;
    private JTextField fieldRotacion;
    private JLabel label;
    
    private BarraHerramientas barra;
    
    public PanelSliderRotacion(BarraHerramientas barra)
    {
        this.barra = barra;
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        
        this.sliderRotacion = new JSlider(0, 360, 0);
        this.sliderRotacion.createStandardLabels(90);
        this.sliderRotacion.setMajorTickSpacing(90);
        this.sliderRotacion.setMinorTickSpacing(45);
        this.sliderRotacion.setPaintLabels(true);
        this.sliderRotacion.setPaintTicks(true);
       
        this.sliderRotacion.addChangeListener(this);
        this.sliderRotacion.setPaintTrack(true);
        
        this.label = new JLabel("Rotación: ");
        
        this.fieldRotacion = new JTextField();
        this.fieldRotacion.setEditable(false);
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10,0,10,5));
        panel.add(fieldRotacion, BorderLayout.CENTER);
        
        this.add(new JSeparator(JSeparator.VERTICAL));
        this.add(Box.createRigidArea(new Dimension(5,0)));
        this.add(label);
        this.add(Box.createRigidArea(new Dimension(5,0)));
        this.add(sliderRotacion);
        this.add(Box.createRigidArea(new Dimension(5,0)));
        this.add(panel);
        
        this.fieldRotacion.setPreferredSize(new Dimension(41, 20));
        this.setRotacionEditable(false);
    }

    public void setInit(int valor)
    {
        this.sliderRotacion.setValue(valor);
    }
    
    public void setRotacionEditable(boolean activar)
    {
        this.sliderRotacion.setEnabled(activar);
    }
    
    public void setRotacion(String text)
    {
        this.fieldRotacion.setText(text);
        this.fieldRotacion.setAlignmentX(JTextField.CENTER_ALIGNMENT);
    }
    
    @Override
    public void stateChanged(ChangeEvent e)
    {      
        this.setRotacion(sliderRotacion.getValue()+" °");
        this.barra.getPanelAreaTrabajo().getImagenSeleccionada().setRotacion(sliderRotacion.getValue());
        this.barra.getPanelAreaTrabajo().repaint();
    }
}
