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
    private final List<Point> points = new ArrayList<>();

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
        g.drawLine(center.getX(), center.getZ(), center.getX(), center.getZ());
        g.setColor(Color.BLUE);
        g.drawOval(center.getX() - radius, center.getZ() - radius, radius * 2, radius * 2);

        g.setColor(Color.RED);
        for (int i = 0; i < points.size(); i++)
        {
            Point p1 = points.get(i % points.size());
            Point p2 = points.get((i + 1) % points.size());
            g.drawLine(p1.getX(), p1.getZ(), p2.getX(), p2.getZ());
        }
    }
}
