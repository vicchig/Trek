
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ChargingEnemy extends Enemy {

    private Player player;
    private int direction = 1;
    private int iterationsCount = 0;
    
    public ChargingEnemy(String name, double xPos, double yPos, int width, int height, int radius, Player player) {
        super(name, xPos, yPos, width, height, radius, player);
        this.setxSpeed(1);
        this.setySpeed(1);
        this.player = player;
    }
    public void spawnEnemy(ArrayList<Enemy> list){}
    public int randNum(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }

    public double randNum(double min, double max) {
        return 0;
    }
    public void changeSprite(double angle){}
    public void move() {
        // 1 - UP, 2 - RIGHT, 3 - DOWN, 4 - LEFT

        if (direction == 1) {
            this.setyPos(this.getyPos() - this.getySpeed());
        } else if (direction == 2) {
            this.setxPos(this.getxPos() + this.getxSpeed());
        } else if (direction == 3) {
            this.setyPos(this.getyPos() + this.getySpeed());
        } else if (direction == 4) {
            this.setxPos(this.getxPos() - this.getxSpeed());
        }
        
        //direction = randNum(1,4);

        
        if (this.getyPos() >= player.getyPos() - 20 && this.getyPos() < player.getyPos() + 20 ) {
            if (this.getxPos() < player.getxPos()) {
                direction = 2;
            } else {
                direction = 4;
            }
            this.setxSpeed(3);
        } else if (this.getxPos() >= player.getxPos() - 20 && this.getxPos() < player.getxPos() + 20 ) {
            if (this.getyPos() < player.getyPos()) {
                direction = 3;
            } else {
                direction = 1;
            }
            this.setySpeed(3);
            
            
        } else if (iterationsCount> randNum(20,100)) {
            direction = randNum(1,4);
            iterationsCount = 0;
        } else {
            this.setySpeed(1);
            this.setxSpeed(1);
            iterationsCount++;
        }
        
        if (this.getxPos() < 89) {
            direction = 2;
        } else if (this.getxPos() > 1068) {
            direction = 4;
        }
        if (this.getyPos() < 89) {
            direction = 3;
        } else if (this.getyPos() > 712) {
            direction = 1;
        }
    }

    @Override
    public <T extends VectorSprite> boolean collideE(T object1, T object2) {
        return false;
    }

    @Override
    public boolean collideO(Enemy object1, Obstacle object2) {
        return false;
    }

    @Override
    public boolean collideP(Player object1) {
        return false;
    }
    
    @Override
    public void collideActionE(Enemy object1) {
    }

    @Override
    public boolean collideE(Enemy object1) {
        return false;
    }

    @Override
    public void collideWObstacle(Obstacle obj) {
    }
    
    public void shoot() {

    }

    public void collisionAction(Obstacle obstacle) {

    }
    

    public void draw(Graphics g) {
        g.fillRect((int) this.getxPos(), (int) this.getyPos(), (int) this.getWidth(), (int) this.getHeight());
    }
    
    
}
