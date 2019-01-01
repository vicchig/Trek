
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public abstract class Enemy extends Character implements RandCalculator {

    private double distanceMoved, totalDistanceP, xDistanceP, yDistanceP, originalxPos, originalyPos;
    private Player player;
    private Rectangle topRight,bottomRight,topLeft,bottomLeft;
    
    public Enemy(String name, double xPos, double yPos, int width, int height, int radius, Player player) {
        super(name, xPos, yPos, width, height, radius);
        this.setxSpeed(1);
        this.setySpeed(1);
        this.setPlayer(player);
        distanceMoved = 0;
        originalxPos = xPos;
        originalyPos = yPos;
        
        topRight = new Rectangle((int)this.getxPos()+(int)this.getWidth(),(int)this.getyPos(),1,1);
        bottomRight = new Rectangle((int)this.getxPos()+(int)this.getWidth(),(int)this.getyPos()+this.getHitHeight(),1,1);
        topLeft = new Rectangle((int)this.getxPos(),(int)this.getyPos(),1,1);
        bottomLeft = new Rectangle((int)this.getxPos(),(int)this.getyPos()+this.getHitHeight(),1,1);
    }
    /**
     * Precondition:
     *  None.
     * Description:
     *  Checks the collision between the object and an obstacle and calls the appropriate collideAction method depending on which side of the object collision
     *  is occurring.
     * Postcondition:
     *  The collision with the obstacle has been checked and the appropriate action method has been called.
     */
    public void collideWObstacle(Obstacle obj){

        //left
        if(topRight.intersects(obj.getHitBoxLeft()) || bottomRight.intersects(obj.getHitBoxLeft()) && this.getxSpeed()>=0){
            obj.collideActionLeftSide(this);
        }
        //right
        if(topLeft.intersects(obj.getHitBoxRight()) || bottomLeft.intersects(obj.getHitBoxRight()) && this.getxSpeed() <= 0){
            obj.collideActionRightSide(this);
        }
        //top
        if(bottomRight.intersects(obj.getHitBoxTop()) || bottomLeft.intersects(obj.getHitBoxTop()) && this.getySpeed()>=0){
            obj.collideActionTopSide(this);
        }
        //bottom
        if(topRight.intersects(obj.getHitBoxBottom()) || topLeft.intersects(obj.getHitBoxBottom()) && this.getySpeed() <= 0){
            obj.collideActionBottomSide(this);
        }
    }
    
    /**
     * Precondition:
     *  None.
     * Description:
     *  General method for spawning enemies used in child classes.
     * Postcondition:
     *  None.
     */
    public abstract void spawnEnemy(ArrayList<Enemy> list);

    /**
     * Precondition:
     *  None.
     * Description:
     *  Sets the reference of the Player object that this class has to the passed reference to a Player object.
     * Postcondition:
     *  Reference to the Player object has been changed.
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Precondition:
     *  @param min < @param max
     * Description:
     *  Returns a random number in the range from min to max ignoring 0.
     * Postcondition:
     *  A random number that is not 0 has been returned.
     */
    public int randNum(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt(max - min + 1) + min;

        while (randomNum == 0) {
            randomNum = rand.nextInt(max - min + 1) + min;
        }

        return randomNum;
    }

    /**
     * Precondition:
     *  @param min  < @param max
     * Description:
     *  Returns a random double between min and max.
     * Postcondition:
     *  A random double in the passed range has been returned.
     */
    public double randNum(double min, double max) {
        Random rand = new Random();
        return min + (max - min) * rand.nextDouble();
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Sets the position of the object to its original when switching rooms.
     * Postcondition:
     *  The x and y positions of the object have been reset to the original.
     */
    public void resetPosition() {
        this.setxPos(originalxPos);
        this.setyPos(originalyPos);
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Returns the distance moved by the object.
     * Postcondition:
     *  The distance moved by the object has been returned.
     */
    public double getDistanceMoved() {
        return distanceMoved;
    }
    /**
     * Precondition:
     *  @param distanceMoved > 0
     * Description:
     *  Sets the value of distanceMoved to the value of the passed argument.
     * Postcondition:
     *  The value of distanceMoved has been changed.
     */
    public void setDistanceMoved(double distanceMoved) {
        this.distanceMoved = distanceMoved;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Calculates the distanceMoved by the object based on it speed.
     * Postcondition:
     *  distanceMoved has been updated.
     */
    public void calcDistanceMoved() {
        distanceMoved += Math.abs(this.getxSpeed()) + Math.abs(this.getySpeed());
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Returns the reference to the Player object the class has.
     * Postcondition:
     *  The reference to the Player object has been returned.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Calculates the x distance from the player object to this.
     * Postcondition:
     *  The x distance has been returned.
     */
    public double getxDistanceP() {
        xDistanceP = (player.getHitCenterX()) - (this.getxPos());
        return xDistanceP;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Calculates the y distance from the player object to this.
     * Postcondition:
     *  The y distance has been returned.
     */
    public double getyDistanceP() {
        yDistanceP = (player.getHitCenterY() - player.getRadius() / 2) - (this.getyPos());
        return yDistanceP;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Calculates the total straight line distance from the player to the this.
     * Postcondition:
     *  The total distance to player has been returned.
     */
    public double getTotalDistanceP() {
        totalDistanceP = Math.sqrt(this.getxDistanceP() * this.getxDistanceP() + this.getyDistanceP() * this.getyDistanceP());
        return totalDistanceP;
    }

    /**
     * Precondition:
     *  @param mult > 0
     * Description:
     *  Updates the x and y positions of the object and its hitboxes.
     * Postcondition:
     *  The x and y positions of the object and its hitboxes have been updated.
     */
    public void move(double mult) {
        topRight.x = (int)this.getxPos()+(int)this.getWidth();
        topRight.y = (int)this.getyPos();
        bottomRight.x = (int)this.getxPos()+(int)this.getWidth();
        bottomRight.y = (int)this.getyPos()+(int)this.getHeight();
        topLeft.x = (int)this.getxPos();
        topLeft.y = (int)this.getyPos();
        bottomLeft.x = (int)this.getxPos();
        bottomLeft.y = (int)this.getyPos()+(int)this.getHeight();

        
        this.moveX(this.getxSpeed() / mult);
        this.moveY(this.getySpeed() / mult);
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Checks the collision between a passed bullet and a passed object.
     * Postcondition:
     *  Returns true if the collision is true and false if it is not.
     */
    public <T extends VectorSprite> boolean bulletCollide(T object1, T object2) { // Player and Enemy
        double xDistance, yDistance, totalDistance;

        xDistance = (float) Math.abs(object1.getxPos() + object1.getRadius() / 2 - (object2.getxPos() + object2.getRadius() / 2));
        yDistance = (float) Math.abs(object1.getyPos() + object1.getRadius() / 2 - (object2.getyPos() + object2.getRadius() / 2));

        totalDistance = Math.sqrt(xDistance * xDistance + yDistance * yDistance);
        return (totalDistance <= object1.getRadius() / 2 + object2.getRadius() / 2);

    }
    
    /**
     * Precondition:
     *  None.
     * Description:
     *  Checks collision between a passed Enemy object and a passed Obstacle object.
     * Postcondition:
     *  If the collision is true returns true, else returns false.
     */
    public boolean collideO(Enemy object1, Obstacle object2) {
        double xDistance, yDistance;
        double centerX = this.getxPos() + this.getRadius() / 2;
        double centerY = this.getyPos() + this.getRadius() / 2;

        xDistance = centerX + object1.getRadius() / 2 - Math.max(object2.getxPos(), Math.min(centerX + object1.getRadius() / 2, object2.getxPos() + object2.getWidth()));
        yDistance = centerY + object1.getRadius() / 2 - Math.max(object2.getyPos(), Math.min(centerY + object1.getRadius() / 2, object2.getyPos() + object2.getHeight()));
        return (xDistance * xDistance + yDistance * yDistance) < (object1.getRadius() / 2 * object1.getRadius() / 2);
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Overrides the super shoot with blank because this class cannot be instantiated.
     * Postcondition:
     *  None.
     */
    public void shoot() {
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Draws the object and its bullets.
     * Postcondition:
     *  The object and its bullets have been drawn.
     */
    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Checks collision between this and another Enemy object.
     * Postcondition:
     *  If true, returns true, else returns false.
     */
    public boolean collideE(Enemy object1) {
        double xDistance, yDistance, totalDistance;
        xDistance = (float) Math.abs(object1.getxPos() + object1.getRadius() / 2
                - this.getxPos() - this.getRadius() / 2);
        yDistance = (float) Math.abs(object1.getyPos() + object1.getRadius() / 2
                - this.getyPos() - this.getRadius() / 2);
        totalDistance = Math.sqrt(xDistance * xDistance + yDistance * yDistance);
        return (totalDistance <= object1.getRadius() / 2 + this.getRadius() / 2);
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Pushes away an Enemy object colliding with the passed object.
     * Postcondition:
     *  The colliding enemy has been pushed away.
     */
    public void collideActionE(Enemy object1) {
        this.moveX(-this.getxSpeed() * 2);
        this.moveY(-this.getySpeed() * 2);
    }
    
    /**
     * Precondition:
     *  None.
     * Description:
     *  Checks the collision between the an Enemy and a Player.
     * Postcondition:
     *  Returns true if the collision is true, false if it is not.
     */
    public boolean collideP(Player object1) {
        double xDistance, yDistance, totalDistance;
        xDistance = (float) Math.abs(object1.getHitCenterX()
                - this.getxPos() - this.getRadius() / 2);
        yDistance = (float) Math.abs(object1.getHitCenterY()
                - this.getyPos() - this.getRadius() / 2);
        totalDistance = Math.sqrt(xDistance * xDistance + yDistance * yDistance);
        return (totalDistance <= object1.getRadius() / 2 + this.getRadius() / 2);
    }
    
    /**
     * Precondition:
     *  None.
     * Description:
     *  Overriding the super abstract method.
     * Postcondition:
     *  None.
     */
    public abstract void changeSprite(double angle);
}
