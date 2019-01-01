
import java.awt.Color;
import java.awt.Graphics;

public class SpikeTrap extends Obstacle {

    private double damage;
    private boolean active;
    private int stateCount;

    public SpikeTrap(String name, double xPos, double yPos, int width, int height, int radius) {
        super(name, xPos, yPos, width, height, radius);
        active = true;
        this.setStartCell(0);
        this.setEndCell(1);
        this.setCurrentCell(0);
        damage = 1;
    }

    public void draw(Graphics g) {
        if (stateCount > 100) {
            stateCount = 0;
            active = !active;
        }
        stateCount++;
        if (active) {
            this.setPicX(0);
        } else {
            this.setPicX(89);
        }
        
        super.draw(g);
        g.setColor(Color.BLACK);
    }

    public void collideAction(Player object) {
        double xDistance, yDistance;
        xDistance = object.getHitCenterX() + object.getRadius() / 2 - Math.max(this.getxPos() + 15, Math.min(object.getHitCenterX() + object.getRadius() / 2, this.getxPos() + 15 + this.getWidth() - 30));
        yDistance = object.getHitCenterY() + object.getRadius() / 2 - Math.max(this.getyPos() + 15, Math.min(object.getHitCenterY() + object.getRadius() / 2, this.getyPos() + 15 + this.getHeight() - 30));
        boolean collide = (xDistance * xDistance + yDistance * yDistance) < (object.getRadius() / 2 * object.getRadius() / 2);

        
        if (active && !object.isFlying() && !object.isDamageTaken() && collide) {
            object.damaged(damage);
            object.setDamageTaken(true);
        }
    }

    @Override
    public void collideActionB(Enemy enemy) {
    }
    
    public void collideActionLeftSide(Enemy enemy){
    }
    public void collideActionRightSide(Enemy enemy){
    }
    public void collideActionTopSide(Enemy enemy){
    }
    public void collideActionBottomSide(Enemy enemy){
    }
    public boolean bulletCollision(Bullet object) {
        return false;
    }
    
}
