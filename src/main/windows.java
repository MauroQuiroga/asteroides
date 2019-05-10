package main;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mauro
 */
public class windows extends JFrame implements Runnable{
    public static final int WIDTH = 1000,HEIGHT = 800;
    private Canvas canvas;
    private Thread thread;
    private boolean running = false;
    
    private BufferStrategy bs;
    private Graphics g;
    
    
 public windows()
 {
  setTitle("JavaApplication32");
  setSize(WIDTH,HEIGHT);
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  setResizable(false);
  setLocationRelativeTo(null);
  setVisible(true);
   canvas = new Canvas ();
   canvas.setPreferredSize(new Dimension (WIDTH,HEIGHT));
   canvas.setMaximumSize(new Dimension (WIDTH,HEIGHT));
   canvas.setMinimumSize(new Dimension (WIDTH,HEIGHT));
   canvas.setFocusable(true);
   add(canvas);
   
 }
    public static void main (String [] args){
       new windows().start();       
}
private void update(){
    
}
private void draw(){
    bs = canvas.getBufferStrategy();
    if (bs==null) {
        canvas.createBufferStrategy(3);
        return;
    }
    g = bs.getDrawGraphics();
    g.drawRect(0,0,100,90);
    g.dispose();
    bs.show();
}        
    @Override
    public void run() {
        while (running) {            
            update();
            draw();
        }
       stop();
    }
    private void start(){
        thread = new Thread(this); 
        thread.start();
        running = true;
    }
    private void stop(){
        try {
            thread.join();
            running = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
