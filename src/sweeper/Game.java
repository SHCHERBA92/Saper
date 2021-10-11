package sweeper;


public class Game
{
    Bomb bomb;
    Flag flag;
    GameState gameState;


    public GameState getGameState() {
        return gameState;
    }

    public Game(int cols, int rows, int bombs) {
        Ranges.setSize(new Coordinate(cols,rows));
        bomb = new Bomb(bombs);
        flag = new Flag();
    }

    public void start()
    {
//        matrixBombMap = new Matrix(Box.ZERO);
//        matrixBombMap.setMatrixx(new Coordinate(0,0),Box.BOMB);
//        matrixBombMap.setMatrixx(new Coordinate(1,1),Box.NUM1);
//        matrixBombMap.setMatrixx(new Coordinate(1,0),Box.NUM2);
        gameState = GameState.PLAYED;
        bomb.start();
        flag.start();

    }

    public Box getBox(Coordinate coordinate)
    {
        //return (Box.values()[(coordinate.getY() + coordinate.getX()) % Box.values().length]) ;
        //return bomb.get(coordinate);

        if (flag.get(coordinate) == Box.OPENED)
        {
            return bomb.get(coordinate);
        }
        else
        {
            return flag.get(coordinate);
        }
    }


    public void pressLeftButt(Coordinate coordinate)
    {
        if (gameOver())
        {
            return;
        }
        openBox(coordinate);
        //flag.setOpenedToBox(coordinate);
        checkWinner();
    }

    public void openBoxAround(Coordinate coordinate)
    {
        flag.setOpenedToBox(coordinate);
        for (Coordinate around: Ranges.getCoordArround(coordinate))
        {
            openBox(around);
        }
    }

    private void openBox(Coordinate coordinate)
    {
        switch (flag.get(coordinate))
        {
            case OPENED: setOpenToBoxAroundNumber(coordinate);return;
            case FLAGED: return;
            case CLOSED:
            {
                switch (bomb.get(coordinate))
                {
                    case ZERO : openBoxAround(coordinate); return;
                    case BOMB: openBombs(coordinate); return;
                    default: flag.setOpenedToBox(coordinate); return;
                }
            }
        }
    }

    public void pressRigthButt(Coordinate coordinate)
    {

        if (gameOver())
        {
            return;
        }
        flag.toggleFlafedToBox(coordinate);
    }

    private void checkWinner()
    {
        if (gameState == GameState.PLAYED)
        {
            if (flag.getClossedOfBoxex() == bomb.getTotalBombs())
            {
                gameState = GameState.WINNER;
            }
        }
    }

    private void openBombs(Coordinate coordinate)
    {
        gameState = GameState.BOMBED;

        flag.setBombedToBox(coordinate);

        for (Coordinate areaBomb : Ranges.getAllCoordinates())
        {
            if (bomb.get(areaBomb) == Box.BOMB)
            {
                flag.setOpenedToCLosesBobex(areaBomb);
            }
            else
            {
                flag.noSetOpenedToCLosesBobex(areaBomb);
            }
        }
    }


        boolean gameOver()
        {
            if (gameState == GameState.PLAYED)
            {
                return false;
            }
            else {
                this.start();
                return true;
            }
        }


    void setOpenToBoxAroundNumber(Coordinate coordinate)
    {
        if (bomb.get(coordinate) != Box.BOMB)
        {
            if (flag.getCountFlagAboutNumber(coordinate) == bomb.get(coordinate).getNumber())
            {
                for (Coordinate arie : Ranges.getCoordArround(coordinate))
                {
                    if (flag.get(arie)==Box.CLOSED)
                    {
                        openBox(arie);
                    }
                }
            }
        }

    }
}
