
public class CrystalBullet extends Bullet{
    private double angle;
    private BossDragon dragon;
    private int followingRadius;
    private int type;
    public CrystalBullet(String name, double xPos, double yPos, int radius, double xSpeed, double ySpeed,BossDragon dragon,int type) {
        super(name, xPos, yPos, radius, xSpeed, ySpeed);
        this.type = type;
        this.setCurrentCell(0);
        this.setEndCell(3);
        this.setStartCell(0);
        this.setWidth(38);
        this.setHeight(37);
        this.setPicY(0);
        this.setPicX(0);
        this.dragon = dragon;
        followingRadius = 0;
        angle = Math.atan((this.getyPos()-dragon.getyPos()-dragon.getHeight()/2)/(this.getxPos()-dragon.getxPos()-dragon.getWidth()/2));
        if((this.getxPos()-dragon.getxPos()-dragon.getWidth()/2) < 0){
            if((this.getyPos()-dragon.getyPos()-dragon.getHeight()/2)< 0){
                angle = (-Math.PI/2)- (Math.PI/2 - Math.abs(angle));
            }
            else{
                angle = (Math.PI) - Math.abs(angle); 
            }
        }

    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public BossDragon getDragon() {
        return dragon;
    }

    public void setDragon(BossDragon dragon) {
        this.dragon = dragon;
    }

    public int getType() {
        return type;
    }
    
    @Override
    public void tick(){
        //reducing the angle increment, reduces rotation speed
        angle += Math.PI/100;
        followingRadius++;
        this.setxPos(dragon.getxPos()+dragon.getWidth()/2+150*Math.cos(angle));
        this.setyPos(dragon.getyPos()+dragon.getHeight()/2+150*Math.sin(angle));

       this.countDistance(1);
       this.animateY();

    }
    public void tickSpecial(){
        //reducing the angle increment, reduces rotation speed
        angle += Math.PI/100;
        followingRadius++;
        this.setxPos(dragon.getxPos()+dragon.getWidth()/2+followingRadius*Math.cos(angle));
        this.setyPos(dragon.getyPos()+dragon.getHeight()/2+followingRadius*Math.sin(angle));

       this.countDistance(1);
       this.animateY();
    }

}
/*
A very cool effect happens when the radius is set to change every time as in:
path radius is supposedly the radius between the object and the center of its rotation
        pathRadius = (int)(Math.sqrt((this.getxPos()-dragon.getxPos()-dragon.getWidth()/2)*(this.getxPos()-dragon.getxPos()-dragon.getWidth()/2)+
                (this.getyPos() - dragon.getyPos()-dragon.getHeight()/2)*(this.getyPos() - dragon.getyPos()-dragon.getHeight()/2)));
        this.setxPos(dragon.getxPos()+dragon.getWidth()/2+pathRadius*Math.cos(angle));
        this.setyPos(dragon.getyPos()+dragon.getHeight()/2+pathRadius*Math.sin(angle));
        This way the bullets rotate around a central bullet adn continuously get closer to it. Idea for another attack maybe?
*/