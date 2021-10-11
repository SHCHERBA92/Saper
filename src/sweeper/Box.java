package sweeper;

public enum Box {
    ZERO,
    NUM1,
    NUM2,
    NUM3,
    NUM4,
    NUM5,
    NUM6,
    NUM7,
    NUM8,
    BOMB,
    OPENED,
    CLOSED,
    FLAGED,
    BOMBED,
    NOBOMB;

    public Object image;

    public Box nextNumberBox()
    {
        Box boxPlusOne= Box.values()[ordinal()+1];
       // System.out.println(ordinal()+1);
        return boxPlusOne;
    }

    int getNumber()
    {
         return this.ordinal();
    }
}
