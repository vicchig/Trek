
import java.awt.Graphics;
import java.util.LinkedList;

public class BulletController {

    protected LinkedList<Bullet> bullets = new LinkedList<Bullet>();
    protected LinkedList<Bomb> bombs = new LinkedList<Bomb>();
    protected LinkedList<CrystalBullet> cBulletsShield = new LinkedList<CrystalBullet>();
    protected LinkedList<FireStep>bulletFStep = new LinkedList<FireStep>();
    protected LinkedList<FollowingBullet>bulletsF = new LinkedList<FollowingBullet>();
    private Bullet tempBullet;
    private Bomb tempBomb;
    private CrystalBullet tempCBullet;
    private FireStep tempFSBullet;
    private int coolDown;
    private int initCoolDown;
    private int bulletRange;
    private int shieldRange;
    private int fireStepCoolDown;
    private int fireStepRange;
    private int followingCoolDown;
    private int followingRange;
    private int initFollowingCoolDown;
    private int initFireStepCoolDown;

    public void setInitFireStepCoolDown(int initFireStepCoolDown) {
        this.initFireStepCoolDown = initFireStepCoolDown;
    }

    public void setInitFollowingCoolDown(int initFollowingCoolDown) {
        this.initFollowingCoolDown = initFollowingCoolDown;
    }

    public int getInitFireStepCoolDown() {
        return initFireStepCoolDown;
    }

    public int getInitFollowingCoolDown() {
        return initFollowingCoolDown;
    }
    
    public int getFollowingCoolDown() {
        return followingCoolDown;
    }

    public void setFollowingCoolDown(int followingCoolDown) {
        this.followingCoolDown = followingCoolDown;
    }

    public int getFollowingRange() {
        return followingRange;
    }

    public void setFollowingRange(int followingRange) {
        this.followingRange = followingRange;
    }
    
    public int getFireStepRange() {
        return fireStepRange;
    }

    public void setFireStepRange(int fireStepRange) {
        this.fireStepRange = fireStepRange;
    }
    
    public int getFireStepCoolDown() {
        return fireStepCoolDown;
    }

    public void setFireStepCoolDown(int fireStepCoolDown) {
        this.fireStepCoolDown = fireStepCoolDown;
    }
    
    public void setInitCoolDown(int coolDown) {
        this.initCoolDown = coolDown;
    }
    public void setBulletRange(int range){
        bulletRange = range;
    }

    public void setShieldRange(int shieldRange) {
        this.shieldRange = shieldRange;
    }

    public int getShieldRange() {
        return shieldRange;
    }

    public int getBulletRange() {
        return bulletRange;
    }
    
    public int getInitCoolDown() {
        return initCoolDown;
    }
    public void increment(){
        for(int i = 0; i < bullets.size(); i++){
            
            tempBullet = bullets.get(i);
            
            if(tempBullet.getDistance() > bulletRange){
                removeBullet(tempBullet);
            }

            tempBullet.tick();
        }
        for(int i = 0;i<cBulletsShield.size();i++){
            tempCBullet = cBulletsShield.get(i);
            
            if(tempCBullet.getDistance() > shieldRange){
                removeCBullet(tempCBullet);
            }
            if(tempCBullet.getType()==1){
                tempCBullet.tick();
            }
            else if(tempCBullet.getType()==2){
                tempCBullet.tickSpecial();
            }
        }
        for(int i = 0;i<bulletFStep.size();i++){
            tempFSBullet = bulletFStep.get(i);
            if(tempFSBullet.getDistance() > fireStepRange){
                bulletFStep.remove(i);
            }
            tempFSBullet.tick();
        }
        for(int i = 0;i<bulletsF.size();i++){
            if(bulletsF.get(i).getDistance() > followingRange){
                bulletsF.remove(i);
            }
            bulletsF.get(i).tick();
        }
        coolDown--;
        fireStepCoolDown--;
        followingCoolDown--;
        for(int i = 0; i < bombs.size(); i++)
        {
            tempBomb = bombs.get(i);
            if(tempBomb.getFuse() <= 0)
            {
                if(tempBomb.isExplode() && tempBomb.getFuse() <= -12)
                {
                    removeBomb(tempBomb);
                }
                else if(tempBomb.getFuse() == 0)
                {
                    tempBomb.explode(175);
                }
            }
            tempBomb.tick();
        }
    }


    public void draw(Graphics g) {
        for (int i = 0; i < bullets.size(); i++) {
            tempBullet = bullets.get(i);
            tempBullet.draw(g);
        }
        for(int i = 0; i < bombs.size(); i++)
        {
            tempBomb = bombs.get(i);
            tempBomb.draw(g);
        }
        for(int i = 0; i < cBulletsShield.size(); i++)
        {
            tempCBullet = cBulletsShield.get(i);
            tempCBullet.draw(g);
        }
        for(int i = 0;i<bulletFStep.size();i++){
            bulletFStep.get(i).draw(g);
        }
        for(int i = 0;i<bulletsF.size();i++){
            bulletsF.get(i).draw(g);
        }
    }

    public void addBullet(String name, double xPos, double yPos, int radius, double xSpeed, double ySpeed) {
        if(coolDown <=0){
            bullets.add(new Bullet(name, xPos,yPos, radius, xSpeed, ySpeed));
            coolDown = this.getInitCoolDown();
        }
    }
    public void addCBullet(String name, double xPos, double yPos, int radius, double xSpeed, double ySpeed,BossDragon dragon){
        if(coolDown <= 0){
            cBulletsShield.add(new CrystalBullet(name,xPos,yPos,radius,xSpeed,ySpeed,dragon,1));
            coolDown = this.getInitCoolDown();
        }
    }
    public void addCBulletS(String name, double xPos, double yPos, int radius, double xSpeed, double ySpeed,BossDragon dragon){
        if(coolDown <= 0){
            cBulletsShield.add(new CrystalBullet(name,xPos,yPos,radius,xSpeed,ySpeed,dragon,2));
            coolDown = this.getInitCoolDown();
        }
    }

    public void addBomb(double xPos, double yPos, int radius)
    {
        bombs.add(new Bomb(xPos, yPos, radius));
    }
    public void addFSBullet(String name, double xPos, double yPos, int radius, double xSpeed, double ySpeed, BossDragon dragon){
        if(fireStepCoolDown <= 0){
            bulletFStep.add(new FireStep(name,xPos,yPos,radius,xSpeed,ySpeed,dragon));
        }
    }
    public void addFollowingBullet(String name, double xPos, double yPos, int radius, double xSpeed, double ySpeed, BossDragon dragon){
        if(followingCoolDown <= 0){
            bulletsF.add(new FollowingBullet(name,xPos,yPos,radius,xSpeed,ySpeed,dragon));
            followingCoolDown = this.getInitFollowingCoolDown();
        }
    }
    public void removeBullet(Bullet bullet) {
        bullets.remove(bullet);
    }
    public void removeCBullet(CrystalBullet bullet){
        cBulletsShield.remove(bullet);
    }
    public void removeAllBullets() {
        for (int i = 0; i < bullets.size(); i++) {
            bullets.remove();
        }
        for (int i = 0; i < bombs.size(); i++) {
            bombs.remove();
        }
        for(int i = 0;i<cBulletsShield.size();i++){
            cBulletsShield.remove();
        }
        for(int i = 0;i<bulletFStep.size();i++){
            bulletFStep.remove();
        }
        for(int i = 0;i<bulletsF.size();i++){
            bulletsF.remove();
        }
    }
    public void removeBomb(Bomb bomb)
    {
        bombs.remove(bomb);
    }

}
