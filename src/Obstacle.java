
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Obstacle extends VectorSprite {

    private double originalxPos, originalyPos;
    private Rectangle hitBoxTop, hitBoxBottom,hitBoxRight,hitBoxLeft;
    public Obstacle(String name, double xPos, double yPos, int width, int height, int radius) {
        super(name, xPos, yPos, width, height, radius);
        originalxPos = xPos;
        originalyPos = yPos;
        hitBoxTop = new Rectangle((int)this.getxPos(),(int)this.getyPos(),(int)this.getWidth(),1);
        hitBoxBottom = new Rectangle((int)this.getxPos(),(int)this.getyPos()+(int)this.getHeight()-10,(int)this.getWidth(),1);
        hitBoxRight = new Rectangle((int)this.getxPos()+(int)this.getWidth()-10,(int)this.getyPos(),1,(int)this.getHeight());
        hitBoxLeft = new Rectangle((int)this.getxPos(),(int)this.getyPos(),1,(int)this.getHeight());

    }

    public void draw(Graphics g) {
        super.draw(g);
        g.setColor(Color.RED);
    }

    public Rectangle getHitBoxBottom() {
        return hitBoxBottom;
    }

    public Rectangle getHitBoxLeft() {
        return hitBoxLeft;
    }

    public Rectangle getHitBoxRight() {
        return hitBoxRight;
    }

    public Rectangle getHitBoxTop() {
        return hitBoxTop;
    }


    public void collideAction(Player object) {
        if (!object.isFlying()) {
            //top of the object
            if (object.getHitCenterY() + object.getRadius() / 2 <= this.getyPos()) {
                object.setySpeed(-object.getMaxSpeed()/5.7);
            }
            //bottom of the object
            if (object.getHitCenterY() + object.getRadius() / 2 >= this.getyPos() + this.getHeight()) {
                object.setySpeed(object.getMaxSpeed()/5.7);
            }
            //left of the object
            if (object.getHitCenterX() + object.getRadius() / 2 <= this.getxPos()) {
                object.setxSpeed(-object.getMaxSpeed()/5.7);

            }
            //right of the object
            if (object.getHitCenterX() + object.getRadius() / 2 >= this.getxPos() + this.getWidth()) {
                object.setxSpeed(object.getMaxSpeed()/5.7);
            }
        }
    }
    
    public boolean bulletCollision(Bullet object) {
        double xCenter, yCenter;
        double xDistance, yDistance;
        yCenter = object.getyPos()+object.getRadius()/2;
        xCenter = object.getxPos()+object.getRadius()/2;
        xDistance = xCenter + object.getRadius() / 2 - Math.max(this.getxPos() + 15, Math.min(xCenter + object.getRadius() / 2, this.getxPos() + 15 + this.getWidth() - 30));
        yDistance = yCenter + object.getRadius() / 2 - Math.max(this.getyPos() + 15, Math.min(yCenter + object.getRadius() / 2, this.getyPos() + 15 + this.getHeight() - 30));
        boolean collide = (xDistance * xDistance + yDistance * yDistance) < (object.getRadius() / 2 * object.getRadius() / 2);
        
        return collide;
    }
    
    public boolean isRock()
    {
        if(this.getId().equals("obstacle_rock.png"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public void collideActionB(Enemy enemy){
        enemy.setxSpeed(-enemy.getxSpeed()*2);
        enemy.setySpeed(-enemy.getySpeed()*2);
    }
//    public void collideActionO(Enemy enemy, Player player){
//        double oCenterX, oCenterY;
//        double eCenterX, eCenterY;
//        
//        oCenterX = this.getxPos() + this.getWidth()/2;
//        oCenterY = this.getyPos() + this.getHeight()/2;
//        
//        eCenterX = this.getxPos() + this.getRadius()/2;
//        eCenterY = this.getyPos() + this.getRadius()/2;
//        
//        //left of object
//        if(eCenterX <= oCenterX){
//            enemy.moveX(-enemy.getxSpeed());
//        }
//        //right
//        if(eCenterX >= oCenterX){
//            enemy.moveX(-enemy.getxSpeed());
//        }
//        //top
//        if(eCenterY <= oCenterY){
//            enemy.moveY(-enemy.getySpeed());
//
//        }
//        //down
//        if(eCenterY >= oCenterY){
//             enemy.moveY(-enemy.getySpeed());
//        }
//    }
    public void collideActionLeftSide(Enemy enemy){
        enemy.setxPos(this.getxPos()-enemy.getWidth()-2);
    }
    public void collideActionRightSide(Enemy enemy){
        enemy.setxPos(this.getxPos()+this.getWidth()+2);
    }
    public void collideActionTopSide(Enemy enemy){
        enemy.setyPos(this.getyPos()-enemy.getHeight()-2);
    }
    public void collideActionBottomSide(Enemy enemy){
        enemy.setyPos(this.getyPos()+this.getHeight()+5);
    }
    
    public double getOriginalxPos() {
        return originalxPos;
    }

    public double getOriginalyPos() {
        return originalyPos;
    }

    

}
