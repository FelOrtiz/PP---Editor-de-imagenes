/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Collage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author HoLeX
 */
public class BarraHerramientas extends JToolBar implements ActionListener
{
    private JButton botonAdd;
    private JButton botonDelete;
    private JButton botonRecortar;
    private JButton botonAgregarBorde;
    private JButton botonMoverFrente;
    private JButton botonMoverFondo;
    private JButton botonPatron;
    
    private JFileChooser fileChooserImagen;
    
    private PanelAreaTrabajo panelAreaTrabajo;
    
    private ImagenCollage imagenSeleccionada;
    
    private PanelPatrones panelPatrones;
    private PanelPatronPersonalizado panelPatronPersonalizado;

    private PanelSliderRotacion panelRotacion;
    private PanelSliderRedimension panelRedimension;
    
    public BarraHerramientas(PanelAreaTrabajo panelAreaTrabajo)
    {
        this.setFloatable(false);
        this.setLayout(new FlowLayout(FlowLayout.LEADING));
        this.panelAreaTrabajo = panelAreaTrabajo;
        this.botonAdd = new JButton();
        this.botonAdd.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/AddImagen.png"))));
        this.botonAdd.addActionListener(this);
        this.botonAdd.setToolTipText("Agregar una imagen");
        this.botonDelete = new JButton();
        this.botonDelete.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/DeleteImagen.png"))));
        this.botonDelete.addActionListener(this);
        this.botonDelete.setToolTipText("Eliminar imagen");
        this.botonRecortar = new JButton();
        this.botonRecortar.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/RecortarImagen.png"))));
        this.botonRecortar.addActionListener(this);
        this.botonRecortar.setToolTipText("Recortar imagen");
        this.botonAgregarBorde = new JButton();
        this.botonAgregarBorde.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/EditImagen.png"))));
        this.botonAgregarBorde.addActionListener(this);
        this.botonAgregarBorde.setToolTipText("Agregar marco a imagen");
        this.botonMoverFrente = new JButton();
        this.botonMoverFrente.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/MoverFrente.png"))));
        this.botonMoverFrente.addActionListener(this);
        this.botonMoverFrente.setToolTipText("Mover al frente");
        this.botonMoverFondo = new JButton();
        this.botonMoverFondo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/MoverFondo.png"))));
        this.botonMoverFondo.addActionListener(this);
        this.botonMoverFondo.setToolTipText("Mover al fondo");
        this.botonPatron = new JButton();
        this.botonPatron.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/Patron.png"))));
        this.botonPatron.addActionListener(this);
        this.botonPatron.setToolTipText("Crear collage a partir de un patrón");
        
        this.botonDelete.setEnabled(false);
        this.botonRecortar.setEnabled(false);
        this.botonAgregarBorde.setEnabled(false);
        this.botonMoverFondo.setEnabled(false);
        this.botonMoverFrente.setEnabled(false);
        this.botonPatron.setEnabled(false);
        
        this.panelRotacion = new PanelSliderRotacion(this);
        this.panelRedimension = new PanelSliderRedimension(this);
        
        this.add(botonAdd);
        this.add(botonDelete);
        this.add(botonRecortar);
        this.add(botonAgregarBorde);
        this.add(botonMoverFrente);
        this.add(botonMoverFondo);
        this.add(botonPatron);
        this.add(panelRotacion);
        this.add(panelRedimension);
        
        this.fileChooserImagen = new JFileChooser();
        this.fileChooserImagen.setFileSelectionMode(JFileChooser.FILES_ONLY);
        this.fileChooserImagen.setApproveButtonText("Agregar imagen");
        this.fileChooserImagen.setMultiSelectionEnabled(true);
        FileFilter filtro = new FileNameExtensionFilter("Archivos de Imagen", "jpg", "jpeg", "png", "bmp");
        this.fileChooserImagen.setAcceptAllFileFilterUsed(false);
        this.fileChooserImagen.addChoosableFileFilter(filtro);    
        
        this.panelPatrones = new PanelPatrones(this.panelAreaTrabajo);
        
        this.panelPatronPersonalizado = new PanelPatronPersonalizado(this.panelAreaTrabajo);

    }

    public PanelAreaTrabajo getPanelAreaTrabajo()
    {
        return panelAreaTrabajo;
    }

    public ImagenCollage getImagenSeleccionada()
    {
        return imagenSeleccionada;
    }

    public PanelPatrones getPanelPatrones()
    {
        return panelPatrones;
    }
    
    /*
    Activa o desactiva los botones del panel collage
    */
    public void activarBotones(boolean flag, ImagenCollage imagen)
    {
        this.botonDelete.setEnabled(flag);
        this.botonRecortar.setEnabled(flag);
        this.botonAgregarBorde.setEnabled(flag);   
        this.botonMoverFrente.setEnabled(flag);
        this.botonMoverFondo.setEnabled(flag);
        this.botonPatron.setEnabled(flag);
        
        this.panelRotacion.setRotacionEditable(flag);
        this.panelRedimension.setRedimensionEditable(flag);
        
        this.imagenSeleccionada = imagen;
        
        if(flag)//si la imagen es distinto de null
        {
            this.panelRotacion.setRotacion(Math.round(this.panelAreaTrabajo.getImagenSeleccionada().getRotacion())+" °");
            this.panelRotacion.setInit((int) Math.round(this.panelAreaTrabajo.getImagenSeleccionada().getRotacion()));
            this.panelRedimension.setRedimension(Math.round(this.panelAreaTrabajo.getImagenSeleccionada().getPorcentajeRedimension())+" %");  
            this.panelRedimension.setInit(Math.round(this.panelAreaTrabajo.getImagenSeleccionada().getPorcentajeRedimension()));
        }
        else//si la imagen es null
        {
            this.panelRotacion.setRotacion("");
            this.panelRedimension.setRedimension("");
        }
    }
    
    public void eliminarImagenSeleccionada()
    {        
        
        this.panelAreaTrabajo.eliminarImagen(imagenSeleccionada);
        
    }
    
    public BufferedImage redimensionarImagen(int porcentaje)
    {
        int alto = this.imagenSeleccionada.getHeight();
        int ancho = this.imagenSeleccionada.getWidth();
        
        double porcentajeListo = (double) porcentaje/100;

        if(porcentaje < 100)
        {           
            return this.getPanelAreaTrabajo().imageIconToBufferedImage(this.imagenSeleccionada.getImagen().getScaledInstance((int)(ancho*porcentajeListo), (int)(alto*porcentajeListo), Image.SCALE_REPLICATE));
        }
        else if(porcentaje > 100)
        {
           return this.getPanelAreaTrabajo().imageIconToBufferedImage(this.imagenSeleccionada.getImagen().getScaledInstance((int)(ancho*porcentajeListo), (int)(alto*porcentajeListo), Image.SCALE_DEFAULT));
        }
        else
        {
            return this.imagenSeleccionada.getImagen();
        }
    }       

    public PanelSliderRotacion getPanelRotacion()
    {
        return panelRotacion;
    }

    public void setPanelRotacion(PanelSliderRotacion panelRotacion)
    {
        this.panelRotacion = panelRotacion;
    }

    public PanelSliderRedimension getPanelRedimension()
    {
        return panelRedimension;
    }

    public void setPanelRedimension(PanelSliderRedimension panelRedimension)
    {
        this.panelRedimension = panelRedimension;
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == this.botonAdd)
        {
            int opcion = this.fileChooserImagen.showOpenDialog(null);
            
            if(opcion == 0)
            {
                for(int i = 0; i < this.fileChooserImagen.getSelectedFiles().length; i++)
                {
                    String ruta = this.fileChooserImagen.getSelectedFiles()[i].getAbsolutePath();
                    this.panelAreaTrabajo.agregarImagen(ruta);
                }
            }
        }
        else if(e.getSource() == this.botonDelete)
        {
            int opcion = JOptionPane.showConfirmDialog(null, "Desea elminar la imagen seleccionada", "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            
            if (opcion == 0)
            {
                
                this.eliminarImagenSeleccionada();
            }
        }
        else if(e.getSource() == this.botonRecortar)
        {
            BufferedImage imagen = this.getPanelAreaTrabajo().getImagenSeleccionada().getImagenOriginal();
            PanelRecorte panelRecorte = new PanelRecorte(imagen);
            
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout(FlowLayout.CENTER));
            
            JScrollPane scroll = new JScrollPane(panelRecorte);
            
            if(imagen.getWidth() < 1200 && imagen.getHeight() < 600)
            {
                scroll.setPreferredSize(new Dimension(imagen.getWidth(), imagen.getHeight()));
                scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
                scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);                
            }
            else if(imagen.getWidth() < 1200 && imagen.getHeight() > 600)
            {
                scroll.setPreferredSize(new Dimension(imagen.getWidth(), 600));
                scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);                 
            }
            else if(imagen.getWidth() > 1200 && imagen.getHeight() < 600)
            {
                scroll.setPreferredSize(new Dimension(1200, imagen.getHeight()));
                scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
                scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);                 
            }
            else if(imagen.getWidth() > 1200 && imagen.getHeight() > 600)
            {
                scroll.setPreferredSize(new Dimension(1200, 600));
                scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);                  
            }
            JPanel panel2 = new JPanel(new BorderLayout());
            panel2.add(scroll, BorderLayout.NORTH);
            panel.add(panel2);
            
            int opcion = JOptionPane.showOptionDialog(null, panel, "Recortar imagen", JOptionPane.YES_NO_OPTION ,JOptionPane.PLAIN_MESSAGE, null, new Object[] {"Guardar recorte", "Conservar imagen original"} ,null);
        
            if(opcion == 0)
            {
                panelRecorte.guardarRecorte();
                
                if(panelRecorte.getImagenRecortada() != null)
                {
                    this.panelAreaTrabajo.sobreescribirImagen(panelRecorte.getImagenRecortada(), true);
                }
            }
            else if(opcion == 1)
            {
                try
                {                   
                    this.panelAreaTrabajo.sobreescribirImagen(ImageIO.read(this.imagenSeleccionada.getFile()), false);
                }
                catch (IOException ex)
                {
                }
            }
        }
        else if(e.getSource() == this.botonAgregarBorde)
        {
            int opcion = JOptionPane.showOptionDialog(null, "Tipo de borde a agregar: ", "Bordes", JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE, null, new Object[] {"Polaroid", "Conservar imagen original"}, null);
        
            if(opcion == 0)
            {
                try
                {
                    BufferedImage marcoPolaroid = ImageIO.read(getClass().getResource("/Imagenes/Polaroid.jpg"));
                    
                    BufferedImage imagenResultante = new BufferedImage(marcoPolaroid.getWidth(), marcoPolaroid.getHeight(), BufferedImage.TYPE_INT_RGB);
                    
                    Graphics g = imagenResultante.createGraphics();
                    g.drawImage(marcoPolaroid, 0, 0, marcoPolaroid.getWidth(), marcoPolaroid.getHeight(), null);
                    
                    
                    BufferedImage imagenEscalada = this.panelAreaTrabajo.RedimensionarImagen(ImageIO.read(this.imagenSeleccionada.getFile()), 1065, 1085);
                    
                    int ancho = imagenEscalada.getWidth();
                    int alto = imagenEscalada.getHeight();
                    
                    BufferedImage recorteImagen = null;
                           
                    if(ancho >= alto)
                    {                       
                        recorteImagen = imagenEscalada.getSubimage(((ancho - 1065)/2), 0, 1065, 1085);
                    }
                    else if(ancho < alto)
                    {
                        recorteImagen = imagenEscalada.getSubimage(0, ((alto - 1085)/2), 1065, 1085);
                    }
                    g.drawImage(recorteImagen, 67, 80, 1065, 1085, null);
                    
                    this.panelAreaTrabajo.sobreescribirImagen(imagenResultante, true);
                    this.panelAreaTrabajo.moverAlFrente(this.getPanelAreaTrabajo().getImagenSeleccionada());
                }
                catch(IOException ex)
                {
                }                
            }
            else if(opcion == 1)
            {
                try
                {
                    BufferedImage imagen = ImageIO.read(this.imagenSeleccionada.getFile());
                    
                    this.panelAreaTrabajo.sobreescribirImagen(imagen, false);
                }
                catch (IOException ex)
                {
                }                
            }
        }
        else if(e.getSource() == this.botonMoverFrente)
        {
            this.panelAreaTrabajo.moverAlFrente(this.imagenSeleccionada);
        }
        else if(e.getSource() == this.botonMoverFondo)
        {
            this.panelAreaTrabajo.moverAlFondo(this.imagenSeleccionada);
        }
        else if(e.getSource() == this.botonPatron)
        {           
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
            panel.add(this.panelPatrones);
            panel.add(this.panelPatronPersonalizado);
            
            int opcion = JOptionPane.showOptionDialog(null, panel, "Escoga un patron...",JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[] {"Aplicar", "Deshacer collage", "Cancelar"}, null);
        
            BufferedImage imagen = null;
            
            if(opcion == 0 && !this.panelPatrones.getCampoTexto().getText().equals("Ninguna"))
            {
                if(this.panelPatrones.getCampoTexto().getText().equals("Gato"))
                {
                    imagen = this.panelPatrones.getGato();
                }
                else if(this.panelPatrones.getCampoTexto().getText().equals("Batman"))
                {
                    imagen = this.panelPatrones.getBatman();
                }
                else if(this.panelPatrones.getCampoTexto().getText().equals("Corazón"))
                {
                    imagen = this.panelPatrones.getCorazon();
                }
                else if(this.panelPatrones.getCampoTexto().getText().equals("Apple"))
                {
                    imagen = this.panelPatrones.getApple();
                }
                else if(this.panelPatrones.getCampoTexto().getText().equals("Pez"))
                {
                    imagen = this.panelPatrones.getPez();
                }
                else if(this.panelPatrones.getCampoTexto().getText().equals("Hombre"))
                {
                    imagen = this.panelPatrones.getHombre();
                }
                else if(this.panelPatrones.getCampoTexto().getText().equals("Nube"))
                {
                    imagen = this.panelPatrones.getNube();
                }
                else if(this.panelPatrones.getCampoTexto().getText().equals("Avión"))
                {
                    imagen = this.panelPatrones.getAvion();
                }
                else if(this.panelPatrones.getCampoTexto().getText().equals("Estrella"))
                {
                    imagen = this.panelPatrones.getEstrella();
                }
                else if(this.panelPatrones.getCampoTexto().getText().equals("Luna"))
                {
                    imagen = this.panelPatrones.getLuna();
                }
                else if(this.panelPatrones.getCampoTexto().getText().equals("Personalizada"))
                {
                    imagen = this.panelPatronPersonalizado.getImagenActual();
                }
                //creamos la matriz con 1 y 0 que representan la imagen
                int[][] matriz = new int[imagen.getWidth()][imagen.getHeight()];

                for (int i = 0; i < imagen.getWidth(); i++)
                {
                    for (int j = 0; j < imagen.getHeight(); j++)
                    {
                        Color color = new Color(imagen.getRGB(i, j));

                        if(color.equals(new Color(0,0,0)))//si el color es 0
                        {
                            matriz[i][j] = 0;
                        }
                        else//si el color es distinto de 0
                        {
                            matriz[i][j] = 1;
                        }
                    }
                }

                this.panelAreaTrabajo.generarCollagePatron(matriz, imagen.getWidth(), imagen.getHeight());

                this.panelAreaTrabajo.repaint();
            }
            if(opcion == 1 && !this.panelPatrones.getCampoTexto().getText().equals("Ninguna"))
            {
                this.panelAreaTrabajo.deshacerCollage();
            }
        }
    }
}
