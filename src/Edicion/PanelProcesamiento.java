/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Edicion;

import cl.visual.Filtro;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 *
 * @author HoLeX
 */
public class PanelProcesamiento extends JPanel implements ActionListener
{
    private PanelFiltro panelFiltro;
    
    private JPanel panelPrincipal;
    
    private Imagen imagenParaProcesar;
    
    private JPanel panelBox;
    private JComboBox boxFiltros;
    private JPanel panelBotones;
    private JButton botonAplicar;
    private JButton botonReestablecer;
    
    private Stack desHacerImagenes;
    private Stack reHacerImagenes;
    
    private boolean tieneFiltro;
    
    public PanelProcesamiento(PanelFiltro panelFiltro)
    {
        super.setLayout(new BorderLayout());
        this.panelFiltro = panelFiltro; 
        
        this.panelPrincipal = new JPanel();
        this.panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        TitledBorder borde = BorderFactory.createTitledBorder(" Filtros ");
        borde.setTitleJustification(TitledBorder.CENTER);        
        this.panelPrincipal.setBorder(borde);
        
        Object[] filtros = 
        {
            "Sepia", "Grises", "Binarización", "Sobel", "Suavizado", "Agua", "Enroscar"
        };
        
        this.panelBox = new JPanel(new BorderLayout());
        this.panelBox.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        this.boxFiltros = new JComboBox(filtros);
        this.boxFiltros.setSelectedIndex(-1);
        
        this.panelBox.add(boxFiltros);
        
        this.panelBotones = new JPanel(new BorderLayout());
        this.panelBotones.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
        
        this.botonAplicar = new JButton("Aplicar");
        this.botonReestablecer = new JButton("Restablecer");
        
        this.panelBotones.add(botonAplicar, BorderLayout.CENTER);
        this.panelBotones.add(botonReestablecer, BorderLayout.EAST);
        
        this.botonAplicar.addActionListener(this);
        this.botonReestablecer.addActionListener(this);
        
        this.panelPrincipal.add(panelBox);
        this.panelPrincipal.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelPrincipal.add(panelBotones);
        this.panelPrincipal.add(Box.createRigidArea(new Dimension(0,5)));
        
        this.add(panelPrincipal, BorderLayout.NORTH);
        
        this.desHacerImagenes = new Stack();
        this.reHacerImagenes = new Stack();
        
        this.tieneFiltro = false;
    }  
    
    public void setImagenFiltro(Imagen imagen)
    {
        this.imagenParaProcesar = imagen;
    }
    
    public void desactivarFiltros(boolean desactivar)
    {
        this.boxFiltros.setEnabled(desactivar);
        this.botonAplicar.setEnabled(desactivar);
        this.botonReestablecer.setEnabled(desactivar);
        this.botonAplicar.setEnabled(desactivar);
    }
    
    public void agregarFiltro(final Filtro filtro)
    {
        JButton boton = new JButton("Aplicar");
        boton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                imagenParaProcesar = panelFiltro.getPanelEdicion().getImagen();
                desHacerImagenes.push(imagenParaProcesar);
                imagenParaProcesar = new Imagen(new ImageIcon(filtro.filtro(imagenParaProcesar.imageIconToBufferedImage(imagenParaProcesar.getImagen().getImage()))));
                panelFiltro.getPanelEdicion().setImagen(imagenParaProcesar, panelFiltro.getPanelEdicion().getRutaActual());
                tieneFiltro = true;
            }
        });
        this.agregarBoton(filtro.getName(), boton);
    }
    
    public void agregarBoton(String nombre, JButton boton)
    {
        JPanel panel = new JPanel(new BorderLayout());
        TitledBorder borde = BorderFactory.createTitledBorder(" Filtros Importado: "+nombre);
        borde.setTitleJustification(TitledBorder.CENTER);        
        panel.setBorder(borde);        
        
        JPanel panel2 = new JPanel(new BorderLayout());
        panel2.add(boton);
        
        panel.add(panel2);

        this.panelPrincipal.add(panel);
        this.panelPrincipal.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelPrincipal.revalidate();
    }

    public Imagen getImagenParaProcesar()
    {
        return imagenParaProcesar;
    }

    
    public void desHacer()
    {
        if(this.desHacerImagenes.size() > 0)
        {
            if( this.panelFiltro.getPanelEdicion().getImagen()!=null)
            {
                this.reHacerImagenes.push(this.panelFiltro.getPanelEdicion().getImagen());
            }
            
            Imagen imagen = (Imagen) this.desHacerImagenes.pop();
            //this.reHacerImagenes.push(imagen);
            this.panelFiltro.getPanelEdicion().setImagen(imagen, this.panelFiltro.getPanelEdicion().getRutaActual());
            
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No se puede deshacer");
        }
    }
    
    public void reHacer()
    {
        if(this.reHacerImagenes.size() > 0)
        {
            if( this.panelFiltro.getPanelEdicion().getImagen()!=null)
            {
                this.desHacerImagenes.push(this.panelFiltro.getPanelEdicion().getImagen());
            }
            Imagen imagen = (Imagen) this.reHacerImagenes.pop();
            //this.desHacerImagenes.push(imagen);
            this.panelFiltro.getPanelEdicion().setImagen(imagen, this.panelFiltro.getPanelEdicion().getRutaActual());
            
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No se puede rehacer");
        }
    }

    public void setDesHacerImagenes(Stack desHacerImagenes)
    {
        this.desHacerImagenes = desHacerImagenes;
    }

    public Stack getDesHacerImagenes()
    {
        return desHacerImagenes;
    }

    public Stack getReHacerImagenes()
    {
        return reHacerImagenes;
    }

    public void setReHacerImagenes(Stack reHacerImagenes)
    {
        this.reHacerImagenes = reHacerImagenes;
    }

    public boolean isTieneFiltro()
    {
        return tieneFiltro;
    }

    public void setTieneFiltro(boolean tieneFiltro)
    {
        this.tieneFiltro = tieneFiltro;
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == this.botonAplicar)
        {
            if(this.boxFiltros.getSelectedIndex() == -1)
            {
                JOptionPane.showMessageDialog(null, "Primero debe seleccionar un tipo de filtro", "Atencion", JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {       
                this.desHacerImagenes.push(this.imagenParaProcesar);
                Imagen imagen = null;

                if(this.boxFiltros.getSelectedItem().equals("Sepia"))
                {
                    imagen = new Imagen(this.imagenParaProcesar.sepia(imagenParaProcesar.getImagen()));
                }
                if(this.boxFiltros.getSelectedItem().equals("Suavizado"))
                {
                    
                    imagen = new Imagen(this.imagenParaProcesar.suavizado(imagenParaProcesar.getImagen()));
                }
                if(this.boxFiltros.getSelectedItem().equals("Binarización"))
                {
                    String valor = JOptionPane.showInputDialog(null, "Ingrese el valor del umbral: \n Debe ser entre 130 y 200", "Valor de umbral", JOptionPane.QUESTION_MESSAGE);
                    int umbral = 0;
                    
                    if(valor != null)
                    {
                        try
                        {
                            umbral = Integer.parseInt(valor);
                            
                            while(umbral < 130 || umbral > 200)
                            {
                                valor = JOptionPane.showInputDialog(null, "Ingrese el valor del umbral: \n Debe ser entre 130 y 200", "Valor de umbral", JOptionPane.QUESTION_MESSAGE);
                                umbral = 0;
                                
                                if(valor != null)
                                {              
                                    try
                                    {
                                        umbral = Integer.parseInt(valor);   
                                    }
                                    catch (NumberFormatException ev)
                                    {
                            
                                    }
                                }                  
                            }
                        }
                        catch (NumberFormatException ev)
                        {
                            
                        }
                    }
                    if(umbral != 0)
                    {
                        imagen = new Imagen(this.imagenParaProcesar.binarizacion(umbral, imagenParaProcesar.getImagen()));
                    }
                }
                if(this.boxFiltros.getSelectedItem().equals("Sobel"))
                {
                   imagen = new Imagen(this.imagenParaProcesar.sobel(imagenParaProcesar.getImagen()));
                }
                if(this.boxFiltros.getSelectedItem().equals("Grises"))
                {
                    imagen = new Imagen(this.imagenParaProcesar.grises(imagenParaProcesar.getImagen()));
                }
                if(this.boxFiltros.getSelectedItem().equals("Agua"))
                {
                    imagen = new Imagen(this.imagenParaProcesar.agua(imagenParaProcesar.getImagen()));
                }
                if(this.boxFiltros.getSelectedItem().equals("Enroscar"))
                {
                    imagen = new Imagen(this.imagenParaProcesar.enroscar(imagenParaProcesar.getImagen()));
                }      
                this.tieneFiltro = true;
                this.panelFiltro.getPanelEdicion().setImagen(imagen, this.panelFiltro.getPanelEdicion().getRutaActual());
            }
        }
        else if(e.getSource() == this.botonReestablecer)
        {
            this.panelFiltro.getPanelEdicion().setImagen(new Imagen(new ImageIcon(this.panelFiltro.getPanelEdicion().getRutaActual())), this.panelFiltro.getPanelEdicion().getRutaActual());
            this.tieneFiltro = false;
            this.desHacerImagenes.clear();
            this.reHacerImagenes.clear();
        }
    }
}