package GUI;



import Controller.Controller;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SquareButton extends JButton implements ActionListener {

    private int buttonX;
    private int buttonY;

    public SquareButton(int x, int y)
    {
        this.addActionListener(this);
        buttonX = x;
        buttonY = y;
    }


    /**
     * A function that gets the color that the square should be
     * @param x the x-coordinate of the board of the SquareButton
     * @param y the y-coordinate of the board of the SquareButton
     * @return the correct color
     */
    public Color getCorrectColor(int x,int y)
    {
        if((x%2==0 && y%2==0) || (x%2!=0 && y%2!=0))
            return new Color(255,200,155);
        else return new Color(131,58,0);
    }


    /**
     * An overridden function that dictates the behavior when a button is pressed (a square)
     * @param actionEvent the button being pressed
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Controller.buttonAccessed(this);
    }

    /**
     * A getter function that returns the y-coordinate of the button
     * @return the x-coordinate of the button
     */
    public int getButtonX()
    {
        return buttonX;
    }

    /**
     * A getter function that returns the y-coordinate of the button
     * @return the y-coordinate of the button
     */
    public int getButtonY()
    {
        return buttonY;
    }



}
