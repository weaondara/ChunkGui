package net.wea_ondara.chunkgui;

import java.util.Iterator;
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
        
        //insert code here
        /*cd.getPoints().add(new Point(16*5, 16*4));
        cd.getPoints().add(new Point(16*6, 16*4));
        cd.getPoints().add(new Point(16*6, 16*5));
        cd.getPoints().add(new Point(16*6, 16*6));
        cd.getPoints().add(new Point(16*7, 16*6));*/
        
        int radiusBlock = 8;
        int xBlock = 11;
        int yBlock = 10;
        
        mbc.setRadius(radiusBlock);
        mbc.setCenter(new Point(xBlock, yBlock));
        mbc.defineRadius();
        
        // Middle Approximation
        cd.setRadius(radiusBlock * 16);
        cd.setCenter(new Point(xBlock * 16, yBlock * 16));
        
        for (int i = 0; i < mbc.getPoints().size(); i++)
        {
            cd.getPoints().add(new Point(mbc.getPoints().get(i).getX() * 16, mbc.getPoints().get(i).getZ() * 16));
        }
        
        // 1st Bigger Approximation
        mbc.setRadius(radiusBlock + 1);
        mbc.defineRadius();

        for (int i = 0; i < mbc.getPoints().size(); i++)
        {
            cd.getPointsout().add(new Point(mbc.getPoints().get(i).getX() * 16, mbc.getPoints().get(i).getZ() * 16));
        }
        
        // 1st Smaller Approximation
        mbc.setRadius(radiusBlock - 1);
        mbc.defineRadius();

        for (int i = 0; i < mbc.getPoints().size(); i++)
        {
            cd.getPointsin().add(new Point(mbc.getPoints().get(i).getX() * 16, mbc.getPoints().get(i).getZ() * 16));
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
