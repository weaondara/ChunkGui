package net.wea_ondara.chunkgui;

import java.util.Iterator;
import java.util.List;
import javax.swing.JFrame;

public class Main extends JFrame
{
    private ChunkDisplay cd;
    private MinecraftBlockCircular mbc;
    public static void main(String[] args)
    {
        new Main();
    }
    
    public Main()
    {
        init();
        
        int radius = 153;
        int x = 189;
        int z = 195;
        
        mbc.setRadius(radius);
        mbc.setCenter(new Point(x, z));
        mbc.defineRadiusChunk();
        
        cd.setRadius(radius);
        cd.setCenter(new Point(x, z));
        
        for (int i = 0; i < mbc.getPoints().size(); i++)
        {
            cd.getPoints().add(mbc.getPoints().get(i));
        }
        
        List<Point> newpoints = mbc.smallerCircuitApproximation();
        for (int i = 0; i < newpoints.size(); i++)
        {
            cd.getPointsin().add(newpoints.get(i));
        }
        
        //repaint with: cd.repaint();
        cd.repaint();
        
        //show window
        setVisible(true);
    }
     
    private void init()
    {
        cd = new ChunkDisplay();
        mbc = new MinecraftBlockCircular();
        
        getContentPane().add(cd);
        
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
