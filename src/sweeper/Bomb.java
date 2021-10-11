package sweeper;

public class Bomb {
    private Matrix matrixBomb;
    private int totalBomb;

    public Bomb(int totalBomb) {
        this.totalBomb = totalBomb;
        fixBombCount();
    }

    private void placeBomb()
    {
        while (true)
        {
            Coordinate coordinate = Ranges.getRandCoordinate();
            if (Box.BOMB == matrixBomb.getMatrixx(coordinate))
            {
                continue;
            }
            matrixBomb.setMatrixx(coordinate,Box.BOMB);

            //matrixBomb.setMatrixx(new Coordinate(4,4),Box.BOMB);
            incNumberAroundBomb(coordinate);
            break;
        }
    }

    Box get(Coordinate coordinate)
    {
        return matrixBomb.getMatrixx(coordinate);
    }

    void start()
    {
        matrixBomb = new Matrix(Box.ZERO);
        for (int i = 0; i < totalBomb; i++) {
            placeBomb();
        }

    }

    private void fixBombCount()
    {
        int maxBomb = (Ranges.getSize().getX() * Ranges.getSize().getY())/2;
        if (totalBomb > maxBomb)
        {
            totalBomb = maxBomb;
        }
    }

    private void incNumberAroundBomb(Coordinate coordinate)
    {
        for (Coordinate myCoordinate : Ranges.getCoordArround(coordinate))
        {
            if (Box.BOMB != matrixBomb.getMatrixx(myCoordinate))
            {
                matrixBomb.setMatrixx(myCoordinate, matrixBomb.getMatrixx(myCoordinate).nextNumberBox());
            }

        }
    }

    public int getTotalBombs()
    {
        return this.totalBomb;
    }
}
