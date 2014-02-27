package GUI;



import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.*;
import javax.swing.*;
import Model.Model.BoardClasses.*;
import Controller.Controller;
import Model.Model.PieceClasses.*;


public class Window {

    private JPanel glass;
    private JPanel finalGlass;

    private JFrame frame;
    private JButton undoButton;
    private int counter;

    private BufferedImage blackPawn;
    private BufferedImage whitePawn;
    private BufferedImage blackRook;
    private BufferedImage whiteRook;
    private BufferedImage blackBishop;
    private BufferedImage whiteBishop;
    private BufferedImage blackKnight;
    private BufferedImage whiteKnight;
    private BufferedImage blackQueen;
    private BufferedImage whiteQueen;
    private BufferedImage blackKing;
    private BufferedImage whiteKing;
    private BufferedImage whiteChancellor;
    private BufferedImage blackChancellor;
    private BufferedImage whiteArchbishop;
    private BufferedImage blackArchbishop;


    /**
     * A Constructor function that creates the starting Window for the board
     *
     */
    public Window()
    {
        //Creates a new frame and sets the dimensions
        frame = new JFrame("Chess Board");

        frame.setSize(800,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Specifies that we want a Grid
        Container pane = frame.getContentPane();
        pane.setLayout(new GridLayout(8,8));


        //load all the icons into BufferedImages
        getImages();

        makeMenuBar(frame,0,0);

        makePauseScreen();




        //Sets colors and then creates 64 JPanels with interlocking colors
        Color black = new Color(131,58,0);
        Color color = black;
        for(int i=0; i<8; i++) //i=y
        {
            color = switchColors(color);
            for(int j=0; j<8; j++) //j=x
            {
                SquareButton aSquare = new SquareButton(j,i) ;
                aSquare.setBackground(color);
                aSquare.setBorderPainted(false);
                aSquare.setOpaque(true);
                color = switchColors(color);

                Controller.setSquareButton(j,i, aSquare);


                pane.add(aSquare);
            }
        }
        frame.setVisible(true);
    }

    /**
     * A function that will reset the board back to the original layout
     * @param player1 The score of player 1
     * @param player2 The score of player 2
     */
    public void resetBoard(int player1, int player2)
    {
        makeMenuBar(frame,player1,player2);
        Color black = new Color(131,58,0);
        Color color = black;
        for(int i=0; i<8; i++)
        {
            color = switchColors(color);
            for(int j=0; j<8; j++)
            {
                SquareButton aSquare = Controller.getSquareButton(j, i);
                aSquare.setBackground(color);
                aSquare.setBorderPainted(false);
                aSquare.setOpaque(true);
                color = switchColors(color);

            }
        }
    }

    /**
     * A function that interlocks the colors of the board(creates grid theme)
     * @param color the current color of the tile
     * @return the opposite color
     */
    public Color switchColors(Color color)
    {
        Color black = new Color(131,58,0);
        Color white = new Color(255,200,155);
        if(color.equals(black))
            return white;
        else if(color.equals(white))
            return black;
        else return null;
    }

    /**
     * A Function that creates and initializes the pause frame of the game
     */
    private void makePauseScreen() {
        frame.setGlassPane(new JPanel());
        glass = (JPanel) frame.getGlassPane();
        glass.setOpaque(false);
        glass.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JButton glassButton = new JButton(new AbstractAction("UnPause") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Controller.setPause();

            }
        });
        c.ipady = 100;
        c.ipadx = 100;
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 2;
        c.gridy = 1;
        glass.add(glassButton, c);
    }

    /**
     * A function that places the pieces on the board for a classic game of chess
     * @param theBoard the board that is associated with the GUI
     */
    public void updateGame(Board theBoard)
    {
        for(int i=0; i<8; i++) //i=y
        {
            for(int j=0; j<8; j++) //j=x
            {
                BufferedImage correctImage = getPieceForImage(j, i, theBoard);
                placeImage(correctImage, Controller.getSquareButton(j, i));

            }
        }
    }

    /**
     * A function that defines the menu bar that holds all the options for creating a new game
     * or forfeiting or undoing as well as the current score
     */
    private void makeMenuBar(final JFrame frame, int player1, int player2)
    {
        //create a menu bar and a menu
        JMenuBar gameMenuBar = new JMenuBar();
        JMenu chessMenu = new JMenu("File");
        gameMenuBar.add(chessMenu);

        counter=0;
        //create new menu items
        JMenuItem restartMenuItem = new JMenuItem(new AbstractAction("Restart Game") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                counter++;
                Controller.nextTurn();
                if(counter>=2)
                {
                    Controller.getBoard().clearArray();
                    Controller.makeNewGame();
                }
            }
        });
        chessMenu.add(restartMenuItem);

        JMenuItem fairyMenuItem = new JMenuItem(new AbstractAction("New Fairy Game") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Controller.setIsFairy(true);
                Controller.getBoard().clearArray();
                Controller.makeNewGame();
            }
        });
        chessMenu.add(fairyMenuItem);

        JMenuItem newGameMenuItem = new JMenuItem(new AbstractAction("New Classic Game") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Controller.setIsFairy(false);
                Controller.getBoard().clearArray();
                Controller.makeNewGame();
            }
        });
        chessMenu.add(newGameMenuItem);

        JMenuItem forfeitMenuItem = new JMenuItem(new AbstractAction("ForfeitGame") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int turn = Controller.getTurn();
                if(turn==0)
                    Controller.forfeitGame("white");
                else Controller.forfeitGame("black");
            }
        });
        chessMenu.add(forfeitMenuItem);



        JMenuItem pauseButton = new JMenuItem(new AbstractAction("Pause") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Controller.setPause();

            }
        });
        gameMenuBar.add(pauseButton);


        undoButton = new JButton(new AbstractAction("Undo") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
               Controller.returnToSavedState();


            }
        });
        undoButton.setEnabled(false);
        gameMenuBar.add(undoButton);


        String playerScore = "Score|  Player1: " + player1 + " Player2: "+ player2 + "  ";
        JLabel Score1 = new JLabel(playerScore);
        Score1.setHorizontalAlignment(0);
        gameMenuBar.add(Score1);

        //add menu to frame
        frame.setJMenuBar(gameMenuBar);



    }
    /**
     * A function that loads the piece images into a specific square
     * @param x the x-coordinate of the square
     * @param y the y-coordinate of the square
     * @param theBoard the board that is associated with the GUI
     * @return the buffered image ready to be placed in the GUI
     */
    private BufferedImage getPieceForImage(int x, int y, Board theBoard)
    {
        if(theBoard.getPieceOnSquare(x,y) instanceof Pawn)
        {
            if(theBoard.getChessBoardSquareGetTeamOnSquare(x,y).equals("white"))
                return whitePawn;
            if(theBoard.getChessBoardSquareGetTeamOnSquare(x,y).equals("black"))
                return blackPawn;
        }
        else if(theBoard.getPieceOnSquare(x,y) instanceof Rook)
        {
            if(theBoard.getChessBoardSquareGetTeamOnSquare(x,y).equals("white"))
                return whiteRook;
            if(theBoard.getChessBoardSquareGetTeamOnSquare(x,y).equals("black"))
                return blackRook;
        }
        else if(theBoard.getPieceOnSquare(x,y) instanceof Bishop)
        {
            if(theBoard.getChessBoardSquareGetTeamOnSquare(x,y).equals("white"))
                return whiteBishop;
            if(theBoard.getChessBoardSquareGetTeamOnSquare(x,y).equals("black"))
                return blackBishop;
        }
        else if(theBoard.getPieceOnSquare(x,y) instanceof Knight)
        {
            if(theBoard.getChessBoardSquareGetTeamOnSquare(x,y).equals("white"))
                return whiteKnight;
            if(theBoard.getChessBoardSquareGetTeamOnSquare(x,y).equals("black"))
                return blackKnight;
        }
        else if(theBoard.getPieceOnSquare(x,y) instanceof King)
        {
            if(theBoard.getChessBoardSquareGetTeamOnSquare(x,y).equals("white"))
                return whiteKing;
            if(theBoard.getChessBoardSquareGetTeamOnSquare(x,y).equals("black"))
                return blackKing;
        }
        else if(theBoard.getPieceOnSquare(x,y) instanceof Queen)
        {
            if(theBoard.getChessBoardSquareGetTeamOnSquare(x,y).equals("white"))
                return whiteQueen;
            if(theBoard.getChessBoardSquareGetTeamOnSquare(x,y).equals("black"))
                return blackQueen;
        }
        else if(theBoard.getPieceOnSquare(x,y) instanceof Chancellor)
        {
            if(theBoard.getChessBoardSquareGetTeamOnSquare(x,y).equals("white"))
                return whiteChancellor;
            if(theBoard.getChessBoardSquareGetTeamOnSquare(x,y).equals("black"))
                return blackChancellor;
        }
        else if(theBoard.getPieceOnSquare(x,y) instanceof Wareagle)
        {
            if(theBoard.getChessBoardSquareGetTeamOnSquare(x,y).equals("white"))
                return whiteArchbishop;
            if(theBoard.getChessBoardSquareGetTeamOnSquare(x,y).equals("black"))
                return blackArchbishop;
        }
        return null;

    }


    /**
     * A function that places the correct image into the GUI
     * @param image the buffered image of the piece
     * @param aSquare the SquareButton called square that the piece needs to be placed on
     */
    private void placeImage(BufferedImage image, SquareButton aSquare)
    {
        if(image == null)
            aSquare.setIcon(null);
        else
            aSquare.setIcon(new ImageIcon(image));
    }

    /**
     * A function that loads all the images from the memory
     */
    private void getImages()
    {

        try {
            blackPawn = ImageIO.read(new File("ChessPieceImages/trunk/blackPawn.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            whitePawn = ImageIO.read(new File("ChessPieceImages/trunk/whitePawn.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            blackKnight = ImageIO.read(new File("ChessPieceImages/trunk/blackKnight.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            whiteKnight = ImageIO.read(new File("ChessPieceImages/trunk/whiteKnight.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            blackRook = ImageIO.read(new File("ChessPieceImages/trunk/blackRook.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            whiteRook = ImageIO.read(new File("ChessPieceImages/trunk/whiteRook.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            blackBishop = ImageIO.read(new File("ChessPieceImages/trunk/blackBishop.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            whiteBishop = ImageIO.read(new File("ChessPieceImages/trunk/whiteBishop.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            blackQueen = ImageIO.read(new File("ChessPieceImages/trunk/blackQueen.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            whiteQueen = ImageIO.read(new File("ChessPieceImages/trunk/whiteQueen.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            blackKing = ImageIO.read(new File("ChessPieceImages/trunk/blackKing.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            whiteKing = ImageIO.read(new File("ChessPieceImages/trunk/whiteKing.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            whiteArchbishop = ImageIO.read(new File("ChessPieceImages/trunk/whiteArchbishop.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            blackArchbishop = ImageIO.read(new File("ChessPieceImages/trunk/blackArchbishop.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            whiteChancellor = ImageIO.read(new File("ChessPieceImages/trunk/whiteChancellor.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            blackChancellor = ImageIO.read(new File("ChessPieceImages/trunk/blackChancellor.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * A funtion that returns the frame in the window
     * @return the frame needed
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * A function that returns the glass panel that is invisible in front of the frame
     * @param finished A boolean that decides whether to get the pause screen or the endgame screen
     * @return the glass panel
     */
    public JPanel getGlass(boolean finished)
    {
        if(!finished)
            return glass;
        else return finalGlass;
    }

    /**
     * A getter function that gets the undo button so that it can be set in the Controller
     * @return the undoButton
     */
    public JButton getUndo()
    {
        return undoButton;
    }

    /**
     * Creates the end game screen
     * @param team the team that lost the game
     */
    public void makeEndGameScreen(final String team)
    {
        int player = -1;
        if(team.equals("white"))
            player = 2;
        if(team.equals("black"))
            player = 1;
        frame.setGlassPane(new JPanel());
        finalGlass = (JPanel) frame.getGlassPane();
        finalGlass.setOpaque(false);
        finalGlass.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        JButton finalGlassButton = new JButton(new AbstractAction("Player "+player+" wins!!") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                for(int i=0; i<8; i++)
                    for(int j=0; j<8; j++)
                        Controller.getSquareButton(i, j).setEnabled(true);
                finalGlass.setVisible(false);
                Controller.forfeitGame(team);
            }
        });
        c.ipady = 100;
        c.ipadx = 100;
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 2;
        c.gridy = 1;
        finalGlass.add(finalGlassButton, c);
    }

}
