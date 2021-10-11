package sweeper;

import java.util.ArrayList;
import java.util.Random;

public class Ranges
{
    private static Coordinate size;
    private static ArrayList<Coordinate> allCoordinates;
    private static Random random = new Random();

    public static void setSize(Coordinate _size)
    {
        size = _size;
        allCoordinates = new ArrayList<>();
        for (int y = 0; y < size.getY(); y++)
        {
            for (int x = 0; x < size.getX(); x++)
            {
                allCoordinates.add(new Coordinate(x,y));
            }
        }
    }

    public static Coordinate getSize() {
        return size;
    }

    public static ArrayList<Coordinate> getAllCoordinates()
    {
        return allCoordinates;
    }

    public static boolean inRange(Coordinate coordinate)
    {
        return coordinate.getX() >= 0 && coordinate.getX() < size.getX()
                            &&
                coordinate.getY() >= 0 && coordinate.getY() < size.getY();
    }

    public static Coordinate getRandCoordinate()
    {
        return new Coordinate(random.nextInt(size.getX()), random.nextInt(size.getY()));
    }

    public static ArrayList<Coordinate> getCoordArround(Coordinate coordinate)
    {
        Coordinate around;
        ArrayList<Coordinate> coordinatesArray = new ArrayList<>();
        for (int x = coordinate.getX()-1; x <= coordinate.getX() + 1; x++)
        {
            for (int y = coordinate.getY() - 1; y <= coordinate.getY()+1;y++)
            {
                if (inRange(around = new Coordinate(x,y)))
                {
                    if (!around.equals(coordinate))
                    {
                        coordinatesArray.add(around);
                    }
                }
            }
        }
        return coordinatesArray;
    }
}
