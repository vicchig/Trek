
import java.util.ArrayList;


public class EyeEnemy extends FollowingEnemy{

    public EyeEnemy(String name, double xPos, double yPos, int width, int height, int radius, Player player) {
        super(name, xPos, yPos, width, height, radius, player);
        this.setHealth(25);
        this.setMaxHp(25);
        bController.setInitCoolDown(35);
        bController.setBulletRange(250);
        this.setAttack(1);
        this.setStartCell(0);
        this.setEndCell(2);
        this.setCurrentCell(0);
        this.setLoopCells(true);
        this.setHitWidth(38);
        this.setHitHeight(44);
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Disables the collision with obstacles by leaving the body blank.
     * Postcondition:
     *  None.
     */
    @Override
    public void collideWObstacle(Obstacle obj) {
    }
    
    /**
     * Precondition:
     *  @param angle >= -Pi && @param angle <= Pi
     * Description:
     *  Changes the sprite orientation based on the value of the passed movement angle.
     * Postcondition:
     *  The sprite orientation has been changed.
     */
    @Override
    public void changeSprite(double angle) {
        //up
        if(angle >= -Math.PI/2-0.3 && angle <= -Math.PI/2+0.3){
            this.setWidth(39);
            this.setHeight(76);
            this.setPicX(81);
            this.setPicY(0);
        }
        //down
        else if(angle >= Math.PI/2-0.3 && angle <= Math.PI/2+0.3){
            this.setWidth(38);
            this.setHeight(76);
            this.setPicX(0);
            this.setPicY(0);
        }
        //left
        else if((angle < -Math.PI/2-0.2 && angle > -Math.PI) || (angle > Math.PI/2+0.2 && angle < Math.PI)){
            this.setWidth(43);
            this.setHeight(76);
            this.setPicX(163);
            this.setPicY(0);
        }
        //right
        else if( (angle <= 0 && angle > -Math.PI+0.15) || (angle > 0 && angle < Math.PI/2-0.15)){
            this.setWidth(44);
            this.setHeight(76);
            this.setPicX(247);
            this.setPicY(0);
        }
    
    }

    /**
     * Precondition:
     *  @param multi > 0
     * Description:
     *  Updates the position of the object and animates it.
     * Postcondition:
     *  The object's position has been updated and the object has been animated.
     */
    public void move(double mult) {
        super.move(mult); 
        this.animateY();
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  The body is blank since this enemy does not spawn other enemies.
     * Postcondition:
     *  None.
     */
    @Override
    public void spawnEnemy(ArrayList<Enemy> list) {
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Shoots a bullet in the direction of the movement.
     * Postcondition:
     *  A bullet has been shot.
     */
    @Override
    public void shoot() {
        bController.addBullet("enemybullet.png",this.getxPos(),this.getyPos(),20,this.getxSpeed()*5,this.getySpeed()*5);
    }
    
}
