package net.wea_ondara.chunkgui;

import javax.swing.JFrame;

public class Main extends JFrame
{
    private ChunkDisplay cd;
    public static void main(String[] args)
    {
        new Main();
    }
    
    public Main()
    {
        init();
        
        //insert code here
        cd.getPoints().add(new Point(250, 200));
        cd.getPoints().add(new Point(450, 200));
        cd.getPoints().add(new Point(350, 370));
        
        //repaint with: cd.repaint();
        cd.repaint();
        
        //show window
        setVisible(true);
    }
     
    private void init()
    {
        cd = new ChunkDisplay();
        
        getContentPane().add(cd);
        
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
