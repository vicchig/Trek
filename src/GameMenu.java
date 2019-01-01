import java.applet.Applet;
import java.awt.*;
import java.applet.AudioClip;
import java.io.*;
import java.util.ArrayList;

public class GameMenu {

    private boolean runMenu, changeColorP, changeColorQ, runControls, changeColorC;
    private Color grey1;
    private Color grey2;
    private Polygon playButton, quitButton, startButton;
    private int width, height;
    private boolean story;
    int y;
    double opacity1, opacity2;
    
    public GameMenu(int width, int height) {

        this.width = width;
        this.height = height;
        y = 801;
        opacity1 = 255;
        opacity2 = 0;
        changeColorP = false;
        changeColorQ = false;
        runMenu = true;
        runControls = false;
        changeColorC = false;
        story = false;
        grey1 = new Color(168, 162, 162);
        grey2 = new Color(222, 218, 218);
        playButton = new Polygon();
        quitButton = new Polygon();
        startButton = new Polygon();

        playButton.addPoint(width / 2 - 175, 300);
        playButton.addPoint(width / 2 + 175, 300);
        playButton.addPoint(width / 2 + 200, 350);
        playButton.addPoint(width / 2 - 200, 350);

        quitButton.addPoint(width / 2 - 175, 450);
        quitButton.addPoint(width / 2 + 175, 450);
        quitButton.addPoint(width / 2 + 200, 500);
        quitButton.addPoint(width / 2 - 200, 500);
        
        startButton.addPoint(width/2-60, 50);
        startButton.addPoint(width/2+60, 50);
        startButton.addPoint(width/2+60, 100);
        startButton.addPoint(width/2-60, 100);
        
        
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Checks the collision between menu buttons and the mouse. Changes the colors of the buttons and when clicked loads the button associated menu or action.
     * Postcondition:
     *  Button color has been changed, menu has been updated.
     */
    public void runMenu(int mX, int mY, boolean mousePressed) {
        if (mouseCollision(mX, mY, playButton)) {
            changeColorP = true;
            if (mousePressed) {
                story = true;
                runMenu = false;
                runControls = false;
            }
        } else {
            changeColorP = false;
        }
        if (mouseCollision(mX, mY, quitButton)) {
            changeColorQ = true;
            if (mousePressed) {
                System.exit(0);
            }
        } else {
            changeColorQ = false;
        }
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Draws the pause screen when the game is paused.
     * Postcondition:
     *  The paused screen has been drawn.
     */
    public void drawPauseScreen(Graphics g, Font font) {
        g.setColor(new Color(0, 0, 0, 100));
        g.fillRect(0, 0, width, height);
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString("Paused", width / 2 - 60, height / 2);
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Draws the story of the game and checks the collision with the skip button.
     * Postcondition:
     *  The story text has been displayed and if the skip button is pressed, the game is begun.
     */
    public void drawStoryScreen(Graphics g, Font font, int mX, int mY, boolean mousePressed) {
        y -= 1;
        opacity1 -= 0.5;
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, width, height);
        
        g.setColor(grey1);
        if (mouseCollision(mX, mY, startButton)) {
            g.setColor(grey2);
            if (mousePressed) {
                y = 801;
                opacity1 = 255;
                opacity2 = 0;
                story = false;
            }
        }
        
        g.fillPolygon(startButton);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 30));
        g.drawString("Begin", width/2-40, 85);
        
        if (opacity1 < 1) {
            opacity1 = 255;

        }
        if (y >= 796) {

            g.setColor(Color.BLACK);

        } else {
            g.setColor(new Color(255, 255, 255, (int) opacity1));
        }
        g.setFont(font);
        if (y > 300) {
            g.drawString("The Trek is a series of trials that all upcoming wizards must pass.", 100, y);
        }
        if (y > -200) {
            g.drawString("It consists of three death defying trials that aspiring young wizards", 100, y + 500);
            g.drawString("must complete, delving deeper into a sunken tower.", 100, y + 525);
        }
        if (y > -700) {
            g.drawString("They are required to uphold mental fortitude and not become restrained", 100, y + 1025);
            g.drawString("by their own subconscious.", 100, y + 1050);
        }
        if (y > -1200) {
            g.drawString("Today, we follow of the story of a young pupil who must conquer his", 100, y + 1550);
            g.drawString("Fears and face his deepest Regrets.", 100, y + 1575);
        }

        if (y < -1300) {
            if (opacity2 < 254) {
                opacity2++;
            }
            g.setColor(new Color(255, 255, 255, (int) opacity2));
            g.drawString("This is his story.", 100, 380);
        }
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Draws the main menu.
     * Postcondition:
     *  Main menu has been drawn.
     */
    public void drawMenu(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        g.setColor(grey1);
        g.setFont(new Font("Arial", Font.PLAIN, 60));
        g.drawString("Trek", width / 2 - 50, 100);
        g.fillPolygon(playButton);
        g.fillPolygon(quitButton);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 30));
        g.drawString("Play", width / 2 - 25, 335);
        g.drawString("Quit", width / 2 - 25, 485);

        if (changeColorQ) {
            changeColor(quitButton, g, "Quit", width / 2 - 25, 485, grey2);
        }
        if (changeColorP) {
            changeColor(playButton, g, "Play", width / 2 - 25, 335, grey2);
        }
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Check the collision between a mouse and a given Polygon.
     * Postcondition:
     *  If the collision is true returns true, else returns false.
     */
    private boolean mouseCollision(int x, int y, Polygon shape) {
        Rectangle mouse = new Rectangle(x, y, 1, 1);

        if (shape.contains(mouse)) {
            return true;
        }
        return false;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Changes the color of the passed button.
     * Postcondition:
     *  The color of the passed button has been changed.
     */
    private void changeColor(Polygon shape, Graphics g, String text, int x, int y, Color color) {
        g.setColor(color);
        g.fillPolygon(shape);
        g.setColor(Color.BLACK);
        g.drawString(text, x, y);
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Returns the value of story.
     * Postcondition:
     *  The value of story has been removed.
     */
    public boolean isStory() {
        return story;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  The value of story is set to the value of the passed argument.
     * Postcondition:
     *  The value of story has been changed.
     */
    public void setStory(boolean story) {
        this.story = story;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Returns the value of runMenu.
     * Postcondition:
     *  The value of runMenu has been returned.
     */
    public boolean isRunMenu() {
        return runMenu;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Sets the value of runMenu to the passed argument's value.
     * Postcondition:
     *  The value of runMenu has been changed.
     */
    public void setRunMenu(boolean runMenu) {
        this.runMenu = runMenu;
    }

}