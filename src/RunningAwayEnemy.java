
import java.util.ArrayList;
import java.util.Random;


public class RunningAwayEnemy extends Enemy{
    private int distanceMovedQ1,distanceMovedQ2,distanceMovedQ3,distanceMovedQ4;
    private boolean runningAway;
    private boolean spawnEnemy;
    private int spawnDistance, spawnCap;
    public RunningAwayEnemy(String name, double xPos, double yPos, int width, int height, int radius, Player player,int spawnDistance) {
        super(name, xPos, yPos, width, height, radius, player);
        this.setxSpeed(randNum(-1,1));
        this.setySpeed(randNum(-1,1));
        this.setHealth(20);
        this.setPlayer(player);
        spawnCap = 0;
        spawnEnemy = false;
        this.spawnDistance = spawnDistance;
    }
    public void changeSprite(double angle){}
    
    /**
     * Precondition:
     *  @param mult > 0
     * Description:
     *  Updates the position of the enemy.
     * Postcondition:
     *  The position has been updated.
     */
    //running away
    public void move(double mult){
        int rand = 0;
        runningAway = false;
        
        this.calcDistanceMoved();
        //movement
        if(this.getTotalDistanceP() <= 200){
            runningAway = true;
            
            //regular movement for running away
            if((this.getxDistanceP() < 0 && this.getPlayer().getxSpeed() > 0 ) || (this.getxDistanceP() > 0 && this.getPlayer().getxSpeed() < 0)){
                this.setxSpeed(this.getPlayer().getxSpeed()*1.5);
                //depending on how we want it, adding the other speed in these statements will make the enemy follow in exactly opposite direction
                //but without it it looks more natural in my opinion
                this.setySpeed(this.getPlayer().getySpeed()*1.5);
            }
            
            if((this.getyDistanceP() < 0 && this.getPlayer().getySpeed() > 0) || (this.getyDistanceP() > 0 && this.getPlayer().getySpeed() < 0)){
                this.setySpeed(this.getPlayer().getySpeed()*1.5);
                this.setxSpeed(this.getPlayer().getxSpeed()*1.5);
            }
            if((this.getxDistanceP() < 0 && this.getPlayer().getxSpeed() < 0 ) || (this.getxDistanceP() > 0 && this.getPlayer().getxSpeed() > 0)){
                this.setxSpeed(-this.getPlayer().getxSpeed()*1.5);
                //this.setySpeed(-this.getPlayer().getySpeed()*1.5);
            }
            if((this.getyDistanceP() < 0 && this.getPlayer().getySpeed() < 0) || (this.getyDistanceP() > 0 && this.getPlayer().getySpeed() > 0)){
                //this.setxSpeed(-this.getPlayer().getxSpeed()*1.5);
                this.setySpeed(-this.getPlayer().getySpeed()*1.5);
            }
            //fixes the enemy moving too slow when player is stationary and in range
            if(this.getxSpeed() < 0){
                if(this.getxSpeed() > -0.9){
                    this.setxSpeed(-1);
                }
            }
            else if(this.getxSpeed() > 0){
                if(this.getxSpeed() < 0.9){
                    this.setxSpeed(1);
                }
            }
            if(this.getySpeed() < 0){
                if(this.getySpeed() > -0.9){
                    this.setySpeed(-1);
                }
            }
            else if(this.getySpeed() > 0){
                if(this.getySpeed() < 0.9){
                    this.setySpeed(1);
                }
            }
        }
        
        //random movement for when the enemy is not being chased
        else if(this.getDistanceMoved() >= spawnDistance && Math.abs(this.getTotalDistanceP()) > 200){
            this.setxSpeed(randNum(-1,1));
            this.setySpeed(randNum(-1,1));
            rand = randNum(1,5);
            if(rand == 1){
                this.setxSpeed(0);
            }
            else if(rand == 5){
                this.setySpeed(0);
            }
            this.setDistanceMoved(0);  
            spawnEnemy = true;
        }
        
        //boundaries so that the player does not get stuck to the walls when not being chased
        if(!runningAway){
            if(this.getxPos() <= 89){
                this.setxSpeed(1);
                this.setySpeed(randNum(-1,1));
            }
            if(this.getyPos() <= 89){
                this.setySpeed(1);
                this.setxSpeed(randNum(-1,1));
            }
            if(this.getxPos()+this.getWidth() >= 1067){
                this.setxSpeed(-1);
                this.setySpeed(randNum(-1,1));
            }
            if(this.getyPos()+this.getHeight() >= 712){
                this.setySpeed(-1);
                this.setxSpeed(randNum(-1,1));
            }  
        }
        else if(runningAway){
            //left screen boundary
            if(this.getxPos() < 89){
                this.setxSpeed(0);
                if((this.getyDistanceP() < 0 && this.getPlayer().getySpeed() > 0) || (this.getyDistanceP() > 0 && this.getPlayer().getySpeed() < 0)){
                    this.setySpeed(this.getPlayer().getySpeed()*1.5);
                    this.setxPos(89);
                }
            }
            //top screen boundary
            if(this.getyPos() < 89){
                this.setySpeed(0);
                if((this.getxDistanceP() < 0 && this.getPlayer().getxSpeed() > 0) || (this.getxDistanceP() > 0 && this.getPlayer().getxSpeed() < 0)){
                    this.setxSpeed(this.getPlayer().getxSpeed()*1.5);
                    this.setyPos(89);
                }
            }
            //right screen boundary
            if(this.getxPos()+this.getWidth() > 1067){
                this.setxSpeed(0);
                this.setxPos(1067-this.getWidth());
                if((this.getyDistanceP() < 0 && this.getPlayer().getySpeed() > 0) || (this.getyDistanceP() > 0 && this.getPlayer().getySpeed() < 0)){
                    this.setySpeed(this.getPlayer().getySpeed()*1.5);
                    this.setxPos(1067-this.getWidth());
                }
            }
            //bottom screen boundary
            if(this.getyPos()+this.getHeight() > 712){
                this.setySpeed(0);
                if((this.getxDistanceP() < 0 && this.getPlayer().getxSpeed() > 0) || (this.getxDistanceP() > 0 && this.getPlayer().getxSpeed() < 0)){
                    this.setxSpeed(this.getPlayer().getxSpeed()*1.5);
                    this.setyPos(712-this.getHeight());
                }
            }
        }  
        super.move(mult);  
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Returns the value of runningAway.
     * Postcondition:
     *  The value of runningAway has been returned.
     */
    public boolean isRunningAway() {
        return runningAway;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Sets the value of runnignAway to the value of the passed argument.
     * Postcondition:
     *  The value of runningAway is changed.
     */
    public void setRunningAway(boolean runningAway) {
        this.runningAway = runningAway;
    }


    @Override
    public boolean collideE(Enemy object1) {
        return false;
    }
    /**
     * Precondition:
     *  None.
     * Description:
     *  Returns the name of the enemy.
     * Postcondition:
     *  The reference ot the string with the name is returned.
     */
    @Override
    public String toString() {
        String name  = "RunningE";
        return name;
    }
    
    /**
     * Precondition:
     *  None.
     * Description:
     *  Spawns new enemies into the room.
     * Postcondition:
     *  A new enemy has been spawned.
     */
    public void spawnEnemy(ArrayList<Enemy> list){
        
        //System.out.println(spawnCap);
        if(spawnEnemy && spawnCap < 3){
            spawnCap++;
            list.add(new EyeEnemy("/flying + following + shooting enemy/eyeball.png", this.getxPos(), this.getyPos(), 20, 20, 30, this.getPlayer()));
            spawnEnemy = false;
        }
    }
}

