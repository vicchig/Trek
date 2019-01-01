
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class BossSlime extends FollowingEnemy{
    
    private boolean stage1,stage2,stage3, stage4, stage5,specialMoveActive;
    
    public BossSlime(String name, double xPos, double yPos, int width, int height, int radius, Player player) {
        super(name, xPos, yPos, width, height, radius, player);
        this.setMaxHp(200);
        this.setHealth(200);
        this.setAttack(1);
        stage1 = true;
        stage2 = true;
        stage3 = true;
        stage4 = true;
        stage5 = true;
        bController.setInitCoolDown(0);
        bController.setBulletRange(300);
        this.setPlayer(player);
        this.setLoopCells(true);
        this.setStartCell(0);
        this.setCurrentCell(0);
        this.setEndCell(4);
        this.setHitHeight(114);
        this.setHitWidth((int)this.getWidth());
    }
    /**
     * Precondition:
     *  None.
     * Description:
     *  Shoots radial attacks based on distance moved.
     * Postcondition:
     *  The boss has shot a radial attack.
     */
    public void shoot(){
        this.calcDistanceMoved();

        //Radial attack
        if(this.getDistanceMoved() >= 200){
            for(double i = 0;i<Math.PI*2;i+=0.4){
                bController.addBullet("enemybullet.png",this.getxPos()+this.getRadius()/2+(this.getRadius()/2*Math.cos(i)),this.getyPos()+this.getRadius()/2+(this.getRadius()/2*Math.sin(i)),10,Math.cos(i)*2,Math.sin(i)*2);
            }
            this.setDistanceMoved(0);
        }
    }
    /**
     * Precondition:
     *  @param mult > 0
     * Description:
     *  Moves the boss and animates it.
     * Postcondition:
     *  The boss has been moved and animated.
     */
    public void move(double mult) {
        super.move(mult);
        this.animate();
        
    }
    /**
     * Precondition:
     *  None.
     * Description:
     *  Checks if spawn minions should be spawned based on player health.
     * Postcondition:
     *  If the boss health it at the appropriate level, spawnEnemy is called.
     */
    public void spawnStage(ArrayList<Enemy> list){
        if(this.getHealth() <= 150 && stage1){
            spawnEnemy(list);
            stage1 = false;
        }
        else if(this.getHealth() <= 100 && stage2){
            spawnEnemy(list);
            stage2 = false;
        }
        else if(this.getHealth() <= 50 && stage3){
            spawnEnemy(list);
            stage3 = false;
        }
        else if(this.getHealth() <= 20 && stage4){
            spawnEnemy(list);
            stage4 = false;
        }
    }
    /**
     * Precondition:
     *  None.
     * Description:
     *  Spawns enemy minions for the boss.
     * Postcondition:
     *  Enemy slimes have been spawned.
     */
    public void spawnEnemy(ArrayList<Enemy> list) {
        if (list.isEmpty()) {
            list.add(new MinionSlime("/slimes/greenslime.png", 100, 100, 74, 62, 38, this.getPlayer()));
            list.add(new MinionSlime("/slimes/redslime.png", 800,600, 74, 62, 38, this.getPlayer()));
            list.add(new MinionSlime("/slimes/blueslime.png", 400, 600,74, 62, 38, this.getPlayer()));
            list.add(new MinionSlime("/slimes/yellowslime.png", 500, 200, 74, 62, 38, this.getPlayer()));
        }
    }
    /**
     * Precondition:
     *  None.
     * Description:
     *  Returns the name of the boss.
     * Postcondition:
     *  The name of the boss has been returned.
     */
    @Override
    public String toString() {
        String name = "Sandval, the Malformed Alchemist";
        return name;
    }
    /**
     * Precondition:
     *  None.
     * Description:
     *  Draws the boss and its health bar.
     * Postcondition:
     *  The boss and its health bar have been drawn.
     */
    @Override
    public void draw(Graphics g) {
        bController.draw(g);
        super.draw(g);
//        g.setColor(new Color(0,0,0,150));
//        g.fillRect(1157/2-330, 801-89-40, 660, 30);
//        g.setColor(new Color(144,0,32,100));
//        g.fillRect(1157/2-330, 801-89-40, (int) (660 * (this.getHealth()/this.getMaxHp())), 30);
//        g.setFont(new Font("Chiller", Font.BOLD, 40));
//        g.setColor(Color.WHITE);
//        g.drawString(this.toString(),1157/2-330,801-89-30-15);
    }

}
