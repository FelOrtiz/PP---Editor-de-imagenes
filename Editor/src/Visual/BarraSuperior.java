/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Visual;

import cl.visual.Filtro;
import java.awt.event.ActionEvent;
import static java.awt.event.ActionEvent.SHIFT_MASK;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.io.FilenameUtils;
/**
 *
 * @author HoLeX
 */
public class BarraSuperior extends JMenuBar implements ActionListener
{
    private VentanaPrincipal ventana;
    
    private JMenu archivo;
    private JMenuItem guardar;
    private JMenuItem guardarComo;
    private JMenuItem salir;
    
    private JMenu imagen;
    private JMenuItem administrar;
    private JMenuItem verEditar;
    private JMenuItem deshacerCambios;
    private JMenuItem rehacerCambios;
    
    private JMenu crear;
    private JMenuItem collage;
    
    private JMenu opciones;
    private JMenuItem libreria;
    
    private JMenu ayuda;
    private JMenuItem ayudaFiltro;
    private JMenuItem acercaDe;
    
    private JFileChooser fileChooserLibreria;
    private JFileChooser fileChooserGuardarComo; 
    
    private int panelMostradoActualemte; /* 1 -> panelAdministracion | 2 -> panelEdicion | 3 -> panelCollage*/
    private int subPanelEdicionMostrado;
    
    public BarraSuperior(VentanaPrincipal ventana)
    {
        this.ventana = ventana;
        this.panelMostradoActualemte = 1;
        
        this.archivo = new JMenu("Archivo");
        this.archivo.setMnemonic('A');
        
        this.guardar = new JMenuItem("Guardar");
        this.guardar.setEnabled(false);
        this.guardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        this.guardar.addActionListener(this);
        
        this.guardarComo = new JMenuItem("Guardar como...");
        this.guardarComo.setEnabled(false);
        this.guardarComo.addActionListener(this);
        
        this.salir = new JMenuItem("Salir");
        this.salir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        this.salir.addActionListener(this);           
        
        this.imagen = new JMenu("Imagen");
        this.imagen.setMnemonic('I');
        
        this.administrar = new JMenuItem("Administrar");
        this.administrar.setEnabled(false);
        this.administrar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.CTRL_MASK));
        this.administrar.addActionListener(this);    
        
        this.verEditar = new JMenuItem("Ver y editar");
        this.verEditar.setEnabled(false);
        this.verEditar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.CTRL_MASK));
        this.verEditar.addActionListener(this);                
        
        this.deshacerCambios = new JMenuItem("Deshacer cambios");
        this.deshacerCambios.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        this.deshacerCambios.addActionListener(this);        
        
        this.rehacerCambios = new JMenuItem("Rehacer cambios");
        this.rehacerCambios.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK+SHIFT_MASK));
        this.rehacerCambios.addActionListener(this);        
        
        this.crear = new JMenu("Crear");
        this.crear.setMnemonic('C');
        
        this.collage = new JMenuItem("Collage de imágenes...");
        this.collage.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.CTRL_MASK));
        this.collage.setEnabled(false);
        this.collage.addActionListener(this);
        
        this.opciones = new JMenu("Opciones");
        this.opciones.setMnemonic('O');
        
        this.libreria = new JMenuItem("Agregar filtro...");
        this.libreria.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        this.libreria.setEnabled(false);
        this.libreria.addActionListener(this);
        
        this.ayuda = new JMenu("Ayuda");
        this.ayuda.setMnemonic('H');
        
        this.ayudaFiltro = new JMenuItem("Crear filtro...");
        this.ayudaFiltro.addActionListener(this);
        
        this.acercaDe = new JMenuItem("Acerca De...");
        this.acercaDe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, ActionEvent.ALT_MASK));
        this.acercaDe.addActionListener(this);
        
        this.archivo.add(guardar);
        this.archivo.add(guardarComo);
        this.archivo.add(new JPopupMenu.Separator());
        this.archivo.add(salir);
        
        this.imagen.add(administrar);
        this.imagen.add(new JPopupMenu.Separator());
        this.imagen.add(verEditar);
        this.imagen.add(new JPopupMenu.Separator());
        this.imagen.add(deshacerCambios);
        this.imagen.add(rehacerCambios);
        
        this.crear.add(collage);
        
        this.opciones.add(libreria);
        
        this.ayuda.add(ayudaFiltro);
        this.ayuda.add(new JPopupMenu.Separator());
        this.ayuda.add(acercaDe);
        
        this.add(archivo);
        this.add(imagen);
        this.add(crear);
        this.add(opciones);
        this.add(ayuda);
        //----------------------------------------------------------------------
        //-------------------------- File Chooser ------------------------------
        this.fileChooserLibreria = new JFileChooser();
        this.fileChooserLibreria.setFileSelectionMode(JFileChooser.FILES_ONLY);
        this.fileChooserLibreria.setApproveButtonText("Agregar Filtro");
        this.fileChooserLibreria.setMultiSelectionEnabled(false);
        FileFilter filtro = new FileNameExtensionFilter("Librerías (.jar)", "jar");
        this.fileChooserLibreria.setAcceptAllFileFilterUsed(false);
        this.fileChooserLibreria.addChoosableFileFilter(filtro);
        
        this.fileChooserGuardarComo = new JFileChooser();
        this.fileChooserGuardarComo.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileFilter filtroJPEG = new FileNameExtensionFilter("Archivo JPEG", "jpeg", "jpg");
        FileFilter filtroPNG = new FileNameExtensionFilter("Archivo PNG", "png");
        FileFilter filtroBMP = new FileNameExtensionFilter("Archivo BMP", "bmp");
        
        this.fileChooserGuardarComo.setAcceptAllFileFilterUsed(false);
        this.fileChooserGuardarComo.addChoosableFileFilter(filtroJPEG);
        this.fileChooserGuardarComo.addChoosableFileFilter(filtroPNG);
        this.fileChooserGuardarComo.addChoosableFileFilter(filtroBMP);
        //----------------------------------------------------------------------
    }
    
    /*
    Dependiendo de la imagen, deshabilito o no los botones de edicion como:
    si es un gif, deshabilito; sino los habilito
    */
    public void habilitarBotones(String ruta, boolean flag)
    {
        this.ventana.setRuta(ruta);
        this.verEditar.setEnabled(flag);
    }
    
    /*
    Si pinchan en la opcion de Ver y Editar o hacen Ctrl+2 este metodo se encarga
    de pasar a este panel.
    */
    public void eventoVerEditar()
    {
        this.panelMostradoActualemte = 2;//es un 2 porque se esta mostrando el panelVerEditar
        this.subPanelEdicionMostrado = 1;// es un 1 porque se esta mostrando en la pestaña, las opciones de edicion
        
        if(this.ventana.getRuta().toLowerCase().endsWith("gif"))//si la imagen es gif
        {
            this.ventana.repintarPanelEdicion(true);//si la imagen es gif, se repinta el panel, pero se deshabilitan los botones
        }
        else
        {
            this.ventana.repintarPanelEdicion(false);//si la imagen no es gif, se repinta el panel, y se habilitan los botones
        }
        this.verEditar.setEnabled(false);
        this.administrar.setEnabled(true);
        this.guardar.setEnabled(true);
        this.guardarComo.setEnabled(true);
        this.collage.setEnabled(true);
        this.libreria.setEnabled(true);
    }
    
    /*
    Si pinchan en la opcion Administrar imagen o hacen Ctrl+1 este metodo se encarga de pintar este panel
    */
    public void eventoAdministrar()
    {
        this.panelMostradoActualemte = 1;//es un 1 porque se estra mostrando el panelAdministrar ahora
        this.ventana.repintarPanelAdministrar();//repintamos el panel
        this.administrar.setEnabled(false);
        this.verEditar.setEnabled(true);
        this.guardar.setEnabled(false);
        this.guardarComo.setEnabled(false);
        this.collage.setEnabled(false);
        this.libreria.setEnabled(true);
    }
    
    /*
    Si pinchan en la opcion Crear collage o hacen Ctrl+3 este metodo se encarga de pintar este panel
    */
    public void eventoCrearCollage()
    {
        if(this.ventana.getPanelEdicion().getRutaActual().toLowerCase().endsWith("gif"))//Si pasan a este panel y la imegn actual es un gif, esto se encarga de validar y no dejar crear el collage hasta que pinche otra imagen
        {
            JOptionPane.showMessageDialog(null, "No puede seleccionar imagenes tipo GIF para crear un collage. Por favor, intente nuevamente", "Atención", JOptionPane.WARNING_MESSAGE);
        }
        else
        {
            this.panelMostradoActualemte = 3;//es un 3 porque se estra mostrando el panelCollage ahora
            this.ventana.repintarPanelCollage();//repintamos el panel
            this.collage.setEnabled(false);
            this.verEditar.setEnabled(false);
            this.guardar.setEnabled(false);
            this.guardarComo.setEnabled(true);        
            this.administrar.setEnabled(true);
            this.libreria.setEnabled(true);
        }        
    }
    
    /*
    Evento encargado de agregar una librería
    */
    public void eventoAgregarLibreria() throws Exception
    {
        this.fileChooserLibreria.showOpenDialog(null);//abrimos el selector de archivos
        String ruta = this.fileChooserLibreria.getSelectedFile().getAbsolutePath();//pedimos la ruta del archivo seleccionado
        URL[] urls = {new URL("file:///"+ruta)};//transformamos la ruta a url
       
        URLClassLoader urlClassLoader = URLClassLoader.newInstance(urls);//creamos una instancia de esa url
        Class clazz = urlClassLoader.loadClass("cl.visual.filtros.FiltroNuevo");//pemimos la clase de nombre "FiltroNuevo" del paquete "cl.visual.filtro" ubicada en nuestra url
        
        if(clazz.newInstance() instanceof Filtro)//validamos que el filtro importado sea una instancia de Filtro
        {
            Filtro filtro = (Filtro)clazz.newInstance();//Creamos nuestro filtro en el programa        
            this.ventana.getPanelEdicion().getPanelFiltro().getPanelProcesamiento().agregarFiltro(filtro);//Llamamos a otro metodo que se encarga de tomar este filtro y pintar el boton y su evento en el panelProcesamiento
        }       
    }

    public int getPanelMostradoActualemte()
    {
        return panelMostradoActualemte;
    }

    public void setPanelMostradoActualemte(int panelMostradoActualemte)
    {
        this.panelMostradoActualemte = panelMostradoActualemte;
    }

    public int getSubPanelEdicionMostrado()
    {
        return subPanelEdicionMostrado;
    }

    public void setSubPanelEdicionMostrado(int subPanelEdicionMostrado)
    {
        this.subPanelEdicionMostrado = subPanelEdicionMostrado;
    }
    
    public void eventoDeshacerCambios()
    {
        if(panelMostradoActualemte == 2)//panel edicion
        {
            if(subPanelEdicionMostrado == 1)// panel de figuras, lineas, etc
            {                
                this.ventana.getPanelEdicion().getPanelImagen().desHacer();
            }
            else if(subPanelEdicionMostrado == 2)// panel filtros
            {
                this.ventana.getPanelEdicion().getPanelFiltro().getPanelProcesamiento().desHacer();
            }
        }
        else if(panelMostradoActualemte == 3)// panel collage
        {
            this.ventana.getPanelCollage().getBarraHerramientas().getPanelAreaTrabajo().desHacer();
        }
    }
    
    public void eventoRehacerCambios()
    {
        if(panelMostradoActualemte == 2)//panel edicion
        {
            if(subPanelEdicionMostrado == 1) // panel de figuras, lineas, etc
            {
                this.ventana.getPanelEdicion().getPanelImagen().reHacer();
            }
            else if(subPanelEdicionMostrado == 2) // panel filtros
            {
                this.ventana.getPanelEdicion().getPanelFiltro().getPanelProcesamiento().reHacer();
            }
        }
        else if(panelMostradoActualemte == 3)// panel collage
        {
            this.ventana.getPanelCollage().getBarraHerramientas().getPanelAreaTrabajo().reHacer();
        }        
    }
    
    public void eventoGuardar(String ruta, boolean flag)
    {
        if(ruta != null)
        {
            String rutaActual = ruta;
            if(flag)//si apreto 'Guardar'
            {
                if(ruta.endsWith("jpg") || ruta.endsWith("jpeg") || ruta.endsWith("png") || ruta.endsWith("bmp") || ruta.endsWith("JPG") 
                        || ruta.endsWith("JPEG") || ruta.endsWith("PNG") || ruta.endsWith("BMP") )
                {
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Para guardar una imagen, debe ser: JPG, BMP o PNG");
                }
            }
            else//si apreto 'Guardar Como..."
            {
                if(this.fileChooserGuardarComo.getFileFilter().getDescription().equals("Archivo JPEG"))
                {
                    rutaActual = rutaActual+".jpg";
                }
                else if(this.fileChooserGuardarComo.getFileFilter().getDescription().equals("Archivo PNG"))
                {
                    rutaActual = rutaActual+".png";
                }
                else if(this.fileChooserGuardarComo.getFileFilter().getDescription().equals("Archivo BMP"))
                {
                    rutaActual = rutaActual+".bmp";
                }
            }

            if(this.panelMostradoActualemte == 2)
            {
                if(this.subPanelEdicionMostrado == 2)
                {
                    this.ventana.getPanelEdicion().getPanelImagen().guardarImagen(rutaActual);
                    //this.ventana.getPanelEdicion().guardarImagen(rutaActual);
                }
                else if(this.subPanelEdicionMostrado == 1)
                {
                    this.ventana.getPanelEdicion().getPanelImagen().guardarImagen(rutaActual);
                }
            }
            else if(this.panelMostradoActualemte == 3)
            {
                this.ventana.getPanelCollage().guardarImagen(rutaActual);
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent evento)
    {
        if(evento.getSource() == this.guardar)
        {
            this.eventoGuardar(this.ventana.getRuta(), true);
        }
        if(evento.getSource() == this.guardarComo)
        {
            this.fileChooserGuardarComo.setSelectedFile(new File(FilenameUtils.removeExtension(new File(this.ventana.getPanelEdicion().getRutaActual()).getName())));            
            int valor = this.fileChooserGuardarComo.showSaveDialog(null);
            File file = this.fileChooserGuardarComo.getSelectedFile();
            
            if(file != null && valor == 0)
            {
                String ruta = file.getAbsolutePath();
                this.eventoGuardar(ruta, false);
            }
        }
        if(evento.getSource() == this.administrar)
        {
            this.eventoAdministrar();
        }
        if(evento.getSource() == this.verEditar)
        {
            this.eventoVerEditar();
        }
        if(evento.getSource() == this.collage)
        {
            this.eventoCrearCollage();
        }
        if(evento.getSource() == this.libreria)
        {
            try
            {
                this.eventoAgregarLibreria();
            }
            catch (Exception ex)
            {
            }
        }
        if(evento.getSource() == this.deshacerCambios)
        {
            this.eventoDeshacerCambios();
        }
        if(evento.getSource() == this.rehacerCambios)
        {
            this.eventoRehacerCambios();
        }
        if(evento.getSource() == this.salir)
        {
            System.exit(0);
        }
        if(evento.getSource() == this.acercaDe)
        {
            JOptionPane.showMessageDialog(null,
                     "   Sistema de gestion y visualizacion de imagenes  \n"
                    +"                                                   \n"
                    +"    Version: 1.0                                   \n"
                    +"                                                   \n"
                    +"    Plataforma: Java SE 7                          \n"
                    +"                                                   \n"
                    +"    Entorno: NetBeans 7.3                          \n"
                    +"                                                   \n"
                    +"    Desarrolladores:                               \n"
                    +"                                                   \n"                    
                    +"      + Felipe Ortiz Gonzalez                      \n"
                    +"      + Yerco Nuñez Gallardo                       \n"
                    +"      + Michael Aravena Velasquez                  \n"
                    +"                                                   \n","Acerca De...",JOptionPane.PLAIN_MESSAGE);            
        }       
        if(evento.getSource() == this.ayudaFiltro)
        {
            JOptionPane.showMessageDialog(null, 
                    "   Instrucciones\n"
                    + "                                                                                                                       \n" 
                    + "    Para crear un filtro personalizado usted debe:                                                                     \n"
                    + "                                                                                                                       \n" 
                    + "      1. Descargar e instalar NetBeans IDE 7 o superior                                                                \n"
                    + "      2. Crear un nuevo proyecto con el nombre de su filtro por ejemplo: 'Sepia', 'Negativo', etc.                     \n"
                    + "      3. Luego el paquete por defecto debe ser renombrado a: 'cl.visual.filtros' (obligatorio)                         \n"
                    + "      4. Crear una nueva clase dentro del paquete anteriormente creado, con el nombre: 'FiltroNuevo' (obligatorio)     \n"
                    + "      5. Luego debe importar la librería 'Filtros' en su proyecto                                                      \n"
                    + "      6. Dentro de su clase, debe hacer que implemente 'Filtro', es decir, 'FiltroNuevo implements Filtro'             \n"
                    + "      7. Si la implementacion fue correcta de apareceran dos metodos, con lo cual debe sobrecarcarglos                 \n"         
                    + "      8. Para el metodo 'filtro' usted recibe la imagen original en formato 'BufferedImage', y debe                    \n"
                    + "      retornar la imagen con el filtro aplicado en el mismo formato                                                    \n"         
                    + "      9. Despues, para el metodo 'getName' usted debe retornar un 'String' con el nombre de su filtro                  \n"         
                    + "      10. Por último, debe construir la version .jar de su filtro para luego importarlo desde Visual                   \n"
                    + "                                                                                                                       \n"
                    , "Crear filtro personalizado", JOptionPane.PLAIN_MESSAGE);
        }
    }
    
}
