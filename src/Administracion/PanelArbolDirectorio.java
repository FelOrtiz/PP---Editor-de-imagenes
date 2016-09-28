package Administracion;

import Edicion.Imagen;
import java.awt.BorderLayout;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileSystemView;
 
/**
 *
 * @author HoLeX
 */
public class PanelArbolDirectorio extends JPanel 
{
    private JTree fileTree;
    private Arbol arbol;
    private PanelAdministrar panelAdministrar;
 
    public PanelArbolDirectorio(String directory, PanelAdministrar panelAdministrar) 
    {
        this.panelAdministrar = panelAdministrar;
        this.setLayout(new BorderLayout());        
        this.arbol = new Arbol(new File(directory).getParentFile().getParentFile());//con esto abrimos el arbol desde C:
        this.fileTree = new JTree(arbol);
        this.fileTree.setEditable(false);
        this.fileTree.addTreeSelectionListener(new TreeSelectionListener() 
        {
            @Override
            public void valueChanged(TreeSelectionEvent event) 
            {
                File file = (File) fileTree.getLastSelectedPathComponent();//con esto obtenemos el archivo seleccionado
                cambiarImagen(file);//llamamos a la funcion para validar el archivo (si es imagen, carpeta, u otro archivo)
            }
        });
        this.fileTree.setCellRenderer(new RendererArbol());//con esto le cambiamos el apecto al arbol(iconos de windows)     
        this.add(fileTree, BorderLayout.CENTER);
    }
 
    /*
    Funcion que interactua con el arbol y el panel administrar
    */
    public void cambiarImagen(File file)
    {
        if(file.isDirectory())//si el archivo es una carpeta o acceso directo a carpeta
        {
            this.panelAdministrar.mostrarNada();
            this.panelAdministrar.mostrarNadaMetadatos();
            this.panelAdministrar.habilitarEdicion(null, false);
        }
        //si el archivo es alguna imagen
        else if(file.getAbsolutePath().endsWith("png") || file.getAbsolutePath().endsWith("jpg") || file.getAbsolutePath().endsWith("jpeg") || file.getAbsolutePath().endsWith("bmp") || file.getAbsolutePath().endsWith("gif") || file.getAbsolutePath().endsWith("PNG") || file.getAbsolutePath().endsWith("JPG") || file.getAbsolutePath().endsWith("JPEG") || file.getAbsolutePath().endsWith("BMP") || file.getAbsolutePath().endsWith("GIF"))
        {
            if(file.getAbsolutePath().endsWith("bmp") || file.getAbsolutePath().endsWith("BMP"))//si la imagen es BMP
            {
                ImageIcon imagen = this.panelAdministrar.setImagenPrincipalBMP(file);
                this.panelAdministrar.mostrarInformacionBMP(file, imagen);
            }
            else//si la imagen es ditinto a BMP
            {
                this.panelAdministrar.setImagenPrincipal(new Imagen(new ImageIcon(file.getAbsolutePath())));
                this.panelAdministrar.mostrarInformacion(file);
                
            }
            this.panelAdministrar.habilitarEdicion(file.getAbsolutePath(), true);//habilitamos la opcion de pasar al panelEditar
        }
        else//si es un archivo distinto a una imagen (musica, videos, pdf, etc)
        {
            this.panelAdministrar.mostrarError();
            this.panelAdministrar.mostrarNadaMetadatos();
            this.panelAdministrar.habilitarEdicion(null, false);
        }
        
    }

    public JTree getFileTree()
    {
        return fileTree;
    }

    public void setFileTree(JTree fileTree)
    {
        this.fileTree = fileTree;
    }

}