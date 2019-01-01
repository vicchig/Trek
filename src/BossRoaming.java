
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

public class BossRoaming extends RoamingEnemy{
    
    private boolean phase1, phase2, phase1Done, phase2Done;
    
    public BossRoaming(String name, double xPos, double yPos, int width, int height, int radius, Player player)
    {
        super(name, xPos, yPos, width, height, radius, player, 0, 5);
        this.setMaxHp(100);
        this.setHealth(100);
        this.setAttack(1);
        phase1 = false;
        phase2 = false;
        phase1Done = false;
        phase2Done = false;
        this.setCurrentCell(0);
        this.setStartCell(0);
        this.setEndCell(2);
        setLoopCells(true);
        this.setRadius(radius);
        
    }
    
    @Override
    public String toString() {
        String name = "Regret";
        return name;
    }
    
    @Override
    public void draw(Graphics g) {
        super.draw(g);
//        g.setColor(new Color(0,0,0,150));
//        g.fillRect(1157/2-330, 801-89-40, 660, 30);
//        g.setColor(new Color(144,0,32,100));
//        g.fillRect(1157/2-330, 801-89-40, (int) (660 * (this.getHealth()/this.getMaxHp())), 30);
//        g.setFont(new Font("Chiller", Font.BOLD, 40));
//        g.setColor(Color.WHITE);
//        g.drawString(this.toString(),1157/2-330,801-89-30-15);
    }
    public void spawnStage(ArrayList<Enemy> enemies){
        if(getHealth()<= 66 && !phase1)
        {
            spawnEnemy(enemies);
            phase1 = true;
        }
        if(getHealth()<= 33 && !phase2)
        {
            spawnEnemy(enemies);
            phase2 = true;
        }
    }
    @Override
    public void move(double mult)
    {
        
        if(this.getxPos() <= 89)
        {
            this.setxSpeed(1);
        }
        else if(this.getxPos() >= 89 * 12 - this.getWidth())
        {
            this.setxSpeed(1);
        }
        if(this.getyPos() <= 89)
        {
            this.setySpeed(1);
        }
        else if(this.getyPos() >= 89 * 8 - this.getHeight())
        {
            this.setySpeed(-1);
        }
          
        super.move(mult);
        changeSprite(1);
        this.animate();
    }

    public boolean isPhase1Done() {
        return phase1Done;
    }

    public boolean isPhase2Done() {
        return phase2Done;
    }

    public void setPhase1Done(boolean phase1Done) {
        this.phase1Done = phase1Done;
    }

    public void setPhase2Done(boolean phase2Done) {
        this.phase2Done = phase2Done;
    }

    public boolean isPhase1() {
        return phase1;
    }

    public boolean isPhase2() {
        return phase2;
    }

    public void setPhase1(boolean phase1) {
        this.phase1 = phase1;
    }

    public void setPhase2(boolean phase2) {
        this.phase2 = phase2;
    }

    public void spawnEnemy(ArrayList<Enemy> list) {
        int location = 0;
        if (list.isEmpty()) {
            list.add(new RoamingEnemy("eyeballenemyG.png", this.getxPos() + 50, this.getyPos(), 79, 76, 30, this.getPlayer(), 0, 10));
            list.add(new RoamingEnemy("eyeballenemyG.png", this.getxPos() - 50, this.getyPos(), 79, 76, 30, this.getPlayer(), 1, 6));
            list.add(new RoamingEnemy("eyeballenemyG.png", this.getxPos(), this.getyPos() + 50, 79, 76, 30, this.getPlayer(), 2, 3));
            //list.add(new RoamingEnemy("eyeballenemyG.png", this.getxPos(), this.getyPos() - 50, 79, 76, 30, this.getPlayer(), 3, 9));
            
        }
    }

    @Override
    public void changeSprite(double angle) {
        if(this.getxSpeed() > 0 && (this.getySpeed() > 0 || this.getySpeed() < 0)){
            this.setPicX(0);
            this.setPicY(190);
        }
        else if(this.getxSpeed() < 0 && (this.getySpeed() > 0 || this.getySpeed() < 0)){
            this.setPicX(0);
            this.setPicY(0);
        }
    }
}
