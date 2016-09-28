/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Edicion;

import Visual.VentanaPrincipal;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author HoLeX
 */
public class PanelMiniatura extends JPanel
{
    private PanelEdicion panelEdicion;
    private Imagen imagen;
    private final Color defaultColor;
    private String ruta;
    private int indice;
    
    public PanelMiniatura(String ruta, PanelEdicion panelEdicion, int indice) 
    {
        this.panelEdicion = panelEdicion;
        this.ruta = ruta;
        this.indice = indice;
        super.setLayout(new BorderLayout());
        super.setPreferredSize(new Dimension(100,100));
        Border borde = BorderFactory.createEmptyBorder(0,5,0,5);//top, left, bottom, right
        super.setBorder(borde);
        
        if(ruta.endsWith("bmp") || ruta.endsWith("BMP"))
        {
            BufferedImage imagen = null;
            try {
                imagen = ImageIO.read(new File(ruta));
            }
            catch (IOException ex) {
                Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.imagen = new Imagen(new ImageIcon(imagen));
        }
        else
        {
            this.imagen = new Imagen(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ruta)));
        }                
        
        this.defaultColor = super.getBackground();
        
        this.addMouseListener(new MouseListener() 
        {

            @Override
            public void mouseClicked(MouseEvent e)
            {
                
            }

            @Override
            public void mousePressed(MouseEvent e)
            {
                if(getPanelEdicion().getPanelImagen().getFiguras().size() > 0 && !getPanelEdicion().getPanelTiraImagenes().getMiniaturas().get(getIndice()).getRuta().equals(getPanelEdicion().getRutaActual()))
                {
                    int opcion = JOptionPane.showConfirmDialog(null, "Tiene figuras dibujadas, desea guardar la imagen y sus cambios?", "Atencion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
               
                    if(opcion == 0)
                    {
                        getPanelEdicion().getPanelImagen().guardarImagen(getPanelEdicion().getRutaActual());
                        getPanelEdicion().getPanelTiraImagenes().getMiniaturas().get(getIndice()).setRuta(getPanelEdicion().getRutaActual());
                        getPanelEdicion().getPanelImagen().setFiguraSeleccionada(null);
                        getPanelEdicion().getPanelImagen().repaint();
                        getPanelEdicion().getVentana().repintarPanelEdicion(false);
                    }
                    else
                    {
                        getPanelEdicion().getPanelImagen().getFiguras().clear();
                        getPanelEdicion().getPanelImagen().setFiguraSeleccionada(null);
                        getPanelEdicion().getPanelImagen().getReHacer().clear();
                        getPanelEdicion().getPanelImagen().getDesHacer().clear();
                        getPanelEdicion().getPanelImagen().repaint();
                    }
                }
                if(getPanelEdicion().getPanelFiltro().getPanelProcesamiento().isTieneFiltro() && !getPanelEdicion().getPanelTiraImagenes().getMiniaturas().get(getIndice()).getRuta().equals(getPanelEdicion().getRutaActual()))
                {
                    int opcion = JOptionPane.showConfirmDialog(null, "Tiene filtro aplicado, desea guardar la imagen y sus cambios?", "Atencion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    
                    if(opcion == 0)
                    {
                        getPanelEdicion().getPanelImagen().guardarImagen(getPanelEdicion().getRutaActual());
                        getPanelEdicion().getPanelTiraImagenes().getMiniaturas().get(getIndice()).setRuta(getPanelEdicion().getRutaActual()); 
                    }
                    else
                    {
                        getPanelEdicion().setImagen(new Imagen(new ImageIcon(getPanelEdicion().getRutaActual())), getPanelEdicion().getRutaActual());
                        getPanelEdicion().getPanelFiltro().getPanelProcesamiento().getReHacerImagenes().clear();
                        getPanelEdicion().getPanelFiltro().getPanelProcesamiento().getDesHacerImagenes().clear();
                    }
                    getPanelEdicion().getPanelFiltro().getPanelProcesamiento().setTieneFiltro(false);
                }
                else
                {
                    getPanelEdicion().setImagen(imagen, getRuta());
                }   
                getPanelEdicion().getPanelImagen().setModoDibujo(0);
            }

            @Override
            public void mouseReleased(MouseEvent e)
            {
            }

            @Override
            public void mouseEntered(MouseEvent e)
            {
                pintarFondo(true);                
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                pintarFondo(false);
            }
        });
        
        boolean flag;
        
        if(ruta.endsWith("gif") || ruta.endsWith("GIF"))
        {
            flag = true;
        }
        else
        {
            flag = false;
        }
        
        this.add(new Imagen(this.imagen.EscalarImagen(flag)));
    }

    public PanelEdicion getPanelEdicion()
    {
        return panelEdicion;
    }

    public void setPanelEdicion(PanelEdicion panelEdicion)
    {
        this.panelEdicion = panelEdicion;
    }

    public String getRuta()
    {
        return ruta;
    }

    public void setRuta(String ruta)
    {
        this.ruta = ruta;
    }

    public int getIndice()
    {
        return indice;
    }
    
    private void pintarFondo(boolean flag)
    {       
        if(flag)
        {
            super.setBackground(Color.LIGHT_GRAY);
        }
        else if(!flag)
        {
            super.setBackground(defaultColor);
        }
    }
}