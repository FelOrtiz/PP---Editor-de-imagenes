/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Administracion;

import Edicion.Imagen;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 *
 * @author HoLeX
 */
public class PanelCentral extends JPanel
{
    private JPanel panel;
    private Imagen imagen;
    
    public PanelCentral()
    {
        this.setLayout(new BorderLayout());
        this.panel = new JPanel(new BorderLayout());
    }  
    
    /*
    Funcion que cambia la imagen del panelAdministrar
    */
    public void setImagen(Imagen imagen)
    {
        this.panel.removeAll();
        this.panel.revalidate();
        this.removeAll();
        this.revalidate();
        this.panel.add(imagen, BorderLayout.CENTER);
        this.add(panel, BorderLayout.CENTER);
        
        this.imagen = imagen;
    }
    
    /*
    Funcion que muestra el mensaje de error en el panelAdministrar
    */
    public void setError()
    {
        this.panel.removeAll();
        this.panel.revalidate();
        this.removeAll();
        this.revalidate();        
        JLabel label = new JLabel("El archivo seleccionado no es una imagen, por favor intente nuevamente.");
        label.setHorizontalAlignment(JLabel.CENTER);
        this.panel.add(label, BorderLayout.CENTER);
        this.add(panel, BorderLayout.CENTER);
    }
    
    /*
    Funcion que muestra un mensaje vacio en el panelAdministrar
    */
    public void mostrarNada()
    {
        this.panel.removeAll();
        this.panel.revalidate();
        this.removeAll();
        this.revalidate();  
        JLabel label = new JLabel("");
        label.setHorizontalAlignment(JLabel.CENTER);
        this.panel.add(label, BorderLayout.CENTER);
        this.add(panel, BorderLayout.CENTER);        
    }

    public Imagen getImagen()
    {
        return imagen;
    }

}
