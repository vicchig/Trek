
public class FollowingBullet extends CrystalBullet{

    public FollowingBullet(String name, double xPos, double yPos, int radius, double xSpeed, double ySpeed, BossDragon dragon) {
        super(name, xPos, yPos, radius, xSpeed, ySpeed, dragon,0);
        this.setWidth(40);
        this.setHeight(40);
    }
    public void tick(){
        this.setxSpeed(this.getDragon().getxSpeed()*7);
        this.setySpeed(this.getDragon().getySpeed()*7);
        this.moveX(this.getxSpeed());
        this.moveY(this.getySpeed());
        this.countDistance(1);
        this.animate();
    }
}
