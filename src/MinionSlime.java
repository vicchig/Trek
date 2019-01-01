
import java.awt.Graphics;


public class MinionSlime extends FollowingEnemy{
    private boolean shoot;
    public MinionSlime(String name, double xPos, double yPos, int width, int height, int radius, Player player) {
        super(name, xPos, yPos, width, height, radius, player);
        this.setStartCell(0);
        this.setEndCell(4);
        this.setCurrentCell(0);
        this.setPicX(0);
        this.setPicY(0);
        this.setLoopCells(true);
        this.setHealth(20);
        this.setMaxHp(20);
        this.setAttack(1);
        bController.setInitCoolDown(0);
        bController.setBulletRange(300);
        shoot = true;
        this.setHitHeight(65);
        this.setHitWidth((int)this.getWidth());
    }
    
    /**
     * Precondition:
     *  @param mult > 0
     * Description:
     *  Updates the position and animates the object.
     * Postcondition:
     *  The position has been updated and the object animated.
     */
    public void move(double mult){
        super.move(mult);
        this.animate();
    }
    
    /**
     * Precondition:
     *  None.
     * Description:
     *  Shoots bullets in perpendicular directions based on a random cool down.
     * Postcondition:
     *  Bullets have been shot.
     */
    public void shoot(){
        int rand = 0;
        if(shoot){
            rand = randNum(0,150);
            if(rand == 150){
                shoot = false;
            }
        }
        if(rand == 150){
            //shooting one bullet in each direction perpendicullarly
                bController.addBullet("enemybullet.png",this.getxPos()+this.getRadius()/2,this.getyPos()+this.getRadius()/2,10,0,-2);
                bController.addBullet("enemybullet.png",this.getxPos()+this.getRadius()/2,this.getyPos()+this.getRadius()/2,10,0,2);
                bController.addBullet("enemybullet.png",this.getxPos()+this.getRadius()/2,this.getyPos()+this.getRadius()/2,10,2,0);
                bController.addBullet("enemybullet.png",this.getxPos()+this.getRadius()/2,this.getyPos()+this.getRadius()/2,10,-2,0);
                shoot = true;
        }
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Returns the name of the object.
     * Postcondition:
     *  The name of the object has been returned.
     */
    @Override
    public String toString() {
        String name = "MINION";
        return name;
    }
    
    /**
     * Precondition:
     *  None.
     * Description:
     *  Draws the object by calling the super draw.
     * Postcondition:
     *  The object has been drawn.
     */
    public void draw(Graphics g){
        super.draw(g);
    }
}