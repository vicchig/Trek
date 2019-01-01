
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public class FollowingEnemy extends Enemy{
    
    private double angle;
    
    public FollowingEnemy(String name, double xPos, double yPos, int width, int height, int radius, Player player) {
        super(name, xPos, yPos, width, height, radius, player);
        angle = Math.PI/2;
        this.setHealth(20);
        this.setMaxHp(20);
        bController.setInitCoolDown(20);
        bController.setBulletRange(100);
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Returns the value of angle.
     * Postcondition:
     *  The value of angle has been returned.
     */
    public double getAngle() {
        return angle;
    }

    /**
     * Precondition:
     *  @param angle >= -Pi && angle <= Pi
     * Description:
     *  Sets the value of angle to the value of the passed argument.
     * Postcondition:
     *  The value of angle has been set.
     */
    public void setAngle(double angle) {
        this.angle = angle;
    }
    
    /**
     * Precondition:
     *  None.
     * Description:
     *  Leaves the method blank as this class has no sprite associated with it.
     * Postcondition:
     *  None.
     */
    public void changeSprite(double angle){}
    
    /**
     * Precondition:
     *  @param mult > 0
     * Description:
     *  Updates the position of this and makes it follow the player.
     * Postcondition:
     *  The position of the object has been updated.
     */
    public void move(double mult){
        int rand = 0;
        
        //stores the current position of the enemy
        double tempPosX = this.getxPos();
        double tempPosY = this.getyPos();
        
        //tracking the player
        angle = Math.atan(this.getyDistanceP()/this.getxDistanceP());

        if(this.getxDistanceP() < 0){
            if(this.getyDistanceP() < 0){
                angle = (-Math.PI/2)- (Math.PI/2 - Math.abs(angle));
            }
            else{
                angle = (Math.PI) - Math.abs(angle); 
            }
        }
        
        //moving on the calculated angle
        this.setxSpeed(Math.cos(angle));
        this.setySpeed(Math.sin(angle));

        changeSprite(angle);
        super.move(mult);
    }
    
    /**
     * Precondition:
     *  None.
     * Description:
     *  Blank as this enemy does not spawn anything.
     * Postcondition:
     *  None.
     */
    public void spawnEnemy(ArrayList<Enemy> list){}
    
    /**
     * Precondition:
     *  None.
     * Description:
     *  Blank as this enemy has no ability to shoot.
     * Postcondition:
     *  None.
     */
    public void shoot() {
    }

     /**
     * Precondition:
     *  None.
     * Description:
     *  Draws the enemy and bullets by calling the super draw method..
     * Postcondition:
     *  The enemy and bullets have been drawn.
     */
    @Override
    public void draw(Graphics g){
        super.draw(g);   
    }
    
    
}
