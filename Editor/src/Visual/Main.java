/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Visual;
import java.awt.Color;
import java.text.ParseException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author HoLeX
 */
public class Main 
{
    public static void main(String[] args) throws ParseException
    {

        try 
        {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } 
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) 
        {
        }       
        VentanaPrincipal ventana = new VentanaPrincipal();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);        
    } 
}