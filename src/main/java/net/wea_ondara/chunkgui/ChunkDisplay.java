package net.wea_ondara.chunkgui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ChunkDisplay extends JPanel
{
    //circle
    @Setter
    private Point center = new Point(200, 200);
    @Setter
    private int radius = 150;

    //chunk points
    private List<Point> points = new ArrayList<>();
    private List<Point> pointsout = new ArrayList<>();
    private List<Point> pointsin = new ArrayList<>();

    public void resetPoints()
    {
        points = new ArrayList<>();
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        g.clearRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.LIGHT_GRAY);
        for (int x = 16; x < getWidth(); x += 16)
        {
            g.drawLine(x, 0, x, getHeight());
        }
        for (int y = 16; y < getHeight(); y += 16)
        {
            g.drawLine(0, y, getWidth(), y);
        }

        g.setColor(Color.BLUE);
        g.fillOval(center.getX() - 2, center.getZ() - 2, 2 * 2, 2 * 2);
        //g.drawLine(center.getX(), center.getZ(), center.getX(), center.getZ());
        g.setColor(Color.BLUE);
        g.drawOval(center.getX() - radius, center.getZ() - radius, radius * 2, radius * 2);

        g.setColor(Color.RED);
        for (int i = 0; i < points.size(); i++)
        {
            Point p1 = points.get(i % points.size());
            Point p2 = points.get((i + 1) % points.size());
            g.drawLine(p1.getX(), p1.getZ(), p2.getX(), p2.getZ());
        }
        g.setColor(Color.GREEN);
        for (int i = 0; i < pointsout.size(); i++)
        {
            Point p1 = pointsout.get(i % pointsout.size());
            Point p2 = pointsout.get((i + 1) % pointsout.size());
            g.drawLine(p1.getX(), p1.getZ(), p2.getX(), p2.getZ());
        }
        g.setColor(Color.ORANGE);
        for (int i = 0; i < pointsin.size(); i++)
        {
            Point p1 = pointsin.get(i % pointsin.size());
            Point p2 = pointsin.get((i + 1) % pointsin.size());
            g.drawLine(p1.getX(), p1.getZ(), p2.getX(), p2.getZ());
        }
    }
}
