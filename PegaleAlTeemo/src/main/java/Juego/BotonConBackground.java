package Juego;


import java.awt.*;
import javax.swing.JButton;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ElFel
 */
public class BotonConBackground extends JButton{
    private Image background;
    public BotonConBackground(Image background){
        this.background= background;
        setContentAreaFilled(false);
    }
    @Override
    protected void paintComponent(Graphics g) {
        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
        super.paintComponent(g);
    }
}
