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
    
    public void defineRadius()
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
    public void defineRadiusChunk()
    {
        int x;
        int y;
        int xstart;
        int ystart;
        int length;
        int chunksize = 16;
        boolean isxmultiples = ((center.getX() - radius) % chunksize) == 0;
        boolean iszmultiples = ((center.getZ() - radius) % chunksize) == 0;
        int blocksoverlap = calculateBlocksoverlap(radius);// = (int)((double)radius*Math.sin(Math.acos(((double)radius - 0.75) / ((double)radius))));
        
        //System.out.print(blocksoverlap);
        points = new ArrayList<Point>();
        
        xstart = center.getX() + radius + chunksize - (center.getX() + radius) % chunksize;
        ystart = center.getZ() - chunksize - center.getZ() % chunksize;
        
        x = xstart;
        y = ystart;
        points.add(new Point(x,y));
        while ((x >= center.getX()) && (y <= center.getZ() + radius + chunksize))
        {  
            x -= chunksize;
            length = calculateLength(x - center.getX(), y - center.getZ() + 1);
            if (length <= radius)
            {
                x += chunksize;
                y += chunksize;
            }
            points.add(new Point(x,y));
        }

        while ((x >= center.getX() - radius - chunksize) && (y >= center.getZ() - blocksoverlap / 2))
        {
            y -= chunksize;
            length = calculateLength(x - center.getX() - 1, y - center.getZ());
            if (length <= radius || length <= radius + 1)// || y >= center.getZ() + blocksoverlap / 2)
            {
                y += chunksize;
                x -= chunksize;
            }
            points.add(new Point(x,y));
        }

        while ((x <= center.getX() + blocksoverlap / 2) && (y >= center.getZ() - radius - chunksize))
        {
            x += chunksize;
            length = calculateLength(x - center.getX(), y - center.getZ() - 1);
            if (length <= radius || length <= radius + 1)
            {
                x -= chunksize;
                y -= chunksize;
            }
            points.add(new Point(x,y));
        }

        while ((x <= center.getX() + radius + chunksize) && (y <= center.getZ()) && x != points.get(0).getX() && y != points.get(0).getZ())
        {
            y += chunksize;
            length = calculateLength(x - center.getX() + 1, y - center.getZ());
            if (length <= radius)
            {
                y -= chunksize;
                x += chunksize;
            }
            points.add(new Point(x,y));
        }
    }
    public List<Point> smallerCircuitApproximation()
    {
        List<Point> newpoints = new ArrayList<Point>();
        int[] x = new int[3];
        int[] z = new int[3];
        x[0] = points.get(points.size() - 1).getX();
        z[0] = points.get(points.size() - 1).getZ();
        x[1] = points.get(0).getX();
        z[1] = points.get(0).getZ();
        x[2] = points.get(1).getX();
        z[2] = points.get(1).getZ();
        newpoints.add(calculateNewPoint(x[0], z[0], x[1], z[1], x[2], z[2]));
        for (int i = 1; i < points.size() - 2; i++)
        {
            x[0] = points.get(i).getX();
            z[0] = points.get(i).getZ();
            x[1] = points.get(i + 1).getX();
            z[1] = points.get(i + 1).getZ();
            x[2] = points.get(i + 2).getX();
            z[2] = points.get(i + 2).getZ();
            newpoints.add(calculateNewPoint(x[0], z[0], x[1], z[1], x[2], z[2]));
        }
        x[0] = points.get(points.size() - 2).getX();
        z[0] = points.get(points.size() - 2).getZ();
        x[1] = points.get(points.size() - 1).getX();
        z[1] = points.get(points.size() - 1).getZ();
        x[2] = points.get(0).getX();
        z[2] = points.get(0).getZ();
        newpoints.add(calculateNewPoint(x[0], z[0], x[1], z[1], x[2], z[2]));
        
        return newpoints;
    }
    private Point calculateNewPoint(int x0, int z0, int x1, int z1, int x2, int z2)
    {
        int newx = x1;
        int newz = z1;
        
        if (x0 < x1 && z0 == z1 && x1 < x2 && z1 == z2)
        {
            newz++;
        }
        else if (x0 == x1 && z0 < z1 && x1 == x2 && z1 < z2)
        {
            newx--;
        }
        else if (x0 > x1 && z0 == z1 && x1 > x2 && z1 == z2)
        {
            newz--;
        }
        else if (x0 == x1 && z0 > z1 && x1 == x2 && z1 > z2)
        {
            newx++;
        }
        else if (x0 < x1 && z0 == z1 && x1 == x2 && z1 < z2 ||
                 x0 == x1 && z0 < z1 && x1 < x2 && z1 == z2)
        {
            newx--;
            newz++;
        }
        else if (x0 == x1 && z0 < z1 && x1 > x2 && z1 == z2 ||
                 x0 > x1 && z0 == z1 && x1 == x2 && z1 < z2)
        {
            newx--;
            newz--;
        }
        else if (x0 > x1 && z0 == z1 && x1 == x2 && z1 > z2 ||
                 x0 == x1 && z0 > z1 && x1 > x2 && z1 == z2)
        {
            newx++;
            newz--;
        }
        else if (x0 == x1 && z0 > z1 && x1 < x2 && z1 == z2 ||
                 x0 < x1 && z0 == z1 && x1 == x2 && z1 > z2)
        {
            newx++;
            newz++;
        }
        
        Point newpoint = new Point(newx, newz);
        return newpoint;
    }
    private int calculateLength(int x, int y)
    {
        return (int)Math.sqrt(Math.pow((double)x,2) + Math.pow((double)y,2));
    }
    private double calculateLengthAccurate(double x, double y)
    {
        return Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
    }
    private int calculateBlocksoverlap(int radius)
    {
        double alpha = 0.1;
        double dalpha = 0.001;
        double x;
        double y;
        
        while ((int)((double)radius * Math.cos(alpha)) >= radius - 1)
        {
            alpha += dalpha;
            //System.out.println("inkrement" + alpha + "   " + (int)((double)radius * Math.cos(alpha)));
        }
        dalpha /= 10.0;
        while ((int)((double)radius * Math.cos(alpha)) < radius - 1)
        {
            alpha -= dalpha;
            //System.out.println("dekrement" + alpha + "   " + (int)((double)radius * Math.cos(alpha)));
        }
        dalpha /= 10.0;
        while ((int)((double)radius * Math.cos(alpha)) >= radius - 1)
        {
            alpha += dalpha;
            //System.out.println("inkrement" + alpha + "   " + (int)((double)radius * Math.cos(alpha)));
        }
        dalpha /= 10.0;
        while ((int)((double)radius * Math.cos(alpha)) < radius - 1)
        {
            alpha -= dalpha;
            //System.out.println("dekrement" + alpha + "   " + (int)((double)radius * Math.cos(alpha)));
        }
        
        return (int)((double)radius * Math.sin(alpha));
    }
}