
import java.awt.Graphics;
import java.util.ArrayList;


public abstract class Character extends VectorSprite{
    
    private double health, attack;
    private double bulletSpeedMulti, fireRateMulti, damageMulti;
    protected BulletController bController;
    private double maxHp, currentHp;
    private boolean dead;
    public Character(String name, double xPos, double yPos,int width, int height,int radius) {
        super(name, xPos, yPos,width, height, radius);
        bController = new BulletController();
        bulletSpeedMulti = 1;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  Sets dead to the value of the passed argument.
     * Postcondition:
     *  Dead has be reassigned.
     */
    public void setDead(boolean dead) {
        this.dead = dead;
    }
    /**
     * Precondition:
     *  None.
     * Description:
     *  Returns @param dead.
     * Postcondition:
     *  @param dead has been returned.
     */
    public boolean isDead() {
        return dead;
    }
    /**
     * Precondition:
     *  None.
     * Description:
     *  Returns the value of maxHp.
     * Postcondition:
     *  The value of maxHp has been returned.
     */
    public double getMaxHp() {
        return maxHp;
    }
    /**
     * Precondition:
     *  None.
     * Description:
     *  Sets the value of maxHp to the value of the passed argument.
     * Postcondition:
     *  The value of maxHp has been reassigned.
     */
    public void setMaxHp(double maxHp) {
        this.maxHp = maxHp;
    }

    /**
     * Precondition:
     *  None.
     * Description:
     *  An attack method used for every enemy that can be instantiated.
     * Postcondition:
     *  None.
     */
    public abstract void shoot();
    
    /**
     * Precondition:
     *  None.
     * Description:
     *  Move method used for every enemy.
     * Postcondition:
     *  None.
     */
    public abstract void move(double mult);
    
    /**
     * Precondition:
     *  None.
     * Description:
     *  Returns the speed multiplyer for bullets.
     * Postcondition:
     *  The value of the bullet speed multiplyer has been returned.
     */
    public double getBulletSpeedMulti() {
        return bulletSpeedMulti;
    }
    
    /**
     * Precondition:
     *  @param damage > 0
     * Description:
     *  Sets the attack value of the object to @param damage.
     * Postcondition:
     *  The value of the attack state has been changed.
     */
    public void setAttack(int damage) {
        this.attack = damage;
    }
    
    /**
     * Precondition:
     *  None.
     * Description:
     *  Returns the value of the attack state.
     * Postcondition:
     *  The value of the attack state has been returned.
     */
    public double getAttack() {
        return attack;
    }
    
    /**
     * Precondition:
     *  None.
     * Description:
     *  Returns the value of health.
     * Postcondition:
     *  The value of health has been returned.
     */
    public double getHealth() {
        return health;
    }

    /**
     * Precondition:
     *  @param health > 0
     * Description:
     *  Sets the value of health to @param health.
     * Postcondition:
     *  The value of health has been changed.
     */
    public void setHealth(int health) {
        this.health = health;
    }
    
    /**
     * Precondition:
     *  @param damage > 0.
     * Description:
     *  Decreases the health of the object by the value of @param damage.
     * Postcondition:
     *  The value of health has been decreased.
     */ 
    public void damaged(double damage){
        this.health -= damage;
    }
    
    /**
     * Precondition:
     *  None.
     * Description:
     *  Draws the bullets of the object and its sprite.
     * Postcondition:
     *  The bullets and sprite of the object have been drawn.
     */
    public void draw(Graphics g){
        bController.draw(g);
        super.draw(g);
    }
}
