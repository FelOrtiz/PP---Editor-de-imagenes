/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Collage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author HoLeX
 */
public class PanelPatrones extends JPanel implements MouseListener
{
    private JPanel panelFiguras;
    private JLabel labelGato;
    private JLabel labelBatman;
    private JLabel labelCorazon;
    private JLabel labelApple;
    private JLabel labelFish;
    private JLabel labelBoy;
    private JLabel labelCloud;
    private JLabel labelAirplane;
    private JLabel labelStar;
    private JLabel labelMoon;
    
    private JPanel panelTexto;
    private JLabel labelTexto;
    private JTextField campoTexto;
    
    private BufferedImage gato;
    private BufferedImage batman;
    private BufferedImage corazon;
    private BufferedImage apple;
    private BufferedImage pez;
    private BufferedImage hombre;
    private BufferedImage nube;
    private BufferedImage avion;
    private BufferedImage estrella;
    private BufferedImage luna;
    
    private PanelAreaTrabajo panelAreaTrabajo;
    
    public PanelPatrones(PanelAreaTrabajo panelAreaTrabajo)
    {
        this.panelAreaTrabajo = panelAreaTrabajo;
        
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(600, 200));
        this.setBackground(Color.WHITE);
        
        this.gato = this.panelAreaTrabajo.imageIconToBufferedImage((new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/maskCat.png"))).getImage()));
        this.batman = this.panelAreaTrabajo.imageIconToBufferedImage((new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/maskBatman.png"))).getImage()));
        this.apple = this.panelAreaTrabajo.imageIconToBufferedImage((new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/maskApple.png"))).getImage()));
        this.corazon = this.panelAreaTrabajo.imageIconToBufferedImage((new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/maskHeart.png"))).getImage()));
        this.pez = this.panelAreaTrabajo.imageIconToBufferedImage((new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/maskFish.png"))).getImage()));
        this.hombre = this.panelAreaTrabajo.imageIconToBufferedImage((new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/maskBoy.png"))).getImage()));
        this.nube = this.panelAreaTrabajo.imageIconToBufferedImage((new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/maskCloud.png"))).getImage()));
        this.avion = this.panelAreaTrabajo.imageIconToBufferedImage((new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/maskAirplane.png"))).getImage()));
        this.estrella = this.panelAreaTrabajo.imageIconToBufferedImage((new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/maskStar.png"))).getImage()));
        this.luna = this.panelAreaTrabajo.imageIconToBufferedImage((new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/maskMoon.png"))).getImage()));
        
        this.panelFiguras = new JPanel();
        this.panelFiguras.setLayout(new BoxLayout(this.panelFiguras, BoxLayout.Y_AXIS));
        this.panelFiguras.setBackground(Color.WHITE);
        this.panelFiguras.addMouseListener(this);
        
        this.labelGato = new JLabel();
        this.labelGato.setBackground(Color.WHITE);
        this.labelGato.setIcon(new ImageIcon(this.panelAreaTrabajo.EscalarImagen((this.panelAreaTrabajo.imageIconToBufferedImage((new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/maskCat.png")))).getImage())), 200, 200)));
        this.labelGato.setHorizontalAlignment(JLabel.CENTER);
        this.labelGato.setPreferredSize(new Dimension(120, 120));
        
        this.labelBatman = new JLabel();
        this.labelBatman.setBackground(Color.WHITE);
        this.labelBatman.setIcon(new ImageIcon(this.panelAreaTrabajo.escalarCercano((this.panelAreaTrabajo.imageIconToBufferedImage((new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/maskBatman.png")))).getImage())), 200)));
        this.labelBatman.setHorizontalAlignment(JLabel.CENTER);
        this.labelBatman.setPreferredSize(new Dimension(120, 120));
        
        this.labelApple = new JLabel();
        this.labelApple.setBackground(Color.WHITE);
        this.labelApple.setIcon(new ImageIcon(this.panelAreaTrabajo.EscalarImagen((this.panelAreaTrabajo.imageIconToBufferedImage((new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/maskApple.png")))).getImage())), 200, 200)));
        this.labelApple.setHorizontalAlignment(JLabel.CENTER);
        this.labelApple.setPreferredSize(new Dimension(120, 120));
        
        this.labelCorazon = new JLabel();
        this.labelCorazon.setBackground(Color.WHITE);
        this.labelCorazon.setIcon(new ImageIcon(this.panelAreaTrabajo.EscalarImagen((this.panelAreaTrabajo.imageIconToBufferedImage((new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/maskHeart.png")))).getImage())), 200, 200)));
        this.labelCorazon.setHorizontalAlignment(JLabel.CENTER);
        this.labelCorazon.setPreferredSize(new Dimension(120, 120));
        
        this.labelFish = new JLabel();
        this.labelFish.setBackground(Color.WHITE);
        this.labelFish.setIcon(new ImageIcon(this.panelAreaTrabajo.EscalarImagen((this.panelAreaTrabajo.imageIconToBufferedImage((new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/maskFish.png")))).getImage())), 200, 200)));
        this.labelFish.setHorizontalAlignment(JLabel.CENTER);
        this.labelFish.setPreferredSize(new Dimension(120, 120));
        
        this.labelBoy = new JLabel();
        this.labelBoy.setBackground(Color.WHITE);
        this.labelBoy.setIcon(new ImageIcon(this.panelAreaTrabajo.EscalarImagen((this.panelAreaTrabajo.imageIconToBufferedImage((new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/maskBoy.png")))).getImage())), 200, 200)));
        this.labelBoy.setHorizontalAlignment(JLabel.CENTER);
        this.labelBoy.setPreferredSize(new Dimension(120, 120));
        
        this.labelCloud = new JLabel();
        this.labelCloud.setBackground(Color.WHITE);
        this.labelCloud.setIcon(new ImageIcon(this.panelAreaTrabajo.EscalarImagen((this.panelAreaTrabajo.imageIconToBufferedImage((new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/maskCloud.png")))).getImage())), 200, 200)));
        this.labelCloud.setHorizontalAlignment(JLabel.CENTER);
        this.labelCloud.setPreferredSize(new Dimension(120, 120));
        
        this.labelAirplane = new JLabel();
        this.labelAirplane.setBackground(Color.WHITE);
        this.labelAirplane.setIcon(new ImageIcon(this.panelAreaTrabajo.EscalarImagen((this.panelAreaTrabajo.imageIconToBufferedImage((new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/maskAirplane.png")))).getImage())), 200, 200)));
        this.labelAirplane.setHorizontalAlignment(JLabel.CENTER);
        this.labelAirplane.setPreferredSize(new Dimension(120, 120));
        
        this.labelStar= new JLabel();
        this.labelStar.setBackground(Color.WHITE);
        this.labelStar.setIcon(new ImageIcon(this.panelAreaTrabajo.EscalarImagen((this.panelAreaTrabajo.imageIconToBufferedImage((new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/maskStar.png")))).getImage())), 200, 200)));
        this.labelStar.setHorizontalAlignment(JLabel.CENTER);
        this.labelStar.setPreferredSize(new Dimension(120, 120));
        
        this.labelMoon = new JLabel();
        this.labelMoon.setBackground(Color.WHITE);
        this.labelMoon.setIcon(new ImageIcon(this.panelAreaTrabajo.EscalarImagen((this.panelAreaTrabajo.imageIconToBufferedImage((new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/maskMoon.png")))).getImage())), 200, 200)));
        this.labelMoon.setHorizontalAlignment(JLabel.CENTER);
        this.labelMoon.setPreferredSize(new Dimension(120, 120));

        
        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.WHITE);
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
        
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.WHITE);
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
        
        panel1.add(labelGato);
        panel1.add(labelBatman);
        panel1.add(labelCorazon);
        panel1.add(labelApple);
        panel1.add(labelFish);
        
        panel2.add(labelBoy);
        panel2.add(labelCloud);
        panel2.add(labelAirplane);
        panel2.add(labelStar);
        panel2.add(labelMoon);
        
        this.panelFiguras.add(panel1);
        this.panelFiguras.add(panel2);
        
        this.panelTexto = new JPanel(new BorderLayout());
        this.panelTexto.setBorder(BorderFactory.createEmptyBorder(10, 0, 3, 0));
        
        this.campoTexto = new JTextField();
        this.campoTexto.setEditable(false);
        this.campoTexto.setText("Ninguna");
        this.campoTexto.setHorizontalAlignment(JTextField.CENTER);
        
        this.labelTexto = new JLabel("Imagen seleccionada: ");
        this.labelTexto.setLabelFor(this.campoTexto);
        
        this.panelTexto.add(labelTexto, BorderLayout.WEST);
        this.panelTexto.add(campoTexto, BorderLayout.CENTER);
        
        this.add(panelFiguras, BorderLayout.CENTER);
        this.add(panelTexto, BorderLayout.SOUTH);
    }

    public JTextField getCampoTexto()
    {
        return campoTexto;
    }

    public JLabel getLabelGato()
    {
        return labelGato;
    }

    public JLabel getLabelCorazon()
    {
        return labelCorazon;
    }

    public JLabel getLabelTexto()
    {
        return labelTexto;
    }

    public BufferedImage getGato()
    {
        return gato;
    }

    public BufferedImage getBatman()
    {
        return batman;
    }

    public BufferedImage getCorazon()
    {
        return corazon;
    }

    public BufferedImage getApple()
    {
        return apple;
    }

    public BufferedImage getPez()
    {
        return pez;
    }

    public BufferedImage getHombre()
    {
        return hombre;
    }

    public BufferedImage getNube()
    {
        return nube;
    }

    public BufferedImage getAvion()
    {
        return avion;
    }

    public BufferedImage getEstrella()
    {
        return estrella;
    }

    public BufferedImage getLuna()
    {
        return luna;
    }
    
    @Override
    public void mouseClicked(MouseEvent e)
    {
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        if(e.getButton() == MouseEvent.BUTTON1 && e.getSource() == this.panelFiguras)
        {
            if(e.getX() < 120 && e.getY() < 95)
            {
                campoTexto.setText("Gato");
                campoTexto.setHorizontalAlignment(JTextField.CENTER);
            }
            else if(e.getX()> 120 && e.getX() < 240 && e.getY() < 95)
            {
                campoTexto.setText("Batman");
                campoTexto.setHorizontalAlignment(JTextField.CENTER);
            }
            else if(e.getX() > 240 && e.getX() < 360 && e.getY() < 95)
            {
                campoTexto.setText("Corazón");
                campoTexto.setHorizontalAlignment(JTextField.CENTER);
            }
            else if(e.getX() > 360 && e.getX() < 480 && e.getY() < 95)
            {
                campoTexto.setText("Apple");
                campoTexto.setHorizontalAlignment(JTextField.CENTER);
            }
            else if(e.getX() > 480 && e.getY() < 95)
            {
                campoTexto.setText("Pez");
                campoTexto.setHorizontalAlignment(JTextField.CENTER);
            }
            else if(e.getX() < 120 && e.getY() > 95)
            {
                campoTexto.setText("Hombre");
                campoTexto.setHorizontalAlignment(JTextField.CENTER);
            }
            else if(e.getX()> 120 && e.getX() < 240 && e.getY() > 95)
            {
                campoTexto.setText("Nube");
                campoTexto.setHorizontalAlignment(JTextField.CENTER);
            }
            else if(e.getX() > 240 && e.getX() < 360 && e.getY() > 95)
            {
                campoTexto.setText("Avión");
                campoTexto.setHorizontalAlignment(JTextField.CENTER);
            }
            else if(e.getX() > 360 && e.getX() < 480 && e.getY() > 95)
            {
                campoTexto.setText("Estrella");
                campoTexto.setHorizontalAlignment(JTextField.CENTER);
            }
            else if(e.getX() > 480 && e.getY() > 95)
            {
                campoTexto.setText("Luna");
                campoTexto.setHorizontalAlignment(JTextField.CENTER);
            }
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
