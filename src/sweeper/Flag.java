package sweeper;

class Flag {
    private Matrix flagMap;
    private int countOfClosedBoxex;

    void start()
    {
        flagMap = new Matrix(Box.CLOSED);
        countOfClosedBoxex = Ranges.getSize().getX() * Ranges.getSize().getY();
//        for (Coordinate around : Ranges.getCoordArround(new Coordinate(4,4)))
//        {
//            flagMap.setMatrixx(around, Box.OPENED);
//        }
        //flagMap.setMatrixx(new Coordinate(4,4),Box.OPENED);
    }

    Box get(Coordinate coordinate)
    {
        return flagMap.getMatrixx(coordinate);
    }

    void setOpenedToBox(Coordinate coordinate)
    {
        flagMap.setMatrixx(coordinate, Box.OPENED);
        countOfClosedBoxex--;
    }

    public int getClossedOfBoxex()
    {
        return countOfClosedBoxex;
    }

    void setFlagedToBox(Coordinate coordinate)
    {
        flagMap.setMatrixx(coordinate, Box.FLAGED);
    }

    void setCloseToBox(Coordinate coordinate)
    {
        flagMap.setMatrixx(coordinate, Box.CLOSED);
    }

    void toggleFlafedToBox(Coordinate coordinate)
    {

        switch (flagMap.getMatrixx(coordinate))
        {
            case FLAGED: setCloseToBox(coordinate); break;
            case CLOSED: setFlagedToBox(coordinate); break;
        }
    }

    void setBombedToBox(Coordinate coordinate)
    {
        flagMap.setMatrixx(coordinate, Box.BOMBED);
    }

    void setOpenedToCLosesBobex(Coordinate c)
    {
        if (flagMap.getMatrixx(c) == Box.CLOSED)
        {
            flagMap.setMatrixx(c, Box.OPENED);
        }
    }


    void noSetOpenedToCLosesBobex(Coordinate c)
    {
        if (flagMap.getMatrixx(c) == Box.FLAGED)
        {
            flagMap.setMatrixx(c, Box.NOBOMB);
        }
    }

    public int getCountFlagAboutNumber(Coordinate coordinate) {
        int count = 0;
        for (Coordinate arr : Ranges.getCoordArround(coordinate))
        {
            if (flagMap.getMatrixx(arr)==Box.FLAGED)
            {
                count++;
            }
        }
        return count;
    }
}
