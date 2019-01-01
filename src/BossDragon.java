
import java.util.ArrayList;


public class BossDragon extends FollowingEnemy{
    private int rand;
    public BossDragon(String name, double xPos, double yPos, int width, int height, int radius, Player player) {
        super(name, xPos, yPos, width, height, radius, player);
        this.setMaxHp(450);
        this.setHealth(450);
        this.setAttack(3);
        bController.setInitCoolDown(0);
        bController.setBulletRange(250);
        bController.setInitFollowingCoolDown(50);
        bController.setShieldRange(300);
        bController.setInitFireStepCoolDown(0);
        bController.setFireStepRange(1000);
        bController.setFollowingRange(150);
        this.setPlayer(player);
        this.setLoopCells(true);
        this.setStartCell(0);
        this.setCurrentCell(0);
        this.setEndCell(4);
        this.setHitHeight(114);
        this.setHitWidth((int)this.getWidth());
    }
    @Override
    public void changeSprite(double angle){
        //up
        if(angle >= -Math.PI/2-0.3 && angle <= -Math.PI/2+0.3){
            this.setPicX(0);
            this.setPicY(289);
        }
        //down
        else if(angle >= Math.PI/2-0.3 && angle <= Math.PI/2+0.3){
            this.setPicX(0);
            this.setPicY(0);
        }
        //left
        else if((angle < -Math.PI/2-0.2 && angle > -Math.PI) || (angle > Math.PI/2+0.2 && angle < Math.PI)){
            this.setPicX(0);
            this.setPicY(96);
        }
        //right
        else if( (angle <= 0 && angle > -Math.PI+0.15) || (angle > 0 && angle < Math.PI/2-0.15)){
            this.setPicX(0);
            this.setPicY(195);
        }
    }
    @Override
    public void shoot(){
        this.calcDistanceMoved();

        if(this.getDistanceMoved() > 200 && bController.cBulletsShield.size() == 0){
            rand = randNum(0, 25);
            this.setDistanceMoved(0);
        }
        //System.out.println(rand);
        if (rand >= 10 && rand <= 25) {
            rand = -1;
            bController.addCBullet("crystalDragonAttack.png",this.getxPos()+this.getWidth()/2-15,this.getyPos()+this.getHeight()/2-90,15,this.getxSpeed(),this.getySpeed(),this);
            bController.addCBullet("crystalDragonAttack.png",this.getxPos()+this.getWidth()/2+50,this.getyPos()+this.getHeight()/2,15,this.getxSpeed(),this.getySpeed(),this);
            bController.addCBullet("crystalDragonAttack.png",this.getxPos()+this.getWidth()/2-15,this.getyPos()+this.getHeight()/2+60,15,this.getxSpeed(),this.getySpeed(),this);
            bController.addCBullet("crystalDragonAttack.png",this.getxPos()+this.getWidth()/2-85,this.getyPos()+this.getHeight()/2,15,this.getxSpeed(),this.getySpeed(),this);
            
            bController.addCBulletS("crystalDragonAttack.png",this.getxPos()+this.getWidth()/2-15,this.getyPos()+this.getHeight()/2-90,15,this.getxSpeed(),this.getySpeed(),this);
            bController.addCBulletS("crystalDragonAttack.png",this.getxPos()+this.getWidth()/2+50,this.getyPos()+this.getHeight()/2,15,this.getxSpeed(),this.getySpeed(),this);
            bController.addCBulletS("crystalDragonAttack.png",this.getxPos()+this.getWidth()/2-15,this.getyPos()+this.getHeight()/2+60,15,this.getxSpeed(),this.getySpeed(),this);
            bController.addCBulletS("crystalDragonAttack.png",this.getxPos()+this.getWidth()/2-85,this.getyPos()+this.getHeight()/2,15,this.getxSpeed(),this.getySpeed(),this);

        }
        else if(rand < 10 && rand >= 0){
            bController.addFSBullet("fireWalk.png", this.getxPos(), this.getyPos(), 90, this.getxSpeed(), this.getySpeed(), this);  
            rand = -1;
        }

        else{
            bController.addFollowingBullet("enemybullet.png",this.getxPos(), this.getyPos(), 20, this.getxSpeed(), this.getySpeed(), this);
        }
    }   

    @Override
    public void spawnEnemy(ArrayList<Enemy> list) {
    }

    @Override
    public void move(double mult) {
        super.move(mult); 
        changeSprite(this.getAngle());
        this.animate();
    }

    @Override
    public String toString() {
        String name = "Irgret, The Last Crystal Dragon";
        return name;
    }
    
}
