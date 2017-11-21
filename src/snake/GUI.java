package snake;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class GUI extends JFrame 
{
   public GUI() 
   {
      super("java snake");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //getContentPane().add(null);
      pack();
      setLocationRelativeTo(null);
      setVisible(true);
   }

   public static void main(String[] args) 
   {
      new GUI();
   }
}