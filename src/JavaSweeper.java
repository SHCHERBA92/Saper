import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import sweeper.*;
import sweeper.Box;

public class JavaSweeper extends JFrame {

    private Game game;

    JPanel myPanel;
    JLabel label;
    private final int COLS = 10;
    private final int ROWS = 10;
    private final int BOMBS = 10;
    private final int IMAGE_SIZE = 50;


    public JavaSweeper() throws HeadlessException {
        game = new Game(COLS, ROWS, BOMBS);
//        Ranges.setSize(new Coordinate(COLS, ROWS));
        game.start();
        this.setImage();
        this.initLAbel();
        this.initPanel();
        this.initFrame();
    }

    void initLAbel()
    {
        label = new JLabel("Hello");
        this.add(label, BorderLayout.SOUTH);
    }

    public void initFrame()
    {
        this.pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("JavaSweeper");
        this.setLocationRelativeTo(null);   // находился по центру
        this.setResizable(false);   // нельзя менять оазмер
        this.setVisible(true);
        this.setIconImage(getImage("icon"));
    }


    // работа с панелью
    public void initPanel()
    {
        myPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                for (Coordinate coordinate : Ranges.getAllCoordinates())
                {
                    g.drawImage((Image) game.getBox(coordinate).image, coordinate.getX() * IMAGE_SIZE, coordinate.getY() * IMAGE_SIZE,this);
                }

            }
        };



        myPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / IMAGE_SIZE;
                int y = e.getY() / IMAGE_SIZE;
                Coordinate coordinateFromMouse = new Coordinate(x,y);
                if (e.getButton() == MouseEvent.BUTTON1)
                {
                    game.pressLeftButt(coordinateFromMouse);
                }
                if (e.getButton() == MouseEvent.BUTTON3)
                {
                    game.pressRigthButt(coordinateFromMouse);
                }
                if(e.getButton() == MouseEvent.BUTTON2)
                {
                    game.start();
                }
                label.setText(getMessage());
                myPanel.repaint();
            }
        });
        myPanel.setPreferredSize(new Dimension(IMAGE_SIZE * Ranges.getSize().getX(),IMAGE_SIZE * Ranges.getSize().getY()));
        this.add(myPanel);
    }

    private Image getImage(String name)
    {
        String fileName = "img/" + name.toLowerCase() + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(fileName));
        return icon.getImage();
    }

    private void setImage()
    {
        for (Box b : Box.values()){
            b.image = getImage(b.name());
        }
    }

    private String getMessage()
    {
        switch (game.getGameState())
        {
            case PLAYED : {
                return "Игра продолжается";
            }
            case BOMBED : {
                return  "Проиграли";
            }
            case WINNER:{
                return "ВЫ победили!";
            }
            default:{
                return "welcom";
            }
        }
    }


}
