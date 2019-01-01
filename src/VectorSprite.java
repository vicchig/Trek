
import java.awt.Graphics;

public class VectorSprite extends GraphicsObject {

    private double xSpeed, ySpeed;
    private int currentCell, endCell, startCell, changeFrameDelay, radius, distance, hitWidth, hitHeight;
    private boolean loopCells;
    private boolean flying;
    private double hitCenterX, hitCenterY;
    private double maxSpeed;
    private double health;
    
    public VectorSprite(String name, double xPos, double yPos, int width, int height, int radius) {
        super(name, xPos, yPos, width, height);
        this.radius = radius;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Returns the value of maxSpeed.
     * Postcondition:
     *  The value of maxSpeed is returned.
     */
    public double getMaxSpeed() {
        return maxSpeed;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  The value of maxSpeed is set to the value of the passed argument.
     * Postcondition:
     *  The value of maxSpeed is changed.
     */
    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Sets the value of radius to the value of the passed argument.
     * Postcondition:
     *  The value of radius is changed.
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }
    
    
    /**
     * Precondition:
     *  None.
     * Description:
     *  Returns the hitCenterX of the object.
     * Postcondition:
     *  hitCenterX is returned.
     */
    public double getHitCenterX() {
        return hitCenterX;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Returns the value of getHitCenterY.
     * Postcondition:
     *  getHitCenterY is returned.
     */
    public double getHitCenterY() {
        return hitCenterY;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Returns the value of flying.
     * Postcondition:
     *  The value of flying has been returned.
     */
    public boolean isFlying() {
        return flying;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Sets the value of flying to the value of the passed argument.
     * Postcondition:
     *  The value of flying has been cahnged.
     */
    public void setFlying(boolean flying) {
        this.flying = flying;
    }
    
    /**
     * Precondition:
     *  None.
     * Description:
     *  Sets the centerX of teh object to the value of the passed arguemnt.
     * Postcondition:
     *  The value of centerX is changed.
     */
    public void setHitCenterX(double hitCenterX) {
        this.hitCenterX = hitCenterX;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Sets the value of centerY to the value of the passed argument.
     * Postcondition:
     *  The value of centerY is changed.
     */
    public void setHitCenterY(double hitCenterY) {
        this.hitCenterY = hitCenterY;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  The value of radius is returned.
     * Postcondition:
     *  The value of radius has been returned.
     */
    public int getRadius() {
        return radius;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Returns the value of health.
     * Postcondition:
     *  The value of health has been returned.
     */ 
    public double getHealth() {
        return health;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Sets the value of health to the value of the passed argument.
     * Postcondition:
     *  The value of health is changed.
     */
    public void setHealth(double health) {
        this.health = health;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Returns the value of speed in the y axis.
     * Postcondition:
     *  The value of y speed has been returned.
     */
    public double getySpeed() {
        return ySpeed;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Returns the value of xSpeed.
     * Postcondition:
     *  The value of xSpeed has been returned.
     */
    public double getxSpeed() {
        return xSpeed;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  The value of loopCells is set to the value of the passed argument.
     * Postcondition:
     *  The value of loopCells has been changed.
     */
    public void setLoopCells(boolean TorF) {
        this.loopCells = TorF;
    }

        /**
     * Precondition:
     *  None.
     * Description:
     *  The value of xSpeed is set to the value of the passed argument.
     * Postcondition:
     *  The value of xSpeed has been changed.
     */
    public void setxSpeed(double xSpeed) {
        this.xSpeed = xSpeed;
    }

            /**
     * Precondition:
     *  None.
     * Description:
     *  The value of ySpeed is set to the value of the passed argument.
     * Postcondition:
     *  The value of ySpeed has been changed.
     */
    public void setySpeed(double ySpeed) {
        this.ySpeed = ySpeed;
    }

            /**
     * Precondition:
     *  None.
     * Description:
     *  The value of currentCell is set to the value of the passed argument.
     * Postcondition:
     *  The value of currentCell has been changed.
     */
    public void setCurrentCell(int currentCell) {
        this.currentCell = currentCell;
    }

                /**
     * Precondition:
     *  None.
     * Description:
     *  The value of startCell is set to the value of the passed argument.
     * Postcondition:
     *  The value of startCell has been changed.
     */
    public void setStartCell(int startCell) {
        this.startCell = startCell;
    }

                /**
     * Precondition:
     *  None.
     * Description:
     *  The value of endCell is set to the value of the passed argument.
     * Postcondition:
     *  The value of endCell has been changed.
     */
    public void setEndCell(int endCell) {
        this.endCell = endCell;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Returns the value of distance moved by the object.
     * Postcondition:
     *  The value of distance is returned.
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Updates the distance moved by the object.
     * Postcondition:
     *  The distance moved by the object has been returned.
     */
    public void countDistance(int num) {
        this.distance += num;
    }
    
    /**
     * Precondition:
     *  None.
     * Description:
     *  Animates the sprite of the object.
     * Postcondition:
     *  The sprite has been animated.
     */
    public void animate() {
        changeFrameDelay++;
        if (changeFrameDelay >= 10) {
            currentCell++;
            changeFrameDelay = 0;
        }
        if (currentCell >= endCell) { //resets the cell if it has reached the end of the animation
            if (loopCells) {
                currentCell = startCell;//returns the current cell to startcell
            } else {
                currentCell = endCell;//so that it stays at the end cell and does not exceed the width of the sprtie and produce the error
            }
        }
        this.setPicX((int) (this.getWidth() * currentCell));//changes the cell based on width and current cell
    }
    /**
     * Precondition:
     *  None.
     * Description:
     *  Animates the sprite vertically.
     * Postcondition:
     *  The sprite has been animated.
     */
    public void animateY() {
        changeFrameDelay++;
        if (changeFrameDelay >= 10) {
            currentCell++;
            changeFrameDelay = 0;
        }
        if (currentCell >= endCell) { //resets the cell if it has reached the end of the animation
            if (loopCells) {
                currentCell = startCell;//returns the current cell to startcell
            } else {
                currentCell = endCell;//so that it stays at the end cell and does not exceed the width of the sprtie and produce the error
            }
        }
        this.setPicY((int) (this.getHeight() * currentCell));//changes the cell based on width and current cell
    }
    
    /**
     * Precondition:
     *  None.
     * Description:
     *  Checks the collision between the object and another object of the type.
     * Postcondition:
     *  Returns true if the collision is occurring, else returns false.
     */
    public <T extends VectorSprite> boolean collideE(T object1, T object2) {
        double xDistance, yDistance;

        xDistance = object1.getxPos() + object1.getRadius() / 2 - Math.max(object2.getxPos(), Math.min(object1.getxPos() + object1.getRadius() / 2, object2.getxPos() + object2.getWidth()));
        yDistance = object1.getyPos() + object1.getRadius() / 2 - Math.max(object2.getyPos(), Math.min(object1.getyPos() + object1.getRadius() / 2, object2.getyPos() + object2.getHeight()));
        
        
        return (xDistance * xDistance + yDistance * yDistance) < (object1.getRadius() / 2 * object1.getRadius() / 2);
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Returns the value of changeFrameDelay.
     * Postcondition:
     *  The value of changeFrame delay has been returned.
     */
    public int getChangeFrameDelay() {
        return changeFrameDelay;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Sets the value of changeFrameDelay to the value of the passed argument.
     * Postcondition:
     *  The value of changeFrameDelay has been changed.
     */
    public void setChangeFrameDelay(int changeFrameDelay) {
        this.changeFrameDelay = changeFrameDelay;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Moves the object left or right.
     * Postcondition:
     *  The object has been moved.
     */
    public void moveX(double xSpeed) {
        double tempPos = this.getxPos();
        tempPos += xSpeed;
        this.setxPos(tempPos);
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Moves the object up or down.
     * Postcondition:
     *  The object has been moved.
     */
    public void moveY(double ySpeed) {
        double tempPos = this.getyPos();
        tempPos += ySpeed;
        this.setyPos(tempPos);
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Sets the value of hitHeight to the value of the passed argument.
     * Postcondition:
     *  The value of hitHeight has been changed.
     */
    public void setHitHeight(int hitHeight) {
        this.hitHeight = hitHeight;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Sets the value of hitWidth to the value of the passed argument.
     * Postcondition:
     *  The value of hitWidth has been changed.
     */
    public void setHitWidth(int hitWidth) {
        this.hitWidth = hitWidth;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Returns the value of hitHeight.
     * Postcondition:
     *  The value of hitHeight has been returned.
     */
    public int getHitHeight() {
        return hitHeight;
    }

        /**
     * Precondition:
     *  None.
     * Description:
     *  Returns the value of hitWidth.
     * Postcondition:
     *  The value of hitWidth has been returned.
     */
    public int getHitWidth() {
        return hitWidth;
    }


    
}
