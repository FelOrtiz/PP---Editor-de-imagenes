/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Edicion;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ProgressMonitor;

/**
 *
 * @author HoLeX
 */
public class PanelTiraImagenes extends JPanel
{
    private File ruta;
    private PanelEdicion panelEdicion;
    private JPanel panelSuperior;
    private JLabel nombre;
    private JPanel panelImagenes;
    
    private ProgressMonitor pm;
    private int componentes;
    private ArrayList<PanelMiniatura> miniaturas;
    
    public PanelTiraImagenes(String ruta, PanelEdicion panelEdicion) 
    {
        super.setLayout(new BorderLayout());
        
        this.panelEdicion = panelEdicion;
        this.ruta = new File(ruta).getParentFile();
        
        this.panelSuperior = new JPanel(new BorderLayout());
        this.panelSuperior.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        this.nombre = new JLabel("Tira de imagenes");
        this.panelSuperior.add(this.nombre, BorderLayout.WEST);
        
        this.panelImagenes = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
        this.add(panelSuperior, BorderLayout.NORTH);
        this.add(panelImagenes, BorderLayout.CENTER);
        
        this.pm = new ProgressMonitor(null, "Cargando imágenes", "Cargando...", 0, 100);
        this.pm.setProgress(0);
        this.miniaturas = new ArrayList<PanelMiniatura>();
        this.crearMiniaturas();
    }

    public ArrayList<PanelMiniatura> getMiniaturas()
    {
        return miniaturas;
    }

    public void setMiniaturas(ArrayList<PanelMiniatura> miniaturas)
    {
        this.miniaturas = miniaturas;
    }
    
    public void crearMiniaturas()
    {
        new Thread()
        {
            public void run()
            {
                FilenameFilter filtro = new FilenameFilter() 
                {
                    public boolean accept(File directory, String fileName) 
                    {
                        if(fileName.endsWith("png") || fileName.endsWith("jpg") || fileName.endsWith("jpeg") || fileName.endsWith("bmp") || fileName.endsWith("gif") || fileName.endsWith("PNG") || fileName.endsWith("JPG") || fileName.endsWith("JPEG") || fileName.endsWith("BMP") || fileName.endsWith("GIF"))
                        {
                            return true;
                        }
                        else
                        {
                            return false;
                        }
                    }
                };
                File[] imagenesCargar = ruta.listFiles(filtro); 
                panelImagenes.setPreferredSize(new Dimension(100*imagenesCargar.length, 0));
                componentes = imagenesCargar.length;                
                for (int i = 0; i < componentes; i++)
                {
                    int progreso = (int)((double)(((double)(i+1)*100)/(double)(componentes)));
                    pm.setProgress(progreso);
                    pm.setNote(progreso+"% Completado.");
                    panelEdicion.getVentana().setCursor(new Cursor(Cursor.WAIT_CURSOR));
                    File file = imagenesCargar[i];
                    PanelMiniatura panel = new PanelMiniatura(file.getAbsolutePath(), panelEdicion, i);
                    panelImagenes.add(panel);
                    miniaturas.add(panel);
                    panelImagenes.revalidate();
                }
                panelEdicion.getVentana().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            };
        }.start();
    }
}
