/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Collage;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import net.coobird.thumbnailator.Thumbnails;

/**
 *
 * @author HoLeX
 */
public class PanelAreaTrabajo extends JPanel implements MouseListener, MouseMotionListener
{
    private PanelCollage panelCollage;
    private ArrayList<ImagenCollage> imagenes;//array con imagenes actuales
    private ArrayList<ImagenCollage> imagenesAux;//array con imagenes originales agregadas
    private Stack desHacer;
    private Stack reHacer;
    
    private ImagenCollage imagenSeleccionada;
    
    private int movX;
    private int movY;
    private int indice;
    
    public PanelAreaTrabajo(PanelCollage panelCollage)
    {
        this.indice = 0;
        this.panelCollage = panelCollage;
        this.setPreferredSize(new Dimension(1366,634));
        this.imagenes = new ArrayList<ImagenCollage>();
        this.imagenesAux = new ArrayList<ImagenCollage>();
            
        this.setBackground(Color.WHITE);
        
        this.desHacer = new Stack();
        this.reHacer = new Stack();
        this.addMouseListener(this);
        this.addMouseMotionListener(this); 
        this.agregarImagen(panelCollage.getRutaActual()); 
        this.imagenSeleccionada = null;
    }
    //segun la posicion del mouse retornamos la imagen seleccionada
    public ImagenCollage buscarImagen(int x, int y)
    {
        for (int i = (this.imagenes.size()-1); i >= 0; i--)
        {
            ImagenCollage imagen = this.imagenes.get(i);
            
            if(imagen.estado(x, y))
            {
                return imagen;
            }
        }
        return null;
    }
    //agregamos la iamgen al collage a partir de la ruta de esta
    public void agregarImagen(String ruta)
    {
        if(indice!=0 && this.imagenes.size()>0)
        {
            this.desHacer.push(this.imagenes.get(this.imagenes.size()-1));
        }
        File file = new File(ruta);
        Random r = new Random();//este random lo usamos para la rotacion de la imagen
        
        try
        {
            BufferedImage imagenOriginal = ImageIO.read(file);//leemos la imagen
            
            BufferedImage image = this.escalarCercano(imagenOriginal, 300);//la escalamos
            
            int numX = r.nextInt(1366 - 300);//pedimos un x random
            int numY = r.nextInt(634 - 300);//pedimos un y random
            
            int ancho = imagenOriginal.getWidth();
            int porcentaje = (int) Math.round((double)(image.getWidth()*100)/(double)ancho); 
          
            imagenes.add(new ImagenCollage(image, numX, numY, image.getWidth(), image.getHeight(), r.nextInt(359), file, porcentaje, imagenOriginal));
            imagenesAux.add(new ImagenCollage(image, numX, numY, image.getWidth(), image.getHeight(), r.nextInt(350), file, porcentaje, imagenOriginal));
            this.repaint();
            this.indice = 1;
        }
        catch (IOException ex)
        { 
        }
    }
    
    
    public BufferedImage escalarCercano(BufferedImage imagen, int max)
    {
        int ancho = imagen.getWidth();
        int alto = imagen.getHeight();
        
        if(ancho < max && alto < max)
        {
            Image image = imagen.getScaledInstance(imagen.getWidth(), imagen.getHeight(), Image.SCALE_SMOOTH);
                
            return this.imageIconToBufferedImage(image);
        }
        else
        {
            if(ancho >= alto)
            {
                double valor = (double)(((double)max*100)/(double)ancho);
                double porcentaje = (double)((double)Math.round(valor)/(double)100);
                
                Image image = imagen.getScaledInstance((int) Math.round(ancho*porcentaje), (int) Math.round(alto*porcentaje), Image.SCALE_SMOOTH);
                
                return this.imageIconToBufferedImage(image);
            }
            else if(ancho < alto)
            {
                double valor = (double)(((double)max*100)/(double)alto);
                double porcentaje = (double)((double)Math.round(valor)/(double)100);
                
                Image image = imagen.getScaledInstance((int) Math.round(ancho*porcentaje), (int) Math.round(alto*porcentaje), Image.SCALE_SMOOTH);
                
                return this.imageIconToBufferedImage(image);
            }
            else
            {
                return null;
            }
        }
    }

    public void generarCollagePatron(int[][] matriz, int ancho, int alto)
    {      
        if(this.imagenes.size()>0)
        {
            this.desHacer.push(this.imagenes.get( this.imagenes.size()-1 ) );
        }
        this.imagenes.clear();
        Random r = new Random();
        
        ArrayList<ImagenCollage> array = new ArrayList<ImagenCollage>();
        
        for (int i = 0; i < ancho; i++)
        {
            for (int j = 0; j < alto; j++)
            {
                if(matriz[i][j] == 0)
                {
                    int numero = r.nextInt(this.imagenesAux.size());
                    
                    ImagenCollage imagenCollage = this.imagenesAux.get(numero);
                    
                    BufferedImage imagenOriginal = imagenCollage.getImagenOriginal();//null;
                    
//                    if(imagenCollage.isRecortada())
//                    {
//                        imagenOriginal = imagenCollage.getImagenOriginal();
//                    }
//                    else
//                    {
//                        try 
//                        {
//                            imagenOriginal = ImageIO.read(imagenCollage.getFile());
//                        }
//                        catch (IOException ex) 
//                        {
//                        } 
//                    }
                    BufferedImage imagenEscalada = this.escalarCercano(imagenCollage.getImagen(), 40);
                    int porcentaje = (int) Math.round((double)(imagenEscalada.getWidth()*100)/(double)imagenOriginal.getWidth());
                    ImagenCollage imagen = new ImagenCollage(imagenEscalada, i, j, imagenEscalada.getWidth(), imagenEscalada.getHeight(), imagenCollage.getRotacion(), imagenCollage.getFile(), porcentaje, imagenOriginal);
                    imagen.setRotacion(r.nextInt(359));
                    
                    if(imagenCollage.isRecortada())
                    {
                        imagen.setRecortada(true);
                    }

                    array.add(imagen);
                    
                    if(j+20 < alto)//el 20 representa la cantidad de separacion en alto entre una imagen y otra
                    {
                        j+=20;
                    }
                    else if(j+20 >= alto)
                    {
                        j = alto;
                    }
                }
            }
            if(i+30 < ancho)//el 30 representa la cantidad de separacion en ancho entre una imagen y otra
            {
                i+=30;
            }            
            else if(i+30 >= ancho)
            {
                i = ancho;
            }
            Cursor cursor = new Cursor(Cursor.WAIT_CURSOR);
            this.panelCollage.getVentana().setCursor(cursor);
        }
        Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
        this.panelCollage.getVentana().setCursor(cursor); 
        this.imagenes = array;
        this.imagenSeleccionada = null;
        this.repaint();
    }
    
    /*
    Deshacemos el patron creado y mostramos la imagenes originales que estan en imagenesAux
    */
    public void deshacerCollage()
    {   
        this.imagenSeleccionada = null;
        this.imagenes.clear();
        
        for(ImagenCollage imagen : this.imagenesAux)
        {
            this.imagenes.add(imagen);
        } 
        this.repaint();
    }
    
    public void reHacer()
    {
        /*if(!this.reHacer.empty())
        {
            Object o = this.reHacer.pop();
            if( o instanceof ImagenCollage)
            {
                this.imagenes.add((ImagenCollage)o);
            }
            
            if(this.imagenes.size()>0)
            {
                if(this.imagenes.size()<70)
                {
                    this.desHacer.push(this.imagenes);
                }
                else
                {
                    this.desHacer.push(this.imagenes.get( this.imagenes.size()-1 ));
                }
                
            }
            this.imagenSeleccionada = null;
            Object o = this.reHacer.pop();
            if( o instanceof ImagenCollage )
            {
                this.imagenes.add((ImagenCollage)o);
            }
            else
            {
                ArrayList<ImagenCollage> array = (ArrayList<ImagenCollage>) this.reHacer.pop();
                this.imagenes.clear();
                this.imagenes = array;
            }*/
            this.repaint();
        
    }
    
    public void desHacer()
    {
        /*if(!this.desHacer.empty() )
        {
            if(this.imagenes.size()>0)
            {
                this.reHacer.push(this.imagenes.get(this.imagenes.size()-1));
            }
            this.imagenSeleccionada = null;
            
            Object o = this.desHacer.pop();
            if( o instanceof ImagenCollage )
            {
                this.imagenes.add((ImagenCollage)o);
            }
            else
            {
                ArrayList<ImagenCollage> array = (ArrayList<ImagenCollage>) o;
                this.imagenes.clear();
                this.imagenes = array;
            }*/
            this.repaint();
            
            
        
        
    }
    
    
    public BufferedImage EscalarImagen(BufferedImage file, int anchoMax, int altoMax)
    {
        int ancho = (new ImageIcon(file)).getIconWidth();
        int alto = (new ImageIcon(file)).getIconHeight();
        
        BufferedImage image = null;
        try
        {
            image = Thumbnails.of(file).size(ancho, alto).rotate(0).asBufferedImage();
        }
        catch (IOException ex)
        {
        }
        
        Image imagenFinal = null;
        
        if(ancho < anchoMax && alto < altoMax)
        {
            imagenFinal = file;
        }
        else if(ancho > alto)
        {
            int x = ((alto*anchoMax)/ancho);
            imagenFinal = image.getScaledInstance(anchoMax, x, Image.SCALE_SMOOTH);
        }
        else if(alto > ancho)
        {
            int x = ((ancho*altoMax)/alto);
            imagenFinal = image.getScaledInstance(x, altoMax, Image.SCALE_SMOOTH);
        }
        else
        {
            imagenFinal = image.getScaledInstance(anchoMax, altoMax, Image.SCALE_SMOOTH);
        }
        
        return imageIconToBufferedImage(imagenFinal);
    }         
    
    public BufferedImage imageIconToBufferedImage(Image im) 
    {
        BufferedImage bi = new BufferedImage(im.getWidth(null),im.getHeight(null),BufferedImage.TYPE_INT_RGB);
        Graphics bg = bi.getGraphics();
        bg.drawImage(im, 0, 0, null);
        bg.dispose();
        return bi;
    }    

    public ImagenCollage getImagenSeleccionada()
    {
        return this.imagenSeleccionada;
    }
    
    @Override
    public void mouseClicked(MouseEvent e)
    {
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        if(e.getButton() == MouseEvent.BUTTON1)
        {
            ImagenCollage imagen = buscarImagen(e.getX(), e.getY());

            if(imagen != null)
            {
                this.imagenSeleccionada = imagen;
                this.activarBotones(true, this.imagenSeleccionada);
                this.movX = e.getX()-this.imagenSeleccionada.getCoordenadaX();
                this.movY = e.getY()-this.imagenSeleccionada.getCoordenadaY(); 
            }                
            else
            {
                this.imagenSeleccionada = null;
                this.activarBotones(false, this.imagenSeleccionada);
            }
            this.repaint();
            this.repaint();
            this.repaint();
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

    @Override
    public void mouseDragged(MouseEvent e)
    {       
        if(this.imagenSeleccionada != null)
        {
            int nuevoX = e.getX()-movX;
            int nuevoY = e.getY()-movY;

            this.imagenSeleccionada.setCoordenadaX(nuevoX);
            this.imagenSeleccionada.setCoordenadaY(nuevoY);

            this.repaint();
            this.repaint();
            this.repaint(); 
        }          
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        
    }   
    
    public void activarBotones(boolean flag, ImagenCollage imagen)
    {
        this.panelCollage.getBarraHerramientas().activarBotones(flag, imagen);
    }

    /*
    Eliminamos la imagen del arrayList
    */
    public void eliminarImagen(ImagenCollage imagenDelete)
    {
        this.desHacer.push( (new ArrayList<ImagenCollage>()).add(imagenDelete) );
        
        for (int i = 0; i < imagenes.size(); i++)
        {
            ImagenCollage imagen = imagenes.get(i);
            
            if(imagen.equals(imagenDelete))
            {
                this.imagenes.remove(imagen);
                
            }
        }
        for(ImagenCollage imagen : this.imagenesAux)
        {
            if(imagen.equals(imagenDelete))
            {
                this.imagenesAux.remove(imagen);
            }
        }
        this.imagenSeleccionada = null;
        this.panelCollage.getBarraHerramientas().activarBotones(false, imagenDelete);
        this.repaint();
    }

    /*
    Metodo usado cuando recortamos o aplicamos un marco, esta funcion sobreescribe la imagen con el efecto
    por la imagen original
    */
    public void sobreescribirImagen(BufferedImage imagen, boolean recortada)
    {
        this.desHacer.push(this.imagenes.clone());
        for(ImagenCollage imagenCollage : this.imagenes)
        {
            if(imagenCollage.equals(this.imagenSeleccionada))
            { 
                if(recortada)
                {
                    BufferedImage imagenEscalada = this.escalarCercano(imagen, 300);
                    int porcentaje = (int) Math.round((double)(imagenEscalada.getWidth()*100)/(double)imagen.getWidth()); 

                    this.imagenSeleccionada.setImagen(imagen);
                    this.imagenSeleccionada.setImagenOriginal(imagen);
                    this.imagenSeleccionada.setRecortada(recortada);
                    this.imagenSeleccionada.setRotacion(imagenSeleccionada.getRotacion());
                    this.panelCollage.getBarraHerramientas().getPanelRedimension().setRedimension(porcentaje+" %");  
                    this.panelCollage.getBarraHerramientas().getPanelRedimension().setInit(porcentaje);                      
                }
                else
                {
                    BufferedImage imagenEscalada = this.escalarCercano(imagen, 300);
                    int porcentaje = (int) Math.round((double)(imagenEscalada.getWidth()*100)/(double)imagen.getWidth()); 
                    
                    this.imagenSeleccionada.setWidth(imagenEscalada.getWidth());
                    this.imagenSeleccionada.setHeight(imagenEscalada.getHeight());
                    this.imagenSeleccionada.setImagen(imagenEscalada);
                    try
                    {
                        this.imagenSeleccionada.setImagenOriginal(ImageIO.read(this.imagenSeleccionada.getFile()));
                    }
                    catch (IOException ex)
                    {
                    }
                    this.imagenSeleccionada.setRecortada(recortada);
                    this.imagenSeleccionada.setRotacion(imagenSeleccionada.getRotacion());
                    this.imagenSeleccionada.setPorcentajeRedimension(porcentaje);
                    
                    this.panelCollage.getBarraHerramientas().getPanelRedimension().setRedimension(porcentaje+" %");  
                    this.panelCollage.getBarraHerramientas().getPanelRedimension().setInit(porcentaje);                    
                }
            }
        }
        this.repaint();
    }
    
    /*
    Funcion que recorre el arraylist y cambia la imagen a la ultima posicion, de este modo
    se pintara al frente de las demas
    */
    public void moverAlFrente(ImagenCollage imagen)
    {
        ArrayList<ImagenCollage> array = new ArrayList<ImagenCollage>();

        for(ImagenCollage imagenCollage : this.imagenes)
        {
            if(!imagenCollage.equals(imagen))
            {
                array.add(imagenCollage);
            }
        }
        array.add(imagen);
        
        this.imagenes.clear();
        this.imagenes = array;
        this.imagenSeleccionada = imagen;
        this.repaint();
    }
    
    /*
    Funcion que recorre el arraylist y cambia la imagen a la primera posicion la imagen, de este modo
    se pintara al ultimo de las demas    
    */
    public void moverAlFondo(ImagenCollage imagen)
    {
        ArrayList<ImagenCollage> array = new ArrayList<>();

        array.add(imagen);
        
        for(ImagenCollage imagenCollage : this.imagenes)
        {
            if(!imagenCollage.equals(imagen))
            {
                array.add(imagenCollage);
            }
        }
        
        this.imagenes.clear();
        this.imagenes = array;
        this.imagenSeleccionada = imagen;
        this.repaint();
    }
    
    public BufferedImage RedimensionarImagen(BufferedImage imagen, int anchoMax, int altoMax)
    {
        int anchoImagen = imagen.getWidth();
        int altoImagen = imagen.getHeight();
        
        Image imagenFinal = null;
        
        if(anchoImagen >= altoImagen)
        {
            double porcentaje = (double) ((double) altoMax/(double) altoImagen);
            double nuevoAncho = (double) (anchoImagen * porcentaje);
            double nuevoAlto = (double) (altoImagen * porcentaje);
            
            imagenFinal = imagen.getScaledInstance((int) nuevoAncho, (int)nuevoAlto, Image.SCALE_SMOOTH);
        }
        else if(anchoImagen < altoImagen)
        {
            double porcentaje = (double) ((double) anchoMax/(double) anchoImagen);
            double nuevoAncho = (double) (anchoImagen * porcentaje);
            double nuevoAlto = (double) (altoImagen * porcentaje);
            
            imagenFinal = imagen.getScaledInstance((int) nuevoAncho, (int)nuevoAlto, Image.SCALE_SMOOTH);            
        }     
        return this.imageIconToBufferedImage(imagenFinal);
    }
    
    /*
    Metodo que guarda el collage 
    */
    public void guardarImagen(String ruta)
    {
        BufferedImage imagen = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = imagen.createGraphics();
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
        for(ImagenCollage imagenCollage:this.imagenes)
        {
            imagenCollage.dibujar(g2);
        }
        g2.dispose();        
        
        File outputFile = new File(ruta);

        if(outputFile.exists())
        {
            int opcion = JOptionPane.showConfirmDialog(null, "Desea sobreescribir la imagen?", "Guardar como...", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            
            if(opcion == 0)
            {
                try
                {
                    ImageIO.write(imagen,"jpg", outputFile);
                }
                catch (IOException e)
                {
                    JOptionPane.showMessageDialog(null, "Error al guardar la imagen", "Error", JOptionPane.ERROR_MESSAGE);
                }  
            }
        }        
        else
        {
            try
            {
                ImageIO.write(imagen,"jpg", outputFile);
            }
            catch (IOException e)
            {
                JOptionPane.showMessageDialog(null, "Error al guardar la imagen", "Error", JOptionPane.ERROR_MESSAGE);
            }             
        }
    }
    
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);      
        for(ImagenCollage imagen: this.imagenes)
        {
            imagen.dibujar(g);    
        }
        if(this.imagenSeleccionada != null)
        {
            this.imagenSeleccionada.dibujar(g);
            double radians = Math.toRadians(this.imagenSeleccionada.getRotacion());
            AffineTransform af = new AffineTransform();
            af.rotate(radians, this.imagenSeleccionada.getCoordenadaX()+(this.imagenSeleccionada.getWidth()/2), this.imagenSeleccionada.getCoordenadaY()+(this.imagenSeleccionada.getHeight()/2));
            ((Graphics2D)g).setTransform(af);
            g.setColor(Color.cyan);
            g.drawRect(this.imagenSeleccionada.getCoordenadaX()-1, this.imagenSeleccionada.getCoordenadaY()-1, this.imagenSeleccionada.getWidth()+1, this.imagenSeleccionada.getHeight()+1);
            af.rotate(-radians, this.imagenSeleccionada.getCoordenadaX()+(this.imagenSeleccionada.getWidth()/2), this.imagenSeleccionada.getCoordenadaY()+(this.imagenSeleccionada.getHeight()/2));
            ((Graphics2D)g).setTransform(af);
        }
    }

    public ArrayList<ImagenCollage> getImagenes()
    {
        return imagenes;
    }

    public void setImagenes(ArrayList<ImagenCollage> imagenes)
    {
        this.imagenes = imagenes;
    }

    public PanelCollage getPanelCollage()
    {
        return panelCollage;
    }

    public void setPanelCollage(PanelCollage panelCollage)
    {
        this.panelCollage = panelCollage;
    }
    
    
    
}
