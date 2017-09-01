/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author shaun.lee
 */
public class HangmanViewer 
{
    public static void main (String args[]) throws IOException
    {
        JFrame frame = new Hangman();
      frame.setSize(500,500);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);    
    }
 
}
