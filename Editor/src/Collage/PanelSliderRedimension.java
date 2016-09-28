/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Collage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
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
public class PanelSliderRedimension extends JPanel implements ChangeListener
{
    private JSlider sliderRedimension;
    private JTextField fieldRedimension;
    private JLabel label;
    
    private BarraHerramientas barra;
    
    public PanelSliderRedimension(BarraHerramientas barra)
    {
        this.barra = barra;
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        
        this.sliderRedimension = new JSlider(1, 200, 1);
        this.sliderRedimension.createStandardLabels(100);
        this.sliderRedimension.setMajorTickSpacing(40);
        this.sliderRedimension.setPaintLabels(true);
        this.sliderRedimension.setPaintTicks(true);
       
        this.sliderRedimension.addChangeListener(this);
        this.sliderRedimension.setPaintTrack(true);
        
        this.label = new JLabel("Redimension: ");
        
        this.fieldRedimension = new JTextField();
        this.fieldRedimension.setEditable(false);
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
        panel.add(fieldRedimension, BorderLayout.CENTER);
        
        this.add(new JSeparator(JSeparator.VERTICAL));
        this.add(Box.createRigidArea(new Dimension(5,0)));
        this.add(label);
        this.add(Box.createRigidArea(new Dimension(5,0)));
        this.add(sliderRedimension);
        this.add(Box.createRigidArea(new Dimension(5,0)));
        this.add(panel);
        
        this.fieldRedimension.setPreferredSize(new Dimension(41, 20));
        this.setRedimensionEditable(false);
    }

    public void setInit(int valor)
    {
        this.sliderRedimension.setValue(valor);
    }
    
    public void setComienzo(int valor)
    {
        this.sliderRedimension.setMinimum(valor);
    }
    
    public void setRedimensionEditable(boolean activar)
    {
        this.sliderRedimension.setEnabled(activar);
    }
    
    public void setRedimension(String text)
    {
        this.fieldRedimension.setText(text);
        this.fieldRedimension.setAlignmentX(JTextField.CENTER);
    } 
    
    public BufferedImage Zoom(BufferedImage file, int valor)
    {
        int ancho = file.getWidth();
        int alto = file.getHeight();
        
        double porcentaje = (double)((double)valor/(double)100);
        
        Image image = null;
        
        if(ancho > 20 || alto > 20)
        {
            int nuevoAncho = (int) Math.round((double)ancho*(double)porcentaje);
            int nuevoAlto = (int) Math.round((double)alto*(double)porcentaje);
        
            image = file.getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);
            
            return this.barra.getPanelAreaTrabajo().imageIconToBufferedImage(image);            
        }
        else
        {        
            return file;
        }
    }        
    
    public void redimension(int valor)
    {
        BufferedImage imagenOriginal = this.barra.getPanelAreaTrabajo().getImagenSeleccionada().getImagenOriginal();
        if(this.barra.getPanelAreaTrabajo().getImagenSeleccionada().getPorcentajeRedimension() != valor)
        {
            BufferedImage imagen = this.Zoom(imagenOriginal, valor);
            
            this.barra.getPanelAreaTrabajo().getImagenSeleccionada().setImagen(imagen);
            this.barra.getPanelAreaTrabajo().getImagenSeleccionada().setWidth(imagen.getWidth());
            this.barra.getPanelAreaTrabajo().getImagenSeleccionada().setHeight(imagen.getHeight());
            this.barra.getPanelAreaTrabajo().getImagenSeleccionada().setPorcentajeRedimension(valor);
            
            this.barra.getPanelAreaTrabajo().repaint();
            this.barra.getPanelAreaTrabajo().repaint();
            this.barra.getPanelAreaTrabajo().repaint(); 
        }
    }
    
    @Override
    public void stateChanged(ChangeEvent e)
    {        
        this.redimension(sliderRedimension.getValue());
        this.setRedimension(this.sliderRedimension.getValue()+" %");
    }
}
