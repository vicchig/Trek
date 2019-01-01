
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

 class GraphicsObject {

    //STATES WITH GETS AND SETS
    private double xPos, yPos;
    private String id;
    private boolean isAbove, paused;
    private int picX, picY, height, width;
    
    
    //STATES WITHOUT GETS AND SETS
    private BufferedImage objectImg, spriteSheet;
    private BufferedImageLoader loader;
    private SpriteSheet ss;

    public GraphicsObject(String name, double xPos, double yPos, int w, int h) {
        id = name;
        this.xPos = xPos;
        this.yPos = yPos;
        width = w;
        height = h;
        loadImage(id);
    }
    /**
     * Precondition:
     *  None.
     * Description:
     *  Draws the passed sprite.
     * Postcondition:
     *  A sprite has been drawn.
     */
    public void draw(Graphics g) {
        //the picX and picY are the location of the top left corner of the sprite to cut out, the w and h are dimensions, this is for the sprite, not in game
        objectImg = ss.grabSprite(picX, picY, width, height);
        //these xPos and yPos are the locaiton of the player in game
        g.drawImage(objectImg, (int)xPos, (int)yPos, null);
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Loads an image from a given file.
     * Postcondition:
     *  An image has been loaded from a file.
     */
    private void loadImage(String str){
        loader = new BufferedImageLoader();

        objectImg = null;
        try {
            spriteSheet = loader.loadImage(str);
        }catch (IOException e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }

        ss = new SpriteSheet(spriteSheet);
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Returns the value of height.
     * Postcondition:
     *  The value of height has been returned.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Returns the reference to id.
     * Postcondition:
     *  The reference to id has been returned.
     */
    public String getId() {
        return id;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Returns the x position of the image on the sprite sheet..
     * Postcondition:
     *  The x position on the sprite is returned.
     */
    public int getPicX() {
        return picX;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Returns the y position of the image on the sprite sheet.
     * Postcondition:
     *  The y position has been returned.
     */
    public int getPicY() {
        return picY;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Returns the value of width.
     * Postcondition:
     *  The value of width is returned.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Returns the x position of the object.
     * Postcondition:
     *  The x position has been returned.
     */
    public double getxPos() {
        return xPos;
    }
    
    /**
     * Precondition:
     *  None.
     * Description:
     *  Returns the y position of the object.
     * Postcondition:
     *  The y position has been returned.
     */
    public double getyPos() {
        return yPos;
    }

    /**
     * Precondition:
     *  @param height > 0
     * Description:
     *  Sets the value of height to the passed value.
     * Postcondition:
     *  The value of height has been changed.
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Precondition:
     *  @param picX is inside the sprite sheet.
     * Description:
     *  Sets the x position of the object on the sprite sheet.
     * Postcondition:
     *  The x position of the image has been set.
     */
    public void setPicX(int picX) {
        this.picX = picX;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Sets the reference that id points to.
     * Postcondition:
     *  The reference of id has been changed.
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * Precondition:
     *  @param picY is inside the sprite sheet.
     * Description:
     *  Sets the value of the y position of the image on the sprite sheet.
     * Postcondition:
     *  The y position of the image has been set.
     */
    public void setPicY(int picY) {
        this.picY = picY;
    }

    /**
     * Precondition:
     *  @param width > 0
     * Description:
     *  Sets the value of width to the passed value.
     * Postcondition:
     *  The value of width has been changed.
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Sets the x position of the object.
     * Postcondition:
     *  The x position has been changed.
     */
    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Sets the y position of the object.
     * Postcondition:
     *  The y position of the object has been changed.
     */
    public void setyPos(double yPos) {
        this.yPos = yPos;
    }
    
    /**
     * Precondition:
     *  None.
     * Description:
     *  Moves the object in the x axis.
     * Postcondition:
     *  The object has been moved left or right.
     */
    public void moveX(double xSpeed){
        this.xPos += xSpeed;
    }
    
    /**
     * Precondition:
     *  None.
     * Description:
     *  Moves the object in the y axis.
     * Postcondition:
     *  The object has been moved up or down.
     */
    public void moveY(double ySpeed){
        this.yPos += ySpeed;
    }
    
    /**
     * Precondition:
     *  None.
     * Description:
     *  Sets pause to true.
     * Postcondition:
     *  pause has been set to true.
     */
    public void pause() {
        this.paused = true;
    }
    
    


    
}
