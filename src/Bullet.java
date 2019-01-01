
import java.awt.Graphics;

public class Bullet extends VectorSprite{
    
    private double damage;
    private String name;
    public Bullet (String name, double xPos, double yPos, int radius, double xSpeed, double ySpeed) {
        //instead of 0,0 for width and height need to pass the dimension sof the sprite to cut out
        super(name,xPos,yPos,0,0,radius);
        this.setxSpeed(xSpeed);
        this.setySpeed(ySpeed);
        this.setCurrentCell(0);
        this.setEndCell(3);
        this.setStartCell(0);
        this.name = name;
        if(this.name.equals("enemybullet.png")){
            this.setWidth(40);
            this.setHeight(40);
        }
        else if(this.name.equals("playerbullet.png")){
            this.setWidth(36);
            this.setHeight(40);
        }

        this.setLoopCells(true);
    }
    
   public void tick(){
       this.moveX(this.getxSpeed());
       this.moveY(this.getySpeed());
       this.countDistance(1);
       this.animate();
   }
   
   public void draw(Graphics g){
       super.draw(g);
   }
   
}