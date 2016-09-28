/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Administracion;

import Visual.VentanaPrincipal;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import org.apache.sanselan.ImageReadException;
import org.apache.sanselan.Sanselan;
import org.apache.sanselan.common.IImageMetadata;
import org.apache.sanselan.common.RationalNumber;
import org.apache.sanselan.formats.jpeg.JpegImageMetadata;
import org.apache.sanselan.formats.tiff.TiffConstants;
import org.apache.sanselan.formats.tiff.TiffField;

/**
 *
 * @author HoLeX
 */
public class PanelMetadatos extends JPanel
{
    private JPanel panelPrincipal;
    private JPanel panelImagen;
    private JPanel panelIzquierdoImagen;
    private JPanel panelDerechoImagen;
    private JLabel idImagen;
    private JLabel valorIdImagen;
    private JLabel dimensionesImagen;
    private JLabel valorDimensionesImagen;
    private JLabel anchoImagen;
    private JLabel valorAnchoImagen;
    private JLabel altoImagen;
    private JLabel valorAltoImagen;
    private JLabel profundidadBitsImagen;
    private JLabel valorProfundidadBitsImagen;
    private JLabel resolucionHorizontalImagen;
    private JLabel valorResolucionHorizontalImagen;
    private JLabel resolucionVerticalImagen;
    private JLabel valorResolcuionVerticalImagen;
     
    private JPanel panelArchivo;
    private JPanel panelIzquierdoArchivo;
    private JPanel panelDerechoArchivo;
    private JLabel nombreArchivo;
    private JLabel valorNombreArchivo;
    private JLabel tipoArchivo;
    private JLabel valorTipoArchivo;
    private JLabel fechaCreacionArchivo;
    private JLabel valorFechaCreacionArchivo;
    private JLabel fechaModificacionArchivo;
    private JLabel valorFechaModificacionArchivo;
    private JLabel sizeArchivo;
    private JLabel valorSizeArchivo;
    private JLabel propietarioArchivo;
    private JLabel valorPropietarioArchivo;
    
    private JPanel panelOrigen;
    private JPanel panelIzquierdoOrigen;
    private JPanel panelDerechoOrigen;
    private JLabel autoresOrigen;
    private JLabel valorAutoresOrigen;
    private JLabel fechaCapturaOrigen;
    private JLabel valorFechaCapturaOrigen;
    private JLabel nombreProgramaOrigen;
    private JLabel valorNombreProgramaOrigen;
    private JLabel copyrightOrigen;
    private JLabel valorCopyrightOrigen;
     
    private JPanel panelCamara;
    private JPanel panelIzquierdoCamara;
    private JPanel panelDerechoCamara;
    private JLabel fabricanteCamara;
    private JLabel valorFabricanteCamara;
    private JLabel modeloCamara;
    private JLabel valorModeloCamara;
    private JLabel puntoFCamara;
    private JLabel valorPuntoFCamara;
    private JLabel tiempoExposicionCamara;
    private JLabel valorTiempoExposicionCamara;
    private JLabel velocidadIsoCamara;
    private JLabel valorVelocidadIsoCamara;
    private JLabel distanciaFocalCamara;
    private JLabel valorDistanciaFocalCamara;
    private JLabel modoFlashCamara;
    private JLabel valorModoFlashCamara;
    private JLabel longitudFocal35Camara;
    private JLabel valorLongitudFocal35Camara;
    
    private JPanel panelGPS;
    private JPanel panelIzquierdoGPS;
    private JPanel panelDerechoGPS;
    private JLabel latitudGPS;
    private JLabel valorLatitudGPS;
    private JLabel longitudGPS;
    private JLabel valorLongitudGPS;
    private JLabel altitudGPS;
    private JLabel valorAltitudGPS;
    
    private VentanaPrincipal ventana;
    
    private final String textoVacio = " ";
    
    public PanelMetadatos(VentanaPrincipal ventana)
    {
        this.ventana = ventana;
         
        this.setLayout(new BorderLayout());
        TitledBorder bordePrincipal = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "  Metadatos  ");
        bordePrincipal.setTitleJustification(TitledBorder.CENTER);
        this.setBorder(bordePrincipal);
         
        this.panelPrincipal = new JPanel();
        this.panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        this.panelPrincipal.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        
        //------------------------- PANEL IMAGEN
        
        this.panelImagen = new JPanel();
        this.panelImagen.setLayout(new BorderLayout());
        TitledBorder bordeImagen = BorderFactory.createTitledBorder("  Imagen  ");
        this.panelImagen.setBorder(bordeImagen);
        this.panelIzquierdoImagen = new JPanel();
        this.panelIzquierdoImagen.setLayout(new BoxLayout(panelIzquierdoImagen, BoxLayout.PAGE_AXIS));
        this.panelIzquierdoImagen.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
         
        this.idImagen = new JLabel("ID de imagen: ");
        this.dimensionesImagen = new JLabel("Dimensiones: ");
        this.anchoImagen = new JLabel("Ancho: ");
        this.altoImagen = new JLabel("Alto: ");
        this.profundidadBitsImagen = new JLabel("Profundidad de bits: ");
        this.resolucionHorizontalImagen = new JLabel("Resolucion horizontal: ");
        this.resolucionVerticalImagen = new JLabel("Resolucion vertical: ");
        
        this.panelIzquierdoImagen.add(idImagen);
        this.panelIzquierdoImagen.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelIzquierdoImagen.add(dimensionesImagen);
        this.panelIzquierdoImagen.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelIzquierdoImagen.add(anchoImagen);
        this.panelIzquierdoImagen.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelIzquierdoImagen.add(altoImagen);
        this.panelIzquierdoImagen.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelIzquierdoImagen.add(profundidadBitsImagen);
        this.panelIzquierdoImagen.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelIzquierdoImagen.add(resolucionHorizontalImagen);
        this.panelIzquierdoImagen.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelIzquierdoImagen.add(resolucionVerticalImagen);
        
        this.panelDerechoImagen = new JPanel();
        this.panelDerechoImagen.setLayout(new BoxLayout(panelDerechoImagen, BoxLayout.Y_AXIS));
        this.panelDerechoImagen.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        
        this.valorIdImagen = new JLabel(textoVacio);
        this.valorDimensionesImagen = new JLabel(textoVacio);
        this.valorAnchoImagen = new JLabel(textoVacio);
        this.valorAltoImagen = new JLabel(textoVacio);
        this.valorProfundidadBitsImagen = new JLabel(textoVacio);
        this.valorResolucionHorizontalImagen = new JLabel(textoVacio);
        this.valorResolcuionVerticalImagen = new JLabel(textoVacio);
        
        this.panelDerechoImagen.add(valorIdImagen);
        this.panelDerechoImagen.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelDerechoImagen.add(valorDimensionesImagen);
        this.panelDerechoImagen.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelDerechoImagen.add(valorAnchoImagen);
        this.panelDerechoImagen.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelDerechoImagen.add(valorAltoImagen);         
        this.panelDerechoImagen.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelDerechoImagen.add(valorProfundidadBitsImagen);         
        this.panelDerechoImagen.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelDerechoImagen.add(valorResolucionHorizontalImagen);         
        this.panelDerechoImagen.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelDerechoImagen.add(valorResolcuionVerticalImagen);         
        
        this.panelImagen.add(panelIzquierdoImagen, BorderLayout.WEST);
        this.panelImagen.add(panelDerechoImagen, BorderLayout.CENTER);
        
        //------------------------- PANEL ARCHIVO
        
        this.panelArchivo = new JPanel();
        this.panelArchivo.setLayout(new BorderLayout());
        TitledBorder bordeArchivo = BorderFactory.createTitledBorder("  Archivo  ");
        this.panelArchivo.setBorder(bordeArchivo);
        
        this.panelIzquierdoArchivo = new JPanel();
        this.panelIzquierdoArchivo.setLayout(new BoxLayout(panelIzquierdoArchivo, BoxLayout.PAGE_AXIS));
        this.panelIzquierdoArchivo.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        
        this.nombreArchivo = new JLabel("Nombre: ");
        this.tipoArchivo = new JLabel("Tipo: ");
        this.fechaCreacionArchivo = new JLabel("Fecha de creación: ");
        this.fechaModificacionArchivo = new JLabel("Fecha de ultima modificación: ");
        this.sizeArchivo = new JLabel("Tamaño: ");
        this.propietarioArchivo = new JLabel("Propietario: ");
        
        this.panelIzquierdoArchivo.add(nombreArchivo);
        this.panelIzquierdoArchivo.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelIzquierdoArchivo.add(tipoArchivo);
        this.panelIzquierdoArchivo.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelIzquierdoArchivo.add(fechaCreacionArchivo);
        this.panelIzquierdoArchivo.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelIzquierdoArchivo.add(fechaModificacionArchivo);
        this.panelIzquierdoArchivo.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelIzquierdoArchivo.add(sizeArchivo);
        this.panelIzquierdoArchivo.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelIzquierdoArchivo.add(propietarioArchivo);
        
        this.panelDerechoArchivo = new JPanel();
        this.panelDerechoArchivo.setLayout(new BoxLayout(panelDerechoArchivo, BoxLayout.PAGE_AXIS));
        this.panelDerechoArchivo.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        
        this.valorNombreArchivo = new JLabel(textoVacio);
        this.valorTipoArchivo = new JLabel(textoVacio);
        this.valorFechaCreacionArchivo = new JLabel(textoVacio);
        this.valorFechaModificacionArchivo = new JLabel(textoVacio);
        this.valorSizeArchivo = new JLabel(textoVacio);
        this.valorPropietarioArchivo = new JLabel(textoVacio);
        
        this.panelDerechoArchivo.add(valorNombreArchivo);
        this.panelDerechoArchivo.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelDerechoArchivo.add(valorTipoArchivo);
        this.panelDerechoArchivo.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelDerechoArchivo.add(valorFechaCreacionArchivo);
        this.panelDerechoArchivo.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelDerechoArchivo.add(valorFechaModificacionArchivo);
        this.panelDerechoArchivo.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelDerechoArchivo.add(valorSizeArchivo);
        this.panelDerechoArchivo.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelDerechoArchivo.add(valorPropietarioArchivo);
     
        this.panelArchivo.add(panelIzquierdoArchivo, BorderLayout.WEST);
        this.panelArchivo.add(panelDerechoArchivo, BorderLayout.CENTER);
        
        //------------------------- PANEL ORIGEN
        
        this.panelOrigen = new JPanel();
        this.panelOrigen.setLayout(new BorderLayout());
        TitledBorder bordeOrigen = BorderFactory.createTitledBorder("  Origen  ");
        this.panelOrigen.setBorder(bordeOrigen);
        
        this.panelIzquierdoOrigen = new JPanel();
        this.panelIzquierdoOrigen.setLayout(new BoxLayout(panelIzquierdoOrigen, BoxLayout.PAGE_AXIS));
        this.panelIzquierdoOrigen.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        
        this.autoresOrigen = new JLabel("Autores: ");
        this.fechaCapturaOrigen = new JLabel("Fecha de captura: ");
        this.nombreProgramaOrigen = new JLabel("Nombre del programa: ");
        this.copyrightOrigen = new JLabel("Copyright: ");
        
        this.panelIzquierdoOrigen.add(autoresOrigen);
        this.panelIzquierdoOrigen.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelIzquierdoOrigen.add(fechaCapturaOrigen);
        this.panelIzquierdoOrigen.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelIzquierdoOrigen.add(nombreProgramaOrigen);
        this.panelIzquierdoOrigen.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelIzquierdoOrigen.add(copyrightOrigen);
        
        this.panelDerechoOrigen = new JPanel();
        this.panelDerechoOrigen.setLayout(new BoxLayout(panelDerechoOrigen, BoxLayout.PAGE_AXIS));
        this.panelDerechoOrigen.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        
        this.valorAutoresOrigen = new JLabel(textoVacio);
        this.valorFechaCapturaOrigen = new JLabel(textoVacio);
        this.valorNombreProgramaOrigen = new JLabel(textoVacio);
        this.valorCopyrightOrigen = new JLabel(textoVacio);
        
        this.panelDerechoOrigen.add(valorAutoresOrigen);
        this.panelDerechoOrigen.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelDerechoOrigen.add(valorFechaCapturaOrigen);
        this.panelDerechoOrigen.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelDerechoOrigen.add(valorNombreProgramaOrigen);
        this.panelDerechoOrigen.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelDerechoOrigen.add(valorCopyrightOrigen);
     
        this.panelOrigen.add(panelIzquierdoOrigen, BorderLayout.WEST);
        this.panelOrigen.add(panelDerechoOrigen, BorderLayout.CENTER);         
        
        //------------------------- PANEL CAMARA
        
        this.panelCamara = new JPanel();
        this.panelCamara.setLayout(new BorderLayout());
        TitledBorder bordeCamara = BorderFactory.createTitledBorder("  Cámara  ");
        this.panelCamara.setBorder(bordeCamara);
        
        this.panelIzquierdoCamara = new JPanel();
        this.panelIzquierdoCamara.setLayout(new BoxLayout(panelIzquierdoCamara, BoxLayout.PAGE_AXIS));
        this.panelIzquierdoCamara.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        
        this.fabricanteCamara = new JLabel("Fabricante de la cámara: ");
        this.modeloCamara = new JLabel("Modelo de la cámara: ");
        this.puntoFCamara = new JLabel("Punto F: ");
        this.tiempoExposicionCamara = new JLabel("Tiempo de exposición: ");
        this.velocidadIsoCamara = new JLabel("Velocidad ISO: ");
        this.distanciaFocalCamara = new JLabel("Distancia Focal: ");
        this.modoFlashCamara = new JLabel("Modo Flash: ");
        this.longitudFocal35Camara = new JLabel("Longitud focal de 35mm: ");
        
        this.panelIzquierdoCamara.add(fabricanteCamara);
        this.panelIzquierdoCamara.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelIzquierdoCamara.add(modeloCamara);
        this.panelIzquierdoCamara.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelIzquierdoCamara.add(puntoFCamara);
        this.panelIzquierdoCamara.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelIzquierdoCamara.add(tiempoExposicionCamara);
        this.panelIzquierdoCamara.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelIzquierdoCamara.add(velocidadIsoCamara);
        this.panelIzquierdoCamara.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelIzquierdoCamara.add(distanciaFocalCamara);
        this.panelIzquierdoCamara.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelIzquierdoCamara.add(modoFlashCamara);
        this.panelIzquierdoCamara.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelIzquierdoCamara.add(longitudFocal35Camara);
        
        this.panelDerechoCamara = new JPanel();
        this.panelDerechoCamara.setLayout(new BoxLayout(panelDerechoCamara, BoxLayout.PAGE_AXIS));
        this.panelDerechoCamara.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        
        this.valorFabricanteCamara = new JLabel(textoVacio);
        this.valorModeloCamara = new JLabel(textoVacio);
        this.valorPuntoFCamara = new JLabel(textoVacio);
        this.valorTiempoExposicionCamara = new JLabel(textoVacio);
        this.valorVelocidadIsoCamara = new JLabel(textoVacio);
        this.valorDistanciaFocalCamara = new JLabel(textoVacio);
        this.valorModoFlashCamara = new JLabel(textoVacio);
        this.valorLongitudFocal35Camara = new JLabel(textoVacio);
        
        this.panelDerechoCamara.add(valorFabricanteCamara);
        this.panelDerechoCamara.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelDerechoCamara.add(valorModeloCamara);
        this.panelDerechoCamara.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelDerechoCamara.add(valorPuntoFCamara);
        this.panelDerechoCamara.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelDerechoCamara.add(valorTiempoExposicionCamara);
        this.panelDerechoCamara.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelDerechoCamara.add(valorVelocidadIsoCamara);
        this.panelDerechoCamara.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelDerechoCamara.add(valorDistanciaFocalCamara);
        this.panelDerechoCamara.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelDerechoCamara.add(valorModoFlashCamara);
        this.panelDerechoCamara.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelDerechoCamara.add(valorLongitudFocal35Camara);
        this.panelCamara.add(panelIzquierdoCamara, BorderLayout.WEST);
        this.panelCamara.add(panelDerechoCamara, BorderLayout.CENTER);                     
        
        //------------------------- PANEL GPS
        
        this.panelGPS = new JPanel();
        this.panelGPS.setLayout(new BorderLayout());
        TitledBorder bordeGPS = BorderFactory.createTitledBorder("  GPS  ");
        this.panelGPS.setBorder(bordeGPS);
         
        this.panelIzquierdoGPS = new JPanel();
        this.panelIzquierdoGPS.setLayout(new BoxLayout(panelIzquierdoGPS, BoxLayout.PAGE_AXIS));
        this.panelIzquierdoGPS.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        
        this.latitudGPS = new JLabel("Latitud: ");
        this.longitudGPS = new JLabel("Longitud: ");
        this.altitudGPS = new JLabel("Altitud: ");
         
        this.panelIzquierdoGPS.add(latitudGPS);
        this.panelIzquierdoGPS.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelIzquierdoGPS.add(longitudGPS);
        this.panelIzquierdoGPS.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelIzquierdoGPS.add(altitudGPS);
        
        this.panelDerechoGPS = new JPanel();
        this.panelDerechoGPS.setLayout(new BoxLayout(panelDerechoGPS, BoxLayout.PAGE_AXIS));
        this.panelDerechoGPS.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        
        this.valorLatitudGPS = new JLabel(textoVacio);
        this.valorLongitudGPS = new JLabel(textoVacio);
        this.valorAltitudGPS = new JLabel(textoVacio);
        
        this.panelDerechoGPS.add(valorLatitudGPS);
        this.panelDerechoGPS.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelDerechoGPS.add(valorLongitudGPS);
        this.panelDerechoGPS.add(Box.createRigidArea(new Dimension(0,5)));
        this.panelDerechoGPS.add(valorAltitudGPS);
     
        this.panelGPS.add(panelIzquierdoGPS, BorderLayout.WEST);
        this.panelGPS.add(panelDerechoGPS, BorderLayout.CENTER);            
        
        //-------------------------
        
        this.panelPrincipal.add(panelImagen);
        this.panelPrincipal.add(panelArchivo);
        this.panelPrincipal.add(panelOrigen);
        this.panelPrincipal.add(panelCamara);
        this.panelPrincipal.add(panelGPS);
        this.add(panelPrincipal, BorderLayout.NORTH);
        
        this.panelIzquierdoImagen.setPreferredSize(new Dimension((int)panelIzquierdoArchivo.getPreferredSize().getWidth(), 0));
        this.panelIzquierdoOrigen.setPreferredSize(new Dimension((int)panelIzquierdoArchivo.getPreferredSize().getWidth(), 0));
        this.panelIzquierdoCamara.setPreferredSize(new Dimension((int)panelIzquierdoArchivo.getPreferredSize().getWidth(), 0));
        this.panelIzquierdoGPS.setPreferredSize(new Dimension((int)panelIzquierdoArchivo.getPreferredSize().getWidth(), 0)); 
    }
    
    /*
    Esta funcion se encarga de mostrar los metadatos de la imagen
    */
    public void setMetadatos(File file)
    {
        try 
        {
            this.mostrarAtributosBasicos(file);
           
            IImageMetadata metadata = Sanselan.getMetadata(file);//aca ocupamos la librería para obtener los metadatos avanzados
           
            if (metadata instanceof JpegImageMetadata)//si los metadatos son procedentes de imagen JPG o JPEG
            {
                this.mostrarAtributosAvanzados((JpegImageMetadata) metadata);//mostramos los metadatos avanzados
            }
        }
        catch (ImageReadException ex) 
        {
        }
        catch (IOException ex) 
        {
        }  
        catch(ArrayIndexOutOfBoundsException ex)
        {
        }
     }
     
    /*
    Esta funcion muestra los metadatos basicos de una imagen
    */
     public void mostrarAtributosBasicos(File file)
     {
         try
         {
             BasicFileAttributes attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
            
             this.valorIdImagen.setText(textoVacio);
             this.valorDimensionesImagen.setText((new ImageIcon(file.getAbsolutePath())).getIconWidth()+"x"+(new ImageIcon(file.getAbsolutePath())).getIconHeight());
             this.valorAnchoImagen.setText((new ImageIcon(file.getAbsolutePath())).getIconWidth()+" píxeles");
             this.valorAltoImagen.setText((new ImageIcon(file.getAbsolutePath())).getIconHeight()+" píxeles");
             this.valorResolucionHorizontalImagen.setText(textoVacio);
             this.valorResolcuionVerticalImagen.setText(textoVacio);
             this.valorProfundidadBitsImagen.setText(this.getBitDepth(file)+"");
            
             this.valorNombreArchivo.setText(file.getName());
             this.valorTipoArchivo.setText(this.getExtension(file.getAbsolutePath()).toUpperCase());
             this.valorFechaCreacionArchivo.setText(new SimpleDateFormat("dd-MM-YYYY   hh:mm:ss").format(new Date(attr.creationTime().toMillis())));
             this.valorFechaModificacionArchivo.setText(new SimpleDateFormat("dd-MM-YYYY   hh:mm:ss").format(new Date(attr.lastModifiedTime().toMillis())));
             this.valorSizeArchivo.setText(this.getSize(attr.size()));
             this.valorPropietarioArchivo.setText(InetAddress.getLocalHost().getHostName()+" - "+System.getProperty("user.name"));
         
             this.valorAutoresOrigen.setText(textoVacio);
             this.valorFechaCapturaOrigen.setText(textoVacio);
             this.valorNombreProgramaOrigen.setText(textoVacio);
             this.valorCopyrightOrigen.setText(textoVacio);
             
             this.valorFabricanteCamara.setText(textoVacio);
             this.valorModeloCamara.setText(textoVacio);
             this.valorPuntoFCamara.setText(textoVacio);
             this.valorTiempoExposicionCamara.setText(textoVacio);
             this.valorVelocidadIsoCamara.setText(textoVacio);
             this.valorDistanciaFocalCamara.setText(textoVacio);
             this.valorModoFlashCamara.setText(textoVacio);
             this.valorLongitudFocal35Camara.setText(textoVacio); 

             this.valorLatitudGPS.setText(textoVacio);
             this.valorLongitudGPS.setText(textoVacio);
             this.valorAltitudGPS.setText(textoVacio);       
         }
         catch (IOException ex)
         {
         }         
     }
     
     /*
     Aca mostramos los metadatos basicos de una imagen, cuando esta es BMP
     */
     public void mostrarAtributosBasicosBMP(File file, ImageIcon imagen)
     {
         try
         {
             BasicFileAttributes attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
            
             this.valorIdImagen.setText(textoVacio);
             this.valorDimensionesImagen.setText(imagen.getIconWidth()+"x"+imagen.getIconHeight());
             this.valorAnchoImagen.setText(imagen.getIconWidth()+" píxeles");
             this.valorAltoImagen.setText(imagen.getIconHeight()+" píxeles");
             this.valorResolucionHorizontalImagen.setText(textoVacio);
             this.valorResolcuionVerticalImagen.setText(textoVacio);  
             this.valorProfundidadBitsImagen.setText(this.getBitDepth(file)+"");
             
             this.valorNombreArchivo.setText(file.getName());
             this.valorTipoArchivo.setText(this.getExtension(file.getAbsolutePath()).toUpperCase());
             this.valorFechaCreacionArchivo.setText(new SimpleDateFormat("dd-MM-YYYY   hh:mm:ss").format(new Date(attr.creationTime().toMillis())));
             this.valorFechaModificacionArchivo.setText(new SimpleDateFormat("dd-MM-YYYY   hh:mm:ss").format(new Date(attr.lastModifiedTime().toMillis())));
             this.valorSizeArchivo.setText(this.getSize(attr.size()));
             this.valorPropietarioArchivo.setText(InetAddress.getLocalHost().getHostName()+" - "+System.getProperty("user.name"));
         
             this.valorAutoresOrigen.setText(textoVacio);
             this.valorFechaCapturaOrigen.setText(textoVacio);
             this.valorNombreProgramaOrigen.setText(textoVacio);
             this.valorCopyrightOrigen.setText(textoVacio);
             
             this.valorFabricanteCamara.setText(textoVacio);
             this.valorModeloCamara.setText(textoVacio);
             this.valorPuntoFCamara.setText(textoVacio);
             this.valorTiempoExposicionCamara.setText(textoVacio);
             this.valorVelocidadIsoCamara.setText(textoVacio);
             this.valorDistanciaFocalCamara.setText(textoVacio);
             this.valorModoFlashCamara.setText(textoVacio);
             this.valorLongitudFocal35Camara.setText(textoVacio);  

             this.valorLatitudGPS.setText(textoVacio);
             this.valorLongitudGPS.setText(textoVacio);
             this.valorAltitudGPS.setText(textoVacio);         
         }
         catch (IOException ex)
         {
             
         }         
     }

     /*
     Funcion que se encarga de mostrar los atributos de la imagen (JPG o JPEG)
     */
     public void mostrarAtributosAvanzados(JpegImageMetadata jpegMetadata)
     {
         TiffField field;
         DecimalFormat formato = new DecimalFormat("#.#");
         
         field = jpegMetadata.findEXIFValue(TiffConstants.TIFF_TAG_ImageUniqueID);
         if (field == null) 
         {
             this.valorIdImagen.setText(" ");
         }
         else 
         {
             this.valorIdImagen.setText(field.getValueDescription());
         }
         field = jpegMetadata.findEXIFValue(TiffConstants.TIFF_TAG_XResolution);
         if (field == null) 
         {
             this.valorResolucionHorizontalImagen.setText(" ");
         }
         else 
         {
             this.valorResolucionHorizontalImagen.setText(field.getValueDescription()+" ppp");
         }
         field = jpegMetadata.findEXIFValue(TiffConstants.TIFF_TAG_YResolution);
         if (field == null) 
         {
             this.valorResolcuionVerticalImagen.setText(" ");
         }
         else 
         {
             this.valorResolcuionVerticalImagen.setText(field.getValueDescription()+" ppp");
         }
         field = jpegMetadata.findEXIFValue(TiffConstants.TIFF_TAG_Artist);
         if (field == null) 
         {
             this.valorAutoresOrigen.setText(" ");
         }
         else 
         {
             this.valorAutoresOrigen.setText(field.getValueDescription().substring(1, field.getValueDescription().length()-1));
         }
         field = jpegMetadata.findEXIFValue(TiffConstants.TIFF_TAG_DateTime);
         if (field == null) 
         {
             this.valorFechaCapturaOrigen.setText(" ");
         }
         else 
         {
             this.valorFechaCapturaOrigen.setText(new SimpleDateFormat("dd-MM-YYYY   hh:mm:ss").format((Date)field.getValue()));
         }
         field = jpegMetadata.findEXIFValue(TiffConstants.TIFF_TAG_Software);
         if (field == null) 
         {
             this.valorNombreProgramaOrigen.setText(" ");
         }
         else 
         {
             this.valorNombreProgramaOrigen.setText(field.getValueDescription().substring(1, field.getValueDescription().length()-1));
         }         
         field = jpegMetadata.findEXIFValue(TiffConstants.TIFF_TAG_Copyright);
         if (field == null) 
         {
             this.valorCopyrightOrigen.setText(" ");
         }
         else 
         {
             this.valorCopyrightOrigen.setText(field.getValueDescription().substring(1, field.getValueDescription().length()-1));
         }         
         field = jpegMetadata.findEXIFValue(TiffConstants.TIFF_TAG_Make);
         if (field == null) 
         {
             this.valorFabricanteCamara.setText(" ");
         }
         else 
         {
             this.valorFabricanteCamara.setText(field.getValueDescription().substring(1, field.getValueDescription().length()-1));
         }         
         field = jpegMetadata.findEXIFValue(TiffConstants.TIFF_TAG_Model);
         if (field == null) 
         {
             this.valorModeloCamara.setText(" ");
         }
         else 
         {
             this.valorModeloCamara.setText(field.getValueDescription().substring(1, field.getValueDescription().length()-1));
         }         
         field = jpegMetadata.findEXIFValue(TiffConstants.TIFF_TAG_FNumber);
         if (field == null) 
         {
             this.valorPuntoFCamara.setText(" ");
         }
         else 
         {
             this.valorPuntoFCamara.setText("f/"+formato.format((double)((RationalNumber)field.getValue()).doubleValue()));
         }         
         field = jpegMetadata.findEXIFValue(TiffConstants.TIFF_TAG_ExposureTime);
         if (field == null) 
         {
             this.valorTiempoExposicionCamara.setText(" ");
         }
         else 
         {
             this.valorTiempoExposicionCamara.setText(field.getValueDescription()+" s");
         }         
         field = jpegMetadata.findEXIFValue(TiffConstants.TIFF_TAG_ISOSpeedRatings);
         if (field == null) 
         {
             this.valorVelocidadIsoCamara.setText(" ");
         }
         else 
         {
             this.valorVelocidadIsoCamara.setText("ISO - "+field.getValueDescription());
         }         
         field = jpegMetadata.findEXIFValue(TiffConstants.TIFF_TAG_FocalLength);
         if (field == null) 
         {
             this.valorDistanciaFocalCamara.setText(" ");
         }
         else 
         {
             this.valorDistanciaFocalCamara.setText(Math.round(((RationalNumber)field.getValue()).doubleValue())+" mm");
         }         
         field = jpegMetadata.findEXIFValue(TiffConstants.TIFF_TAG_Flash);
         if (field == null) 
         {
             this.valorModoFlashCamara.setText(" ");
         }
         else 
         {
             int valor = 1500;
             try
             {
                 if(((String)field.getValueDescription()).length() > 2)
                 {
                    valor = Integer.parseInt(((String)field.getValueDescription()).split(" ")[0]);
                 }
                 else
                 {
                     valor = Integer.parseInt((String)field.getValueDescription());
                 }
             }
             catch(NumberFormatException ex)
             {
                 
             }
             
             if(valor == 0)
             {
                 this.valorModoFlashCamara.setText("Sin flash");
             }
             else if(valor == 1)
             {
                 this.valorModoFlashCamara.setText("Flash");
             }
             else if(valor == 5)
             {
                 this.valorModoFlashCamara.setText("Flash, sin retorno estroboscópico");
             }
             else if(valor == 7)
             {
                 this.valorModoFlashCamara.setText("Flash, con retorno estroboscópico");
             }
             else if(valor == 9)
             {
                 this.valorModoFlashCamara.setText("Flash, obligatorio");
             }
             else if(valor == 13)
             {
                 this.valorModoFlashCamara.setText("Flash, obligatorio, sin retorno estroboscópico");
             }
             else if(valor == 15)
             {
                 this.valorModoFlashCamara.setText("Flash, obligatorio, con retorno estroboscópico");
             }
             else if(valor == 16)
             {
                 this.valorModoFlashCamara.setText("Sin flash, obligatorio");
             }
             else if(valor == 24)
             {
                 this.valorModoFlashCamara.setText("Sin flash, automático");
             }
             else if(valor == 25)
             {
                 this.valorModoFlashCamara.setText("Flash, automático");
             }
             else if(valor == 29)
             {
                 this.valorModoFlashCamara.setText("Flash, automático, sin retorno estrobocópico");
             }
             else if(valor == 31)
             {
                 this.valorModoFlashCamara.setText("Flash, automático, con retorno estroboscópico");
             }
             else if(valor == 32)
             {
                 this.valorModoFlashCamara.setText("Sin función de flash");
             }
             else if(valor == 65)
             {
                 this.valorModoFlashCamara.setText("Flash, ojos rojos");
             }
             else if(valor == 69)
             {
                 this.valorModoFlashCamara.setText("Flash, ojos rojos, sin retorno estroboscópico");
             }
             else if(valor == 71)
             {
                 this.valorModoFlashCamara.setText("Flash, ojos rojos, con retorno estroboscópico");
             }
             else if(valor == 73)
             {
                 this.valorModoFlashCamara.setText("Flash, obligatorio, ojos rojos");
             }
             else if(valor == 77)
             {
                 this.valorModoFlashCamara.setText("Flash, obligatorio, ojos rojos, sin retorno estroboscópico");
             }
             else if(valor == 79)
             {
                 this.valorModoFlashCamara.setText("Flash, obligatorio, ojos rojos, con retorno estroboscópico");
             }
             else if(valor == 89)
             {
                 this.valorModoFlashCamara.setText("Flash, automático, ojos rojos");
             }
             else if(valor == 93)
             {
                 this.valorModoFlashCamara.setText("Flash, automático, ojos rojos, sin retorno estroboscópico");
             }
             else if(valor == 95)
             {
                 this.valorModoFlashCamara.setText("Flash, automático, ojos rojos, con retorno estroboscópico");
             }
             else
             {
                 this.valorModoFlashCamara.setText("Desconocido: "+field.getValue());
             }
             
         }
         field = jpegMetadata.findEXIFValue(TiffConstants.TIFF_TAG_FocalLengthIn35mmFilm);
         if (field == null) 
         {
             this.valorLongitudFocal35Camara.setText(" ");
         }
         else 
         {
             this.valorLongitudFocal35Camara.setText(field.getValue()+"");
         }
         field = jpegMetadata.findEXIFValue(TiffConstants.TIFF_TAG_GPSLatitude);
         TiffField field2 = jpegMetadata.findEXIFValue(TiffConstants.TIFF_TAG_GPSLatitudeRef);
         if (field == null || field2 == null) 
         {
             this.valorLatitudGPS.setText(" ");
         }
         else if(field2.getValueDescription().toLowerCase().equals("w") || field2.getValueDescription().toLowerCase().equals("e") || field2.getValueDescription().toLowerCase().equals("n") || field2.getValueDescription().toLowerCase().equals("s"))
         {
            this.valorLatitudGPS.setText(field.getValueDescription().split(",")[0]+", "+( (Math.round ((Double.parseDouble(field.getValueDescription().split(",")[1].split("/")[0]))/(Double.parseDouble(field.getValueDescription().split(",")[1].split("/")[1]))) )+", "+field.getValueDescription().split(",")[2])+" | "+ field2.getValue());
         }
         else 
         {
             this.valorLatitudGPS.setText(" ");
         }           
         field = jpegMetadata.findEXIFValue(TiffConstants.TIFF_TAG_GPSLongitude);
         field2 = jpegMetadata.findEXIFValue(TiffConstants.TIFF_TAG_GPSLongitudeRef);
         if (field == null || field2 == null) 
         {
             this.valorLongitudGPS.setText(" ");
         }
         else 
         {
             this.valorLongitudGPS.setText(field.getValueDescription().split(",")[0]+", "+( (Math.round ((Double.parseDouble(field.getValueDescription().split(",")[1].split("/")[0]))/(Double.parseDouble(field.getValueDescription().split(",")[1].split("/")[1]))) )+", "+field.getValueDescription().split(",")[2])+" | "+ field2.getValue());
         }           
         field = jpegMetadata.findEXIFValue(TiffConstants.TIFF_TAG_GPSAltitude);
         if (field == null) 
         {
             this.valorAltitudGPS.setText(" ");
         }
         else 
         {
             this.valorAltitudGPS.setText( (((RationalNumber)field.getValue()).doubleValue())+"" );
         }           
     }

     public Integer tryParse(String text) 
     {
         try 
         {
             return new Integer(text);
         } 
         catch (NumberFormatException e) 
         {
             return null;
         }   
     }
     
     public void mostrarNada()
     {
         this.valorIdImagen.setText(textoVacio);
         this.valorDimensionesImagen.setText(textoVacio);
         this.valorAnchoImagen.setText(textoVacio);
         this.valorAltoImagen.setText(textoVacio);
         this.valorProfundidadBitsImagen.setText(textoVacio);
         this.valorResolucionHorizontalImagen.setText(textoVacio);
         this.valorResolcuionVerticalImagen.setText(textoVacio);     
         this.valorNombreArchivo.setText(textoVacio);
         this.valorTipoArchivo.setText(textoVacio);
         this.valorFechaCreacionArchivo.setText(textoVacio);
         this.valorFechaModificacionArchivo.setText(textoVacio);
         this.valorSizeArchivo.setText(textoVacio);
         this.valorPropietarioArchivo.setText(textoVacio);        
         this.valorAutoresOrigen.setText(textoVacio);
         this.valorFechaCapturaOrigen.setText(textoVacio);
         this.valorNombreProgramaOrigen.setText(textoVacio);
         this.valorCopyrightOrigen.setText(textoVacio);         
         this.valorFabricanteCamara.setText(textoVacio);
         this.valorModeloCamara.setText(textoVacio);
         this.valorPuntoFCamara.setText(textoVacio);
         this.valorTiempoExposicionCamara.setText(textoVacio);
         this.valorVelocidadIsoCamara.setText(textoVacio);
         this.valorDistanciaFocalCamara.setText(textoVacio);
         this.valorModoFlashCamara.setText(textoVacio);
         this.valorLongitudFocal35Camara.setText(textoVacio);
         this.valorLatitudGPS.setText(textoVacio);
         this.valorLongitudGPS.setText(textoVacio);
         this.valorAltitudGPS.setText(textoVacio);
     }
     
     /*
     Funcion que se encarga de calcular la profundidad de bits de una imagen
     */
     public int getBitDepth(File f) throws IOException 
     {
         ImageInputStream in = ImageIO.createImageInputStream(f);
         if(in == null) 
         {
             throw new IOException("Can't create ImageInputStream!");
         }

         try
         {
             Iterator<ImageReader> readers = ImageIO.getImageReaders(in);

             ImageReader reader;
             if(!readers.hasNext()) 
             {
                 throw new IOException("Can't read image format!");
             }
             else 
             {
                 reader = readers.next();
             }
             
             reader.setInput(in,true,true);
             int bitDepth = reader.getImageTypes(0).next().getColorModel().getPixelSize();
             reader.dispose();
             
             return bitDepth;
         }
         finally 
         {
            in.close();
         }
     } 
     
     /*
     Esta funcion retorna la extension de un archivo
     */
     public String getExtension(String ruta)
     {
         String extension = "";

         int i = ruta.lastIndexOf('.');
         int p = Math.max(ruta.lastIndexOf('/'), ruta.lastIndexOf('\\'));

         if (i > p) 
         {
             extension = ruta.substring(i+1);
         }
         
         return extension;
     }
     
     /*
     Esta funcion valida y retorna el valor de una imagen, como el tamaño se mide en bits
     esta funcion los acerca a KB, MB, etc..
     
     EJ: si son 1024 bits -> funcion -> 1 KB
     */
     public String getSize(long size)
     {
         double sizeFinal = 0;
         DecimalFormat formato = new DecimalFormat("#.##");
         String sizeString = "";
         
         if(size > 1024)
         {
             if(size > Math.pow(1024, 2))
             {
                 if(size > Math.pow(1024, 3))
                 {
                     if(size > Math.pow(1024, 4))
                     {
                         System.out.println("IMPOSIBRU!!!!!");
                     }
                     else
                     {
                        sizeFinal = (double)((double)size/(double)(Math.pow(1024, 3)));
                        sizeString = formato.format(sizeFinal) + " GB";                         
                     }
                 }
                 else
                 {
                     sizeFinal = (double)((double)size/(double)(Math.pow(1024, 2)));
                     sizeString = formato.format(sizeFinal) + " MB";
                 }
             }
             else
             {
                 sizeFinal = (double)(size/1024);
                 sizeString = formato.format(sizeFinal) + " KB";
             }
         }
         else
         {
             sizeFinal = (double)size;
             sizeString = formato.format(sizeFinal) + "Bytes";
         }
         return sizeString;
     }
}
