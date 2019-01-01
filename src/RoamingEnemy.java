
import java.awt.Graphics;
import java.util.ArrayList;

public class RoamingEnemy extends Enemy {
    
    public RoamingEnemy(String name, double xPos, double yPos, int width, int height, int radius, Player player, int direction, int speed) {
        super(name, xPos, yPos, width, height, radius, player);
        this.setHealth(15);
        this.setMaxHp(15);
        this.setAttack(1);
        this.setStartCell(0);
        this.setEndCell(2);
        this.setCurrentCell(0);
        this.setLoopCells(true);
        this.setHitWidth(38);
        this.setHitHeight(44);
        this.setPlayer(player);
        if(direction == 0)
        {
            this.setxSpeed(speed);
            this.setySpeed(speed);
        }
        else if(direction == 1)
        {
            this.setxSpeed(-speed);
            this.setySpeed(speed);
        }
        else if(direction == 2)
        {
            this.setxSpeed(speed);
            this.setySpeed(-speed);
        }
        else if(direction == 3)
        {
            this.setxSpeed(-speed);
            this.setySpeed(-speed);
        }
    }
    
    @Override
    public void move(double mult)
    {
        if(this.getxPos() <= 89)
        {
            this.setxSpeed(5);
        }
        else if(this.getxPos() >= 89 * 12 - this.getWidth())
        {
            this.setxSpeed(-5);
        }
        if(this.getyPos() <= 89)
        {
            this.setySpeed(5);
        }
        else if(this.getyPos() >= 89 * 8 - this.getHeight())
        {
            this.setySpeed(-5);
        }
        super.move(mult);
        changeSprite(1);
        this.animate();
    }
    
    @Override
    public void shoot(){
        
    }

    public void paint(Graphics g)
    {
        g.fillOval((int)this.getxPos(), (int)this.getyPos(), this.getRadius(), this.getRadius());
    }
    
    @Override
    public void spawnEnemy(ArrayList<Enemy> list) {
    }
    
    @Override
    public boolean collideO(Enemy object1, Obstacle object2) {
        return false;
    }

    @Override
    public void collideActionE(Enemy object1) {
       
    }

    @Override
    public void collideWObstacle(Obstacle obj) {
    }

    @Override
    public boolean collideE(Enemy object1) {
        return super.collideE(object1); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void changeSprite(double angle) {
        if(this.getxSpeed() > 0 && (this.getySpeed() > 0 || this.getySpeed() < 0)){
            this.setWidth(79);
            this.setHeight(76);
            this.setPicX(0);
            this.setPicY(76);
        }
         if(this.getxSpeed() < 0 && (this.getySpeed() > 0 || this.getySpeed() < 0)){
            this.setWidth(79);
            this.setHeight(76);
            this.setPicX(0);
            this.setPicY(0);
        }

    }
    
}
