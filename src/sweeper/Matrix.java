package sweeper;

class Matrix {

    private Box[][] matrix;

    public Matrix(Box defaultBox)
    {
        matrix = new Box[Ranges.getSize().getX()][Ranges.getSize().getY()];
        for(Coordinate coordinate : Ranges.getAllCoordinates())
        {
            matrix[coordinate.getX()][coordinate.getY()] = defaultBox;
        }
    }

    public Box getMatrixx(Coordinate coordinate)
    {
        if (Ranges.inRange(coordinate))
        {
            return matrix[coordinate.getX()][coordinate.getY()];
        }
        else return null;

    }

    public void setMatrixx(Coordinate coordinate, Box box)
    {
        if (Ranges.inRange(coordinate))
        {
            matrix[coordinate.getX()][coordinate.getY()] = box;
        }

    }
}
