package net.wea_ondara.chunkgui;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
public class MinecraftBlockCircular
{
    @Setter
    private int radius = 7;
    @Setter
    private Point center = new Point(15, 15);
    private List<Point> points = new ArrayList<Point>();
    
    void defineRadius()
    {
        int x;
        int y;
        int length;
        points = new ArrayList<Point>();
        
        x = center.getX() + radius;
        y = center.getZ();
        points.add(new Point(x,y));
        while ((x >= center.getX()) && (y <= center.getZ() + radius))
        {  
            x--;
            length = calculateLength(x - center.getX(), y - center.getZ() + 1);
            if (length <= radius)
            {
                x++;
                y++;
            }
            points.add(new Point(x,y));
        }
        
        x = center.getX();
        y = center.getZ() + radius;
        points.add(new Point(x,y));
        while ((x >= center.getX() - radius) && (y >= center.getZ()))
        {
            y--;
            length = calculateLength(x - center.getX() - 1, y - center.getZ());
            if (length <= radius)
            {
                y++;
                x--;
            }
            points.add(new Point(x,y));
        }
        
        x = center.getX() - radius;
        y = center.getZ();
        points.add(new Point(x,y));
        while ((x <= center.getX()) && (y >= center.getZ() - radius))
        {
            x++;
            length = calculateLength(x - center.getX(), y - center.getZ() - 1);
            if (length <= radius)
            {
                x--;
                y--;
            }
            points.add(new Point(x,y));
        }
        
        x = center.getX();
        y = center.getZ() - radius;
        points.add(new Point(x,y));
        while ((x <= center.getX() + radius) && (y <= center.getZ()))
        {
            y++;
            length = calculateLength(x - center.getX() + 1, y - center.getZ());
            if (length <= radius)
            {
                y--;
                x++;
            }
            points.add(new Point(x,y));
        }
    }
    int calculateLength(int x, int y)
    {
        return (int)Math.sqrt(Math.pow((double)x,2) + Math.pow((double)y,2));
    }
}