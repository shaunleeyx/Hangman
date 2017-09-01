/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author shaun.lee
 */
public class Hangman extends JFrame 
{
TextFieldListener listener = new TextFieldListener();
JTextField field = new JTextField(4);
JLabel word = new JLabel("Guessed word: ");
JLabel Missed = new JLabel("Missed letters:");
String wordtemp;
int wordLength;
char[] wordarr;
int guesscount = 0;
Hangman() throws IOException
{
 wordPicker();
  wordLength = wordtemp.length();
 wordarr = wordtemp.toCharArray();
 for(int z = 0; z < wordLength; z++){
     word.setText(word.getText()+"*");
 }
 JPanel hanger = new hanger();
 JPanel panel = new JPanel();
 JPanel southpanel = new JPanel();
 southpanel.setLayout(new BorderLayout());
 panel.setLayout(new BorderLayout());
 panel.add(hanger,BorderLayout.CENTER);
 southpanel.add(field, BorderLayout.SOUTH);
 southpanel.add(word,BorderLayout.CENTER);
 southpanel.add(Missed,BorderLayout.NORTH);
 panel.add(southpanel,BorderLayout.SOUTH);
 this.add(panel);
 field.addActionListener(listener);
 
}
public class hanger extends JPanel {
	hanger() {
	    setPreferredSize(new Dimension(150,150));
	}
	
	public void paint (Graphics g) {
	    g.setColor(Color.BLACK);
            g.drawRect(25,175, 125, 12);
            g.drawLine(87, 175, 87, 12);
            g.drawLine(87,12,175,12);
	    
            if(guesscount >= 1)
		g.drawLine(175, 12, 175, 25);
	    if(guesscount >= 2)
		g.drawOval(150, 25, 50, 50);
	    if(guesscount >= 3)
		g.drawLine(175, 75, 175, 150);
	    if(guesscount >= 4)
		g.drawLine(175,150,125,162);
	    if(guesscount >= 5)
		g.drawLine(175,150,225,162);
	    if(guesscount >= 6)
		g.drawLine(175, 75, 125, 62);
	    if(guesscount >= 7)
		g.drawLine(175, 75, 225, 62);
            
	}
    }
private void wordPicker() throws IOException{
    try{
    BufferedReader reader = new BufferedReader(new FileReader("Words.txt"));
    String line = reader.readLine();
    List<String> words = new ArrayList<String>();
    while(line != null) {
        String[] wordsLine = line.split(" ");
        for(String word : wordsLine) {
            words.add(word);
        }
        line = reader.readLine();
    }

    Random rand = new Random(System.currentTimeMillis());
    wordtemp = words.get(rand.nextInt(words.size()));
    
} catch (Exception e) {
    wordtemp = "null";
}

}
private class TextFieldListener implements ActionListener
{
    public void actionPerformed(ActionEvent event)
    {
        if(guesscount == 7)
            {
                word.setText("Guessed word: ");
                Missed.setText("Missed letters:");
                guesscount = 0;
                repaint();
            try {
                wordPicker();
            } 
catch (IOException ex) {
            }

	  wordLength = wordtemp.length();
		 wordarr = wordtemp.toCharArray();
	 for(int z = 0; z < wordLength; z++){
	     word.setText(word.getText()+"*");
	 }
            }

        else{
	boolean isNotInWord = true;
        char temp = field.getText().charAt(0);
        for (int i = 0; i < wordarr.length; i++)
        {
            if(wordarr[i]==(temp))
		{
		char[] tempedit = word.getText().toCharArray();
                tempedit[14+i] = temp;
            	word.setText(new String(tempedit));	            
		isNotInWord = false;
                field.selectAll();
                field.cut();
        	}
        }
        
        if(isNotInWord)
        {
            guesscount++;
            if(guesscount == 7)
            {
                word.setText("The word was: " + wordtemp);
                Missed.setText("To continue the game, Press ENTER");
                field.selectAll();
                field.cut();
                
            }
            else
            {
                Missed.setText(Missed.getText()+" " +temp);
                field.selectAll();
                field.cut();
            }
            repaint();
        }
        }
    }
}    
    
}
