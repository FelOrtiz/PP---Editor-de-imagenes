/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Administracion;

import java.awt.Color;
import java.awt.Component;
import java.io.File;
import javax.swing.JTree;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author HoLeX
 */
public class RendererArbol extends DefaultTreeCellRenderer 
{
    private FileSystemView fsv;
    
    public RendererArbol() 
    {
        this(FileSystemView.getFileSystemView());
    }

    public RendererArbol(FileSystemView fsv) 
    {
        this.fsv = fsv;
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) 
    {
        if (!(value instanceof File)) 
        {
            return super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        }

        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

        setText(fsv.getSystemDisplayName((File) value));
        setIcon(fsv.getSystemIcon((File) value));

        return this;
    }
}