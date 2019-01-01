
import java.awt.Color;
import java.awt.Graphics;

public class Wall extends Obstacle {

    public Wall(String name, double xPos, double yPos, int width, int height, int radius) {
        super(name, xPos, yPos, width, height, radius);
    }

    public void draw(Graphics g) {
        super.draw(g);
        g.setColor(Color.BLACK);
    }

    public <T extends VectorSprite> void collideAction(T object) {
        //top of the object
        if (object.getHitCenterY() + object.getRadius() / 2 <= this.getyPos()) {
            object.setySpeed(-object.getMaxSpeed() / 5.7);
        }
        //bottom of the object
        if (object.getHitCenterY() + object.getRadius() / 2 >= this.getyPos() + this.getHeight()) {
            object.setySpeed(object.getMaxSpeed() / 5.7);
        }
        //left of the object
        if (object.getHitCenterX() + object.getRadius() / 2 <= this.getxPos()) {
            object.setxSpeed(-object.getMaxSpeed() / 5.7);

        }
        //right of the object
        if (object.getHitCenterX() + object.getRadius() / 2 >= this.getxPos() + this.getWidth()) {
            object.setxSpeed(object.getMaxSpeed() / 5.7);
        }
    }
    public void collideActionO(Enemy enemy){
        enemy.setxSpeed(-enemy.getxSpeed());
        enemy.setySpeed(-enemy.getySpeed());
    }

//        //top of the object | bottom of this
//        if (enemy.getyPos() <= this.getyPos()+this.getHeight()) {
//            enemy.setyPos(this.getyPos()+this.getHeight());
//        }
//        //bottom of the object | top of this
//        if (enemy.getyPos()+this.getRadius() >= this.getyPos()) {
//            enemy.setyPos(this.getyPos()-enemy.getRadius());
//        }
//        //left of the object | right of this
//        if (enemy.getxPos() <= this.getxPos()+this.getWidth()) {
//            enemy.setxPos(this.getxPos()+this.getWidth());
//
//        }
//        //right of the object | left of this
//        if (enemy.getxPos() + enemy.getRadius() >= this.getxPos()) {
//            enemy.setxPos(this.getxPos()-enemy.getRadius());
//        }

    

}
