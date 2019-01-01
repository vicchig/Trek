
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;


public class BossPlayer extends Enemy{

    private boolean bulletDetected;
    private double newDistance;
    private Bullet bulletInRange;
    private double xDistance, yDistance, hypo;
    private boolean moveRight, moveLeft,moveUp,moveDown;
    private int radialCoolDown;
    private boolean evading;
    private boolean shootRadial;
    private int evadingCoolDown;
    private ArrayList<Obstacle>obstacles;
    
    public BossPlayer(String name, double xPos, double yPos, int width, int height, int radius, Player player,ArrayList<Obstacle>obstacles) {
        super(name, xPos, yPos, width, height, radius, player);
        this.obstacles = obstacles;
        this.setHealth(150);
        this.setMaxHp(150);
        radialCoolDown = 0;
        shootRadial = false;
        evading = false;
        evadingCoolDown = 0;
        this.bController.setInitCoolDown(20);
        this.bController.setBulletRange(200);
        bulletDetected = false;
        this.setxSpeed(0);
        this.setySpeed(0);
        this.setPlayer(player);
        this.setStartCell(0);
        this.setEndCell(4);
        this.setCurrentCell(0);
        this.setPicX(0);
        this.setPicY(0);
        this.setAttack(1);
        this.setLoopCells(true);
        newDistance = 100;
    }
    
    /**
     * Precondition:
     *  None.
     * Description:
     *  Checks if there is a bullet in a set radius around the player.
     * Postcondition:
     *  Returns true if a bullet is inside the radius, false if it is not.
     */
    public boolean bulletDetection(){
        LinkedList<Bullet> tempList = this.getPlayer().bController.bullets;
        double bulletX, bulletY;
        
        bulletDetected = false;
        
        for (int i= 0;i<tempList.size();i++){

            bulletX = tempList.get(i).getxPos();
            bulletY = tempList.get(i).getyPos();
            
            xDistance = (this.getxPos() - bulletX);
            yDistance = (this.getyPos() - bulletY);
            
            hypo = Math.sqrt(xDistance*xDistance + yDistance*yDistance);
            
            if (hypo < 200){
                if(hypo < newDistance){
                    bulletInRange = new Bullet("enemybullet.png",bulletX, bulletY, 10,tempList.get(i).getxSpeed(),tempList.get(i).getySpeed());
                    newDistance = hypo;
                    bulletDetected = true;
                }
            }
        }
        return bulletDetected;
    }
    
    /**
     * Precondition:
     *  None.
     * Description:
     *  Shoots various attacks based on cool downs and the objects distance from the player.
     * Postcondition:
     *  The object has shot a bullet or multiple bullets.
     */
    @Override
    public void shoot() {  
        //Radial shot detection and cool down
        if(this.getTotalDistanceP() <= 100 && !shootRadial){
            radialCoolDown++;
            if(radialCoolDown >= 50){
                shootRadial = true;
                radialCoolDown = 0;
            }
        }
        else{
            //Regular Attacks
            if(!evading){
                bController.setInitCoolDown(20);
                bController.setBulletRange(200);
                bController.addBullet("enemybullet.png",this.getxPos()+this.getRadius()/2, this.getyPos()+this.getRadius()/2, 10, this.getxSpeed()*6, this.getySpeed()*6);
            }
            //Shoots radial when evading bullets
            else if (evading){
                evadingCoolDown++;
                if(evadingCoolDown >= 50){
                    
                    bController.setInitCoolDown(0);
                    bController.setBulletRange(300);
                    for(double i = 0;i<Math.PI*2;i+=0.8){
                        bController.addBullet("enemybullet.png",this.getxPos()+this.getRadius()/2+(this.getRadius()/2*Math.cos(i)),this.getyPos()+this.getRadius()/2+(this.getRadius()/2*Math.sin(i)),10,Math.cos(i)*4,Math.sin(i)*4);
                    }
                    evadingCoolDown = 0;
                }
            }
        }
        //Radial Shot
        if(shootRadial){
            bController.setInitCoolDown(0);
            for(double i = 0;i<Math.PI*2;i+=0.4){
                bController.addBullet("enemybullet.png",this.getxPos()+this.getRadius()/2+(this.getRadius()/2*Math.cos(i)),this.getyPos()+this.getRadius()/2+(this.getRadius()/2*Math.sin(i)),10,Math.cos(i)*2,Math.sin(i)*2);
            }
            bController.setInitCoolDown(20);
            shootRadial = false;
        }
    }
    /**
     * Precondition:
     *  Angle is between -PI and PI
     * Description:
     *  Changes the object sprite based on the angle it is moving at.
     * Postcondition:
     *  The object's sprite's orientation has been changed.
     */
    @Override
    public void changeSprite(double angle){
        //up
        if(angle >= -Math.PI/2-0.3 && angle <= -Math.PI/2+0.3){
            this.setPicX(0);
            this.setPicY(211);
        }
        //down
        else if(angle >= Math.PI/2-0.3 && angle <= Math.PI/2+0.3){
            this.setPicX(0);
            this.setPicY(0);
        }
        //left
        else if((angle < -Math.PI/2-0.2 && angle > -Math.PI) || (angle > Math.PI/2+0.2 && angle < Math.PI)){
            this.setPicX(0);
            this.setPicY(141);
        }
        //right
        else if( (angle <= 0 && angle > -Math.PI+0.15) || (angle > 0 && angle < Math.PI/2-0.15)){
            this.setPicX(0);
            this.setPicY(74);
        }
    }
    /**
     * Precondition:
     *  None.
     * Description:
     *  Projects the passed bullet's path based on its speed and angle.
     * Postcondition:
     *  The bullet's path has been projected.
     */
    public void projectDistance(Bullet bullet){
        bullet.setxSpeed((xDistance/hypo)*bullet.getxSpeed());
        bullet.setySpeed((yDistance/hypo)*bullet.getySpeed());
        bullet.moveX(bullet.getxSpeed());
        bullet.moveY(bullet.getySpeed());
    }
    /**
     * Precondition:
     *  @param mult is greater than 0.
     * Description:
     *  Moves the object and makes it evade bullets if any are detected.
     * Postcondition:
     *  The object has been moved and animated.
     */
    @Override
    public void move(double mult) {
        int rand = 0;
        double angle = 0;
        double tempPosX, tempPosY;
        
        bulletDetected = bulletDetection();
        newDistance = 201;
        //Evasion if a bullet is in range.
        if(bulletDetected){
            projectDistance(bulletInRange);
            evading = true;
            xDistance = this.getxPos() - bulletInRange.getxPos(); 
            yDistance = this.getyPos() - bulletInRange.getyPos();
            
            hypo = Math.sqrt(xDistance*xDistance + yDistance*yDistance);

            this.setxSpeed(xDistance/hypo * 2);
            this.setySpeed(yDistance/hypo * 2);
        
            hypo = Math.sqrt(this.getxSpeed()*this.getxSpeed() + this.getySpeed()*this.getySpeed());
            
            this.setxSpeed(this.getxSpeed()/hypo);
            this.setySpeed(this.getySpeed()/hypo);
            for(int i = 0;i<obstacles.size();i++){
                if(this.collideO(this, obstacles.get(i))){
                    obstacles.get(i).collideActionB(this);
                }
            }
            super.move(0.5); 
        }
        //Regular Movement
        else{
            evading = false;
            //stores the current position of the enemy
            tempPosX = this.getxPos();
            tempPosY = this.getyPos();

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
            changeSprite(angle);

            //moving on the calculated angle
            this.setxSpeed(Math.cos(angle));
            this.setySpeed(Math.sin(angle));
            super.move(0.7); 
        }
        //Boundaries
        if(this.getxPos() < 81){
            this.setxPos(81);
        }
        
        else if(this.getyPos() < 81){
            this.setyPos(81);
        }
        else if(this.getxPos()+this.getWidth() >= 1157-89){
            this.setxPos(1157-89-this.getWidth());
        }
        else if(this.getyPos()+this.getHeight() >= 801-89){
            this.setyPos(801-89-this.getHeight());
        }
        this.animate();
    }

    @Override
    public String toString() {
        String name = "Fear";
        return name;
    }
    /**
     * Precondition:
     *  None.
     * Description:
     *  Overrides the super spawn enemy with a blank.
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
     *  Draws the object and its health bar.
     * Postcondition:
     *  The object and its health bar have been drawn.
     */
    public void draw(Graphics g){
        super.draw(g);
//        g.setColor(new Color(0,0,0,150));
//        g.fillRect(1157/2-330, 801-89-40, 660, 30);
//        g.setColor(new Color(144,0,32,100));
//        g.fillRect(1157/2-330, 801-89-40, (int) (660 * (this.getHealth()/this.getMaxHp())), 30);
//        g.setFont(new Font("Chiller", Font.BOLD, 40));
//        g.setColor(Color.WHITE);
//        g.drawString(this.toString(),1157/2-330,801-89-30-15);
    }
}
