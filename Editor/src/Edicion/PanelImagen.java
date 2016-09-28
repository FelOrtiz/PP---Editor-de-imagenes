/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Edicion;

import Figuras.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;


/**
 *
 * @author Yerco
 */

public class PanelImagen extends JLabel implements MouseWheelListener, MouseMotionListener, MouseListener
{
    private Imagen imagen;

    private PanelEdicion panelEdicion;
    
    /*
     * Para El Paint
     */
    private final static int CIRCULO = 1, OVALO = 2, RECTANGULO = 3,
            ROMBO = 4, ESTRELLA = 5, PENTAGONO = 6, LINEA = 7, LAPIZ = 8,
            GOMA = 9, SPRITE= 10, PUNTO = 11,SELECCIONAR= 12, PUNTOCIRCULO = 13,PUNTOCUADRADO = 14, PUNTOROMBO = 15, PUNTOESTRELLA=16, NULO = 0;
    
    private int tamanioPunto;
    
    private ArrayList<Figura> figuras;
    private Stack desHacer, reHacer;
    private Figura figuraSeleccionada;
    private boolean estaDibujando;
    private int modoDibujo;
    private int movX, movY;
    private int xInicioZonaDibujo;
    private int yInicioZonaDibujo;
    private int xFinalZonaDibujo;
    private int yFinalZonaDibujo;
    private int inicioX, finX, inicioY, finY;
    private Color colorBorde, colorRelleno;
    private int tamanioBorde;
   
    private Segmento segmento;
    private SegmentoSprite segmentoSprite;
    
    //Cnfiguracion De La Linea
    private int tipoLinea;
    private int tipoGuionLinea;
    private int tipoConectorInicio;
    private int tipoConectorFinal;
    
    public PanelImagen(PanelEdicion panelEdicion)
    {
        this.panelEdicion = panelEdicion;
        this.setLayout(new BorderLayout());  
        
        this.addMouseWheelListener(this);
        
        this.tamanioPunto = 0;
        
        this.segmento = null;
        this.segmentoSprite = null;
        
        this.tipoLinea = 0;
        this.tipoGuionLinea = 0;
        this.tipoConectorInicio = 0;
        this.tipoConectorFinal = 0;

        
        this.figuras = new ArrayList<Figura>();
        this.desHacer = new Stack();
        this.reHacer = new Stack();
        
        this.colorBorde = Color.BLACK;
        this.colorRelleno = null;
        this.tamanioBorde = 5;
        this.tamanioPunto = 0;
        
        this.movX = 0;
        this.movY = 0;
        this.modoDibujo = 0;
        this.inicioX = 0;
        this.finX = 0;
        this.inicioY = 0;
        this.finY = 0;
        
        this.estaDibujando = false;
        super.setBackground(Color.WHITE);
        super.setOpaque(true);
        super.setPreferredSize(new Dimension(600, 400));
        this.xInicioZonaDibujo = 0;
        this.yInicioZonaDibujo = 0;
        this.xFinalZonaDibujo = super.getWidth();
        this.yFinalZonaDibujo = super.getHeight();
        this.figuraSeleccionada = null;
        super.setBorder(new BevelBorder(BevelBorder.RAISED));
        super.addMouseListener(this);
        super.addMouseMotionListener(this);
    }  
    
    public void configurarZonaDibujo()
    {
        int anchoFijoPanel = 1032;
        int altoFijoPanel = 472;
        
        if(this.getPreferredSize().getWidth() <= anchoFijoPanel && this.getPreferredSize().getHeight() <= altoFijoPanel)
        {
            this.xInicioZonaDibujo = (int) Math.round((double) (anchoFijoPanel - (this.getPreferredSize().getWidth()))/2);
            this.yInicioZonaDibujo = (int) Math.round((double) (altoFijoPanel - (this.getPreferredSize().getHeight()))/2);
        }
        else if(this.getPreferredSize().getWidth() <= anchoFijoPanel && this.getPreferredSize().getHeight() > altoFijoPanel)
        {
            this.xInicioZonaDibujo = (int) Math.round((double)(anchoFijoPanel - (this.getPreferredSize().getWidth()))/2);
            this.yInicioZonaDibujo = 0;           
        }
        else if(this.getPreferredSize().getWidth() > anchoFijoPanel && this.getPreferredSize().getHeight() <= altoFijoPanel)
        {
            this.xInicioZonaDibujo = 0;
            this.yInicioZonaDibujo = (int) Math.round((double)(altoFijoPanel - (this.getPreferredSize().getHeight()))/2);    
        }
        else if(this.getPreferredSize().getWidth() > anchoFijoPanel && this.getPreferredSize().getHeight() > altoFijoPanel)
        {
            this.xInicioZonaDibujo = 0;
            this.yInicioZonaDibujo = 0;           
        }
        this.xFinalZonaDibujo = (int) (xInicioZonaDibujo + this.getPreferredSize().getWidth())-1;
        this.yFinalZonaDibujo = (int) (yInicioZonaDibujo + this.getPreferredSize().getHeight()-3);    

        
    }
    
    
    @Override
    public void mouseWheelMoved(MouseWheelEvent e)
    {
        getPanelEdicion().getPanelZoom().setInit(getPanelEdicion().getPanelZoom().getInit()-(e.getWheelRotation()));
    }
    
    @Override
    public void mouseDragged(MouseEvent e)
    {
        if( this.modoDibujo == SELECCIONAR && this.figuraSeleccionada != null )
        {
            if(this.figuraSeleccionada instanceof Linea)
            {
                Linea r = (Linea)this.figuraSeleccionada;
                r.setPosicion((int)r.getX()
                        + (e.getX() - movX), (int)r.getY()
                        + (e.getY() - movY));
                r.setXFin( (int)r.getXFin()
                        + (e.getX() - movX) );
                r.setYFin((int)r.getYFin()
                        + (e.getY() - movY));
                int xRelativo = r.getX() - this.xInicioZonaDibujo;
                int yRelativo = r.getY() - this.yInicioZonaDibujo;
                int xRelativoFinal = r.getXFin() - this.xInicioZonaDibujo;
                int yRelativoFinal = r.getYFin() - this.yInicioZonaDibujo;
                
                r.setxProporcionInicial(((int) Math.round( ( ((double)100*(double)xRelativo)/(double) this.panelEdicion.getPanelZoom().getInit()))));
                r.setyProporcionInicial(((int) Math.round( ( ((double)100*(double)yRelativo)/(double) this.panelEdicion.getPanelZoom().getInit()))));
                r.setxProporcionFinal(((int) Math.round( ( ((double)100*(double)xRelativoFinal)/(double) this.panelEdicion.getPanelZoom().getInit()))));                
                r.setyProporcionFinal(((int) Math.round( ( ((double)100*(double)yRelativoFinal)/(double) this.panelEdicion.getPanelZoom().getInit()))));                
                
                this.movX = e.getX();
                this.movY = e.getY();
            }
            else if (this.figuraSeleccionada instanceof Rectangulo ){
                this.figuraSeleccionada.setPosicion((int)this.figuraSeleccionada.getX()
                        + (e.getX() - movX), (int)this.figuraSeleccionada.getY()
                        + (e.getY() - movY));
                Rectangulo r = (Rectangulo)this.figuraSeleccionada;
                r.setAnchoProporcion((int) Math.round( ( ((double)100*(double)r.getAncho())/(double) this.panelEdicion.getPanelZoom().getInit() ) ));
                r.setAltoProporcion((int) Math.round( ( ((double)100*(double)r.getAlto())/(double) this.panelEdicion.getPanelZoom().getInit() ) ));
                int xRelativo = r.getX() - this.xInicioZonaDibujo;
                int yRelativo = r.getY() - this.yInicioZonaDibujo;
                
                r.setxProporcion(((int) Math.round( ( ((double)100*(double)xRelativo)/(double) this.panelEdicion.getPanelZoom().getInit()))));
                r.setyProporcion(((int) Math.round( ( ((double)100*(double)yRelativo)/(double) this.panelEdicion.getPanelZoom().getInit()))));

                this.movX = e.getX();
                this.movY = e.getY();
            }
            else if( this.figuraSeleccionada instanceof Circulo  )
            {
                 Circulo c = (Circulo)this.figuraSeleccionada;
                 this.figuraSeleccionada.setInicio((int)this.figuraSeleccionada.getX() - (int)(c.getRadio())
                        + (e.getX() - movX), (int)this.figuraSeleccionada.getY()-(int)(c.getRadio())
                        + (e.getY() - movY));
                c.setRadioProporcion((int) Math.round( ( ((double)100*(double)c.getRadio())/(double) this.panelEdicion.getPanelZoom().getInit() ) ));
                int xRelativo = c.getX() - this.xInicioZonaDibujo;
                int yRelativo = c.getY() - this.yInicioZonaDibujo;
                c.setxProporcion(((int) Math.round( ( ((double)100*(double)xRelativo)/(double) this.panelEdicion.getPanelZoom().getInit()))));
                c.setyProporcion(((int) Math.round( ( ((double)100*(double)yRelativo)/(double) this.panelEdicion.getPanelZoom().getInit()))));                

                 
                this.movX = e.getX()-(int)(c.getRadio());
                this.movY = e.getY()-(int)(c.getRadio());   
            }  
            repaint();
            
        }
        
        else if(estaDibujando)
        {
            setFinX(e.getX());
            setFinY(e.getY());
            if(this.modoDibujo == LAPIZ )
            {
                Linea linea = new Linea( getInicioX(), getInicioY(),
                        getFinX(), getFinY(), getColorBorde(), null,
                        (int)getTamanioBorde());
                                
                linea.dibujar(getGraphics());
                this.dibujarLimites(getGraphics());
                int xRelativoInicial = getInicioX() - this.xInicioZonaDibujo;
                int yRelativoInicial = getInicioY() - this.yInicioZonaDibujo;
                int xRelativoFinal = getFinX() - this.xInicioZonaDibujo;
                int yRelativoFinal = getFinY() - this.yInicioZonaDibujo;
                
                linea.setxProporcionInicial(((int) Math.round( ( ((double)100*(double)xRelativoInicial)/(double) this.panelEdicion.getPanelZoom().getInit()))));
                linea.setyProporcionInicial(((int) Math.round( ( ((double)100*(double)yRelativoInicial)/(double) this.panelEdicion.getPanelZoom().getInit()))));                
                linea.setxProporcionFinal(((int) Math.round( ( ((double)100*(double)xRelativoFinal)/(double) this.panelEdicion.getPanelZoom().getInit()))));                
                linea.setyProporcionFinal(((int) Math.round( ( ((double)100*(double)yRelativoFinal)/(double) this.panelEdicion.getPanelZoom().getInit()))));                
                
                setInicioX(getFinX());
                setInicioY(getFinY());
                this.segmento.agregarLinea( linea );
                this.dibujarLimites(getGraphics());
            }
            else if( this.modoDibujo == SPRITE )
            {
                PuntoSprite punto = new PuntoSprite(getInicioX(), getInicioY(),getTamanioBorde(), 28, getColorBorde(), getColorRelleno(), getTamanioBorde());
                punto.setRadioProporcion((int) Math.round( ( ((double)100*(double)punto.getRadio())/(double) this.panelEdicion.getPanelZoom().getInit() ) ));
                int xRelativo = punto.getX() - this.xInicioZonaDibujo;
                int yRelativo = punto.getY() - this.yInicioZonaDibujo;
                punto.setxProporcion(((int) Math.round( ( ((double)100*(double)xRelativo)/(double) this.panelEdicion.getPanelZoom().getInit()))));
                punto.setyProporcion(((int) Math.round( ( ((double)100*(double)yRelativo)/(double) this.panelEdicion.getPanelZoom().getInit()))));                

                setInicioX(getFinX());
                setInicioY(getFinY());
                this.segmentoSprite.agregarPunto(punto);
                this.dibujarLimites(getGraphics());
                punto.dibujar( getGraphics() );
                this.dibujarLimites(getGraphics());
            }
            else
            {
                repaint();
                repaint();
            }
            
        }
        
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
    }

    public void dibujarLimites(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.fillRect( 0, 0, this.getWidth(), this.yInicioZonaDibujo );
        g.fillRect(0, 0,  this.xInicioZonaDibujo, this.getHeight() );
        g.fillRect(this.xInicioZonaDibujo, this.yFinalZonaDibujo,  this.getWidth(), this.getHeight());
        g.fillRect(this.xFinalZonaDibujo, this.yInicioZonaDibujo,  this.getWidth(), this.getHeight() );
    }
    
    @Override
    public void mouseClicked(MouseEvent e)
    {
        if( this.modoDibujo == SELECCIONAR )
        {
            Figura f = devolverFigura(e.getX(), e.getY());
            this.figuraSeleccionada = f;
            if( this.figuraSeleccionada !=null && this.figuraSeleccionada instanceof Linea)
            {
                this.figuraSeleccionada = null;
                //this.panelEdicion.getPanelFiltro().getPanelFormas().getPanelHerramientaLinea().desactivarHerramientaLinea(true);
            }
            else
            {
                this.panelEdicion.getPanelFiltro().getPanelFormas().getPanelHerramientaLinea().desactivarHerramientaLinea(false);
           
            }
            repaint();
        }
        else if( this.modoDibujo == LAPIZ && e.getButton()==MouseEvent.BUTTON1)
        {
                if( e.getX() >= this.xInicioZonaDibujo && e.getX() <= this.xFinalZonaDibujo &&
                        e.getY()>= this.yInicioZonaDibujo && e.getY()<= this.yFinalZonaDibujo)
                {
                    Linea linea = new Linea( e.getX(), e.getY(),
                    e.getX(), e.getY(), getColorBorde(), null,
                    (int)getTamanioBorde());
                    this.figuras.add( linea );
                    this.desHacer.push(linea);
                    linea.dibujar(getGraphics());
                }
            
        }
        else if( this.modoDibujo == SPRITE && e.getButton()==MouseEvent.BUTTON1 )
        {
                PuntoSprite punto = new PuntoSprite(getInicioX(), getInicioY(),getTamanioBorde(), 28, getColorBorde(), getColorRelleno(), getTamanioBorde());
                punto.setRadioProporcion((int) Math.round( ( ((double)100*(double)punto.getRadio())/(double) this.panelEdicion.getPanelZoom().getInit() ) ));
                int xRelativo = punto.getX() - this.xInicioZonaDibujo;
                int yRelativo = punto.getY() - this.yInicioZonaDibujo;
                punto.setxProporcion(((int) Math.round( ( ((double)100*(double)xRelativo)/(double) this.panelEdicion.getPanelZoom().getInit()))));
                punto.setyProporcion(((int) Math.round( ( ((double)100*(double)yRelativo)/(double) this.panelEdicion.getPanelZoom().getInit()))));                
                
                
                this.figuras.add(punto);
                this.desHacer.push(punto);
                punto.dibujar( getGraphics() ); 
        }
        else if( this.modoDibujo == PUNTOCIRCULO && e.getButton()==MouseEvent.BUTTON1 )
        {
            Circulo circulo =  new Circulo(e.getX(), 
                        e.getY(), (int)(this.tamanioPunto/2), getColorBorde(),
                        getColorBorde(), (int)getTamanioBorde());
            circulo.setRadioProporcion((int) Math.round( ( ((double)100*(double)circulo.getRadio())/(double) this.panelEdicion.getPanelZoom().getInit() ) ));
            int xRelativo = circulo.getX() - this.xInicioZonaDibujo;
            int yRelativo = circulo.getY() - this.yInicioZonaDibujo;
            circulo.setxProporcion(((int) Math.round( ( ((double)100*(double)xRelativo)/(double) this.panelEdicion.getPanelZoom().getInit()))));
            circulo.setyProporcion(((int) Math.round( ( ((double)100*(double)yRelativo)/(double) this.panelEdicion.getPanelZoom().getInit()))));                

            this.figuras.add(circulo);
            this.desHacer.push(circulo);
            circulo.dibujar( getGraphics() );
            
        }
        else if( this.modoDibujo == PUNTOCUADRADO && e.getButton()==MouseEvent.BUTTON1 )
        {
            Rectangulo rectangulo = new Rectangulo(e.getX()-this.tamanioPunto/2,e.getY()-this.tamanioPunto/2,
                    tamanioPunto,tamanioPunto,getColorBorde(), getColorBorde(), (int)getTamanioBorde());
            rectangulo.setAnchoProporcion((int) Math.round( ( ((double)100*(double)rectangulo.getAncho())/(double) this.panelEdicion.getPanelZoom().getInit() ) ));
            rectangulo.setAltoProporcion((int) Math.round( ( ((double)100*(double)rectangulo.getAlto())/(double) this.panelEdicion.getPanelZoom().getInit() ) ));
            int xRelativo = rectangulo.getX() - this.xInicioZonaDibujo;
            int yRelativo = rectangulo.getY() - this.yInicioZonaDibujo;
            rectangulo.setxProporcion(((int) Math.round( ( ((double)100*(double)xRelativo)/(double) this.panelEdicion.getPanelZoom().getInit()))));
            rectangulo.setyProporcion(((int) Math.round( ( ((double)100*(double)yRelativo)/(double) this.panelEdicion.getPanelZoom().getInit()))));
                    
            this.figuras.add(rectangulo);
            this.desHacer.push(rectangulo);
            rectangulo.dibujar( getGraphics() );
            
        }
        else if( this.modoDibujo == PUNTOROMBO && e.getButton()==MouseEvent.BUTTON1 )
        {
            Rombo rombo = new Rombo( (int) Math.round((double)e.getX() - ((double)this.tamanioPunto/(double)2)),(int) Math.round((double)e.getY() - ((double)this.tamanioPunto/(double)2)),
                    tamanioPunto,tamanioPunto,getColorBorde(), getColorBorde(), (int)getTamanioBorde());
            rombo.setAnchoProporcion((int) Math.round( ( ((double)100*(double)rombo.getAncho())/(double) this.panelEdicion.getPanelZoom().getInit() ) ));
            rombo.setAltoProporcion((int) Math.round( ( ((double)100*(double)rombo.getAlto())/(double) this.panelEdicion.getPanelZoom().getInit() ) ));
                
            int xRelativo = rombo.getX() - this.xInicioZonaDibujo;
            int yRelativo = rombo.getY() - this.yInicioZonaDibujo;
            rombo.setxProporcion(((int) Math.round( ( ((double)100*(double)xRelativo)/(double) this.panelEdicion.getPanelZoom().getInit()))));
            rombo.setyProporcion(((int) Math.round( ( ((double)100*(double)yRelativo)/(double) this.panelEdicion.getPanelZoom().getInit()))));
                
            
            this.figuras.add(rombo);
            this.desHacer.push(rombo);
            rombo.dibujar( getGraphics() );
        }
        else if( this.modoDibujo == PUNTOESTRELLA && e.getButton()==MouseEvent.BUTTON1 )
        {
            Estrella2 estrella = new Estrella2(e.getX(), 
                        e.getY(), (int)(this.tamanioPunto/2), getColorBorde(),
                        getColorBorde(), (int)getTamanioBorde());
            estrella.setRadioProporcion((int) Math.round( ( ((double)100*(double)estrella.getRadio())/(double) this.panelEdicion.getPanelZoom().getInit() ) ));
            int xRelativo = estrella.getX() - this.xInicioZonaDibujo;
            int yRelativo = estrella.getY() - this.yInicioZonaDibujo;
            estrella.setxProporcion(((int) Math.round( ( ((double)100*(double)xRelativo)/(double) this.panelEdicion.getPanelZoom().getInit()))));
            estrella.setyProporcion(((int) Math.round( ( ((double)100*(double)yRelativo)/(double) this.panelEdicion.getPanelZoom().getInit()))));                

            this.figuras.add(estrella);
            this.desHacer.push(estrella);
            estrella.dibujar( getGraphics() );
        }
        else if(e.getButton()==MouseEvent.BUTTON3 )
        {
            this.figuraSeleccionada = null;
            Figura f = devolverFigura(e.getX(), e.getY());
            if(f!=null)
            {
                this.reHacer.push(f);
                this.figuras.remove(f);
            }
            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        if( this.modoDibujo == SELECCIONAR  )
        {
            this.figuraSeleccionada = devolverFigura(e.getX(), e.getY());
            Figura f = devolverFigura(e.getX(), e.getY());
            figuraSeleccionada = f;
            if(f != null)
            {
                if(  this.figuraSeleccionada instanceof Rectangulo || this.figuraSeleccionada instanceof Linea)
                {
                    this.movX = e.getX();
                    this.movY = e.getY();        
                }
                else
                {
                    Circulo c = (Circulo)this.figuraSeleccionada;
                    this.movX = (int)(e.getX()-c.getRadio());
                    this.movY =(int)( e.getY()-c.getRadio()); 
                }
                this.panelEdicion.getPanelZoom().activarRotacion(true);
                this.panelEdicion.getPanelZoom().setInitRotacion((int) this.figuraSeleccionada.getRotacion());
            }
            else
            {
                this.panelEdicion.getPanelZoom().activarRotacion(false);
            }
             
            repaint();
        }
        else if(e.getButton()==MouseEvent.BUTTON1 && e.getX()>=this.xInicioZonaDibujo && e.getX()<=this.xFinalZonaDibujo
                && e.getY()>=this.yInicioZonaDibujo && e.getY()<=this.yFinalZonaDibujo )
        {
            if( this.modoDibujo == LAPIZ )
            {
                this.segmento = new Segmento(inicioX, inicioY, finX, finY, new Point2D.Double(getInicioX(), getInicioY()) , null, colorBorde, colorRelleno, tamanioBorde);
            }
            if( this.modoDibujo == SPRITE )
            {
                this.segmentoSprite = new SegmentoSprite(e.getX(), e.getY(), e.getX(), e.getY(), new Point2D.Double(getInicioX(), getInicioY()) , null, colorBorde, colorRelleno, tamanioBorde);
            }
            this.estaDibujando = true;
             
            this.inicioX = e.getX();
            this.inicioY = e.getY();
            
        }
        
    }

    public boolean estaDentro(int x, int y)
    {
        if(x >= this.xInicioZonaDibujo && x<=this.xFinalZonaDibujo && y>=this.yInicioZonaDibujo && y<=this.yFinalZonaDibujo)
        {
            return true;
        }
        return false;
    }
    
    @Override
    public void mouseReleased(MouseEvent e)
    {
        if(estaDibujando)
        {
            if(this.modoDibujo == NULO )
            {
                repaint();
                this.inicioX = e.getX();
                this.finX = e.getX();
                this.inicioY = e.getY();
                this.finY = e.getY();
                return;
            }
            else if(this.modoDibujo == CIRCULO )
            {
                Circulo circulo =  this.crearCirculo();
                figuras.add(circulo);
                desHacer.push(circulo);
            }
            else if(this.modoDibujo == OVALO )
            {
                Ovalo ovalo = this.crearOvalo();
                figuras.add(ovalo);
                desHacer.push(ovalo);
            }
            else if(this.modoDibujo == RECTANGULO )
            {
                Rectangulo rectangulo = this.crearRectangulo();
                figuras.add(rectangulo);
                desHacer.push(rectangulo);
            }
            else if(this.modoDibujo == ROMBO )
            {
                Rombo rombo = this.crearRombo();
                figuras.add(rombo);
                desHacer.push(rombo);
                repaint();
            }
            else if(this.modoDibujo == ESTRELLA )
            {
                Estrella2 estrella = this.crearEstrella();
                figuras.add(estrella);
                desHacer.push(estrella);
            }
            else if(this.modoDibujo == PENTAGONO )
            {
                Pentagono pentagono = this.crearPentagono();
                figuras.add(pentagono);
                desHacer.push(pentagono);
            }
            else if(this.modoDibujo == LINEA )
            {
                Linea linea = this.crearLinea();
                this.figuras.add(linea);
                this.desHacer.push(linea);
            }
            else if(this.modoDibujo == LAPIZ )
            {
                this.segmento.setxFin( getFinX() );
                this.segmento.setyFin( getFinX() );
                this.figuras.add(this.segmento);
                this.desHacer.push(this.segmento);
                this.segmento = null;

            }
            else if(this.modoDibujo == GOMA )
            {
                Figura f = devolverFigura(e.getX(), e.getY());
                if(f!=null)
                {
                    if(f.equals(figuraSeleccionada))
                    {
                        figuraSeleccionada = null;
                    }
                    this.figuras.remove(f);
                }
                
            }
            else if(this.modoDibujo == SPRITE )
            {
                this.figuras.add(this.segmentoSprite);
                this.desHacer.push(this.segmentoSprite);
                this.segmentoSprite = null;
            }
            repaint();
            
            this.estaDibujando = false;
        }
        repaint();
    }
    
    @Override
    public void mouseEntered(MouseEvent e)
    {
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        
    }
    
    public Figura devolverFigura(int x, int y)
    {
        for(int i=this.figuras.size()-1;i>=0;i-- )
        {
            if( this.figuras.get(i).estaDentro(x, y))
            {
                return this.figuras.get(i);
            }
        }
        return null;
    }
    
    @Override
    public void paint(Graphics g)
    {
        super.paintComponent(g);
        for (Figura figura : this.figuras){
            figura.dibujar(g);
        }
        g.setColor(getColorBorde());
        
        if(estaDibujando)
        {
            if (this.modoDibujo == OVALO ){
                Ovalo ovalo = this.crearOvalo();
                ovalo.dibujar(g);
            }

            else if (this.modoDibujo == CIRCULO ){
                
                Circulo circulo =  this.crearCirculo();
                circulo.dibujar(g);
            }
            else if (this.modoDibujo == RECTANGULO){
                Rectangulo rectangulo = this.crearRectangulo();
                rectangulo.dibujar(g);
            }
            else if (this.modoDibujo == ROMBO){

                Rombo rombo = this.crearRombo();
                rombo.dibujar(g);
            }
            else if (this.modoDibujo == PENTAGONO){

                Pentagono pentagono = this.crearPentagono();
                pentagono.dibujar(g);
            }
            else if( this.modoDibujo == ESTRELLA )
            {
                Estrella2 estrella = this.crearEstrella();
                estrella.dibujar(g);
            }
            else if( this.modoDibujo == LINEA )
            {
                Linea linea = this.crearLinea();
                linea.dibujar(g);
            }
            
            
        }
        //SE VUELVE A DIBUJAR LA FIGURA SELECCIONADA Y SU CONTORNO DE SELECCION 
        if(figuraSeleccionada!=null)
        {
            
            float dash1[] = {5.0f};
            Stroke bordeFigura = new BasicStroke(1.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER, 1.0f,dash1,2.0f);
            Graphics2D  g2 = (Graphics2D)g;  
            
            if(this.figuraSeleccionada.getRotacion()==0)
            {
                if( this.figuraSeleccionada instanceof Estrella2 )
                {
                    Estrella2 e = (Estrella2)figuraSeleccionada;
                    e.dibujar(g);
                    e.dibujar(g);
                    g2.setColor(new Color(255-e.getColorBorde().getRed(), 255-e.getColorBorde().getGreen(), 255-e.getColorBorde().getBlue() ));
                    g2.setStroke(bordeFigura);
                    g2.draw(e.getEstrella());

                }
                else if( this.figuraSeleccionada instanceof Circulo)
                {
                    Circulo c = (Circulo)this.figuraSeleccionada;
                    c.dibujar(g);
                    g2.setColor(new Color(255-c.getColorBorde().getRed(), 255-c.getColorBorde().getGreen(), 255-c.getColorBorde().getBlue() ));
                    g2.setStroke(bordeFigura);
                    g2.draw(c.getElipse());
                }
                else if( this.figuraSeleccionada instanceof Rombo )
                {
                    Rombo r = (Rombo)this.figuraSeleccionada;
                    r.dibujar(g);
                    g2.setColor(new Color(255-r.getColorBorde().getRed(), 255-r.getColorBorde().getGreen(), 255-r.getColorBorde().getBlue() ));
                    g2.setStroke(bordeFigura);
                    g2.drawPolygon(r.getPoligono());
                }

                else if( this.figuraSeleccionada instanceof Pentagono )
                {
                    Pentagono r = (Pentagono)figuraSeleccionada;
                    r.dibujar(g);
                    g2.setColor(new Color(255-r.getColorBorde().getRed(), 255-r.getColorBorde().getGreen(), 255-r.getColorBorde().getBlue() ));
                    g2.setStroke(bordeFigura);
                    g2.drawPolygon(r.getPoligono());

                }
                else if( this.figuraSeleccionada instanceof Ovalo )
                {
                    Ovalo r2 = (Ovalo)this.figuraSeleccionada;
                    r2.dibujar(g);
                    g2.setColor(new Color(255-r2.getColorBorde().getRed(), 255-r2.getColorBorde().getGreen(), 255-r2.getColorBorde().getBlue() ));
                    g2.setStroke(bordeFigura);
                    g2.draw(r2.getElipse());
                }
                else if( this.figuraSeleccionada instanceof Rectangulo )
                {
                    Rectangulo r = (Rectangulo)this.figuraSeleccionada;
                    g2.setColor(new Color(255-r.getColorBorde().getRed(), 255-r.getColorBorde().getGreen(), 255-r.getColorBorde().getBlue() ));
                    g2.setStroke(bordeFigura);
                    g2.draw(r.getRectangulo());    
                }
                else if( this.figuraSeleccionada instanceof Linea )
                {
                    Linea r = (Linea)this.figuraSeleccionada;
                    g2.setColor(new Color(255-r.getColorBorde().getRed(), 255-r.getColorBorde().getGreen(), 255-r.getColorBorde().getBlue() ));
                    g2.setStroke(bordeFigura);
                    g2.draw(r.getPoligono());    
                }
            }
            if(this.figuraSeleccionada.getRotacion()>0)
            {
                AffineTransform af = new AffineTransform();
                double radians = Math.toRadians(this.figuraSeleccionada.getRotacion());
                if( this.figuraSeleccionada instanceof Estrella2 )
                {
                    Estrella2 e = (Estrella2)figuraSeleccionada;
                    e.dibujar(g);
                    af.rotate(radians, e.getX() , e.getY());
                    ((Graphics2D)g).setTransform(af);
                    g2.setColor(new Color(255-e.getColorBorde().getRed(), 255-e.getColorBorde().getGreen(), 255-e.getColorBorde().getBlue() ));
                    g2.setStroke(bordeFigura);
                    g2.draw(e.getEstrella());
                    af.rotate(-radians, e.getX() , e.getY());

                }
                else if( this.figuraSeleccionada instanceof Circulo)
                {
                    Circulo c = (Circulo)this.figuraSeleccionada;
                    c.dibujar(g);
                    af.rotate(radians, c.getX() , c.getY());
                    ((Graphics2D)g).setTransform(af);
                    g2.setColor(new Color(255-c.getColorBorde().getRed(), 255-c.getColorBorde().getGreen(), 255-c.getColorBorde().getBlue() ));
                    g2.setStroke(bordeFigura);
                    g2.draw(c.getElipse());
                    af.rotate(-radians, c.getX() , c.getY());
                }
                else if( this.figuraSeleccionada instanceof Rombo )
                {
                    Rombo r = (Rombo)this.figuraSeleccionada;
                    r.dibujar(g);
                    af.rotate(radians, r.getX()+r.getAncho()/2 , r.getY()+r.getAlto()/2);
                    ((Graphics2D)g).setTransform(af);
                    g2.setColor(new Color(255-r.getColorBorde().getRed(), 255-r.getColorBorde().getGreen(), 255-r.getColorBorde().getBlue() ));
                    g2.setStroke(bordeFigura);
                    g2.drawPolygon(r.getPoligono());
                    af.rotate(-radians, r.getX()+r.getAncho()/2 , r.getY()+r.getAlto()/2);
                }

                else if( this.figuraSeleccionada instanceof Pentagono )
                {
                    Pentagono r = (Pentagono)figuraSeleccionada;
                    r.dibujar(g);
                    af.rotate(radians, r.getX()+r.getAncho()/2 , r.getY()+r.getAlto()/2);
                    ((Graphics2D)g).setTransform(af);
                    g2.setColor(new Color(255-r.getColorBorde().getRed(), 255-r.getColorBorde().getGreen(), 255-r.getColorBorde().getBlue() ));
                    g2.setStroke(bordeFigura);
                    g2.drawPolygon(r.getPoligono());
                    af.rotate(-radians, r.getX()+r.getAncho()/2 , r.getY()+r.getAlto()/2);

                }
                else if( this.figuraSeleccionada instanceof Ovalo )
                {
                    Ovalo r2 = (Ovalo)this.figuraSeleccionada;
                    r2.dibujar(g);
                    af.rotate(radians, r2.getX()+r2.getAncho()/2 , r2.getY()+r2.getAlto()/2);
                    ((Graphics2D)g).setTransform(af);
                    g2.setColor(new Color(255-r2.getColorBorde().getRed(), 255-r2.getColorBorde().getGreen(), 255-r2.getColorBorde().getBlue() ));
                    g2.setStroke(bordeFigura);
                    g2.draw(r2.getElipse());
                    af.rotate(-radians, r2.getX()+r2.getAncho()/2 , r2.getY()+r2.getAlto()/2);
                }
                else if( this.figuraSeleccionada instanceof Rectangulo )
                {
                    Rectangulo r = (Rectangulo)this.figuraSeleccionada;
                    af.rotate(radians, r.getX()+r.getAncho()/2 , r.getY()+r.getAlto()/2);
                    ((Graphics2D)g).setTransform(af);
                    g2.setColor(new Color(255-r.getColorBorde().getRed(), 255-r.getColorBorde().getGreen(), 255-r.getColorBorde().getBlue() ));
                    g2.setStroke(bordeFigura);
                    g2.draw(r.getRectangulo());    
                    af.rotate(-radians, r.getX()+r.getAncho()/2 , r.getY()+r.getAlto()/2);
                }
                else if( this.figuraSeleccionada instanceof Linea )
                {
                    Linea r = (Linea)this.figuraSeleccionada;
                    af.rotate(radians, r.getX()+((double)(r.getXFin()-r.getX()))/2 , r.getY()+((double)r.getYFin()-r.getY())/2);
                    ((Graphics2D)g).setTransform(af);
                    g2.setColor(new Color(255-r.getColorBorde().getRed(), 255-r.getColorBorde().getGreen(), 255-r.getColorBorde().getBlue() ));
                    g2.setStroke(bordeFigura);
                    g2.draw(r.getPoligono());    
                    af.rotate(-radians, r.getX()+((double)(r.getXFin()-r.getX()))/2 , r.getY()+((double)r.getYFin()-r.getY())/2);

                }
                ((Graphics2D)g).setTransform(af);
            }
            
            
        }
        this.dibujarLimites(g);
    }
    
    public void configurarLinea( int tipoLinea, int tipoGuionLinea, int tipoConectorInicio, int tipoConectorFinal )
    {
        this.tipoLinea = tipoLinea;
        this.tipoGuionLinea = tipoGuionLinea;
        this.tipoConectorInicio = tipoConectorInicio;
        this.tipoConectorFinal = tipoConectorFinal;
    }
    
    
    /*************************************************************
     *************************************************************
     ******METODO ENCARGADO DE CONFIGURAR LAS FIGURAS************* 
     ************CUANDO SE HACE ZOOM EN LA IMAGEN*****************
     *************************************************************
     *************************************************************
     *************************************************************/
    public void configurarfiguras()
    {
        
        for( Figura f :this.figuras )
        {
            if( f instanceof PuntoSprite)
            {
                PuntoSprite r = (PuntoSprite)f;
                double porcentaje = (double)((double)this.panelEdicion.getPanelZoom().getInit()/(double)100);
                
                double nuevoX = (double) ((double)r.getxProporcion()*(double)porcentaje);
                double nuevoY = (double) ((double)r.getyProporcion()*(double)porcentaje);
                
                r.setX(this.xInicioZonaDibujo + (int)Math.round(nuevoX));
                r.setY(this.yInicioZonaDibujo + (int)Math.round(nuevoY));  
                r.configurarPuntos( );
                repaint();
            }
            else if( f instanceof SegmentoSprite )
            {
                SegmentoSprite segmento = (SegmentoSprite)f;
                
                ArrayList<PuntoSprite> puntos = segmento.getPuntos();
                
                for(PuntoSprite p : puntos)
                {
                    double porcentaje = (double)((double)this.panelEdicion.getPanelZoom().getInit()/(double)100);

                    double nuevoX = (double) ((double)p.getxProporcion()*(double)porcentaje);
                    double nuevoY = (double) ((double)p.getyProporcion()*(double)porcentaje);

                    p.setX(this.xInicioZonaDibujo + (int)Math.round(nuevoX));
                    p.setY(this.yInicioZonaDibujo + (int)Math.round(nuevoY));  
                    p.configurarPuntos( );
                    repaint();
                }
            }
            else if( f instanceof Circulo)
            {
                double porcentaje = (double)((double)this.panelEdicion.getPanelZoom().getInit()/(double)100);
                
                Circulo r = (Circulo) f;
                
                r.setRadio((int) Math.round((double)r.getRadioProporcion()*(double)porcentaje));
                
                double nuevoX = (double) ((double)r.getxProporcion()*(double)porcentaje);
                double nuevoY = (double) ((double)r.getyProporcion()*(double)porcentaje);
                
                r.setX(this.xInicioZonaDibujo + (int)Math.round(nuevoX));
                r.setY(this.yInicioZonaDibujo + (int)Math.round(nuevoY));                
            }
            else if( f instanceof Rectangulo)
            {
                double porcentaje = (double)((double)this.panelEdicion.getPanelZoom().getInit()/(double)100);
                
                Rectangulo r = (Rectangulo) f;
                r.setAncho( (int) Math.round((double)r.getAnchoProporcion()*(double)porcentaje));
                r.setAlto( (int) Math.round((double)r.getAltoProporcion()*(double)porcentaje));               
                
                double nuevoX = (double) ((double)r.getxProporcion()*(double)porcentaje);
                double nuevoY = (double) ((double)r.getyProporcion()*(double)porcentaje);
                
                r.setX(this.xInicioZonaDibujo + (int)Math.round(nuevoX));
                r.setY(this.yInicioZonaDibujo + (int)Math.round(nuevoY));
            }
            else if( f instanceof Linea)
            {
                double porcentaje = (double)((double)this.panelEdicion.getPanelZoom().getInit()/(double)100);
                
                Linea r = (Linea) f;
                
                double nuevoXInicial = (double) ((double)r.getxProporcionInicial()*(double)porcentaje);
                double nuevoYInicial = (double) ((double)r.getyProporcionInicial()*(double)porcentaje);
                
                double nuevoXFinal = (double) ((double)r.getxProporcionFinal()*(double)porcentaje);                
                double nuevoYFinal = (double) ((double)r.getyProporcionFinal()*(double)porcentaje);                
                
                r.setX(this.xInicioZonaDibujo + (int) Math.round(nuevoXInicial));
                r.setY(this.yInicioZonaDibujo + (int) Math.round(nuevoYInicial));
                
                r.setXFin(this.xInicioZonaDibujo + (int) Math.round(nuevoXFinal));
                r.setYFin(this.yInicioZonaDibujo + (int) Math.round(nuevoYFinal));
            }
            else if( f instanceof Segmento)
            {
                Segmento segmento = (Segmento)f;
                
                ArrayList<Linea> lineas = segmento.getLineas();
                
                for(Linea linea : lineas)
                {
                    double porcentaje = (double)((double)this.panelEdicion.getPanelZoom().getInit()/(double)100);

                    double nuevoXInicial = (double) ((double)linea.getxProporcionInicial()*(double)porcentaje);
                    double nuevoYInicial = (double) ((double)linea.getyProporcionInicial()*(double)porcentaje);

                    double nuevoXFinal = (double) ((double)linea.getxProporcionFinal()*(double)porcentaje);                
                    double nuevoYFinal = (double) ((double)linea.getyProporcionFinal()*(double)porcentaje);                

                    linea.setX(this.xInicioZonaDibujo + (int) Math.round(nuevoXInicial));
                    linea.setY(this.yInicioZonaDibujo + (int) Math.round(nuevoYInicial));

                    linea.setXFin(this.xInicioZonaDibujo + (int) Math.round(nuevoXFinal));
                    linea.setYFin(this.yInicioZonaDibujo + (int) Math.round(nuevoYFinal));
                }
            }
            
            
        }
        
        
    }

    /*************************************************************
     *************************************************************
     ******METODOS DESHACER Y REHACER*****************************
     *************************************************************
     *************************************************************
     *************************************************************/
    
    public void reHacer()
    {
        if(this.reHacer.isEmpty() )
        {
            JOptionPane.showMessageDialog(null, "No se puede Re-hacer",
                    "Advertencia",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            //Se Saca EL ELEMENTO DE LA PILA DEL REHACER PARA VOLVER A PONERLO EN LAS FIGURAS
            Figura f = (Figura)this.reHacer.pop();
            this.figuras.add(  f );
            this.desHacer.push( f );
        }
        repaint();
        repaint();
    }
    
    public void desHacer()
    {
        if(this.desHacer.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "No se puede deshacer",
                    "Advertencia",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            //Se Saca EL ELEMENTO DE LA PILA DEL DESHACER PARA VOLVER A PONERLO EN LAS FIGURAS
            Figura f = (Figura)this.desHacer.pop();
            if(f == this.figuraSeleccionada)//EN CASO QUE LA IMAGEN QUE ESTA SELECCIONADA HAY QUE SACARLA
            {
               this.figuraSeleccionada = null; 
            }
            this.figuras.remove(f);
            this.reHacer.push(f);
            
        }
        repaint();
        
    }
    
    
    /*************************************************************
     *************************************************************
     ******GUARDAR IMAGEN ACTUAL INCLUYENDO SI TIENE FIGURAS******
     *************************************************************
     *************************************************************
     *************************************************************/
    
    public void guardarImagen(String ruta)
    {       
        File outputFile = new File(ruta);
        //SE CREA EL BUFFERED IMAGEN EN EL QUE SE GUARDARA LA IMAGEN CON LAS FIGURAS        
        BufferedImage imagenAGuardar = this.panelEdicion.getImagen().imageIconToBufferedImage(this.panelEdicion.getImagen().getImagen().getImage());
        Graphics2D g2 = imagenAGuardar.createGraphics();
        ArrayList<Figura> array = (ArrayList<Figura>) this.figuras.clone();
        //SE RECORRE LAS FIGURAS ACTUALES Y SE VA DETERMINANDO DE QUE CLASE SON Y SE CONFIGURAN AL PORCENTAJE ORIGINAL 
        //CON RESPECTO A LA IMAGEN ORIGINAL.
        for(Figura figura: array)
        {
            if(figura instanceof Circulo)
            {
                Circulo r = (Circulo)figura;
                r.setRadio(r.getRadioProporcion());
                r.setX(r.getxProporcion());
                r.setY(r.getyProporcion());
            }
            else if(figura instanceof Rectangulo)
            {
                Rectangulo r = (Rectangulo)figura; 
                r.setX(r.getxProporcion());
                r.setY(r.getyProporcion());
                r.setAncho(r.getAnchoProporcion());
                r.setAlto(r.getAltoProporcion());
            }
            
            else if(figura instanceof Linea)
            {
                Linea r = (Linea)figura;
                r.setX(r.getxProporcionInicial());
                r.setY(r.getyProporcionInicial());
                r.setXFin(r.getxProporcionFinal());
                r.setYFin(r.getyProporcionFinal());
            }
            else if(figura instanceof Segmento)
            {
                Segmento segmento = (Segmento)figura;
                
                ArrayList<Linea> lineas = segmento.getLineas();
                
                for(Linea linea : lineas)
                {
                    linea.setX(linea.getxProporcionInicial());
                    linea.setY(linea.getyProporcionInicial());
                    linea.setXFin(linea.getxProporcionFinal());
                    linea.setYFin(linea.getyProporcionFinal());
                }
            }          
            else if(figura instanceof SegmentoSprite)
            {
                SegmentoSprite segmento = (SegmentoSprite)figura;
                
                ArrayList<PuntoSprite> puntos = segmento.getPuntos();
                
                for(PuntoSprite punto : puntos)
                {
                    punto.setX(punto.getxProporcion());
                    punto.setY(punto.getyProporcion());
                    punto.setRadio(punto.getRadioProporcion());
                    punto.iniciar();
                }
            }
            figura.dibujar(g2);
        }
        g2.dispose();
        //SE REVISA SI LA IMAGEN YA EXISTE Y SI ES QUE SE QUIERE SOBRE ESCRIBIR
        if(outputFile.exists())
        {
            Object[] opciones = {"Aceptar", "Cancelar"};
            
            int opcion = JOptionPane.showOptionDialog(null, "Desea sobreescribir la imagen?", "Guardar como...", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, null);
            
            if(opcion == 0)
            {
                try
                {
                    ImageIO.write(imagenAGuardar,"jpg", outputFile);
                }
                catch (IOException e)
                {
                    JOptionPane.showMessageDialog(null, "Error al guardar la imagen", "Error", JOptionPane.ERROR_MESSAGE);
                }  
            }
        }
        else//EN CASO DE QUE LA IMAGEN NO EXISTA
        {
            try
            {
                ImageIO.write(imagenAGuardar,"jpg", outputFile);
            }
            catch (IOException e)
            {
                JOptionPane.showMessageDialog(null, "Error al guardar la imagen", "Error", JOptionPane.ERROR_MESSAGE);
            }             
        }      
        this.panelEdicion.setImagen(new Imagen(new ImageIcon(imagenAGuardar)), outputFile.getAbsolutePath());
        this.figuras.clear();
        this.desHacer.clear();
        this.reHacer.clear();
        this.panelEdicion.getPanelFiltro().getPanelProcesamiento().getDesHacerImagenes().clear();
        this.panelEdicion.getPanelFiltro().getPanelProcesamiento().getReHacerImagenes().clear();
        this.panelEdicion.getPanelFiltro().getPanelProcesamiento().setTieneFiltro(false);
        
    }

    
    
    /*************************************************************
     *************************************************************
     ******METODOS QUE CREAN TODOS LOS TIPOS DE FIGURAS***********
     *************************************************************
     *************************************************************
     *************************************************************/
    
    
    public Circulo crearCirculo()
    {
        double radio = Math.sqrt(Math.pow(getFinX() - getInicioX(),2) +
                            Math.pow(getFinY() - getInicioY(),2));
        Circulo circulo =  new Circulo(getInicioX(), 
                getInicioY(), (int)radio, getColorBorde(),
                getColorRelleno(), (int)getTamanioBorde());
        circulo.setRadioProporcion((int) Math.round( ( ((double)100*(double)circulo.getRadio())/(double) this.panelEdicion.getPanelZoom().getInit() ) ));
                
        int xRelativo = circulo.getX() - this.xInicioZonaDibujo;
        int yRelativo = circulo.getY() - this.yInicioZonaDibujo;
                
        circulo.setxProporcion(((int) Math.round( ( ((double)100*(double)xRelativo)/(double) this.panelEdicion.getPanelZoom().getInit()))));
        circulo.setyProporcion(((int) Math.round( ( ((double)100*(double)yRelativo)/(double) this.panelEdicion.getPanelZoom().getInit()))));                
        return circulo;
    }
    
    public Rectangulo crearRectangulo()
    {
        Rectangulo rectangulo = new Rectangulo(Math.min(getInicioX(), getFinX()),
                            Math.min(getInicioY(), getFinY()),
                            Math.abs(getInicioX() - getFinX()),
                            Math.abs(getInicioY() - getFinY()),
                            getColorBorde(), getColorRelleno(), (int)getTamanioBorde());
        rectangulo.setAnchoProporcion((int) Math.round( ( ((double)100*(double)rectangulo.getAncho())/(double) this.panelEdicion.getPanelZoom().getInit() ) ));
        rectangulo.setAltoProporcion((int) Math.round( ( ((double)100*(double)rectangulo.getAlto())/(double) this.panelEdicion.getPanelZoom().getInit() ) ));
                
        int xRelativo = rectangulo.getX() - this.xInicioZonaDibujo;
        int yRelativo = rectangulo.getY() - this.yInicioZonaDibujo;
                
        rectangulo.setxProporcion(((int) Math.round( ( ((double)100*(double)xRelativo)/(double) this.panelEdicion.getPanelZoom().getInit()))));
        rectangulo.setyProporcion(((int) Math.round( ( ((double)100*(double)yRelativo)/(double) this.panelEdicion.getPanelZoom().getInit()))));
        return rectangulo;
    }
    public Rombo crearRombo()
    {
        Rombo rombo = new Rombo(Math.min(getInicioX(), getFinX()),
                            Math.min(getInicioY(), getFinY()),
                            Math.abs(getInicioX() - getFinX()),
                            Math.abs(getInicioY() - getFinY()),
                            getColorBorde(), getColorRelleno(), (int)getTamanioBorde());
        rombo.setAnchoProporcion((int) Math.round( ( ((double)100*(double)rombo.getAncho())/(double) this.panelEdicion.getPanelZoom().getInit() ) ));
        rombo.setAltoProporcion((int) Math.round( ( ((double)100*(double)rombo.getAlto())/(double) this.panelEdicion.getPanelZoom().getInit() ) ));
                
        int xRelativo = rombo.getX() - this.xInicioZonaDibujo;
        int yRelativo = rombo.getY() - this.yInicioZonaDibujo;
        rombo.setxProporcion(((int) Math.round( ( ((double)100*(double)xRelativo)/(double) this.panelEdicion.getPanelZoom().getInit()))));
        rombo.setyProporcion(((int) Math.round( ( ((double)100*(double)yRelativo)/(double) this.panelEdicion.getPanelZoom().getInit()))));
        return rombo;
    }
    
    public Estrella2 crearEstrella()
    {
        double radio = Math.sqrt(Math.pow(getFinX() - getInicioX(),2) +
                            Math.pow(getFinY() - getInicioY(),2));
        Estrella2 e =  new Estrella2(getInicioX(), 
                getInicioY(), (int)radio, getColorBorde(),
                getColorRelleno(), (int)getTamanioBorde());
        e.setRadioProporcion((int) Math.round( ( ((double)100*(double)e.getRadio())/(double) this.panelEdicion.getPanelZoom().getInit() ) ));
                
        int xRelativo = e.getX() - this.xInicioZonaDibujo;
        int yRelativo = e.getY() - this.yInicioZonaDibujo;
                
        e.setxProporcion(((int) Math.round( ( ((double)100*(double)xRelativo)/(double) this.panelEdicion.getPanelZoom().getInit()))));
        e.setyProporcion(((int) Math.round( ( ((double)100*(double)yRelativo)/(double) this.panelEdicion.getPanelZoom().getInit()))));                
        return e;
    }
    
    public Ovalo crearOvalo()
    {
        Ovalo ovalo =  new Ovalo(Math.min(getInicioX(), getFinX()),
                            Math.min(getInicioY(), getFinY()),
                            Math.abs(getInicioX() - getFinX()),
                            Math.abs(getInicioY() - getFinY()),
                            getColorBorde(), getColorRelleno(), (int)getTamanioBorde());
        ovalo.setAnchoProporcion((int) Math.round( ( ((double)100*(double)ovalo.getAncho())/(double) this.panelEdicion.getPanelZoom().getInit() ) ));
        ovalo.setAltoProporcion((int) Math.round( ( ((double)100*(double)ovalo.getAlto())/(double) this.panelEdicion.getPanelZoom().getInit() ) ));
                
        int xRelativo = ovalo.getX() - this.xInicioZonaDibujo;
        int yRelativo = ovalo.getY() - this.yInicioZonaDibujo;
                
        ovalo.setxProporcion(((int) Math.round( ( ((double)100*(double)xRelativo)/(double) this.panelEdicion.getPanelZoom().getInit()))));
        ovalo.setyProporcion(((int) Math.round( ( ((double)100*(double)yRelativo)/(double) this.panelEdicion.getPanelZoom().getInit()))));
        return ovalo;
    }
    
    public Pentagono crearPentagono()
    {
        Pentagono pentagono = new Pentagono(Math.min(getInicioX(), getFinX()),
                            Math.min(getInicioY(), getFinY()),
                            Math.abs(getInicioX() - getFinX()),
                            Math.abs(getInicioY() - getFinY()),
                            getColorBorde(), getColorRelleno(), (int)getTamanioBorde());
        pentagono.setAnchoProporcion((int) Math.round( ( ((double)100*(double)pentagono.getAncho())/(double) this.panelEdicion.getPanelZoom().getInit() ) ));
        pentagono.setAltoProporcion((int) Math.round( ( ((double)100*(double)pentagono.getAlto())/(double) this.panelEdicion.getPanelZoom().getInit() ) ));
        int xRelativo = pentagono.getX() - this.xInicioZonaDibujo;
        int yRelativo = pentagono.getY() - this.yInicioZonaDibujo;
        pentagono.setxProporcion(((int) Math.round( ( ((double)100*(double)xRelativo)/(double) this.panelEdicion.getPanelZoom().getInit()))));
        pentagono.setyProporcion(((int) Math.round( ( ((double)100*(double)yRelativo)/(double) this.panelEdicion.getPanelZoom().getInit()))));
        return pentagono;
    }
    
    public Linea crearLinea()
    {
        Linea linea = new Linea( getInicioX(), getInicioY(),
                    getFinX(), getFinY(), getColorBorde(), null,
                    (int)getTamanioBorde()); 
        int xRelativoInicial = getInicioX() - this.xInicioZonaDibujo;
        int yRelativoInicial = getInicioY() - this.yInicioZonaDibujo;
        int xRelativoFinal = getFinX() - this.xInicioZonaDibujo;
        int yRelativoFinal = getFinY() - this.yInicioZonaDibujo;
                
        linea.setxProporcionInicial(((int) Math.round( ( ((double)100*(double)xRelativoInicial)/(double) this.panelEdicion.getPanelZoom().getInit()))));
        linea.setyProporcionInicial(((int) Math.round( ( ((double)100*(double)yRelativoInicial)/(double) this.panelEdicion.getPanelZoom().getInit()))));                
        linea.setxProporcionFinal(((int) Math.round( ( ((double)100*(double)xRelativoFinal)/(double) this.panelEdicion.getPanelZoom().getInit()))));                
        linea.setyProporcionFinal(((int) Math.round( ( ((double)100*(double)yRelativoFinal)/(double) this.panelEdicion.getPanelZoom().getInit()))));                
        linea.reset(tipoLinea, tipoGuionLinea, tipoConectorInicio, tipoConectorFinal);
        return linea;
    }
           
    
    /****************************************************
     ****************************************************
     ************METODOS SETTERS Y GETTERS***************
     ****************************************************
     *****************************************************
     ****************************************************/
    
    public Stack getDesHacer()
    {
        return desHacer;
    }

    public void setDesHacer(Stack desHacer)
    {
        this.desHacer = desHacer;
    }

    public Stack getReHacer()
    {
        return reHacer;
    }

    public void setReHacer(Stack reHacer)
    {
        this.reHacer = reHacer;
    }

    
    public ArrayList<Figura> getFiguras()
    {
        return figuras;
    }

    public void setFiguras(ArrayList<Figura> figuras)
    {
        this.figuras = figuras;
    }

    public int getTipoConectorFinal()
    {
        return tipoConectorFinal;
    }

    public void setTipoConectorFinal(int tipoConectorFinal)
    {
        this.tipoConectorFinal = tipoConectorFinal;
    }

    public int getTipoConectorInicio()
    {
        return tipoConectorInicio;
    }

    public void setTipoConectorInicio(int tipoConectorInicio)
    {
        this.tipoConectorInicio = tipoConectorInicio;
    }

    public int getTipoGuionLinea()
    {
        return tipoGuionLinea;
    }

    public void setTipoGuionLinea(int tipoGuionLinea)
    {
        this.tipoGuionLinea = tipoGuionLinea;
    }

    public int getTipoLinea()
    {
        return tipoLinea;
    }

    public void setTipoLinea(int tipoLinea)
    {
        this.tipoLinea = tipoLinea;
    }
    
    public int getTamanioPunto()
    {
        return tamanioPunto;
    }

    public void setTamanioPunto(int tamanioPunto)
    {
        this.tamanioPunto = 15 + 2*tamanioPunto;
        revalidate();
    }
    
    public Figura getFiguraSeleccionada()
    {
        return figuraSeleccionada;
    }

    public void setFiguraSeleccionada(Figura figuraSeleccionada)
    {
        this.figuraSeleccionada = figuraSeleccionada;
        repaint();
    }
    
    public void setModoDibujo(int modoDibujo)
    {
        this.modoDibujo = modoDibujo;
    }
    
    
    public int getFinX()
    {
        return finX;
    }

    public void setFinX(int finX)
    {
        this.finX = finX;
    }

    public int getFinY()
    {
        return finY;
    }

    public void setFinY(int finY)
    {
        this.finY = finY;
    }

    public int getInicioX()
    {
        return inicioX;
    }

    public void setInicioX(int inicioX)
    {
        this.inicioX = inicioX;
    }

    public int getInicioY()
    {
        return inicioY;
    }

    public void setInicioY(int inicioY)
    {
        this.inicioY = inicioY;
    }

    public Color getColorBorde()
    {
        return colorBorde;
    }

    public void setColorBorde(Color colorBorde)
    {
        this.colorBorde = colorBorde;
    }

    public int getTamanioBorde()
    {
        return tamanioBorde;
    }

    public void setTamanioBorde(int tamanioBorde)
    {
        this.tamanioBorde = tamanioBorde;
        repaint();
    }

    public Color getColorRelleno()
    {
        return colorRelleno;
    }

    public void setColorRelleno(Color colorRelleno)
    {
        this.colorRelleno = colorRelleno;
    }  
    
    public PanelEdicion getPanelEdicion()
    {
        return this.panelEdicion;
    }
    
    public void setImagen(Imagen imagen)
    {
        this.removeAll();
        this.revalidate();
        this.setIcon(imagen.getImagen());  
        this.setHorizontalAlignment(CENTER);
        this.imagen = imagen;
        this.setPreferredSize(new Dimension(imagen.getImagen().getIconWidth(), imagen.getImagen().getIconHeight()));
        this.configurarZonaDibujo();
        this.configurarfiguras();
    }    
    
    public Imagen getImagen()
    {
        return imagen;
    }    
    
}
