
import java.awt.Color;
import java.awt.Graphics;

public class Bomb extends Bullet{
    
    private GraphicsObject bomb;
    private VectorSprite explosion;
    private double fuse;
    private boolean explode;
    private int count;
    
    public Bomb(double xPos, double yPos, int radius)
    {
        super("",xPos, yPos, radius, 0, 0);
        this.fuse = 100;
        this.bomb = new GraphicsObject("bomb.png", this.getxPos(), this.getyPos(), 50, 50);
        this.explosion = new VectorSprite("explosion.png", this.getxPos(), this.getyPos(),185 , 185, radius);
        this.explode = false;
        this.explosion.setStartCell(0);
        this.explosion.setCurrentCell(0);
        this.explosion.setEndCell(6);
        this.count = 0;
        
    }
    
    @Override
    public void tick()
    {
        this.fuse--;
    }
    

    public double getFuse() {
        return fuse;
    }
    
    public void explode(int radius)
    {
        this.setRadius(radius);
        this.setxPos(this.getxPos() - radius/2 + 40);
        this.setyPos(this.getyPos() - radius/2 + 40);
        explosion.setxPos(this.getxPos());
        explosion.setyPos(this.getyPos());
        this.explode = true;
    }
    
    @Override
    public void draw(Graphics g)
    {
        if(explode)
        {
            explosion.draw(g);
            explosion.setChangeFrameDelay(explosion.getChangeFrameDelay()+4);
            explosion.animate();
            //g.fillOval((int)this.getxPos(), (int)this.getyPos(), this.getRadius(), this.getRadius());
        }
        else
        {
            bomb.draw(g);
            blink(g);
        }
        
    }

    public boolean isExplode() {
        return explode;
    }
    
    public void blink(Graphics g)
    {
        count++;
        if(count % 4 == 0)
        {
            g.setColor(new Color(255, 0, 0, 90));
            g.fillOval((int)this.getxPos() + 9, (int)this.getyPos() + 13, 33, 33);
        }
        if(count > 50)
        {
            this.count = 0;
        }
    }
   
    
    
}
