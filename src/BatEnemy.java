
import java.util.ArrayList;

public class BatEnemy extends RunningAwayEnemy{

    public BatEnemy(String name, double xPos, double yPos, int width, int height, int radius, Player player, int spawnDistance) {
        super(name, xPos, yPos, width, height, radius, player, spawnDistance);
        this.setPicX(0);
        this.setPicY(0);
        this.setLoopCells(true);
        this.setCurrentCell(0);
        this.setStartCell(0);
        this.setEndCell(3);
        this.setHitHeight(36);
        this.setHitWidth(50);
        this.setAttack(1);
        this.setMaxHp(20);
        this.setHealth(20);
        
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Overrides the super collideWObstacles
     * Postcondition:
     * None.
     */
    @Override
    public void collideWObstacle(Obstacle obj) {
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Changes the sprite of the object based on thew direction of its movement.
     * Postcondition:
     *  Sprite orientation has been changed.
     */
    @Override
    public void changeSprite(double angle) {
        //diagonal right up
        if(this.getxSpeed() > 0 && this.getySpeed() < 0){
            this.setPicX(0);
            this.setPicY(156);
        }
        //diagonal left up
        else if(this.getxSpeed() < 0 && this.getySpeed() < 0){
            this.setPicX(0);
            this.setPicY(80);
        }
        //diagonal right down
        else if(this.getxSpeed() > 0 && this.getySpeed() > 0){
            this.setPicX(0);
            this.setPicY(156);
        }
        //diagonal left down
        else if(this.getxSpeed() < 0 && this.getySpeed() > 0){
            this.setPicX(0);
            this.setPicY(80);
        }
        //down
        else if(this.getxSpeed() == 0 && this.getySpeed() >= 0){
            this.setWidth(50);
            this.setPicX(0);
            this.setPicY(0);
        }
        //up
        else if(this.getxSpeed() == 0 && this.getySpeed() <= 0){
            this.setWidth(50);
            this.setPicY(231);
            this.setPicX(0);
        }
        //rigth
        else if(this.getxSpeed() >= 0 && this.getySpeed() == 0){
            this.setPicX(0);
            this.setPicY(156);
        }
        //left
        else if(this.getxSpeed() <= 0 && this.getySpeed() == 0){
            this.setPicX(0);
            this.setPicY(80);
        }
    }

    /**
     * Precondition:
     *  @param mult is greater than 0
     * Description:
     *  Overrides the super.move, changes the sprite orientation and animates the object.
     * Postcondition:
     *  Position and sprite orientation have been changed, sprite has been animated. 
     */
    @Override
    public void move(double mult) {
        super.move(mult); 
        changeSprite(1);
        this.animate();
    }
    
}
