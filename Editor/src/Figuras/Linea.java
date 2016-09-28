/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Figuras;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

/**
 *
 * @author Yerco
 */
public class Linea extends Figura
{
    private Polygon poligono;
    
    private int LINEANORMAL = 0, LINEADOBLE = 1, LINEATRIPLE = 2;
    private int GUIONDESACTIVADO = 0, SOLOGUION = 1, GUIONPUNTO = 2, SOLOPUNTO = 3;
    private int CONECTORDESACTIVADO = 0, CONECTORFLECHARELLENA = 1, CONECTORFLECHAVACIA = 2, CIRCULO = 3, ROMBO = 4, CUADRADO = 5;
    
    
    private int tipoLinea;
    private int tipoGuion;
    private int tipoConectorInicio;
    private int tipoConectorFinal;
    //private int tipo
    private int angulo;
    private int xProporcionInicial, yProporcionInicial, xProporcionFinal, yProporcionFinal ;
    
    
    private int xFin, yFin;
    
       
    public Linea(int inicioX, int inicioY, int finX, int finY, Color colorBorde, Color colorRelleno,  int tamanhio){
        super(inicioX, inicioY, colorBorde, colorRelleno, tamanhio);
        this.xFin = finX;
        this.yFin = finY;
        
        this.tipoLinea = 0;
        this.tipoGuion = 0;
        this.tipoConectorInicio = 0;
        this.tipoConectorFinal = 0;
        this.poligono = new Polygon();
        
        this.xProporcionInicial = 0;
        this.xProporcionFinal = 0;
        this.yProporcionInicial = 0;
        this.yProporcionFinal = 0;
        
    }
    
    public void reset(int tipoLinea, int tipoGuion, int tipoConectorInicio, int tipoConectorFinal)
    {
        this.tipoLinea = tipoLinea;
        this.tipoGuion = tipoGuion;
        this.tipoConectorInicio = tipoConectorInicio;
        this.tipoConectorFinal = tipoConectorFinal;
        this.iniciarPoligono();
        
    }

    public void iniciarPoligono()
    {
        int xI = getX();
        int yI = getY();
        int xF = this.getXFin();
        int yF = this.getYFin();
        Line2D  linea1 = new Line2D.Float( xI, yI,
                xF, yF  );
        int pixeles = (int)Math.round((double)getTamanio()*((double)7/(double)4));
        double L =  Math.sqrt((double)((xI-xF)*(xI-xF)+(yI-yF)*(yI-yF)));
            
        double xIni = xI + pixeles * (yF-yI) / L;
        double xFi =  xF + pixeles * (yF-yI) / L;
        double yIni = yI + pixeles * (xI-xF) / L;
        double yFi =  yF + pixeles * (xI-xF) / L;
            
        Line2D linea2 = new Line2D.Float( (int)xIni, (int)yIni,
                (int)xFi, (int)yFi);
            
        double L2 =  Math.sqrt((double)((xI-xF)*(xI-xF)+(yI-yF)*(yI-yF)));

        xI = (int)(xIni + pixeles * (yF-yI) / L2);
        xF =  (int)(xFi + pixeles * (yF-yI) / L2);
        yI = (int)(yIni + pixeles * (xIni-xFi) / L2);
        yF =  (int)(yFi + pixeles * (xIni-xFi) / L2);
            
        Line2D linea3 = new Line2D.Float( xI, yI,
                xF, yF  );
        
        int puntosX[] = { (int)linea1.getX1(), (int)linea1.getX2(), (int)linea2.getX2(), (int)linea2.getX1() };
        int puntosY[] = { (int)linea1.getY1(), (int)linea1.getY2(),(int)linea2.getY2(), (int)linea1.getY1() };
        this.poligono = new Polygon(puntosX, puntosY, 4);
    }
    
    @Override
    public boolean estaDentro(int x, int y)
    {
        
        int i = (int)Math.round( ((double)(getYFin()-getY())/(double)(getXFin()-getX()))*(x-getX()));
        if(y-getY()==i)
        {
            return true;
        }
        
        int xI = getX();
        int yI = getY();
        int xF = this.getXFin();
        int yF = this.getYFin();        
        int pixeles = 1;
        double L =  Math.sqrt((double)((xI-xF)*(xI-xF)+(yI-yF)*(yI-yF)));

        double xIni = xI - pixeles * (yF-yI) / L;
        double xFi =  xF - pixeles * (yF-yI) / L;
        double yIni = yI - pixeles * (xI-xF) / L;
        double yFi =  yF - pixeles * (xI-xF) / L;

        double j =  (int)Math.round( ((double)(yFi-yIni)/(double)(xFi-xIni))*(x-xIni));
        if(y - yIni == j)
        {
            return true;
        }

        double xIni2 = xIni - pixeles * (yFi-yIni) / L;
        double xFi2 =  xFi - pixeles * (yFi-yIni) / L;
        double yIni2 = yIni - pixeles * (xIni-xFi) / L;
        double yFi2 =  yFi - pixeles * (xIni-xFi) / L;

        double k =  (int)Math.round( ((double)(yFi2-yIni2)/(double)(xFi2-xIni2))*(x-xIni2));
        if(y - yIni2 == k)
        {
            return true;
        }

        double xIni3 = xIni2 - pixeles * (yFi2-yIni2) / L;
        double xFi3 =  xFi2 - pixeles * (yFi2-yIni2) / L;
        double yIni3 = yIni2 - pixeles * (xIni2-xFi2) / L;
        double yFi3 =  yFi2 - pixeles * (xIni2-xFi2) / L;

        double l =  (int)Math.round( ((double)(yFi3-yIni3)/(double)(xFi3-xIni3))*(x-xIni3));
        if(y - yIni3 == l)
        {
            return true;
        }
        return false;
    }

    @Override
    public void setPosicion(int x, int y)
    {
        setX(x);
        setY(y);
        
        
    }

   
    
    @Override
    public void dibujar(Graphics g)
    {
        // Objetos
        this.iniciarPoligono();
        Graphics2D g2 = (Graphics2D)g;
        Line2D line2D;
        Stroke bordeFigura = bordeFigura = new BasicStroke(getTamanio(),  BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
        g2.setColor(getColorBorde());
        g.setColor(getColorBorde());
        double radians = Math.toRadians(getRotacion());
        AffineTransform af = new AffineTransform();
        
        line2D = null;
        
        if(getRotacion() == 0)
        {
            if(this.tipoLinea == LINEANORMAL)
            {
                bordeFigura = configurarGuionLinea();
                g2.setColor(getColorBorde());
                g2.setStroke(bordeFigura);
                line2D = new Line2D.Float( getX(), getY(),
                    this.xFin, this.yFin );
                
                g2.draw(line2D);
            }
            else if(this.tipoLinea == LINEADOBLE )
            {
                bordeFigura = configurarGuionLinea();
                g2.setColor(getColorBorde());
                g2.setStroke(bordeFigura);

                int xI = getX();
                int yI = getY();
                int xF = this.getXFin();
                int yF = this.getYFin();
                line2D = new Line2D.Float( xI, yI,
                    xF, yF  );
               // g2.draw(line2D);

                //int pixeles = (int)Math.round((double)getTamanio()/(double)2);
                int pixeles = (int)Math.round((double)getTamanio()*((double)7/(double)4));
                double L =  Math.sqrt((double)((xI-xF)*(xI-xF)+(yI-yF)*(yI-yF)));

                double xIni = xI + pixeles * (yF-yI) / L;
                double xFi =  xF + pixeles * (yF-yI) / L;
                double yIni = yI + pixeles * (xI-xF) / L;
                double yFi =  yF + pixeles * (xI-xF) / L;

                line2D = new Line2D.Float( (int)xIni, (int)yIni,
                    (int)xFi, (int)yFi);
                g2.draw(line2D);
                xIni = xI - pixeles * (yF-yI) / L;
                xFi =  xF - pixeles * (yF-yI) / L;
                yIni = yI - pixeles * (xI-xF) / L;
                yFi =  yF - pixeles * (xI-xF) / L;

                line2D = new Line2D.Float( (int)xIni, (int)yIni,
                    (int)xFi, (int)yFi);
                
                g2.draw(line2D);

            }
            else if(this.tipoLinea == LINEATRIPLE)
            {
                bordeFigura = configurarGuionLinea();
                g2.setColor(getColorBorde());
                g2.setStroke(bordeFigura);

                int xI = getX();
                int yI = getY();
                int xF = this.getXFin();
                int yF = this.getYFin();
                line2D = new Line2D.Float( xI, yI,
                    xF, yF  );
                g2.draw(line2D);

                int pixeles = (int)Math.round((double)getTamanio()*((double)7/(double)4));
                //int pixeles = 10;
                double L =  Math.sqrt((double)((xI-xF)*(xI-xF)+(yI-yF)*(yI-yF)));

                double xIni = xI - pixeles * (yF-yI) / L;
                double xFi =  xF - pixeles * (yF-yI) / L;
                double yIni = yI - pixeles * (xI-xF) / L;
                double yFi =  yF - pixeles * (xI-xF) / L;

                line2D = new Line2D.Float( (int)xIni, (int)yIni,
                    (int)xFi, (int)yFi);
                g2.draw(line2D);

                double xIni2 = xI + pixeles * (yF-yI) / L;
                double xFi2 =  xF + pixeles * (yF-yI) / L;
                double yIni2 = yI + pixeles * (xI-xF) / L;
                double yFi2 =  yF + pixeles * (xI-xF) / L;

                line2D = new Line2D.Float( (int)xIni2, (int)yIni2,
                    (int)xFi2, (int)yFi2);
                g2.draw(line2D);
                
                
            }
            Polygon p;
            double alfa;
            int x1, y1;
            int k ; 
            int xa;
            int ya;
            int x2;
            int y2;
            int xa2;
            int ya2;
            int xMedio;
            int yMedio;

            
            g2.setStroke(new BasicStroke(getTamanio(),  BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
           
            if(this.tipoConectorInicio==CONECTORFLECHARELLENA)
            {
                //Angulo para formar la punta de flecha en el inicio de la linea
                alfa = Math.atan2(  (-1)*(this.getYFin()-this.getY()),   (-1)*( this.getXFin()-this.getX() ) );
                x1 = this.getX();
                y1 = this.getY();
                k = this.getTamanio()*5;//Controla el tamaño de la punta de Flecha en relacion al grosor de la Linea
                //Lado 1 de la flecha con origen en el punto de termino de la Linea
                xa =(int)(x1- k*Math.cos(alfa+1));//Nuevo Punto X del cual se trazara un extremo de la flecha
                ya =(int)(y1- k*Math.sin(alfa+1));//Nuevo Punto Y ""...

                x2 = xa;
                y2 = ya;
                //lado 2 de la Flecha
                xa2=(int)(x1-k*Math.cos(alfa-1));//Lo mismo que arriba pero corresponden al otro extremo de la punta
                ya2=(int)(y1-k*Math.sin(alfa-1));//

                //Lado 1 de la Flecha invertida
                xa =(int)(x1+ k*Math.cos(alfa+1));//Se invierte el la dirrecion del extremo (punto X)
                ya =(int)(y1+ k*Math.sin(alfa+1));//Se invierte el la dirrecion del extremo (punto Y)

                x2 = xa;
                y2 = ya;
                //Lado 2 de la Flecha invertida
                xa2=(int)(x1+k*Math.cos(alfa-1));//Se invierte el la dirrecion del extremo (punto X)
                ya2=(int)(y1+k*Math.sin(alfa-1));//Se invierte el la dirrecion del extremo (punto Y)
                
                //despues de invertir la punta de flecha se toman las coordenadas para cerrar la base de esta
                //y calcular los el punto medio de esta
                xMedio = (x2+xa2)/2;//punto medio X
                yMedio = (y2+ya2)/2;//punto medio Y
                //Lado 2 flecha
                xa2=(int)(xMedio-k*Math.cos(alfa-1));//se calculan los nuevos puntos paralos extremos de la flecha 
                ya2=(int)(yMedio-k*Math.sin(alfa-1));//con referencia en el punto medio

                //Lado 1 flecha
                xa =(int)(xMedio- k*Math.cos(alfa+1));
                ya =(int)(yMedio- k*Math.sin(alfa+1));

                int punX[] = { xa, xMedio, xa2};
                int punY[] = { ya, yMedio, ya2 };
                
                // se tienen los 3 puntos que forman el triangulo
                p = new Polygon(punX, punY, 3);
                g2.fillPolygon(p);//SE PINTA EL POLIGONO
                g2.drawPolygon(p);
                
                //y asi deja la fecha rellena
            }
            else if(this.tipoConectorInicio==CONECTORFLECHAVACIA)
            {
                //Angulo para formar la punta de flecha en el inicio de la linea
                alfa = Math.atan2((-1)*(this.getYFin()-this.getY()),(-1)*(this.getXFin()-this.getX()));
                x1 = this.getX();
                y1 = this.getY();
                k = this.getTamanio()*5; //Controla el tamaño de la punta de Flecha en relacion al grosor de la Linea
                //Lado 1 de la flecha con origen en el punto de termino de la Linea
                xa =(int)(x1- k*Math.cos(alfa+1));//Nuevo Punto X del cual se trazara un extremo de la flecha
                ya =(int)(y1- k*Math.sin(alfa+1));//Nuevo Punto Y ""...

                x2 = xa;
                y2 = ya;
                //Lado 2 de la Flecha
                xa2=(int)(x1-k*Math.cos(alfa-1));//Lo mismo que arriba pero corresponden al otro extremo de la punta
                ya2=(int)(y1-k*Math.sin(alfa-1));// ""

                //Lado 1 de la Flecha invertida
                xa =(int)(x1+ k*Math.cos(alfa+1));//Se invierte el la dirrecion del extremo (punto X)
                ya =(int)(y1+ k*Math.sin(alfa+1));//Se invierte la direccion del extremo (punto Y)

                x2 = xa;
                y2 = ya;
                //Lado 2 de la flecha Invertida
                xa2=(int)(x1+k*Math.cos(alfa-1));//Se invierte el la dirrecion del extremo (punto X)
                ya2=(int)(y1+k*Math.sin(alfa-1));//Se invierte el la dirrecion del extremo (punto X)

                //despues de invertir la punta de flecha se toman las coordenadas para cerrar la base de esta
                //y calcular los el punto medio de esta
                xMedio = (x2+xa2)/2;//punto medio X
                yMedio = (y2+ya2)/2;//punto medio Y

                //Lado 2 flecha
                xa2=(int)(xMedio-k*Math.cos(alfa-1));//se calculan los nuevos puntos paralos extremos de la flecha 
                ya2=(int)(yMedio-k*Math.sin(alfa-1));//con referencia en el punto medio

                //Lado 1
                xa =(int)(xMedio- k*Math.cos(alfa+1));
                ya =(int)(yMedio- k*Math.sin(alfa+1));

                g2.drawLine(xa2,ya2,xMedio,yMedio); //se dibuja un extremo
                g2.drawLine(xa, ya, xMedio, yMedio);// se dibuja otro extremo
                g2.drawLine(xa,ya,xa2,ya2);// se cierra la base del triangulo
                
                //y asi la linea no pasa por el interior de la flecha
                
               
            }
            else if(this.tipoConectorInicio==CIRCULO)
            {
                Circulo c = new Circulo(getX(), getY(), getTamanio()*2, Color.orange, Color.green, 2);
                g2.fill(c.getElipse());
                g2.draw(c.getElipse());
            }
            else if(this.tipoConectorInicio==ROMBO)
            {
                //utilizo el angulo que se ocupo en la flecha
                alfa = Math.atan2(  (-1)*(this.getYFin()-this.getY()),   (-1)*( this.getXFin()-this.getX() ) );

                x1 = this.getX();
                y1 = this.getY();
                k = this.getTamanio()*5;//Controla el tamaño de los lados del rombo (que se formara con 2 traingulos) en relacion al grosor de la linea
                
                //Lado 1 primer triangulo en sentido inverso como se necesita para el rombo
                xa =(int)(x1+ k*Math.cos(alfa+1));// al igual que el la flecha se calculas los puntos 
                ya =(int)(y1+ k*Math.sin(alfa+1));// para generar un lado
                
                int xCorregido =xa;
                int yCorregido =ya;
                x2 = xa;
                y2 = ya;
                //Lado 2 primer triangulo ens entido inverso
                xa2=(int)(x1+k*Math.cos(alfa-1));//se genera los puntos del otro lado
                ya2=(int)(y1+k*Math.sin(alfa-1));// sentido inverso
                int xPs[] = {xa,x1,xa2};
                int yPs[] = {ya, y1, ya2};
                //se crea el primer triangulo con los 3 puntos
                //los puntos calculados y el punto de incio de la linea
                p = new Polygon( xPs, yPs, 3 );
                
                g2.fillPolygon(p);//se dibuja el triangulo
                
                int xCorregido2 = xa2;
                int yCorregido2 = ya2;

                //se deterimina los puntos medios de la recta q cerraria ese trinagulo
                xMedio = (x2+xa2)/2;
                yMedio = (y2+ya2)/2;
                
                //a partir de ese punto  se calculan otros como para hacer otro trianen el mismo sentido
                xa2=(int)(xMedio+k*Math.cos(alfa-1));
                ya2=(int)(yMedio+k*Math.sin(alfa-1));

                xa =(int)(xMedio+ k*Math.cos(alfa+1));
                ya =(int)(yMedio+ k*Math.sin(alfa+1));

                //pero solo se calcula el punto medio de lo que seria la base del otro traingulo
                xMedio = (xa+xa2)/2;
                yMedio = (ya+ya2)/2;
                
                //a partir de ese punto medio se tranzan las lineas hacia los puntos
                //de termino del primeros lados cerrando el rombo
                int xP[] = {xCorregido,xMedio,xCorregido2};
                int yP[] = {yCorregido, yMedio, yCorregido2};
                p = new Polygon( xP, yP, 3 );
                g2.fillPolygon(p);
            
            }
            else if(this.tipoConectorInicio==CUADRADO)
            {
                //nuevamente se usa el angulo del bendito triangulo(flecha)
                alfa = Math.atan2(this.getYFin() - this.getY(), this.getXFin() - this.getX());
                x1 = this.getX();
                y1 = this.getY();
                k = this.getTamanio()*(int)3.5;//matiene el tamaño del cuadrado en relacion al grosor de linea
                //lado 1 del triangulo con orginen desde el punto final de la linea y se extendiende hacia atras
                xa =(int)(x1- k*Math.cos(alfa+1));
                ya =(int)(y1- k*Math.sin(alfa+1));

                x2 = xa;
                y2 = ya;
                //lado 2 del triangulo con origen desde el punto final de la linea y se extendiende hacia atras
                xa2=(int)(x1-k*Math.cos(alfa-1));
                ya2=(int)(y1-k*Math.sin(alfa-1));

                //lado 1 del triangulo con direcion inversa o bien se puede entender su contrario simetrico
                //pa q se lo imaginen xd
                xa =(int)(x1+ k*Math.cos(alfa+1));
                ya =(int)(y1+ k*Math.sin(alfa+1));

                x2 = xa;
                y2 = ya;
                //lado 2 del triangulo con direcion inversa o bien se puede entender su contrario simetrico
                xa2=(int)(x1+k*Math.cos(alfa-1));
                ya2=(int)(y1+k*Math.sin(alfa-1));
                // a ese triangulo que de forma en sentido inverso a lo que seria la flecha
                //se le calcula el punto medio a la base
                xMedio = (x2+xa2)/2;
                yMedio = (y2+ya2)/2;
                
                //y desde ese punto medio calculo nuevos puntos como si quisiera bijar la flecha nuevamente
                // de modo que el punto medio de la base de este chouque precisamente con el punto final del la linea
                //lado 1 lo que seria ese triangulo pero no se dibuja
                xa2=(int)(xMedio-k*Math.cos(alfa-1));
                ya2=(int)(yMedio-k*Math.sin(alfa-1));
                //lado2 lo que seria ese triangulo pero no se dibuja
                xa =(int)(xMedio- k*Math.cos(alfa+1));
                ya =(int)(yMedio- k*Math.sin(alfa+1));

                
                //g.drawLine(xa,ya,xa2,ya2);
                //se copian los puntos de la base del triangulo que en este caso seria el lado
                //del cuadrado que va unido a la recta
                int x1Aux = xa;
                int y1Aux = ya;
                int x2Aux = xa2;
                int y2Aux = ya2;
                // el largo del lado (modulo)
                double modulo = Math.sqrt((xa-xa2)*(xa-xa2)+((ya-ya2)*(ya-ya2)));

                double L =  Math.sqrt((double)((xa-xa2)*(xa-xa2)+(ya-ya2)*(ya-ya2)));
                
                // se calculan puntos paralelos a la otra recta en relacion al modulo para generar el cuadrado
                
                double xIni = xa + modulo * (ya2-ya) / L;
                double xFi =  xa2 + modulo * (ya2-ya) / L;
                double yIni = ya + modulo * (xa-xa2) / L;
                double yFi =  ya2 + modulo * (xa-xa2) / L;
                
                //solo resta unir los puntos y crear el cuadrado
                int cX[] = { x1Aux, x2Aux, (int)xFi, (int)xIni  };
                int cY[] = { y1Aux, y2Aux, (int)yFi, (int)yIni};
                p = new Polygon( cX, cY, 4);
                g2.fillPolygon(p);
            }
            
            if(this.tipoConectorFinal==CONECTORFLECHARELLENA)
            {
                //Angulo para formar la punta de flecha en el final de la linea
                alfa = Math.atan2(this.getYFin()-this.getY(), this.getXFin()-this.getX());
                
                x1 = this.getXFin();
                y1 = this.getYFin();
                
                k = this.getTamanio()*5;//Controla el tamaño de la flecha en relacion al grosor de la linea
                //Lado 1 de la flecha con origen en el punto de termino de la Linea
                xa =(int)(x1- k*Math.cos(alfa+1));//Nuevo Punto X del cual se trazara un extremo de la flecha
                ya =(int)(y1- k*Math.sin(alfa+1));//Nuevo Punto Y ""...

                x2 = xa;
                y2 = ya;
                //lado 2 de la Flecha
                xa2=(int)(x1-k*Math.cos(alfa-1));//Lo mismo que arrbba pero corresponden al otro extremo de la punta
                ya2=(int)(y1-k*Math.sin(alfa-1));//""

                 //Lado 1 de la Flecha invertida
                xa =(int)(x1+ k*Math.cos(alfa+1));//Se invierte el la dirrecion del extremo (punto X)
                ya =(int)(y1+ k*Math.sin(alfa+1));//Se invierte el la dirrecion del extremo (punto Y)

                x2 = xa;
                y2 = ya;
                //Lado 2 de la flecha invertida
                xa2=(int)(x1+k*Math.cos(alfa-1));//Se invierte el la dirrecion del extremo (punto X)
                ya2=(int)(y1+k*Math.sin(alfa-1));//Se invierte el la dirrecion del extremo (punto Y)

                 //despues de invertir la punta de flecha se toman las coordenadas para cerrar la base de esta
                //y calcular los el punto medio de esta
                xMedio = (x2+xa2)/2;//punto medio x
                yMedio = (y2+ya2)/2;// punto medio y

                //Lado 2 Flecha
                xa2=(int)(xMedio-k*Math.cos(alfa-1));//se calculan los nuevos puntos paralos extremos de la flecha 
                ya2=(int)(yMedio-k*Math.sin(alfa-1));//con referencia en el punto medio
                
                //Lado 1 Flecha
                xa =(int)(xMedio- k*Math.cos(alfa+1));// lo mismo con el lado 2
                ya =(int)(yMedio- k*Math.sin(alfa+1));

                // se tienen los 3 puntos que forman el triangulo
                int punX[] = { xa, xMedio, xa2};
                int punY[] = { ya, yMedio, ya2 };
                // se crea un poligono con ellos
                p = new Polygon(punX, punY, 3);
                g2.fillPolygon(p);// y se pinta dejando la flecha rellena
                g2.drawPolygon(p);
                
                
                
            }
            else if(this.tipoConectorFinal==CONECTORFLECHAVACIA)
            {
                //Angulo para formar la punta de flecha en el final de la linea
                alfa = Math.atan2(this.getYFin() - this.getY(), this.getXFin() - this.getX());
             
                x1 = this.getXFin();
                y1 = this.getYFin();
                k = this.getTamanio()*5;//Controla el tamaño de la flecha en relacion al grosor de la linea
                //Lado 1 de la flecha con origen en el punto de termino de la Linea
                xa =(int)(x1- k*Math.cos(alfa+1));//Nuevo Punto X del cual se trazara un extremo de la flecha
                ya =(int)(y1- k*Math.sin(alfa+1));//Nuevo Punto Y ""...

                x2 = xa;
                y2 = ya;
                //lado 2 de la Flecha
                xa2=(int)(x1-k*Math.cos(alfa-1));//Lo mismo que arrbba pero corresponden al otro extremo de la punta
                ya2=(int)(y1-k*Math.sin(alfa-1));//""

                //Lado 1 de la Flecha invertida
                xa =(int)(x1+ k*Math.cos(alfa+1));//Se invierte el la dirrecion del extremo (punto X)
                ya =(int)(y1+ k*Math.sin(alfa+1));//Se invierte el la dirrecion del extremo (punto Y)

                x2 = xa;
                y2 = ya;
                //Lado 2 de la Flecha invertida
                xa2=(int)(x1+k*Math.cos(alfa-1));//Se invierte el la dirrecion del extremo (punto X)
                ya2=(int)(y1+k*Math.sin(alfa-1));//Se invierte el la dirrecion del extremo (punto Y)

                 //despues de invertir la punta de flecha se toman las coordenadas para cerrar la base de esta
                //y calcular los el punto medio de esta
                xMedio = (x2+xa2)/2;//punto medio x
                yMedio = (y2+ya2)/2;//punto medio y

                //Lado 2
                xa2=(int)(xMedio-k*Math.cos(alfa-1));//se calculan los nuevos puntos paralos extremos de la flecha 
                ya2=(int)(yMedio-k*Math.sin(alfa-1));//con referencia del punto medio

                //lado 1
                xa =(int)(xMedio- k*Math.cos(alfa+1));
                ya =(int)(yMedio- k*Math.sin(alfa+1));

                g2.drawLine(xa2,ya2,xMedio,yMedio);//se dibuja un extremo
                g2.drawLine(xa, ya, xMedio, yMedio);//se dibuja el otro extremo
                g2.drawLine(xa,ya,xa2,ya2);//Cierra el Triangulo
                
                //y asi la linea no pasa por el interior del triangulo

            }
            else if(this.tipoConectorFinal==CIRCULO)
            {
                Circulo c = new Circulo(getXFin(), getYFin(), getTamanio()*2, Color.orange, Color.green, 2);
                g2.fill(c.getElipse());
                g2.draw(c.getElipse());

            }
            else if(this.tipoConectorFinal==ROMBO)
            {
                 //utilizo el angulo que se ocupo en la flecha
                alfa = Math.atan2(this.getYFin() - this.getY(), this.getXFin() - this.getX());
           
                x1 = this.getXFin();
                y1 = this.getYFin();
                k = this.getTamanio()*5;//Controla el tamaño de los lados del rombo (que se formara con 2 traingulos) en relacion al grosor de la linea
                //Lado 1 primer triangulo en sentido inverso como se necesita para el rombo
                xa =(int)(x1+ k*Math.cos(alfa+1));// al igual que el la flecha se calculas los puntos 
                ya =(int)(y1+ k*Math.sin(alfa+1));// para generar un lado
                
                int xCorregido =xa;
                int yCorregido =ya;
                x2 = xa;
                y2 = ya;
                //Lado 2 primer triangulo ens entido inverso
                xa2=(int)(x1+k*Math.cos(alfa-1));//se genera los puntos del otro lado
                ya2=(int)(y1+k*Math.sin(alfa-1));//en sentido inverso
                int xPs[] = {xa,x1,xa2};
                int yPs[] = {ya, y1, ya2};
                //se crea el primer triangulo con los 3 puntos
                //los puntos calculados y el punto de incio de la linea
                p = new Polygon( xPs, yPs, 3 );
                g2.fillPolygon(p);//se dibuja el triangulo
                int xCorregido2 = xa2;
                int yCorregido2 = ya2;
                 
                //se deterimina los puntos medios de la recta q cerraria ese trinagulo
                xMedio = (x2+xa2)/2;
                yMedio = (y2+ya2)/2;
                
                //a partir de ese punto  se calculan otros como para hacer otro trianen el mismo sentido
                xa2=(int)(xMedio+k*Math.cos(alfa-1));
                ya2=(int)(yMedio+k*Math.sin(alfa-1));

                xa =(int)(xMedio+ k*Math.cos(alfa+1));
                ya =(int)(yMedio+ k*Math.sin(alfa+1));

                 //pero solo se calcula el punto medio de lo que seria la base del otro traingulo
                xMedio = (xa+xa2)/2;
                yMedio = (ya+ya2)/2;

                int xP[] = {xCorregido,xMedio,xCorregido2};
                int yP[] = {yCorregido, yMedio, yCorregido2};
                //a partir de ese punto medio se tranzan las lineas hacia los puntos
                //de termino del primeros lados cerrando el rombo
                p = new Polygon( xP, yP, 3 );
                g2.fillPolygon(p);
               
            
            }
            else if(this.tipoConectorFinal==CUADRADO)
            {
                alfa = Math.atan2(this.getYFin() - this.getY(), this.getXFin() - this.getX());
                x1 = this.getXFin();
                y1 = this.getYFin();
                
                k = this.getTamanio()*(int)3.5;//matiene el tamaño del cuadrado en relacion al grosor de linea
                //lado 1 del triangulo con orginen desde el punto final de la linea y se extendiende hacia atras
                xa =(int)(x1- k*Math.cos(alfa+1));
                ya =(int)(y1- k*Math.sin(alfa+1));

                x2 = xa;
                y2 = ya;
                
                //lado 2 del triangulo con origen desde el punto final de la linea y se extendiende hacia atras
                xa2=(int)(x1-k*Math.cos(alfa-1));
                ya2=(int)(y1-k*Math.sin(alfa-1));
                //lado 1 del triangulo con direcion inversa o bien se puede entender su contrario simetrico
                //pa q se lo imaginen xd
                xa =(int)(x1+ k*Math.cos(alfa+1));
                ya =(int)(y1+ k*Math.sin(alfa+1));

                x2 = xa;
                y2 = ya;
                 //lado 2 del triangulo con direcion inversa o bien se puede entender su contrario simetrico
                xa2=(int)(x1+k*Math.cos(alfa-1));
                ya2=(int)(y1+k*Math.sin(alfa-1));
                  // a ese triangulo que de forma en sentido inverso a lo que seria la flecha
                //se le calcula el punto medio a la base
                xMedio = (x2+xa2)/2;
                yMedio = (y2+ya2)/2;
                //y desde ese punto medio calculo nuevos puntos como si quisiera bijar la flecha nuevamente
                // de modo que el punto medio de la base de este chouque precisamente con el punto final del la linea
                //lado 1 lo que seria ese triangulo pero no se dibuja
                
                xa2=(int)(xMedio-k*Math.cos(alfa-1));
                ya2=(int)(yMedio-k*Math.sin(alfa-1));

                xa =(int)(xMedio- k*Math.cos(alfa+1));
                ya =(int)(yMedio- k*Math.sin(alfa+1));

                //g.drawLine(xa,ya,xa2,ya2);//Cierra el Triangulo
                
                //se copian los puntos de la base del triangulo que en este caso seria el lado
                //del cuadrado que va unido a la recta
                int x1Aux = xa;
                int y1Aux = ya;
                int x2Aux = xa2;
                int y2Aux = ya2;
                 // el largo del lado (modulo)
                double modulo = Math.sqrt((xa-xa2)*(xa-xa2)+((ya-ya2)*(ya-ya2)));

                double L =  Math.sqrt((double)((xa-xa2)*(xa-xa2)+(ya-ya2)*(ya-ya2)));
                // se calculan puntos paralelos a la otra recta en relacion al modulo para generar el cuadrado

                double xIni = xa + modulo * (ya2-ya) / L;
                double xFi =  xa2 + modulo * (ya2-ya) / L;
                double yIni = ya + modulo * (xa-xa2) / L;
                double yFi =  ya2 + modulo * (xa-xa2) / L;
                //solo resta unir los puntos y crear el cuadrado
                int cX[] = { x1Aux, x2Aux, (int)xFi, (int)xIni  };
                int cY[] = { y1Aux, y2Aux, (int)yFi, (int)yIni};
                p = new Polygon( cX, cY, 4);
                g2.fillPolygon(p);
            }
            
            
        }
        
        else if (getRotacion() > 0)
        {
            radians = Math.toRadians(getRotacion());
            af.rotate(radians, getX()+(int) Math.round( (double) ( (double)(getXFin()-getX()) / (double)2 ) ), getY()+(int) Math.round( (double) ( (double)(getYFin()-getY()) / (double)2 ) ));
            ((Graphics2D)g).setTransform(af);
            if(this.tipoLinea == LINEANORMAL)
            {
                bordeFigura = configurarGuionLinea();
                line2D = new Line2D.Float( getX(), getY(),
                    this.xFin, this.yFin );
                g2.setColor(getColorBorde());
                g2.setStroke(bordeFigura);
                g2.draw(line2D);
            }
            else if(this.tipoLinea == LINEADOBLE )
            {
                bordeFigura = configurarGuionLinea();
                g2.setColor(getColorBorde());
                g2.setStroke(bordeFigura);

                int xI = getX();
                int yI = getY();
                int xF = this.getXFin();
                int yF = this.getYFin();
                line2D = new Line2D.Float( xI, yI,
                    xF, yF  );
                g2.draw(line2D);

                int pixeles = 10;
                double L =  Math.sqrt((double)((xI-xF)*(xI-xF)+(yI-yF)*(yI-yF)));

                double xIni = xI + pixeles * (yF-yI) / L;
                double xFi =  xF + pixeles * (yF-yI) / L;
                double yIni = yI + pixeles * (xI-xF) / L;
                double yFi =  yF + pixeles * (xI-xF) / L;

                line2D = new Line2D.Float( (int)xIni, (int)yIni,
                    (int)xFi, (int)yFi);
                g2.draw(line2D);

            }
            else if(this.tipoLinea == LINEATRIPLE)
            {
                bordeFigura = configurarGuionLinea();
                g2.setColor(getColorBorde());
                g2.setStroke(bordeFigura);

                int xI = getX();
                int yI = getY();
                int xF = this.getXFin();
                int yF = this.getYFin();
                line2D = new Line2D.Float( xI, yI,
                    xF, yF  );
                g2.draw(line2D);

                int pixeles = 10;
                double L =  Math.sqrt((double)((xI-xF)*(xI-xF)+(yI-yF)*(yI-yF)));

                double xIni = xI + pixeles * (yF-yI) / L;
                double xFi =  xF + pixeles * (yF-yI) / L;
                double yIni = yI + pixeles * (xI-xF) / L;
                double yFi =  yF + pixeles * (xI-xF) / L;

                line2D = new Line2D.Float( (int)xIni, (int)yIni,
                    (int)xFi, (int)yFi);
                g2.draw(line2D);

                double L2 =  Math.sqrt((double)((xI-xF)*(xI-xF)+(yI-yF)*(yI-yF)));

                xI = (int)(xIni + pixeles * (yF-yI) / L2);
                xF =  (int)(xFi + pixeles * (yF-yI) / L2);
                yI = (int)(yIni + pixeles * (xIni-xFi) / L2);
                yF =  (int)(yFi + pixeles * (xIni-xFi) / L2);

                line2D = new Line2D.Float( xI, yI,
                    xF, yF  );
                g2.draw(line2D);
            }
            Polygon p;
            double alfa;
            int x1, y1;
            int k ; 
            int xa;
            int ya;
            int x2;
            int y2;
            int xa2;
            int ya2;
            int xMedio;
            int yMedio;

            
            g2.setStroke(new BasicStroke(getTamanio(),  BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
           
            if(this.tipoConectorInicio==CONECTORFLECHARELLENA)
            {
                alfa = Math.atan2(  (-1)*(this.getYFin()-this.getY()),   (-1)*( this.getXFin()-this.getX() ) );
                x1 = this.getX();
                y1 = this.getY();
                k = this.getTamanio()*5;
                xa =(int)(x1- k*Math.cos(alfa+1));
                ya =(int)(y1- k*Math.sin(alfa+1));

                x2 = xa;
                y2 = ya;
                xa2=(int)(x1-k*Math.cos(alfa-1));
                ya2=(int)(y1-k*Math.sin(alfa-1));

                xa =(int)(x1+ k*Math.cos(alfa+1));
                ya =(int)(y1+ k*Math.sin(alfa+1));

                x2 = xa;
                y2 = ya;
                xa2=(int)(x1+k*Math.cos(alfa-1));
                ya2=(int)(y1+k*Math.sin(alfa-1));

                xMedio = (x2+xa2)/2;
                yMedio = (y2+ya2)/2;

                xa2=(int)(xMedio-k*Math.cos(alfa-1));
                ya2=(int)(yMedio-k*Math.sin(alfa-1));

                xa =(int)(xMedio- k*Math.cos(alfa+1));
                ya =(int)(yMedio- k*Math.sin(alfa+1));

                int punX[] = { xa, xMedio, xa2};
                int punY[] = { ya, yMedio, ya2 };
                p = new Polygon(punX, punY, 3);
                g2.fillPolygon(p);
                g2.drawPolygon(p);
                
            }
            else if(this.tipoConectorInicio==CONECTORFLECHAVACIA)
            {
                alfa = Math.atan2(  (-1)*(this.getYFin()-this.getY()),   (-1)*( this.getXFin()-this.getX() ) );
                x1 = this.getX();
                y1 = this.getY();
                k = this.getTamanio()*5;
                xa =(int)(x1- k*Math.cos(alfa+1));
                ya =(int)(y1- k*Math.sin(alfa+1));

                x2 = xa;
                y2 = ya;
                xa2=(int)(x1-k*Math.cos(alfa-1));
                ya2=(int)(y1-k*Math.sin(alfa-1));

                xa =(int)(x1+ k*Math.cos(alfa+1));
                ya =(int)(y1+ k*Math.sin(alfa+1));

                x2 = xa;
                y2 = ya;
                xa2=(int)(x1+k*Math.cos(alfa-1));
                ya2=(int)(y1+k*Math.sin(alfa-1));

                xMedio = (x2+xa2)/2;
                yMedio = (y2+ya2)/2;

                xa2=(int)(xMedio-k*Math.cos(alfa-1));
                ya2=(int)(yMedio-k*Math.sin(alfa-1));

                xa =(int)(xMedio- k*Math.cos(alfa+1));
                ya =(int)(yMedio- k*Math.sin(alfa+1));

                g2.drawLine(xa2,ya2,xMedio,yMedio);
                g2.drawLine(xa, ya, xMedio, yMedio);
                g2.drawLine(xa,ya,xa2,ya2);
                
                
               
            }
            else if(this.tipoConectorInicio==CIRCULO)
            {
                Circulo c = new Circulo(getX(), getY(), getTamanio()*2, Color.orange, Color.green, 2);
                g2.fill(c.getElipse());
                g2.draw(c.getElipse());
            }
            else if(this.tipoConectorInicio==ROMBO)
            {
                alfa = Math.atan2(  (-1)*(this.getYFin()-this.getY()),   (-1)*( this.getXFin()-this.getX() ) );

                x1 = this.getX();
                y1 = this.getY();
                k = this.getTamanio()*5;
                xa =(int)(x1+ k*Math.cos(alfa+1));
                ya =(int)(y1+ k*Math.sin(alfa+1));
                
                int xCorregido =xa;
                int yCorregido =ya;
                x2 = xa;
                y2 = ya;
                xa2=(int)(x1+k*Math.cos(alfa-1));
                ya2=(int)(y1+k*Math.sin(alfa-1));
                int xPs[] = {xa,x1,xa2};
                int yPs[] = {ya, y1, ya2};
                p = new Polygon( xPs, yPs, 3 );
                g2.fillPolygon(p);
                int xCorregido2 = xa2;
                int yCorregido2 = ya2;


                xMedio = (x2+xa2)/2;
                yMedio = (y2+ya2)/2;
                xa2=(int)(xMedio+k*Math.cos(alfa-1));
                ya2=(int)(yMedio+k*Math.sin(alfa-1));

                xa =(int)(xMedio+ k*Math.cos(alfa+1));
                ya =(int)(yMedio+ k*Math.sin(alfa+1));

                xMedio = (xa+xa2)/2;
                yMedio = (ya+ya2)/2;

                int xP[] = {xCorregido,xMedio,xCorregido2};
                int yP[] = {yCorregido, yMedio, yCorregido2};
                p = new Polygon( xP, yP, 3 );
                g2.fillPolygon(p);
            
            }
            else if(this.tipoConectorInicio==CUADRADO)
            {
                 //nuevamente se usa el angulo del bendito triangulo(flecha)
                alfa = Math.atan2(this.getYFin() - this.getY(), this.getXFin() - this.getX());
                x1 = this.getX();
                y1 = this.getY();
                k = this.getTamanio()*(int)3.5;
                xa =(int)(x1- k*Math.cos(alfa+1));
                ya =(int)(y1- k*Math.sin(alfa+1));

                x2 = xa;
                y2 = ya;
                xa2=(int)(x1-k*Math.cos(alfa-1));
                ya2=(int)(y1-k*Math.sin(alfa-1));

                xa =(int)(x1+ k*Math.cos(alfa+1));
                ya =(int)(y1+ k*Math.sin(alfa+1));

                x2 = xa;
                y2 = ya;
                xa2=(int)(x1+k*Math.cos(alfa-1));
                ya2=(int)(y1+k*Math.sin(alfa-1));

                xMedio = (x2+xa2)/2;
                yMedio = (y2+ya2)/2;

                xa2=(int)(xMedio-k*Math.cos(alfa-1));
                ya2=(int)(yMedio-k*Math.sin(alfa-1));

                xa =(int)(xMedio- k*Math.cos(alfa+1));
                ya =(int)(yMedio- k*Math.sin(alfa+1));

                //g.drawLine(xa,ya,xa2,ya2);//Cierra el Triangulo
                int x1Aux = xa;
                int y1Aux = ya;
                int x2Aux = xa2;
                int y2Aux = ya2;
                double modulo = Math.sqrt((xa-xa2)*(xa-xa2)+((ya-ya2)*(ya-ya2)));

                double L =  Math.sqrt((double)((xa-xa2)*(xa-xa2)+(ya-ya2)*(ya-ya2)));

                double xIni = xa + modulo * (ya2-ya) / L;
                double xFi =  xa2 + modulo * (ya2-ya) / L;
                double yIni = ya + modulo * (xa-xa2) / L;
                double yFi =  ya2 + modulo * (xa-xa2) / L;
                
                int cX[] = { x1Aux, x2Aux, (int)xFi, (int)xIni  };
                int cY[] = { y1Aux, y2Aux, (int)yFi, (int)yIni};
                p = new Polygon( cX, cY, 4);
                g2.fillPolygon(p);
            }
            
            if(this.tipoConectorFinal==CONECTORFLECHARELLENA)
            {
                alfa = Math.atan2(this.getYFin() - this.getY(), this.getXFin() - this.getX());
                x1 = this.getXFin();
                y1 = this.getYFin();
                k = this.getTamanio()*5;
                xa =(int)(x1- k*Math.cos(alfa+1));
                ya =(int)(y1- k*Math.sin(alfa+1));

                x2 = xa;
                y2 = ya;
                xa2=(int)(x1-k*Math.cos(alfa-1));
                ya2=(int)(y1-k*Math.sin(alfa-1));

                xa =(int)(x1+ k*Math.cos(alfa+1));
                ya =(int)(y1+ k*Math.sin(alfa+1));

                x2 = xa;
                y2 = ya;
                xa2=(int)(x1+k*Math.cos(alfa-1));
                ya2=(int)(y1+k*Math.sin(alfa-1));

                xMedio = (x2+xa2)/2;
                yMedio = (y2+ya2)/2;

                xa2=(int)(xMedio-k*Math.cos(alfa-1));
                ya2=(int)(yMedio-k*Math.sin(alfa-1));

                xa =(int)(xMedio- k*Math.cos(alfa+1));
                ya =(int)(yMedio- k*Math.sin(alfa+1));

               
                int punX[] = { xa, xMedio, xa2};
                int punY[] = { ya, yMedio, ya2 };
                p = new Polygon(punX, punY, 3);
                g2.fillPolygon(p);
                g2.drawPolygon(p);
                
                
                
            }
            else if(this.tipoConectorFinal==CONECTORFLECHAVACIA)
            {
                alfa = Math.atan2(this.getYFin() - this.getY(), this.getXFin() - this.getX());
             
                x1 = this.getXFin();
                y1 = this.getYFin();
                k = this.getTamanio()*5;
                xa =(int)(x1- k*Math.cos(alfa+1));
                ya =(int)(y1- k*Math.sin(alfa+1));

                x2 = xa;
                y2 = ya;
                xa2=(int)(x1-k*Math.cos(alfa-1));
                ya2=(int)(y1-k*Math.sin(alfa-1));

                xa =(int)(x1+ k*Math.cos(alfa+1));
                ya =(int)(y1+ k*Math.sin(alfa+1));

                x2 = xa;
                y2 = ya;
                xa2=(int)(x1+k*Math.cos(alfa-1));
                ya2=(int)(y1+k*Math.sin(alfa-1));

                xMedio = (x2+xa2)/2;
                yMedio = (y2+ya2)/2;

                xa2=(int)(xMedio-k*Math.cos(alfa-1));
                ya2=(int)(yMedio-k*Math.sin(alfa-1));

                xa =(int)(xMedio- k*Math.cos(alfa+1));
                ya =(int)(yMedio- k*Math.sin(alfa+1));

                g2.drawLine(xa2,ya2,xMedio,yMedio);
                g2.drawLine(xa, ya, xMedio, yMedio);
                g2.drawLine(xa,ya,xa2,ya2);//Cierra el Triangulo

            }
            else if(this.tipoConectorFinal==CIRCULO)
            {
                Circulo c = new Circulo(getXFin(), getYFin(), getTamanio()*2, Color.orange, Color.green, 2);
                g2.fill(c.getElipse());
                g2.draw(c.getElipse());

            }
            else if(this.tipoConectorFinal==ROMBO)
            {
                alfa = Math.atan2(this.getYFin() - this.getY(), this.getXFin() - this.getX());
           
                x1 = this.getXFin();
                y1 = this.getYFin();
                k = this.getTamanio()*5;
                xa =(int)(x1+ k*Math.cos(alfa+1));
                ya =(int)(y1+ k*Math.sin(alfa+1));
                
                int xCorregido =xa;
                int yCorregido =ya;
                x2 = xa;
                y2 = ya;
                xa2=(int)(x1+k*Math.cos(alfa-1));
                ya2=(int)(y1+k*Math.sin(alfa-1));
                int xPs[] = {xa,x1,xa2};
                int yPs[] = {ya, y1, ya2};
                p = new Polygon( xPs, yPs, 3 );
                g2.fillPolygon(p);
                int xCorregido2 = xa2;
                int yCorregido2 = ya2;


                xMedio = (x2+xa2)/2;
                yMedio = (y2+ya2)/2;
                xa2=(int)(xMedio+k*Math.cos(alfa-1));
                ya2=(int)(yMedio+k*Math.sin(alfa-1));

                xa =(int)(xMedio+ k*Math.cos(alfa+1));
                ya =(int)(yMedio+ k*Math.sin(alfa+1));

                xMedio = (xa+xa2)/2;
                yMedio = (ya+ya2)/2;

                int xP[] = {xCorregido,xMedio,xCorregido2};
                int yP[] = {yCorregido, yMedio, yCorregido2};
                p = new Polygon( xP, yP, 3 );
                g2.fillPolygon(p);
            }
            else if(this.tipoConectorFinal==CUADRADO)
            {
                alfa = Math.atan2(this.getYFin() - this.getY(), this.getXFin() - this.getX());
                x1 = this.getXFin();
                y1 = this.getYFin();
                
                k = this.getTamanio()*(int)3.5;
                xa =(int)(x1- k*Math.cos(alfa+1));
                ya =(int)(y1- k*Math.sin(alfa+1));

                x2 = xa;
                y2 = ya;
                xa2=(int)(x1-k*Math.cos(alfa-1));
                ya2=(int)(y1-k*Math.sin(alfa-1));

                xa =(int)(x1+ k*Math.cos(alfa+1));
                ya =(int)(y1+ k*Math.sin(alfa+1));

                x2 = xa;
                y2 = ya;
                xa2=(int)(x1+k*Math.cos(alfa-1));
                ya2=(int)(y1+k*Math.sin(alfa-1));

                xMedio = (x2+xa2)/2;
                yMedio = (y2+ya2)/2;

                xa2=(int)(xMedio-k*Math.cos(alfa-1));
                ya2=(int)(yMedio-k*Math.sin(alfa-1));

                xa =(int)(xMedio- k*Math.cos(alfa+1));
                ya =(int)(yMedio- k*Math.sin(alfa+1));

                //g.drawLine(xa,ya,xa2,ya2);//Cierra el Triangulo
                int x1Aux = xa;
                int y1Aux = ya;
                int x2Aux = xa2;
                int y2Aux = ya2;
                double modulo = Math.sqrt((xa-xa2)*(xa-xa2)+((ya-ya2)*(ya-ya2)));

                double L =  Math.sqrt((double)((xa-xa2)*(xa-xa2)+(ya-ya2)*(ya-ya2)));

                double xIni = xa + modulo * (ya2-ya) / L;
                double xFi =  xa2 + modulo * (ya2-ya) / L;
                double yIni = ya + modulo * (xa-xa2) / L;
                double yFi =  ya2 + modulo * (xa-xa2) / L;
                
                int cX[] = { x1Aux, x2Aux, (int)xFi, (int)xIni  };
                int cY[] = { y1Aux, y2Aux, (int)yFi, (int)yIni};
                p = new Polygon( cX, cY, 4);
                g2.fillPolygon(p);
            }
            
            af.rotate(-radians, getX()+(int) Math.round( (double) ( (double)(getXFin()-getX()) / (double)2 ) ), getY()+(int) Math.round( (double) ( (double)(getYFin()-getY()) / (double)2 ) ));
            ((Graphics2D)g).setTransform(af);
        }    
        
        
        
        
        
       
    }
    
    public BasicStroke configurarGuionLinea()
    {
        BasicStroke  basicStroke = null;
        if(this.tipoGuion == GUIONDESACTIVADO )
        {
            return new BasicStroke(getTamanio(),  BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
        }
        else if( this.tipoGuion == SOLOGUION ) 
        {
            float dash[] = {30, 10};
            return new BasicStroke( getTamanio(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 5.0f, dash, 0.0f);
            
        }
        else if( this.tipoGuion == GUIONPUNTO  )
        {
            float dash[] = {30, 5, 5, 5};
            return new BasicStroke( getTamanio(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 5.0f, dash, 0.0f);
            
        }
        else if( this.tipoGuion == SOLOPUNTO )
        {
            float dash[] = {5};
            return new BasicStroke( getTamanio(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 5.0f, dash, 0.0f);
            
        }
        return null;
    }

    
    
    
    public Polygon getPoligono()
    {
        return poligono;
    }
    
    
    
    
     public int getXFin()
    {
        return xFin;
    }    

    public int getxProporcionInicial()
    {
        return xProporcionInicial;
    }

    public void setxProporcionInicial(int xProporcionInicial)
    {
        this.xProporcionInicial = xProporcionInicial;
    }

    public int getyProporcionInicial()
    {
        return yProporcionInicial;
    }

    public void setyProporcionInicial(int yProporcionInicial)
    {
        this.yProporcionInicial = yProporcionInicial;
    }

    public int getxProporcionFinal()
    {
        return xProporcionFinal;
    }

    public void setxProporcionFinal(int xProporcionFinal)
    {
        this.xProporcionFinal = xProporcionFinal;
    }

    public int getyProporcionFinal()
    {
        return yProporcionFinal;
    }

    public void setyProporcionFinal(int yProporcionFinal)
    {
        this.yProporcionFinal = yProporcionFinal;
    }

    
    
    public void setXFin(int xFin)
    {
        this.xFin = xFin;
    }

    public int getYFin()
    {
        return yFin;
    }

    public void setYFin(int yFin)
    {
        this.yFin = yFin;
    }
    
    
    
    public void setModo(int modo)
    {
        this.tipoLinea = modo;
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

    public int getTipoGuion()
    {
        return tipoGuion;
    }

    public void setTipoGuion(int tipoGuion)
    {
        this.tipoGuion = tipoGuion;
    }

    public int getTipoLinea()
    {
        return tipoLinea;
    }

    public void setTipoLinea(int tipoLinea)
    {
        this.tipoLinea = tipoLinea;
    }

    
    /*FLecha Rellena Inicio
                 * alfa = Math.atan2(this.getYFin() - this.getY(), this.getXFin() - this.getX());

                x1 = this.getXFin();
                y1 = this.getYFin();
                k = this.getTamanio()*5; 
                xa =(int)(x1- k*Math.cos(alfa+1));
                ya =(int)(y1- k*Math.sin(alfa+1));

                x2 = xa;
                y2 = ya;
                xa2=(int)(x1-k*Math.cos(alfa-1));
                ya2=(int)(y1-k*Math.sin(alfa-1));

                xMedio = (int) Math.round((double)(x2+xa)/(double)2);
                yMedio = (int) Math.round((double)(y2+ya)/(double)2);

                int punX[] = {x1, xa, xa2};
                int punY[] = {y1, ya, ya2 };
                p = new Polygon(punX, punY, 3);
                g.fillPolygon(p);
                g.drawPolygon(p);*/
    
    
     /*Linea Vacia
      * alfa = Math.atan2(  (-1)*(this.getYFin()-this.getY()),   (-1)*( this.getXFin()-this.getX() ) );

                x1 = this.getX();
                y1 = this.getY();
                k = this.getTamanio()*5; 
                xa =(int)(x1- k*Math.cos(alfa+1));
                ya =(int)(y1- k*Math.sin(alfa+1));

                g.drawLine(xa,ya,x1,y1);

                x2 = xa;
                y2 = ya;
                xa2=(int)(x1-k*Math.cos(alfa-1));
                ya2=(int)(y1-k*Math.sin(alfa-1));

                xMedio = (int) Math.round((double)(x2+xa)/(double)2);
                yMedio = (int) Math.round((double)(y2+ya)/(double)2);

                int punX[] = {x1, xa, xa2};
                int punY[] = {y1, ya, ya2 };
                p = new Polygon(punX, punY, 3);
                g.drawPolygon(p);*/
    
}
