/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Collage;

import com.jhlabs.image.ThresholdFilter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author HoLeX
 */
public class PanelPatronPersonalizado extends JPanel implements ActionListener, MouseListener
{
    private JPanel panelPrincipal;
    private JLabel label;
    private BufferedImage imagenActual;
    
    private JButton botonBuscar;
    private JFileChooser chooser;
    
    private PanelAreaTrabajo panelAreaTrabajo;
    
    public PanelPatronPersonalizado(PanelAreaTrabajo panelAreaTrabajo)
    {
        this.panelAreaTrabajo = panelAreaTrabajo;
        this.setPreferredSize(new Dimension(200,200));
        this.setLayout(new BorderLayout());
        this.panelPrincipal = new JPanel(new BorderLayout());
        this.label = new JLabel();
        this.label.setBackground(Color.WHITE);
        this.label.setOpaque(true);
        
        this.botonBuscar = new JButton("Importar imagen...");
        this.botonBuscar.addActionListener(this);
        
        this.panelPrincipal.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
        this.panelPrincipal.add(label, BorderLayout.CENTER);
        this.add(panelPrincipal, BorderLayout.CENTER);
        this.add(botonBuscar, BorderLayout.SOUTH);
        
        this.addMouseListener(this);
        
        this.chooser = new JFileChooser();
        this.chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileFilter filtroJPEG = new FileNameExtensionFilter("Archivo JPEG", "jpeg", "jpg");
        FileFilter filtroPNG = new FileNameExtensionFilter("Archivo PNG", "png");
        FileFilter filtroBMP = new FileNameExtensionFilter("Archivo BMP", "bmp"); 
        this.chooser.setAcceptAllFileFilterUsed(false);
        this.chooser.addChoosableFileFilter(filtroJPEG);
        this.chooser.addChoosableFileFilter(filtroPNG);
        this.chooser.addChoosableFileFilter(filtroBMP);        
        
        this.imagenActual = null;
    }
    
    public BufferedImage binarizacion(BufferedImage imagenOriginal, int valor)
    {
        BufferedImage imagen = imagenOriginal;
        ThresholdFilter filtro = new ThresholdFilter();
        filtro.setLowerThreshold(valor);
        BufferedImage filter = filtro.filter(imagenOriginal, imagen);
        return filter;
    }  

    public BufferedImage getImagenActual()
    {
        return imagenActual;
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == this.botonBuscar)
        {
            int opcion = this.chooser.showOpenDialog(null);
            File file = this.chooser.getSelectedFile();
            
            if(opcion == 0 && file != null)
            {
                try 
                {
                    this.label.removeAll();
                    BufferedImage imagen = ImageIO.read(file);
                    BufferedImage imagenEscalada = this.panelAreaTrabajo.EscalarImagen(this.binarizacion(imagen, 130), 500, 500);
                    
                    //--
                    this.imagenActual = new BufferedImage(1360, 600, BufferedImage.TYPE_INT_RGB);
                    Graphics g = this.imagenActual.createGraphics();
                    
                    int x = (int)Math.round((double)( ((double)1360/(double)2) - ((double)imagenEscalada.getWidth()/(double)2)));
                    g.setColor(Color.WHITE);
                    g.fillRect(0, 0, 1360, 600);
                    g.drawImage(imagenEscalada, x, 60, imagenEscalada.getWidth(), imagenEscalada.getHeight(), null);
                    g.dispose();
                    
                    //--
                    this.label.setIcon(new ImageIcon(this.panelAreaTrabajo.EscalarImagen(imagenEscalada, 200, 165)));
                    this.label.setHorizontalAlignment(JLabel.CENTER);
                    this.label.revalidate();
                    this.panelAreaTrabajo.getPanelCollage().getBarraHerramientas().getPanelPatrones().getCampoTexto().setText("Personalizada");         
                }
                catch (IOException ex) 
                {
                }
            }    
            else
            {
                this.panelAreaTrabajo.getPanelCollage().getBarraHerramientas().getPanelPatrones().getCampoTexto().setText("Ninguna"); 
                this.label.removeAll();
                this.label.revalidate();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        if(this.imagenActual != null)
        {
            this.panelAreaTrabajo.getPanelCollage().getBarraHerramientas().getPanelPatrones().getCampoTexto().setText("Personalizada");
        }
        else
        {
            this.panelAreaTrabajo.getPanelCollage().getBarraHerramientas().getPanelPatrones().getCampoTexto().setText("Ninguna"); 
        }
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
}
